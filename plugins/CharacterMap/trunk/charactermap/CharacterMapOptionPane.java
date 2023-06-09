/*
 *  CharacterMapOptionPane.java
 * :folding=explicit:collapseFolds=1:
 *
 *  Copyright (C) 2003 Mark Wickens
 *  Copyright (C) 2012 Max Funk
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package charactermap;

//{{{ Imports
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import org.gjt.sp.jedit.AbstractOptionPane;
import org.gjt.sp.jedit.EditAction;
import org.gjt.sp.jedit.PluginJAR;
import org.gjt.sp.jedit.gui.ColorWellButton;
import org.gjt.sp.jedit.gui.DockableWindowManager;
import org.gjt.sp.jedit.jEdit;
//import org.gjt.sp.util.Log;
//}}}

/**
 * Options pane displayed when Character Map
 * is selected in the Plugin Options... tree
 * Allows the user to customize the appearence of the
 * character map plugin.
 */
public class CharacterMapOptionPane extends AbstractOptionPane
{
//{{{ Private Variables
	/** Shortcut to CharacterMapPlugin.NAME_PREFIX */
	private final String NAME_PREFIX = CharacterMapPlugin.NAME_PREFIX;
	/** Shortcut to CharacterMapPlugin.OPTION_PREFIX */
	private final String OPTION_PREFIX = CharacterMapPlugin.OPTION_PREFIX;

	/** Checkbox controlling display of encoding combo box */
	private JCheckBox encoding;
	/** Checkbox controlling display of unicode blocks slider */
	private JCheckBox blocks;
	/** Checkbox to controlling alphabetic order of unicode blocks */
	private JCheckBox blocksABC;
	/** Checkbox to enable higher Unicode planes (Characters above 0xFFFF) */
	private JCheckBox higherPlanes;
	/** Checkbox controlling anti-aliasing */
	private JCheckBox antiAlias;
	/** Checkbox controlling display of status line */
	private JCheckBox status;

	/** Spinner controlling number of columns displayed in floating table */
	private JSpinner columnsSpinner;
	/** Spinner controlling number of columns displayed in left/right docked table */
	private JSpinner columnsSpinnerDockLR;
	/** Spinner controlling number of columns displayed in top/bottom docked table */
	private JSpinner columnsSpinnerDockTB;
	/** Label for spinner options (floating) */
	private JLabel columnsSpinnerLabel;
	/** Label for spinner options (docked left/right) */
	private JLabel columnsSpinnerDockLRLabel;
	/** Label for spinner options (docked top/bottom) */
	private JLabel columnsSpinnerDockTBLabel;
	/** List for spinner with possible numbers of columns */
	private ArrayList<Integer> spinnerValues;
	/** Model for spinner options (floating) */
	private SpinnerModel spinnerModel;
	/** Model for spinner options (docked left/right) */
	private SpinnerModel spinnerModelDockLR;
	/** Model for spinner options (docked top/bottom) */
	private SpinnerModel spinnerModelDockTB;

	/** Selector for normal character color */
	private ColorWellButton normalColor;
	/** Selector for control character color */
	private ColorWellButton controlColor;
	/** Selector for private use character color */
	private ColorWellButton privateColor;
	/** Selector for unassigned character color */
	private ColorWellButton unassignedColor;
	/** Selector for invalid character color */
	private ColorWellButton invalidColor;

	/** Checkbox controlling display of large character  */
	private JCheckBox showLarge;
	/** Textfield containing size of large character in points */
	private JTextField largeSize;
	/** Label for textfield containing large character size */
	private JLabel largeSizeLabel;
	/** Checkbox controlling display of super-size character */
	private JCheckBox showSuper;
	/** Textfield containing size of super character in points */
	private JTextField superSize;
	/** Label for textfield containing super character size */
	private JLabel superSizeLabel;
//}}}

//{{{ Constructor
		/**
	 * Default constructor.
	 */
	public CharacterMapOptionPane()
	{
		super(CharacterMapPlugin.NAME);
	}
//}}}

//{{{ _init() method
	/**
	 * Create and initialise the options page with options
	 * and labels read from the properties for this plugin
	 */
	@Override
	public void _init()
	{
		//Initialise general option components
		encoding = new JCheckBox(jEdit.getProperty(OPTION_PREFIX + "encoding.label"),
			jEdit.getBooleanProperty(OPTION_PREFIX + "encoding"));
		blocks = new JCheckBox(jEdit.getProperty(OPTION_PREFIX + "blocks.label"),
			jEdit.getBooleanProperty(OPTION_PREFIX + "blocks"));
		blocksABC = new JCheckBox(jEdit.getProperty(OPTION_PREFIX + "blocks-abc.label"),
			jEdit.getBooleanProperty(OPTION_PREFIX + "blocks-abc"));
		higherPlanes = new JCheckBox(jEdit.getProperty(OPTION_PREFIX + "higher-planes.label"),
			jEdit.getBooleanProperty(OPTION_PREFIX + "higher-planes"));
		antiAlias = new JCheckBox(jEdit.getProperty(OPTION_PREFIX + "anti-alias.label"),
			jEdit.getBooleanProperty(OPTION_PREFIX + "anti-alias"));
		status = new JCheckBox(jEdit.getProperty(OPTION_PREFIX + "status.label"),
			jEdit.getBooleanProperty(OPTION_PREFIX + "status"));

		//Initialise table option components
		spinnerValues = new ArrayList<Integer>();
		for (int i = 8; i >= 0; i -= 1) {
			spinnerValues.add(Integer.valueOf(1 << i));
		}

		columnsSpinnerLabel = new JLabel(jEdit.getProperty(OPTION_PREFIX + "columns.label"));
		spinnerModel = new SpinnerListModel(spinnerValues);
		columnsSpinner = new JSpinner(spinnerModel);
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) columnsSpinner.getEditor();
		JTextField columnsTextField = spinnerEditor.getTextField();
		columnsTextField.setColumns(3);
		columnsTextField.setHorizontalAlignment(JTextField.LEFT);
		int column = jEdit.getIntegerProperty(OPTION_PREFIX + "columns");
		columnsSpinner.setValue(Integer.valueOf(column));

