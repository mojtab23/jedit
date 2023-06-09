/*
 * XPathTool.java - GUI for evaluating XPath expressions
 *
 * Copyright (C) 2002 Greg Merrill
 *               2002, 2003, 2004 Robert McKinnon
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

package xslt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.text.MessageFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.xpath.NodeSetDTM;
import org.apache.xpath.XPathAPI;
import org.apache.xpath.objects.XObject;
import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.gui.DefaultFocusComponent;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * GUI for evaluating XPath expressions.
 *
 * @author Greg Merrill
 * @author Robert McKinnon
 */
public class XPathTool extends JPanel implements ListSelectionListener,
		ActionListener, DefaultFocusComponent {

	private View view;
	private final XPathInputSelectionPanel inputSelectionPanel;
	private final XPathExpressionPanel expressionPanel = new XPathExpressionPanel();
	private final EvaluatePanel evaluatePanel = new EvaluatePanel();
	private final JTextField dateTypeField = new JTextField();
	private final ResultsPanel resultValuePanel = new ResultsPanel(jEdit.getProperty("xpath.result.value.label"));
	private final NodeSetResultsPanel nodeSetTablePanel = new NodeSetResultsPanel(jEdit.getProperty("xpath.result.node-set-summary.label"));
	private final XmlFragmentsPanel xmlFragmentsPanel = new XmlFragmentsPanel(jEdit.getProperty("xpath.result.xml-fragments.label"));
	private JPanel dataTypePanel;


	public XPathTool(View view) {
		super(new GridBagLayout());
		this.view = view;

		inputSelectionPanel = new XPathInputSelectionPanel(view);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel(jEdit.getProperty("xpath.result.data-type.label")), BorderLayout.NORTH);
		panel.add(this.dateTypeField);
		dataTypePanel = new JPanel(new BorderLayout());
		dataTypePanel.add(panel, BorderLayout.NORTH);


		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		add(inputSelectionPanel, gbc);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = gbc.weighty = 1;
		gbc.gridy = 2;
		add(expressionPanel, gbc);

		gbc = new GridBagConstraints();
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;
		add(evaluatePanel, gbc);

		gbc = new GridBagConstraints();
		gbc.gridy = 4;
		gbc.weightx = 1;
		gbc.weighty = 6;
		gbc.fill = GridBagConstraints.BOTH;
		add(getSplitPane(), gbc);
	}


	public void focusOnDefaultComponent() {
		expressionPanel.focusOnDefaultComponent();
	}

	/**
	 * Creates parser, parses input source and returns resulting document.
	 */
	public static Document parse(InputSource source, String inputPath) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);

		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setEntityResolver(new EntityResolverImpl(inputPath));
		Document document = builder.parse(source);
		return document;
	}


	/**
	 * Returns string result of enclosing expression in a XPath <code>string</code> function and evaluating it.
	 */
	public static String evalString(Document document, String expression) throws TransformerException {
		XObject xObject = XPathAPI.eval(document, "string(" + expression + ")");
		return xObject.xstr().toString();
	}


	/**
	 * Returns string result of enclosing expression in a XPath <code>count</code> function and evaluating it.
	 */
	public static int evalCount(Document document, String expression) throws TransformerException {
		XObject xObject = XPathAPI.eval(document, "count(" + expression + ")");
		return Integer.parseInt(xObject.xstr().toString());
	}

	/**
	 * Clicks the evaluate XPath button.
	 */
	public void clickEvaluateButton() {
		evaluatePanel.button.doClick();
	}


	public void actionPerformed(ActionEvent event) {
		try {
			evaluateExpression();

		} catch (IllegalStateException e) {
			XSLTPlugin.processException(e, e.getMessage(), XPathTool.this);
		} catch (SAXException e) { // parse problem
			XSLTPlugin.processException(e, jEdit.getProperty("xpath.result.message.buffer-unparseable"), XPathTool.this);
		} catch (IOException e) { // parse problem
			XSLTPlugin.processException(e, jEdit.getProperty("xpath.result.message.buffer-unparseable"), XPathTool.this);
		} catch (TransformerException e) { // evaluation problem
			XSLTPlugin.processException(e, jEdit.getProperty("xpath.result.message.expression-unevaluateable"), XPathTool.this);
		} catch (Exception e) { // catch-all
			XSLTPlugin.processException(e, jEdit.getProperty("xpath.result.message.unkown-problem"), XPathTool.this);
		}
	}


	private void evaluateExpression() throws Exception, IOException, SAXException, TransformerException {
		String path = new String();
		InputSource inputSource;

		if (inputSelectionPanel.isFileSelected()) { //take input from file
			path = inputSelectionPanel.getSourceFieldText();
			FileReader textReader = new FileReader(new File(path));
			inputSource = new InputSource(textReader);
		} else { // take input from active buffer
			Buffer buffer = view.getBuffer();
			path = buffer.getPath();
			String text = buffer.getText(0, buffer.getLength());
			inputSource = new InputSource(new StringReader(text));
		}

		inputSource.setSystemId(path);
		Document document = parse(inputSource, path);

		String expression = new String();

		expression = expressionPanel.getExpression();

		XObject xObject = XPathAPI.eval(document, expression);
		expressionPanel.addToHistory(expression);

		dateTypeField.setText(getDataTypeMessage(xObject));
		xObject.xstr().toString();
		setResultValue(xObject, document, expression);
		setNodeSetResults(xObject);
		setXmlFragments(xObject);
	}


	private JSplitPane getSplitPane() {
		JSplitPane bottomSplitPane = getSplitPane(nodeSetTablePanel, xmlFragmentsPanel, 150);
		JSplitPane middleSplitPane = getSplitPane(resultValuePanel, bottomSplitPane, 80);
		JSplitPane topSplitPane = getSplitPane(dataTypePanel, middleSplitPane, 35);
		return topSplitPane;
	}


	private JSplitPane getSplitPane(final JComponent top, final JComponent bottom, final int dividerLocation) {
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, bottom);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(dividerLocation);
		return splitPane;
	}


	/**
	 * Returns a message containing the data type selected by the XPath expression.
	 * Note: there are four data types in the XPath 1.0 data model: node-set, string,
	 * number, and boolean.
	 *
	 * @param xObject XObject to be converted
	 * @return string describing the selected data type
	 */
	private String getDataTypeMessage(XObject xObject) throws TransformerException {
		String typeMessage = null;

		if (xObject.getType() == XObject.CLASS_NODESET) {
			NodeSetDTM nodeSet = xObject.mutableNodeset();

			Object[] messageArgs = new Object[]{new Integer(nodeSet.size())};
			typeMessage = MessageFormat.format(jEdit.getProperty("xpath.result.data-type.node-set"), messageArgs);
		} else {
			Object[] messageArgs = new Object[]{xObject.getTypeString().substring(1).toLowerCase()};
			typeMessage = MessageFormat.format(jEdit.getProperty("xpath.result.data-type.not-node-set"), messageArgs);
		}

		return typeMessage;
	}


	private void setResultValue(XObject xObject, final Document document, final String expression) {
		final boolean isNodeSet = isNodeSet(xObject);
		final String text = xObject.xstr().toString();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(isNodeSet) {
					resultValuePanel.setLabelText(jEdit.getProperty("xpath.result.string-value.label"));
					try {
						String stringValue = XPathAPI.eval(document, "string(" + expression + ")").toString();
						resultValuePanel.setText(stringValue);
					} catch (TransformerException e) {
						resultValuePanel.setText("");
					}
				} else {
					resultValuePanel.setLabelText(jEdit.getProperty("xpath.result.value.label"));
					resultValuePanel.setText(text);
				}
				resultValuePanel.resetCaretPosition();
			}
		});
	}


	private void setXmlFragments(XObject xObject) throws Exception {
		if (isNodeSet(xObject)) {
			try {
				XMLFragmentsString xmlFragments = new XMLFragmentsString(xObject.nodelist());
				xmlFragmentsPanel.setXmlFragments(xmlFragments);
			} catch (Exception e) {
				xmlFragmentsPanel.setXmlFragments(null);
				throw e;
			}
		} else {
			xmlFragmentsPanel.setXmlFragments(null);
		}
	}


	/**
	 * Populates a node set table model with the results of an XPath evaluation, if
	 * the results are of type node-set.
	 * Note: there are four data types in the XPath 1.0 data model: node-set, string,
	 * number, and boolean.
	 *
	 * @param xObject XObject containing results
	 */
	private void setNodeSetResults(XObject xObject) throws TransformerException {
		NodeSetTableModel tableModel = nodeSetTablePanel.getTableModel();
		if (isNodeSet(xObject)) {
			NodeSetDTM nodeSet = xObject.mutableNodeset();
			tableModel.resetRows(nodeSet.size());
			boolean isNodeWithName = false;
			boolean isNodeWithValue = false;
			XPathNode node;

			for (int i = 0; i < nodeSet.size(); i++) {
				node = XPathNode.getXPathNode(nodeSet, i);

				if (node != null) {
					isNodeWithName = isNodeWithName || node.hasExpandedName();
					isNodeWithValue = isNodeWithValue || node.hasDomValue();

					tableModel.setNodeType(node.getType(), i);
					tableModel.setNodeName(node.getName(), i);
					tableModel.setNodeValue(node.getDomValue(), i);
				}
			}

			if (!isNodeWithName && !isNodeWithValue) {
				tableModel.removeNameOrValueColumn();
			} else if (!isNodeWithName) {
				tableModel.removeNameColumn();
			} else if (!isNodeWithValue) {
				tableModel.removeValueColumn();
			}
		} else {
			tableModel.resetRows(0);
		}
	}


	private static boolean isNodeSet(XObject xObject) {
		return xObject.getType() == XObject.CLASS_NODESET;
	}


	/**
	 * Panel housing the "Evaluate" button
	 */
	class EvaluatePanel extends JPanel {
		private JButton button;


		EvaluatePanel() {
			String iconName = jEdit.getProperty("xpath.evaluate.button.icon");
			String toolTipText = jEdit.getProperty("xpath.evaluate.button.tooltip");
			String shortcut = jEdit.getProperty("xpath.evaluate.shortcut");

			if (shortcut != null) {
				toolTipText += " (" + shortcut + ")";
			}

			URL url = XSLTProcessor.class.getResource(iconName);
			button = new JButton(new ImageIcon(url));
			button.setToolTipText(toolTipText);
			button.addActionListener(XPathTool.this);

			Dimension dimension = new Dimension(76, 30);
			button.setMinimumSize(dimension);
			button.setPreferredSize(dimension);

			add(button);
		}
	}

	/**
	 * JTextArea that let's tab key change focus to the next component.
	 */
	public class JTextAreaWithoutTab extends JTextArea {
		public boolean isManagingFocus() {
			return false;
		}
	}


	/**
	 * Panel housing the "Results" label & text area
	 */
	class ResultsPanel extends JPanel {
		protected final JTextArea textArea = new JTextAreaWithoutTab();
		private final JLabel label = new JLabel();


		ResultsPanel(String labelString) {
			super(new BorderLayout());
			setLabelText(labelString);
			this.textArea.setEditable(false);
			int width = (int) textArea.getMinimumSize().getWidth();
			int height = (int) textArea.getPreferredSize().getHeight();
			this.textArea.setMinimumSize(new Dimension(width, height));

			add(label, BorderLayout.NORTH);
			add(new JScrollPane(this.textArea));
		}


		void setLabelText(String text) {
			this.label.setText(text);
		}


		void setText(String text) {
			this.textArea.setText(text);
		}


		void resetCaretPosition() {
			this.textArea.setCaretPosition(0);
		}

	}


	/**
	 * Handles the selection of a row on the node set results table,
	 * implements interface {@link javax.swing.event.ListSelectionListener}.
	 */
	public void valueChanged(ListSelectionEvent event) {
		int selectedRow = this.nodeSetTablePanel.table.getSelectedRow();
		this.xmlFragmentsPanel.highlightFragment(selectedRow);
	}


	/**
	 * Panel housing the XML fragments results.
	 */
	class XmlFragmentsPanel extends ResultsPanel {

		private XMLFragmentsString xmlFragments;
		private int selected;


		XmlFragmentsPanel(String labelString) {
			super(labelString);
		}


		public void highlightFragment(int index) {
			if (this.xmlFragments != null && index >= 0) {
				int startIndex = this.xmlFragments.getFragmentPosition(index);
				int endIndex = this.xmlFragments.getFragmentPosition(index + 1);

				textArea.getHighlighter().removeAllHighlights();

				try {
					textArea.getHighlighter().addHighlight(startIndex, endIndex, new DefaultHighlighter.DefaultHighlightPainter(new Color(200, 200, 200)));
				} catch (BadLocationException e) {
					throw new IllegalArgumentException(e.toString());
				}

				if (this.selected > index) {
					int temp = startIndex;
					startIndex = endIndex;
					endIndex = temp;
				}

				this.selected = index;

				textArea.setCaretPosition(startIndex);
				textArea.moveCaretPosition(endIndex);
			}
		}


		public void setXmlFragments(XMLFragmentsString xmlFragments) {
			this.xmlFragments = xmlFragments;
			this.selected = -1;

			if (xmlFragments == null) {
				setText("");
			} else {
				setText(xmlFragments.getString());
			}

			resetCaretPosition();
		}
	}


	/**
	 * Panel housing the "Results" label & text area
	 */
	class NodeSetResultsPanel extends JPanel {
		private final NodeSetTableModel tableModel = new NodeSetTableModel();
		private final JTable table = new JTable();


		NodeSetResultsPanel(String label) {
			super(new BorderLayout());

			table.getSelectionModel().addListSelectionListener(XPathTool.this);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setModel(tableModel);

			add(new JLabel(label), BorderLayout.NORTH);
			JScrollPane tablePane = new JScrollPane(table);
			add(tablePane);
		}


		NodeSetTableModel getTableModel() {
			return this.tableModel;
		}

	}


}
