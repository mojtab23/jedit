/*
 * TextToolsPlugin.java - Plugin for a number of text related functions
 * Copyright (C) 1999 mike dillon
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

import java.util.Vector;
import javax.swing.JMenu;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.textarea.JEditTextArea;

public class TextToolsPlugin extends EditPlugin
{
	public void createMenuItems(Vector menuItems)
	{
		menuItems.addElement(GUIUtilities.loadMenu("text-tools"));
	}

	public static void sortLines(JEditTextArea textArea, boolean reverse)
	{
		if(!textArea.isEditable())
		{
			textArea.getToolkit().beep();
			return;
		}

		Buffer b = textArea.getBuffer();
		b.beginCompoundEdit();
		if(textArea.getSelectionEnd() != textArea.getSelectionStart())
		{
			sortLines(b, textArea.getSelectionStart(),
				textArea.getSelectionEnd()
				- textArea.getSelectionStart(), reverse);
		}
		else
		{
			sortLines(b, reverse);
		}
		b.endCompoundEdit();
	}

	public static void sortLines(Document d, boolean reverse)
	{
		sortLines(d, 0, d.getLength(), reverse);
	}

	public static void sortLines(Document d, int offset, int length, boolean reverse)
	{
		Element root = d.getDefaultRootElement();
		Element lineElement;

		int fromIndex = root.getElementIndex(offset);
		int toIndex = root.getElementIndex(offset + length);

		String[] lines = new String[toIndex - fromIndex + 1];

		try
		{
			for (int i = 0; i < lines.length; i++)
			{
				lineElement = root.getElement(fromIndex + i);
				lines[i] = d.getText(lineElement.getStartOffset(),
					lineElement.getEndOffset()
					- lineElement.getStartOffset() - 1);
			}

			MiscUtilities.Compare compare = new MiscUtilities.StringCompare();
			if(reverse)
				compare = new ReverseCompare(compare);

			MiscUtilities.quicksort(lines,compare);

			StringBuffer buf = new StringBuffer();

			for(int i = 0; i < lines.length - 1; i++)
			{
				buf.append(lines[i]);
				buf.append('\n');
			}
			buf.append(lines[lines.length - 1]);

			int selStart = root.getElement(fromIndex).getStartOffset();
			int selLength = root.getElement(toIndex).getEndOffset() - selStart - 1;

			d.remove(selStart,selLength);
			d.insertString(selStart,buf.toString(),null);
		}
		catch (BadLocationException ble)
		{
			ble.printStackTrace();
		}
	}

	/**
	 * A wrapper that reverses a sort.
	 */
	static class ReverseCompare implements MiscUtilities.Compare
	{
		private MiscUtilities.Compare comp;

		ReverseCompare(MiscUtilities.Compare comp)
		{
			this.comp = comp;
		}

		public int compare(Object obj1, Object obj2)
		{
			return comp.compare(obj2,obj1);
		}
	}

	public static void transposeChars(JEditTextArea textArea)
	{
		int line = textArea.getCaretLine();

		if (!textArea.isEditable() || textArea.getLineLength(line) < 2)
		{
			textArea.getToolkit().beep();
			return;
		}

		int start, caret;
		start = caret = textArea.getCaretPosition();

		if (caret == textArea.getLineStartOffset(line))
		{
			// caret is before the first character in the line,
			// so move it forward one character
			caret++;
		}
		else if (caret == textArea.getLineEndOffset(line) - 1)
		{
			// caret is after the last character in the line,
			// so move it back one character
			caret--;
		}

		Buffer b = textArea.getBuffer();

		b.beginCompoundEdit();

		try
		{
			String pair = b.getText(caret - 1, 2);
			String trans = new String(new char[] { pair.charAt(1),
				pair.charAt(0) });
			b.remove(caret - 1, 2);
			b.insertString(caret - 1, trans, null);
		}
		catch (BadLocationException ble)
		{
			// shouldn't happen ;)
			ble.printStackTrace();
		}

		// put the caret back where it was before the transposition
		textArea.setCaretPosition(start);

		b.endCompoundEdit();
	}

	public static void transposeWords(JEditTextArea textArea)
	{
		int line = textArea.getCaretLine();

		if(!textArea.isEditable() || textArea.getLineLength(line) == 0)
		{
			textArea.getToolkit().beep();
			return;
		}

		int lineStart = textArea.getLineStartOffset(line);
		int offset = textArea.getCaretPosition() - lineStart;

		Buffer buffer = textArea.getBuffer();

		String lineText = textArea.getLineText(line);
		String noWordSep = (String)buffer.getProperty("noWordSep");

		if(offset == lineText.length()) offset--;

		int wordStart = TextUtilities.findWordStart(lineText, offset,
			noWordSep);
		int wordEnd = TextUtilities.findWordEnd(lineText, offset + 1,
			noWordSep);

		// only one "word" in this line, so do nothing
		if (wordStart == 0 && wordEnd == lineText.length()) return;

		boolean reverseBias = false;

		// figure out whether a word or a non-word was found
		boolean word = isWordChar(lineText.charAt(wordStart), noWordSep)
			|| (reverseBias = wordStart == offset);

		int word1Start, word1End, word2Start, word2End;

		word1Start = word1End = word2Start = word2End = 0;

		if (word)
		{
			if (wordStart == 0)
			{
				// search forward to find the next word
				word1Start = wordStart;
				word1End = wordEnd;

				// find the end of the non-word span after
				// word1, if it's the end of the line, return;
				// otherwise, it is the beginning of word2
				word2Start = TextUtilities.findWordEnd(
					lineText, word1End + 1, noWordSep);

				if (word2Start == lineText.length()) return;

				word2End = TextUtilities.findWordEnd(
					lineText, word2Start + 1, noWordSep);
			}
			else if (wordEnd == lineText.length())
			{
				// search backward to find the previous word
				word2Start = wordStart;
				word2End = wordEnd;

				// find the start of the non-word span before
				// word2, if it's the start of the line, return;
				// otherwise, it is the end of word1
				word1End = TextUtilities.findWordStart(
					lineText, word2Start - 1, noWordSep);

				if (word1End == 0) return;

				word1Start = TextUtilities.findWordStart(
					lineText, word1End - 1, noWordSep);
			}
			else if (reverseBias)
			{
				// search backward to find the last two words
				word2Start = TextUtilities.findWordStart(
					lineText, wordStart - 1, noWordSep);

				// only one word in the line so do nothing
				if (word2Start == 0) return;

				word2End = wordStart;

				// find the start of the non-word span before
				// word2, if it's the start of the line, return;
				// otherwise, it is the end of word1
				word1End = TextUtilities.findWordStart(
					lineText, word2Start - 1, noWordSep);

				if (word1End == 0) return;

				word1Start = TextUtilities.findWordStart(
					lineText, word1End - 1, noWordSep);
			}
			else
			{
				word2Start = wordStart;
				word2End = wordEnd;

				// find the start of the non-word span before
				// word2, if it's the start of the line, return;
				// otherwise, it is the end of word1
				word1End = TextUtilities.findWordStart(
					lineText, word2Start - 1, noWordSep);

				if (word1End == 0) return;

				word1Start = TextUtilities.findWordStart(
					lineText, word1End - 1, noWordSep);
			}
		}
		else
		{
			if (wordStart == 0)
			{
				// search forward to find the first two words
				word1End = TextUtilities.findWordEnd(
					lineText, wordEnd + 1, noWordSep);

				// only one word in the line so do nothing
				if (word1End == lineText.length()) return;

				word1Start = wordEnd;

				// find the end of the non-word span after
				// word1, if it's the end of the line, return;
				// otherwise, it is the beginning of word2
				word2Start = TextUtilities.findWordEnd(
					lineText, word1End + 1, noWordSep);

				if (word2Start == lineText.length()) return;

				word2End = TextUtilities.findWordEnd(
					lineText, word2Start + 1, noWordSep);
			}
			else if (wordEnd == lineText.length())
			{
				// search backward to find the last two words
				word2Start = TextUtilities.findWordStart(
					lineText, wordStart - 1, noWordSep);

				// only one word in the line so do nothing
				if (word2Start == 0) return;

				word2End = wordStart;

				// find the start of the non-word span before
				// word2, if it's the start of the line, return;
				// otherwise, it is the end of word1
				word1End = TextUtilities.findWordStart(
					lineText, word2Start - 1, noWordSep);

				if (word1End == 0) return;

				word1Start = TextUtilities.findWordStart(
					lineText, word1End - 1, noWordSep);
			}
			else
			{
				// find the two words on either side
				word1Start = TextUtilities.findWordStart(
					lineText, wordStart - 1, noWordSep);
				word1End = TextUtilities.findWordEnd(
					lineText, wordStart, noWordSep);

				word2Start = TextUtilities.findWordStart(
					lineText, wordEnd, noWordSep);
				word2End = TextUtilities.findWordEnd(
					lineText, wordEnd + 1, noWordSep);
			}
		}

		StringBuffer buf = new StringBuffer();

		buf.append(lineText.substring(word2Start, word2End));
		buf.append(lineText.substring(word1End, word2Start));
		buf.append(lineText.substring(word1Start, word1End));

		buffer.beginCompoundEdit();

		try
		{
			buffer.remove(lineStart + word1Start,
				word2End - word1Start);
			buffer.insertString(lineStart + word1Start,
				buf.toString(), null);
		}
		catch (BadLocationException ble)
		{
			ble.printStackTrace();
		}

		textArea.setCaretPosition(lineStart + word2End);

		buffer.endCompoundEdit();
	}

	public static void transposeLines(JEditTextArea textArea)
	{
		if (!textArea.isEditable() || textArea.getLineCount() < 2)
		{
			textArea.getToolkit().beep();
			return;
		}

		int line = textArea.getCaretLine();

		if (line == 0)
		{
			// this is the first line, so move forward one line
			line++;
		}

		int start = textArea.getLineStartOffset(line - 1);
		int end = textArea.getLineEndOffset(line);

		StringBuffer buf = new StringBuffer();

		buf.append(textArea.getLineText(line) + "\n");
		buf.append(textArea.getLineText(line - 1) + "\n");

		Buffer b = textArea.getBuffer();

		b.beginCompoundEdit();

		try
		{
			b.remove(start, end - start);
			b.insertString(start, buf.toString(), null);
		}
		catch (BadLocationException ble)
		{
			// shouldn't happen ;)
			ble.printStackTrace();
		}

		// put the caret at the end of the last line transposed
		textArea.setCaretPosition(end - 1);

		b.endCompoundEdit();
	}

	// private members
	private static final boolean isWordChar(char ch, String noWordSep)
	{
		return Character.isLetterOrDigit(ch) ||
			(noWordSep != null && noWordSep.indexOf(ch) != -1);
	}
}
