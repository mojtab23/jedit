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

package projectviewer.tree;

import javax.swing.tree.*;
import java.awt.*;
import javax.swing.*;
import java.net.URL;

import projectviewer.*;


/**
Used by the jTree to change the background font for subscribed projects.

@author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>
@version $Revision$
*/
public class TreeRenderer implements TreeCellRenderer {
 
  private JLabel listCellRenderer = null;
  private JLabel treeCellRenderer = null;

  private Font normalFont;
  private Font openedFont;

  private Icon fileIcon;
  private Icon dirClosedIcon;
  private Icon dirOpenIcon;
  private Icon projectIcon;
  
  private Color treeSelectionForeground;
  private Color treeNoSelectionForeground;
  private Color treeSelectionBackground;
  private Color treeNoSelectionBackground;

  public TreeRenderer() {
    Font f = UIManager.getFont("Tree.font");
    normalFont = new Font(f.getName(), Font.PLAIN, f.getSize());
    openedFont = new Font(f.getName(), Font.BOLD , f.getSize());
    
    treeSelectionForeground = UIManager.getColor("Tree.selectionForeground");
    treeNoSelectionForeground = UIManager.getColor("Tree.textForeground");
    treeSelectionBackground = UIManager.getColor("Tree.selectionBackground");
    treeNoSelectionBackground = UIManager.getColor("Tree.textBackground");
    
    fileIcon      = UIManager.getIcon("Tree.leafIcon");
    dirClosedIcon = UIManager.getIcon("Tree.closedIcon");
    dirOpenIcon   = UIManager.getIcon("Tree.openIcon");

    URL url = getClass().getResource( "/projectviewer/icons/Project.gif" );
    projectIcon = new ImageIcon(url);
  }
    
  public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                boolean sel, boolean expanded,
                                                boolean leaf, int row,
                                                boolean focus)
  {
    if(treeCellRenderer == null) {
      treeCellRenderer = new JLabel() {
        public Dimension getPreferredSize() {
          // this prevents the "..." from showing up
          Dimension d = super.getPreferredSize();
          int width = d.width + (int)(d.width * .15);
          if(d != null) d = new Dimension(width, d.height);
          return d;
        }
      };
      treeCellRenderer.setOpaque(true);
    }
        
    if(sel) {
      treeCellRenderer.setBackground(treeSelectionBackground);
      treeCellRenderer.setForeground(treeSelectionForeground);
    } else {
      treeCellRenderer.setBackground(treeNoSelectionBackground);
      treeCellRenderer.setForeground(treeNoSelectionForeground);
    }

    if (value instanceof ProjectFile) {
      treeCellRenderer.setFont(getFontForFile( (ProjectFile) value ));
    } else {
      treeCellRenderer.setFont(normalFont);
    }

    //set the font for these guys...
    if (value instanceof Project ) {
      treeCellRenderer.setIcon(projectIcon);
    } else if (value instanceof ProjectDirectory) {
      if (expanded) {
        treeCellRenderer.setIcon(dirOpenIcon);
      } else {
        treeCellRenderer.setIcon(dirClosedIcon);
      }
    } else if (value instanceof ProjectFile) {
      treeCellRenderer.setIcon(fileIcon);
    } else {
      if (leaf) {
        treeCellRenderer.setIcon(fileIcon);
      } else if (expanded) {
        treeCellRenderer.setIcon(dirOpenIcon);
      } else {
        treeCellRenderer.setIcon(dirClosedIcon);
      }
    }
    treeCellRenderer.setText(value.toString());
    treeCellRenderer.setEnabled(tree.isEnabled());
    return treeCellRenderer;
  }

  /**
   * Returns the font to use for the specified file.
   */
  protected Font getFontForFile( ProjectFile file ) {
    return file.isOpened()? openedFont : normalFont;
  }
    
}
