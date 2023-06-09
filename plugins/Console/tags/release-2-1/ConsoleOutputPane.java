/*
 * ConsoleOutputPane.java - Output-only tab
 * Copyright (C) 2000 Slava Pestov
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

import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;
import org.gjt.sp.jedit.Output;
import org.gjt.sp.jedit.View;
import org.gjt.sp.util.Log;

public class ConsoleOutputPane extends JPanel implements Output
{
	public ConsoleOutputPane(ConsoleFrame console)
	{
		super(new BorderLayout());

		this.console = console;

		output = new JTextPane();
		propertiesChanged();

		add(BorderLayout.CENTER,new JScrollPane(output));
	}

	public void printPlain(String msg)
	{
		addOutput(console.plainColor,msg);
	}

	public void printInfo(String msg)
	{
		addOutput(console.infoColor,msg);
	}

	public void printWarning(String msg)
	{
		addOutput(console.warningColor,msg);
	}

	public void printError(String msg)
	{
		addOutput(console.errorColor,msg);
	}

	public View getView()
	{
		return console.getView();
	}

	public void clear()
	{
		output.setText("");
	}

	// package-private members
	void propertiesChanged()
	{
		output.setBackground(console.bgColor);
		output.setFont(console.defaultFont);
	}

	// private members
	private ConsoleFrame console;
	private JTextPane output;

	private synchronized void addOutput(Color color, String msg)
	{
		Document outputDocument = output.getDocument();

		SimpleAttributeSet style = new SimpleAttributeSet();
		style.addAttribute(StyleConstants.Foreground,color);

		try
		{
			outputDocument.insertString(outputDocument.getLength(),
				msg,style);
			outputDocument.insertString(outputDocument.getLength(),
				"\n",null);
		}
		catch(BadLocationException bl)
		{
			Log.log(Log.ERROR,this,bl);
		}

		output.setCaretPosition(outputDocument.getLength());
	}
}
