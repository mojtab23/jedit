/*
 * WhiteSpacePlugin.java
 * Copyright (c) 2000 Andre Kaplan
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


package whitespace;


import java.util.Vector;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EBMessage;
import org.gjt.sp.jedit.EBPlugin;
import org.gjt.sp.jedit.EditPane;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.OptionGroup;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.OptionsDialog;
import org.gjt.sp.jedit.msg.BufferUpdate;
import org.gjt.sp.jedit.msg.EditPaneUpdate;
import org.gjt.sp.jedit.msg.PropertiesChanged;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.jedit.textarea.TextAreaHighlight;
import org.gjt.sp.jedit.textarea.TextAreaPainter;
import org.gjt.sp.util.Log;

import whitespace.options.FoldOptionPane;
import whitespace.options.OnSaveOptionPane;
import whitespace.options.ParagraphOptionPane;
import whitespace.options.SpaceOptionPane;
import whitespace.options.TabOptionPane;
import whitespace.options.WhiteSpaceOptionPane;


public class WhiteSpacePlugin
    extends EBPlugin
{
    public void start() {}


    public void stop() {}


    public void createMenuItems(Vector menuItems) {
        menuItems.addElement(GUIUtilities.loadMenu("white-space-menu"));
    }


    public void createOptionPanes(OptionsDialog dialog) {
        OptionGroup group = new OptionGroup("WhiteSpace");

        group.addOptionPane(new SpaceOptionPane());
        group.addOptionPane(new TabOptionPane());
        group.addOptionPane(new WhiteSpaceOptionPane());
        group.addOptionPane(new FoldOptionPane());
        group.addOptionPane(new ParagraphOptionPane());
        group.addOptionPane(new OnSaveOptionPane());

        dialog.addOptionGroup(group);
    }


    // EditBus
    public void handleMessage(EBMessage message) {
        if (message instanceof EditPaneUpdate) {
            EditPaneUpdate epu = (EditPaneUpdate) message;
            EditPane editPane = (EditPane) epu.getSource();
            View view = editPane.getView();
            if (epu.getWhat() == EditPaneUpdate.CREATED) {
                this.editPaneCreated(view, editPane);
            } else if (epu.getWhat() == EditPaneUpdate.DESTROYED) {
                BlockHighlight.removeHighlightFrom(editPane);
                WhiteSpaceHighlight.removeHighlightFrom(editPane);
                FoldHighlight.removeHighlightFrom(editPane);
            }
        } else if (message instanceof PropertiesChanged) {
            BlockHighlight.propertiesChanged();
            FoldHighlight.propertiesChanged();
            WhiteSpaceHighlight.propertiesChanged();
        } else if (message instanceof BufferUpdate) {
            BufferUpdate bu = (BufferUpdate) message;
            if (bu.getWhat() == BufferUpdate.SAVING) {
                this.bufferSaving(bu.getBuffer());
            }
        }
    }


    private void editPaneCreated(View view, EditPane editPane) {
        JEditTextArea textArea = editPane.getTextArea();
        TextAreaPainter textAreaPainter = textArea.getPainter();

        boolean showSpaceDefault         = jEdit.getBooleanProperty(
            "white-space.show-space-default", true
        );
        boolean showLeadingSpaceDefault  = jEdit.getBooleanProperty(
            "white-space.show-leading-space-default", true
        );
        boolean showInnerSpaceDefault    = jEdit.getBooleanProperty(
            "white-space.show-inner-space-default", true
        );
        boolean showTrailingSpaceDefault = jEdit.getBooleanProperty(
            "white-space.show-trailing-space-default", true
        );

        boolean showTabDefault         = jEdit.getBooleanProperty(
            "white-space.show-tab-default", true
        );
        boolean showLeadingTabDefault  = jEdit.getBooleanProperty(
            "white-space.show-leading-tab-default", true
        );
        boolean showInnerTabDefault    = jEdit.getBooleanProperty(
            "white-space.show-inner-tab-default", true
        );
        boolean showTrailingTabDefault = jEdit.getBooleanProperty(
            "white-space.show-trailing-tab-default", true
        );

        boolean showWhitespaceDefault  = jEdit.getBooleanProperty(
            "white-space.show-whitespace-default", false
        );
        boolean showBlockDefault       = jEdit.getBooleanProperty(
            "white-space.show-block-default", false
        );
        boolean showFoldDefault        = jEdit.getBooleanProperty(
            "white-space.show-fold-default", true
        );
        boolean showFoldTooltipDefault = jEdit.getBooleanProperty(
            "white-space.show-fold-tooltip-default", true
        );

        BlockHighlight blockHighlight =
            (BlockHighlight) BlockHighlight.addHighlightTo(editPane);
        FoldHighlight foldHighlight =
            (FoldHighlight) FoldHighlight.addHighlightTo(editPane);
        WhiteSpaceHighlight whiteSpaceHighlight =
            (WhiteSpaceHighlight) WhiteSpaceHighlight.addHighlightTo(editPane);

        // Drawn third
        textAreaPainter.addCustomHighlight(blockHighlight);
        // Drawn second: whitespace highlights must be drawn after fold guides
        textAreaPainter.addCustomHighlight(whiteSpaceHighlight);
        // Drawn first
        textAreaPainter.addCustomHighlight(foldHighlight);

        blockHighlight.setEnabled(showBlockDefault);
        foldHighlight.setHighlightEnabled(showFoldDefault);
        foldHighlight.setTooltipEnabled(showFoldTooltipDefault);

        whiteSpaceHighlight.getSpaceHighlight().setEnabled(showSpaceDefault);
        whiteSpaceHighlight.getLeadingSpaceHighlight().setEnabled(showLeadingSpaceDefault);
        whiteSpaceHighlight.getInnerSpaceHighlight().setEnabled(showInnerSpaceDefault);
        whiteSpaceHighlight.getTrailingSpaceHighlight().setEnabled(showTrailingSpaceDefault);

        whiteSpaceHighlight.getTabHighlight().setEnabled(showTabDefault);
        whiteSpaceHighlight.getLeadingTabHighlight().setEnabled(showLeadingTabDefault);
        whiteSpaceHighlight.getInnerTabHighlight().setEnabled(showInnerTabDefault);
        whiteSpaceHighlight.getTrailingTabHighlight().setEnabled(showTrailingTabDefault);

        whiteSpaceHighlight.getWhitespaceHighlight().setEnabled(showWhitespaceDefault);
    }


    private void bufferSaving(Buffer buffer) {
        boolean removeTrailingWhitespace    = jEdit.getBooleanProperty(
            "white-space.remove-trailing-white-space", false
        );
        boolean softTabifyLeadingWhitespace = jEdit.getBooleanProperty(
            "white-space.soft-tabify-leading-white-space", false
        );

        boolean tabifyLeadingWhitespace   = false;
        boolean untabifyLeadingWhitespace = false;
        if (softTabifyLeadingWhitespace) {
            boolean noTabs = buffer.getBooleanProperty("noTabs");
            tabifyLeadingWhitespace   = !noTabs;
            untabifyLeadingWhitespace = noTabs;
        } else {
            tabifyLeadingWhitespace   = jEdit.getBooleanProperty(
                "white-space.tabify-leading-white-space", false
            );
            untabifyLeadingWhitespace = jEdit.getBooleanProperty(
                "white-space.untabify-leading-white-space", false
            );
        }

        if (    !removeTrailingWhitespace
             && !tabifyLeadingWhitespace
             && !untabifyLeadingWhitespace
        ) {
            return;
        }

        long start = System.currentTimeMillis();
        buffer.beginCompoundEdit();

        if (removeTrailingWhitespace) {
            // Escape characters
            String escapeChars = jEdit.getProperty("white-space.escape-chars", "");
            DocumentUtilities.removeTrailingWhiteSpace(buffer, escapeChars);
        }

        if (tabifyLeadingWhitespace != untabifyLeadingWhitespace) {
            if (tabifyLeadingWhitespace) {
                DocumentUtilities.tabifyLeading(buffer, buffer.getTabSize());
            }

            if (untabifyLeadingWhitespace) {
                DocumentUtilities.untabifyLeading(buffer, buffer.getTabSize());
            }
        }

        buffer.endCompoundEdit();
        long end = System.currentTimeMillis();
        Log.log(Log.DEBUG, this, "bufferSaving: " + (end - start) + " ms");
    }
}

