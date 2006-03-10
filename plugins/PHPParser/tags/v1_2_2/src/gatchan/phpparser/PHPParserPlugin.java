package gatchan.phpparser;

import gatchan.phpparser.project.ProjectManager;
import gatchan.phpparser.project.itemfinder.FrameFindItem;
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.msg.EditPaneUpdate;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.jedit.textarea.TextAreaPainter;

import java.awt.*;

/**
 * The PHP Parser plugin.
 *
 * @author Matthieu Casanova
 */
public final class PHPParserPlugin extends EditPlugin {
  private ProjectManager projectManager;

  private static FrameFindItem findItemWindow;

  public void start() {
    projectManager = ProjectManager.getInstance();
    findItemWindow = new FrameFindItem();
    /*View view = jEdit.getFirstView();
    while (view != null) {
      EditPane[] panes = view.getEditPanes();
      for (int i = 0; i < panes.length; i++) {
        JEditTextArea textArea = panes[i].getTextArea();
        initTextArea(textArea);
      }
      view = view.getNext();
    }  */
  }

  public void stop() {
    projectManager.dispose();
    projectManager = null;
    findItemWindow.dispose();
    findItemWindow = null;
    /*View view = jEdit.getFirstView();
    while (view != null) {
      EditPane[] panes = view.getEditPanes();
      for (int i = 0; i < panes.length; i++) {
        JEditTextArea textArea = panes[i].getTextArea();
        uninitTextArea(textArea);
      }
      view = view.getNext();
    } */
  }
      /*
  private static void uninitTextArea(JEditTextArea textArea) {
    TextAreaPainter painter = textArea.getPainter();
    PHPParserTextAreaExtension highlighter = (PHPParserTextAreaExtension) textArea.getClientProperty(PHPParserTextAreaExtension.class);
    if (highlighter != null) {
      painter.removeExtension(highlighter);
      textArea.putClientProperty(PHPParserTextAreaExtension.class, null);
    }
  }

  private static void initTextArea(JEditTextArea textArea) {
    PHPParserTextAreaExtension highlighter = new PHPParserTextAreaExtension(textArea);
    TextAreaPainter painter = textArea.getPainter();
    painter.addExtension(TextAreaPainter.HIGHEST_LAYER, highlighter);
    textArea.putClientProperty(PHPParserTextAreaExtension.class, highlighter);
  }   */

  public void handleMessage(EBMessage message) {
    /* if (message instanceof BufferUpdate) {
   // BufferUpdate bufferUpdate = (BufferUpdate) message;
   // Object what = bufferUpdate.getWhat();
    /*if (what == BufferUpdate.LOADED) {
      Buffer buffer = bufferUpdate.getBuffer();
      if ("php".equals(buffer.getMode().getName())) {
        buffer.setProperty("sidekick.parser", "PHPParser");
      }
    } else if (what == BufferUpdate.PROPERTIES_CHANGED) {
      Buffer buffer = bufferUpdate.getBuffer();
      if ("php".equals(buffer.getMode().getName())) {
        buffer.setProperty("sidekick.parser", "PHPParser");
      } else if ("PHPParser".equals(buffer.getProperty("sidekick.parser"))) {
        buffer.setProperty("sidekick.parser", null);
      }
    }*/
    /* } else */
  /*  if (message instanceof EditPaneUpdate) {
      handleEditPaneMessage((EditPaneUpdate) message);
    }   */
  }

 /* private static void handleEditPaneMessage(EditPaneUpdate message) {
    JEditTextArea textArea = message.getEditPane().getTextArea();
    Object what = message.getWhat();

    if (what == EditPaneUpdate.CREATED) {
      initTextArea(textArea);
    } else if (what == EditPaneUpdate.DESTROYED) {
      uninitTextArea(textArea);
    }
  }   */

  /**
   * show the dialog to find a class.
   *
   * @param view the jEdit's view
   */
  public static void findClass(View view) {
    findItem(view, FrameFindItem.CLASS_MODE, FrameFindItem.PROJECT_SCOPE);
  }

  /**
   * show the dialog to find a class.
   *
   * @param view the jEdit's view
   */
  public static void findInterface(View view) {
    findItem(view, FrameFindItem.INTERFACE_MODE, FrameFindItem.PROJECT_SCOPE);
  }

  /**
   * show the dialog to find a class.
   *
   * @param view the jEdit's view
   */
  public static void findClassOrInterface(View view) {
    findItem(view, FrameFindItem.CLASS_MODE ^ FrameFindItem.INTERFACE_MODE, FrameFindItem.PROJECT_SCOPE);
  }

  /**
   * show the dialog to find a method.
   *
   * @param view the jEdit's view
   */
  public static void findMethod(View view) {
    findItem(view, FrameFindItem.METHOD_MODE, FrameFindItem.PROJECT_SCOPE);
  }

  /**
   * Find any item in the current file.
   *
   * @param view the jEdit's view
   */
  public static void findInFile(View view) {
    findItem(view, FrameFindItem.ALL_MODE, FrameFindItem.FILE_SCOPE);
  }

