/*
 * ConsoleFramePluginPart.java - Manages console frame
 * Copyright (C) 2000 Slava Pestov
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

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.msg.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.Vector;

public class ConsoleFramePluginPart extends EBPlugin
{
	public void start()
	{
		jEdit.addAction(new OpenAction());
	}

	public void createMenuItems(View view, Vector menus, Vector menuItems)
	{
		menuItems.addElement(GUIUtilities.loadMenuItem(view,"console"));
	}

	public void handleMessage(EBMessage msg)
	{
		if(msg instanceof ViewUpdate)
		{
			ViewUpdate viewUpdate = (ViewUpdate)msg;
			View view = viewUpdate.getView();
			if(viewUpdate.getWhat() == ViewUpdate.CREATED)
			{
				if("on".equals(jEdit.getProperty("console.toolbar.enabled")))
				{
					view.addToolBar(new ConsoleToolBar(view));
				}
			}
			else if(viewUpdate.getWhat() == ViewUpdate.CLOSED)
			{
				consoles.remove(view);
			}
		}
	}

	// package-private members
	static boolean isConsoleShowing(View view)
	{
		if(consoles == null)
			return false;

		ConsoleFrame console = (ConsoleFrame)consoles.get(view);
		if(console == null)
			return false;

		return console.isShowing();
	}

	static ConsoleFrame getConsole(View view)
	{
		if(consoles == null)
			consoles = new Hashtable();

		ConsoleFrame console = (ConsoleFrame)consoles.get(view);

		if(console == null)
		{
			console = new ConsoleFrame(view);
			consoles.put(view,console);
		}

		console.setVisible(true);
		console.toFront();
		console.requestFocus();

		return console;
	}

	// private members
	private static Hashtable consoles;

	class OpenAction extends EditAction
	{
		OpenAction()
		{
			super("console");
		}

		public void actionPerformed(ActionEvent evt)
		{
			// If action command is null, just toggle visibility,
			// otherwise if an action command is set (eg if run from
			// a macro) we run that command
			View view = getView(evt);
			String actionCommand = evt.getActionCommand();
			boolean showing = isConsoleShowing(view);
			ConsoleFrame console = getConsole(view);
			if(showing && evt.getActionCommand() == null)
				console.close();
			else if(evt.getActionCommand() != null)
				console.run(null,evt.getActionCommand());
		}

		public boolean isToggle()
		{
			return true;
		}

		public boolean isSelected(Component comp)
		{
			return isConsoleShowing(getView(comp));
		}
	}
}
