/*
 * CompletionInfoHandler.java
 * Copyright (C) 2001 Slava Pestov
 *
 * The XML plugin is licensed under the GNU General Public License, with
 * the following exception:
 *
 * "Permission is granted to link this code with software released under
 * the Apache license version 1.1, for example used by the Xerces XML
 * parser package."
 */

package xml;

import java.io.*;
import java.util.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;

// This class loads tag and entity lists for non-XML files.
class CompletionInfoHandler extends DefaultHandler
{
	CompletionInfoHandler()
	{
		completionInfo = new CompletionInfo(
			false,
			new Vector(), new Hashtable(),
			new Vector(), new Hashtable(),
			new Vector());
	}

	CompletionInfo getCompletionInfo()
	{
		return completionInfo;
	}

	public void setDocumentLocator(Locator loc)
	{
		this.loc = loc;
	}

	public void endDocument()
	{
		MiscUtilities.quicksort(completionInfo.elements,
			new XmlParser.ElementDeclCompare());
		MiscUtilities.quicksort(completionInfo.entities,
			new XmlParser.EntityDeclCompare());
	}

	public InputSource resolveEntity(String publicId, String systemId)
		throws SAXException
	{
		try
		{
			return CatalogManager.resolve(
				loc.getSystemId(),publicId,systemId);
		}
		catch(IOException io)
		{
			throw new SAXException(io);
		}
	}

	public void startElement(String namespaceURI,
		String sName, // simple name
		String qName, // qualified name
		Attributes attrs) throws SAXException
	{
		if(sName.equals("dtd"))
		{
			completionInfo.html = "true".equals(attrs.getValue("html"));
			//completionInfo.extend = attrs.getValue("extend");
		}
		else if(sName.equals("entity"))
		{
			addEntity(new EntityDecl(
				EntityDecl.INTERNAL,
				attrs.getValue("name"),
				attrs.getValue("value")));
		}
		else if(sName.equals("element"))
		{
			element = new ElementDecl(
				attrs.getValue("name"),
				"true".equals(attrs.getValue("empty")),
				"true".equals(attrs.getValue("html")));

			completionInfo.elements.addElement(element);
			completionInfo.elementHash.put(element.name,element);
		}
		else if(sName.equals("attribute"))
		{
			String name = attrs.getValue("name");
			String value = attrs.getValue("value");

			Vector _values;
			String values = attrs.getValue("values");
			int type;
			if(values == null)
			{
				type = ElementDecl.AttributeDecl.CDATA;
				_values = null;
			}
			else
			{
				type = ElementDecl.AttributeDecl.CHOICE;
				_values = new Vector();

				StringTokenizer st = new StringTokenizer(
					values,"|");
				while(st.hasMoreTokens())
				{
					_values.addElement(st.nextToken());
				}
			}

			boolean required = "true".equals(attrs.getValue("required"));

			element.addAttribute(new ElementDecl.AttributeDecl(
				name,value,_values,type,required));
		}
	}

	// private members
	private CompletionInfo completionInfo;
	private Locator loc;
	private ElementDecl element;

	private void addEntity(EntityDecl entity)
	{
		completionInfo.entities.addElement(entity);
		if(entity.type == EntityDecl.INTERNAL
			&& entity.value.length() == 1)
		{
			Character ch = new Character(
				entity.value.charAt(0));
			completionInfo.entityHash.put(entity.name,ch);
			completionInfo.entityHash.put(ch,entity.name);
		}
	}
}
