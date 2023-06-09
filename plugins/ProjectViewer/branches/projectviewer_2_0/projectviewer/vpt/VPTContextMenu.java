/*
 * :tabSize=4:indentSize=4:noTabs=false:
 * :folding=explicit:collapseFolds=1:
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
package projectviewer.vpt;

//{{{ Imports
import java.util.Iterator;
import java.util.ArrayList;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JTree;
import javax.swing.JPopupMenu;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;

import projectviewer.ProjectViewer;

import projectviewer.action.Action;
import projectviewer.action.SearchAction;
import projectviewer.action.ArchiveAction;
import projectviewer.action.ReimportAction;
import projectviewer.action.FileImportAction;
import projectviewer.action.EditProjectAction;
import projectviewer.action.NodeRemoverAction;
import projectviewer.action.NodeRenamerAction;
import projectviewer.action.OpenWithAppAction;
import projectviewer.action.LaunchBrowserAction;

import projectviewer.config.AppLauncher;
//}}}

/**
 *	A handler for context menu requests on the tree, providing node-sensitive
 *	functionality.
 *
 *	@author		Marcelo Vanzin
 *	@version	$Id$
 */
public class VPTContextMenu extends MouseAdapter {

	//{{{ Static Members

	private static final ArrayList actions = new ArrayList();
	private static final ArrayList intActions = new ArrayList();
	private static long lastMod = System.currentTimeMillis();

	/** Initializes the internal action list. */
	static {
		intActions.add(new EditProjectAction());
		intActions.add(new FileImportAction());
		intActions.add(new NodeRemoverAction(false));
		intActions.add(new NodeRemoverAction(true));
		intActions.add(new NodeRenamerAction());
		intActions.add(new LaunchBrowserAction());
		intActions.add(new OpenWithAppAction());
		intActions.add(new SearchAction());
		intActions.add(new ReimportAction());
		intActions.add(new ArchiveAction());
	}
	
	//{{{ registerAction(Action) method
	/**
	 *	Adds an action to be shown on the context menu. Actions are shown in the
	 *	same order as they are registered.
	 */
	public static void registerAction(Action action) {
		actions.add(action);
		lastMod = System.currentTimeMillis();
	} //}}}

	//{{{ unregisterAction(Action) method
	/** Removes an action from the context menu. */
	public static void unregisterAction(Action action) {
		actions.remove(action);
		lastMod = System.currentTimeMillis();
	} //}}}

	//}}}

	//{{{ Instance Variables
	private final ProjectViewer viewer;
	private final AppLauncher appList;
	private final JPopupMenu popupMenu;
	private final ArrayList internalActions;
	private long pmLastBuilt;
	//}}}

	//{{{ Constructors

	/**
	 *  Constructs a listener that will ask the provided viewer instance for
	 *  information about the nodes clicked.
	 */
	public VPTContextMenu(ProjectViewer viewer) {
		this.viewer = viewer;
		appList = AppLauncher.getInstance();
		internalActions = new ArrayList();
		popupMenu = new JPopupMenu();
		loadGUI();
	}

	//}}}

	//{{{ Event Handling

	//{{{ mousePressed() method
	/** Context-menus are shown on the "pressed" event. */
	public void mousePressed(MouseEvent me) {
		JTree tree = (JTree) me.getSource();

		if (SwingUtilities.isRightMouseButton(me)) {
			TreePath tp = tree.getClosestPathForLocation(me.getX(),me.getY());
			if (tp != null && !tree.isPathSelected(tp)) {
				if ((me.getModifiers() & MouseEvent.CTRL_MASK) == MouseEvent.CTRL_MASK) {
					tree.addSelectionPath(tp);
				} else {
					tree.setSelectionPath(tp);
				}
			}
		}

		if (me.isPopupTrigger()) {
			handleMouseEvent(me);
		}
	} //}}}

	//{{{ mouseReleased() method
	/** Context-menus are shown on the "pressed" event. */
	public void mouseReleased(MouseEvent me) {
		if (me.isPopupTrigger()) {
			handleMouseEvent(me);
		}
	} //}}}

	//}}}

	//{{{ Private Methods

	//{{{ handleMouseEvent() method
	/** Handles the mouse event internally. */
	private void handleMouseEvent(MouseEvent me) {
		JTree tree = viewer.getCurrentTree();

		if (tree.getSelectionCount() == 0) {
			return;
		} else {
			prepareMenu( tree.getSelectionCount() > 1 ? null : viewer.getSelectedNode() );
			popupMenu.show(me.getComponent(), me.getX(), me.getY());
		}
	} //}}}

	//{{{ loadGUI() method
	/** Constructs the menus' GUI. */
	private void loadGUI() {
		internalActions.clear();
		popupMenu.removeAll();

		Action a;
		
		for (Iterator it = intActions.iterator(); it.hasNext(); ) {
			a = (Action) it.next();
			a = (Action) a.clone();
			a.setViewer(viewer);
			internalActions.add(a);
			popupMenu.add(a.getMenuItem());
		}


		if (actions.size() > 0)
			popupMenu.addSeparator();

		for (Iterator it = actions.iterator(); it.hasNext(); ) {
			a = (Action) it.next();
			a = (Action) a.clone();
			a.setViewer(viewer);
			internalActions.add(a);
			popupMenu.add(a.getMenuItem());
		}

		pmLastBuilt = System.currentTimeMillis();
	} //}}}

	//{{{ prepareMenu(VPTNode) method
	/**
	 *	Prepares the context menu for the given node. Shows only menu items
	 *	that are allowed for the node (e.g., "Add Project" only applies for
	 *	the root node). If the node is null, the method guesses that multiple
	 *	nodes are selected, and chooses the appropriate entries.
	 */
	private void prepareMenu(VPTNode selectedNode) {

		if (pmLastBuilt < lastMod) {
			loadGUI();
		}

		for (Iterator it = internalActions.iterator(); it.hasNext(); ) {
			((Action)it.next()).prepareForNode(selectedNode);
		}

	} //}}}

	//}}}

}

