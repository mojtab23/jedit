/*
 *  AntFarmPlugin.java - Plugin for running Ant builds from jEdit.
 *  Copyright (C) 2001 Brian Knowles
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package antfarm;

import java.io.*;

import java.util.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.menu.EnhancedMenu;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.util.*;

import console.*;
import plugin.integration.*;
import errorlist.*;
//import projectviewer.*;

public class AntFarmPlugin extends EditPlugin
{

	public final static String NAME = "antfarm";
	public final static String OPTION_PREFIX = "options." + NAME + ".";
	public final static String ANT_HOME = "ant.home";
	public final static AntFarmShell ANT_SHELL = new AntFarmShell();

	private static DefaultErrorSource _errorSource;


	public static DefaultErrorSource getErrorSource()
	{
		return _errorSource;
	}


	public static AntFarmShell getAntFarmShell() {
		return ANT_SHELL;
	}

	static Console getConsole( View view )
	{
		return getConsole( view, true );
	}


	static Console getConsole( View view, boolean bringToFront )
	{
		DockableWindowManager mgr = view.getDockableWindowManager();
		String CONSOLE = "console";
		// Get the current console instance
		Console console =
			(Console) mgr.getDockable( CONSOLE );
		if(console == null)
		{
			mgr.addDockableWindow( CONSOLE );
			console = (Console) mgr.getDockable( CONSOLE );
		}

		console.setShell( AntFarmPlugin.ANT_SHELL );

		if ( bringToFront )
			view.getDockableWindowManager().showDockableWindow( CONSOLE );

		return console;
	}


	static Properties getGlobalProperties()
	{
		Properties properties = new Properties();

		String name;
		int counter = 1;
		while ( ( name = jEdit.getProperty( PropertiesOptionPane.PROPERTY + counter + PropertiesOptionPane.NAME ) ) != null ) {
			properties.put( name, jEdit.getProperty( PropertiesOptionPane.PROPERTY + counter + PropertiesOptionPane.VALUE ) );
			counter++;
		}
		return properties;
	}

	public void start()
	{
		//IntegrationManager integration = new IntegrationManager( this );
		//integration.addBridge( "projectviewer.ProjectPlugin", "antfarm.ProjectBridge" );

		boolean useSameJvm
			 = jEdit.getBooleanProperty( AntFarmPlugin.OPTION_PREFIX + "use-same-jvm", true );

		// initalize error source
		_errorSource = new DefaultErrorSource( NAME );
		ErrorSource.registerErrorSource(_errorSource);

		// check whether tools.jar is available on JDK 1.2 or higher:
		if ( !MiscUtilities.isToolsJarAvailable() && useSameJvm) {
			String warning = "This will cause problems when trying to use the modern and classic compilers.\n" +
				"If you want AntFarm to work properly, please make sure tools.jar is\n" +
				"in one of the above locations, and restart jEdit.";
			Log.log( Log.WARNING, this, warning );
			GUIUtilities.message(null, "tools-jar-missing", null);
		}

		// Register the ant shell with the console plugin.
		// Shell.registerShell( ANT_SHELL );

		// put the ant home in the environment for scripts to use.
		System.getProperties().put( ANT_HOME, getAntFarmPath() );

		loadCustomClasspath();
	}

	public void stop()
	{
		// Shell.unregisterShell( ANT_SHELL );
	}

	static void loadCustomClasspath()
	{
		String classpath = jEdit.getProperty( AntFarmPlugin.OPTION_PREFIX
			 + "classpath" );
		if ( classpath == null )
			return;

		StringTokenizer st = new StringTokenizer( classpath, File.pathSeparator );
		while ( st.hasMoreTokens() ) {
			String path = st.nextToken();

			PluginJAR jar;
			jar = jEdit.getPluginJAR( path );
			if ( jar == null ) {
				Log.log( Log.DEBUG, null,
					"- adding " + path + " to jEdit plugins." );
				jEdit.addPluginJAR(path);
			}
			else
				Log.log( Log.DEBUG, null,
					"- has been loaded before: " + path );
		}
	}

	String getAntFarmPath()
	{
		PluginJAR jar = getPluginJAR();
		String path = MiscUtilities.getParentOfPath( jar.getPath() );
		Log.log( Log.DEBUG, this, "path: " + path );
		return MiscUtilities.constructPath( path, "AntFarm" );
	}


	static boolean useProjectBridge()
	{
		return jEdit.getBooleanProperty(
			AntFarmPlugin.OPTION_PREFIX + "use-project-bridge"
			 );
	}

	static boolean supressSubTargets()
	{
		return jEdit.getBooleanProperty(
			AntFarmPlugin.OPTION_PREFIX + "supress-sub-targets"
			 );
	}
}

