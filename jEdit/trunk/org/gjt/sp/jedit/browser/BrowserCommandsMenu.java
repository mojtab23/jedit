/*
 * BrowserCommandsMenu.java - provides various commands
 * :tabSize=4:indentSize=4:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 2000, 2003 Slava Pestov
 * Portions copyright (C) 1999 Jason Ginchereau
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA	02111-1307, USA.
 */

package org.gjt.sp.jedit.browser;

//{{{ Imports
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

import org.gjt.sp.jedit.io.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.menu.MenuItemTextComparator;
//}}}

/**
 * @version $Id$
 * @author Slava Pestov and Jason Ginchereau
 */
public class BrowserCommandsMenu extends JPopupMenu
{
	//{{{ BrowserCommandsMenu constructor
	public BrowserCommandsMenu(VFSBrowser browser, VFSFile[] files)
	{
		this.browser = browser;

		if(files != null)
		{
			VFS vfs = VFSManager.getVFSForPath(
				files[0].getDeletePath());
			int type = files[0].getType();

			boolean fileOpen = (jEdit.getBuffer(files[0].getPath()) != null);

			/* We check this flag separately so that we can
			delete open files from the favorites. */
			boolean deletePathOpen = (jEdit.getBuffer(files[0].getDeletePath()) != null);

			boolean delete = !deletePathOpen
				&& (vfs.getCapabilities()
				& VFS.DELETE_CAP) != 0;
			boolean rename = !fileOpen
				&& (vfs.getCapabilities()
				& VFS.RENAME_CAP) != 0;

			for(int i = 1; i < files.length; i++)
			{
				VFSFile file = files[i];

				VFS _vfs = VFSManager.getVFSForPath(file.getDeletePath());
				delete &= (vfs == _vfs) && (_vfs.getCapabilities()
					& VFS.DELETE_CAP) != 0;

				if(type == file.getType())
					/* all good */;
				else
				{
					// this will disable most operations if
					// files of multiple types are selected
					type = -1;
				}

				// set rename to false if > 1 file selected
				rename = false;

				// show 'close' item if at least one selected
				// file is currently open
				if(jEdit.getBuffer(file.getPath()) != null)
					fileOpen = true;
			}

			if(type == VFSFile.DIRECTORY
				|| type == VFSFile.FILESYSTEM)
			{
				if(files.length == 1)
					add(createMenuItem("browse"));
				if(browser.getMode() == VFSBrowser.BROWSER)
					add(createMenuItem("browse-window"));
			}
			else if(type == VFSFile.FILE
				&& (browser.getMode() == VFSBrowser.BROWSER
				|| browser.getMode() == VFSBrowser.BROWSER_DIALOG))
			{
				add(createMenuItem("open", "22x22/actions/document-open.png"));
				add(GUIUtilities.loadMenu(VFSBrowser.getActionContext(), "vfs.browser.open-in"));
				if (browser.getSelectedFiles().length == 1) 
				{
					add(createMenuItem("open-desktop", "22x22/actions/document-open.png"));
					add(createMenuItem("insert"));
				}
				if(fileOpen)
					add(createMenuItem("close"));
			}
			else if(type != -1)
				add(createMenuItem("open", "22x22/actions/document-open.png"));

			if(rename)
				add(createMenuItem("rename"));

			if(delete)
				add(createMenuItem("delete", "22x22/actions/edit-delete.png"));

			add(createMenuItem("copy-path", "22x22/actions/edit-copy.png"));
			add(createMenuItem("paste", "22x22/actions/edit-paste.png"));

			if((files.length == 1) || (browser.getSelectedFiles().length != 0))
		   		add(createMenuItem("properties", "22x22/actions/document-properties.png"));

			addSeparator();
		}

		add(createMenuItem("up", "22x22/actions/go-parent.png"));
		add(createMenuItem("previous", "22x22/actions/go-previous.png"));
		add(createMenuItem("next", "22x22/actions/go-next.png"));
		add(createMenuItem("reload", "22x22/actions/document-reload.png"));
		add(createMenuItem("roots"));
		add(createMenuItem("home", "22x22/actions/go-home.png"));
		add(createMenuItem("synchronize"));
		addSeparator();

		if(browser.getMode() == VFSBrowser.BROWSER)
			add(createMenuItem("new-file", "22x22/actions/document-new.png"));

		add(createMenuItem("new-directory", "22x22/actions/folder-new.png"));

		if(browser.getMode() == VFSBrowser.BROWSER)
		{
			addSeparator();
			add(createMenuItem("search-directory", "22x22/actions/system-search.png"));
		}

		addSeparator();

		add(createMenuItem("show-hidden-files"));

		if(browser.getMode() == VFSBrowser.BROWSER
			|| browser.getMode() == VFSBrowser.BROWSER_DIALOG)
		{
			addSeparator();
			add(createEncodingMenu());
		}
		JMenu customMenu = createCustomMenu();
		if (customMenu != null)
		{
			addSeparator();
			Component[] menuComponents = customMenu.getMenuComponents();
			for (Component menuComponent : menuComponents)
			{
				add((JMenuItem) menuComponent);
			}
		}

		addSeparator();
		add(createPluginMenu(browser));
		update();
	} //}}}

