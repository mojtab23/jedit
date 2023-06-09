/*
 * ErrorListOptionPane.java - Error list options panel
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 1999, 2000 Slava Pestov
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

package errorlist;

//{{{ Imports
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.jedit.*;
//}}}

public class ErrorListOptionPane extends AbstractOptionPane
{
	//{{{ ErrorListOptionPane constructor
	public ErrorListOptionPane()
	{
		super("error-list");
	} //}}}

	//{{{ _init() method
	protected void _init()
	{
		addComponent(showOnError = new JCheckBox(jEdit.getProperty(
			"options.error-list.showOnError")));
		showOnError.getModel().setSelected(jEdit.getBooleanProperty(
			"error-list.showOnError"));

		addComponent(jEdit.getProperty("options.error-list.warningColor"),
			warningColor = createColorButton("error-list.warningColor"));
		addComponent(jEdit.getProperty("options.error-list.errorColor"),
			errorColor = createColorButton("error-list.errorColor"));
	} //}}}

	//{{{ _save() method
	protected void _save()
	{
		jEdit.setBooleanProperty("error-list.showOnError",showOnError
			.getModel().isSelected());
		jEdit.setProperty("error-list.warningColor",GUIUtilities
			.getColorHexString(warningColor.getBackground()));
		jEdit.setProperty("error-list.errorColor",GUIUtilities
			.getColorHexString(errorColor.getBackground()));
	} //}}}

	//{{{ Private members
	private JCheckBox showOnError;
	private JButton warningColor;
	private JButton errorColor;

	//{{{ createColorButton() method
	private JButton createColorButton(String property)
	{
		JButton b = new JButton(" ");
		b.setBackground(GUIUtilities.parseColor(jEdit.getProperty(property)));
		b.addActionListener(new ActionHandler());
		b.setRequestFocusEnabled(false);
		return b;
	} //}}}

	//}}}

	//{{{ ActionHandler class
	class ActionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			JButton button = (JButton)evt.getSource();
			Color c = JColorChooser.showDialog(ErrorListOptionPane.this,
				jEdit.getProperty("colorChooser.title"),
				button.getBackground());
			if(c != null)
				button.setBackground(c);
		}
	} //}}}
}
