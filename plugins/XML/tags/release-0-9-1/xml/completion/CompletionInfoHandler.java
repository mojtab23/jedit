/*
 * CompletionInfoHandler.java
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 2001 Slava Pestov
 *
 * The XML plugin is licensed under the GNU General Public License, with
 * the following exception:
 *
 * "Permission is granted to link this code with software released under
 * the Apache license version 1.1, for example used by the Xerces XML
 * parser package."
 */

package xml.completion;

//{{{ Imports
import java.io.*;
import java.util.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;
import xml.parser.*;
import xml.*;
//}}}

public class CompletionInfoHandler extends DefaultHandler
{
	//{{{ CompletionInfoHandler constructor
	public CompletionInfoHandler()
	{
		completionInfo = new CompletionInfo();

		addEntity(new EntityDecl(EntityDecl.INTERNAL,"lt","<"));
		addEntity(new EntityDecl(EntityDecl.INTERNAL,"gt",">"));
		addEntity(new EntityDecl(EntityDecl.INTERNAL,"amp","&"));
		addEntity(new EntityDecl(EntityDecl.INTERNAL,"quot","\""));
		addEntity(new EntityDecl(EntityDecl.INTERNAL,"apos","'"));
	} //}}}

	//{{{ getCompletionInfo() method
	public CompletionInfo getCompletionInfo()
	{
		return completionInfo;
	} //}}}

	//{{{ setDocumentLocator() method
	public void setDocumentLocator(Locator loc)
	{
		this.loc = loc;
	} //}}}

	//{{{ endDocument() method
	public void endDocument()
	{
		MiscUtilities.quicksort(completionInfo.elements,
			new ElementDecl.Compare());
		MiscUtilities.quicksort(completionInfo.entities,
			new EntityDecl.Compare());
	} //}}}

	//{{{ resolveEntity() method
	public InputSource resolveEntity(String publicId, String systemId)
		throws SAXException
	{
		try
		{
			return CatalogManager.resolve(
				loc.getSystemId(),publicId,systemId);
		}
		catch(Exception e)
		{
			throw new SAXException(e);
		}
	} //}}}

	//{{{ startElement() method
	public void startElement(String namespaceURI,
		String sName, // simple name
		String qName, // qualified name
		Attributes attrs) throws SAXException
	{
		if(sName.equals("dtd"))
		{
			String extend = attrs.getValue("extend");
			if(extend != null)
			{
				String infoURI = jEdit.getProperty(
					"mode." + extend + "."
					+ XmlPlugin.COMPLETION_INFO_PROPERTY);
				if(infoURI != null)
				{
					CompletionInfo extendInfo = CompletionInfo
						.getCompletionInfo(infoURI);
					if(extendInfo != null)
						completionInfo = (CompletionInfo)extendInfo.clone();
				}
			}

			completionInfo.html = "true".equals(attrs.getValue("html"));
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
				attrs.getValue("content"),
				"true".equals(attrs.getValue("html")));

			completionInfo.elements.add(element);
			completionInfo.elementHash.put(element.name,element);

			if("true".equals(attrs.getValue("anywhere")))
				completionInfo.elementsAllowedAnywhere.add(element);
		}
		else if(sName.equals("attribute"))
		{
			String name = attrs.getValue("name");
			String value = attrs.getValue("value");
			String type = attrs.getValue("type");

			ArrayList values;

			if(type.startsWith("("))
			{
				values = new ArrayList();

				StringTokenizer st = new StringTokenizer(
					type.substring(1,type.length() - 1),"|");
				while(st.hasMoreTokens())
				{
					values.add(st.nextToken());
				}
			}
			else
				values = null;

			boolean required = "true".equals(attrs.getValue("required"));

			element.addAttribute(new ElementDecl.AttributeDecl(
				name,value,values,type,required));
		}
	} //}}}

	//{{{ Private members
	private CompletionInfo completionInfo;
	private Locator loc;
	private ElementDecl element;

	//{{{ addEntity() method
	private void addEntity(EntityDecl entity)
	{
		completionInfo.entities.add(entity);
		if(entity.type == EntityDecl.INTERNAL
			&& entity.value.length() == 1)
		{
			Character ch = new Character(
				entity.value.charAt(0));
			completionInfo.entityHash.put(entity.name,ch);
			completionInfo.entityHash.put(ch,entity.name);
		}
	} //}}}

	//}}}
}
