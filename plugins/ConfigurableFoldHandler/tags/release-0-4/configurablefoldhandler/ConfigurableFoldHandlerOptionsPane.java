package configurablefoldhandler;

/*
 * ConfigurableFoldHandlerOptionsPane.java
 *
 * Copyright (c) 2002 C.J.Kent
 *
 * :tabSize=4:indentSize=4:noTabs=false:
 * :folding=custom:collapseFolds=0:
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

import org.gjt.sp.jedit.AbstractOptionPane;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.Mode;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Hashtable;

/**
 * @author    C.J.Kent
 * @created   15 May 2002
 */
public class ConfigurableFoldHandlerOptionsPane extends AbstractOptionPane
{
	private JTextField defStartField;
	private JTextField defEndField;
	private JTextField modeStartField;
	private JTextField modeEndField;
	private JComboBox modeBox;
	private JCheckBox defUseRegex;
	private JCheckBox modeUseDefault;
	private JCheckBox modeUseRegex;
	private ConfigurableFoldHandlerPlugin plugin;
	private String[] modeNames;
	private Hashtable modeStrings = new Hashtable();
	private String lastModeName;
	private boolean initialising;
	
	public ConfigurableFoldHandlerOptionsPane(
		ConfigurableFoldHandlerPlugin plugin)
	{
		super("configurablefoldhandler");
		this.plugin = plugin;
		
		defUseRegex = new JCheckBox(jEdit.getProperty(
			"options.configurablefoldhandler.use-regex"),
			jEdit.getBooleanProperty("configurablefoldhandler.use-regex",
			false));
		
		defStartField = new JTextField(jEdit.getProperty(
			"configurablefoldhandler.startfold",
			ConfigurableFoldHandler.DEFAULT_FOLD_STRINGS.getStartString()));
		
		defEndField = new JTextField(jEdit.getProperty(
			"configurablefoldhandler.endfold",
			ConfigurableFoldHandler.DEFAULT_FOLD_STRINGS.getEndString()));
	
		Mode[] modes = jEdit.getModes();
		modeNames = new String[modes.length];
		
		for(int i = 0; i < modes.length; i++)
			modeNames[i] = modes[i].getName();
		
		modeBox = new JComboBox(modeNames);
		
		modeUseDefault = new JCheckBox(jEdit.getProperty(
			"options.configurablefoldhandler.use-default"));
		
		modeUseRegex = new JCheckBox(jEdit.getProperty(
			"options.configurablefoldhandler.use-regex"));
		
		modeStartField = new JTextField();
		modeEndField   = new JTextField();
		
		addSeparator("options.configurablefoldhandler.separator.default");
	
		addComponent(defUseRegex);
	
		addComponent(jEdit.getProperty(
			"options.configurablefoldhandler.foldstart"), defStartField);
		
		addComponent(jEdit.getProperty(
			"options.configurablefoldhandler.foldend"), defEndField);
		
		addSeparator("options.configurablefoldhandler.separator.mode-specific");
		
		addComponent(jEdit.getProperty("options.configurablefoldhandler.mode"),
			modeBox);
		
		addComponent(modeUseDefault);
		addComponent(modeUseRegex);
		
		addComponent(jEdit.getProperty(
			"options.configurablefoldhandler.foldstart"), modeStartField);
		
		addComponent(jEdit.getProperty(
			"options.configurablefoldhandler.foldend"), modeEndField);
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == modeBox)
					modeChanged();
				else if(e.getSource() == modeUseDefault)
					useDefClicked();
			}
		};
		modeBox.addActionListener(actionListener);
		modeUseDefault.addActionListener(actionListener);
		
		String startProp;
		String endProp;
		String regexProp;
		String modeFoldStart;
		String modeFoldEnd;
		boolean isRegex;
		
		for(int i = 0; i < modeNames.length; i++)
		{
			startProp = "configurablefoldhandler.mode." + modeNames[i] +
				".startfold";
			
			endProp = "configurablefoldhandler.mode." + modeNames[i] +
				".endfold";
			
			regexProp = "configurablefoldhandler.mode." + modeNames[i] +
				".use-regex";
			
			modeFoldStart = jEdit.getProperty(startProp);
			modeFoldEnd   = jEdit.getProperty(endProp);
			isRegex       = jEdit.getBooleanProperty(regexProp, false);
			
			if(modeFoldStart != null && modeFoldEnd != null)
			{
				modeStrings.put(modeNames[i], new FoldStrings(
					modeFoldStart, modeFoldEnd, isRegex));
			}
		}
	}
	
	protected void _init()
	{
		// don't want anything to happen when the selected index of the mode
		// combo box is changed in code
		initialising = true;
		
		int lastSelIndex = jEdit.getIntegerProperty("options." +
			"configurablefoldhandler.last-mode-selected", 0);
		
		if(lastSelIndex < modeBox.getItemCount())
			modeBox.setSelectedIndex(lastSelIndex);
		
		lastModeName = modeNames[modeBox.getSelectedIndex()];
		checkMode();
		
		initialising = false;
	}
	
	/**
	 * Called when the options dialog's `OK' button is pressed.
	 * This should save any properties saved in this option pane.
	 */
	protected void _save()
	{
		modeChanged();
		
		jEdit.setBooleanProperty("configurablefoldhandler.use-regex",
			defUseRegex.isSelected());
		
		jEdit.setProperty("configurablefoldhandler.startfold",
			defStartField.getText());
		
		jEdit.setProperty("configurablefoldhandler.endfold",
			defEndField.getText());
		
		String startProp;
		String endProp;
		String regexProp;
		String modeFoldStart;
		String modeFoldEnd;
		FoldStrings foldStrings;
		
		for(int i = 0; i < modeNames.length; i++)
		{
			foldStrings = (FoldStrings)modeStrings.get(modeNames[i]);
			
			startProp = "configurablefoldhandler.mode." + modeNames[i] +
				".startfold";
			
			endProp = "configurablefoldhandler.mode." + modeNames[i] +
				".endfold";
			
			regexProp = "configurablefoldhandler.mode." + modeNames[i] +
				".use-regex";
			
			if(foldStrings != null)
			{
				jEdit.setProperty(startProp, foldStrings.getStartString());
				jEdit.setProperty(endProp, foldStrings.getEndString());
				jEdit.setBooleanProperty(regexProp, foldStrings.useRegex());
			}
			else
			{
				jEdit.resetProperty(startProp);
				jEdit.resetProperty(endProp);
				jEdit.resetProperty(regexProp);
			}
		}
		plugin.foldStringsChanged();
		
		jEdit.setIntegerProperty("options.configurablefoldhandler." +
			"last-mode-selected", modeBox.getSelectedIndex());
	}
	
	/**
	 * saves the fold settings for the last selected mode when the user selects
	 * a new mode from the combo box.
	 */
	private void modeChanged()
	{
		if(initialising)
			return;
		
		if(modeUseDefault.isSelected())
		{
			modeStrings.remove(lastModeName);
		}
		else
		{
			modeStrings.put(lastModeName, new FoldStrings(
				modeStartField.getText(), modeEndField.getText(),
				modeUseRegex.isSelected()));
		}
		checkMode();
		lastModeName = modeNames[modeBox.getSelectedIndex()];
	}
	
	private void useDefClicked()
	{
		modeStartField.setEnabled(!modeUseDefault.isSelected());
		modeEndField.setEnabled(!modeUseDefault.isSelected());
		modeUseRegex.setEnabled(!modeUseDefault.isSelected());
	}
	
	/**
	 * checks if there are any fold strings for the mode currently selected in
	 * the mode combo box. if there are then they are put into the text fields
	 * and they're enabled, otherwise the text fields are emptied and the fields
	 * are disabled
	 */
	private void checkMode()
	{
		String modeName = modeNames[modeBox.getSelectedIndex()];
		FoldStrings foldStrings = (FoldStrings)modeStrings.get(modeName);
		
		if(foldStrings != null)
		{
			modeUseDefault.setSelected(false);
			modeStartField.setEnabled(true);
			modeEndField.setEnabled(true);
			modeUseRegex.setEnabled(true);
			modeUseRegex.setSelected(foldStrings.useRegex());
			modeStartField.setText(foldStrings.getStartString());
			modeEndField.setText(foldStrings.getEndString());
		}
		else
		{
			foldStrings = plugin.getDefaultFoldStrings();
			
			modeUseDefault.setSelected(true);
			modeUseRegex.setSelected(false);
			modeUseRegex.setEnabled(false);
			modeStartField.setEnabled(false);
			modeEndField.setEnabled(false);
			modeStartField.setText(foldStrings.getStartString());
			modeEndField.setText(foldStrings.getEndString());
		}
	}
}

