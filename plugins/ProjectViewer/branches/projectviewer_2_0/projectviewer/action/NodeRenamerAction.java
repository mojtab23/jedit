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
import java.io.File;
import java.util.Iterator;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.Buffer;

import projectviewer.ProjectViewer;
import projectviewer.ProjectManager;
import projectviewer.vpt.VPTNode;
import projectviewer.vpt.VPTFile;
import projectviewer.vpt.VPTProject;
import projectviewer.vpt.VPTDirectory;
//}}}

/**
 *	Action for renaming files, directories and projects.
 *
 *	@author		Marcelo Vanzin
 *	@version	$Id$
 */
public class NodeRenamerAction extends Action {
	
	//{{{ getText() method
	/** Returns the text to be shown on the button and/or menu item. */
	public String getText() {
		return jEdit.getProperty("projectviewer.action.rename");
	} //}}}
	
	//{{{ getIcon() method
	/** Returns null. This action shouldn't be added to the toolbar. */
	public Icon getIcon() {
		return null;
	} //}}}
	
	//{{{ actionPerformed(ActionEvent) method
	/** Renames a node. */
	public void actionPerformed(ActionEvent e) {
		VPTNode node = viewer.getSelectedNode();
		boolean isValid = false;
		String newName = null;
		
		while (!isValid) {
			newName = JOptionPane.showInputDialog(viewer,
				jEdit.getProperty("projectviewer.action.rename.message"),
				jEdit.getProperty("projectviewer.action.rename.title"),
				JOptionPane.PLAIN_MESSAGE);
			
			if (newName == null || newName.length() == 0) {
				return;
			}
			
			// checks the input
			if (node.isFile() || node.isDirectory()) {
				if (newName.indexOf('/') != -1 || newName.indexOf('\\') != -1) {
					JOptionPane.showMessageDialog(viewer,
						jEdit.getProperty("projectviewer.action.rename.file_error"),
						jEdit.getProperty("projectviewer.action.rename.title"),
						JOptionPane.ERROR_MESSAGE);
				} else {
					isValid = true;
				}
			} else if (node.isProject()) {
				if (ProjectManager.getInstance().hasProject(newName)) {
					JOptionPane.showMessageDialog(viewer,
						jEdit.getProperty("projectviewer.project.options.name_exists"),
						jEdit.getProperty("projectviewer.action.rename.title"),
						JOptionPane.ERROR_MESSAGE);
				} else {
					isValid = true;
				}
			}
		}

		// renames the node		
		if (node.isFile()) {
			VPTFile f = (VPTFile) node;

			// updates all files from the old directory to point to the new one
			while (!node.isProject()) {
				node = (VPTNode) node.getParent();
			}

			((VPTProject)node).unregisterFile(f);
			
			if (!renameFile(f, new File(f.getFile().getParent(), newName))) {
				JOptionPane.showMessageDialog(viewer,
						jEdit.getProperty("projectviewer.action.rename.rename_error"),
						jEdit.getProperty("projectviewer.action.rename.title"),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			((VPTProject)node).registerFile(f);
			ProjectViewer.nodeChanged(f);
		} else if (node.isDirectory()) {
			VPTDirectory dir = (VPTDirectory) node;
			String oldDir = dir.getFile().getAbsolutePath();
			String newDir = dir.getFile().getParent() + File.separator + newName;
			File newFile = new File(newDir);

			if (!dir.getFile().renameTo(newFile)) {
				JOptionPane.showMessageDialog(viewer,
						jEdit.getProperty("projectviewer.action.rename.rename_error"),
						jEdit.getProperty("projectviewer.action.rename.title"),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			dir.setFile(newFile);
			
			// updates all files from the old directory to point to the new one
			while (!node.isProject()) {
				node = (VPTNode) node.getParent();
			}
			
			for (Iterator i = ((VPTProject)node).getFiles().iterator(); i.hasNext(); ) {
				VPTFile f = (VPTFile) i.next();
				if (f.getNodePath().startsWith(oldDir)) {
					renameFile(f, new File(dir.getFile(), f.getName()));
				}
			}
			ProjectViewer.nodeChanged(dir);
		} else if (node.isProject()) {
			String oldName = node.getName();
			node.setName(newName);
			ProjectManager.getInstance().renameProject(oldName, newName);
			ProjectViewer.nodeChanged(node);
		}
	
	} //}}}

	//{{{ prepareForNode(VPTNode) method
	/** Disable action only for the root node. */
	public void prepareForNode(VPTNode node) {
		cmItem.setVisible(node != null && 
			(node.isFile() || node.isDirectory() || node.isProject()));
	} //}}}
	
	//{{{ renameFile(VPTFile, String) method
	/** Renames a file and tries not to mess up jEdit's current buffer. */
	private boolean renameFile(VPTFile f, File newFile) {
		Buffer b = jEdit.getActiveView().getBuffer();
		if (b.getPath().equals(f.getNodePath())) {
			b = null;
		}
		boolean open = f.isOpened();
		f.close();
		if (!f.getFile().renameTo(newFile)) {
			return false;
		}
		f.setFile(newFile);
		if (open) {
			// this is an ugly hack to avoid "file has been modified on
			// disk" warnings that shouldn't happen, but do.
			try { Thread.sleep(1); } catch (Exception e) { }
			f.open();
			if (b != null) 
				jEdit.getActiveView().setBuffer(b);
		}
		return true;
	} //}}}
}

