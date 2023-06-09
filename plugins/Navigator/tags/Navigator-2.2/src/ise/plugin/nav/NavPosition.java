/*
Copyright (c) 2002, Dale Anson
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.
* Neither the name of the author nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package ise.plugin.nav;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EditPane;
import org.gjt.sp.jedit.jEdit;

/**
 * This object is used to track caret positions from PositionChanging events from the EditBus.
 *
 * @author Dale Anson
 */
public class NavPosition {

    // hashCode of EditPane, used to identify specific EditPanes
    public int editPane = 0;

    // path of file in Buffer
    public String path = null;

    // filename of buffer
    public String name = null;

    // caret position
    public int caret = 0;

    // line number of caret line
    public int lineno = 0;

    // text of the caret line, used for back and forward list display
    public String linetext = "";

    /**
     * @param view the View containing the editPane
     * @param editPane the EditPane containing the text area that is displaying the buffer
     * @param buffer the buffer
     * @param caretPosition the position of the caret within the text area containing the buffer
     * @param the text of the caret line
     */
    public NavPosition(EditPane editPane, Buffer buffer, int caretPosition, String linetext) {
        if (editPane == null) {
            throw new IllegalArgumentException("editPane cannot be null");
        }
        if (buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null");
        }
        if (caretPosition < 0) {
            throw new IllegalArgumentException("caret position cannot less than 0");
        }
        this.editPane = editPane.hashCode();
        path = buffer.getPath();
        name = buffer.getName();
        caret = caretPosition;
        lineno = buffer.getLineOfOffset(caret);
        this.linetext = linetext == null ? "" : linetext;
    }

    public NavPosition(NavPosition other) {
        editPane = other.editPane;
        path = other.path;
        name = other.name;
        caret = other.caret;
        lineno = other.lineno;
        linetext = other.linetext;
    }

    /**
     * Two NavPositions are equal to each other if they have the same
     * path, line number, and caret position.
     */
    @Override
    public boolean equals(Object obj) {
        // check for reference equality
        if (this == obj) {
            return true;
        }

        // type check
        if (! (obj instanceof NavPosition)) {
            return false;
        }

        // cast to correct type
        NavPosition other = (NavPosition) obj;

        // check fields
        boolean groupByLine = jEdit.getBooleanProperty("navigator.groupByLine", false);
        if (groupByLine) {
            return (path.equalsIgnoreCase(other.path) && lineno == other.lineno);
        }
        return (path.equalsIgnoreCase(other.path) && lineno == other.lineno && caret == other.caret);
    }

    @Override
    public int hashCode() {
        return (path + lineno + caret).hashCode();
    }

    // kind of a cheat here -- I want to use these as the objects in a JComboBox,
    // and I want the editor to be a NumberTextField.  This lets me do that.
    @Override
    public String toString() {
        return String.valueOf(lineno + 1);
    }

    public String plainText() {
        boolean showPath = jEdit.getBooleanProperty("navigator.showPath", true);
        boolean showLineNumber = jEdit.getBooleanProperty("navigator.showLineNumber", true);
        boolean showCaretOffset = jEdit.getBooleanProperty("navigator.showCaretOffset", true);
        StringBuilder sb = new StringBuilder();
        sb.append(showPath ? path : name);
        if (showLineNumber) {
            // lineno is 0-based, but gutter lines are 1-based, so add one
            sb.append(":").append(lineno + 1);
        }
        if (showCaretOffset) {
            sb.append(":").append(caret);
        }
        return sb.toString();
    }

    /**
     * @return an HTML representation of this position for display in the
     * back and forward popup lists.
     */
    public String htmlText() {
        return htmlText(Integer.MAX_VALUE);
    }

    /**
     * @param maxLineLength The maximum number of characters of the line text to display.
     * @return an HTML representation of this position for display in the
     * back and forward popup lists.
     */
    public String htmlText(int maxLineLength) {
        boolean showPath = jEdit.getBooleanProperty("navigator.showPath", true);
        boolean showLineNumber = jEdit.getBooleanProperty("navigator.showLineNumber", true);
        boolean showCaretOffset = jEdit.getBooleanProperty("navigator.showCaretOffset", true);
        boolean showLineText = jEdit.getBooleanProperty("navigator.showLineText", false);

        StringBuilder sb = new StringBuilder();
        sb.append("<html><tt>");
        sb.append(showPath ? path : name);
        if (showLineNumber) {
            // lineno is 0-based, but gutter lines are 1-based, so add one
            sb.append(":").append(lineno + 1);
        }
        if (showCaretOffset) {
            sb.append(":").append(caret);
        }
        sb.append("</tt>");
        if (showLineText && linetext != null) {
            // might need to escape the line text as it might already be html
            String text = linetext;
            text = text.trim();
            text = text.replaceAll("[<]", "&lt;");
            text = text.replaceAll("[>]", "&gt;");
            if (text.length() > maxLineLength) {
                text = text.substring(0, maxLineLength);   
            }
            sb.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(text);
        }
        return sb.toString();
    }
}