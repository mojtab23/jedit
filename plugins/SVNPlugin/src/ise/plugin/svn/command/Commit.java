package ise.plugin.svn.command;

import java.io.*;
import java.util.*;

import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.cli.command.SVNCommandEventProcessor;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;


public class Commit {

    public void commit( CommitData cd ) throws CommandInitializationException, SVNException {

        // validate commit data values
        if (cd.getPaths() == null) {
            return;     // nothing to do
        }
        if (cd.getOut() == null) {
            throw new CommandInitializationException("Invalid output stream.");
        }
        if (cd.getErr() == null) {
            cd.setErr(cd.getOut());
        }
        if (cd.getCommitMessage() == null) {
            cd.setCommitMessage("");
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
        SVNCommitClient client = clientManager.getCommitClient();

        // set an event handler so that messages go to the commit data streams for display
        client.setEventHandler( new SVNCommandEventProcessor( cd.getOut(), cd.getErr(), false ) );

        // actually do the commit
        SVNCommitInfo result = client.doCommit( localPaths, cd.getKeepLocks(), cd.getCommitMessage(), false, cd.getRecursive() );

        // handle the results
        PrintStream out = cd.getOut();
        if ( result != SVNCommitInfo.NULL ) {
            out.println();
            out.println( "Committed revision " + result.getNewRevision() + "." );
            out.flush();
        }
        else {
            out.println();
            if ( result.getErrorMessage() != null ) {
                out.println("Commit failed:");
                out.println( result.getErrorMessage() );
            }
            else {
                out.println("Commit failed.");
            }
            out.flush();
        }
        out.close();
    }
}
