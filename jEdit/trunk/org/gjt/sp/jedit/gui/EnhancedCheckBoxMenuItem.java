/*
 * EnhancedCheckBoxMenuItem.java - Check box menu item
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 1999, 2000, 2001, 2002 Slava Pestov
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

package org.gjt.sp.jedit.gui;

//{{{ Imports
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;
//}}}

/**
 * jEdit's custom menu item. It adds support for multi-key shortcuts.
 */
public class EnhancedCheckBoxMenuItem extends JCheckBoxMenuItem
{
	//{{{ EnhancedCheckBoxMenuItem constructor
	public EnhancedCheckBoxMenuItem(String label, EditAction action)
	{
		this.action = action;
		this.shortcut = getShortcut();
		if(OperatingSystem.isMacOSLF() && shortcut != null)
		{
			setText(label + " (" + shortcut + ")");
			shortcut = null;
		}
		else
			setText(label);

		if(action != null)
		{
			setEnabled(true);
			addActionListener(new EditAction.Wrapper(action));

			addMouseListener(new MouseHandler());
		}
		else
			setEnabled(false);

		setModel(new Model());
	} //}}}

	//{{{ getPreferredSize() method
	public Dimension getPreferredSize()
	{
		Dimension d = super.getPreferredSize();

		if(shortcut != null)
		{
			d.width += (getFontMetrics(EnhancedMenuItem.acceleratorFont)
				.stringWidth(shortcut) + 15);
		}
		return d;
	} //}}}

	//{{{ paint() method
	public void paint(Graphics g)
	{
		super.paint(g);

		if(shortcut != null)
		{
			g.setFont(EnhancedMenuItem.acceleratorFont);
			g.setColor(getModel().isArmed() ?
				EnhancedMenuItem.acceleratorSelectionForeground :
				EnhancedMenuItem.acceleratorForeground);
			FontMetrics fm = g.getFontMetrics();
			Insets insets = getInsets();
			g.drawString(shortcut,getWidth() - (fm.stringWidth(
				shortcut) + insets.right + insets.left + 5),
				getFont().getSize() + (insets.top - 
				(OperatingSystem.isMacOSLF() ? 0 : 1))
				/* XXX magic number */);
		}
	} //}}}

	//{{{ Private members

	//{{{ Instance variables
	private String shortcut;
	private EditAction action;
	//}}}

	//{{{ getShortcut() method
	private String getShortcut()
	{
		if(action == null)
			return null;
		else
		{
			String shortcut1 = jEdit.getProperty(action.getName() + ".shortcut");
			String shortcut2 = jEdit.getProperty(action.getName() + ".shortcut2");

			if(shortcut1 == null || shortcut1.length() == 0)
			{
				if(shortcut2 == null || shortcut2.length() == 0)
					return null;
				else
					return shortcut2;
			}
			else
			{
				if(shortcut2 == null || shortcut2.length() == 0)
					return shortcut1;
				else
					return shortcut1 + " or " + shortcut2;
			}
		}
	} //}}}

	//{{{ Model class
	class Model extends DefaultButtonModel
	{
		public boolean isSelected()
		{
			if(!isShowing())
				return false;

			try
			{
				return action.isSelected(GUIUtilities.getView(
					EnhancedCheckBoxMenuItem.this));
			}
			catch(Throwable t)
			{
				Log.log(Log.ERROR,this,t);
				return false;
			}
		}

		public void setSelected(boolean b) {}
	} //}}}

	//{{{ MouseHandler class
	class MouseHandler extends MouseAdapter
	{
		boolean msgSet = false;

		public void mouseReleased(MouseEvent evt)
		{
			if(msgSet)
			{
				GUIUtilities.getView((Component)evt.getSource())
					.getStatus().setMessage(null);
				msgSet = false;
			}
		}

		public void mouseEntered(MouseEvent evt)
		{
			String msg = action.getMouseOverText();
			if(msg != null)
			{
				GUIUtilities.getView((Component)evt.getSource())
					.getStatus().setMessage(msg);
				msgSet = true;
			}
		}

		public void mouseExited(MouseEvent evt)
		{
			if(msgSet)
			{
				GUIUtilities.getView((Component)evt.getSource())
					.getStatus().setMessage(null);
				msgSet = false;
			}
		}
	} //}}}
}
