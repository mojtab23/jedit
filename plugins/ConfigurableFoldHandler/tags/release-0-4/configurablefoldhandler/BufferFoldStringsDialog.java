package configurablefoldhandler;

/*
 * BufferFoldStringsDialog.java
 * 
 * Copyright (c) 2002 C.J.Kent
 *
 * :tabSize=4:indentSize=4:noTabs=false:
 * :folding=custom:collapseFolds=0:
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

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.EnhancedDialog;

/**
 * the dialog for specifying buffer fold strings
 */
public class BufferFoldStringsDialog extends EnhancedDialog
{
	private JTextField startField;
	private JTextField endField;
	private JCheckBox useDefault;
	private JCheckBox useRegex;
	private View view;
	
	public BufferFoldStringsDialog(View parent)
	{
		super(parent, jEdit.getProperty(
			"options.configurablefoldhandler.dialog.title"), true);
		
		JButton cancel = new JButton(jEdit.getProperty(
			"options.configurablefoldhandler.dialog.cancel"));
		
		JButton ok = new JButton(jEdit.getProperty(
			"options.configurablefoldhandler.dialog.ok"));
		
		useDefault = new JCheckBox(jEdit.getProperty(
			"options.configurablefoldhandler.use-default-mode"));
		
		useRegex = new JCheckBox(jEdit.getProperty(
			"options.configurablefoldhandler.use-regex"));
		
		JLabel startLabel = new JLabel(jEdit.getProperty(
			"options.configurablefoldhandler.foldstart"));
		JLabel endLabel   = new JLabel(jEdit.getProperty(
			"options.configurablefoldhandler.foldend"));
		
		view = parent;
		
		startField = new JTextField();
		endField   = new JTextField();
		/* Insets insets1 = new Insets(0, 10, 8, 3); */
		Insets insets2 = new Insets(2, 5, 2, 3);
		
		ok.setActionCommand("ok");
		cancel.setActionCommand("cancel");
		useDefault.setActionCommand("usedefault");
		
		ConfigurableFoldHandlerPlugin plugin =
			ConfigurableFoldHandlerPlugin.getInstance();
		
		FoldStrings foldStrings = plugin.getBufferFoldStrings(view.getBuffer());
		
		if(foldStrings == null)
		{
			useDefault.setSelected(true);
			useRegex.setEnabled(false);
			startField.setEnabled(false);
			endField.setEnabled(false);
			
			FoldStrings strings = plugin.getModeFoldStrings(
				view.getBuffer().getMode().getName());
			
			if(strings == null)
				strings = plugin.getDefaultFoldStrings();
			
			useRegex.setSelected(strings.useRegex());
			startField.setText(strings.getStartString());
			endField.setText(strings.getEndString());
		}
		else
		{
			useDefault.setSelected(false);
			useRegex.setEnabled(true);
			startField.setEnabled(true);
			endField.setEnabled(true);
			useRegex.setSelected(foldStrings.useRegex());
			startField.setText(foldStrings.getStartString());
			endField.setText(foldStrings.getEndString());
		}
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String cmd = e.getActionCommand();
				if(cmd.equals("ok"))
					ok();
				else if(cmd.equals("cancel"))
					cancel();
				else if(cmd.equals("usedefault"))
				{
					useRegex.setEnabled(!useDefault.isSelected());
					startField.setEnabled(!useDefault.isSelected());
					endField.setEnabled(!useDefault.isSelected());
				}
			}
		};
		
		cancel.addActionListener(actionListener);
		ok.addActionListener(actionListener);
		useDefault.addActionListener(actionListener);
		
		GridBagLayout gbLayout = new GridBagLayout();
		
		JPanel mainPanel   = new JPanel(gbLayout);
		JPanel bottomPanel = new JPanel(new FlowLayout());
		
		mainPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
		
		bottomPanel.add(ok);
		bottomPanel.add(cancel);
		
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(7, 10, 0, 0);
		cons.gridwidth = 2;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		gbLayout.setConstraints(useDefault, cons);
		mainPanel.add(useDefault);
		
		cons.gridy = 1;
		cons.insets = new Insets(0, 10, 4, 0);
		gbLayout.setConstraints(useRegex, cons);
		mainPanel.add(useRegex);
		
		cons.gridx = 0;
		cons.gridy = 2;
		cons.insets = insets2;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0f;
		gbLayout.setConstraints(startLabel, cons);
		mainPanel.add(startLabel);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1f;
		gbLayout.setConstraints(startField, cons);
		mainPanel.add(startField);
		
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0f;
		gbLayout.setConstraints(endLabel, cons);
		mainPanel.add(endLabel);
		
		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1f;
		gbLayout.setConstraints(endField, cons);
		mainPanel.add(endField);
		
		getRootPane().setDefaultButton(ok);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		setSize(330, 180);
		setLocationRelativeTo(parent);
	}
	
	public void ok()
	{
		if(useDefault.isSelected())
		{
			ConfigurableFoldHandlerPlugin.getInstance().setBufferFoldStrings(
				view.getBuffer(), null);
		}
		else
		{
			ConfigurableFoldHandlerPlugin.getInstance().setBufferFoldStrings(
				view.getBuffer(), new FoldStrings(startField.getText(),
				endField.getText(), useRegex.isSelected()));
		}
		dispose();
	}
	
	public void cancel()
	{
		dispose();
	}
}
