/*
 * DiffGlobalOverview.java
 * Copyright (C) 2000 Andre Kaplan
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


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import jdiff.util.Diff;

import org.gjt.sp.jedit.textarea.JEditTextArea;

import org.gjt.sp.util.Log;


public class DiffGlobalOverview extends DiffOverview
{
    public DiffGlobalOverview(
        Diff.change edits,
        int lineCount0,
        int lineCount1,
        JEditTextArea textArea0,
        JEditTextArea textArea1
    ) {
        super(edits, lineCount0, lineCount1, textArea0, textArea1);
    }


    public void paint(Graphics gfx) {
        Rectangle size = getBounds();

        gfx.setColor(getBackground());
        gfx.fillRect(0, 0, size.width, size.height);

        Rectangle inner = new Rectangle(4, 4, size.width - 8, size.height - 8);

        int lines = Math.max(this.lineCount0, this.lineCount1);
        double pxlPerLine = ((double) inner.height) / lines;

        Rectangle left = new Rectangle(
            inner.x,
            inner.y,
            inner.width / 3,
            Math.max(1, (int) Math.round(pxlPerLine * this.lineCount0))
        );
        Rectangle right = new Rectangle(
            inner.x + (inner.width - left.width),
            inner.y,
            left.width,
            Math.max(1, (int) Math.round(pxlPerLine * this.lineCount1))
        );

        Rectangle cursor = new Rectangle(inner.x + inner.width / 2 - 1, inner.y,
            2, 0);

        Color leftColor  = JDiffPlugin.invalidHunkColor;
        Color rightColor = JDiffPlugin.invalidHunkColor;

        gfx.setColor(Color.black);
        gfx.drawRect(left.x - 1, left.y - 1, left.width + 1, left.height + 1);
        gfx.drawRect(right.x - 1, right.y - 1, right.width + 1, right.height + 1);

        gfx.setColor(Color.white);
        gfx.fillRect(left.x, left.y, left.width, left.height);
        gfx.fillRect(right.x, right.y, right.width, right.height);

        Diff.change hunk = this.edits;

        int leftOffset  = 0;
        int rightOffset = 0;
        for (; hunk != null; hunk = hunk.link) {
            leftOffset  = hunk.line0;
            rightOffset = hunk.line1;

            if (hunk.inserted == 0 && hunk.deleted != 0) { // DELETE
               leftColor  = JDiffPlugin.deletedHunkColor;
               rightColor = JDiffPlugin.invalidHunkColor;
            } else if (hunk.inserted != 0 && hunk.deleted == 0) { // INSERT
               leftColor  = JDiffPlugin.invalidHunkColor;
               rightColor = JDiffPlugin.insertedHunkColor;
            } else { // CHANGE
               leftColor  = JDiffPlugin.changedHunkColor;
               rightColor = JDiffPlugin.changedHunkColor;
            }

            left.y  = inner.y + (int) Math.round(leftOffset * pxlPerLine);
            right.y = inner.y + (int) Math.round(rightOffset * pxlPerLine);
            left.height  = Math.max(1, (int) Math.round(hunk.deleted * pxlPerLine));
            right.height = Math.max(1, (int) Math.round(hunk.inserted * pxlPerLine));
            gfx.setColor(leftColor);
            gfx.fillRect(left.x, left.y, left.width, left.height);
            gfx.setColor(rightColor);
            gfx.fillRect(right.x, right.y, right.width, right.height);

            gfx.setColor(Color.black);
            gfx.drawLine(left.x + left.width + 1, left.y, right.x - 1, right.y);
        }

        // Display the textArea cursor
        this.paintCursor(gfx);
    }


    public void paintCursor(Graphics gfx) {
        Rectangle size = getBounds();

        Rectangle inner = new Rectangle(4, 4, size.width - 8, size.height - 8);

        int lines = Math.max(this.lineCount0, this.lineCount1);
        double pxlPerLine = ((double) inner.height) / lines;

        Rectangle leftCursor = new Rectangle(
            inner.x, inner.y + ((int) Math.round(pxlPerLine * this.textArea0.getFirstLine())),
            inner.width / 3,
            Math.max(1, (int) Math.round(pxlPerLine * Math.min(this.lineCount0, this.textArea0.getVisibleLines())))
        );

        Rectangle rightCursor = new Rectangle(
            inner.x + (inner.width - leftCursor.width),
            inner.y + ((int) Math.round(pxlPerLine * this.textArea1.getFirstLine())),
            leftCursor.width,
            Math.max(1, (int) Math.round(pxlPerLine * Math.min(this.lineCount1, this.textArea1.getVisibleLines())))
        );

        gfx.setColor(JDiffPlugin.leftCursorColor);
        gfx.drawRect(leftCursor.x, leftCursor.y, leftCursor.width - 1, leftCursor.height - 1);
        gfx.setColor(JDiffPlugin.rightCursorColor);
        gfx.drawRect(rightCursor.x, rightCursor.y, rightCursor.width - 1, rightCursor.height - 1);
    }
}
