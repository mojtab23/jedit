// $Id$
/*
Copyright (c) 2002, Dale Anson
All rights reserved.
 
Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:
 
* Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.
* Neither the name of the author nor the names of its contributors
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

package ise.plugin.nav;

import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EBMessage;
import org.gjt.sp.jedit.EditPane;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.EBPlugin;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.msg.*;
import org.gjt.sp.jedit.buffer.BufferAdapter;
import org.gjt.sp.jedit.buffer.BufferListener;
import org.gjt.sp.jedit.buffer.JEditBuffer;


/**
 * NavigatorPlugin, mostly static methods, allows one Navigator per View.
 *
 * @author Dale Anson
 * @version $Revision$
 * @since Oct 25, 2003
 */
public class NavigatorPlugin extends EBPlugin {

    public final static String NAME = "Navigator";

    /**
     * VIEW_SCOPE indicates to Navigator that history is tracked per View.
     */
    public final static int VIEW_SCOPE = 1;

    /**
     * EDITPANE_SCOPE indicates to Navigator that history is tracked per EditPane.
     */
    public final static int EDITPANE_SCOPE = 2;

    // key into property file for show on toolbar value
    public static final String showOnToolBarKey = "navigator.showOnToolbar";

    /**
     * View/Navigator map.  Each View is assigned exactly one Navigator.
     */
    private final static HashMap<View, Navigator> viewNavigatorMap = new HashMap<View, Navigator>();

    /**
     * EditPane/Navigator map.  If scope is by EditPane, then each EditPane is 
     * assigned its own Navigator.
     */
    private final static HashMap<EditPane, Navigator> editPaneNavigatorMap = new HashMap<EditPane, Navigator>();

    /**
     * Whether auto-jump is taking place, during which BufferUpdate.LOADED
     * should be ignored.
     */
    private boolean autoJump = false;
    
    private BufferListener bufferListener = null;
    
    /**
     * @return true if the Navigator buttons should be shown on the main toolbar 
     * for the View.
     */
    public static boolean showOnToolBars() {
        return jEdit.getBooleanProperty( showOnToolBarKey, false );
    }

    /**
     * @return true if the positions shown in the back and forward popup lists
     * should be grouped by filename.
     */
    public static boolean groupByFile() {
        return jEdit.getBooleanProperty( "navigator.groupByFile", false );
    }

    /**
     * Toggle group by file to on or off.    
     */
    public static void toggleGroupByFile() {
        jEdit.setBooleanProperty( "navigator.groupByFile", !groupByFile() );
    }

    /**
     * @return true if the positions shown in the back and forward popup lists
     * should be grouped by line
     */
    public static boolean groupByLine() {
        return jEdit.getBooleanProperty( "navigator.groupByLine", false );
    }

    /**
     * Toggle group by line to on or off.    
     */
    public static void toggleGroupByLine() {
        jEdit.setBooleanProperty( "navigator.groupByLine", !groupByLine() );
    }

    public static boolean combineLists() {
        return jEdit.getBooleanProperty( "navigator.combineLists", false );
    }

    /**
     * Show the Navigator buttons on the main toolbar for the View.
     */
    public static void showButtons() {
        jEdit.setBooleanProperty( showOnToolBarKey, true );
        setToolBars();
    }

    /**
     * Hide the Navigator buttons on the main toolbar for the View.
     */
    public static void hideButtons( View view ) {
        jEdit.setBooleanProperty( showOnToolBarKey, false );
        clearToolBars();
    }

    /**
     * Actually add the Navigator buttons to the main toolbar of the view.
     */
    public static void setToolBars() {
        SwingUtilities.invokeLater(
            new Runnable() {
                public void run() {
                    if ( showOnToolBars() ) {
                        clearToolBars(true);
                        View[] views = jEdit.getViews();
                        for (View view : views) {
                            if (view.getToolBar() != null) {
                                Navigator nav = getNavigator(view);
                                if (nav != null) {
                                    NavToolBar toolBar = new NavToolBar(nav);
                                    view.getToolBar().add(toolBar);
                                }
                            }
                        }
                    }
                }
            }
        );
    }

    /**
     * Create a Navigator for each currently open View.  Add the Navigator
     * buttons to the Views, depending on the user settings for the toolbar.
     * Note that when jEdit first starts up, this won't do anything as plugins
     * are started before the first View is actually created, so this is really
     * only useful when reloading this plugin.
     */
    public void start() {
        for ( View v : jEdit.getViews() ) {
            createNavigator( v );
            createNavigators( v );
        }
        clearToolBars();
        setToolBars();
        if (bufferListener == null) {
            bufferListener = new BufferEditListener();   
        }
        Buffer[] openBuffers = jEdit.getBuffers();
        for (Buffer buffer : openBuffers) {
            buffer.removeBufferListener(bufferListener);
            buffer.addBufferListener(bufferListener);
        }
    }

