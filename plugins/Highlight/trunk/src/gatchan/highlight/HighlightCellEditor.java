package gatchan.highlight;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

/**
 * The highlight cell editor used by the JTable containing Highlights.
 *
 * @author Matthieu Casanova
 */
public final class HighlightCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener, DocumentListener {
  private Highlight highlight;
  private final HighlightTablePanel renderer = new HighlightTablePanel();

  public HighlightCellEditor() {
    renderer.setCellEditor(this);
  }

  public Object getCellEditorValue() {
    return highlight;
  }

  public boolean stopCellEditing() {
    try {
      renderer.save(highlight);
      renderer.stopEdition();
      HighlightManagerTableModel.getManager().setHighlightEnable(true);
      fireEditingStopped();
      return true;
    } catch (InvalidHighlightException e) {
      JOptionPane.showMessageDialog(renderer, e.getMessage(), "Invalid highlight", JOptionPane.ERROR_MESSAGE);
      return false;
    }
  }

  public boolean isCellEditable(EventObject e) {
    /* if (e instanceof MouseEvent) {
       final MouseEvent mouseEvent = (MouseEvent) e;
       return mouseEvent.getClickCount() == 2;
     }   */
    return true;
  }

  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    highlight = (Highlight) value;
    renderer.setHighlight(highlight);
    return renderer;
  }

  public void actionPerformed(ActionEvent e) {
    stopCellEditing();
  }

  public void changedUpdate(DocumentEvent e) {
    saveHighlight();
  }

  public void insertUpdate(DocumentEvent e) {
    saveHighlight();
  }

  public void removeUpdate(DocumentEvent e) {
    saveHighlight();
  }

  private void saveHighlight() {
    try {
      renderer.save(highlight);
      HighlightManagerTableModel.getManager().setHighlightEnable(true);
    } catch (InvalidHighlightException e) {
    }
  }
}
