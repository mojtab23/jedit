/*
 * HTMLStyle.java
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

import java.awt.Color;

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.syntax.*;

import org.gjt.sp.util.Log;

public class HTMLStyle
{
	public static String toCSS(SyntaxStyle style) {
		if (style == null) {
			Log.log(Log.DEBUG, HTMLStyle.class, 
					"toCSS(SyntaxStyle style): null style");
			return "";
		}
		StringBuffer buf = new StringBuffer();

		Color c;		
		if ((c = style.getBackgroundColor()) != null) {
			buf.append("background: ")
				.append(GUIUtilities.getColorHexString(c))
				.append(";\n");
		}

		if ((c = style.getForegroundColor()) != null) {
			buf.append("color: ")
				.append(GUIUtilities.getColorHexString(c))
				.append(";\n");
		}

		if (style.isBold()) { 
			buf.append("font-weight: bold;\n");
		}
		
		if (style.isItalic()) {
			buf.append("font-style: italic;\n");
		}
		
		return buf.toString();
	}

	public static String toHTML(SyntaxStyle style, String text) {
		if (style == null) {
			Log.log(Log.DEBUG, HTMLStyle.class, 
					"toCSS(SyntaxStyle style): null style");
			return text;
		}
		StringBuffer bufOpen  = new StringBuffer();
		StringBuffer bufClose = new StringBuffer();

		Color c;
		/*
		if ((c = style.getBackgroundColor()) != null) {
			bufOpen.append("<FONT")
				.append(" BGCOLOR=\"")
				.append(GUIUtilities.getColorHexString(c))
				.append("\">");
			bufClose.insert(0, "</FONT>");
		}
		*/

		if ((c = style.getForegroundColor()) != null) {
			bufOpen.append("<FONT")
				.append(" COLOR=\"")
				.append(GUIUtilities.getColorHexString(c))
				.append("\">");
			bufClose.insert(0, "</FONT>");
		}

		if (style.isBold()) { 
			bufOpen.append("<STRONG>");
			bufClose.insert(0, "</STRONG>");
		}
		
		if (style.isItalic()) {
			bufOpen.append("<EM>");
			bufClose.insert(0, "</EM>");
		}
		
		StringBuffer buf = new StringBuffer();
		buf.append(bufOpen).append(text).append(bufClose);
		
		return buf.toString();
	}
	
	public static String toSpan(int styleId, String text) {
		StringBuffer buf = new StringBuffer();
		buf.append("<SPAN CLASS=\"syntax" + styleId + "\">")
			.append(text)
			.append("</SPAN>");
		return buf.toString();
	}
	
	public static String toCSS(SyntaxStyle[] styles) {
		StringBuffer buf = new StringBuffer();
		
		for (int i = 0; i < styles.length; i++) {
			buf.append(".syntax" + i + " {\n")
				.append(toCSS(styles[i]))
				.append("}\n");
		}
		
		return buf.toString();
	}
}
