/*
 * BackgroundOptionPane.java
 * Copyright (c) 2002 Andre Kaplan
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


package background;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.AbstractOptionPane;
import org.gjt.sp.jedit.jEdit;


public class BackgroundOptionPane extends AbstractOptionPane
{
    private JButton    btnBackground;
    private JTextField tfBackground;
    private JCheckBox  blend;
    private JButton    blendColor;


    public BackgroundOptionPane() {
        super("background");
    }


    protected void _init() {
        this.tfBackground = new JTextField();
        this.tfBackground.setText(jEdit.getProperty(
              "background.file"
        ));
        this.btnBackground = new JButton(jEdit.getProperty(
            "options.background.choose-file"
        ));
        this.btnBackground.addActionListener(new FileActionHandler());

        JPanel filePanel = new JPanel(new BorderLayout(5, 0));
        filePanel.add(this.tfBackground,  BorderLayout.CENTER);
        filePanel.add(this.btnBackground, BorderLayout.EAST);

        addComponent(
            jEdit.getProperty("options.background.file"),
            filePanel
        );

        this.blend = new JCheckBox(
            jEdit.getProperty("options.background.blend"),
            jEdit.getBooleanProperty("background.blend", false)
        );
        addComponent(this.blend);

        this.blendColor = this.createColorButton(
            "background.blend-color", jEdit.getColorProperty("view.bgColor", Color.white)
        );
        addComponent(
            jEdit.getProperty("options.background.blend-color"),
            this.blendColor
        );
    }


    protected void _save() {
        jEdit.setProperty(
            "background.file", this.tfBackground.getText()
        );

        jEdit.setBooleanProperty(
            "background.blend", this.blend.isSelected()
        );

        jEdit.setColorProperty(
            "background.blend-color", this.blendColor.getBackground()
        );
    }


    private class FileActionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt) {
            String[] paths = GUIUtilities.showVFSFileDialog(
                null, null, JFileChooser.OPEN_DIALOG, false
            );

            if (paths != null) {
                BackgroundOptionPane.this.tfBackground.setText(paths[0]);
            }
        }
    }


    private JButton createColorButton(String property, Color defaultColor) {
        JButton b = new JButton(" ");
        b.setBackground(jEdit.getColorProperty(property, defaultColor));
        b.addActionListener(new ColorActionHandler());
        b.setRequestFocusEnabled(false);
        return b;
    }


    private class ColorActionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt) {
            JButton button = (JButton) evt.getSource();
            Color c = JColorChooser.showDialog(BackgroundOptionPane.this,
                jEdit.getProperty("colorChooser.title"),
                button.getBackground()
            );
            if (c != null) {
                button.setBackground(c);
            }
        }
    }
}

