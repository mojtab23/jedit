/*
 * SFtpConnection.java - A connection to an SSH FTP server
 * Copyright (C) 2002-2014 Slava Pestov, Nicholas O'Leary, Vadim Voituk, Alan Ezust
 *
 * :tabSize=4:indentSize=4:noTabs=false:
 * :folding=explicit:collapseFolds=1:
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

//{{{ Imports
package ftp;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.MiscUtilities;
import org.gjt.sp.jedit.io.FileVFS;
import org.gjt.sp.jedit.io.VFSFile;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Proxy;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.ProxySOCKS5;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
import com.jcraft.jsch.ConfigRepository;

import ftp.FtpVFS.FtpDirectoryEntry;
//}}}

//{{{ SFtpConnection class

/**
 * Secure FTP connection class
 *
 * @author Slava Pestov
 * @author Vadim Voituk
 */
public class SFtpConnection extends Connection implements UserInfo, UIKeyboardInteractive
{
	//{{{ data members
	private final ChannelSftp sftp;
	private final Session session;
	private int keyAttempts;
	private String passphrase;
	//}}}

	//{{{ ctor
	public SFtpConnection(ConnectionInfo info) throws IOException
	{
		super(info);
		try
		{
			if (ConnectionManager.client == null)
			{
				ConnectionManager.client = new JSch();
			}
			String dir = SFtpConnection.getUserConfigDir();
			if (dir != null)
			{
				File f = new File(dir);
				if (!f.exists())
					f.mkdir();
				File configFile = new File(getUserConfigFile());
				if (configFile.exists())
				{
					ConfigRepository configRepository =
						com.jcraft.jsch.OpenSSHConfig.parseFile(getUserConfigFile());
					ConnectionManager.client.setConfigRepository(configRepository);
				}
				String known_hosts = MiscUtilities.constructPath(getUserConfigDir(), "known_hosts");
				File knownHostsFile = new File(known_hosts);
				if (!knownHostsFile.exists())
				{
					knownHostsFile.createNewFile();
				}
				ConnectionManager.client.setKnownHosts(known_hosts);
			}
			JSch.setLogger(new SftpLogger());

			// {{{ Detect proxy settings if needed
			Proxy proxy = null;
			if (jEdit.getBooleanProperty("vfs.ftp.useProxy"))
			{

				if (jEdit.getBooleanProperty("firewall.socks.enabled", false))
				{
					//Detect SOCKS Proxy
					proxy = new ProxySOCKS5(jEdit.getProperty("firewall.socks.host"), jEdit.getIntegerProperty("firewall.socks.port", 3128));
				}
				else if (jEdit.getBooleanProperty("firewall.enabled", false))
				{
					// HTTP-Proxy detect
					ProxyHTTP httpProxy = new ProxyHTTP(jEdit.getProperty("firewall.host"), jEdit.getIntegerProperty("firewall.port", 3128));
					if (!jEdit.getProperty("firewall.user", "").isEmpty())
						httpProxy.setUserPasswd(jEdit.getProperty("firewall.user"), jEdit.getProperty("firewall.password"));
					proxy = httpProxy;
				}
			}
			// }}}
			if (info.user.isEmpty())
				session = ConnectionManager.client.getSession(info.host);
			else
				session = ConnectionManager.client.getSession(info.user, info.host, info.port);
			if (proxy != null)
				session.setProxy(proxy);

			Log.log(Log.DEBUG, this, "info.privateKey=" + info.privateKey);
			if (info.privateKey != null && !info.privateKey.isEmpty())
			{
				Log.log(Log.DEBUG, this, "Attempting public key authentication");
				Log.log(Log.DEBUG, this, "Using key: " + info.privateKey);
				ConnectionManager.client.addIdentity(info.privateKey);
			}
			keyAttempts = 0;
			session.setUserInfo(this);

			if (jEdit.getBooleanProperty("vfs.sftp.compression"))
			{
				session.setConfig("compression.s2c", "zlib@openssh.com,zlib,none");
				session.setConfig("compression.c2s", "zlib@openssh.com,zlib,none");
				session.setConfig("compression_level", "9");
			}

			// Don't lock out user when exceeding bad password attempts on some servers
			session.setConfig("MaxAuthTries", jEdit.getProperty("vfs.sftp.MaxAuthTries", "2"));        // (default was 6)

			session.connect(ConnectionManager.connectionTimeout);

			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			sftp.setAgentForwarding(true);
			home = sftp.getHome();
			keyAttempts = 0;
		}
		catch (Exception e)
		{
			Log.log(Log.ERROR, this, e);
			// Signal that the login failed
			throw new IOException(e);
		}
	}//}}}

