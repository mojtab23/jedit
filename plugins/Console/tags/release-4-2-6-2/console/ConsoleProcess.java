/*
 * ConsoleProcess.java - A running process
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 1999, 2004 Slava Pestov
 * With mods Copyright (C) 2005 Alan Ezust
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

package console;

// {{{ Imports
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.util.Log;

import console.utils.StringList;

// }}}

class ConsoleProcess
{
	
	// {{{ ConsoleProcess constructor
	ConsoleProcess(Console console, Output output, String[] args,
			ProcessBuilder pBuilder,  SystemShell.ConsoleState consoleState,
			boolean foreground)
	{
		this.args = args;
		this.currentDirectory = consoleState.currentDirectory;
		this.console = console;

		if (foreground)
		{
			this.output = output;
			this.error = new ErrorOutput(output, console.getErrorColor());
			this.consoleState = consoleState;
		}

		try
		{
			// Streams for getting user input
			pipeIn = new PipedInputStream();
			pipeOut = new PipedOutputStream(pipeIn);
			process = ProcessRunner.getProcessRunner().exec(args, pBuilder,
					currentDirectory);
			if (process == null) {
				String str = StringList.join(args, " ");
				throw new RuntimeException( "Unrecognized command: " + str );
			}
			console.startAnimation();

			parserThread = null;
			
			stdout = new StreamThread(this, process.getInputStream(), console.getPlainColor());
			stdout.start();
			boolean merge = jEdit.getBooleanProperty("console.processrunner.mergeError", true);

			if (merge) 
			{
				stderr = null;
			}
			else 
			{
				stderr = new StreamThread(this, process.getErrorStream(), console.getErrorColor());
				stderr.start();
			}

			stdin = new InputThread(this, process.getOutputStream());
			stdin.start();
		}
		catch (IOException ioe) 
		{
			Log.log(Log.ERROR, ioe, "ConsoleProcess()");
		}

	} // }}}

	// {{{ detach() method
	void detach()
	{
		if (console != null)
		{
			Object[] pp = { args[0] };
			error.print(console.getErrorColor(), 
				jEdit.getProperty("console.shell.detached", pp));
			output.commandDone();
			if (error != null)  
			{
				error.commandDone();
			}
		}
		
		consoleState.process = null;
		consoleState = null;
		console = null;
	} // }}}

	// {{{ stop() method
	synchronized void stop()
	{
		if (process != null)
		{
			stopped = true;

			if (stdin != null) stdin.abort();

			if (stdout != null) stdout.abort();
			if (stderr != null) stderr.abort();
			if (parserThread!= null) parserThread.finishErrorParsing();
			try
			{
				pipeOut.close();
			} catch (IOException e)
			{
				throw new RuntimeException(e);
			}

			try 
			{
				process.destroy();
				output.commandDone();
			}
			catch (Exception e) {}
			process = null;

			if (console != null)
			{
				Object[] pp = { args[0] };
/*				error.print(console.getErrorColor(), jEdit.getProperty(
						"console.shell.killed", pp)); */
			}
			// error.commandDone();
		}

		if (consoleState != null)
			consoleState.process = null;
	} // }}}

	// {{{ isRunning() method
	boolean isRunning()
	{
		if (process == null)
			return false;
		try 
		{
			// should throw an exception of the thing is still running
			process.exitValue();
		}
		catch (IllegalThreadStateException itse) 
		{
			return true;
		}
		return false;
	} // }}}

	// {{{ getExitStatus() method
	int getExitStatus()
	{
		return exitCode;
	} // }}}

	// {{{ getConsole() method
	Console getConsole()
	{
		return console;
	} // }}}

	// {{{ getOutput() method
	Output getOutput()
	{
		return output;
	} // }}}

	// {{{ getErrorOutput() method
	Output getErrorOutput()
	{
		return error;
	} // }}}

	// {{{ getCurrentDirectory() method
	String getCurrentDirectory()
	{
		return currentDirectory;
	} // }}}

	// {{{ getPipeInput() method
	public PipedInputStream getPipeInput()
	{
		return pipeIn;
	} // }}}

	String[] getArgs() {
		return args;
	}
	
	// {{{ getPipeOutput() method
	public PipedOutputStream getPipeOutput()
	{
		return pipeOut;
	} // }}}
	
	// {{{ waitFor()
	/** @see Process.waitFor() */
	public int waitFor() throws InterruptedException 
	{
		return process.waitFor();
	} // }}}
	
	// {{{ threadDone() method
	synchronized void threadDone()
	{

		threadDoneCount++;

		if ((!stopped) && (process != null))
		{
			// we don't want unkillable processes to hang
			// jEdit

			try
			{
				exitCode = process.waitFor();
			} catch (InterruptedException e)
			{
				Log.log(Log.ERROR, this, e);
			}
			
			stop();
		}
		jEdit.checkBufferStatus(jEdit.getActiveView());
	}
	// }}}
	
	// {{{ Private members
	private SystemShell.ConsoleState consoleState;

	private String currentDirectory;

	private Console console;

	private Output output;

	private Output error;

	private String[] args;

//	private String[] env;
	
	/** The running subprocess */

	Process process;
	
	// Threads for handling the streams of running subprocesses

	private InputThread stdin;

	private StreamThread stdout;
	
	private StreamThread stderr;
	
	private CommandOutputParser parserThread;

	private int threadDoneCount = 0;

	private int exitCode = 834; 

	private boolean stopped;

	/*
	 * AWT thread writes stdin to this pipe, and the input thread writes it to
	 * the process.
	 */
	private PipedInputStream pipeIn;

	private PipedOutputStream pipeOut;
	// }}}

}
