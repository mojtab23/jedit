/**
 * ResultSetWindowPopup.java - Sql Plugin
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
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import org.gjt.sp.jedit.*;
import org.gjt.sp.util.*;

/**
 * A popup menu for BufferList.
 *
 * @author     svu
 * @created    3 �������� 2001 �.
 */
public class ResultSetWindowPopup extends JPopupMenu
{
  protected JTable table;
  protected Point point;


  /**
   *Constructor for the ResultSetWindowPopup object
   *
   * @param  view   Description of Parameter
   * @param  table  Description of Parameter
   * @param  point  Description of Parameter
   * @since
   */
  public ResultSetWindowPopup( JTable table, Point point )
  {
    this.table = table;
    this.point = point;
    final JMenuItem mi = createMenuItem( "copy_cell" );
    add( mi );
    mi.addActionListener(
      new ActionListener()
      {
        public void actionPerformed( ActionEvent evt )
        {
          final int row = ResultSetWindowPopup.this.table.rowAtPoint( ResultSetWindowPopup.this.point );
          final int col = ResultSetWindowPopup.this.table.columnAtPoint( ResultSetWindowPopup.this.point );
          if ( row == -1 || col == -1 )
            return;

          final Registers.Register reg = Registers.getRegister( '$' );// clipboard
          if ( reg == null )
          {
            ResultSetWindowPopup.this.table.getToolkit().beep();
            return;
          }
          final TableModel model = ResultSetWindowPopup.this.table.getModel();
	  final Object o = model.getValueAt( row, col );
          Registers.setRegister( '$', o == null ? "null" : o.toString() );
        }
      } );

    add( new JSeparator() );
    add( createCopyMenuItem( "copy_all_csv", ", ", true ) );
    add( createCopyMenuItem( "copy_all_tab", "\t", false ) );

    final TableModel model = table.getModel();
    final int maxC = model.getColumnCount();

    final JMenu showHideColumnsMenu = new JMenu( jEdit.getProperty( "sql.resultSet.popup.showHideColumnsMenu.label" ) );
    JMenuItem shit = new JMenuItem( jEdit.getProperty( "sql.resultSet.popup.showHideColumnsMenu.showAll.label" ) );
    shit.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt )
      {
        for ( int i = maxC; --i>=0; )
          showColumn( i, true );
      }
    } );
    showHideColumnsMenu.add( shit );
    shit = new JMenuItem( jEdit.getProperty( "sql.resultSet.popup.showHideColumnsMenu.hideAll.label" ) );
    shit.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt )
      {
        for ( int i = maxC; --i>=0; )
          showColumn( i, false );
      }
    } );
    showHideColumnsMenu.add( shit ); 
    showHideColumnsMenu.add( new JSeparator() );

    final ActionListener cbital = new ActionListener() {
      public void actionPerformed( ActionEvent evt )
      {
        final JCheckBoxMenuItem cbmi = (JCheckBoxMenuItem)evt.getSource();
        final boolean newState = cbmi.isSelected();
        final String name = cbmi.getText();
        for ( int i = maxC; --i>=0; )
        {
           if ( model.getColumnName( i ).equals( name ) )
           {
             showColumn( i, newState );
             cbmi.setSelected( newState );
             break;
          }
        }
      }
    };
    for ( int c = maxC; --c >= 0;  )
    {
      final String val = model.getColumnName( c );
      final JCheckBoxMenuItem cbit = new JCheckBoxMenuItem( val );
      cbit.setName( val );
      cbit.setActionCommand( val );

      final Boolean shown = (Boolean)table.getClientProperty( "column." + val + ".visible" );
      cbit.setSelected( shown == null ? true : shown.booleanValue() );
      
      cbit.addActionListener( cbital );
      showHideColumnsMenu.add( cbit ); 
    }
    
    add( showHideColumnsMenu );
  }


  protected void showColumn( int idx, boolean show )
  {
    final TableColumn tc = table.getColumnModel().getColumn( idx );
    final String name = table.getModel().getColumnName( idx );
    if ( show ) // selected 
    {
      final Integer mw = (Integer)table.getClientProperty( "column." + name + ".minWidth" );
      tc.setMinWidth( mw == null ? 50 : mw.intValue() );
      final Integer pw = (Integer)table.getClientProperty( "column." + name + ".preferredWidth" );
      tc.setPreferredWidth( pw == null ? 50 : pw.intValue() );
    } else
    {
      table.putClientProperty( "column." + name + ".minWidth",
                               new Integer( tc.getMinWidth() ) );
      table.putClientProperty( "column." + name + ".preferredWidth",
                               new Integer( tc.getPreferredWidth() ) );
      tc.setMinWidth( 0 );
      tc.setPreferredWidth( 0 );
    }
    table.putClientProperty( "column." + name + ".visible", 
                             new Boolean( show ) );
  }
  
  private JMenuItem createMenuItem( String name )
  {
    final String label = jEdit.getProperty( "sql.resultSet.popup." + name + ".label" );
    final JMenuItem mi = new JMenuItem( label );
    mi.setActionCommand( name );
    return mi;
  }


  private JMenuItem createCopyMenuItem( String name, String delimiter, boolean doCsvize )
  {
    final JMenuItem mi = createMenuItem( name );
    mi.addActionListener( new CopyActionHandler( delimiter, doCsvize ) );
    return mi;
  }


  /**
   *Description of the Method
   *
   * @param  s  Description of Parameter
   * @return    Description of the Returned Value
   * @since
   */
  public static String csvize( String s )
  {
    if ( s.indexOf( ' ' ) == -1 )
      return s;
    return "\"" + s + "\"";
  }


  class CopyActionHandler implements ActionListener
  {
    protected String delimiter;
    protected boolean doCsvize;


    /**
     *Constructor for the CopyActionHandler object
     *
     * @param  delimiter  Description of Parameter
     * @param  doCsvize   Description of Parameter
     * @since
     */
    public CopyActionHandler( String delimiter, boolean doCsvize )
    {
      this.delimiter = delimiter;
      this.doCsvize = doCsvize;
    }


    public void actionPerformed( ActionEvent evt )
    {
      final String actionCommand = evt.getActionCommand();

      final Registers.Register reg = Registers.getRegister( '$' );// clipboard
      if ( reg == null )
      {
        table.getToolkit().beep();
        return;
      }

      final TableModel model = table.getModel();
      final StringBuffer sb = new StringBuffer();

      final int maxR = model.getRowCount();
      final int maxC = model.getColumnCount();

      for ( int c = maxC; --c >= 0;  )
      {
        final String val = model.getColumnName( c );
        sb.insert( 0,
            doCsvize ? csvize( val ) : val );
        if ( c != 0 )
          sb.insert( 0, delimiter );
      }

      for ( int r = 0; r < maxR; r++ )
      {
        sb.append( '\n' );

        final StringBuffer rowb = new StringBuffer();
        for ( int c = maxC; --c >= 0;  )
        {
          final Object o = model.getValueAt( r, c );
          final String val = o == null ? "null" : o.toString();
          rowb.insert( 0,
              doCsvize ? csvize( val ) : val );
          if ( c != 0 )
            rowb.insert( 0, delimiter );
        }

        sb.append( new String( rowb ) );
      }

      Registers.setRegister( '$', new String( sb ) );

    }
  }
}

