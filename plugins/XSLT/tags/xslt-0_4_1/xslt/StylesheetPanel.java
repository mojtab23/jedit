/* *  StylesheetPanel.java - GUI panel for list of XSL files * *  Copyright (C) 2003 Robert McKinnon * *  This program is free software; you can redistribute it and/or *  modify it under the terms of the GNU General Public License *  as published by the Free Software Foundation; either version 2 *  of the License, or any later version. * *  This program is distributed in the hope that it will be useful, *  but WITHOUT ANY WARRANTY; without even the implied warranty of *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the *  GNU General Public License for more details. * *  You should have received a copy of the GNU General Public License *  along with this program; if not, write to the Free Software *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. */package xslt;import org.gjt.sp.jedit.GUIUtilities;import org.gjt.sp.jedit.View;import org.gjt.sp.jedit.jEdit;import org.gjt.sp.jedit.MiscUtilities;import javax.swing.*;import javax.swing.event.ListSelectionEvent;import javax.swing.event.ListSelectionListener;import java.awt.*;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.util.Arrays;import java.util.Iterator;import java.util.List;/** * GUI panel for list of XSL files * * @author Robert McKinnon - robmckinnon@users.sourceforge.net */public class StylesheetPanel extends JPanel implements ActionListener, ListSelectionListener {  private static final String STYLESHEET_ADD = "stylesheets.add";  private static final String STYLESHEET_REMOVE = "stylesheets.remove";  private static final String STYLESHEET_UP = "stylesheets.up";  private static final String STYLESHEET_DOWN = "stylesheets.down";  private View view;  private XSLTProcessor processor;  private DefaultListModel stylesheetsListModel;  private JList stylesheetsList;  private JButton addStylesheetButton;  private JButton removeStylesheetButton;  private JButton upButton;  private JButton downButton;  /**   * Constructor for the XSLTProcessor object.   *   *@param theView   */  public StylesheetPanel(View theView, XSLTProcessor processor) {    super(new GridBagLayout());    this.view = theView;    this.processor = processor;    this.stylesheetsListModel = initStylesheetListModel();    this.stylesheetsList = initStylesheetList();    this.addStylesheetButton = XSLTProcessor.initButton(STYLESHEET_ADD, this, true);    this.removeStylesheetButton = XSLTProcessor.initButton(STYLESHEET_REMOVE, this, false);    this.upButton = XSLTProcessor.initButton(STYLESHEET_UP, this, false);    this.downButton = XSLTProcessor.initButton(STYLESHEET_DOWN, this, false);    String label = jEdit.getProperty("XSLTProcessor.stylesheets.label");    JScrollPane editorComponent = new JScrollPane(stylesheetsList);    initPanel(this, label, editorComponent, addStylesheetButton, removeStylesheetButton);    GridBagConstraints constraints = XSLTProcessor.getConstraints(4, new Insets(2, 2, 0, 2));    constraints.anchor = GridBagConstraints.EAST;    add(upButton, constraints);    constraints = XSLTProcessor.getConstraints(5, new Insets(0, 2, 2, 2));    constraints.anchor = GridBagConstraints.EAST;    add(downButton, constraints);    constraints = XSLTProcessor.getConstraints(6, new Insets(4, 4, 4, 4));    constraints.fill = GridBagConstraints.VERTICAL;    add(new JPanel(), constraints);  }  public String getStylesheet(int index) {    return (String)stylesheetsListModel.get(index);  }  public Object[] getStylesheets() {    return stylesheetsListModel.toArray();  }  public void setStylesheets(String[] stylesheets) {    stylesheetsListModel.clear();    for(int i = 0; i < stylesheets.length; i++) {      stylesheetsListModel.add(i, stylesheets[i]);    }    if(getStylesheetCount() > 0) {      processor.getTransformButton().setEnabled(true);    } else {      processor.getTransformButton().setEnabled(false);    }        storeStylesheets();  }  public boolean stylesheetsExist() {    return stylesheetsListModel.size() > 0;  }  public int getStylesheetCount() {    return stylesheetsListModel.size();  }  public void valueChanged(ListSelectionEvent e) {    boolean selectionExists = stylesheetsList.getSelectedIndex() != -1;    removeStylesheetButton.setEnabled(selectionExists);    upButton.setEnabled(selectionExists && (stylesheetsListModel.getSize() > 1)        && (stylesheetsList.getSelectedIndex() != 0));    downButton.setEnabled(selectionExists && (stylesheetsListModel.getSize() > 1)        && (stylesheetsList.getSelectedIndex() < stylesheetsListModel.getSize() - 1));  }  private DefaultListModel initStylesheetListModel() {    DefaultListModel stylesheetsListModel = new DefaultListModel();    List values = PropertyUtil.getEnumeratedProperty("XSLTProcessor.lastStylesheet", jEdit.getProperties());    Iterator it = values.iterator();    while(it.hasNext()) {      stylesheetsListModel.addElement(it.next());    }    return stylesheetsListModel;  }  private JList initStylesheetList() {    JList list = new JList(stylesheetsListModel);    list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    list.getSelectionModel().addListSelectionListener(this);    return list;  }  static void initPanel(JPanel panel, String label, JScrollPane editorComponent, JButton addButton, JButton removeButton) {    GridBagConstraints constraints = XSLTProcessor.getConstraints(1, new Insets(4, 4, 0, 4));    constraints.anchor = GridBagConstraints.NORTHWEST;    panel.add(new JLabel(label), constraints);    constraints = XSLTProcessor.getConstraints(2, new Insets(4, 4, 4, 4));    constraints.gridheight = 5;    constraints.weightx = constraints.weighty = 5;    constraints.fill = GridBagConstraints.BOTH;    panel.add(editorComponent, constraints);    constraints = XSLTProcessor.getConstraints(2, new Insets(2, 2, 0, 2));    constraints.anchor = GridBagConstraints.EAST;    panel.add(addButton, constraints);    constraints = XSLTProcessor.getConstraints(3, new Insets(0, 2, 2, 2));    constraints.anchor = GridBagConstraints.EAST;    panel.add(removeButton, constraints);  }  public void actionPerformed(ActionEvent e) {    String action = e.getActionCommand();    if(action == STYLESHEET_ADD) {      addStylesheet();    } else if(action == STYLESHEET_REMOVE) {      removeStylesheet(stylesheetsList.getSelectedIndex());    } else if(action == STYLESHEET_UP) {      moveStylesheet(true);    } else if(action == STYLESHEET_DOWN) {      moveStylesheet(false);    }  }  private void storeStylesheets() {    List stylesheetsList = Arrays.asList(stylesheetsListModel.toArray());    PropertyUtil.setEnumeratedProperty("XSLTProcessor.lastStylesheet", stylesheetsList, jEdit.getProperties());  }  private void addStylesheet() {    String path = null;    if(stylesheetsExist()) {      path = MiscUtilities.getParentOfPath(getStylesheet(0));    }    String[] selections = GUIUtilities.showVFSFileDialog(view, path, JFileChooser.OPEN_DIALOG, false);    if(selections != null) {      stylesheetsListModel.addElement(selections[0]);      processor.getTransformButton().setEnabled(true);      if((stylesheetsList.getSelectedIndex() != -1) && (stylesheetsListModel.getSize() > 1)) {        downButton.setEnabled(true);      }      storeStylesheets();    }    Container topLevelAncestor = StylesheetPanel.this.getTopLevelAncestor();    if(topLevelAncestor instanceof JFrame) {      ((JFrame)topLevelAncestor).toFront();    }  }  private void removeStylesheet(int index) {    stylesheetsListModel.remove(index);    if(stylesheetsListModel.getSize() > 0) {      stylesheetsList.setSelectedIndex(0);    } else {      removeStylesheetButton.setEnabled(false);      processor.getTransformButton().setEnabled(false);    }    storeStylesheets();  }  public void moveStylesheet(boolean isMoveUp) {    int move = isMoveUp ? -1 : 1;    int selectedIndex = stylesheetsList.getSelectedIndex();    Object selected = stylesheetsListModel.get(selectedIndex);    stylesheetsListModel.remove(selectedIndex);    stylesheetsListModel.insertElementAt(selected, selectedIndex + move);    stylesheetsList.setSelectedIndex(selectedIndex + move);    storeStylesheets();  }}