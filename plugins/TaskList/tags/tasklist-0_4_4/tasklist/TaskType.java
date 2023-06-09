/*
 * TaskType.java - TaskList plugin
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

package tasklist;

//{{{ imports
import java.net.URL;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.util.Log;
import gnu.regexp.*;
//}}}

public class TaskType
{
	public static final RESyntax RE_SYNTAX = new RESyntax(
		RESyntax.RE_SYNTAX_PERL5).set(
			RESyntax.RE_CHAR_CLASSES);

	//{{{ default constructors
	public TaskType()
	{
		setName("");
		setPattern("");
		setSample("");
		setIgnoreCase(false);
		setIconPath("Exclamation.gif");
	}

	public TaskType(String name, String pattern, String sample,
		boolean ignoreCase, String iconPath)
	{
		setName(name);
		setPattern(pattern);
		setSample(sample);
		setIgnoreCase(ignoreCase);
		setIconPath(iconPath);	// will attempt to load icon too

		compileRE();
	}//}}}

	//{{{ extractTask() method
	public Task extractTask(Buffer buffer, String tokenText,
		int line, int tokenOffset)
	{
		REMatch match = this.re.getMatch(tokenText);

		if(match == null)
			return null;

		int start = (displayIdentifier == true ? match.getStartIndex(1) :
			match.getStartIndex(2));
		int end = match.getEndIndex(2);

		/*
		Log.log(Log.DEBUG,this,"Task '" + this.name + "' found");
		Log.log(Log.DEBUG,this,"re: " + re.toString());
		Log.log(Log.DEBUG,this,"on: " + tokenText);
		Log.log(Log.DEBUG,this,"Match = `" + match.toString() + "`");
		Log.log(Log.DEBUG,this,"start = " + start);
		Log.log(Log.DEBUG,this,"end = " + end);
		Log.log(Log.DEBUG,this,"buffer = " + buffer.toString());
		Log.log(Log.DEBUG,this,"id/tag = " + tokenText.substring(match.getStartIndex(1), match.getEndIndex(1)));
		Log.log(Log.DEBUG,this,"comment = " + tokenText.substring(match.getStartIndex(2), match.getEndIndex(2)));
		Log.log(Log.DEBUG,this,"text = " + tokenText.substring(start, end));
		Log.log(Log.DEBUG,this,"Match 0 = " + match.toString(0));
		Log.log(Log.DEBUG,this,"Match 1 = " + match.toString(1));
		Log.log(Log.DEBUG,this,"Match 2 = " + match.toString(2));
		Log.log(Log.DEBUG,this,"Match 3 = " + match.toString(3));
		*/

		return new Task(buffer,
			icon,
			/* line number */
			line,
			/* identifier/name */
			tokenText.substring(match.getStartIndex(1), match.getEndIndex(1)),
			/* comment */
			tokenText.substring(match.getStartIndex(2), match.getEndIndex(2)),
			/* text to display in list: identifier, whitespace, and any comment */
			tokenText.substring(start, end),
			/* start position */
			tokenOffset + start,
			/* end position */
			tokenOffset + end);
	}//}}}

	//{{{ get/setName() methods
	public String getName() { return this.name; }

	public void setName(String name)
	{
		this.name = name;
	}//}}}

	//{{{ get/setPattern() methods
	public String getPattern(){ return this.pattern; }

	public void setPattern(String pattern)
	{
		if(this.pattern == null || !this.pattern.equals(pattern))
		{
			this.pattern = pattern;
			compileRE();
		}
	}//}}}

	//{{{ get/setSample() methods
	public String getSample(){ return this.sample; }

	public void setSample(String sample)
	{
		this.sample = sample;
	}//}}}

	//{{{ get/setIgnoreCase() methods
	public boolean getIgnoreCase(){ return this.ignoreCase; }

	public void setIgnoreCase(boolean ignoreCase)
	{
		if(this.ignoreCase != ignoreCase)
		{
			this.ignoreCase = ignoreCase;
			this.reFlags = (ignoreCase ? RE.REG_ICASE : 0);
			compileRE();
		}
	}//}}}

	public Icon getIcon(){ return this.icon; }

	//{{{ get/setIconPath() method
	public String getIconPath(){ return this.iconPath; }

	public void setIconPath(String iconPath)
	{
		if(this.iconPath != iconPath || this.icon == null)
		{
			this.iconPath = iconPath;
			Icon _icon = TaskType.loadIcon(iconPath);
			if(_icon != null)
				this.icon = _icon;
		}
	}//}}}

	//{{{ getREFlags() method
	public int getREFlags()
	{
		return reFlags;
	}//}}}

	//{{{ compileRE() method
	private void compileRE()
	{
		this.re = null;

		try
		{
			this.re = new RE(this.pattern, this.getREFlags(),
				TaskType.RE_SYNTAX);
		}
		catch(REException e)
		{
			Log.log(Log.ERROR, TaskType.class,
				"Failed to compile task pattern: " + pattern +
					e.toString());
		}
	}//}}}

	//{{{ save() method
	public void save(int i)
	{
		jEdit.setProperty("tasklist.tasktype." + i + ".name", name);
		jEdit.setProperty("tasklist.tasktype." + i + ".pattern", pattern);
		jEdit.setProperty("tasklist.tasktype." + i + ".sample", sample);
		jEdit.setBooleanProperty("tasklist.tasktype." + i + ".ignorecase", ignoreCase);
		jEdit.setProperty("tasklist.tasktype." + i + ".iconpath", iconPath);
	}//}}}

	//{{{ toString() method
	public String toString()
	{
		return this.pattern;
	}//}}}

	//{{{ private members
	private RE re;
	private int reFlags;
	private String name;
	private String pattern;
	private String sample;
	private boolean ignoreCase;
	private String iconPath;
	private Icon icon;

	private boolean displayIdentifier = true;

	private static Hashtable icons;
	//}}}

	//{{{ loadIcon() method
	/**
	* Loads an icon for later use
	* @param iconName a file name (start with 'file:' or resource path)
	*/
	public static Icon loadIcon(String iconName)
	{
		//Log.log(Log.DEBUG, TaskType.class,
		//	"TaskType.loadIcon(" + iconName + ")");//##

		// check if there is a cached version first
		Icon icon = (Icon)icons.get(iconName);
		if(icon != null)
			return icon;

		// load the icon
		if(iconName.startsWith("file:"))
		{
			icon = new ImageIcon(iconName.substring(5));
		}
		else
		{
			URL url = TaskListPlugin.class.getResource("/icons/" + iconName);
			if(url == null)
			{
				Log.log(Log.ERROR, TaskType.class,
					"TaskType.loadIcon() - icon not found: " + iconName);
				return null;
			}

			icon = new ImageIcon(url);
		}

		icons.put(iconName, icon);
		return icon;
	}//}}}

	//{{{ static initializer
	static
	{
		icons = new Hashtable();
		StringTokenizer st = new StringTokenizer(jEdit.getProperty("tasklist.icons"));
		while(st.hasMoreElements())
		{
			String icon = st.nextToken();
			loadIcon(icon);
		}
	}//}}}

}

// :collapseFolds=1:folding=explicit:indentSize=4:lineSeparator=\n:noTabs=false:tabSize=4:
