//          \includegraphics*[width=7cm]{graphics\complexes.png}
//      :latex.root='D:\Projects\Thesis\src\Thesis.tex':
package uk.co.antroy.latextools;

import console.Console;
import console.Shell;

import gnu.regexp.RE;
import gnu.regexp.REException;
import gnu.regexp.REMatch;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.*;

import java.util.*;

import javax.swing.*;
import javax.swing.JPanel;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.HistoryTextField;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.textarea.JEditTextArea;


public class LaTeXMacros {
    public static final String MAIN_TEX_FILE_KEY = "latex.root";
    public static final String IMPORT_REG_EX = "\\\\(?:(?:input)|(?:import))\\{(.*?)\\}";

    public static void repeat(String expression, int start, int no, View view) {
        StringBuffer sb = new StringBuffer("");

        for (int i = start; i < (no + start); i++) {
            String replace = "" + i;
            String exp = "";

            try {
                RE regEx = new RE("\\#");
                exp = regEx.substituteAll(expression, replace);
            } catch (REException e) {
            }

            //String exp = expression.replaceAll("#",replace);
            sb.append(exp).append("\n");
        }

        view.getTextArea().setSelectedText(sb.toString());
    }

    public static void repeat(View view, boolean startDialog) {
        String expression = Macros.input(view, 
                                         "Enter expression (# where numbers should go)");

        if (expression == null)

            return;

        String noString = Macros.input(view, "Enter number of iterations");

        if (noString == null)

            return;

        int no = Integer.parseInt(noString);
        int start;

        if (startDialog) {
            String startString = Macros.input(view, "Enter start number");

            if (startString == null)

                return;

            start = Integer.parseInt(startString);
        } else {
            start = 1;
        }

        repeat(expression, start, no, view);
    }

    public static void surround(View view, String prefix, String suffix) {
        JEditTextArea textArea = view.getTextArea();
        int caret = textArea.getCaretPosition();

        //      prefix = Macros.input(view, "Enter prefix");
        //      suffix = Macros.input(view, "Enter suffix");
        if (prefix == null || prefix.length() == 0)

            return;

        if (suffix == null || suffix.length() == 0)
            suffix = prefix;

        String text = textArea.getSelectedText();

        if (text == null)
            text = "";

        StringBuffer sb = new StringBuffer();
        sb.append(prefix);
        sb.append(text);
        sb.append(suffix);
        textArea.setSelectedText(sb.toString());

        //if no selected text, put the caret between the tags
        if (text.length() == 0)
            textArea.setCaretPosition(caret + prefix.length());
    }

    public static void surround(View view) {
        String prefix = Macros.input(view, "Enter prefix");

        if (prefix == null)

            return;

        String suffix = Macros.input(view, "Enter suffix");

        if (suffix == null)

            return;

        surround(view, prefix, suffix);
    }

    public static void newCommand(View view) {
        String command = Macros.input(view, "Enter command");

        if (command == null)

            return;

        surround(view, "\\" + command + "{", "}");
    }

    public static void newEnvironment(View view) {
        String env = Macros.input(view, "Enter environment name");

        if (env == null)

            return;

        surround(view, "\\begin{" + env + "}\n", "\n\\end{" + env + "}");
    }

    // ***** Macros to work with a main file **********
    public static void setMainFile(Buffer buffer) {
        String currentFile = buffer.getPath();
        jEdit.setProperty(MAIN_TEX_FILE_KEY, currentFile);
    }

    public static void resetMainFile() {
        jEdit.setProperty(MAIN_TEX_FILE_KEY, "");
    }

    public static void showMainFile(View v) {
        Macros.message(v, getMainTeXPath(v.getBuffer()));
    }

    public static String getMainTeXPath(Buffer buffer) {
        boolean mainInFile = false;
        String path = "";
        StringBuffer regex = new StringBuffer(":");
        regex.append(MAIN_TEX_FILE_KEY);
        regex.append("=(?:'|\"){0,1}(.*?)(?:'|\"){0,1}:");
        REMatch match = findInDocument(buffer, regex.toString(), 0, 5);

        if (match != null) {
            path = match.toString(1);
            mainInFile = true;
        }

        File texFile = new File(buffer.getPath());
        String tex;

        if (mainInFile) {
            File main = new File(path);

            if (main.exists()) {
                tex = main.getAbsolutePath();
            } else {
                tex = new File(texFile.getParentFile(), path).getAbsolutePath();
            }
        } else {
            tex = jEdit.getProperty(MAIN_TEX_FILE_KEY);
        }

        if (tex == null || tex.equals("") || !(new File(tex).exists())) {
            tex = buffer.getPath().toString();
        }

        return tex;
    }

    public static String getMainTeXDir(Buffer buffer) {

        return getMainTeXFile(buffer).getParent();
    }

    public static File getMainTeXFile(Buffer buffer) {

        return new File(getMainTeXPath(buffer));
    }

    private static REMatch findInDocument(Buffer buf, String regex) {

        return findInDocument(buf, regex, 0, buf.getLineCount());
    }

    private static REMatch findInDocument(Buffer buf, String regex, 
                                          int startLine, int endLine) {

        for (int i = startLine;
             i < (buf.getLineCount() < endLine ? buf.getLineCount() : endLine);
             i++) {
            String s = buf.getLineText(i);
            RE exp = null;

            try {
                exp = new RE(regex, RE.REG_ICASE | RE.REG_MULTILINE);
            } catch (Exception e) {
                e.printStackTrace();
            }

            REMatch match = exp.getMatch(s);

            if (match != null) {

                return match;
            }
        }

        return null;
    }

    private static REMatch[] findAllInDocument(Buffer buf, String regex) {
       return null;
    }
                                             
    public static void compile(View view, Buffer buffer, boolean prompt) {
        String tex = getMainTeXPath(buffer);

        if (!(tex.substring(tex.length() - 3, tex.length()).equals("tex"))) {
            Macros.error(view, tex + " is not a TeX file.");

            return;
        }

        String command;

        if (prompt) {
            CommandHistoryDialog hd = new CommandHistoryDialog(view);
            hd.setVisible(true);
            command = hd.getCommand();

            if (command == null) {
                Macros.message(view, "Compile Aborted...");

                return;
            }
        } else {
            command = jEdit.getProperty("latex.compile.command");
        }

        jEdit.saveAllBuffers(view, false);
        String texRoot = new File(tex).getParent().toString();
        StringBuffer str = new StringBuffer(command);
        str.append(" '").append(tex).append("'");
        command = str.toString();
        runCommand(view, texRoot, command);
    }

    private static void runCommand(View view, String dir, String command) {
        Console console = (Console)view.getDockableWindowManager().getDockable(
                                  "console");
        Shell _shell = Shell.getShell("System");
        console.setShell(_shell);
        console.run(_shell, console, "%kill");
        console.run(_shell, console, "cd " + '"' + dir + '"');
        console.run(_shell, console, command);
    }

    public static void bibtex(View view, Buffer buffer) {
        String tex = getMainTeXPath(buffer);

        if (!(tex.substring(tex.length() - 3, tex.length()).equals("tex"))) {
            Macros.error(view, tex + " is not a TeX file.");

            return;
        }

        if (!new File(tex).exists()) {
            Macros.error(view, tex + " is not a TeX file.");

            return;
        }

        String texRoot = new File(tex).getParent().toString();
        tex = tex.substring(0, tex.length() - 4);
        String str = "bibtex '" + tex + "'";
        runCommand(view, texRoot, str);
    }

    public static void deleteWorkingFiles(View view, Buffer buffer) {
        String tex = getMainTeXPath(buffer);

        if (!(tex.substring(tex.length() - 3, tex.length()).equals("tex"))) {
            Macros.error(view, tex + " is not a TeX file.");

            return;
        }

        String[] extensions = {
            ".log", ".bak", ".aux", ".bbl", ".blg", ".toc", ".pdf", ".xyc"
        };
        WorkingClassDialog dialog = new WorkingClassDialog(view, extensions);
        dialog.setVisible(true);
        Set toRemove = dialog.getToRemove();

        if (toRemove == null) {

            return;
        }

        File dir = (new File(tex)).getParentFile();
        File[] toDelete = dir.listFiles(new ExtensionFilter(toRemove));
        StringBuffer sb = new StringBuffer(
                                  "<html><h3>About to delete the following files:</h3>");

        for (int i = 0; i < toDelete.length; i++) {
            sb.append(toDelete[i]);
            sb.append("<br>");
        }

        sb.append("<br><b>Erase Now?");
        int del = Macros.confirm(view, sb.toString(), 
                                 JOptionPane.YES_NO_OPTION);

        if (del == JOptionPane.YES_OPTION) {

            for (int i = 0; i < toDelete.length; i++) {
                toDelete[i].delete();
            }
        }
    }

