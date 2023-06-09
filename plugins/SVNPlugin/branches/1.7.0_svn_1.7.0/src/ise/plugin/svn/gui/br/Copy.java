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

package ise.plugin.svn.gui.br;

import java.awt.event.ActionEvent;
import javax.swing.tree.TreePath;
import ise.plugin.svn.action.*;
import ise.plugin.svn.gui.CopyDialog;
import ise.plugin.svn.gui.DirTreeNode;
import ise.plugin.svn.data.*;
import ise.plugin.svn.library.GUIUtils;
import java.util.*;


public class Copy extends BRAction {

    public void actionPerformed( ActionEvent ae ) {
        TreePath[] tree_paths = tree.getSelectionPaths();
        if ( tree_paths.length == 0 ) {
            return ;
        }
        boolean hasDirectory = false;
        List<String> paths = new ArrayList<String>();
        for ( TreePath path : tree_paths ) {
            if ( path != null ) {
                Object[] parts = path.getPath();
                StringBuilder sb = new StringBuilder();
                sb.append( parts[ 0 ] );
                for ( int i = 1; i < parts.length; i++ ) {
                    sb.append( "/" ).append( parts[ i ].toString() );
                }
                String url = sb.toString();
                paths.add( url );
                DirTreeNode node = ( DirTreeNode ) path.getLastPathComponent();
                if ( !hasDirectory && !node.isLeaf() ) {
                    hasDirectory = true;
                }
            }
        }
        CopyDialog dialog = new CopyDialog( view, null, repositoryUrl, paths );
        GUIUtils.center( view, dialog );
        dialog.setVisible( true );
        CopyData data = dialog.getData();
        if ( data == null ) {
            return ;     // user canceled
        }

        if ( username != null && password != null ) {
            data.setUsername( username );
            data.setPassword( password );
        }

        CopyAction action = new CopyAction( view, data );
        action.actionPerformed( null );
    }
}
