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
package projectviewer.action;

//{{{ Imports
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Enumeration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTree;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;

import org.gjt.sp.util.Log;
import org.gjt.sp.jedit.jEdit;

import projectviewer.ProjectViewer;
import projectviewer.ProjectManager;
import projectviewer.config.ProjectViewerConfig;

import projectviewer.vpt.VPTNode;
import projectviewer.vpt.VPTFile;
import projectviewer.vpt.VPTRoot;
import projectviewer.vpt.VPTProject;
import projectviewer.vpt.VPTDirectory;
//}}}

/**
 *	Action that when executed removes nodes from the trees.
 *
 *	@author		Marcelo Vanzin
 *	@version	$Id$
 */
public class NodeRemoverAction extends Action {

	//{{{ Constants
    private final static int FILE  = 1;
    private final static int DIR   = 2;
    private final static int PROJ  = 3;
    private final static int MULTI = 4;
	//}}}

	//{{{ Constructor
	/**
	 *	Cretes a new node remover actions. Delete defines what "remove" means:
	 *	"false" means it only removes the node from the tree, "true" means it
	 *	will try to delete it. Deletion is only available for file nodes at
	 *	this moment.
	 */
	public NodeRemoverAction(boolean delete) {
		this.delete = delete;
	} //}}}

	//{{{ Instance variables
	private HashSet changed;
	private boolean delete;
	//}}}

	//{{{ getText() method
	/** Returns the text to be shown on the button and/or menu item. */
	public String getText() {
		return (delete) ? jEdit.getProperty("projectviewer.action.delete")
						: null;
	} //}}}

	//{{{ actionPerformed(ActionEvent) method
	/** Try to remove nodes from the project, asking when necessary. */
	public void actionPerformed(ActionEvent e) {
		JTree tree = viewer.getCurrentTree();
		changed = new HashSet();

        switch (tree.getSelectionCount()) {
            case 0:
                // No Selecion! Shouldn't happen, but, just in case...
                return;

            case 1: {
                // Single selection!
                remove((VPTNode)tree.getLastSelectedPathComponent(), true);
                break;
            }

            default: {
                if (confirmAction(MULTI)) {
                    // Any other thing == multiple selection
                    ArrayList sel = getSelectedArtifacts(tree.getSelectionPaths());
                    for (Iterator i = sel.iterator(); i.hasNext(); ) {
                        remove((VPTNode)i.next(), false);
                    }
                }
            }
        }

		ArrayList projects = new ArrayList();
		for (Iterator i = changed.iterator(); i.hasNext(); ) {
			VPTNode next = (VPTNode) i.next();
			if (next.isRoot()) {
				ProjectViewer.nodeStructureChangedFlat(next);
			} else {
				VPTProject p = VPTNode.findProjectFor(next);
				if (!projects.contains(p)) {
					ProjectViewer.nodeStructureChangedFlat(p);
					projects.add(p);
				}
			}
		}
	} //}}}

	//{{{ prepareForNode(VPTNode) method
	/** Enable action only for non-root nodes. */
	public void prepareForNode(VPTNode node) {
		if (delete) {
			if (node == null) {
				// if only writable files are selected, enable the "delete"
				// menu item
				cmItem.setVisible(true);
				JTree tree = viewer.getCurrentTree();
				TreePath[] paths = tree.getSelectionPaths();
				for (int i = 0; i < paths.length; i++) {
					VPTNode n = (VPTNode) paths[i].getLastPathComponent();
					if (!n.isFile() || !((VPTFile)n).canWrite()) {
						cmItem.setVisible(false);
						break;
					}
				}
			} else {
				cmItem.setVisible(node.isFile() && node.canWrite());
			}
		} else {
			if (node == null) {
				((JMenuItem)cmItem).setText(
					jEdit.getProperty("projectviewer.action.remove_multiple"));
			} else {
				((JMenuItem)cmItem).setText(
					node.isProject() ? jEdit.getProperty("projectviewer.action.remove_project")
									 : jEdit.getProperty("projectviewer.action.remove"));
			}cmItem.setVisible(node == null || !node.isRoot());
		}
	} //}}}