    private static class CommandHistoryDialog
        extends JDialog
        implements ActionListener,
                   WindowListener {
        private HistoryTextField htf;
        private String command;

        CommandHistoryDialog(Frame owner) {
            super(owner, "Enter Compilation Command", true);
            JPanel panel = new JPanel();
            htf = new HistoryTextField("latextools.compile.history", false, 
                                       true);
            htf.setColumns(20);

            if (htf.getModel().getSize() > 0) {
                htf.setText(htf.getModel().getItem(0));
            }

            htf.addActionListener(this);
            panel.add(htf, BorderLayout.NORTH);
            JButton ok = new JButton("OK");
            JButton cancel = new JButton("Cancel");
            ok.addActionListener(this);
            cancel.addActionListener(this);
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(ok);
            buttonPanel.add(cancel);
            panel.add(buttonPanel, BorderLayout.SOUTH);
            setContentPane(panel);
            addWindowListener(this);
            pack();
            setLocation(getCenter(owner, this));
        }

        public String getCommand() {

            return command;
        }

        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand() == "OK") {
                command = htf.getText();
                setVisible(false);
            } else if (e.getActionCommand() == "Cancel") {
                command = null;
                setVisible(false);
            }
        }

        public void windowActivated(WindowEvent e) {
        }

        public void windowClosed(WindowEvent e) {
        }

        public void windowClosing(WindowEvent e) {
            command = null;
            setVisible(false);
        }

        public void windowDeactivated(WindowEvent e) {
        }

        public void windowDeiconified(WindowEvent e) {
        }

        public void windowIconified(WindowEvent e) {
        }

        public void windowOpened(WindowEvent e) {
        }
    }

    private static class WorkingClassDialog
        extends JDialog
        implements ActionListener {
        private String[] extensions;
        private Set toRemove;
        private JCheckBox[] boxes;

        WorkingClassDialog(Frame owner, String[] extensions) {
            super(owner, "Erase Working Files", true);
            this.extensions = extensions;
            toRemove = new HashSet();
            boxes = new JCheckBox[extensions.length];
            JPanel boxPanel = new JPanel(new GridLayout(0, 2));

            for (int i = 0; i < boxes.length; i++) {
                boxes[i] = new JCheckBox(extensions[i]);
                boxes[i].setSelected(true);
                boxPanel.add(boxes[i]);
            }

            JButton ok = new JButton("OK");
            JButton cancel = new JButton("Cancel");
            ok.addActionListener(this);
            cancel.addActionListener(this);

            if (boxes.length % 2 == 1) {
                boxPanel.add(new JLabel(""));
            }

            boxPanel.add(ok);
            boxPanel.add(cancel);
            setContentPane(boxPanel);
            pack();
            setLocation(getCenter(owner, this));
        }

        public Set getToRemove() {

            return toRemove;
        }

        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand() == "OK") {

                for (int i = 0; i < extensions.length; i++) {

                    if (boxes[i].isSelected()) {
                        toRemove.add(boxes[i].getText());
                    }
                }

                setVisible(false);
            } else if (e.getActionCommand() == "Cancel") {
                toRemove = null;
                setVisible(false);
            }
        }
    }

    private static class ExtensionFilter
        implements FilenameFilter {
        StringBuffer sb;
        RE regEx;

        ExtensionFilter(Set accept) {
            Iterator it = accept.iterator();
            sb = new StringBuffer("(\\w+?\\");
            sb.append(it.next()).append(")");

            for (; it.hasNext();) {
                sb.append("|(\\w+?\\");
                sb.append(it.next()).append(")");
            }

            sb.append("\\b");

            try {
                regEx = new RE(sb.toString());
            } catch (REException e) {
                e.printStackTrace();
            }
        }

        public boolean accept(File dir, String name) {

            return regEx.getMatch(name) != null;
        }
    }

    public static void openImport(View view, Buffer buffer){
        String tex = getMainTeXPath(buffer);
        if (!(tex.substring(tex.length() - 3, tex.length()).equals("tex"))) {
            Macros.error(view, tex + " is not a TeX file.");
            return;
        }
       int line = view.getTextArea().getCaretLine();

        REMatch match = findInDocument(buffer , IMPORT_REG_EX, line, line +1);
        if (match == null) return;
        
        String file = match.toString(1);
        if (file.indexOf(".") < 0) {
           file += ".tex";
        }
        
        File imported = new File(getMainTeXFile(buffer).getParentFile(), file);
        
        jEdit.openFile(view, imported.toString());
     }
    
    public static File[] getProjectFiles(Buffer buffer){
       
       
       
       return null;
    }
    
    private static Point getCenter(Component parent, Component dialog) {
        Dimension pd = parent.getSize();
        Dimension cd = dialog.getSize();
        Point pp = parent.getLocation();
        Point cp = new Point(pp);
        int x = (int)((pd.getWidth() - cd.getWidth()) / 2);
        int y = (int)((pd.getHeight() - cd.getHeight()) / 2);
        cp.translate(x, y);

        return cp;
    }
}
