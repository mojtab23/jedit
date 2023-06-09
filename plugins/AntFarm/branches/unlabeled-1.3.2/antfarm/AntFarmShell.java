/*
 *  AntFarmShell.java - Plugin for running Ant builds from jEdit.
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
import console.*;
import java.awt.Color;
import java.io.*;
import java.util.*;
import org.apache.tools.ant.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;

public class AntFarmShell extends Shell
{

	private Project _currentProject;
	private File _currentBuildFile;
	private TargetRunner _targetRunner;
	private AntFarm _antBrowser;


	public AntFarmShell()
	{
		super( "Ant" );
	}


	// ----- Begin Shell implementation -----

	public void printInfoMessage( Output output )
	{
		printUsage(
			jEdit.getProperty( AntFarmPlugin.NAME + ".shell.msg.info" ),
			null,
			output
			 );
		printCurrentProjectInfo( null, output );
	}


	public void execute( Console console, Output output, String command )
	{
		stop( console );

		command = command.trim();

		if ( command.startsWith( "!" ) ) {
			if ( _currentProject == null ) {

				output.print(
					console.getErrorColor(),
					jEdit.getProperty( AntFarmPlugin.NAME + ".shell.msg.non-selected" )
					 );
				output.commandDone();
				return;
			}
			String requestedTarget = command.substring( 1 );
			if ( requestedTarget.equals( "" ) ) {
				requestedTarget = _currentProject.getDefaultTarget();
			}
			Target target = (Target)
				_currentProject.getTargets().get( requestedTarget );

			int logLevel = getAntFarm(console).getMessageOutputLevel();

			_targetRunner = new TargetRunner( target,
				_currentBuildFile, console.getView(), output, logLevel );
		}
		else if ( command.equals( "?" ) ) {

			printUsage( "", null, output );

			Vector buildFiles = getAntFarm( console ).getAntBuildFiles();
			output.print(
				null,
				jEdit.getProperty( AntFarmPlugin.NAME + ".shell.label.available-files" )
				 );

			String fileList = "";
			for ( int i = 0; i < buildFiles.size(); i++ ) {
				fileList += "(" + ( i + 1 ) + ") " + buildFiles.elementAt( i ) + "\n";
			}
			output.print( console.getInfoColor(), fileList );

			printCurrentProjectInfo( console.getInfoColor(), output );

			output.commandDone();
		}
		else if ( command.startsWith( "=" ) ) {

			Vector buildFiles = getAntFarm( console ).getAntBuildFiles();

			try {
				int fileNumber = Integer.parseInt( command.substring( 1 ) );

				if ( fileNumber - 1 >= buildFiles.size() ) {
					printUsage(
						jEdit.getProperty( AntFarmPlugin.NAME + ".shell.msg.no-build-file" ),
						console.getErrorColor(),
						output
						 );
					output.commandDone();
					return;
				}
				String buildFilePath = (String) buildFiles.elementAt( fileNumber - 1 );
				File buildFile = new File( buildFilePath );
				Project project = null;
				try {
					project = getAntFarm( console ).getProject( buildFile.getAbsolutePath() );
					setCurrentProject( project, buildFile, console );
				}
				catch ( Exception e ) {
					setCurrentProject( null, buildFile, console );
					output.print(
						console.getErrorColor(),
						jEdit.getProperty( AntFarmPlugin.NAME + ".shell.msg.broken-file" )
						 + buildFile + "\n" + e
						 );
					output.commandDone();
					return;
				}
				output.commandDone();
			}
			catch ( NumberFormatException e ) {
				printUsage(
					jEdit.getProperty( AntFarmPlugin.NAME + ".shell.msg.invalid-usage" ),
					console.getErrorColor(),
					output
					 );
				output.commandDone();
				return;
			}
		}
		else if ( command.startsWith( "+" ) ) {

			String filePath = command.substring( 1 );
			try {
				File file = new File( filePath );
				Project project = getAntFarm( console ).getProject( file.getAbsolutePath() );
				setCurrentProject( project, file, console );
				getAntFarm( console ).addAntBuildFile( filePath );
			}
			catch ( Exception e ) {
				output.print(
					console.getErrorColor(),
					jEdit.getProperty( AntFarmPlugin.NAME + ".shell.msg.broken-file" )
					 + filePath + "\n" + e
					 );
				output.commandDone();
				return;
			}
			output.commandDone();
		}
		else {
			printUsage(
				jEdit.getProperty( AntFarmPlugin.NAME + ".shell.msg.invalid-usage" ),
				console.getErrorColor(),
				output
				 );
			output.commandDone();
		}
	}


	public void stop( Console console )
	{
		if ( _targetRunner != null ) {
			if ( _targetRunner.isAlive() ) {
				_targetRunner.stop();
				console.print(
					console.getErrorColor(),
					jEdit.getProperty( AntFarmPlugin.NAME + ".shell.msg.killed" )
					 );
			}
			_targetRunner = null;
		}
	}


	public boolean waitFor( Console console )
	{
		if ( _targetRunner != null ) {
			try {
				synchronized ( _targetRunner ) {
					_targetRunner.join();
					_targetRunner = null;
				}
			}
			catch ( InterruptedException ie ) {
				return false;
			}
		}
		return true;
	}


	// ----- End Shell implementation -----

	private void setCurrentProject( Project currentProject, File currentBuildFile, Console console )
	{
		boolean printMessage = false;
		if ( !currentBuildFile.equals( _currentBuildFile ) )
			printMessage = true;

		_currentBuildFile = currentBuildFile;
		_currentProject = currentProject;

		if ( console.getShell() == AntFarmPlugin.ANT_SHELL && printMessage ) {
			printCurrentProjectInfo( console.getInfoColor(), console );
		}
	}


	private AntFarm getAntFarm( Console console )
	{
		AntFarm antBrowser = (AntFarm) console.getView().getDockableWindowManager().getDockable( "antfarm" );
		if ( antBrowser != null ) {
			return antBrowser;
		}
		if ( _antBrowser == null ) {
			_antBrowser = new AntFarm( console.getView() );
		}
		return _antBrowser;
	}


	private void printCurrentProjectInfo( Color color, Output output )
	{
		if ( _currentProject == null )
			return;
		output.print(
			null,
			jEdit.getProperty( AntFarmPlugin.NAME + ".shell.label.current-file" )
			 );
		String projectName = _currentProject.getName() != null ? _currentProject.getName() : "Untitled";

		output.print( color, projectName + " ("
			 + _currentBuildFile.getAbsolutePath() + ")\n" );

		output.print(
			null,
			jEdit.getProperty( AntFarmPlugin.NAME + ".shell.label.available-targets" )
			 );
		String info = "";
		Enumeration targets = _currentProject.getTargets().keys();
		while ( targets.hasMoreElements() ) {
			String target = (String) targets.nextElement();
			info += target + printDefaultLabel( _currentProject, target ) + "\t";
		}
		output.print( color, info );

	}


	private String printDefaultLabel( Project project, String target )
	{
		if ( project.getDefaultTarget().equals( target ) )
			return " [default]";
		return "";
	}


	private void printUsage( String additionalMessage, Color color, Output output )
	{
		output.print( color, additionalMessage );
		output.print(
			null,
			jEdit.getProperty( AntFarmPlugin.NAME + ".shell.msg.usage" )
			 );
	}
}

