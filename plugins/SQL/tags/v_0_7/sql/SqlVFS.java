/**
 * SqlVFS.java - Sql Plugin
 * Copyright (C) 2001 Sergey V. Udaltsov
 * svu@users.sourceforge.net
 *
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
package sql;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.io.*;
import org.gjt.sp.jedit.msg.*;
import org.gjt.sp.util.*;

import SqlPlugin;

/**
 *  SQL VFS "sql:/server/tablespace/table"
 *
 * @author     svu
 * @created    26 ������ 2001 �.
 */
public class SqlVFS extends VFS
{
  /**
   *  Description of the Field
   *
   * @since
   */
  public final static String separatorString = "/";
  /**
   *  Description of the Field
   *
   * @since
   */
  public final static char separatorChar = '/';
  /**
   *  Description of the Field
   *
   * @since
   */
  public final static String PROTOCOL = "sql";

  /**
   *  Description of the Field
   *
   * @since
   */
  public final static int ERROR_LEVEL = -1;
  /**
   *  Description of the Field
   *
   * @since
   */
  public final static int ROOT_LEVEL = 0;
  /**
   *  Description of the Field
   *
   * @since
   */
  public final static int DB_LEVEL = 1;

  /**
   *  Description of the Field
   *
   * @since
   */
  public final static String RUN_ON_LOAD_PROPERTY = "run_on_load";


  /**
   *  Constructor for the SqlVFS object
   *
   * @since
   */
  public SqlVFS()
  {
    super( "sql" );
    EditBus.addToBus( new LoadListener() );
  }


  /**
   *  Gets the Capabilities attribute of the SqlVFS object
   *
   * @return    The Capabilities value
   * @since
   */
  public int getCapabilities()
  {
    return BROWSE_CAP
         | READ_CAP
    //  | DELETE_CAP
        ;
  }


  /**
   *  Gets the ParentOfPath attribute of the SqlVFS object
   *
   * @param  path  Description of Parameter
   * @return       The ParentOfPath value
   * @since
   */
  public String getParentOfPath( String path )
  {
    return MiscUtilities.getParentOfPath( path );
  }


  /**
   *  Description of the Method
   *
   * @param  session  Description of Parameter
   * @param  comp     Description of Parameter
   * @return          Description of the Returned Value
   * @since
   */
  public String showBrowseDialog( Object[] session, Component comp )
  {
    return PROTOCOL + ":/";
  }


  /**
   *  Description of the Method
   *
   * @param  session          Description of Parameter
   * @param  path             Description of Parameter
   * @param  comp             Description of Parameter
   * @return                  Description of the Returned Value
   * @exception  IOException  Description of Exception
   * @since
   */
  public VFS.DirectoryEntry[] _listDirectory( Object session, String path,
      Component comp )
       throws IOException
  {
    Log.log( Log.DEBUG, SqlVFS.class,
        "Listing " + path );
    VFS.DirectoryEntry[] retval = null;

    path = normalize( path );
    final int level = getPathLevel( path );

    int i;
    int idx;
    SqlServerRecord rec;

    switch ( level )
    {
      case ROOT_LEVEL:
        final Hashtable recs = SqlServerRecord.getAllRecords();
        retval = new VFS.DirectoryEntry[recs.size()];
        i = 0;
        for ( Enumeration e = recs.elements(); e.hasMoreElements();  )
        {
          final SqlServerRecord r = (SqlServerRecord) e.nextElement();
          retval[i++] =
              _getDirectoryEntry( session, path + separatorString + r.getName(), comp );
        }
        break;
      default:
        rec = getServerRecord( path );

        if ( rec != null )
          retval = rec.getServerType().getSubVFS()._listDirectory( session, path, comp, rec, level );
        else
          retval = null;
    }
    Log.log( Log.DEBUG, SqlVFS.class,
        "Listed total " + ( retval == null ? -1 : retval.length ) + " items" );
    return retval;
  }


  /**
   *  Description of the Method
   *
   * @param  session          Description of Parameter
   * @param  path             Description of Parameter
   * @param  comp             Description of Parameter
   * @return                  Description of the Returned Value
   * @exception  IOException  Description of Exception
   * @since
   */
  public DirectoryEntry _getDirectoryEntry( Object session, String path,
      Component comp )
       throws IOException
  {
    path = normalize( path );

    final int level = getPathLevel( path );
    if ( level == ERROR_LEVEL )
      return null;

    if ( level <= DB_LEVEL )
      return new VFS.DirectoryEntry( path, path, path,
          VFS.DirectoryEntry.FILESYSTEM, 0L, false );

    final SqlServerRecord rec = getServerRecord( path );
    if ( rec != null )
      return rec.getServerType().getSubVFS()._getDirectoryEntry( session, path, comp, level );
    else
      return null;
  }


  /**
   *  Description of the Method
   *
   * @param  parent  Description of Parameter
   * @param  path    Description of Parameter
   * @return         Description of the Returned Value
   * @since
   */
  public String constructPath( String parent, String path )
  {
    if ( parent.endsWith( separatorString ) )
      return parent + path;
    else
      return parent + separatorString + path;
  }


  /**
   *  Description of the Method
   *
   * @param  view    Description of Parameter
   * @param  buffer  Description of Parameter
   * @param  path    Description of Parameter
   * @return         Description of the Returned Value
   * @since
   */
  public boolean load( final View view, final Buffer buffer, final String path )
  {
    if ( !super.load( view, buffer, path ) )
      return false;

    final SqlServerRecord rec = getServerRecord( path );
    if ( rec != null )
      return rec.getServerType().getSubVFS().afterLoad( this, view, buffer, path, getPathLevel( path ) );

    return true;
  }


