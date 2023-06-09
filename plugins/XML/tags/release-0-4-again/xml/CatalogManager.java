/*
 * CatalogManager.java
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

import javax.swing.text.BadLocationException;
import java.io.*;
import java.net.*;
import java.util.*;
import com.arbortext.catalog.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;
import org.xml.sax.*;

public class CatalogManager
{
	public static InputSource resolve(String current,
		String publicId, String systemId)
		throws SAXException, IOException
	{
		load();

		String newSystemId = null;

		if(publicId == null && systemId != null && current != null)
		{
			current = MiscUtilities.getParentOfPath(current);
			if(systemId.startsWith(current))
			{
				// first, try resolving a relative name,
				// to handle jEdit built-in DTDs
				newSystemId = catalog.resolveSystem(
					systemId.substring(current.length()));
			}
		}

		// next, try resolving full path name
		if(newSystemId == null)
		{
			if(publicId == null)
				newSystemId = catalog.resolveSystem(systemId);
			else
				newSystemId = catalog.resolvePublic(publicId,systemId);
		}

		// well, the catalog can't help us, so just assume the
		// system id points to a file
		if(newSystemId == null)
		{
			if(systemId == null)
				return null;
			else
				newSystemId = systemId;
		}

		// Xerces has a bug where an InputSource without a byte
		// stream is loaded incorrectly.
		InputSource source = new InputSource(newSystemId);
		source.setByteStream(new URL(newSystemId).openStream());
		return source;
	}

	public static void propertiesChanged()
	{
		loaded = false;
	}

	// private members
	private static Catalog catalog;
	private static boolean loaded;

	private synchronized static void load()
	{
		if(loaded)
			return;

		loaded = true;

		catalog = new Catalog();
		catalog.setParserClass("org.apache.xerces.parsers.SAXParser");

		int i = 0;
		String uri;

		try
		{
			catalog.loadSystemCatalogs();

			catalog.parseCatalog("jeditresource:XML.jar!/xml/catalog");

			while((uri = jEdit.getProperty("xml.catalog." + i)) != null)
			{
				catalog.parseCatalog(uri);
				i++;
			}

			i = 0;
			String id;
			while((id = jEdit.getProperty("xml.public-id." + i)) != null)
			{
				catalog.addEntry(new CatalogEntry(
					CatalogEntry.PUBLIC,
					id,jEdit.getProperty(
					"xml.public-id." + i + ".uri")));
				i++;
			}

			i = 0;
			while((id = jEdit.getProperty("xml.system-id." + i)) != null)
			{
				catalog.addEntry(new CatalogEntry(
					CatalogEntry.SYSTEM,
					id,jEdit.getProperty(
					"xml.system-id." + i + ".uri")));
				i++;
			}
		}
		catch(Exception e)
		{
			Log.log(Log.ERROR,CatalogManager.class,e);
		}
	}
}