    /**
     * Remove the Navigator from each View.  Remove the Navigator navigation
     * buttons from the main toolbar for each View.  Removes nav mouse listeners
     * from all edit panes.
     */
    public void stop() {
        // clean up
        viewNavigatorMap.clear();
        editPaneNavigatorMap.clear();
        clearToolBars( true );
    }

    /**
     * @return one of VIEW_SCOPE or EDITPANE_SCOPE
     */
    public static int getScope() {
        return jEdit.getIntegerProperty( "navigator.scope", VIEW_SCOPE );
    }

    /**
     * Set the scope of the Navigators.  All Navigators use the same scope.
     * @param scope one of VIEW_SCOPE or EDITPANE_SCOPE.
     */
    public static void setScope( int scope ) {
        switch ( scope ) {
            case VIEW_SCOPE:
            case EDITPANE_SCOPE:
                jEdit.setIntegerProperty("navigator.scope", scope);
        }
    }

    /**
     * Toggle scope from view scope to edit pane scope or vice versa.    
     */
    public static void toggleScope() {
        String msg = null;
        int scope = jEdit.getIntegerProperty("navigator.scope", VIEW_SCOPE);
        switch ( scope ) {
            case VIEW_SCOPE:
                scope = EDITPANE_SCOPE;
                msg = jEdit.getProperty( "navigator.message.editPaneScope", "Navigator switched to EditPane scope." );
                break;
            case EDITPANE_SCOPE:
                scope = VIEW_SCOPE;
                msg = jEdit.getProperty( "navigator.message.viewScope", "Navigator switched to View scope." );
                break;
            default:
                return ;
        }
        jEdit.setIntegerProperty( "navigator.scope", scope );
        if ( msg != null ) {
            for ( View view : viewNavigatorMap.keySet() ) {
                view.getStatus().setMessage( msg );
            }
        }
    }

    /**
     * Removes the Navigator buttons from the main toolbar for each View.
     */
    public static void clearToolBars() {
        clearToolBars( false );
    }

    /**
     * @param force force removal of the buttons from the main toolbar for each
     * View regardless of the showOnToolBars setting.
     */
    public static void clearToolBars( boolean force ) {
        if ( force || !showOnToolBars() ) {
            for (View view : jEdit.getViews()) {
                Container viewToolBar = view.getToolBar();
                if (viewToolBar != null) {
                    for (Component comp : viewToolBar.getComponents()) {
                        if (comp instanceof NavToolBar) {
                            viewToolBar.remove(comp);   
                        }
                    }
                    viewToolBar.repaint();
                }
            }
        }
    }

    /**
     * Adds a Navigator. Navigators are per View.
     *
     * @param view
     *                The View for the Navigator
     * @param navigator
     *                The Navigator
     */
    public static void addNavigator( View view, Navigator navigator ) {
        if ( view == null ) {
            return ;
        }
        if ( viewNavigatorMap.containsKey( view ) ) {
            // already have a Navigator for this view
            return ;
        }
        viewNavigatorMap.put( view, navigator );
    }

    public static void addNavigator( EditPane editPane, Navigator navigator ) {
        if ( editPane == null ) {
            return ;
        }

        if ( editPaneNavigatorMap.containsKey( editPane ) ) {
            // already have a Navigator for this EditPane
            return ;
        }
        editPaneNavigatorMap.put( editPane, navigator );
    }

    /**
     * Gets the current Navigator for the given View.  If scope is EDITPANE_SCOPE,
     * gets the Navigator for the current EditPane in the View.
     * @param view the View to find the Navigator for.
     * @return Depending on the scope, returns either the Navigator for the View,
     * or the Navigator for the current EditPane in the View.  Returns null if
     * there is no Navigator for the View or EditPane.
     */
    public static Navigator getNavigator( View view ) {
        Navigator navigator = null;
        if ( getScope() == EDITPANE_SCOPE ) {
            navigator = editPaneNavigatorMap.get( view.getEditPane() );
            if ( navigator == null ) {
                navigator = createNavigator( view.getEditPane() );
            }
        }
        else {
            navigator = viewNavigatorMap.get( view );
            if ( navigator == null ) {
                navigator = createNavigator( view );
            }
        }
        return navigator;
    }

    /**
     * Create a Navigator for the given View.
     * @param view the View to create a Navigator for.
     * @return a previously existing or a newly created Navigator for this View
     */
    public static Navigator createNavigator( View view ) {
        if ( view == null ) {
            return null;
        }
        Navigator navigator = viewNavigatorMap.get( view );
        if ( navigator == null ) {
            navigator = new Navigator( view );
            viewNavigatorMap.put( view, navigator );
        }
        return navigator;
    }

