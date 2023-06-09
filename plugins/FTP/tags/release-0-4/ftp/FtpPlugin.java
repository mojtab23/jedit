/*
 * FtpPlugin.java - Main class of virtual filesystem
 * Copyright (C) 2000, 2002 Slava Pestov
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

package ftp;

import java.awt.Component;
import java.util.Hashtable;
import java.util.Vector;
import org.gjt.sp.jedit.browser.VFSBrowser;
import org.gjt.sp.jedit.io.*;
import org.gjt.sp.jedit.msg.*;
import org.gjt.sp.jedit.*;

public class FtpPlugin extends EditPlugin
{
	public void start()
	{
		VFSManager.registerVFS(FtpVFS.PROTOCOL,new FtpVFS());
	}

	public void stop()
	{
		DirectoryCache.clearAllCachedDirectories();
	}

	public void createMenuItems(Vector menuItems)
	{
		menuItems.addElement(GUIUtilities.loadMenu("ftp"));
	}

	public static void showOpenFTPDialog(View view)
	{
		String path = ((FtpVFS)VFSManager.getVFSForProtocol("ftp"))
			.showBrowseDialog(new Object[1],view);
		if(path != null)
		{
			String[] files = GUIUtilities.showVFSFileDialog(
				view,path,VFSBrowser.OPEN_DIALOG,true);
			if(files == null)
				return;

			Buffer buffer = null;
			for(int i = 0; i < files.length; i++)
			{
				Buffer _buffer = jEdit.openFile(null,files[i]);
				if(_buffer != null)
					buffer = _buffer;
			}
			if(buffer != null)
				view.setBuffer(buffer);
		}
	}

	public static void showSaveFTPDialog(View view)
	{
		String path = ((FtpVFS)VFSManager.getVFSForProtocol("ftp"))
			.showBrowseDialog(new Object[1],view);
		if(path != null)
		{
			String[] files = GUIUtilities.showVFSFileDialog(
				view,path,VFSBrowser.SAVE_DIALOG,false);
			if(files == null)
				return;

			view.getBuffer().save(view,files[0],true);
		}
	}
}
