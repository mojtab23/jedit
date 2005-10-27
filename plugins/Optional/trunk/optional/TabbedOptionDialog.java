/*
 * TabbedOptionDialog.java - Plugin options model
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 2005 Slava Pestov, Alan Ezust
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

package optional;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;


import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.OptionGroup;
import org.gjt.sp.jedit.OptionPane;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.gui.EnhancedDialog;
import org.gjt.sp.jedit.gui.OptionsDialog.OptionTreeModel;


/**
 * Replacement for OptionsDialog.
 * It uses the OptionGroupPane instead of managing its own optons.
 * 
 * This class should eventually replace @ref OptionsDialog
 * 
 * @author ezust
 *
 */

public class TabbedOptionDialog extends EnhancedDialog implements ActionListener
{
	JTabbedPane tabs;
	LinkedList panes;
	private JButton ok;
	private JButton cancel;
	private JButton apply;

	public TabbedOptionDialog(Frame frame, String name)
	{
		super(frame, jEdit.getProperty(name + ".title"), true);
		setName(name);
		setupTabs();
		
	} //}}}

	//{{{ OptionsDialog constructor
	public TabbedOptionDialog(Dialog dialog, String name) 
	{
		super(dialog, jEdit.getProperty(name + ".title"), true);
		setName(name);
		setupTabs();
		
	} //}}}

	
	void setSelectedIndex(int index) {
		tabs.setSelectedIndex(index);
	}
	
	void setupTabs() 
	{
		panes = new LinkedList();
		tabs = new JTabbedPane();
		JPanel content = new JPanel(new BorderLayout());
		content.setBorder(new EmptyBorder(12,12,12,12));
		
		content.add(tabs, BorderLayout.CENTER);

		Box buttons = new Box(BoxLayout.X_AXIS);
		buttons.add(Box.createGlue());

		ok = new JButton(jEdit.getProperty("common.ok"));
		ok.addActionListener(this);
		buttons.add(ok);
		buttons.add(Box.createHorizontalStrut(6));
		getRootPane().setDefaultButton(ok);
		cancel = new JButton(jEdit.getProperty("common.cancel"));
		cancel.addActionListener(this);
		buttons.add(cancel);
		buttons.add(Box.createHorizontalStrut(6));
		apply = new JButton(jEdit.getProperty("common.apply"));
		apply.addActionListener(this);
		buttons.add(apply);

		buttons.add(Box.createGlue());

		content.add(buttons, BorderLayout.SOUTH);
		setContentPane(content);
		GUIUtilities.loadGeometry(this, getName());

	}
	public void actionPerformed(ActionEvent evt)
	{
		Object source = evt.getSource();

		if(source == ok)
		{
			ok();
		}
		else if(source == cancel)
		{
			cancel();
		}
		else if(source == apply)
		{
			ok(false);
		}
	} //}}}

	public void addOptionPane(OptionPane pane) {
		panes.add(pane);
		JPanel panel = (JPanel) pane;
		tabs.addTab(pane.getName(), panel);
		
	}
	public void addOptionGroup(OptionGroup group) {
		OptionGroupPane pane = new OptionGroupPane(group);
		addOptionPane(pane);
	}
	
	
	public void ok()
	{
		ok(true);
	}
	
	public void ok(boolean dispose) 
	{
		GUIUtilities.saveGeometry(this, getName());

		Iterator itr = panes.iterator();
		while (itr.hasNext() ) {
			OptionPane op = (OptionPane) itr.next();
			op.save();
		}
		Point p = getLocation();
		Dimension d = getSize();

		/* This will fire the PROPERTIES_CHANGED event */
		jEdit.propertiesChanged();

		// Save settings to disk
		jEdit.saveSettings();

		// get rid of this dialog if necessary
		if(dispose)
			dispose();
	} //}}}

	public void cancel()
	{
		GUIUtilities.saveGeometry(this, getName());

		dispose();
	}

	
}
