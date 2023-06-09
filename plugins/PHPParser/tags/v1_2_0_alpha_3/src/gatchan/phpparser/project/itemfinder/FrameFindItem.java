package gatchan.phpparser.project.itemfinder;

import gatchan.phpparser.project.Project;
import gatchan.phpparser.project.ProjectManager;
import net.sourceforge.phpdt.internal.compiler.ast.ClassHeader;
import net.sourceforge.phpdt.internal.compiler.ast.MethodHeader;
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.msg.BufferUpdate;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.util.Log;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * This window will help you to find a php item.
 *
 * @author Matthieu Casanova
 */
public final class FrameFindItem extends JFrame implements EBComponent {
  public static final int CLASS_MODE = 0;
  public static final int METHOD_MODE = 1;

  private final SimpleListModel listModel = new SimpleListModel();
  private View view;
  private final ProjectManager projectManager;
  private final JLabel label = new JLabel();

  /** This window will contains the scroll with the items. */
  private final JWindow window = new JWindow(this);
  private final JTextField searchField;
  private final JList itemList;

  private int mode;
  private int wantedCaretPosition;
  private Buffer buffer;
  private PHPItemCellRenderer cellRenderer;
  private static final Color LIST_SELECTION_BACKGROUND = new Color(0xcc, 0xcc, 0xff);