	//{{{ update() method
	public void update()
	{
		if(encodingMenuItems != null && browser.currentEncoding != null)
		{
			JRadioButtonMenuItem mi = encodingMenuItems.get(
				browser.currentEncoding);
			if(mi != null)
			{
				mi.setSelected(true);
				otherEncoding.setText(jEdit.getProperty(
					"vfs.browser.other-encoding.label"));
			}
			else
			{
				otherEncoding.setSelected(true);
				otherEncoding.setText(jEdit.getProperty(
					"vfs.browser.other-encoding-2.label",
					new String[] { browser.currentEncoding }));
			}
		}
	} //}}}

	//{{{ Private members
	private VFSBrowser browser;
	private HashMap<String, JRadioButtonMenuItem> encodingMenuItems;
	private JCheckBoxMenuItem autoDetect;
	private JRadioButtonMenuItem otherEncoding;

	//{{{ createMenuItem() methods
	private JMenuItem createMenuItem(String name, String iconName)
	{
		JMenuItem jMenuItem =
			GUIUtilities.loadMenuItem(VFSBrowser.getActionContext(), "vfs.browser." + name, false);
		jMenuItem.setIcon(GUIUtilities.loadIcon(iconName));
		return jMenuItem;
	}

	private JMenuItem createMenuItem(String name)
	{
		return createMenuItem(name, null);
	} //}}}

	//{{{ createEncodingMenu() method
	private JMenu createEncodingMenu()
	{
		ActionHandler actionHandler = new ActionHandler();

		encodingMenuItems = new HashMap<String, JRadioButtonMenuItem>();
		JMenu encodingMenu = new JMenu(jEdit.getProperty(
			"vfs.browser.commands.encoding.label"));

		JMenu menu = encodingMenu;

		autoDetect = new JCheckBoxMenuItem(
			jEdit.getProperty(
			"vfs.browser.commands.encoding.auto-detect"));
		autoDetect.setSelected(browser.autoDetectEncoding);
		autoDetect.setActionCommand("auto-detect");
		autoDetect.addActionListener(actionHandler);
		menu.add(autoDetect);
		menu.addSeparator();

		ButtonGroup grp = new ButtonGroup();

		List<JMenuItem> encodingMenuItemList = new ArrayList<JMenuItem>();
		String[] encodings = MiscUtilities.getEncodings(true);
		for (String encoding : encodings)
		{
			JRadioButtonMenuItem mi = new JRadioButtonMenuItem(encoding);
			mi.setActionCommand("encoding@" + encoding);
			mi.addActionListener(actionHandler);
			grp.add(mi);
			encodingMenuItems.put(encoding, mi);
			encodingMenuItemList.add(mi);
		}

		String systemEncoding = System.getProperty("file.encoding");
		if(encodingMenuItems.get(systemEncoding) == null)
		{
			JRadioButtonMenuItem mi = new JRadioButtonMenuItem(
				systemEncoding);
			mi.setActionCommand("encoding@" + systemEncoding);
			mi.addActionListener(actionHandler);
			grp.add(mi);
			encodingMenuItems.put(systemEncoding,mi);
			encodingMenuItemList.add(mi);
		}

		Collections.sort(encodingMenuItemList,
			new MenuItemTextComparator());

		Iterator iter = encodingMenuItemList.iterator();
		while(iter.hasNext())
		{
			JRadioButtonMenuItem mi = (JRadioButtonMenuItem)
				iter.next();

			if(menu.getMenuComponentCount() > 20)
			{
				JMenu newMenu = new JMenu(
					jEdit.getProperty("common.more"));
				menu.add(newMenu);
				menu = newMenu;
			}

			menu.add(mi);
		}
		menu.addSeparator();

		otherEncoding = new JRadioButtonMenuItem();
		otherEncoding.setActionCommand("other-encoding");
		otherEncoding.addActionListener(actionHandler);
		grp.add(otherEncoding);
		menu.add(otherEncoding);

		return encodingMenu;
	} //}}}

	//{{{ createCustomMenu() method
	private JMenu createCustomMenu()
	{
		if (jEdit.getProperty("browser.custom.context", "").length() != 0)
		{
			JMenu custom = GUIUtilities.loadMenu(VFSBrowser.getActionContext(),
				"browser.custom.context");
			return custom;
		}
		return null;
	} //}}}

	//{{{ createPluginsMenu() method
	private JMenu createPluginMenu(VFSBrowser browser)
	{
		JMenu pluginMenu = new JMenu(jEdit.getProperty(
			"vfs.browser.plugins.label"));
		GUIUtilities.setAutoMnemonic(pluginMenu);
		return (JMenu)browser.createPluginsMenu(pluginMenu,false);

	} //}}}


	//}}}

	//{{{ ActionHandler class
	class ActionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String actionCommand = evt.getActionCommand();

			if(actionCommand.equals("auto-detect"))
			{
				browser.autoDetectEncoding
					= autoDetect.isSelected();
			}
			else if(actionCommand.equals("other-encoding"))
			{
				String encoding = GUIUtilities.input(browser,
					"encoding-prompt",null,
					jEdit.getProperty("buffer.encoding",
					System.getProperty("file.encoding")));
				if(encoding == null)
					return;
				browser.currentEncoding = encoding;
			}
			else if(actionCommand.startsWith("encoding@"))
			{
				browser.currentEncoding = actionCommand.substring(9);
			}
		}
	} //}}}
}