	//{{{ getUserConfigDir()

	/**
	 * @return the openssh user configuration directory
	 */
	static String getUserConfigDir()
	{
		return MiscUtilities.constructPath(System.getProperty("user.home"), ".ssh");
	}//}}}

	//{{{ getUserConfigFile()

	/**
	 * @return the desired location of the .ssh/config file to be used
	 */
	static String getUserConfigFile()
	{
		String defaultValue = MiscUtilities.constructPath(getUserConfigDir(), "config");
		return jEdit.getProperty("ssh.config", defaultValue);
	}// }}}

	//{{{ listDirectory()
	@Override
	@SuppressWarnings("unchecked")
	FtpVFS.FtpDirectoryEntry[] listDirectory(String path) throws IOException
	{
		FtpVFS.FtpDirectoryEntry[] listing;
		try
		{
			List<ChannelSftp.LsEntry> lsEntries = sftp.ls(path);
			listing = lsEntries
				.stream()
				.map(lsEntry -> createDirectoryEntry(lsEntry.getFilename(), lsEntry.getAttrs()))
				.toArray(FtpDirectoryEntry[]::new);
		}
		catch (SftpException e)
		{
			return null;
		}
		return listing;
	}//}}}

	//{{{ getDirectoryEntry()
	@Override
	FtpVFS.FtpDirectoryEntry getDirectoryEntry(String path) throws IOException
	{
		FtpVFS.FtpDirectoryEntry returnValue = null;
		try
		{
			SftpATTRS attrs = sftp.stat(path);
			String name = MiscUtilities.getFileName(path);

			returnValue = createDirectoryEntry(name, attrs);

			returnValue.setPath(path);
			returnValue.setDeletePath(path);
		}
		catch (SftpException e)
		{
		}
		return returnValue;
	}//}}}

	//{{{ removeFile()
	@Override
	boolean removeFile(String path) throws IOException
	{
		try
		{
			sftp.rm(path);
			return true;
		}
		catch (SftpException e)
		{
			return false;
		}
	}//}}}

	//{{{ removeDirectory()
	@Override
	boolean removeDirectory(String path) throws IOException
	{
		try
		{
			sftp.rmdir(path);
			return true;
		}
		catch (SftpException e)
		{
			return false;
		}
	}//}}}

	//{{{ rename()
	@Override
	boolean rename(String from, String to) throws IOException
	{
		try
		{
			sftp.rename(from, to);
			return true;
		}
		catch (SftpException e)
		{
			return false;
		}
	}//}}}

	//{{{ makeDirectory()
	@Override
	boolean makeDirectory(String path) throws IOException
	{
		try
		{
			sftp.mkdir(path);
			return true;
		}
		catch (SftpException e)
		{
			return false;
		}
	}//}}}

	//{{{ retrieve()
	@Override
	InputStream retrieve(String path) throws IOException
	{
		try
		{
			return sftp.get(path);
		}
		catch (SftpException e)
		{
			throw new IOException(e.toString());
		}
	}//}}}

	//{{{ store()
	@Override
	OutputStream store(String path) throws IOException
	{
		OutputStream returnValue;
		try
		{
			returnValue = sftp.put(path);
		}
		catch (SftpException e)
		{
			throw new IOException(e.toString());
		}
		return returnValue;
	}//}}}

