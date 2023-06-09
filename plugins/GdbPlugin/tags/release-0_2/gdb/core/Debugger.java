/*
Copyright (C) 2007  Shlomy Reinstein

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

package gdb.core;

import gdb.breakpoints.Breakpoint;
import gdb.breakpoints.BreakpointList;
import gdb.breakpoints.BreakpointView;
import gdb.context.StackTrace;
import gdb.core.GdbState.State;
import gdb.core.Parser.GdbResult;
import gdb.core.Parser.ResultHandler;
import gdb.execution.ControlView;
import gdb.launch.LaunchConfiguration;
import gdb.launch.LaunchConfigurationListDialog;
import gdb.launch.LaunchConfigurationManager;
import gdb.options.GeneralOptionPane;
import gdb.output.MIShell;
import gdb.output.ProgramShell;
import gdb.variables.LocalVariables;
import gdb.variables.Variables;
import gdb.variables.Watches;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.ServiceManager;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.textarea.JEditTextArea;

import debugger.itf.DebuggerTool;
import debugger.itf.IData;
import debugger.itf.JEditFrontEnd;

public class Debugger implements DebuggerTool {

	private static Debugger debugger = null;

	private JEditFrontEnd frontEnd = null;

	private ControlView controlView = null;
	private BreakpointView breakpointsPanel = null;
	private LocalVariables localsPanel = null;
	private StackTrace stackTracePanel = null;
	private Watches watchesPanel = null;
	private Variables variablesPanel = null;
	// Command manager
	private CommandManager commandManager = null;
	// Parser
	private Parser parser = null;
	// Gdb process
	private Process p = null;
	// Gdb internal message
	private String gdbInternalMessage = null;

	public static final String KILL_ACTION = "debugger-kill";
	public static final String GO_ACTION = "debugger-go";
	public static final String NEXT_ACTION = "debugger-next";
	public static final String STEP_ACTION = "debugger-step";
	public static final String FINISH_ACTION = "debugger-finish";
	public static final String UNTIL_ACTION = "debugger-until";
	public static final String PAUSE_ACTION = "debugger-pause";
	public static final String QUIT_ACTION = "debugger-quit";
	public static final String TOGGLE_BREAKPOINT_ACTION = "debugger-toggle-breakpoint";
	public static final String EDIT_LAUNCH_CONFIGS_ACTION = "debugger-edit-launch-configs";
	
	public IData getData(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void editLaunchConfigs(View view) {
		LaunchConfigurationListDialog dlg = new LaunchConfigurationListDialog(view);
		dlg.setVisible(true);
	}
	public void selectLaunchConfig(View view) {
		LaunchConfigurationManager mgr = LaunchConfigurationManager.getInstance();
		Vector<LaunchConfiguration> configs = mgr.get();
		LaunchConfiguration sel = (LaunchConfiguration)
			JOptionPane.showInputDialog(view,
						"Program",
						"Select:",
						JOptionPane.QUESTION_MESSAGE,
						null,
						configs.toArray(),
						mgr.getDefault());
		if (sel == null)
			return;
		mgr.setDefaultIndex(configs.indexOf(sel));
		mgr.save();
		go();
	}
	
	public void go() {
		if (! isRunning())
			start();
		else 
			commandManager.add("-exec-continue");
	}

	public void pause() {
		if (isRunning())
			commandManager.add("-exec-interrupt");
	}

	public void quit() {
		if (! isRunning())
			return;
		commandManager.add("-gdb-exit", new ResultHandler() {
			public void handle(String msg, GdbResult res) {
				if (msg.equals("exit")) {
					sessionEnded();
				}
			}
		});
	}

	public void next() {
		if (isRunning())
			commandManager.add("-exec-next");
	}

	public void step() {
		if (isRunning())
			commandManager.add("-exec-step");
	}

	public void finishCurrentFunction() {
		if (isRunning())
			commandManager.add("-exec-finish");
	}

	public void runToCursor() {
		View view = jEdit.getActiveView();
		Buffer buffer = view.getBuffer();
		JEditTextArea ta = view.getTextArea();
		int line = ta.getCaretLine() + 1;
		if (isRunning())
			commandManager.add("-exec-until " + buffer.getPath() + ":" + line);
	}
	public void kill() {
		sessionEnded();
		destroy();
	}
	public void destroy() {
		if (p != null)
			p.destroy();
	}
	
	private void sessionEnded() {
		parser = null;
		commandManager = null;
		GdbState.setState(State.IDLE);
		frontEnd.programExited();
	}
	public void start() {
		LaunchConfiguration currentConfig =
			LaunchConfigurationManager.getInstance().getDefault();
		if (currentConfig == null) {
			JOptionPane.showMessageDialog(jEdit.getActiveView(),
					"No program is selected for debugging.\n" +
					"Please do one of the following:\n" +
					"- Use the GdbPlugin options to specify the default " +
					"program for debugging ('Make default' button)\n" +
					"- Use 'Debug ...' from the GdbPlugin menu and select " +
					"the program you wish to debug from the list");
			return;
		}
		// Clear the consoles
		getProgramShell().clear();
		getMIShell().clear();
		// Start the debugging process
		debugger.start(currentConfig.getProgram(),
				currentConfig.getArguments(),
				currentConfig.getDirectory(),
				currentConfig.getEnvironmentArray());
	}
	public void start(String prog, String args, String cwd, String [] env) {
		String command = jEdit.getProperty(GeneralOptionPane.GDB_PATH_PROP) +
			" --interpreter=mi " + prog;
		if (cwd == null || cwd.length() == 0)
			cwd = ".";
		File dir = new File(cwd);
		try {
			p = Runtime.getRuntime().exec(command, env, dir);
			GdbState.setState(State.RUNNING);
	        parser = new Parser(this, p);
			parser.addOutOfBandHandler(new OutOfBandHandler());
			parser.start();
			commandManager = new CommandManager(p, parser);
			commandManager.start();
			// First set up the arguments
			commandManager.add("-exec-arguments " + args);
			// Now set up the breakpoints
			Vector<Breakpoint> bps = BreakpointList.getInstance().getBreakpoints();
			for (int i = 0; i < bps.size(); i++) {
				Breakpoint b = bps.get(i);
				b.initialize();
				if (! b.isEnabled())
					b.setEnabled(false);
			}
			commandManager.add("-exec-run");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void toggleBreakpoint(View view)
	{
		Buffer buffer = view.getBuffer();
		JEditTextArea ta = view.getTextArea();
		int line = ta.getCaretLine() + 1;
		Vector<Breakpoint> breakpoints =
			BreakpointList.getInstance().get(buffer.getPath(), line);
		if (breakpoints.isEmpty())
			setBreakpoint(view, buffer, line);
		else
			removeBreakpoint(view);
	}
	public void setBreakpoint(View view) {
		Buffer buffer = view.getBuffer();
		JEditTextArea ta = view.getTextArea();
		int line = ta.getCaretLine() + 1;
		setBreakpoint(view, buffer, line);
	}
	private void setBreakpoint(View view, Buffer buffer, int line) {
		new Breakpoint(view, buffer, line);
	}
	public void removeBreakpoint(View view) {
		Buffer buffer = view.getBuffer();
		JEditTextArea ta = view.getTextArea();
		int line = ta.getCaretLine() + 1;
		Vector<Breakpoint> breakpoints =
			BreakpointList.getInstance().get(buffer.getPath(), line);
		if (breakpoints.isEmpty())
			return;
		for (int i = 0; i < breakpoints.size(); i++) {
			Breakpoint b = breakpoints.get(i);
			b.remove();
		}
	}

	private void stopped(String file, int line) {
		GdbState.setState(State.PAUSED);
		frontEnd.setCurrentLocation(file, line);
		
	}

	public void breakpointHit(int bkptno, String file, int line) {
		String msg = "Breakpoint " + bkptno + " hit";
		if (file != null)
			msg = msg + ", at " + file + ":" + line + ".";
		//System.err.println(msg);
		JOptionPane.showMessageDialog(null, msg);
	}

	public void signalReceived(String signal) {
		String msg = "Received signal: " + signal;
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void setFrontEnd(JEditFrontEnd frontEnd) {
		this.frontEnd = frontEnd;
	}

	private class BreakpointHitHandler implements ResultHandler {
		int bkptno;
		BreakpointHitHandler(int bkptno) {
			this.bkptno = bkptno;
		}
		public void handle(String msg, GdbResult res) {
			String file = res.getStringValue("fullname");
			int line = 0;
			if (file != null) {
				line = Integer.parseInt(res.getStringValue("line"));
			}
			breakpointHit(bkptno, file, line);
			stopped(file, line);
		}
	}
	private class StoppedHandler implements ResultHandler {
		public void handle(String msg, GdbResult res) {
			String file = res.getStringValue("fullname");
			int line = 0;
			if (file != null) {
				line = Integer.parseInt(res.getStringValue("line"));
			}
			stopped(file, line);
		}
	}
	private class OutOfBandHandler implements ResultHandler {
		public void handle(String msg, GdbResult res) {
			final String getCurrentPosition = "-file-list-exec-source-file";
			String reason = res.getStringValue("reason");
			if (reason.equals("breakpoint-hit")) {
				int bkptno = Integer.parseInt(res.getStringValue("bkptno"));
				commandManager.add(getCurrentPosition, new BreakpointHitHandler(bkptno));
			} else if (reason.startsWith("exited")) {
				sessionEnded();
			} else if (reason.startsWith("signal-received")) {
				signalReceived(res.getStringValue("signal-meaning"));
				commandManager.add(getCurrentPosition, new StoppedHandler());
			} else {
				commandManager.add(getCurrentPosition, new StoppedHandler());
			}
		}
	}

	public JEditFrontEnd getFrontEnd() {
		return frontEnd;
	}

	public JPanel showControlPanel(View view) {
		if (controlView == null)
			controlView = new ControlView();
		return controlView;
	}
	public JPanel showBreakpoints(View view) {
		if (breakpointsPanel == null)
			breakpointsPanel = new BreakpointView();
		return breakpointsPanel;
	}
	public JPanel showLocals(View view) {
		if (localsPanel == null)
			localsPanel = new LocalVariables();
		return localsPanel;
	}
	public JPanel showWatches(View view) {
		if (watchesPanel == null)
			watchesPanel = new Watches();
		return watchesPanel;
	}
	public JPanel showVariables(View view) {
		if (variablesPanel == null)
			variablesPanel = new Variables();
		return variablesPanel;
	}
	public JPanel showStackTrace(View view) {
		if (stackTracePanel == null)
			stackTracePanel = new StackTrace();
		return stackTracePanel;
	}
	private MIShell getMIShell() {
		return (MIShell) ServiceManager.getService("console.Shell", MIShell.NAME);
	}
	public void gdbRecord(String line)
	{
		getMIShell().append(line);
	}
	public void commandRecord(String line)
	{
		gdbInternalMessage = null;
		getMIShell().append(line);
	}
	private ProgramShell getProgramShell() {
		return (ProgramShell) ServiceManager.getService("console.Shell", ProgramShell.NAME);
	}
	public void programError(String line) {
		getProgramShell().appendError(line);
	}
	public void programRecord(String line)
	{
		getProgramShell().append(line);
	}
	public static Debugger getInstance() {
		if (debugger == null)
			debugger = new Debugger();
		return debugger;
	}

	public boolean isRunning() {
		return GdbState.isRunning();
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}
	public Parser getParser() {
		return parser;
	}

	public void gdbMessage(String msg) {
		gdbInternalMessage = msg;
	}
	
	public String getGdbMessage() {
		return gdbInternalMessage;
	}
	public Process getGdbProcess() {
		return p;
	}
}