  public FrameFindItem() {
    setUndecorated(true);
    itemList = new JList(listModel);
    cellRenderer = new PHPItemCellRenderer();
    searchField = new JTextField();
    itemList.setSelectionBackground(LIST_SELECTION_BACKGROUND);
    itemList.setCellRenderer(cellRenderer);
    itemList.addKeyListener(new ItemListKeyAdapter(searchField));
    itemList.addMouseListener(new MyMouseAdapter());
    searchField.addKeyListener(new SearchFieldKeyAdapter());
    searchField.getDocument().addDocumentListener(new DocumentListener() {
      public void insertUpdate(DocumentEvent e) {
        updateList();
      }

      public void removeUpdate(DocumentEvent e) {
        updateList();
      }

      public void changedUpdate(DocumentEvent e) {
        updateList();
      }
    });
    final JScrollPane scroll = new JScrollPane(itemList);
    window.setContentPane(scroll);
    itemList.setBorder(BorderFactory.createEtchedBorder());
    final JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEtchedBorder());
    setContentPane(panel);
    panel.add(label);
    panel.add(searchField, BorderLayout.SOUTH);
    pack();
    projectManager = ProjectManager.getInstance();
  }

  /** Update the list. */
  private void updateList() {
    final long start = System.currentTimeMillis();
    final Project project = projectManager.getProject();
    if (project != null) {
      final QuickAccessItemFinder quickAccess = project.getQuickAccess();
      final String searchText = searchField.getText().toLowerCase();
      cellRenderer.setSearchString(searchText);
      final long quickAccessStart = System.currentTimeMillis();
      final java.util.List itemContaining = new ArrayList(quickAccess.getItemContaining(searchText));
      Log.log(Log.DEBUG, QuickAccessItemFinder.class, System.currentTimeMillis() - quickAccessStart + " ms");
      if (itemContaining.isEmpty()) {
        searchField.setForeground(Color.red);
        window.setVisible(false);
        listModel.setList(itemContaining);
      } else {
        final ListIterator listIterator = itemContaining.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
          if (i > 25) { // to remove items and do not have lists > 25 items
            listIterator.remove();
          }
          final PHPItem phpItem = (PHPItem) listIterator.next();
          if (doNotAcceptItem(phpItem, searchText)) {
            listIterator.remove();
          } else {
            i++;
          }
        }
        Log.log(Log.DEBUG, this, itemContaining.size() + " items found");
        listModel.setList(itemContaining);
        if (itemContaining.isEmpty()) {
          searchField.setForeground(Color.red);
          window.setVisible(false);
        } else {
          searchField.setForeground(null);
          itemList.setSelectedIndex(0);
          itemList.setVisibleRowCount(Math.min(itemContaining.size(), 10));
          window.pack();
          window.setVisible(true);
        }
      }
      searchField.requestFocus();
    }

    final long end = System.currentTimeMillis();
    Log.log(Log.DEBUG, this, (end - start) + "ms");
  }

  private boolean doNotAcceptItem(PHPItem phpItem, String searchText) {
    return (mode == CLASS_MODE && !(phpItem instanceof ClassHeader) || mode == METHOD_MODE && !(phpItem instanceof MethodHeader)) || phpItem.getName().toLowerCase().indexOf(searchText) == -1;
  }

  private void selectionMade(PHPItem selectedValue) {
    if (selectedValue != null) {
      final String path = selectedValue.getPath();
      wantedCaretPosition = selectedValue.getSourceStart();
      buffer = jEdit.openFile(view, path);
      if (buffer.isLoaded()) {
        setBufferAndPosition();
      } else {
        EditBus.addToBus(this);
      }
      setVisible(false);
    }
  }

  private void setBufferAndPosition() {
    Log.log(Log.DEBUG, this, "Opening " + buffer.getPath() + " moving caret at " + wantedCaretPosition);
    final JEditTextArea textArea = view.getTextArea();
    textArea.setBuffer(buffer);
    if (textArea.getBufferLength() < wantedCaretPosition) {
      //todo maybe reparse this buffer !
      Log.log(Log.WARNING, this, "The buffer do not have the expected length. It should be reparsed");
    } else {
      textArea.setCaretPosition(wantedCaretPosition);
    }
    buffer = null;
    wantedCaretPosition = -1;
  }

  public void handleMessage(EBMessage message) {
    if (message instanceof BufferUpdate) {
      final BufferUpdate bufferUpdate = (BufferUpdate) message;
      if (buffer.equals(bufferUpdate.getSource()) && bufferUpdate.getWhat() == BufferUpdate.LOADED) {
        setBufferAndPosition();
        EditBus.removeFromBus(this);
      }
    }
  }

  /**
   * Initialize the frame find item.
   *
   * @param view the jEdit's view
   * @param mode the mode ({@link FrameFindItem#CLASS_MODE} or {@link FrameFindItem#METHOD_MODE})
   */
  public void init(View view, int mode) {
    this.mode = mode;
    this.view = view;
    listModel.clear();
    searchField.setText(null);
    if (mode == CLASS_MODE) {
      label.setText("Enter the class name");
    } else {
      label.setText("Enter the method name");
    }
    window.pack();
    pack();
  }

  public void setVisible(boolean b) {
    final Rectangle bounds = getBounds();
    window.setLocation(bounds.x, bounds.y + bounds.height);
    GUIUtilities.requestFocus(this, searchField);
    window.setVisible(false);
    super.setVisible(b);
  }

  private static boolean handledByList(KeyEvent e) {
    return e.getKeyCode() == KeyEvent.VK_DOWN ||
           e.getKeyCode() == KeyEvent.VK_UP ||
           e.getKeyCode() == KeyEvent.VK_PAGE_DOWN ||
           e.getKeyCode() == KeyEvent.VK_PAGE_UP;
  }

  private final class SearchFieldKeyAdapter extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
      if (handledByList(e)) {
        itemList.dispatchEvent(e);
      } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        setVisible(false);
      } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        selectionMade((PHPItem) itemList.getSelectedValue());
      }
    }
  }

  private static final class ItemListKeyAdapter extends KeyAdapter {
    private final JTextField searchField;

    public ItemListKeyAdapter(JTextField searchField) {
      this.searchField = searchField;
    }

    public void keyTyped(KeyEvent e) {
      searchField.dispatchEvent(e);
    }

    public void keyPressed(KeyEvent e) {
      if (!handledByList(e)) {
        searchField.dispatchEvent(e);
      }
    }
  }

  private final class MyMouseAdapter extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
        selectionMade((PHPItem) itemList.getSelectedValue());
      }
    }
  }


}
