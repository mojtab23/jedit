/*
 *  Jump plugin for jEdit
 *  Copyright (c) 2003 Pavlikus
 *
 *  :tabSize=4:indentSize=4:
 *  :folding=explicit:collapseFolds=1:
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package jump;

import java.io.File;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;


// TODO: tabs_files - can't be a HashMap.
// If may cause problems when more than one build.xml, for ex.,
// founded in current project.
class FilesJumpAction {
    private Object[] files;
    public HashMap tabs_files = new HashMap();
    private View view;
    private ProjectBuffer currentTags;
    private String[] unsupported_ext = {
        "gif", "jpg", "png", "jpeg", "class", "bat"
    };

    public FilesJumpAction() {
        super();
        tabs_files = new HashMap();
    }

    public void showList() {
        files = getFileList();

        if (files == null) {
            return;
        }

        FilesJumpMenu jl = new FilesJumpMenu(view, files, new FilesListModel(),
                true, "File to jump:", 30);
    }

    private Object[] getFileList() {
        view = jEdit.getActiveView();

        currentTags = JumpPlugin.getActiveProjectBuffer();

        if (currentTags == null) {
            return null;
        }

        if (currentTags.files.size() < 2) {
            return null;
        }

        Vector files = currentTags.files;

        // Temporary Vector to store only supperted files (no jpg, png etc.)
        Vector valid_files = new Vector();
        String tmp_file = new String();

        for (int i = 0; i < files.size(); i++) {
            String path = new String();
            path = (String) files.get(i);

            if (isSupportedExtension(path)) {
                tmp_file = path.substring(path.lastIndexOf(File.separator) + 1);
                valid_files.add(tmp_file);
                tabs_files.put(tmp_file, path);
            }
        }

        if (valid_files.size() > 1) {
            Object[] arr = valid_files.toArray();
            Arrays.sort(arr, new AlphabeticComparator());

            return arr;
        } else {
            return null;
        }
    }

    private boolean isSupportedExtension(String filename) {
        for (int i = 0; i < unsupported_ext.length; i++) {
            if (filename.endsWith(unsupported_ext[i])) {
                return false;
            }
        }

        return true;
    }

    public class FilesJumpMenu extends JumpList {
        public FilesJumpMenu(View parent, Object[] list, ListModel model,
            boolean incr_search, String title, int list_width) {
            super(parent, list, model, incr_search, title, list_width,
                Jump.getListLocation());
        }

        public void updateStatusBar(Object o) {
            // TODO: Check property SHOW_STATUSBAR_MESSAGES before
            // proceed updateStatusBar()
            JList l = (JList) o;
            String tab_name = (String) l.getModel().getElementAt(l.getSelectedIndex());
            String file_name = (String) tabs_files.get(tab_name);
            view.getStatus().setMessageAndClear("file: " + file_name);
        }

        public void processAction(Object o) {
            JList l = (JList) o;

            String tab_name = (String) l.getModel().getElementAt(l.getSelectedIndex());
            String file_name = (String) tabs_files.get(tab_name);
            jEdit.openFile(parent, file_name);
        }

        public void processActionInNewView(Object o) {
            JList l = (JList) o;
            String tab_name = (String) l.getModel().getElementAt(l.getSelectedIndex());
            String file_name = (String) tabs_files.get(tab_name);
            View new_view = jEdit.newView(jEdit.getActiveView());
            jEdit.openFile(new_view, file_name);
            GUIUtilities.requestFocus(jEdit.getActiveView(),
                new_view.getTextArea());
        }

    }

    class FilesListModel extends AbstractListModel {
        public int getSize() {
            return files.length;
        }

        public Object getElementAt(int index) {
            return files[index];
        }
    }


    class AlphabeticComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;

            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }
}
