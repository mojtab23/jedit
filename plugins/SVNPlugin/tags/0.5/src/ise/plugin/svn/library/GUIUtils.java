// $Id: GUIUtils.java,v 1.4 2004/05/08 02:42:56 danson Exp $
/*
* Based on the Apache Software License, Version 1.1
*
* Copyright (c) 2002 Dale Anson.  All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
*
* 1. Redistributions of source code must retain the above copyright
*    notice, this list of conditions and the following disclaimer.
*
* 2. Redistributions in binary form must reproduce the above copyright
*    notice, this list of conditions and the following disclaimer in
*    the documentation and/or other materials provided with the
*    distribution.
*
* 3. The end-user documentation included with the redistribution, if
*    any, must include the following acknowlegement:
*       "This product includes software developed by Dale Anson,
*        danson@users.sourceforge.net."
*    Alternately, this acknowlegement may appear in the software itself,
*    if and wherever such third-party acknowlegements normally appear.
*
* 4. The name "Antelope" must not be used to endorse or promote products derived
*    from this software without prior written permission. For written
*    permission, please contact danson@users.sourceforge.net.
*
* 5. Products derived from this software may not be called "Antelope"
*    nor may "Antelope" appear in their names without prior written
*    permission of Dale Anson.
*
* THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED.  IN NO EVENT SHALL DALE ANSON OR ANY PROJECT
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
* SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
* LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
* USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
* OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
* OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
* SUCH DAMAGE.
* ====================================================================
*/
package ise.plugin.svn.library;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Some GUI utility methods that I seem to use over and over, so I put them
 * here in one place. Could be easily modified to be AWT-only utilities.
 *
 * @author    Dale Anson
 * @version   $Revision: 1.4 $
 */
public class GUIUtils {

   /**
    * Centers <code>you</code> on <code>me</code>. Useful for centering
    * dialogs on their parent frames.
    *
    * @param me   Component to use as basis for centering.
    * @param you  Component to center on <code>me</code>.
    */
   public static void center( Component me, Component you ) {
      Rectangle my = me.getBounds();
      Dimension your = you.getSize();
      int x = my.x + ( my.width - your.width ) / 2;
      if ( x < 0 )
         x = 0;
      int y = my.y + ( my.height - your.height ) / 2;
      if ( y < 0 )
         y = 0;
      you.setLocation( x, y );
   }

   /**
    * Centers a component on the screen.
    *
    * @param me  Component to center.
    */
   public static void centerOnScreen( Component me ) {
      Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension window_size = me.getSize();
      me.setBounds( ( screen_size.width - window_size.width ) / 2,
            ( screen_size.height - window_size.height ) / 2,
            window_size.width,
            window_size.height );
   }

   /**
    * Expands a component to fill the screen, just like a 'maximize window'.
    *
    * @param frame the component to expand
    */
   public static void fillScreen( Component frame ) {
      String version = System.getProperty( "java.version" );
      if ( version.startsWith( "1.4" ) || version.startsWith( "1.5" ) ) {
         if ( frame instanceof Frame ) {
            ( ( Frame ) frame ).setExtendedState( Frame.MAXIMIZED_BOTH );
            return ;
         }
      }
      Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
      frame.setSize( screen_size );
      centerOnScreen( frame );
   }

   /**
    * @param c  a Component
    * @return   the Frame containing the component or null if the component
    * doesn't have a containing Frame.
    */
   public static Frame getRootFrame( Component c ) {
      Object parent = c.getParent();
      while ( parent != null ) {
         if ( parent instanceof Frame )
            return ( Frame ) parent;
         parent = ( ( Component ) parent ).getParent();
      }
      return null;
   }

   /**
    * This is the only method that relies on Swing.  Comment this one out and
    * this class is AWT-only capable.
    *
    * @param c  a Component
    * @return   the JFrame containing the component or null if the component
    * doesn't have a containing JFrame.
    */
   public static javax.swing.JFrame getRootJFrame( Component c ) {
      Object parent = c.getParent();
      while ( parent != null ) {
         if ( parent instanceof javax.swing.JFrame )
            return ( javax.swing.JFrame ) parent;
         parent = ( ( Component ) parent ).getParent();
      }
      return null;
   }

   /**
    * @param c  a Component
    * @return   the Window containing the component or null if the component
    * doesn't have a containing Window.
    */
   public static Window getRootWindow( Component c ) {
      Object parent = c.getParent();
      while ( parent != null ) {
         if ( parent instanceof Window )
            return ( Window ) parent;
         parent = ( ( Component ) parent ).getParent();
      }
      return null;
   }

   /**
    * Calculates the best location to show the component based on the given (x, y)
    * coordinates. The returned point will be as close as possible to the original
    * point while allowing the entire component to be displayed on screen. This is
    * useful for showing dialogs and popups.
    * @param comp the component that will be shown.
    * @param x the original x-coordinate of the component or of the desired location.
    * @param y the original y-coordinate of the component or of the desired location.
    * @return a point as close to the given (x, y) that will allow the entire
    * component to be shown on the screen.
    */
   public static Point getBestAnchorPoint( Component comp, int x, int y ) {
      int new_x = x;
      int new_y = y;
      Point p = new Point( new_x, new_y );
      javax.swing.SwingUtilities.convertPointToScreen( p, comp );

      Dimension size = comp.getSize();
      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

      boolean move_horizontally = false;
      boolean move_vertically = false;

      // calculate new x coordinate. If the component width is less than the
      // screen width and the right side of the component is off the screen,
      // move it left.
      if ( p.x + size.width > screen.width
              && size.width < screen.width ) {
         new_x += ( screen.width - p.x - size.width );
         move_horizontally = true;
      }

      // calculate new y coordinate. If the component height is less than the
      // screen height and the bottom of the component is off the screen, move
      // it up.
      if ( p.y + size.height > screen.height
              && size.height < screen.height ) {
         new_y += ( screen.height - p.y - size.height );
         move_vertically = true;
      }

      // If the component is a popup and it needed to be moved both horizontally
      // and vertically, the mouse pointer might end up over a menu item, which
      // will be invoked when the mouse is released. In this case, move the
      // component to a location that is not under the point.
      if ( move_horizontally && move_vertically && (comp instanceof javax.swing.JPopupMenu) ) {
         // first try to move it more left
         if (x - size.width - 2 > 0)
            new_x = x - size.width - 2;
         else if (y - size.height - 2 > 0){
            // try to move it up some more
            new_y = y - size.height - 2;
         }
      }
      return new Point( new_x, new_y );
   }

   /**
    * Shows the specified popup menu, ensuring it is displayed within
    * the bounds of the screen.
    *
    * @param popup  The popup menu
    * @param comp   The component to show it for
    * @param x      The x coordinate
    * @param y      The y coordinate
    */
   public static void showPopupMenu( javax.swing.JPopupMenu popup, Component comp,
         int x, int y ) {
      Point p = getBestAnchorPoint( comp, x, y );
      popup.show( comp, p.x, p.y );
   }


   public static JPanel createTablePanel(JTable table) {
        JPanel table_panel = new JPanel(new BorderLayout());
        table_panel.add(table.getTableHeader(), BorderLayout.NORTH);
        table_panel.add(table, BorderLayout.CENTER);
        return table_panel;
   }

}

