/*
 * $Revision: 1.5 $
 * $Date: 2002-07-26 15:36:20 $
 * $Author: lio-sand $
 *
 * Copyright (C) 2001 C. Scott Willy
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

package cswilly.jeditPlugins.spell;

import cswilly.spell.FileSpellChecker;
import cswilly.spell.SpellException;

import org.gjt.sp.jedit.EditPlugin;
import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.OperatingSystem;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.OptionsDialog;
import org.gjt.sp.jedit.gui.StatusBar;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.util.Log;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class SpellCheckPlugin
  extends EditPlugin
{
  public static final String PLUGIN_NAME                        = "SpellCheck";
  public static final String SPELL_CHECK_ACTIONS                = "spell-check-menu";
  public static final String ASPELL_EXE_PROP                    = "spell-check-aspell-exe";
  public static final String ASPELL_LANG_PROP                   = "spell-check-aspell-lang";
  public static final String ASPELL_NO_MARKUP_MODE              = "spell-check-aspell-no-markup-mode";
  public static final String ASPELL_MANUAL_MARKUP_MODE          = "spell-check-aspell-manual-markup-mode";
  public static final String ASPELL_AUTO_MARKUP_MODE            = "spell-check-aspell-auto-markup-mode";
  public static final String ASPELL_OTHER_PARAMS                = "spell-check-aspell-other-params";

  public static final ArrayList defaultModes = new ArrayList( Arrays.asList( new String[] {"html", "shtml", "sgml", "xml", "xsl"} ) );

  private static FileSpellChecker _fileSpellChecker = null;

  private static String aspellMainLanguage;
  private static String aspellCommandLine;


  /**
  * Method called by jEdit to initialize the plugin.
  */
  //public void start() {}

  /**
  * Method called by jEdit before exiting. Usually, nothing
  * needs to be done here.
  */
  //public void stop() {}

  /**
  * Method called every time a view is created to set up the
  * Plugins menu. Menus and menu items should be loaded using the
  * methods in the GUIUtilities class, and added to the list.
  * @param menuItems Add menuitems here
  */
  public void createMenuItems(Vector menuItems)
  {
    menuItems.addElement( GUIUtilities.loadMenu( SPELL_CHECK_ACTIONS ) );
  }

  /**
  * Method called every time the plugin options dialog box is
  * displayed. Any option panes created by the plugin should be
  * added here.
  * @param optionsDialog The plugin options dialog box
  *
  * @see OptionPane
  * @see OptionsDialog#addOptionPane(OptionPane)
  */
  public void createOptionPanes(OptionsDialog optionsDialog)
  {
    optionsDialog.addOptionPane( new SpellCheckOptionPane() );
  }

  /**
  * Displays the spell checker dialog box with specified lang dictionary. This method
  * is called by the spell-check-selection-with-lang action, defined in actions.xml.
  */
  public static void showCustomLangSpellDialog(View view)
    throws SpellException
  {
    String langDict = GUIUtilities.inputProperty(view, "spell-check-selection-with-lang", PLUGIN_NAME + ".last-lang");
    if ( langDict != null )
    {
      setAspellMainLanguage(langDict);
      showSpellDialog(view);
    }
  }


  /**
  * Displays the spell checker dialog box with default lang dictionary. This method
  * is called by the spell-check-selection action, defined in actions.xml.
  */
  public static void showDefaultLangSpellDialog(View view)
    throws SpellException
  {
    setAspellMainLanguage(jEdit.getProperty( ASPELL_LANG_PROP ) );
    showSpellDialog(view);
  }


  /**
  * Displays the spell checker dialog box.
  */
  public static void showSpellDialog(View view)
    throws SpellException
  {
    StatusBar status = view.getStatus();
    status.setMessage( "Spell check in process..." );

    JEditTextArea textArea = view.getTextArea();
    String selectedText =  textArea.getSelectedText();
    if( selectedText == null )
    {
      textArea.selectAll();
      selectedText = textArea.getText();
    }

    String checkedText = checkBuffer( view, selectedText );

    if ( checkedText == null )
    {
      status.setMessageAndClear( "Spell check cancelled" );
      return;
    }

    if ( !checkedText.equals( selectedText ) )
      textArea.setSelectedText( checkedText );

    status.setMessageAndClear( "Spell check terminated with no error" );
  }


  private static
  String checkBuffer( View view, String buffer )
  {
    String checkedBuffer;
    FileSpellChecker checker = null;

    // Construct aspell command line arguments
    String aspellCommandLine = "";

    String mode = view.getBuffer().getMode().getName();
    if ( getAspellManualMarkupMode() || ( getAspellAutoMarkupMode() && isMarkupModeSelected( mode ) ) )
      aspellCommandLine += " --mode=sgml";

    String aspellMainLanguage = getAspellMainLanguage();
    if ( !aspellMainLanguage.equals("") )
      aspellCommandLine += " --lang=" + aspellMainLanguage + " --language-tag=" + aspellMainLanguage;

    String aspellOtherParams = getAspellOtherParams();
    if ( !aspellMainLanguage.equals("") )
      aspellCommandLine += " " + aspellOtherParams;

    aspellCommandLine += " pipe";

    setAspellCommandLine( aspellCommandLine);

    try
    {
      BufferedReader input  = new BufferedReader( new StringReader( buffer ) );
      StringWriter stringWriter = new StringWriter( buffer.length() );
      BufferedWriter output = new BufferedWriter( stringWriter );

      checker = _getFileSpellChecker();

      if ( checker == null )
        throw new SpellException("No or invalid executable specified");

      boolean checkingNotCanceled =  checker.checkFile( input, output );

      // Restore buffer correct line separator if any
      if ( buffer.endsWith("\n") )
        output.write( '\n' );

      input.close();
      output.close();

      if ( checkingNotCanceled )
        checkedBuffer = stringWriter.toString();
      else
        checkedBuffer = null;
    }
    catch( SpellException e )
    {
      Log.log(Log.ERROR, SpellCheckPlugin.class, "Error spell checking (Aspell).");
      Log.log(Log.ERROR, SpellCheckPlugin.class, e);
      Object[] args = { new String (e.getMessage()) };
      GUIUtilities.error( view, "spell-check-error", args);
      checkedBuffer = null;
    }
    catch( IOException e )
    {
      Log.log(Log.ERROR, SpellCheckPlugin.class, "Error spell checking (plugin).");
      Log.log(Log.ERROR, SpellCheckPlugin.class, e);
      Object[] args = { new String (e.getMessage()) };
      GUIUtilities.error( view, "spell-check-error", args);
      checkedBuffer = null;
    }

// ??? May this a configurable option
//    if( checker != null )
//      checker.stop();

    return checkedBuffer;
  }

  private static
  FileSpellChecker _getFileSpellChecker()
  {
    String  aspellExeFilename = getAspellExeFilename();
    String  aspellCommandLine = getAspellCommandLine();

    if ( aspellExeFilename == null )
      return null;

    if( _fileSpellChecker == null )
      _fileSpellChecker = new FileSpellChecker( aspellExeFilename, aspellCommandLine );
    else
      if( !aspellExeFilename.equals( _fileSpellChecker.getAspellExeFilename() )
       || !aspellCommandLine.equals( _fileSpellChecker.getAspellCommandLine() ) )
      {
        _fileSpellChecker.stop();
        _fileSpellChecker = new FileSpellChecker( aspellExeFilename, aspellCommandLine );
      }

    return _fileSpellChecker;
  }

  private static
  String getAspellExeFilename()
  {
    String aspellExeFilename = jEdit.getProperty( ASPELL_EXE_PROP );

    if( aspellExeFilename == null || aspellExeFilename.equals("") )
    {
      if ( OperatingSystem.isUnix() )
        aspellExeFilename = "aspell";
      else
      {
        GUIUtilities.message(null, "spell-check-noAspellExe", null);
        String[] paths = GUIUtilities.showVFSFileDialog( null, null, JFileChooser.OPEN_DIALOG, false );

        if (paths != null)
          aspellExeFilename = paths[0];
        else
        {
          return null;
        }
      }
      jEdit.setProperty( SpellCheckPlugin.ASPELL_EXE_PROP, aspellExeFilename );
    }

    return aspellExeFilename;
  }

  private static
  String getAspellCommandLine()
  {
    if( aspellCommandLine == null )
      aspellCommandLine = "";

    return aspellCommandLine;
  }

  private static
  void setAspellCommandLine(String newCommandLine)
  {
    aspellCommandLine = newCommandLine;
  }

  private static
  String getAspellMainLanguage()
  {
    if( aspellMainLanguage == null )
      aspellMainLanguage = "";

    return aspellMainLanguage;
  }

  private static
  void setAspellMainLanguage(String newLanguage)
  {
    aspellMainLanguage = newLanguage;
  }

  private static
  String getAspellOtherParams()
  {
    return jEdit.getProperty( ASPELL_OTHER_PARAMS, "");
  }

  private static
  boolean getAspellManualMarkupMode()
  {
    return jEdit.getBooleanProperty( ASPELL_MANUAL_MARKUP_MODE, false);
  }

  private static
  boolean getAspellAutoMarkupMode()
  {
    return jEdit.getBooleanProperty( ASPELL_AUTO_MARKUP_MODE, true);
  }

  private static
  boolean isMarkupModeSelected ( String editMode )
  {
    boolean defaultVal;
    if ( defaultModes.contains( editMode ) )
      return jEdit.getBooleanProperty("spell-check-mode-" + editMode + "-isSelected", true);
    else
      return jEdit.getBooleanProperty("spell-check-mode-" + editMode + "-isSelected", false);
  }

  public static
  Vector getAlternateLangDictionaries()
  {
    String dictDirPath = null;
    String line;
    String aspellExeFilename = getAspellExeFilename();
    Vector langs = new Vector();
    try
    {
      // first we try to dump the aspell config into a BufferedReader
      BufferedReader input  = new BufferedReader( new InputStreamReader( Runtime.getRuntime().exec( aspellExeFilename + " dump config" ).getInputStream() ) );
      // then we search for the dict-dir keyword to pick up its value
      while ( ( line = input.readLine() ) != null )
      {
        if ( line.indexOf( "dict-dir" ) > 0 )
          dictDirPath = line.trim().substring( line.lastIndexOf( " " ) + 1 );
      }
      input.close();
      if ( dictDirPath != null )
      {
        // ok, we have found it
        File dictDir = new File( dictDirPath );
        // now, we check that the value found is a directory...
        if ( dictDir.isDirectory() )
        {
          // ...inside which we get all installed dictionaries (all files)
          File[] langFiles = dictDir.listFiles();
          for (int i = 0; i < langFiles.length; i++)
          {
            // we remove suffix if any
            String dict = langFiles[i].getName();
            int pos = dict.lastIndexOf(".");
            if ( pos >= 0 )
              dict = dict.substring(0,pos);
            // and then add the dictionary into the vector unless it is already there
            if ( ! langs.contains(dict) )
              langs.add( dict );
          }
        }
      }
    }
    catch ( IOException e )
    {
    }
    return langs;
  }
}