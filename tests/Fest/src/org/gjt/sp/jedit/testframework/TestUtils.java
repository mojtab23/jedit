/*
* $Revision: 13763 $
* $Date: 2008-09-23 04:02:41 -0600 (Tue, 23 Sep 2008) $
* $Author: kerik-sf $
*
* Copyright (C) 2008-2010 Eric Le Lay
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

package org.gjt.sp.jedit.testframework;


//{{{ Imports

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.SwingUtilities;
import javax.swing.tree.*;
import javax.swing.JTree;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;



//{{{  jEdit
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.msg.BufferUpdate;
import org.gjt.sp.jedit.testframework.EBFixture.MessageListener;

//}}}

//{{{ junit
import org.junit.*;

import static org.junit.Assert.*;

//{{{ FEST...
import org.fest.swing.fixture.*;
import org.fest.swing.core.*;
import org.fest.swing.finder.DialogFinder;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.driver.BasicJTreeCellReader;
import org.fest.swing.lock.ScreenLock;
import org.fest.swing.timing.Timeout;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.edt.GuiActionRunner;
//}}}

///}}}

/**
 * Provides methods to start jEdit and to dispose of the robot.
 */
public class TestUtils {

    // key to environment settings holding value of jEdit settings directory,
    // this is the directory that would be passed to jEdit in the -settings
    // parameter on the command line.
    public static final String ENV_JEDIT_SETTINGS = "test-jedit.settings";

    // common environment variables
    // TODO: confirm these are not used and can be removed
    public static final String ENV_TESTS_DIR = "test-tests.dir";
    public static final String ENV_IN_JEDIT = "test-in-jedit";

    // frameFixture containing the main jEdit View
    private static FrameFixture jEditFrame = null;

    // the main robot for running the tests
    private static Robot robot;

    // press Ctrl+Shift+A to abort running tests
    private static EmergencyAbortListener listener;

    // will create a new instance of jEdit by default
    private static boolean standaloneMode = true;
    
    public static void setStandaloneMode(boolean standaloneMode){
    	assertTrue("can't switch mode while tests are running", robot == null);
    	Log.log("switching to standalone? "+standaloneMode);
    	TestUtils.standaloneMode = standaloneMode;
    }
    
    /**
     * Set up and start jEdit if necessary, or reuse an existing jEdit.    
     */
    public static void setUpjEdit() {
    	if(standaloneMode){
    		setupNewjEdit();
    	}else{
    		setupExistingjEdit(); 
    	}
    }
    
    /**
     * Set up and start jEdit    
     */
    public static void setupNewjEdit(){
        assert( robot == null );
        Log.log( "Setting up jedit" );
		Log.log( "Starting a new jEdit" );
		robot = BasicRobot.robotWithNewAwtHierarchy();
		Log.log( "created robot" );
		listener = EmergencyAbortListener.registerInToolkit();

		String settings = System.getProperty( ENV_JEDIT_SETTINGS );
		Log.log( "checking settings" );
		assertTrue( "Forgot to set env. variable '" + ENV_JEDIT_SETTINGS + "'", settings != null );

		Log.log( "settings = " + settings );
		final String[] args = {"-settings=" + settings, "-norestore", "-nobackground"};
		Thread runJeditThread = new Thread() {
					public void run() {
						jEdit.main( args );
					}
				};
		runJeditThread.start();
		jEditFrame = WindowFinder.findFrame( View.class ).withTimeout( 120000 ).using( robot );
    }

    public static void tearDownNewjEdit() {
        listener.unregister();
        // jEditFrame and robot may be nulled by tearDownExistingjEdit
        // if JEditRunner is used.
        if ( jEditFrame != null ) {
            robot.releaseMouseButtons();
            if (ScreenLock.instance().acquiredBy( robot ))
            	ScreenLock.instance().release( robot );
            
            jEditFrame = null;
            Log.log( "tearDown done in jEdit" );
        }
        else if ( robot != null ) {
            robot.cleanUp();
            robot = null;
        }

    }

    /**
     * reuse an existing jEdit.    
     */
    public static void setupExistingjEdit(){
        assert( robot == null );
        Log.log( "Setting up jedit" );
		robot = BasicRobot.robotWithCurrentAwtHierarchy();
        Log.log( "created robot" );
        // FIXME: is this working reliably ?
        listener = EmergencyAbortListener.registerInToolkit();

		jEditFrame = WindowFinder.findFrame( View.class ).withTimeout( 40000 ).using( robot );
    }

    public static Robot robot() {
        return robot;
    }

    public static FrameFixture jEditFrame() {
        return jEditFrame;
    }

    public static void tearDownjEdit() {
    	if(standaloneMode){
    		tearDownNewjEdit();
    	}else{
    		tearDownExistingjEdit();
    	}
    }

