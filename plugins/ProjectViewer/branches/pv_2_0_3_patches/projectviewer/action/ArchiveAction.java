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

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;

import projectviewer.vpt.VPTNode;
import projectviewer.vpt.VPTProject;
import projectviewer.persist.ProjectZipper;
import projectviewer.config.ProjectViewerConfig;
//}}}

/**
 *	Opens the search dialog for the selected directory/project.
 *
 *	@author		Marcelo Vanzin
 *	@version	$Id$
 */
public class ArchiveAction extends Action {

	//{{{ getText() method
	/** Returns the text to be shown on the button and/or menu item. */
	public String getText() {
		return jEdit.getProperty("projectviewer.action.archive");
	} //}}}

	//{{{ actionPerformed(ActionEvent) method
	/** Creates a new project. */
	public void actionPerformed(ActionEvent e) {
		VPTNode node = viewer.getSelectedNode();
		if (node == null || node.isRoot()) return;

		VPTProject project = VPTNode.findProjectFor(node);
		ProjectZipper dlg = new ProjectZipper(viewer.getView(), project.getRootPath(), false);
		ProjectFileFilter ff = new ProjectFileFilter(project);
		dlg.addFileFilter(ff);
		dlg.setSelectedFilter(ff);
		dlg.show();
	} //}}}

	//{{{ prepareForNode(VPTNode) method
	/** Enable action only for the root node. */
	public void prepareForNode(VPTNode node) {
		cmItem.setVisible(node != null && node.isProject());
	} //}}}

	//{{{ ProjectFileFilter class
	/** Accepts only files contained in the project. */
	private static final class ProjectFileFilter implements java.io.FileFilter {

		private final VPTProject project;

		public ProjectFileFilter(VPTProject p) {
			this.project = p;
		}

		public String toString() {
			return jEdit.getProperty("projectviewer.action.archive.filter",
				new Object[] { project.getName() });
		}

		public boolean accept(File f) {
			return f.isDirectory() || project.getChildNode(f.getAbsolutePath()) != null;
		}

	} //}}}

}

