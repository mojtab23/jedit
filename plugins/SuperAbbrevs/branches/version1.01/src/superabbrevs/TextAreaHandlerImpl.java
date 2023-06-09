/*
 * AbbrevsHandler.java
 *
 * Created on 15. juni 2007, 23:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package superabbrevs;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.gjt.sp.jedit.bsh.EvalError;
import org.gjt.sp.jedit.bsh.ParseException;
import org.gjt.sp.jedit.bsh.TargetError;
import org.gjt.sp.jedit.textarea.Selection;

import superabbrevs.gui.scrollablepopupmenu.ScrollablePopupMenu;
import superabbrevs.gui.scrollablepopupmenu.ScrollablePopupMenuEvent;
import superabbrevs.gui.scrollablepopupmenu.ScrollablePopupMenuListner;
import superabbrevs.gui.searchdialog.SearchAcceptedListener;
import superabbrevs.gui.searchdialog.SearchDialog;
import superabbrevs.gui.searchdialog.SearchDialogModel;
import superabbrevs.model.Abbreviation;
import superabbrevs.utilities.Log;
import superabbrevs.utilities.Log.Level;
import trie.Match;

import com.google.inject.Inject;

/**
 *
 * @author Sune Simonsen
 */
public class TextAreaHandlerImpl implements TextAreaHandler {
    private TemplateHandlerImpl templateHandler;
	private final JEditInterface jedit;

    @Inject 
    public TextAreaHandlerImpl(JEditInterface jedit, TemplateHandlerImpl templateHandler) {
        this.jedit = jedit;
		this.templateHandler = templateHandler;
    }
    
    /* (non-Javadoc)
	 * @see superabbrevs.TextAreaHandler#getTextBeforeCaret()
	 */
    public String getTextBeforeCaret() {
        // the line number of the current line 
        int line = jedit.getCaretLine();
        // the start position of the current line in the full text  
        int lineStart = jedit.getLineStartOffset(line);
        // the offset of the caret in the full text 
        int caretPos = jedit.getCaretPosition();
        // the offset of the caret in the current line 
        int caretPosition = caretPos - lineStart;

        // the text on the current line
        String lineText = jedit.getLineText(line);
        
        return lineText.substring(0,caretPosition);
    }
    
    /* (non-Javadoc)
	 * @see superabbrevs.TextAreaHandler#getModeAtCursor()
	 */
    public String getModeAtCursor() {
        return jedit.getModeAtCursor();
    }
   
    /* (non-Javadoc)
	 * @see superabbrevs.TextAreaHandler#showAbbrevsPopup(java.util.LinkedList)
	 */
    public void showAbbrevsPopup(final Match<Abbreviation> match) {
    	List<Abbreviation> abbrevs = new ArrayList<Abbreviation>();
    	abbrevs.addAll(match.getElements());
        Collections.sort(abbrevs);
    	
        Point location = jedit.getPopUpLocation();
        
        ScrollablePopupMenu<Abbreviation> menu = 
                new ScrollablePopupMenu<Abbreviation>(jedit.getView(), location, abbrevs);
        
        menu.addActionListener(new ScrollablePopupMenuListner<Abbreviation>() {
            public void selectedMenuItem(ScrollablePopupMenuEvent<Abbreviation> event) {
                Abbreviation a = event.getSelectedObject();
                removeMatch(match.getMatchingText());
                expandAbbrev(a, false);
            }
        });
        
        menu.setVisible(true);
    }
    
    /* (non-Javadoc)
	 * @see superabbrevs.TextAreaHandler#removeAbbrev(superabbrevs.model.Abbrev)
	 */
    public void removeMatch(String match) {
        // the offset of the caret in the full text 
        int end = jedit.getCaretPosition();
        int start = end - match.length();
        jedit.setSelection(new Selection.Range(start, end));
        jedit.setSelectedText("");
    }
    
    /* (non-Javadoc)
	 * @see superabbrevs.TextAreaHandler#expandAbbrev(superabbrevs.model.Abbrev, boolean)
	 */
    public void expandAbbrev(Abbreviation abbrev, boolean invokedAsACommand) {
        try {
        	Log.log(Level.MESSAGE, TemplateHandlerImpl.class, 
        			String.format("Expanding Abbreviation: %s to %s", abbrev, abbrev.getExpansion()));
            templateHandler.expandAbbrev(abbrev, invokedAsACommand);
            Log.log(Level.MESSAGE, TemplateHandlerImpl.class, "Expanded Abbreviation");
        } catch (TargetError ex) {
            Log.log(Log.Level.ERROR, TemplateHandlerImpl.class, ex);
        } catch (ParseException ex) {
            Log.log(Log.Level.ERROR, TemplateHandlerImpl.class, ex);
        } catch (EvalError ex) {
            Log.log(Log.Level.ERROR, TemplateHandlerImpl.class, ex);
        } catch (IOException ex) {
            Log.log(Log.Level.ERROR, TemplateHandlerImpl.class, ex);
        }
    }
    
    /* (non-Javadoc)
	 * @see superabbrevs.TextAreaHandler#selectNextAbbrev()
	 */
    public boolean selectNextAbbrev() {
        return templateHandler.selectNextAbbrev();
    }
    
    /* (non-Javadoc)
	 * @see superabbrevs.TextAreaHandler#selectPrevAbbrev()
	 */
    public void selectPrevAbbrev() {
        templateHandler.selectPrevAbbrev();
    }
    
    /* (non-Javadoc)
	 * @see superabbrevs.TextAreaHandler#isInTemplateMode()
	 */
    public boolean isInTemplateMode()
    {
        return templateHandler.isInTempateMode();
    }

    /* (non-Javadoc)
	 * @see superabbrevs.TextAreaHandler#showSearchDialog(java.util.ArrayList)
	 */
    public void showSearchDialog(ArrayList<Abbreviation> abbrevs) {
        SearchDialogModel model = new SearchDialogModel(abbrevs);
        SearchDialog dialog = new SearchDialog(jedit.getView(), "Search for abbreviation", 
                false, model);
        dialog.setLocationRelativeTo(jedit.getView());
        dialog.addSearchAcceptedListener(new SearchAcceptedListener() {
            public void accepted(Object o) {
                Abbreviation a = (Abbreviation)o;
                expandAbbrev(a, true);
            }
        });
        dialog.setVisible(true);
    }

	public void stopTemplateMode() {
		templateHandler.stopTemplateMode();
	}
}