    public static void tearDownExistingjEdit() {
        listener.unregister();
		robot.releaseMouseButtons();
		if (ScreenLock.instance().acquiredBy( robot ))
			ScreenLock.instance().release( robot );

		final Collection<Component> roots = robot.finder().findAll(new ComponentMatcher() {
			
			@Override
			public boolean matches(Component c) {
				return c instanceof Window;
			}
		});

		for(Component c: roots){
			if(c != jEditFrame.target){
				robot().close((Window)c);
			}
		}
		
		// close any open buffer
		for(Buffer b: jEdit.getBuffers()){
			TestUtils.close(jEdit.getActiveView(), b);
		}
		
		robot.cleanUpWithoutDisposingWindows();
		Log.log( "tearDown done in jEdit" );
        robot = null;
        jEditFrame = null;
    }

    /**
     * Set up and start jEdit if necessary.    
     */
    public static void beforeTest() {
        Log.log( "beforeTest" );
        setUpjEdit();
    }

    public static void afterTest() {
        tearDownjEdit();
    }

    /**
     * Set up and start jEdit if necessary.    
     */
    public static void beforeClass() {
        Log.log( "before class" );
        setUpjEdit();
    }

    public static void afterClass() {
        Log.log( "after class" );
        tearDownjEdit();
    }

    /**
     * Convenience method to find a dialog with the given title.
     * @param title the title of a dialog to find.
     */
    public static DialogFixture findDialogByTitle( String title ) {
        return new DialogFixture( robot(), ( Dialog ) robot().finder().find( new FirstDialogMatcher( title ) ) );
    }

    /**
     * Convenience method to find a window with the given title.  This is useful
     * to find plugins in a floating dockable window.
     * @param title the title of the window to find.
     */
    public static FrameFixture findFrameByTitle( String title ) {
        return new FrameFixture( robot(), ( Frame ) robot().finder().find( new FirstFrameMatcher( title ) ) );
    }

    /**
     * Convenience method to close a Buffer.
     * @param view the View containing the Buffer
     * @param buffer the Buffer to close
     */
    public static void close( final View view, final Buffer buffer ) {
        SwingUtilities.invokeLater(
            new Runnable() {
                public void run() {
                    jEdit.closeBuffer( view, buffer );
                }
            }
        );
        try {
            Thread.sleep( 2000 );
        }
        catch ( InterruptedException ie ) {}

        if ( buffer.isDirty() ) {
            jEditFrame().optionPane(Timeout.timeout(2000)).noButton().click();
        }
    }

    /**
     * Convenience method to open a file in jEdit.
     * @param filename the name of the file to open
     * @return the Buffer containing the file
     */
    public static Buffer openFile( final String filename ) {
        final View view = jEditFrame().targetCastedTo( View.class );
		MessageListener listen = new MessageListener();
		listen.registerForMessage(EBFixture.bufferLoadedMessage(filename));
        try {
            SwingUtilities.invokeAndWait(
                new Runnable() {
                    public void run() {
                        jEdit.openFile( view, filename );
                    }
                }
            );
        }
        catch ( Exception e ) {
            System.err.println( e );
        }

        listen.waitForMessage(3000);
        return view.getBuffer();
    }

    /**
     * Convenience method to create a new, empty Buffer in jEdit.
     * @return reference to the new, empty Buffer.
     */
    public static Buffer newFile() {
        final View view = jEditFrame().targetCastedTo( View.class );
        try {
            SwingUtilities.invokeAndWait(
                new Runnable() {
                    public void run() {
                        jEdit.newFile( view );
                    }
                }
            );
        }
        catch ( Exception e ) {
            System.err.println( e );
        }
        
        return view.getBuffer();
    }

    /**
     * Convenience method to get a reference to the main jEdit View.
     * @return the View
     */
    public static View view() {
        return jEditFrame().targetCastedTo( View.class );
    }
    

    /**
     * Convenience method to replace the text in a text component with new text.
     * @param f the text component in question
     * @param text the new text to display in the text component
     */
    public static void replaceText( JTextComponentFixture f, String text ) {
        f.select( f.text() ).deleteText().enterText( text );
    }