	//{{{ chmod()
	@Override
	void chmod(String path, int permissions) throws IOException
	{
		try
		{
			sftp.chmod(permissions, path);
		}
		catch (SftpException e)
		{
			throw new IOException(e.toString());
		}
	} //}}}

	@Override
	boolean checkIfOpen() throws IOException
	{
		return sftp.isConnected();
	}

	@Override
	public String resolveSymlink(String path, String[] name) throws IOException
	{
		return path;
	}

	@Override
	void logout() throws IOException
	{
		sftp.disconnect();
		session.disconnect();
	}


	// private int symLinkDepth = 0; // not used now
	private FtpVFS.FtpDirectoryEntry createDirectoryEntry(String name, SftpATTRS attrs)
	{
		long length = attrs.getSize();
		int permissions = attrs.getPermissions();

		// remove file mode bits from the permissions
		permissions &= 0x1ff; // == binary 111111111
		int type;
		if (attrs.isDir())
			type = VFSFile.DIRECTORY;
		else if (attrs.isLink())
			type = FtpVFS.FtpDirectoryEntry.LINK;
		else
			type = VFSFile.FILE;

		// path field filled out by FtpVFS class
		// (String name, String path, String deletePath,
		//	int type, long length, boolean hidden, int permissions)
		FtpVFS.FtpDirectoryEntry entry = new FtpVFS.FtpDirectoryEntry(
			name, null, null, type, length, name.startsWith("."), permissions, null);
		int mtime = attrs.getMTime();
		if (mtime != 0)
		{
			Date date = new Date(((long) mtime) * 1000);
			String modTime = FileVFS.LocalFile.DATE_FORMAT.format(date);
			entry.setModified(((long) mtime) * 1000);
			entry.setModifiedDate(modTime);
		}

		//boolean w = (permissions&00200)!=0;
		//boolean r = (permissions&00400)!=0;
		entry.setWriteable((permissions & 00200) != 0);
		entry.setReadable((permissions & 00400) != 0);
		return entry;
	}


	@Override
	public String getPassphrase()
	{
		return passphrase;
	}

	@Override
	public String getPassword()
	{
		return info.password;
	}

	//{{{ prompting functions
	@Override
	public boolean promptPassword(String message)
	{
		return true;
	}

	@Override
	public boolean promptPassphrase(String message)
	{
		Log.log(Log.DEBUG, this, message);
		passphrase = ConnectionManager.getPassphrase(info.privateKey);
		if (passphrase == null || keyAttempts != 0)
		{

			GUIUtilities.hideSplashScreen();
			PasswordDialog pd = new PasswordDialog(jEdit.getActiveView(),
				jEdit.getProperty("login.privatekeypassword"), message);
			if (!pd.isOK())
				return false;
			passphrase = new String(pd.getPassword());
			ConnectionManager.setPassphrase(info.privateKey, passphrase);
		}

		keyAttempts++;
		return true;
	}

	@Override
	public boolean promptYesNo(String message)
	{
		int ret[] = new int[1];
		try
		{
			Runnable runnable = () ->
			{
				Object[] options = {"yes", "no"};
				ret[0] = JOptionPane.showOptionDialog(jEdit.getActiveView(),
					message,
					"Warning",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			};
			if (EventQueue.isDispatchThread())
			{
				runnable.run();
			}
			else
			{
				EventQueue.invokeAndWait(runnable);
			}
		}
		catch (InterruptedException | InvocationTargetException e)
		{
			Log.log(Log.ERROR, this, e);
		}
		return ret[0] == 0;
	}

	@Override
	public void showMessage(String message)
	{
		Log.log(Log.ERROR, this, message);
	}

	// See http://marc.info/?l=ant-dev&m=111959408515300&w=2
	@Override
	public String[] promptKeyboardInteractive(String destination, String name, String instruction, String[] prompt, boolean[] echo)
	{
		if (prompt.length != 1 || echo[0])
			return null;
		String[] response = new String[1];
		response[0] = getPassword();
		return response;
	}//}}}

}//}}}
