package console.ssh;
// :folding=explicit:
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

import org.gjt.sp.util.Log;

import com.jcraft.jsch.Session;

import console.Console;
import console.ConsolePane;
import console.Output;

import ftp.ConnectionInfo;

// {{{ class Shell
/**
 * Secure shell interface for jEdit console. A singleton exists for the whole jedit process.
 * State information for individual Console instances is handled by the ConsoleState class.
 * @author ezust
 *
 */
public class Shell extends console.Shell {
	static final byte[] EOF = new byte[] {4};
	static final byte[] SUSPEND = new byte[] {26};
	static final byte[] STOP = new byte[] {3};
	
	/** Send a ctrl-c down the pipe, to end the process, rather than the session. */
	public void stop(Console console)
	{
		new ShellAction(console, STOP).actionPerformed(null);
	}

	/** Sends ctrl-d (EOF) down the pipe, to signal end of file. */
	public void closeConsole(Console console)
	{
		new ShellAction(console, EOF).actionPerformed(null);
	}

	public void openConsole(Console console)
	{
		ConsolePane pane = console.getConsolePane();
		InputMap inputMap = pane.getInputMap();
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C,
			InputEvent.CTRL_MASK),
			new ShellAction(console, STOP));

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,
			InputEvent.CTRL_MASK),
			new ShellAction(console, EOF));
		
		
	}

	public Shell() {
		super("ssh");
	}

    // {{{ execute() method	
	/**
	 * @param console the instance that is running this command
	 * @param input is always null
	 * @param output a ShellState instance 
	 * @param error another writable thing for errors (not used)
	 * @param command the command to execute
	 */
	public void execute(Console console, String input, Output output, Output error, String command)
	{
		ConsoleState cs = ConnectionManager.getConsoleState(console);
		cs.preprocess(command);		
		try {			
			if (cs.conn == null) {
				ConnectionInfo info = ConnectionManager.getConnectionInfo(cs.getPath());
				if (info == null) {
					Log.log(Log.ERROR, this, "Unable to get connectioninfo for: " + cs.getPath());
					return;
				}
				cs.info = info;
				Session session=ConnectionManager.client.getSession(info.user, info.host, info.port);
				Connection c = ConnectionManager.getShellConnection(console, info);
				session.setUserInfo(c);
				cs.os = c.ostr;
				cs.conn = c;
			}
//			Log.log (Log.MESSAGE, this, "Command: " + command + "  input: " + input);
			cs.os.write((command + "\n").getBytes() );
			cs.os.flush();
		}
		catch (Exception e) {
			Log.log (Log.WARNING, this, "execute failed:", e);
		}
		finally {
			printPrompt(console, output);
		}
	} // }}}

    
	public void printPrompt(Console console, Output output)
	{
		ConsoleState s = ConnectionManager.getConsoleState(console);
		String promptString = "[no sftp:// connections?] >";
		if (s.info != null) {
			promptString = "[ssh:" + s.info.user + "@" + s.info.host + "]> ";
		}
		if (s.conn == null || s.conn.inUse != true) { 
		        output.writeAttrs(ConsolePane.colorAttributes(console.getPlainColor()), 
			"\n" + promptString);
		}
	}

	/** sends a ctrl-Z down the pipe, to suspend the current job */
	public void detach(Console console)
	{
		ConsoleState cs = ConnectionManager.getConsoleState(console);
//		console.getOutput().print(console.getWarningColor(), "Job Control disabled.\n");
		if (cs.os != null) try {
			cs.os.write(SUSPEND);
			cs.os.flush();
		}
		catch (IOException IOE) {};

	}


	public static class ShellAction extends AbstractAction {

		Console con;
		byte[] cmd;
		public ShellAction(Console console, byte[] str) {
			con = console;
			cmd = str;
			
		}
		
		public void actionPerformed(ActionEvent e)
		{
			ConsoleState cs = ConnectionManager.getConsoleState(con);
			if (cs != null && cs.os != null) try {
				cs.os.write(cmd);
				cs.os.flush();
			}
			catch (IOException ioe) {}
		}
		
		
	}
	
	
}; // }}}

