/*
Copyright (c) 2005, Dale Anson
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.
* Neither the name of the <ORGANIZATION> nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package sidekick.java;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;

import sidekick.java.node.*;
import sidekick.java.options.*;
import sidekick.java.parser.*;
import sidekick.java.tools.CheckImports;

import sidekick.util.ElementUtil;
import sidekick.util.Location;
import sidekick.util.Range;

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.msg.*;
import errorlist.*;

import sidekick.SideKickCompletion;
import sidekick.SideKickParser;
import sidekick.SideKickParsedData;

public class JavaParser extends SideKickParser implements EBComponent {
    private View currentView = null;
    private JBrowseOptionDialog optionDialog;
    private GeneralOptions options;
    private MutableFilterOptions filterOpt;
    private MutableDisplayOptions displayOpt;
    private boolean sorted = true;      // are the tree nodes sorted by type?

    private JavaCompletionFinder completionFinder = null;

    public static final int JAVA_PARSER = 1;
    public static final int JAVACC_PARSER = 2;

    private int parser_type = JAVA_PARSER;

    private DefaultErrorSource myErrorSource = JavaSideKickPlugin.ERROR_SOURCE;

    /**
     * Defaults to parsing java files.
     */
    public JavaParser() {
        this( JAVA_PARSER );
    }

    /**
     * @param type one of 'java' or 'javacc'
     */
    public JavaParser( int type ) {
        super( type == JAVA_PARSER ? "java" : "javacc" );
        loadOptions();
        switch ( type ) {
            case JAVACC_PARSER:
                parser_type = JAVACC_PARSER;
                break;
            default:
                parser_type = JAVA_PARSER;
        }
    }

    /**
     * @return true if the options have changed and were reloaded, false if there
     * was no need to reload the options because they haven't changed.
     */
    private boolean loadOptions() {
        GeneralOptions tmp_options = new GeneralOptions();
        tmp_options.load( new JEditPropertyAccessor() );
        if ( options == null || !options.equals( tmp_options ) ) {
            options = tmp_options;
            filterOpt = options.getFilterOptions();
            displayOpt = options.getDisplayOptions();
            // re-init the labeler
            TigerLabeler.setDisplayOptions( displayOpt );
            return true;
        }
        return false;
    }


    /**
     * This method is called when a buffer using this parser is selected
     * in the specified view.
     * @param editPane The edit pane
     * @since SideKick 0.3.1
     */
    public void activate( EditPane editPane ) {
        super.activate( editPane );
        currentView = editPane.getView();
        EditBus.addToBus( this );
    }

    public void deactivate( EditPane editPane ) {
        super.deactivate( editPane );
    }

    /**
     * Reparse if the option settings have changed.
     */
    public void handleMessage( EBMessage msg ) {
        // reparse on properties changed
        if ( ( msg instanceof PropertiesChanged ) && loadOptions() ) {
            parse();
        }
        // check imports on buffer loaded
        /* CheckImports is deprecated, use PMD plugin instead
        else if (msg instanceof BufferUpdate) {
            BufferUpdate bu = (BufferUpdate)msg;
            if (BufferUpdate.LOADED.equals(bu.getWhat())) {
                Object o = bu.getBuffer().getProperty("javasidekick.compilationUnit");
                if (o != null && (o instanceof CUNode) ) {
                    CUNode compilationUnit = (CUNode)o;
                    // maybe check imports -- should have an option setting for this
                    // this is slow, so commented out until I can improve the performance
                    if ("true".equals(jEdit.getProperty("javasidekick.checkImports"))) {
                        CheckImports tool = new CheckImports();
                        tool.checkImports( compilationUnit );
                    }
                }
            }
        }
        */
    }

    /**
     * Parse the current buffer in the current view.
     */
    public void parse() {
        if ( currentView != null ) {
            parse( currentView.getBuffer(), null );
        }
    }

    /**
     * Parse the given buffer.
     * TODO: can this be adapted to parse say java code from within a jsp file?
     * @param buffer the buffer to parse.
     * @return a CUNode representing a java compilation unit.
     */
    public CUNode parse( Buffer buffer ) {
        ByteArrayInputStream input = null;
        String filename = buffer.getPath();
        TigerParser parser = null;
        CUNode compilationUnit = null;
        try {
            // read the buffer
            input = new ByteArrayInputStream( buffer.getText( 0, buffer.getLength() ).getBytes() );
            parser = new TigerParser( input );
            int tab_size = buffer.getTabSize();

            // do the parse
            switch ( parser_type ) {
                case JAVACC_PARSER:
                    compilationUnit = parser.getJavaCCRootNode( tab_size );
                    break;
                default:
                    compilationUnit = parser.getJavaRootNode( tab_size );
                    break;
            }

            // set some properties
            compilationUnit.setName( buffer.getName() );
            compilationUnit.setResults( parser.getResults() );
            compilationUnit.setStart( ElementUtil.createStartPosition( buffer, compilationUnit ) );
            compilationUnit.setEnd( ElementUtil.createEndPosition( buffer, compilationUnit ) );
            buffer.setProperty("javasidekick.compilationUnit", compilationUnit);
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            try {
                input.close();
            }
            catch ( Exception e ) {
                // not to worry
            }
        }
        return compilationUnit;
    }

    /**
     * Parse the contents of the given buffer.
     * @param buffer the buffer to parse
     */
    public SideKickParsedData parse( Buffer buffer, DefaultErrorSource errorSource ) {
        ByteArrayInputStream input = null;
        String filename = buffer.getPath();
        SideKickParsedData parsedData = new JavaSideKickParsedData( filename );
        DefaultMutableTreeNode root = parsedData.root;
        TigerParser parser = null;
        CUNode compilationUnit = null;
        try {
            // re-init the labeler
            TigerLabeler.setDisplayOptions( displayOpt );

            if ( buffer.getLength() <= 0 )
                return parsedData;
            // read the source code directly from the Buffer rather than from the
            // file.  This means:
            // 1) a modifed buffer can be parsed without a save
            // 2) reading the buffer should be faster than reading from the file, and
            // 3) jEdit has that 'gzip file on disk' option which won't parse.
            input = new ByteArrayInputStream( buffer.getText( 0, buffer.getLength() ).getBytes() );
            parser = new TigerParser( input );
            int tab_size = buffer.getTabSize();
            switch ( parser_type ) {
                case JAVACC_PARSER:
                    compilationUnit = parser.getJavaCCRootNode( tab_size );
                    break;
                default:
                    compilationUnit = parser.getJavaRootNode( tab_size );
                    break;
            }
            if ("true".equals(jEdit.getProperty("javasidekick.dump"))) {
                System.out.println(compilationUnit.dump());
            }

            // compilationUnit is root node
            compilationUnit.setName( buffer.getName() );
            compilationUnit.setFilename( filename );
            compilationUnit.setResults( parser.getResults() );
            compilationUnit.setStart( ElementUtil.createStartPosition( buffer, compilationUnit ) );
            compilationUnit.setEnd( ElementUtil.createEndPosition( buffer, compilationUnit ) );
            root.setUserObject( compilationUnit );
            buffer.setProperty("javasidekick.compilationUnit", compilationUnit);

            // maybe show imports
            if ( filterOpt.getShowImports() == true ) {
                List imports = compilationUnit.getImportNodes();
                if ( imports != null && !imports.isEmpty() ) {
                    Collections.sort( imports, new Comparator() {
                                public int compare( Object a, Object b ) {
                                    return ( ( TigerNode ) a ).getName().compareTo( ( ( TigerNode ) b ).getName() );
                                }
                            }
                                    );
                    DefaultMutableTreeNode importsNode = new DefaultMutableTreeNode( "Imports" );
                    root.add( importsNode );
                    for ( Iterator it = imports.iterator(); it.hasNext(); ) {
                        TigerNode anImport = ( TigerNode ) it.next();
                        anImport.setStart( ElementUtil.createStartPosition( buffer, anImport ) );
                        anImport.setEnd( ElementUtil.createEndPosition( buffer, anImport ) );
                        importsNode.add( new DefaultMutableTreeNode( anImport ) );
                    }
                }
            }

            // show constructors, fields, methods, etc
            if ( compilationUnit.getChildren() != null ) {
                Collections.sort( compilationUnit.getChildren(), nodeSorter );
                for ( Iterator it = compilationUnit.getChildren().iterator(); it.hasNext(); ) {
                    TigerNode child = ( TigerNode ) it.next();
                    if ( canShow( child ) ) {
                        child.setStart( ElementUtil.createStartPosition( buffer, child ) );
                        child.setEnd( ElementUtil.createEndPosition( buffer, child ) );
                        DefaultMutableTreeNode cuChild = new DefaultMutableTreeNode( child );
                        root.add( cuChild );
                        addChildren( buffer, cuChild, child );
                    }
                }
            }
        }
        catch ( ParseException e ) {
            // removed exception handling, all ParseExceptions are now caught
            // and accumulated in the parser, then dealt with in handleErrors.
        }
        finally {
            try {
                input.close();
            }
            catch ( Exception e ) {
                // not to worry
            }
        }

        /* only handle errors when buffer is saved or code completion is off. Otherwise,
        there will be a lot of spurious errors shown when code completion is on and the
        user is in the middle of typing something. */
        boolean complete_instant = jEdit.getBooleanProperty("sidekick.complete-instant.toggle", true);
        boolean complete_delay = jEdit.getBooleanProperty("sidekick.complete-delay.toggle", true);
        boolean complete_on = complete_instant || complete_delay;
        if ( !complete_on && errorSource != null ) {
            handleErrors( errorSource, parser, buffer );

            /*  remove this, CheckImports is deprecated
            // maybe check imports -- should have an option setting for this
            // this is slow, so it will cause a huge problem when code completion is
            // on.
            if ("true".equals(jEdit.getProperty("javasidekick.checkImports"))) {
                CheckImports tool = new CheckImports();
                tool.checkImports( compilationUnit );
            }
            */
        }
        return parsedData;
    }

    // the parser accumulates errors as it parses.  This method passed them all to
    // the ErrorList plugin.
    private void handleErrors( DefaultErrorSource errorSource, TigerParser parser, Buffer buffer ) {
        /* only show errors for java files.  If the default edit mode is "java" then by default,
        the parser will be invoked by SideKick.  It's annoying to get parse error messages for
        files that aren't actually java files.  Do parse buffers that have yet to be saved, they
        might be java or javacc files eventually.  Otherwise, require a ".java" extension on
        the file. */
        if ( displayOpt.getShowErrors() && ( ( buffer.getPath() == null || buffer.getPath().endsWith( ".java" ) ) || buffer.getMode().getName().equals( "javacc" ) ) ) {
            errorSource.clear();
            for ( Iterator it = parser.getErrors().iterator(); it.hasNext(); ) {
                ErrorNode en = ( ErrorNode ) it.next();
                Exception e = en.getException();
                ParseException pe = null;
                Range range = new Range();
                if ( e instanceof ParseException ) {
                    pe = ( ParseException ) e;
                    pe.printStackTrace();
                    range = getExceptionLocation( pe );
                }
                errorSource.addError( ErrorSource.ERROR, buffer.getPath(), range.startLine, range.startColumn, range.endColumn, e.getMessage() );
            }
        }
    }

    private void addChildren( Buffer buffer, DefaultMutableTreeNode parent, TigerNode tn ) {
        if ( tn.getChildCount() > 0 ) {
            List children = tn.getChildren();
            Collections.sort( children, nodeSorter );
            for ( Iterator it = children.iterator(); it.hasNext(); ) {
                TigerNode child = ( TigerNode ) it.next();
                child.setStart( ElementUtil.createStartPosition( buffer, child ) );
                child.setEnd( ElementUtil.createEndPosition( buffer, child ) );
                if ( canShow( child ) ) {
                    DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode( child );
                    parent.add( treeNode );
                    if ( child.getChildren() != null && child.getChildren().size() > 0 ) {
                        addChildren( buffer, treeNode, child );
                    }
                }
                else {
                    // need to fill in start and end positions
                    setChildPositions( buffer, child );
                }
            }
        }
    }

    private void setChildPositions( Buffer buffer, TigerNode tn ) {
        for ( int i = 0; i < tn.getChildCount(); i++ ) {
            TigerNode child = tn.getChildAt( i );
            child.setStart( ElementUtil.createStartPosition( buffer, child ) );
            child.setEnd( ElementUtil.createEndPosition( buffer, child ) );
            setChildPositions( buffer, child );
        }
    }

    /**
     * @return attempts to return a Location indicating the location of a parser
     * exception.  If the ParseException contains a Token reference, all is well,
     * otherwise, this method attempts to parse the message string for the
     * exception.
     */
    private Range getExceptionLocation( ParseException pe ) {
        Token t = pe.currentToken;
        if ( t != null ) {
            return new Range( new Location( t.next.beginLine - 1, t.next.beginColumn ), new Location( t.next.endLine - 1, t.next.endColumn ) );
        }

        // ParseException message look like: "Parse error at line 116, column 5.  Encountered: }"
        try {
            Pattern p = Pattern.compile( "(.*?)(\\d+)(.*?)(\\d+)(.*?)" );
            Matcher m = p.matcher( pe.getMessage() );
            if ( m.matches() ) {
                String ln = m.group( 2 );
                String cn = m.group( 4 );
                int line_number = -1;
                int column_number = 0;
                if ( ln != null )
                    line_number = Integer.parseInt( ln );
                if ( cn != null )
                    column_number = Integer.parseInt( cn );
                return line_number > -1 ? new Range( new Location( line_number - 1, column_number - 1 ), new Location( line_number - 1, column_number ) ) : null;
            }
            return new Range();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return new Range();
        }
    }

    // single place to check the filter settings, that is, check to see if it
    // is okay to show a particular node
    private boolean canShow( TigerNode node ) {
        if ( !isVisible( node ) )      // visibility based on option settings
            return false;
        if ( !node.isVisible() )        // visibility based on the node itself
            return false;
        if ( node.getOrdinal() == TigerNode.BLOCK )
            return false;
        if ( node.getOrdinal() == TigerNode.INITIALIZER )
            return filterOpt.getShowInitializers();
        if ( node.getOrdinal() == TigerNode.EXTENDS )
            return filterOpt.getShowGeneralizations();
        if ( node.getOrdinal() == TigerNode.IMPLEMENTS )
            return filterOpt.getShowGeneralizations();
        if ( node.getOrdinal() == TigerNode.FIELD && filterOpt.getShowFields() ) {
            if ( ( ( FieldNode ) node ).isPrimitive() )
                return filterOpt.getShowPrimitives();
            return true;
        }
        if ( node.getOrdinal() == TigerNode.VARIABLE ) {
            return filterOpt.getShowVariables();
        }
        if ( node.getOrdinal() == TigerNode.THROWS )
            return filterOpt.getShowThrows();
        return true;
    }

    // check if a node should be visible based on the 'top level' or 'member visible' settings
    private boolean isVisible( TigerNode tn ) {
        if ( ( tn.getOrdinal() == TigerNode.CLASS || tn.getOrdinal() == TigerNode.INTERFACE ) && tn.getParent() != null && tn.getParent().getOrdinal() == TigerNode.COMPILATION_UNIT ) {
            int visible_level = filterOpt.getTopLevelVisIndex();
            switch ( visible_level ) {
                case MutableModifier.TOPLEVEL_VIS_PUBLIC:
                    return ModifierSet.isPublic( tn.getModifiers() );

                case MutableModifier.TOPLEVEL_VIS_PACKAGE:
                    return true;
            }
        }

        int visible_level = filterOpt.getMemberVisIndex();
        switch ( visible_level ) {
            case MutableModifier.MEMBER_VIS_PACKAGE:
                return ModifierSet.isPackage( tn.getModifiers() ) || ModifierSet.isProtected( tn.getModifiers() ) || ModifierSet.isPublic( tn.getModifiers() );
            case MutableModifier.MEMBER_VIS_PROTECTED:
                return ModifierSet.isProtected( tn.getModifiers() ) || ModifierSet.isPublic( tn.getModifiers() );
            case MutableModifier.MEMBER_VIS_PUBLIC:
                return ModifierSet.isPublic( tn.getModifiers() );
            default:
                return true;
        }
    }

    private Comparator nodeSorter = new Comparator() {
                String LINE = jEdit.getProperty( "options.sidekick.java.sortByLine", "Line" );
                String NAME = jEdit.getProperty( "options.sidekick.java.sortByName", "Name" );
                String VISIBILITY = jEdit.getProperty( "options.sidekick.java.sortByVisibility", "Visibility" );

                /**
                 * Compares a TigerNode to another TigerNode for sorting. Sorting may be by
                 * line number or node type as determined by the value of "sorted".
                 * @param o a TigerNode to compare to this node.
                 * @return a negative integer, zero, or a positive integer as this TigerNode is
                 * less than, equal to, or greater than the specified TigerNode.
                 */
                public int compare( Object a, Object b ) {
                    if ( ! ( a instanceof TigerNode ) )
                        return -1;
                    if ( ! ( b instanceof TigerNode ) )
                        return 1;
                    TigerNode tna = ( TigerNode ) a;
                    TigerNode tnb = ( TigerNode ) b;
                    String sortBy = displayOpt.getSortBy();
                    if ( LINE.equals( sortBy ) ) {
                        // sort by line
                        Integer my_line = new Integer( tna.getStartLocation().line );
                        Integer other_line = new Integer( tnb.getStartLocation().line );
                        return my_line.compareTo( other_line );
                    }
                    else if ( VISIBILITY.equals( sortBy ) ) {
                        Integer my_vis = new Integer( ModifierSet.visibilityRank( tna.getModifiers() ) );
                        Integer other_vis = new Integer( ModifierSet.visibilityRank( tnb.getModifiers() ) );
                        int comp = my_vis.compareTo( other_vis );
                        return comp == 0 ? compareNames( tna, tnb ) : comp;
                    }
                    else {
                        // sort by name
                        return compareNames( tna, tnb );
                    }
                }

                private int compareNames( TigerNode tna, TigerNode tnb ) {
                    // sort by name
                    Integer my_ordinal = new Integer( tna.getOrdinal() );
                    Integer other_ordinal = new Integer( tnb.getOrdinal() );
                    int comp = my_ordinal.compareTo( other_ordinal );
                    return comp == 0 ? tna.getName().toLowerCase().compareTo( tnb.getName().toLowerCase() ) : comp;
                }
            };



    /******************************************************************************/
    // taken from jbrowse.JBrowse
    /******************************************************************************/


    private class StatusBarOptionAction implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            JavaParser.this.setStatusVisible(
                JavaParser.this.options.getShowStatusBar()
            );
        }
    }
    public boolean isStatusVisible() {
        return false;
        ///return statusPanel.isVisible();
    }


    public void setStatusVisible( boolean visible ) {
        ///statusPanel.setVisible( visible );
    }

    private ActionListener statusBarOptionAction = null;

    public ActionListener getStatusBarOptionAction() {
        if ( statusBarOptionAction == null ) {
            statusBarOptionAction = new StatusBarOptionAction();
        }

        return statusBarOptionAction;
    }



    public ActionListener getResizeAction() {
        return null;        /// might want to implement this
    }



    // sorting is by line or by node type, with node type being the initial default.
    // The sort type is signalled by setting a System property.


    private ActionListener sortOptionAction = null;
    public ActionListener getSortOptionAction() {
        if ( sortOptionAction == null ) {
            sortOptionAction = new SortOptionAction();
        }

        return sortOptionAction;
    }

    /// dead code?
    private class SortOptionAction implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            // on action, toggle between unsorted (sort by line number) or
            // or sorted (sort by ordinal);
            sorted = !sorted;
            if ( currentView != null )
                parse( currentView.getBuffer(), null );
        }
    }



    // controls what is visible/displayed in the tree
    private ActionListener filterOptionAction = null;
    public ActionListener getFilterOptionAction() {
        if ( filterOptionAction == null ) {
            filterOptionAction = new FilterOptionAction();
        }
        return filterOptionAction;
    }
    private class FilterOptionAction implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            // whether or not to show fields, throws, and other visibility levels
            // just reparsing should do it
            if ( currentView != null )
                parse( currentView.getBuffer(), null );
        }
    }



    // controls how the visible items in the tree are displayed
    private ActionListener displayOptionAction = null;
    public ActionListener getDisplayOptionAction() {
        if ( displayOptionAction == null ) {
            displayOptionAction = new DisplayOptionAction();
        }

        return displayOptionAction;
    }
    private class DisplayOptionAction implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            // whether or not to show line numbers, arguments, etc
            // just reparsing should do it
            if ( currentView != null )
                parse( currentView.getBuffer(), null );
        }
    }

    public ActionListener getPropertySaveListener() {
        return new ActionListener() {
                   public void actionPerformed( ActionEvent ae ) {
                       if ( currentView != null )
                           parse( currentView.getBuffer(), null );
                   }
               };
    }

    /**
     * Returns if the parser supports code completion.
     *
     * Returns false by default.
     */
    public boolean supportsCompletion() {
        return true;
    }


    public SideKickCompletion complete( EditPane editPane, int caret ) {
        if ( completionFinder == null )
            completionFinder = new JavaCompletionFinder();
        return completionFinder.complete( editPane, caret );
    }

}
