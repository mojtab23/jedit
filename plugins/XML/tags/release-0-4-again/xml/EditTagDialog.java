/*
 * EditTagDialog.java
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

import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.jedit.*;

class EditTagDialog extends EnhancedDialog
{
	EditTagDialog(View view, ElementDecl element, Hashtable attributeValues,
		boolean elementEmpty, Hashtable entityHash, Vector ids)
	{
		super(view,jEdit.getProperty("xml-edit-tag.title"),true);

		this.element = element;
		this.entityHash = entityHash;

		JPanel content = new JPanel(new BorderLayout());
		content.setBorder(new EmptyBorder(12,12,12,12));
		setContentPane(content);

		/** Top panel with element name, empty toggle */
		JPanel top = new JPanel(new BorderLayout(6,0));
		top.setBorder(new EmptyBorder(0,0,12,0));

		top.add(BorderLayout.WEST,new JLabel(
			jEdit.getProperty("xml-edit-tag.element-name")));

		top.add(BorderLayout.CENTER,new JLabel(element.name));

		empty = new JCheckBox(jEdit.getProperty("xml-edit-tag.empty"));
		if(element.empty)
		{
			empty.setSelected(true);
			empty.setEnabled(false);
		}
		else
			empty.setSelected(elementEmpty);
		empty.addActionListener(new ActionHandler());

		top.add(BorderLayout.EAST,empty);
		content.add(BorderLayout.NORTH,top);

		/** Attribute table */
		JPanel center = new JPanel(new BorderLayout());
		attributeModel = createAttributeModel(element.attributes,
			attributeValues,ids);
		attributes = new AttributeTable();
		attributes.setRowHeight(new JComboBox(new String[] { "template" })
			.getPreferredSize().height);

		attributes.getTableHeader().setReorderingAllowed(false);
		attributes.getColumnModel().getColumn(0).setPreferredWidth(30);
		attributes.setColumnSelectionAllowed(false);
		attributes.setRowSelectionAllowed(false);
		attributes.setCellSelectionEnabled(false);

		JScrollPane scroller = new JScrollPane(attributes);
		Dimension size = scroller.getPreferredSize();
		size.height = Math.min(size.width,200);
		scroller.setPreferredSize(size);
		center.add(BorderLayout.CENTER,scroller);

		/** Preview field */
		JPanel previewPanel = new JPanel(new BorderLayout());
		previewPanel.setBorder(new EmptyBorder(12,0,0,0));
		previewPanel.add(BorderLayout.NORTH,new JLabel(jEdit.getProperty(
			"xml-edit-tag.preview")));

		preview = new JTextArea(5,5);
		preview.setLineWrap(true);
		preview.setWrapStyleWord(true);
		previewPanel.add(BorderLayout.CENTER,new JScrollPane(preview));

		center.add(BorderLayout.SOUTH,previewPanel);

		content.add(BorderLayout.CENTER,center);

		/** Buttons */
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
		buttons.setBorder(new EmptyBorder(12,0,0,0));

		buttons.add(Box.createGlue());
		buttons.add(ok = new JButton(jEdit.getProperty("common.ok")));
		ok.addActionListener(new ActionHandler());
		getRootPane().setDefaultButton(ok);

		buttons.add(Box.createHorizontalStrut(6));

		buttons.add(cancel = new JButton(jEdit.getProperty("common.cancel")));
		cancel.addActionListener(new ActionHandler());
		buttons.add(cancel);

		buttons.add(Box.createGlue());

		content.add(BorderLayout.SOUTH,buttons);

		updateTag();

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(view);
		show();
	}

	public void ok()
	{
		int row = attributes.getSelectedRow();
		int column = attributes.getSelectedColumn();

		if(row != -1 && column != -1)
		{
			if(attributes.getCellEditor(row,column) != null)
				attributes.getCellEditor(row,column).stopCellEditing();
		}

		isOK = true;
		dispose();
	}

	public void cancel()
	{
		isOK = false;
		dispose();
	}

	public String getNewTag()
	{
		return (isOK ? newTag : null);
	}

	// private members
	private ElementDecl element;
	private Hashtable entityHash;
	private JCheckBox empty;
	private Vector attributeModel;
	private JTable attributes;
	private JTextArea preview;
	private JButton ok;
	private JButton cancel;
	private String newTag;
	private boolean isOK;

	private Vector createAttributeModel(Vector declaredAttributes,
		Hashtable attributeValues, Vector ids)
	{
		Vector attributeModel = new Vector();
		for(int i = 0; i < declaredAttributes.size(); i++)
		{
			ElementDecl.AttributeDecl attr =
				(ElementDecl.AttributeDecl)
				declaredAttributes.elementAt(i);

			boolean set;
			String value = (String)attributeValues.get(attr.name);
			if(value == null)
			{
				set = false;
				value = attr.value;
			}
			else
				set = true;

			if(attr.required)
				set = true;

			Vector values;
			if(attr.type == ElementDecl.AttributeDecl.IDREF)
			{
				values = ids;
				if(value == null && values.size() > 0)
					value = (String)values.elementAt(0);
			}
			else
			{
				values = attr.values;
				if(value == null && values != null
					&& values.size() > 0)
					value = (String)values.elementAt(0);
			}

			attributeModel.addElement(new Attribute(set,attr.name,
				value,values,attr.type,attr.required));
		}

		MiscUtilities.quicksort(attributeModel,new AttributeCompare());

		return attributeModel;
	}

	private void updateTag()
	{
		StringBuffer buf = new StringBuffer("<");
		buf.append(element.name);

		for(int i = 0; i < attributeModel.size(); i++)
		{
			Attribute attr = (Attribute)attributeModel.elementAt(i);
			if(!attr.set)
				continue;

			buf.append(' ');
			buf.append(attr.name);
			buf.append("=\"");
			if(attr.value.value != null)
			{
				buf.append(XmlActions.charactersToEntities(
					attr.value.value,entityHash,true));
			}
			buf.append("\"");
		}

		if(empty.isSelected() && !element.html)
			buf.append(" /");

		buf.append(">");

		newTag = buf.toString();

		preview.setText(newTag);
	}

	class ActionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(evt.getSource() == empty)
				updateTag();
			else if(evt.getSource() == ok)
				ok();
			else if(evt.getSource() == cancel)
				cancel();
		}
	}

	static class Attribute
	{
		boolean set;

		String name;
		Value value;
		int type;
		boolean required;

		Attribute(boolean set, String name,
			String value, Vector values,
			int type, boolean required)
		{
			this.set = set;
			this.name = name;
			this.value = new Value(value,values);
			this.type = type;
			this.required = required;
		}

		static class Value
		{
			String value;
			Vector values;

			Value(String value, Vector values)
			{
				this.value = value;
				this.values = values;
			}

			public String toString()
			{
				return value;
			}
		}
	}

	static class AttributeCompare implements MiscUtilities.Compare
	{
		public int compare(Object obj1, Object obj2)
		{
			Attribute attr1 = (Attribute)obj1;
			Attribute attr2 = (Attribute)obj2;

			// put required attributes at the top
			if(attr1.required && !attr2.required)
				return -1;
			else if(!attr1.required && attr2.required)
				return 1;
			else
			{
				return attr1.name.toLowerCase()
					.compareTo(attr2.name.toLowerCase());
			}
		}
	}

	static ComboValueRenderer comboRenderer = new ComboValueRenderer();

	class AttributeTable extends JTable
	{
		AttributeTable()
		{
			super(new AttributeTableModel());
		}

		public TableCellEditor getCellEditor(int row, int column)
		{
			Object value = getModel().getValueAt(row,column);
			if(value instanceof Attribute.Value)
			{
				Attribute.Value attr = (Attribute.Value)value;
				if(attr.values != null)
					return comboRenderer;
			}

			return super.getCellEditor(row,column);
		}

		public TableCellRenderer getCellRenderer(int row, int column)
		{
			Object value = getModel().getValueAt(row,column);
			if(value instanceof Attribute.Value)
			{
				Attribute.Value attr = (Attribute.Value)value;
				if(attr.values != null)
					return comboRenderer;
			}

			return super.getCellRenderer(row,column);
		}
	}

	class AttributeTableModel extends AbstractTableModel
	{
		public int getColumnCount()
		{
			return 4;
		}

		public int getRowCount()
		{
			return attributeModel.size();
		}

		public Class getColumnClass(int col)
		{
			if(col == 0)
				return Boolean.class;
			else if(col == 3)
				return Attribute.Value.class;
			else
				return String.class;
		}

		public String getColumnName(int col)
		{
			switch(col)
			{
			case 0:
				return jEdit.getProperty("xml-edit-tag.set");
			case 1:
				return jEdit.getProperty("xml-edit-tag.attribute");
			case 2:
				return jEdit.getProperty("xml-edit-tag.type");
			case 3:
				return jEdit.getProperty("xml-edit-tag.value");
			default:
				throw new InternalError();
			}
		}

		public boolean isCellEditable(int row, int col)
		{
			if(col != 1 && col != 2)
				return true;
			else
				return false;
		}

		public Object getValueAt(int row, int col)
		{
			Attribute attr = (Attribute)attributeModel.elementAt(row);
			switch(col)
			{
			case 0:
				return new Boolean(attr.set);
			case 1:
				return attr.name;
			case 2:
				StringBuffer buf = new StringBuffer();
				if(attr.type == ElementDecl.AttributeDecl.CDATA)
					buf.append(jEdit.getProperty("xml-edit-tag.cdata"));
				else if(attr.type == ElementDecl.AttributeDecl.CHOICE)
					buf.append(jEdit.getProperty("xml-edit-tag.choice"));
				else if(attr.type == ElementDecl.AttributeDecl.IDREF)
					buf.append(jEdit.getProperty("xml-edit-tag.idref"));

				if(attr.required)
					buf.append(jEdit.getProperty("xml-edit-tag.required"));

				return buf.toString();
			case 3:
				return attr.value;
			default:
				throw new InternalError();
			}
		}

		public void setValueAt(Object value, int row, int col)
		{
			Attribute attr = (Attribute)attributeModel.elementAt(row);
			switch(col)
			{
			case 0:
				if(attr.required)
					return;

				attr.set = ((Boolean)value).booleanValue();
				break;
			case 3:
				String sValue = value.toString();
				if(equal(attr.value.value,sValue))
					return;

				attr.set = true;
				attr.value.value = sValue;
				break;
			}

			fireTableRowsUpdated(row,row);

			updateTag();
		}

		private boolean equal(Object obj1, Object obj2)
		{
			if(obj1 == null)
			{
				if(obj2 == null)
					return true;
				else
					return false;
			}
			else
			{
				if(obj2 == null)
					return false;
				else
					return obj1.equals(obj2);
			}
		}
	}

	static class ComboValueRenderer extends DefaultCellEditor
		implements TableCellRenderer
	{
		JComboBox editorCombo;
		JComboBox renderCombo;

		ComboValueRenderer()
		{
			this(new JComboBox());
		}

		// this is stupid. why can't you reference instance vars
		// in a super() invocation?
		ComboValueRenderer(JComboBox comboBox)
		{
			super(comboBox);
			this.editorCombo = comboBox;
			this.renderCombo = new JComboBox();
		}

		public Component getTableCellEditorComponent(JTable table,
			Object value, boolean isSelected, int row, int column)
		{
			Attribute.Value _value = (Attribute.Value)value;
			editorCombo.setModel(new DefaultComboBoxModel(
				_value.values));
			//editorCombo.setSelectedItem(_value.value);
			return super.getTableCellEditorComponent(table,
				_value.value,isSelected,row,column);
		}

		public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus,
			int row, int column)
		{
			Attribute.Value _value = (Attribute.Value)value;
			renderCombo.setModel(new DefaultComboBoxModel(
				_value.values));
			renderCombo.setSelectedItem(_value.value);
			return renderCombo;
		}
	}
}
