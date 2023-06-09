/*
 * jEdit - Programmer's Text Editor
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright © 2011-2012 Matthieu Casanova
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.kpouer.jedit.smartopen;

//{{{ 
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import common.gui.itemfinder.AbstractItemFinder;
import common.gui.itemfinder.PathCellRenderer;
import org.gjt.sp.jedit.jEdit;
//}}}

/**
 * @author Matthieu Casanova
 */
public class FileItemFinder extends AbstractItemFinder<String>
{
	private final MyListModel model;
	private final ListCellRenderer listCellRenderer;

	//{{{ FileItemFinder constructor
	public FileItemFinder()
	{
		model = new MyListModel();
		listCellRenderer = new PathCellRenderer();
	} //}}}

	//{{{ getLabel() method
	@Override
	public String getLabel()
	{
		return jEdit.getProperty("search-file.label");
	} //}}}

	//{{{ getModel() method
	@Override
	public ListModel getModel()
	{
		return model;
	} //}}}

	//{{{ updateList() method
	@Override
	public void updateList(String s)
	{
		List<String> files = SmartOpenPlugin.itemFinder.getFiles(s);
		model.setData(files);
	} //}}}

	//{{{ selectionMade() method
	@Override
	public void selectionMade(String item)
	{
		jEdit.openFile(jEdit.getActiveView().getEditPane(), item);
	} //}}}

	//{{{ getListCellRenderer() method
	@Override
	public ListCellRenderer getListCellRenderer()
	{
		return listCellRenderer;
	} //}}}

	//{{{ MyListModel class
	private static class MyListModel extends AbstractListModel
	{
		private List<String> data;

		//{{{ MyListModel constructor
		private MyListModel()
		{
			data = new ArrayList<String>();
		} //}}}

		//{{{ setData() method
		public void setData(List<String> data)
		{
			this.data = data;
			fireContentsChanged(this, 0, data.size());
		} //}}}

		//{{{ getSize() method
		@Override
		public int getSize()
		{
			return data.size();
		} //}}}

		//{{{ getElementAt() method
		@Override
		public Object getElementAt(int index)
		{
			return data.get(index);
		} //}}}
	} //}}}
}