	// {{{ various Tree utilities
    /**
     * Convenience method to find the tree path for a JTree for the given string array.
     * This is handy for example for finding a tree path in the Global Options dialog.
     * @param treeFixture the JTree to search
     * @param path an array of strings describing the path, for example {"jEdit", "Text Area"}
     * would find the path in the Global Options dialog to the Text Area node in the tree.
     * @return the tree path
     */
    public static TreePath getPathForStrings( JTreeFixture treeFixture, String[] path ) {
        JTree tree = treeFixture.targetCastedTo( JTree.class );
        BasicJTreeCellReader rdr = new BasicJTreeCellReader();
        TreeModel mdl = tree.getModel();


        Object parent = mdl.getRoot();
        TreePath returnPath = new TreePath( parent );
        for ( int i = 0;i < path.length;i++ ) {
            boolean found = false;
            int nbChildren = mdl.getChildCount( parent );
            String[] lbls = new String[nbChildren];
            for ( int iChild = 0;!found && iChild < nbChildren;iChild++ ) {
                Object val = mdl.getChild( parent, iChild );
                String lbl = rdr.valueAt( tree, val );
                if ( path[ i ].equals( lbl ) ) {
                    returnPath = returnPath.pathByAddingChild( val );
                    parent = val;
                    found = true;
                }else{
                	lbls[iChild] = lbl;
                }
            }
            if ( !found ) {
                throw new IllegalArgumentException( "Couldn't find path" + Arrays.asList( path ) + ", stopped at level " + i + "\navailable values are "+Arrays.asList(lbls));
            }
        }
        Log.log("tree path = " + returnPath);
        return returnPath;
    }

	public static void requireEmpty(JTreeFixture f){
		
		try{
			f.toggleRow(0);
			fail("should be empty !");
		}catch(RuntimeException re){
			//fine
			System.err.println(re.getClass());
		}
	}

    /**
     * Convenience method to select a path in a JTree.
     * @param treeFixture the JTree
     * @param path the path to select
     */
    public static void selectPath( JTreeFixture treeFixture, String[] path ) {
        JTree tree = treeFixture.targetCastedTo( JTree.class );
        TreePath finalPath = getPathForStrings( treeFixture, path );
        tree.setToggleClickCount(1);
        if ( !tree.isVisible( finalPath ) ) {
            TreePath parentPath = new TreePath( finalPath.getPathComponent( 0 ) );
            for ( int i = 1;i < finalPath.getPathCount();i++ ) {
                TreePath curPath = parentPath.pathByAddingChild( finalPath.getPathComponent( i ) );
                if ( !tree.isVisible( curPath ) ) {
                    treeFixture.expandRow( tree.getRowForPath( curPath.getParentPath() ) );
                }
                parentPath = curPath;
            }
        }

        treeFixture.selectRow( tree.getRowForPath( finalPath ) );

    }
    
    /**
     * Convenience method to select a path in a JTree.
     * Path must be absolute, separator is the first char
     * @param treeFixture the JTree
     * @param path the path to select
     */
    public static void selectPath( JTreeFixture treeFixture, String path ) {
    	String separator = path.substring(0,1);
    	String[] components = path.substring(1).split(Pattern.quote(separator),-1);
    	selectPath(treeFixture, components);
    }
	//}}}

