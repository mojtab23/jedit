/*
 * SwingHTMLParserImpl.java
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 2000, 2001 Slava Pestov
 * Portions copyright (C) 2001 David Walend
 *
 * The XML plugin is licensed under the GNU General Public License, with
 * the following exception:
 *
 * "Permission is granted to link this code with software released under
 * the Apache license version 1.1, for example used by the Xerces XML
 * parser package."
 */

package xml.parser;

//{{{ Imports
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Position;
import javax.swing.tree.*;
import java.io.*;
import java.util.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.Attributes;
import errorlist.*;
import sidekick.*;
import xml.completion.*;
import xml.*;
//}}}

public class SwingHTMLParserImpl extends XmlParser
{
	//{{{ SwingHTMLParserImpl constructor
	public SwingHTMLParserImpl()
	{
		super("html");
	} //}}}

	//{{{ parse() method
	public SideKickParsedData parse(Buffer buffer, DefaultErrorSource errorSource)
	{
		if(buffer.getLength() >= 5 && buffer.getText(0,5).equals("<?xml"))
			return XmlPlugin.XML_PARSER_INSTANCE.parse(buffer,errorSource);

		String text;

		try
		{
			buffer.readLock();
			text = buffer.getText(0,buffer.getLength());
		}
		finally
		{
			buffer.readUnlock();
		}

		XmlParsedData data = new XmlParsedData(buffer.getName(),true);

		CompletionInfo info = CompletionInfo.getCompletionInfoForBuffer(buffer);
		if(info != null)
			data.mappings.put("",info);

		try
		{
			DocumentParser htmlParser = new DocumentParser(DTD.getDTD("html32"));

			htmlParser.parse(new StringReader(text),
				new Handler(buffer,data),
				true);
		}
		catch(IOException ioe)
		{
			Log.log(Log.ERROR,this,ioe);
			errorSource.addError(ErrorSource.ERROR,buffer.getPath(),0,0,0,
				ioe.toString());
		}

		// need to do some cleanup...
		for(int i = 0; i < data.root.getChildCount(); i++)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)data.root.getChildAt(i);
			XmlTag tag = (XmlTag)node.getUserObject();
			if(tag.attributes.getValue("_implied_") != null)
			{
				data.root.remove(i);

				int j = 0;

				while(node.getChildCount() != 0)
				{
					data.root.insert((DefaultMutableTreeNode)node.getChildAt(0),i + j);
					j++;
				}

				i--;
			}
		}

		Collections.sort(data.ids,new IDDecl.Compare());

		return data;
	} //}}}

	//{{{ Handler class
	class Handler extends HTMLEditorKit.ParserCallback
	{
		Buffer buffer;

		XmlParsedData data;
		Stack currentNodeStack;

		//{{{ Handler constructor
		Handler(Buffer buffer, XmlParsedData data)
		{
			this.buffer = buffer;
			this.data = data;
			this.currentNodeStack = new Stack();
		} //}}}

		//{{{ handleStartTag() method
		public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int offset)
		{
			try
			{
				buffer.readLock();

				if(offset > buffer.getLength())
					offset = buffer.getLength();

				Position pos = buffer.createPosition(offset);
				int line = buffer.getLineOfOffset(offset);
				int column = offset - buffer.getLineStartOffset(line);
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
					new XmlTag(t.toString(),
					pos,attributesToSAX(a,t.toString(),line,column)));

				//if(!Boolean.TRUE.equals(a.getAttribute(IMPLIED)))
				{
					if(!currentNodeStack.isEmpty())
					{
						DefaultMutableTreeNode node
							= (DefaultMutableTreeNode)
							currentNodeStack.peek();

						node.insert(newNode,node.getChildCount());
					}
					else
						data.root.insert(newNode,0);
				}

				currentNodeStack.push(newNode);
			}
			finally
			{
				buffer.readUnlock();
			}
		} //}}}

		//{{{ handleEndTag() method
		public void handleEndTag(HTML.Tag t, int offset)
		{
			try
			{
				buffer.readLock();

				if(!currentNodeStack.isEmpty())
				{
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)
						currentNodeStack.pop();
					XmlTag tag = (XmlTag)node.getUserObject();

					if(offset > buffer.getLength())
						offset = buffer.getLength();

					tag.end = buffer.createPosition(offset);
				}
				else
					/* ? */;
			}
			finally
			{
				buffer.readUnlock();
			}
		} //}}}

		//{{{ handleSimpleTag() method
		public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int offset)
		{
			try
			{
				buffer.readLock();

				if(offset > buffer.getLength())
					offset = buffer.getLength();

				Position pos = buffer.createPosition(offset);
				int line = buffer.getLineOfOffset(offset);
				int column = offset - buffer.getLineStartOffset(line);

				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
					new XmlTag(t.toString(),
					pos,attributesToSAX(a,t.toString(),line,column)));

				if(!currentNodeStack.isEmpty())
				{
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)
						currentNodeStack.peek();

					node.add(newNode);
				}
				else
					data.root.add(newNode);
			}
			finally
			{
				buffer.readUnlock();
			}
		} //}}}

		//{{{ handleError() method
		public void handleError(String errorMsg, int pos)
		{
			// The Swing parser's error reporting is broken.
			/*try
			{
				buffer.readLock();

				if(pos > buffer.getLength())
					pos = buffer.getLength();
				int line = buffer.getLineOfOffset(pos);

				errorSource.addError(ErrorSource.ERROR,buffer.getPath(),
					line,0,0,errorMsg);
			}
			finally
			{
				buffer.readUnlock();
			}*/
		} //}}}

		//{{{ attributesToSAX() method
		private Attributes attributesToSAX(MutableAttributeSet a,
			String element, int line, int column)
		{
			ElementDecl elementDecl = data.getElementDecl(element);

			AttributesImpl attrs = new AttributesImpl();
			Enumeration enum = a.getAttributeNames();
			while(enum.hasMoreElements())
			{
				Object attr = enum.nextElement();
				String name = attr.toString();
				String value = a.getAttribute(attr).toString();

				String type = "CDATA";
				if(elementDecl != null)
				{
					ElementDecl.AttributeDecl attrDecl
						= (ElementDecl.AttributeDecl)
						elementDecl.attributeHash
						.get(name.toLowerCase());
					if(attrDecl != null)
					{
						type = attrDecl.type;

						if(type.equals("ID"))
						{
							if(!data.ids.contains(value))
							{
								data.ids.add(new IDDecl(
									buffer.getPath(),
									value,element,line,column));
							}
						}
					}
				}

				attrs.addAttribute(null,name,name,type,value);
			}

			return attrs;
		} //}}}
	} //}}}
}