    //{{{ getSelectedArtifacts(TreePath[]) method
    /**
     *  Receives a collection of TreePath objects and returns the underlying
     *  objects selected, removing a child when its parent has also been
     *  selected.
     */
    private ArrayList getSelectedArtifacts(TreePath[] paths) {
        TreePath last = null;
        ArrayList objs = new ArrayList();

        for (int i = 0; i < paths.length; i++) {
            if (last != null && !paths[i].isDescendant(last)) {
                last = null;
            }

            if (last == null) {
                last = paths[i];
                objs.add(paths[i].getLastPathComponent());
            }
        }

        return objs;
    } //}}}

	//{{{ confirmAction(int) method
     /**
     *  Asks the user if he reeeeeally wants to delete the selection he's
     *  made.
     *
     *  @param  delete  If the selection will be deleted or only removed
     *                  from the project;
     *  @param  type    Type of object being removed. 1=File 2=Directory 3=Project
     *                  4=Multiple objects
     */
    private boolean confirmAction(int type) {
        String message = null;

        // Sanity check
        if (type > MULTI || type < FILE) return false;

        if (delete) {
            switch (type) {
                case FILE: // File
                    message = jEdit.getProperty("projectviewer.remove.confirm_file_del");
                    break;

                case DIR: // Directory
                    message = jEdit.getProperty("projectviewer.remove.confirm_dir_del");
                    break;

                case MULTI: // Multiple
                    message = jEdit.getProperty("projectviewer.remove.confim_multi_del");
                    break;

                default: // No other deletion is supported (yet?)
                    return false;
            }
        } else {
            switch(type) {
                case FILE: // File
                    message = null;
                    break;

                case DIR: // Directory
                    message = jEdit.getProperty("projectviewer.remove.confirm_dir_remove");
                    break;

                case PROJ: // Project
                    message = jEdit.getProperty("projectviewer.remove.confirm_project_remove");
                    break;

                case MULTI: // Multiple
                    message = jEdit.getProperty("projectviewer.remove.confirm_multi_remove");
                    break;

                default: // Won't happen, but...
            }
        }

        if (message == null) return true;

		return (JOptionPane.showConfirmDialog(viewer,
            		message,
					jEdit.getProperty("projectviewer.remove.confirm.title"),
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    } //}}}

    //{{{ remove(Object, boolean) method
    /**
     *  Removes an object from a project (or the project, if the object is one).
     *
     *  @param  o       The object.
     *  @param  delete  If deletion from disk should occur.
     *  @param  ask     Is we should ask for confirmation.
     */
    private void remove(VPTNode o, boolean ask) {
		boolean removed = false;

		if(o == null || o.isRoot()) {
			Log.log(Log.WARNING, this, "Removing: unexpected object o: " + o);
			return;
		}

		if (o.isProject()) {
            if (!ask || confirmAction(PROJ)) {
                ProjectManager.getInstance().removeProject((VPTProject)o);
				changed.add(VPTRoot.getInstance());
            }
        } else {
			VPTProject project = VPTNode.findProjectFor(o);

			if (o.isFile()) {
				if (!delete || (!ask || confirmAction(FILE))) {
					ProjectViewer.removeNodeFromParent(o);
					project.unregisterFile((VPTFile)o);
					if (delete) o.delete();
					removed = true;
				}
			} else if (o.isDirectory()) {
				if (!ask || confirmAction(DIR)) {
					unregisterFiles((VPTDirectory)o, project);
					ProjectViewer.removeNodeFromParent(o);
					removed = true;
				}
			} else {
				ProjectViewer.removeNodeFromParent(o);
			}

			if (ProjectViewerConfig.getInstance().getSaveOnChange()) {
				ProjectManager.getInstance().saveProject(project);
			}

			if (removed) changed.add(project);
		}


    } //}}}

	//{{{ unregisterFiles(VPTDirectory) method
	/**
	 *	Recursively unregisters the files below the given directory from the
	 *	project.
	 */
	private void unregisterFiles(VPTDirectory dir, VPTProject proj) {
		Enumeration e = dir.children();
		while (e.hasMoreElements()) {
			VPTNode n = (VPTNode) e.nextElement();
			if (n.isDirectory()) {
				unregisterFiles((VPTDirectory)n, proj);
			} else if (n.isFile()) {
				proj.unregisterFile((VPTFile)n);
			}
		}
	} //}}}

}

