package gatchan.highlight;

import org.gjt.sp.jedit.GUIUtilities;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The dockable panel that will contains a list of all your highlights.
 *
 * @author Matthieu Casanova
 */
public class HighlightList extends JPanel {

  private JPopupMenu popupMenu;
  private JMenuItem remove;

  private JTable table;
  public static HighlightManagerTableModel tableModel;
  public HighlightList.RemoveAction removeAction;

  public HighlightList() {
    super(new BorderLayout());


    tableModel = HighlightManagerTableModel.getInstance();
    table = new JTable(tableModel);
    table.setDragEnabled(false);
    final HighlightCellRenderer renderer = new HighlightCellRenderer();
    table.setRowHeight(renderer.getPreferredSize().height);
    table.setDefaultRenderer(Highlight.class, renderer);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setShowGrid(false);
    table.setIntercellSpacing(new Dimension(0,0));
    final TableColumn col1 = table.getColumnModel().getColumn(0);
    col1.setPreferredWidth(26);
    col1.setMinWidth(26);
    col1.setMaxWidth(26);
    col1.setResizable(false);

    table.setDefaultEditor(Highlight.class,new HighlightCellEditor());
    table.setDefaultEditor(Boolean.class,table.getDefaultEditor(Boolean.class));
    table.setTableHeader(null);

    table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        final int row = table.rowAtPoint(e.getPoint());
        if (row == -1) return;

        if (e.getButton() == MouseEvent.BUTTON1) {
          final Highlight highlight = (Highlight) table.getValueAt(table.getSelectedRow(), 1);
          tableModel.addElement(highlight);
          if (e.getClickCount() == 2) {
            /* HighlightDialog d = null;
             try {
               d = new HighlightDialog(view);
               d.init(highlight);
               d.setVisible(true);
             } catch (REException e1) {
               Log.log(Log.ERROR,this,e);
             } */
          }
        } else {
          showPopupMenu(e, row);
        }
      }
    });

    final JToolBar toolBar = new JToolBar();
    toolBar.setFloatable(false);
    final JButton clear = new JButton(GUIUtilities.loadIcon("Clear.png"));
    final JCheckBox enableHighlights = new JCheckBox("enable");
    enableHighlights.setSelected(true);

    final MyActionListener actionListener = new MyActionListener(clear, enableHighlights);
    clear.addActionListener(actionListener);
    enableHighlights.addActionListener(actionListener);
    toolBar.add(clear);
    toolBar.add(enableHighlights);

    add(toolBar, BorderLayout.NORTH);
    final JScrollPane scroll = new JScrollPane(table);
    add(scroll);
  }

  private void showPopupMenu(MouseEvent e, int row) {
    if (popupMenu == null) {
      popupMenu = new JPopupMenu();
      removeAction = new RemoveAction();
      remove = popupMenu.add(removeAction);
    }
    remove.setEnabled(tableModel.getRowCount() > 0);
    removeAction.setRow(row);
    GUIUtilities.showPopupMenu(popupMenu, e.getComponent(), e.getX(), e.getY());
    e.consume();
  }

  public class RemoveAction extends AbstractAction {

    private int row;

    public RemoveAction() {
      super("remove");
    }

    public void setRow(int row) {
      this.row = row;
    }

    public void actionPerformed(ActionEvent e) {
      final Object s = tableModel.getHighlight(row);
      tableModel.removeElement(s);
    }
  }

  private class MyActionListener implements ActionListener {
    private JButton clear;
    private JCheckBox enableHighlights;

    public MyActionListener(JButton clear, JCheckBox enableHighlights) {
      this.clear = clear;
      this.enableHighlights = enableHighlights;
    }

    public void actionPerformed(ActionEvent e) {
      if (clear.equals(e.getSource())) {
        tableModel.removeAll();
      } else if (enableHighlights.equals(e.getSource())) {
        tableModel.setHighlightEnable(enableHighlights.isSelected());
      }
    }
  }
}
