/*
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

package net.sourceforge.jedit.jcompiler;

//jedit support 
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.gui.*;


//awt
import java.awt.*;
import java.awt.event.*;

//java stuff
import java.io.*;
import java.util.*;
import java.lang.*;

//build support
import net.sourceforge.jedit.buildtools.*;
import net.sourceforge.jedit.buildtools.msg.*;

import net.sourceforge.jedit.pluginholder.*;


/**
@author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>
@version $Id$
*/
public class JCompilerPlugin extends EBPlugin {



    /**
    @author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>
    @version $Id$
    */
    public void start() {
        try {
            NoExitSecurityManager sm = NoExitSecurityManager.getNoExitSM();
            java.lang.System.setSecurityManager(sm);


            //register the plugin
            PluginHolder.registerPlugin( "net.sourceforge.jedit.jcompiler.JCompiler", "jcompiler.open" );

            
            //FIX ME:  Need to add actions for starting JCompiler
            
            //jEdit.addAction( new JCompiler(sm, "jcompiler", false) );
            //jEdit.addAction( new JCompiler(sm, "jpkgcompiler", true) );
            //jEdit.addAction( new JCompiler(sm, "jpkgrebuild", true, true) );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
    @author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>
    @version $Id$
    */
    public void createMenuItems(View view, Vector menus, Vector menuItems) {
        menus.addElement(GUIUtilities.loadMenu(view, "jcompiler-menu"));

    }
    
    /**
    @author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>
    @version $Id$
    */
    public void createOptionPanes(OptionsDialog od) {
        od.addOptionPane( new JCompilerPane()  );
    }
    

    /**
    Handle message for decompile requests..

    @author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>    
    @version $Id$
    */
    public void handleMessage(EBMessage message) {

        if (message instanceof DecompileClassMessage) {
            
            BuildMessage bm = (BuildMessage)((DecompileClassMessage)message).getMessage();

            DecompileClassMessage decompile = (DecompileClassMessage)message;
            

            //FIX ME
            //JCompilerPlugin.setEditorFile( decompile.getFileName(), bm.getLineNumber() );
            //JCompilerPlugin.dlg.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        }

    }


}
