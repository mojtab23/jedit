/*
 * Task.java - TaskList plugin
 * Copyright (C) 2001 Oliver Rutherfurd
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
 *
 * $Id$
 */

//{{{ imports
import javax.swing.Icon;
import javax.swing.text.*;
import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.util.Log;
//}}}

/**
 * A data object containing the attributes of a formatted comment
 * contained in a source file, along with an Icon represeting the
 * type of task.
 * @author Oliver Rutherfurd
 */
public class Task
{

	//{{{ constructor
	public Task(Buffer buffer, Icon icon, int line, String text,
		int startOffset, int endOffset)
	{
		// DEBUG: dump of object properties
		// Log.log(Log.DEBUG, Task.class,
		// 	"Task.Task(buffer=" + buffer + ",icon=" + icon + ",line=" + line +
		// 	",startOffset=" + startOffset + ",endOffset=" + endOffset +
		// 	",text=" + text + ")");//##

		this.buffer = buffer;
		this.icon = icon;
		this.lineIndex = line;
		this.text = text.replace('\t', (char)187);
		int posOffset = buffer.getLineStartOffset(line);
		this.startPosition = buffer.createPosition(posOffset + startOffset);
		this.endPosition = buffer.createPosition(posOffset + endOffset);
	}//}}}

	public Buffer getBuffer(){ return this.buffer; }
	public Icon getIcon(){ return this.icon; }
	public String getText(){ return this.text; }
	public int getLineIndex(){ return this.lineIndex; }

	//{{{ getStartOffset() method
	public int getStartOffset()
	{
		return startPosition.getOffset()
			- buffer.getLineStartOffset(getLineNumber());
	}//}}}

	//{{{ getEndOffset() method
	public int getEndOffset()
	{
		return endPosition.getOffset()
			- buffer.getLineStartOffset(getLineNumber());
	}//}}}

	//{{{ getStartPosition() method
	public Position getStartPosition()
	{
		return startPosition;
	}//}}}

	//{{{ getEndPosition() method
	public Position getEndPosition()
	{
		return endPosition;
	}//}}}

	//{{{ getLineNumber() method
	/**
	 * Returns the line number of the task, which takes into
	 * account the changes in the associtaed buffer
	 * @return The line number of the task as found in the associated buffer
	 */
	public int getLineNumber()
	{
		if(startPosition != null)
		{
			return buffer.getLineOfOffset(startPosition.getOffset());
		}
		else
		{
			return lineIndex;
		}
	}//}}}

	//{{{ toString() method
	/**
	 * Provides String representation of the object.
	 * @return A String containing the line number and text of the
	 * formatted comment.
	 */
	public String toString()
	{
		return "[" + this.getLineNumber() + "]" + this.text;
	}//}}}

	//{{{ private members
	private Buffer buffer;		// buffer task came from
	private Icon icon;			// icon associated with TaskType

	private String text;		// comment text

	private int startOffset;	// start of task
	private int endOffset;		// end of task

	private int lineIndex;		// line task is on

	private Position position;
	private Position startPosition;
	private Position endPosition;
	//}}}
}

// :collapseFolds=1:folding=explicit:indentSize=4:lineSeparator=\n:noTabs=false:tabSize=4:
