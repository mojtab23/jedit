/*
 * HyperSearchRequest.java - HyperSearch request, run in I/O thread
 * :tabSize=2:indentSize=2:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 1998, 1999, 2000, 2001, 2002 Slava Pestov
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

// package org.gjt.sp.jedit.search;

//{{{ Imports
import javax.swing.text.Segment;
import javax.swing.tree.*;
import javax.swing.SwingUtilities;
import org.gjt.sp.jedit.textarea.Selection;
import org.gjt.sp.jedit.io.VFSManager;
import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.search.*;
import org.gjt.sp.util.*;
//import bsh.BshMethod;
import org.gjt.sp.util.Log;  // for debugging
import org.gjt.sp.jedit.BeanShell;

//}}}

public class HyperSearchRequest extends WorkRequest
{
	//{{{ HyperSearchRequest constructor
	public HyperSearchRequest(View view, SearchMatcher matcher,
		HyperSearchResults results, Selection[] selection)
	{
		this.view = view;
		this.matcher = matcher;

		this.results = results;
		this.resultTreeModel = results.getTreeModel();
		this.resultTreeRoot = (DefaultMutableTreeNode)resultTreeModel
			.getRoot();

		this.selection = selection;
	} //}}}

	//{{{ run() method
	public void run()
	{
		SearchFileSet fileset = SearchAndReplace.getSearchFileSet();
		String[] files = fileset.getFiles(view);
		if(files == null || files.length == 0)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					GUIUtilities.error(view,"empty-fileset",null);
				}
			});
			return;
		}

		setProgressMaximum(fileset.getFileCount(view));

		int resultCount = 0;
		int bufferCount = 0;

		// to minimise synchronization and stuff like that, we only
		// show a status message at most twice a second

		// initially zero, so that we always show the first message
		long lastStatusTime = 0;


		try
		{
			if(selection != null)
			{
				setStatus(jEdit.getProperty("hypersearch.status-selection"));

				Buffer buffer = view.getBuffer();

				bufferCount = 1;
				resultCount = searchInSelection(buffer);
			}
			else
			{
				int current = 0;

loop:				for(int i = 0; i < files.length; i++)
				{
					String file = files[i];
					current++;

					long currentTime = System.currentTimeMillis();
					if(currentTime - lastStatusTime > 500)
					{
						setStatus(jEdit.getProperty("hypersearch.status",
							new String[] { file }));
						setProgressValue(current);
						lastStatusTime = currentTime;
					}

					Buffer buffer = jEdit.openTemporary(null,null,file,false);
					if(buffer == null)
						continue loop;

					int thisResultCount = doHyperSearch(buffer,
						0,buffer.getLength());
					if(thisResultCount != 0)
					{
						bufferCount++;
						resultCount += thisResultCount;
					}
				};
			}
		}
		catch(final Exception e)
		{
			Log.log(Log.ERROR,this,e);
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					GUIUtilities.error(view,"searcherror",
						new String[] { e.toString() });
				}
			});
		}
		catch(WorkThread.Abort a)
		{
		}
		finally
		{
			final int _resultCount = resultCount;
			final int _bufferCount = bufferCount;
			VFSManager.runInAWTThread(new Runnable()
			{
				public void run()
				{
					results.searchDone(_resultCount,_bufferCount);
				}
			});
		}
	} //}}}

	//{{{ Private members

	//{{{ Instance variables
	private View view;
	private SearchMatcher matcher;
	private HyperSearchResults results;
	private DefaultTreeModel resultTreeModel;
	private DefaultMutableTreeNode resultTreeRoot;
	private Selection[] selection;
	//}}}

	//{{{ searchInSelection() method
	private int searchInSelection(Buffer buffer) throws Exception
	{
		setAbortable(false);

		int resultCount = 0;

		try
		{
			buffer.readLock();

			final DefaultMutableTreeNode bufferNode = new DefaultMutableTreeNode(
				buffer.getPath());

			for(int i = 0; i < selection.length; i++)
			{
				Selection s = selection[i];
				if(s instanceof Selection.Rect)
				{
					for(int j = s.getStartLine();
						j <= s.getEndLine(); j++)
					{
						resultCount += doHyperSearch(buffer,
							s.getStart(buffer,j),
							s.getEnd(buffer,j));
					}
				}
				else
				{
					resultCount += doHyperSearch(buffer,
						s.getStart(),s.getEnd());
				}
			}
		}
		finally
		{
			buffer.readUnlock();
		}

		setAbortable(true);

		return resultCount;
	} //}}}

	//{{{ doHyperSearch() method
	private int doHyperSearch(Buffer buffer, int start, int end)
		throws Exception
	{
		setAbortable(false);

		final DefaultMutableTreeNode bufferNode = new DefaultMutableTreeNode(
			buffer.getPath());

		int resultCount = doHyperSearch(buffer,start,end,bufferNode);

		if(resultCount != 0)
		{
			resultTreeRoot.insert(bufferNode,resultTreeRoot.getChildCount());

			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					resultTreeModel.reload(resultTreeRoot);
				}
			});
		}

		setAbortable(true);

		return resultCount;
	} //}}}

	//{{{ doHyperSearch() method
	private int doHyperSearch(Buffer buffer, int start, int end,
		DefaultMutableTreeNode bufferNode)
	{
		int resultCount = 0;

		try
		{
			buffer.readLock();

			boolean endOfLine = (buffer.getLineEndOffset(
				buffer.getLineOfOffset(end)) - 1 == end);

			Segment text = new Segment();
			int offset = start;
			int line = -1;

			loop:			for(int counter = 0; ; counter++)
			{
				boolean startOfLine = (buffer.getLineStartOffset(
					buffer.getLineOfOffset(offset)) == offset);

				buffer.getText(offset,end - offset,text);
				int[] match = matcher.nextMatch(
					new CharIndexedSegment(text,false),
					startOfLine,endOfLine,counter == 0);
				if(match == null)
					break loop;

				int matchStart = offset + match[0];
				int matchEnd = offset + match[1];

				offset += match[1];
Log.log(Log.DEBUG, BeanShell.class,"tp275: matchStart = "+matchStart+", matchEnd = "+matchEnd+", offset = "+offset+
				"found: "+buffer.getText(matchStart, matchEnd - matchStart));
				// rwchg: check extended parameters
				if (!XSearchAndReplace.checkXSearchParameters(view.getTextArea(), buffer, matchStart, matchEnd, true)) {
					Log.log(Log.DEBUG, BeanShell.class,"tp281: ");
           
					// this match was not valid: skip
					continue loop;
				}
				
				resultCount++;  // this line has been moved: count match, even if on same line

				int newLine = buffer.getLineOfOffset(offset);
				if(line == newLine)
				{
					// already had a result on this
					// line, skip
					continue loop;
				}

				line = newLine;

				bufferNode.add(new DefaultMutableTreeNode(
					new HyperSearchResult(buffer,line,
					matchStart,matchEnd),false));
			}
		}
		finally
		{
			buffer.readUnlock();
		}

		return resultCount;
	} //}}}

	//}}}
}
