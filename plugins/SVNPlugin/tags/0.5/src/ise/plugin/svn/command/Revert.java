package ise.plugin.svn.command;

import java.io.*;
import java.util.*;

import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.cli.command.SVNCommandEventProcessor;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import ise.plugin.svn.data.SVNData;
import ise.plugin.svn.data.AddResults;


public class Revert {

    /**
     * @return a list of paths to be reverted.  This is very similar to an "add".
     */
    public AddResults revert( SVNData cd ) throws CommandInitializationException, SVNException {

        // validate data values
        if ( cd.getPaths() == null ) {
            return null;     // nothing to do
        }
        if ( cd.getOut() == null ) {
            throw new CommandInitializationException( "Invalid output stream." );
        }
        if ( cd.getErr() == null ) {
            cd.setErr( cd.getOut() );
        }

        // convert paths to Files
        List<String> paths = cd.getPaths();
        File[] localPaths = new File[ paths.size() ];
        for ( int i = 0; i < paths.size(); i++ ) {
            localPaths[ i ] = new File( paths.get( i ) );
            // check for file existence?
        }

        // use default svn config options
        ISVNOptions options = SVNWCUtil.createDefaultOptions( true );

        // use the svnkit client manager
        SVNClientManager clientManager = SVNClientManager.newInstance( options, cd.getUsername(), cd.getPassword() );

        // get a commit client
        SVNWCClient client = clientManager.getWCClient();

        // set an event handler so that messages go to the commit data streams for display
        client.setEventHandler( new SVNCommandEventProcessor( cd.getOut(), cd.getErr(), false ) );

        // actually do the reverts(s)
        PrintStream out = cd.getOut();
        AddResults results = new AddResults();
        for ( String path : paths ) {
            try {
                File file = new File(path);
                client.doRevert(file, cd.getRecursive());
                results.addPath(path);
            }
            catch ( Exception e ) {
                out.println( e.getMessage() );
                results.addErrorPath(path, e.getMessage());
            }
        }

        out.flush();
        out.close();
        return results;
    }
}
