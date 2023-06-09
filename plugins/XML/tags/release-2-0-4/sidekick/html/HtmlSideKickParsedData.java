/*
 * SideKickParsedData.java
 *
 * Copyright (C) 2003, 2004 Slava Pestov
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

package sidekick.html;

// Imports
import org.gjt.sp.jedit.Buffer;

import sidekick.IAsset;
import xml.XmlParsedData;
import xml.completion.CompletionInfo;

/**
 * Stores a buffer structure tree.
 * 
 * Plugins can extend this class to persist plugin-specific information. For
 * example, the XML plugin stores code completion-related structures using a
 * subclass.
 * 
 * danson: modified for HtmlSideKick.
 */
public class HtmlSideKickParsedData extends XmlParsedData
{
	/**
	 * @param fileName
	 *                The file name being parsed, used as the root of the
	 *                tree.
	 */
	public HtmlSideKickParsedData(String fileName, Buffer buffer)
	{
		super(fileName, true);
		CompletionInfo completionInfo = CompletionInfo.getCompletionInfoForBuffer(buffer);
		setCompletionInfo("", completionInfo);
	}

    /*
	public IAsset getAssetAtOffset(int pos)
	{
		IAsset asset = super.getAssetAtOffset(pos);
		System.out.println(asset.getName() + ": " + asset.getStart());
		return asset;
	}
    */
}
