package ise.plugin.svn.action;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import projectviewer.vpt.VPTNode;
import ise.plugin.svn.command.AddCommand;



public class AddAction extends NodeActor {


    public void actionPerformed( ActionEvent ae ) {
        if ( nodes != null && nodes.size() > 0 ) {
            SwingUtilities.invokeLater( new Runnable() {
                        public void run() {
                            view.getDockableWindowManager().showDockableWindow( "console" );

                            AddCommand command = new AddCommand();
                            String[] params = new String[nodes.size()];
                            for (int i = 0; i < nodes.size(); i++) {
                                params[i] = nodes.get(i).getNodePath();
                            }
                            try {
                                String result = command.execute( params );
                                print( result );
                            }
                            catch ( Exception e ) {
                                printError( e.getMessage() );
                            }
                        }
                    }
                                      );
        }
    }
}