	private static void safelyClose(FileInputStream fis)
	{
		if (fis == null)
			return;
		try
		{
			fis.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Compares two binary files for equality.
	 * Returns true if the files are equals, false if they are not equal
	 * or if one of the files cannot be read.
	 */
	public static boolean compareFiles(String file1, String file2)
	{
		File f1 = new File(file1);
		File f2 = new File(file2);
		FileInputStream fis1 = null, fis2 = null;
		try {
			fis1 = new FileInputStream(f1);
			fis2 = new FileInputStream(f2);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			safelyClose(fis1);
		}
		int blockSize = 1024 * 1024;	// 1MB
		byte [] bytes1 = new byte[blockSize];
		byte [] bytes2 = new byte[blockSize];
		int read1, read2;
		boolean match = false;
		do {
			try	{
				read1 = fis1.read(bytes1);
				read2 = fis2.read(bytes2);
			}
			catch (IOException e) {
				e.printStackTrace();
				break;
			}
			match = (read1 == read2) && (Arrays.equals(bytes1, bytes2));
		}
		while (match && (read1 > 0));
		safelyClose(fis1);
		safelyClose(fis2);
		return match;
	}

	/**
	 * Get text file contents (for comparison with expected text).
	 * @param file	path to the file to load
	 * @param encoding	encoding to use to read file
	 * @return	textual content of file
	 * @see #compareFiles(String, String) for binary file comparison
	 * @throws IOException on read error, file not found, etc.
	 */
	public static String loadFile(String file, String encoding) throws IOException
	{
	     Path path = FileSystems.getDefault().getPath(file);
	     BufferedReader reader = Files.newBufferedReader(path, Charset.forName(encoding));
	     StringBuilder sb = new StringBuilder();
	     char[] buf = new char[4096];
	     for(int cnt = reader.read(buf); cnt >= 0; cnt = reader.read(buf)){
	    	 sb.append(buf, 0, cnt);
	     }
	     reader.close();
	     return sb.toString();
     }
	
	/** buttons in an OptionPane */
    public static enum Option{YES,NO,OK,CANCEL}
	/**
	 * Click on an OptionPane asynchronously.
	 * Typical usage pattern is :
	 * <code>
	 *  ClickT clickT = new ClickT(Option.YES);
	 *  clickT.start();
	 *
	 *  // do something to prompt an option pane
	 *  
	 *  clickT.waitForClick();
	 *  </code>
	 */
	public static final class ClickT extends Thread{
		private final Option opt;
		private final long timeout;
		private transient WaitTimedOutError savedException;
		
		/**
		 * @param	opt	the button you want to click on
		 */
		public ClickT(Option opt){
			this(opt,2000);
		}

		/**
		 * @param	opt	the button you want to click on
		 * @param	timeout	how long do you wait for the Dialog ?
		 */
		public ClickT(Option opt, long timeout){
			this.opt = opt;
			this.timeout = timeout;
		}
		
		
		public void run(){
			try{
				final JOptionPaneFixture options = jEditFrame().optionPane(Timeout.timeout(timeout));
				switch(opt){
				case YES:
					options.yesButton().click();
					break;
				case NO:
					options.noButton().click();
					break;
				case OK:
					options.okButton().click();
					break;
				case CANCEL:
					options.cancelButton().click();
					break;
				default:
					fail("unspecified option to click on !");
				}
			}catch(WaitTimedOutError e){
				savedException = e;
			}
		}
		
		/**
		 * blocking method.
		 * wait until the OptionPane shows up and we click on it,
		 * or until the timeout expires (then you get an WaitTimedOutError)
		 */
		public void waitForClick() throws WaitTimedOutError{
			try{
				this.join();
				if(savedException != null)throw savedException;
			}catch(InterruptedException e){
				fail("Interrupted");
			}
		}
		
	}	
	
	/**
	 * execute an action once, like via the ActionBar
	 * 
	 * @param	action	the action to execute
	 */
	public static void action(final String action){
		action(action,1);
	}

	/**
	 * execute an action, like via the ActionBar
	 * 
	 * @param	action	the action to execute
	 * @param	count	the number of time to execute it (should be at least 1)
	 */
	public static void action(final String action, final int count){
		GuiActionRunner.execute(new GuiTask(){
				protected void executeInEDT(){
					view().getInputHandler().setRepeatCount(count);
					view().getInputHandler().invokeAction(action);
				}
		});
	}
	
		public static void gotoPosition(final int caretPosition){
		GuiActionRunner.execute(new GuiTask(){
				protected void executeInEDT(){
					view().getTextArea().setCaretPosition(caretPosition);
				}
		});
	}
	

	/**
	 * insert text in buffer at pos in Event Dispatch Thread.
	 * Makes tests a bit less cluttered.
	 * 
	 * @param buffer	buffer to insert to
	 * @param pos		position to insert at
	 * @param text		text to insert
	 */
	public static void insertInEDT(final Buffer buffer, final int pos, final String text){
		GuiActionRunner.execute(new GuiTask(){
		protected void executeInEDT(){
			buffer.insert(pos,text);
		}});
		
	}
		
    /**
     * @return the plugin options dialog
     */
    public static PluginOptionsFixture pluginOptions(){
    	jEditFrame().menuItemWithPath("Plugins","Plugin Options...").click();
		
		DialogFixture optionsDialog = WindowFinder.findDialog(PluginOptionsMatcher.INSTANCE).withTimeout(5000).using(robot());
		Dialog target = optionsDialog.targetCastedTo(Dialog.class);
		return new PluginOptionsFixture(robot(),target);
    }
    
    /**
     * @return the plugin options dialog
     */
    public static PluginOptionsFixture globalOptions(){
    	jEditFrame().menuItemWithPath("Utilities","Global Options...").click();
		
		DialogFixture optionsDialog = WindowFinder.findDialog(PluginOptionsMatcher.INSTANCE).withTimeout(5000).using(robot());
		Dialog target = optionsDialog.targetCastedTo(Dialog.class);
		return new PluginOptionsFixture(robot(),target);
    }

    public static class PluginOptionsMatcher extends GenericTypeMatcher<org.gjt.sp.jedit.gui.EnhancedDialog>{
    	public static final PluginOptionsMatcher INSTANCE = new PluginOptionsMatcher();
    	
    	private PluginOptionsMatcher(){
    		super(org.gjt.sp.jedit.gui.EnhancedDialog.class,true);
    	}
    	
    	protected boolean isMatching(org.gjt.sp.jedit.gui.EnhancedDialog maybe){
    		return "org.gjt.sp.jedit.options.PluginOptions".equals(maybe.getClass().getName())
    				|| "org.jedit.options.CombinedOptions".equals(maybe.getClass().getName());
    	}
    }
}
