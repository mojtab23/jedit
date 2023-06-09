/*
 * XmlPlugin.java
 * Copyright (C) 2000, 2001 Slava Pestov
 *
 * The XML plugin is licensed under the GNU General Public License, with
 * the following exception:
 *
 * "Permission is granted to link this code with software released under
 * the Apache license version 1.1, for example used by the Xerces XML
 * parser package."
 */

package xml;

import javax.swing.*;
import java.util.Vector;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.jedit.msg.*;
import org.gjt.sp.jedit.textarea.*;
import org.gjt.sp.jedit.*;

public class XmlPlugin extends EBPlugin
{
	public static final String TREE_NAME = "xml-tree";
	public static final String INSERT_NAME = "xml-insert";

	public static final String COMPLETION_INFO_PROPERTY = "xml.completion-info";

	public void start()
	{
		EditBus.addToNamedList(DockableWindow.DOCKABLE_WINDOW_LIST,TREE_NAME);
		EditBus.addToNamedList(DockableWindow.DOCKABLE_WINDOW_LIST,INSERT_NAME);

		CatalogManager.propertiesChanged();
		XmlActions.propertiesChanged();
	}

	public void createMenuItems(Vector menuItems)
	{
		menuItems.addElement(GUIUtilities.loadMenu("xml-menu"));
	}

	public void createOptionPanes(OptionsDialog dialog)
	{
		OptionGroup grp = new OptionGroup("xml");
		grp.addOptionPane(new GeneralOptionPane());
		grp.addOptionPane(new CompletionOptionPane());
		grp.addOptionPane(new TagHighlightOptionPane());
		grp.addOptionPane(new CatalogsOptionPane());
		dialog.addOptionGroup(grp);
	}

	public void handleMessage(EBMessage msg)
	{
		if(msg instanceof CreateDockableWindow)
		{
			CreateDockableWindow cmsg = (CreateDockableWindow)msg;
			if(cmsg.getDockableWindowName().equals(TREE_NAME))
			{
				cmsg.setDockableWindow(new XmlTree(cmsg.getView(),
					!cmsg.getPosition().equals(
					DockableWindowManager.FLOATING)));
			}
			else if(cmsg.getDockableWindowName().equals(INSERT_NAME))
			{
				Object position = cmsg.getPosition();

				boolean sideBySide;

				if(position.equals(DockableWindowManager.TOP)
					|| position.equals(DockableWindowManager.BOTTOM))
					sideBySide = true;
				else
					sideBySide = false;

				cmsg.setDockableWindow(new XmlInsert(
					cmsg.getView(),sideBySide));
			}
		}
		else if(msg instanceof EditPaneUpdate)
		{
			EditPaneUpdate epu = (EditPaneUpdate)msg;
			EditPane editPane = epu.getEditPane();

			if(epu.getWhat() == EditPaneUpdate.CREATED)
			{
				JEditTextArea textArea = editPane.getTextArea();
				TextAreaPainter textAreaPainter = textArea.getPainter();

				TagHighlight tagHighlight =
					(TagHighlight)TagHighlight.addHighlightTo(editPane);

				textAreaPainter.addCustomHighlight(tagHighlight);
			}
			else if(epu.getWhat() == EditPaneUpdate.DESTROYED)
				TagHighlight.removeHighlightFrom(editPane);
			else if(epu.getWhat() == EditPaneUpdate.BUFFER_CHANGED)
				TagHighlight.bufferChanged(editPane);
		}
		else if(msg instanceof BufferUpdate)
		{
			BufferUpdate bu = (BufferUpdate)msg;
			Buffer buffer = bu.getBuffer();

			if(bu.getWhat() == BufferUpdate.CREATED)
				TagHighlight.bufferCreated(buffer);
			else if(bu.getWhat() == BufferUpdate.LOADED)
				TagHighlight.bufferLoaded(buffer);
			else if(bu.getWhat() == BufferUpdate.CLOSED)
				TagHighlight.bufferClosed(buffer);
		}
		else if(msg instanceof PropertiesChanged)
		{
			XmlActions.propertiesChanged();
			CatalogManager.propertiesChanged();
			TagHighlight.propertiesChanged();
		}
	}
}
