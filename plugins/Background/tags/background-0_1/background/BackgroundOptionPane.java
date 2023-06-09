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


    public BackgroundOptionPane() {
        super("background");
    }


    protected void _init() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel label = new JLabel(jEdit.getProperty("options.background.file"));

        JPanel filePanel = new JPanel(new BorderLayout(5, 0));
        this.tfBackground = new JTextField();
        this.tfBackground.setText(jEdit.getProperty(
              "background.file"
        ));
        this.btnBackground = new JButton(jEdit.getProperty(
            "options.background.choose-file"
        ));
        this.btnBackground.addActionListener(new ActionHandler());
        filePanel.add(this.tfBackground,  BorderLayout.CENTER);
        filePanel.add(this.btnBackground, BorderLayout.EAST);

        JPanel labelPanel = new JPanel(new GridLayout(0, 1));
        labelPanel.setOpaque(false);
        labelPanel.add(label);

        JPanel fieldPanel = new JPanel(new GridLayout(0, 1));
        fieldPanel.setOpaque(false);
        fieldPanel.add(filePanel);

        JPanel northPanel = new JPanel(new BorderLayout(5, 0));
        northPanel.setOpaque(false);
        northPanel.add(labelPanel, BorderLayout.WEST);
        northPanel.add(fieldPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
    }


    protected void _save() {
        jEdit.setProperty(
            "background.file",
            this.tfBackground.getText()
        );
    }


    private class ActionHandler implements ActionListener
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
}