		columnsSpinnerDockLRLabel = new JLabel(jEdit.getProperty(OPTION_PREFIX + "columns-dock-lr.label"));
		spinnerModelDockLR = new SpinnerListModel(spinnerValues);
		columnsSpinnerDockLR = new JSpinner(spinnerModelDockLR);
		spinnerEditor = (JSpinner.DefaultEditor) columnsSpinnerDockLR.getEditor();
		columnsTextField = spinnerEditor.getTextField();
		columnsTextField.setColumns(3);
		columnsTextField.setHorizontalAlignment(JTextField.LEFT);
		column = jEdit.getIntegerProperty(OPTION_PREFIX + "columns-dock-lr");
		columnsSpinnerDockLR.setValue(Integer.valueOf(column));

		columnsSpinnerDockTBLabel = new JLabel(jEdit.getProperty(OPTION_PREFIX + "columns-dock-tb.label"));
		spinnerModelDockTB = new SpinnerListModel(spinnerValues);
		columnsSpinnerDockTB = new JSpinner(spinnerModelDockTB);
		spinnerEditor = (JSpinner.DefaultEditor) columnsSpinnerDockTB.getEditor();
		columnsTextField = spinnerEditor.getTextField();
		columnsTextField.setColumns(3);
		columnsTextField.setHorizontalAlignment(JTextField.LEFT);
		column = jEdit.getIntegerProperty(OPTION_PREFIX + "columns-dock-tb");
		columnsSpinnerDockTB.setValue(Integer.valueOf(column));

		//Initialise color option components
		normalColor = new ColorWellButton(
			jEdit.getColorProperty(OPTION_PREFIX + "color-normal"));
		controlColor = new ColorWellButton(
			jEdit.getColorProperty(OPTION_PREFIX + "color-control"));
		privateColor = new ColorWellButton(
			jEdit.getColorProperty(OPTION_PREFIX + "color-private"));
		unassignedColor = new ColorWellButton(
			jEdit.getColorProperty(OPTION_PREFIX + "color-unassigned"));
		invalidColor = new ColorWellButton(
			jEdit.getColorProperty(OPTION_PREFIX + "color-invalid"));

		//Initialise character option components
		showLarge = new JCheckBox(jEdit.getProperty(OPTION_PREFIX + "large.label"),
			jEdit.getBooleanProperty(OPTION_PREFIX + "large"));
		largeSizeLabel = new JLabel(jEdit.getProperty(OPTION_PREFIX + "large-size.label"));
		largeSize = new JTextField(jEdit.getProperty(OPTION_PREFIX + "large-size"));
		largeSize.setEnabled(showLarge.isSelected());
		largeSizeLabel.setEnabled(showLarge.isSelected());

		showSuper = new JCheckBox(jEdit.getProperty(OPTION_PREFIX + "super.label"),
			jEdit.getBooleanProperty(OPTION_PREFIX + "super"));
		superSizeLabel = new JLabel(jEdit.getProperty(OPTION_PREFIX + "super-size.label"));
		superSizeLabel.setEnabled(showSuper.isSelected());
		superSize = new JTextField(jEdit.getProperty(OPTION_PREFIX + "super-size"));
		superSize.setEnabled(showSuper.isSelected());

