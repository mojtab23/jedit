/*
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
package projectviewer.ui.actions;

import javax.swing.JOptionPane;
import projectviewer.*;
import projectviewer.ui.ProjectViewer;


/**
 * An action to create a new project.
 */
public class CreateProjectAction extends ActionBase {

   /**
    * Create a new <code>CreateProjectAction</code>.
    */
   public CreateProjectAction(ProjectViewer aProjectViewer) {
      super("create-project", aProjectViewer);
   }

   /**
    * Open up a project.
    */
   protected void performAction() throws ProjectException {
      String value = JOptionPane.showInputDialog(projectViewer.getView(), "Project Name:");
      if (value != null) {
         Project prj = new SimpleProject(value);
         getPlugin().getProjectManager().addProject(prj);
         projectViewer.getTreeModel().load();
         projectViewer.setProject(prj);
      }
   }

}
