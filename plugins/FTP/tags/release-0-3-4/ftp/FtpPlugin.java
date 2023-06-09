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

	/**
	 * If a password has been saved for the host and user name in the
	 * session, it sets the value of the session's PASSWORD_KEY value
	 * accordingly. Otherwise, a login dialog box is displayed.
	 * @param session The VFS session
	 * @param comp The component that will parent the login dialog box
	 * @return True if everything is ok, false if the user cancelled the
	 * operation
	 */
	public static boolean showLoginDialog(Object _session, Component comp)
	{
		FtpVFS.FtpSession session = (FtpVFS.FtpSession)_session;

		if(loginHash == null)
			loginHash = new Hashtable();

		if(session.host != null)
		{
			LoginInfo login = (LoginInfo)loginHash.get(session.host);

			if(login != null && (session.user == null
				|| login.user.equals(session.user)))
			{
				if(session.user == null)
					session.user = login.user;

				if(session.password == null)
					session.password = login.password;
			}

			if(session.user != null && session.password != null)
				return true;
		}

		/* since this can be called at startup time,
		 * we need to hide the splash screen. */
		GUIUtilities.hideSplashScreen();

		LoginDialog dialog = new LoginDialog(comp,
			session.host,session.user,session.password);
		if(!dialog.isOK())
			return false;

		session.host = dialog.getHost();
		session.user = dialog.getUser();
		session.password = dialog.getPassword();

		loginHash.put(session.host,new LoginInfo(session.user,session.password));

		return true;
	}

	/**
	 * Forgets all saved passwords.
	 */
	public static void forgetPasswords()
	{
		if(loginHash != null)
			loginHash.clear();
	}

	static class LoginInfo
	{
		String user, password;

		LoginInfo(String user, String password)
		{
			this.user = user;
			this.password = password;
		}
	}

	private static Hashtable loginHash;
}