		showLarge.addActionListener(
			new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent evt)
				{
					boolean selected = showLarge.isSelected();
					largeSizeLabel.setEnabled(selected);
					largeSize.setEnabled(selected);
				}
			});
		showSuper.addActionListener(
			new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent evt)
				{
					boolean selected = showSuper.isSelected();
					superSizeLabel.setEnabled(selected);
					superSize.setEnabled(selected);
				}
			});

		//Display all components

		addSeparator(OPTION_PREFIX + "separator-general.label");
		addComponent(encoding);
		addComponent(blocks);
		addComponent(blocksABC);
		addComponent(higherPlanes);
		addComponent(antiAlias);
		addComponent(status);

		addSeparator(OPTION_PREFIX + "separator-table.label");
		addComponent(columnsSpinnerLabel, columnsSpinner);
		addComponent(columnsSpinnerDockLRLabel, columnsSpinnerDockLR);
		addComponent(columnsSpinnerDockTBLabel, columnsSpinnerDockTB);

		addSeparator(OPTION_PREFIX + "separator-color.label");
		addComponent(jEdit.getProperty(OPTION_PREFIX + "color-normal.label"),
			normalColor, GridBagConstraints.VERTICAL);
		addComponent(jEdit.getProperty(OPTION_PREFIX + "color-control.label"),
			controlColor, GridBagConstraints.VERTICAL);
		addComponent(jEdit.getProperty(OPTION_PREFIX + "color-private.label"),
			privateColor, GridBagConstraints.VERTICAL);
		addComponent(jEdit.getProperty(OPTION_PREFIX + "color-unassigned.label"),
			unassignedColor, GridBagConstraints.VERTICAL);
		addComponent(jEdit.getProperty(OPTION_PREFIX + "color-invalid.label"),
			invalidColor, GridBagConstraints.VERTICAL);

		addSeparator(OPTION_PREFIX + "separator-chars.label");
		addComponent(showLarge);
		addComponent(largeSizeLabel, largeSize);
		addComponent(showSuper);
		addComponent(superSizeLabel, superSize);
	}
//}}}

//{{{ _save() method
	/**
	 * Store the options selected on the pane back to the
	 * jedit properties.
	 */
	@Override
	public void _save()
	{
		// Save options
		jEdit.setBooleanProperty(OPTION_PREFIX + "encoding", encoding.isSelected());
		jEdit.setBooleanProperty(OPTION_PREFIX + "blocks", blocks.isSelected());
		jEdit.setBooleanProperty(OPTION_PREFIX + "blocks-abc", blocksABC.isSelected());
		jEdit.setBooleanProperty(OPTION_PREFIX + "higher-planes", higherPlanes.isSelected());
		jEdit.setBooleanProperty(OPTION_PREFIX + "anti-alias", antiAlias.isSelected());
		jEdit.setBooleanProperty(OPTION_PREFIX + "status", status.isSelected());

		int column = ((Integer) columnsSpinner.getValue()).intValue();
		jEdit.setIntegerProperty(OPTION_PREFIX + "columns", column);
		column = ((Integer) columnsSpinnerDockLR.getValue()).intValue();
		jEdit.setIntegerProperty(OPTION_PREFIX + "columns-dock-lr", column);
		column = ((Integer) columnsSpinnerDockTB.getValue()).intValue();
		jEdit.setIntegerProperty(OPTION_PREFIX + "columns-dock-tb", column);

		jEdit.setColorProperty(OPTION_PREFIX + "color-normal",
			normalColor.getSelectedColor());
		jEdit.setColorProperty(OPTION_PREFIX + "color-control",
			controlColor.getSelectedColor());
		jEdit.setColorProperty(OPTION_PREFIX + "color-private",
			privateColor.getSelectedColor());
		jEdit.setColorProperty(OPTION_PREFIX + "color-unassigned",
			unassignedColor.getSelectedColor());
		jEdit.setColorProperty(OPTION_PREFIX + "color-invalid",
			invalidColor.getSelectedColor());

		jEdit.setBooleanProperty(OPTION_PREFIX + "large", showLarge.isSelected());
		jEdit.setBooleanProperty(OPTION_PREFIX + "super", showSuper.isSelected());
		setIntegerPropertyFromTextField(OPTION_PREFIX + "large-size", largeSize, 36);
		setIntegerPropertyFromTextField(OPTION_PREFIX + "super-size", superSize, 128);

		// Reload CharacterMap.jar
		PluginJAR jar = jEdit.getPlugin("charactermap.CharacterMapPlugin").getPluginJAR();
		jEdit.removePluginJAR(jar,false);
		jEdit.addPluginJAR(jar.getPath());
		boolean isFloating = jEdit.getProperty(NAME_PREFIX + "dock-position",
			DockableWindowManager.FLOATING).equalsIgnoreCase(DockableWindowManager.FLOATING);
		if (!isFloating)
		{
			EditAction act = jEdit.getAction(CharacterMapPlugin.NAME);
			act.invoke(jEdit.getActiveView());
		}
	}
//}}}

//{{{ Auxiliary function
	/**
	 * Determine the value of the given text field and store in the
	 * named jedit property. If the valued does not parse as an integer,
	 * use the specified default instead.
	 * @param property The name of the jedit property to receive the value
	 * @param tf Text field containing the string to be parsed as an integer
	 * @param defaultValue If tf does not contain an integer, use this value instead
	 */
	private void setIntegerPropertyFromTextField(String property, JTextField tf, int defaultValue)
	{
		int value = defaultValue;
		String sValue = tf.getText();
		try {
			value = Integer.parseInt(sValue);
		}
		catch (NumberFormatException nfe) {
			value = jEdit.getIntegerProperty(property, defaultValue);
		}
		finally {
			jEdit.setIntegerProperty(property, value);
		}
	}
//}}}
}