    /**
     * Create a Navigator for each EditPane in the given View.
     * @param view the View containing the EditPanes to create Navigators for.
     */
    public static void createNavigators( View view ) {
        if ( view == null ) {
            return ;
        }
        EditPane[] editPanes = view.getEditPanes();
        for ( EditPane editPane : editPanes ) {
            createNavigator( editPane );
        }
    }

    /**
     * Create a Navigator for the given EditPane.
     * @param editPane the EditPane to create a Navigator for.
     * @return a previously existing or a newly created Navigator for this EditPane
     */
    public static Navigator createNavigator( EditPane editPane ) {
        if ( editPane == null ) {
            return null;
        }
        Navigator navigator = editPaneNavigatorMap.get( editPane );
        if ( navigator == null ) {
            navigator = new Navigator( editPane );
            editPaneNavigatorMap.put( editPane, navigator );
        }
        return navigator;
    }

    /**
     * Wrapper for the 'backList' method of the Navigator for the given view.
     *
     * @param view
     *                The view for the Navigator
     */
    public static NavHistoryPopup backList( View view ) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            return navigator.backList();
        }
        return null;
    }

    /**
     * Wrapper for the 'goBack' method of the Navigator for the given view.
     *
     * @param view
     *                The view for the Navigator
     */
    public static void goBack( View view ) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.goBack();
        }
    }

    /**
     * Wrapper for the 'goBackFile' method of the Navigator for the given view.
     *
     * @param view
     *                The view for the Navigator
     */
    public static void goBackFile( View view ) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.goBackFile();
        }
    }
    
    /**
     * Wrapper for the 'forwardList' method of the Navigator for the given
     * view.
     *
     * @param view
     *                The view for the Navigator
     */
    public static NavHistoryPopup forwardList( View view ) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            return navigator.forwardList();
        }
        return null;
    }

    /**
     * Wrapper for the 'goForward' method of the Navigator for the given
     * view.
     *
     * @param view
     *                The view for the Navigator
     */
    public static void goForward( View view ) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.goForward();
        }
    }
    
    /**
     * Wrapper for the 'goForwardFile' method of the Navigator for the given
     * view.
     *
     * @param view
     *                The view for the Navigator
     */
    public static void goForwardFile( View view ) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {            
            navigator.goForwardFile();
        }
    }
    
    public static void pushPosition(View view) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.pushPosition();
        }
    }
    
    public static void popPosition(View view) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.popPosition();
        }
    }

    public static void gotoTopPosition(View view) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.gotoTopPosition();
        }
    }

    public static void swapCaretAndTop(View view) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.swapCaretAndTop();
        }
    }

    public static void gotoLine(View view) {
        GoToLineDialog dialog = new GoToLineDialog(view);
        NavPosition position = dialog.getLineNumber();
        if (position == null) {
            // user cancelled
            return;   
        }
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.jump(position);
        }
    }

    /**
     * Wrapper for the 'combinedList' method of the Navigator for the given
     * view.
     *
     * @param view
     *                The view for the Navigator
     */
    public static void combinedList( View view ) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.combinedList();
        }
    }

    /**
     * Clear the Navigator history for the given view.  This clears the history
     * for EditPanes contained by the View also.
     */
    public static void clearHistory( View view ) {
        Navigator navigator = getNavigator( view );
        if ( navigator != null ) {
            navigator.clearHistory();
        }
        for ( EditPane editPane : view.getEditPanes() ) {
            navigator = editPaneNavigatorMap.get( editPane );
            if ( navigator != null ) {
                navigator.clearHistory();
            }
        }
        view.getStatus().setMessage( jEdit.getProperty( "navigator.message.Navigator_history_cleared.", "Navigator history cleared." ) );
    }

    public void handleMessage( EBMessage message ) {
        if ( message == null ) {
            return ;
        }
        // if a new buffer was loaded, need to add the first position in the
        // buffer to the history
        if ( message instanceof BufferUpdate ) {
            BufferUpdate bu = ( BufferUpdate ) message;
            if ( bu.getWhat().equals( BufferUpdate.LOADED ) && (! autoJump ) ) {
                if ( bu.getView() != null && bu.getView().getEditPane() != null ) {     // NOPMD
                    Navigator n = getNavigator( bu.getView() );
                    if ( n != null ) {
                        n.addToHistory();
                    }
                }
            }
            else if (BufferUpdate.CLOSED.equals(bu.getWhat())) {
                // remove any untitled buffers from the history lists since they can't be reopened
                if ( bu.getView() != null && bu.getView().getEditPane() != null && bu.getBuffer() != null && bu.getBuffer().isUntitled() ) {     // NOPMD
                    Navigator n = getNavigator( bu.getView() );
                    if ( n != null ) {
                        n.removeAll(bu.getBuffer().getPath());
                    }
                }
            }
            else if (BufferUpdate.CREATED.equals(bu.getWhat())) {
                bu.getBuffer().addBufferListener(bufferListener);
            }
        }
        // Note: handle messages in this order: BufferChanging, then PositionChanging,
        // then EditPaneUpdate last because of inheritance.

        // Record history only if the buffer is actually loaded.
        if ( message instanceof BufferChanging ) {
            BufferChanging bc = ( BufferChanging ) message;
            if ( bc.getBuffer() != null && bc.getBuffer().isLoaded() ) {
                EditPane p = bc.getEditPane();
                if ( p != null ) {
                    Navigator n = getNavigator( p.getView() );
                    if ( n != null ) {
                        n.addToHistory();
                    }
                }
            }
        }

        // If the editpane changes its current position, we want to know
        // just before it happens so the last position can be recorded in the
        // history.
        else if ( message instanceof PositionChanging ) {
        	if (! autoJump ) {
	            PositionChanging cc = ( PositionChanging ) message;
	            EditPane p = cc.getEditPane();
	            if ( p != null ) {
	                Navigator n = getNavigator( p.getView() );
	                if ( n != null ) {
	                    n.addToHistory();
	                }
	            }
        	}
        }

        // add or remove Navigators if EditPane is created or destroyed
        else if ( message instanceof EditPaneUpdate ) {
            EditPaneUpdate epu = ( EditPaneUpdate ) message;
            if ( epu.getWhat() == EditPaneUpdate.CREATED ) {
                // create/update Navigator for View scope
                EditPane editPane = epu.getEditPane();
                Navigator n = viewNavigatorMap.get( editPane.getView() );
                if ( n == null ) {
                    createNavigator( editPane.getView() );
                }
                // create Navigator for EditPane scope
                createNavigator( editPane );
            }
            else if ( epu.getWhat().equals( EditPaneUpdate.DESTROYED ) && jEdit.getIntegerProperty("navigator.scope") == EDITPANE_SCOPE ) {
                EditPane editPane = epu.getEditPane();
                editPaneNavigatorMap.remove( editPane );
            }
        }

        // When we create a new View, create a new navigator for it
        else if ( message instanceof ViewUpdate ) {
            ViewUpdate vu = ( ViewUpdate ) message;
            View v = vu.getView();
            Object what = vu.getWhat();
            if ( what.equals( ViewUpdate.CREATED ) ) {
                Navigator n = createNavigator( v );
                if ( n != null ) {
                    n.addToHistory();
                }
                setToolBars();
            }
            else if ( what.equals( ViewUpdate.CLOSED ) ) {
                viewNavigatorMap.remove( v );
            }
        }

        // update properties for this plugin on PropertiesChanged message
        else if ( message instanceof PropertiesChanged ) {
            if ( showOnToolBars() ) {
                setToolBars();
            }
            else {
                clearToolBars();
            }
            for ( Navigator nav : viewNavigatorMap.values() ) {
                nav.setMaxHistorySize( jEdit.getIntegerProperty( "navigator.maxStackSize", 512 ) );
            }
            setScope( jEdit.getIntegerProperty( "navigator.scope", VIEW_SCOPE ) );
        }

        else if ( message instanceof AutoJump ) {
        	AutoJump aj = ( AutoJump ) message;
        	// Add both sides of the jump (source and destination) to the history 
            Navigator n = getNavigator( aj.getView() );
            if ( n != null ) {
                n.addToHistory();
            }
        	autoJump = aj.getWhat().equals( AutoJump.STARTED );
        }
    }
    
    class BufferEditListener extends BufferAdapter {
        public void contentInserted(JEditBuffer buffer, int startLine, int offset, int numLines, int length) {
            if (buffer == null) {
                return;   
            }
            for (Navigator nav : viewNavigatorMap.values()) {
                nav.contentInserted((Buffer)buffer, startLine, offset, numLines, length);   
            }
            for (Navigator nav : editPaneNavigatorMap.values()) {
                nav.contentInserted((Buffer)buffer, startLine, offset, numLines, length);   
            }
        }
        
        public void contentRemoved(JEditBuffer buffer, int startLine, int offset, int numLines, int length) {
            if (buffer == null) {
                return;   
            }
            for (Navigator nav : viewNavigatorMap.values()) {
                nav.contentRemoved((Buffer)buffer, startLine, offset, numLines, length);   
            }
            for (Navigator nav : editPaneNavigatorMap.values()) {
                nav.contentRemoved((Buffer)buffer, startLine, offset, numLines, length);   
            }
        }
    }
}