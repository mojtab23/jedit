/*
 * ItemFinder.java
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 2010 Matthieu Casanova
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
package gatchan.jedit.rfcreader;

import org.gjt.sp.jedit.jEdit;

import javax.swing.*;
import java.util.List;

/**
 * @author Matthieu Casanova
 */
public class RFCItemFinder implements ItemFinder<RFC>
{
	private final RFCReaderPlugin plugin;
	private final RFCListModel model;

	public RFCItemFinder()
	{
		plugin = (RFCReaderPlugin) jEdit.getPlugin(RFCReaderPlugin.class.getName());
		model = new RFCListModel(plugin.rfcList);
	}

	public ListModel getModel()
	{
		return model;
	}

	public ListCellRenderer getListCellRenderer()
	{
		return null;
	}

	public void updateList(String s)
	{
		if (s == null || s.length() == 0)
			model.reset();
		else
		{
			List<RFC> rfcList = plugin.getIndex().search(s+ '*');
			model.setData(rfcList);
		}
	}

	public void selectionMade(RFC item)
	{
		RFCReaderPlugin.openRFC(jEdit.getActiveView(), item.getNumber());
	}

	public String getLabel()
	{
		return jEdit.getProperty("rfcreader.searchLabel.label");
	}

	public int getWidth()
	{
		return 400;
	}
}