  /**
   * Open the find item frame for the view in the given mode
   *
   * @param view  the view
   * @param mode  one of the following  {@link FrameFindItem#ALL_MODE}, {@link FrameFindItem#CLASS_MODE} or {@link
   *              FrameFindItem#METHOD_MODE}
   * @param scope the scope : {@link FrameFindItem#FILE_SCOPE} or {@link FrameFindItem#PROJECT_SCOPE}
   */
  private static void findItem(View view, int mode, int scope) {
    moveFindItemWindow(view);
    findItemWindow.init(view, mode, scope);
    GUIUtilities.centerOnScreen(findItemWindow);
    findItemWindow.setVisible(true);
  }

  private static void moveFindItemWindow(View view) {
    Dimension viewSize = view.getSize();
    Point locationOnScreen = view.getLocationOnScreen();
    Dimension findItemWindowsSize = findItemWindow.getSize();
    findItemWindow.setLocation(locationOnScreen.x + ((viewSize.width - findItemWindowsSize.width) >> 1),
                               locationOnScreen.y + ((viewSize.height - findItemWindowsSize.height) >> 1));
  }


  /* public static void pastePHP(Buffer buffer, JEditTextArea textArea) {
     if ("php".equals(buffer.getMode().getName())) {
       final String s = Registers.getRegister('$').toString();
       final DefaultTokenHandler tokenHandler = new DefaultTokenHandler();
       final int caretLine = textArea.getCaretLine();
       final int offsetInLine = textArea.getCaretPosition() - textArea.getLineStartOffset(caretLine);

       int caretPosition = textArea.getCaretPosition();
       ParserRuleSet rule = buffer.getRuleSetAtOffset(caretPosition);
       if (!"PHP_LITERAL".equals(rule.getSetName())) {
         rule = buffer.getRuleSetAtOffset(caretPosition + 1);
         if (!"PHP_LITERAL".equals(rule.getSetName())) {
           rule = buffer.getRuleSetAtOffset(caretPosition - 1);
           caretPosition = caretPosition - 2;
         }
       } else {
         caretPosition = caretPosition - 1;
       }
       Log.log(Log.DEBUG, PHPParserPlugin.class, "Rule :" + rule.getSetName());


       if ("PHP_LITERAL".equals(rule.getSetName())) {
         while (buffer.getRuleSetAtOffset(++caretPosition) == rule) {

         }
         rule = buffer.getRuleSetAtOffset(caretPosition);
         final ParserRule escapeRule = rule.getEscapeRule();
         char charForToken0 = textArea.getText(caretPosition - 1, 1).charAt(0);
         char charForToken = textArea.getText(caretPosition, 1).charAt(0);
         Log.log(Log.DEBUG, PHPParserPlugin.class, "Prev :" + charForToken0);
         Log.log(Log.DEBUG,
                 PHPParserPlugin.class,
                         "Escaping :" + charForToken + " caret : " + caretPosition + " rule : " + buffer.getRuleSetAtOffset(caretPosition).getSetName());
         String selectedText = escapeChar(s, escapeRule.hashChar, escapeRule.hashChar);
         selectedText = escapeChar(selectedText,charForToken,escapeRule.hashChar);
         textArea.setSelectedText(selectedText);
       } else {
         Registers.paste(textArea, '$');
       }
     } else {
       Registers.paste(textArea, '$');
     }
   }*/
  /*
private static char getLastTokenOfKind(DefaultTokenHandler tokenHandler,
                       Buffer buffer,
                       JEditTextArea textArea,
                       ParserRuleSet rule,
                       int line,
                       Token token) {
Token nextToken = token.next;
int tokenLine = line;
while (nextToken.rules == rule) {
token = nextToken;
nextToken = token.next;
tokenLine = line;
if (nextToken == null) { // end of line
buffer.markTokens(++line, tokenHandler);
nextToken = TextUtilities.getTokenAtOffset(tokenHandler.getTokens(), 0);
}
}
if (tokenLine != line) {
return textArea.getText(textArea.getLineStartOffset(tokenLine) + token.offset - 1, 1).charAt(0);
} else {
return textArea.getText(textArea.getLineStartOffset(tokenLine) + token.offset, 1).charAt(0);
}
}   */
  /*
   private static String escape(int length,
                                Token nextToken,
                                Buffer buffer,
                                int caretLine,
                                String s,
                                JEditTextArea textArea) {
     if (length == 1) {
       final int nextTokenOffset = nextToken.offset;
       final String lineText = buffer.getLineText(caretLine);
       if (nextTokenOffset < lineText.length()) {
         final char endStringChar = lineText.charAt(nextTokenOffset);
         if (endStringChar == '\'' || endStringChar == '"' || endStringChar == '`') {

           return escapeChar(s, endStringChar, escapeRule.hashChar);
         }
       }
     }
     return s;
   }     */
  /*
private static String escapeChar(String s, char charToEscape, char hashChar) {
int off = s.indexOf(charToEscape);
int oldPos = 0;
if (off != -1) {
final StringBuffer buff = new StringBuffer(s.length() << 1);
while (off != -1) {
  buff.append(s.substring(oldPos, off)).append(hashChar);
  oldPos = off;
  off = s.indexOf(charToEscape, oldPos + 1);
}
buff.append(s.substring(oldPos));
return buff.toString();
}
return s;
}   */
}