  /**
   *  Description of the Method
   *
   * @param  session          Description of Parameter
   * @param  path             Description of Parameter
   * @param  ignoreErrors     Description of Parameter
   * @param  comp             Description of Parameter
   * @return                  Description of the Returned Value
   * @exception  IOException  Description of Exception
   * @since
   */
  public InputStream _createInputStream( Object session, String path,
      boolean ignoreErrors, Component comp ) throws IOException
  {
    final SqlServerRecord rec = getServerRecord( path );
    if ( rec != null )
      return rec.getServerType().getSubVFS()._createInputStream( this, session, path, ignoreErrors, comp, getPathLevel( path ) );

    return super._createInputStream( session, path, ignoreErrors, comp );
  }


  /**
   *  Gets the PathLevel attribute of the SqlVFS class
   *
   * @param  path  Description of Parameter
   * @return       The PathLevel value
   * @since
   */
  public static int getPathLevel( String path )
  {
    if ( path == null || !path.startsWith( PROTOCOL + ":" ) )
      return ERROR_LEVEL;

    String lpath = path.substring( PROTOCOL.length() + 1 );

    if ( lpath.length() == 0 )
      return ROOT_LEVEL;

    // skip initial '/'
    lpath = lpath.substring( 1 );

    int i = lpath.indexOf( separatorChar );

    int level = DB_LEVEL;

    while ( i != -1 )
    {
      i = lpath.indexOf( separatorChar, i + 1 );
      level++;
    }
    return level;
  }


  /**
   *  Gets the PathComponent attribute of the SqlVFS class
   *
   * @param  path   Description of Parameter
   * @param  level  Description of Parameter
   * @return        The PathComponent value
   * @since
   */
  public static String getPathComponent( String path, int level )
  {
    if ( path == null || !path.startsWith( PROTOCOL + ":" ) )
      return null;

    String lpath = path.substring( PROTOCOL.length() + 1 );

    int curlevel = 0;
// sql:/asdfd/
    int lasti = 0;
    int i = lpath.indexOf( separatorChar );

    while ( i != -1 && level > curlevel )
    {
      lasti = i + 1;
      i = lpath.indexOf( separatorChar, i + 1 );
      curlevel++;
    }

    if ( i == -1 )
    {
      if ( level > curlevel )
        return null;
      else
        i = lpath.length();
    }

    return lpath.substring( lasti, i );
  }


  /**
   *  Gets the ServerRecord attribute of the SqlVFS class
   *
   * @param  path  Description of Parameter
   * @return       The ServerRecord value
   * @since
   */
  public static SqlServerRecord getServerRecord( String path )
  {
    Log.log( Log.DEBUG, SqlVFS.class,
        "Looking for record for path " + path );
    path = normalize( path );
    final String recName = getPathComponent( path, DB_LEVEL );
    if ( recName == null )
    {
      Log.log( Log.DEBUG, SqlVFS.class,
          "Rec not found" );
      return null;
    }
    Log.log( Log.DEBUG, SqlVFS.class,
        "Rec for " + recName + " found " + SqlServerRecord.get( recName ) );
    return SqlServerRecord.get( recName );
  }


  /**
   *  Description of the Method
   *
   * @param  args  Description of Parameter
   * @since
   */
  public static void main( String args[] )
  {
    final String strs[] =
        {
        "",
        "sql:/",
        "sql:/aa",
        "sql:/aa/",
        "sql:/aa/bb",
        "sql:/aa/bb/",
        "sql:/aa/bb/cc",
        "sql:/aa/bb/cc/",
        "sql:/aa/bb/cc/dd",
        "sql:/aa/bb/cc/dd/"
        };

    for ( int i = 0; i < strs.length; i++ )
    {
      System.err.println( "[" + strs[i] + "] gives us: " );
      System.err.print( "[" );
      for ( int j = 0; j < 6; j++ )
        System.err.print( getPathComponent( strs[i], j ) + "|" );

      System.err.println( "]" );
    }
  }


  /**
   *  Description of the Method
   *
   * @param  path  Description of Parameter
   * @return       Description of the Returned Value
   * @since
   */
  public static String normalize( String path )
  {
    if ( path.endsWith( separatorString ) )
      return path.substring( 0, path.length() - 1 );

    return path;
  }


  public static class LoadListener implements EBComponent
  {

    public void handleMessage( EBMessage msg )
    {
      if ( !( msg instanceof BufferUpdate ) )
        return;

      final BufferUpdate umsg = (BufferUpdate) msg;
      if ( umsg.getWhat() != umsg.LOADED )
        return;

      final Buffer buffer = umsg.getBuffer();
      final VFS vfs = buffer.getVFS();
      if ( !( vfs instanceof SqlVFS ) )
        return;

      final SqlServerRecord rec = getServerRecord( buffer.getPath() );
      if ( rec != null )
        SqlPlugin.setBufferMode( buffer,
            rec.getServerType().getEditModeName() );

      View view = umsg.getView();
      if ( view == null )
        view = jEdit.getLastView();

      if ( buffer.getBooleanProperty( RUN_ON_LOAD_PROPERTY ) &&
          view != null )
      {
        final String serverName =
            getPathComponent( buffer.getPath(), DB_LEVEL );

        SqlUtils.publishText(
            view,
            buffer,
            0,
            buffer.getLength(),
            serverName );
      }
    }
  }

}

