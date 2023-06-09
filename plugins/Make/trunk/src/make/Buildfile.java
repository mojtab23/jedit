/*
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package make;

import java.io.*;
import java.util.LinkedList;
import java.util.HashMap;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.MiscUtilities;
import org.gjt.sp.jedit.gui.StatusBar;
import org.gjt.sp.util.ThreadUtilities;

/**
 * Abstract representation of a buildfile. When creating your own, override _only_
 * the abstract methods.
 */
public abstract class Buildfile {
	protected File dir;
	protected String name;
	public LinkedList<BuildTarget> targets;
	
	/**
	 * Return the name of the build system that uses this buildfile, e.g. "Ant"
	 */
	public abstract String getName();
	
	/**
	 * Parse available build targets. This is usually done by querying the build system
	 * itself, e.g. running "ant -projecthelp" in a separate process and parsing its
	 * output. Each target should be an instance of the BuildTarget class and added to
	 * the 'targets' LinkedList. See any of the classes in the make.buildfile package
	 * for working examples.
	 * @return true on success, false on failure. Failure is usually caused by an IOException
	 */
	protected abstract boolean _parseTargets();
	
	/**
	 * Run this build. Parameters beyond the target are rare, but are used in some build systems
	 * e.g. rake install["villainy"]
	 * @return the Process for the running build
	 */
	protected abstract Process _runTarget(BuildTarget target, HashMap<String, String> params) throws IOException;
	
	/**
	 * Take a line of output from the build system and parse it to determine if it's reporting
	 * an error. If it is, then MakePlugin.addError() should be called to make sure it gets
	 * added to Error List.
	 */
	protected abstract void _processErrors(String line);
	
	public Buildfile(String dir, String name) {
		this.dir = new File(dir);
		this.name = name;
	}
	
	public boolean parseTargets() {
		String path = this.getPath();
		this.targets = MakePlugin.getCachedTargets(path);
		
		if (this.targets.size() == 0) {
			boolean success = this._parseTargets();
			MakePlugin.cacheTargets(path, this.targets);
			return success;
		} else {
			return true;
		}
	}
	
	public void runTarget(BuildTarget target) {
		this.runTargetWithParams(target, new HashMap<String, String>());
	}
	
	public void runTargetWithParams(final BuildTarget target, final HashMap<String, String> params) {
		try {
			final StatusBar status = jEdit.getActiveView().getStatus();
			final Process p = _runTarget(target, params);
			
			MakePlugin.clearErrors();
			MakePlugin.clearOutput();
			MakePlugin.writeToOutput(jEdit.getProperty("make.msg.build-starting.output", "--- Starting Build ---"));
			MakePlugin.writeToOutput("");
			status.setMessage(jEdit.getProperty("make.msg.build-starting.title", "Running build..."));
			
			// catch stdout
			ThreadUtilities.runInBackground(new Thread() {
					public void run() {
						try {
							BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
							String line;
							while ((line = reader.readLine()) != null) {
								MakePlugin.writeToOutput(line);
								_processErrors(line);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			});
			
			// catch stderr
			ThreadUtilities.runInBackground(new Thread() {
					public void run() {
						try {
							BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
							String line;
							while ((line = reader.readLine()) != null) {
								MakePlugin.writeToOutput(line);
								_processErrors(line);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			});
			
			// wait for build
			ThreadUtilities.runInBackground(new Thread() {
					public void run() {
						try {
							int exitCode = p.waitFor();
							if (exitCode == 0) {
								status.setMessageAndClear(jEdit.getProperty("make.msg.build-succeeded", "Build succeeded"));
							} else {
								status.setMessageAndClear(jEdit.getProperty("make.msg.build-failed", "Build failed"));
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getPath() {
		return MiscUtilities.constructPath(this.dir.getPath(), this.name);
	}
}
