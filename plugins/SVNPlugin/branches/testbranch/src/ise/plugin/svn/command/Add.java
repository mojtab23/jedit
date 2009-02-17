/*
Copyright (c) 2007, Dale Anson
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.
* Neither the name of the author nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package ise.plugin.svn.command;

import java.io.*;
import java.util.*;

import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNEvent;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;

import ise.plugin.svn.data.SVNData;
import ise.plugin.svn.data.AddResults;

/**
 * Performs a subversion "add" command.
 */
public class Add {

	/**
	 * @param cd SVNData containing a list of paths to be added
	 * @return a list of paths that were scheduled to be added.
	 */
	public AddResults add( SVNData cd ) throws CommandInitializationException, SVNException {
		SVNKit.setupLibrary();

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
		SVNClientManager clientManager = SVNClientManager.newInstance( options, new BasicAuthenticationManager(cd.getUsername(), cd.getDecryptedPassword()) );

		// get a commit client
		SVNWCClient client = clientManager.getWCClient();

		// put the add output here
		final AddResults results = new AddResults();

		// set an event handler so that messages go to the commit data streams for display
		client.setEventHandler( new SVNCommandEventProcessor( cd.getOut(), cd.getErr(), false ) {
			@Override
			public void handleEvent(SVNEvent event, double progress) {
				super.handleEvent(event, progress);
				if (event.getFile() != null) {
					results.addPath(event.getFile().toString());
				}
			}
		});

		// actually do the add(s)
		PrintStream out = cd.getOut();
		for ( String path : paths ) {
			// path, force, mkdir, add parents, climbUnversionedParents, depth, includeIgnored, makeParents
			try {
				File file = new File(path);
				client.doAdd( file, false, false, true, SVNDepth.fromRecurse(cd.getRecursive()), false, true );
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
