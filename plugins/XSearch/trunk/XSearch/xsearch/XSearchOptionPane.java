/*
 * XSearchOptionPane.java - plugin options pane for BufferList
 * Copyright (c) 2000,2001 Dirk Moebius
 *
 * :tabSize=2:indentSize=2:noTabs=false:maxLineLen=0:
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

package xsearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.AbstractOptionPane;

/**
 * This is the option pane that jEdit displays for XSearch's options.
 */
public class XSearchOptionPane extends AbstractOptionPane
// implements ActionListener
{

	public XSearchOptionPane()
	{
		super("xsearch");
	}


	public void _init()
	{
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTextArea checkBoxInfo = new JTextArea(jEdit.getProperty(
		"xsearch.options.checkBoxLabel"),2,60);
		checkBoxInfo.setLineWrap(true);
		checkBoxInfo.setWrapStyleWord(true);
		checkBoxInfo.setOpaque(false);

		JTextArea buttonInfo = new JTextArea(jEdit.getProperty(
		"xsearch.options.buttonLabel"),2,60);
		buttonInfo.setLineWrap(true);
		buttonInfo.setWrapStyleWord(true);
		buttonInfo.setOpaque(false);

		JTextArea functionInfo = new JTextArea(jEdit.getProperty(
		"xsearch.options.functionsLabel"),2,60);
		functionInfo.setLineWrap(true);
		functionInfo.setWrapStyleWord(true);
		functionInfo.setOpaque(false);

		wordPartSearch = new JCheckBox(jEdit.getProperty("xsearch.options.wordPartSearch"),
			jEdit.getBooleanProperty("xsearch.wordPartSearch", true));

		columnSearch = new JCheckBox(jEdit.getProperty("xsearch.options.columnSearch"),
			jEdit.getBooleanProperty("xsearch.columnSearch", true));

		rowSearch = new JCheckBox(jEdit.getProperty("xsearch.options.rowSearch"),
			jEdit.getBooleanProperty("xsearch.rowSearch", true));

		foldSearch = new JCheckBox(jEdit.getProperty("xsearch.options.foldSearch"),
			jEdit.getBooleanProperty("xsearch.foldSearch", true));

		commentSearch = new JCheckBox(jEdit.getProperty("xsearch.options.commentSearch"),
			jEdit.getBooleanProperty("xsearch.commentSearch", true));

		tentativSearch = new JCheckBox(jEdit.getProperty("xsearch.options.tentativSearch"),
			jEdit.getBooleanProperty("xsearch.tentativSearch", false));

		hyperRange = new JCheckBox(jEdit.getProperty("xsearch.options.hyperRange"),
			jEdit.getBooleanProperty("xsearch.hyperRange", true));

		settingsHistory = new JCheckBox(jEdit.getProperty("xsearch.options.settingsHistory"),
			jEdit.getBooleanProperty("xsearch.settingsHistory", true));

		findAllButton = new JCheckBox(jEdit.getProperty("xsearch.options.findAllButton"),
			jEdit.getBooleanProperty("xsearch.findAllButton", true));

		resetButton = new JCheckBox(jEdit.getProperty("xsearch.options.resetButton"),
			jEdit.getBooleanProperty("xsearch.resetButton", true));

		hyperReplace = new JCheckBox(jEdit.getProperty("xsearch.options.hyperReplace"),
			jEdit.getBooleanProperty("xsearch.hyperReplace", true));
		hyperReplace.setToolTipText(jEdit.getProperty("xsearch.options.tooltip.hyperReplace"));

		replaceCaseSensitiv = new JCheckBox(jEdit.getProperty("xsearch.options.replaceCaseSensitiv"),
			jEdit.getBooleanProperty("xsearch.replaceCaseSensitiv", true));

		textAreaFont = new JCheckBox(jEdit.getProperty("xsearch.options.textAreaFont"),
			jEdit.getBooleanProperty("xsearch.textAreaFont", true));


		//addSeparator("xsearch.options.separator.searchoptions");
		addComponent(checkBoxInfo);
		addComponent(wordPartSearch);
		addComponent(columnSearch);
		addComponent(rowSearch);
		addComponent(foldSearch);
		addComponent(commentSearch);
		addComponent(tentativSearch);
		addComponent(hyperRange);
		addComponent(settingsHistory);

		addSeparator("xsearch.options.separator.buttons");
		addComponent(buttonInfo);
		addComponent(findAllButton);
		addComponent(resetButton);
		
		addSeparator("xsearch.options.separator.buttons");
		addComponent(functionInfo);
		addComponent(hyperReplace);
		addComponent(replaceCaseSensitiv);
		addComponent(textAreaFont);

	}

//	public void actionPerformed(ActionEvent e) {
//	}

	public void _save() {
		jEdit.setBooleanProperty("xsearch.wordPartSearch", wordPartSearch.isSelected());
		jEdit.setBooleanProperty("xsearch.columnSearch", columnSearch.isSelected());
		jEdit.setBooleanProperty("xsearch.rowSearch", rowSearch.isSelected());
		jEdit.setBooleanProperty("xsearch.foldSearch", foldSearch.isSelected());
		jEdit.setBooleanProperty("xsearch.commentSearch", commentSearch.isSelected());
		jEdit.setBooleanProperty("xsearch.tentativSearch", tentativSearch.isSelected());
		jEdit.setBooleanProperty("xsearch.hyperRange", hyperRange.isSelected());
		jEdit.setBooleanProperty("xsearch.settingsHistory", settingsHistory.isSelected());
		jEdit.setBooleanProperty("xsearch.findAllButton", findAllButton.isSelected());
		jEdit.setBooleanProperty("xsearch.resetButton", resetButton.isSelected());
		jEdit.setBooleanProperty("xsearch.hyperReplace", hyperReplace.isSelected());
		jEdit.setBooleanProperty("xsearch.replaceCaseSensitiv", replaceCaseSensitiv.isSelected());
		jEdit.setBooleanProperty("xsearch.textAreaFont", textAreaFont.isSelected());
		
		// reset settings which are not desired
		if (!wordPartSearch.isSelected())
			XSearchAndReplace.setWordPartOption(XSearchDialog.SEARCH_PART_NONE);
		if (!columnSearch.isSelected())
			XSearchAndReplace.resetColumnSearch();
		if (!rowSearch.isSelected())
			XSearchAndReplace.resetRowSearch();
		if (!foldSearch.isSelected())
			XSearchAndReplace.setFoldOption(XSearchDialog.SEARCH_IN_OUT_NONE);
		if (!commentSearch.isSelected())
			XSearchAndReplace.setCommentOption(XSearchDialog.SEARCH_IN_OUT_NONE);
		if (!tentativSearch.isSelected())
			XSearchAndReplace.setTentativOption(false);
		if (!hyperRange.isSelected())
			XSearchAndReplace.setHyperRange(-1, -1);
	}

	private JCheckBox wordPartSearch;
	private JCheckBox columnSearch;
	private JCheckBox rowSearch;
	private JCheckBox foldSearch;
	private JCheckBox commentSearch;
	private JCheckBox tentativSearch;
	private JCheckBox hyperRange;
	private JCheckBox findAllButton;
	private JCheckBox resetButton;
	private JCheckBox settingsHistory;
	private JCheckBox hyperReplace;
	private JCheckBox replaceCaseSensitiv;
	private JCheckBox textAreaFont;
}

