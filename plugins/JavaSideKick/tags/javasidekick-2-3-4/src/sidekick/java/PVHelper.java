/*
Copyright (c) 21st century, Dale Anson
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.
* Neither the name of the <SOME OBSCURE ORGANIZATION> nor the names of its
contributors (whoever they may be) may be used to endorse or promote products
derived from this software without specific prior written permission.

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

package sidekick.java;

import java.io.File;
import java.util.*;
import org.gjt.sp.jedit.*;
import projectviewer.*;
import projectviewer.vpt.*;
import sidekick.java.util.*;


public class PVHelper {

    /**
     * @return the name of the project containing the given filename
     */
    public static String getProjectNameForFile( String filename ) {
        if (!isProjectViewerAvailable())
            return null;
        ProjectManager pm = ProjectManager.getInstance();
        for ( Iterator it = pm.getProjects(); it.hasNext(); ) {
            VPTProject project = ( VPTProject ) it.next();
            VPTNode node = project.getChildNode( filename );
            if ( node != null ) {
                return project.getName();
            }
        }
        return null;
    }

    /**
     * @return true if the ProjectViewer plugin is loaded
     */
    public static boolean isProjectViewerAvailable() {
        EditPlugin pv = jEdit.getPlugin( "projectviewer.ProjectPlugin", false );
        return pv != null;
    }

    /**
     * @return a Path containing the classpath as set in ProjectViewer for the given project
     */
    public static Path getClassPathForProject(String projectName) {
        boolean useJavaClasspath = jEdit.getBooleanProperty( "sidekick.java.pv." + projectName + ".useJavaClasspath" );
        //System.out.println("+++++ useJavaClasspath = " + useJavaClasspath);
        String classpath = jEdit.getProperty("sidekick.java.pv." + projectName + ".optionalClasspath", "");
        Path path = new Path(classpath);
        if (useJavaClasspath) {
            path.concatSystemClassPath();
        }
        //System.out.println("+++++ PVHelper, classpath for project " + projectName + " = " + path.toString());
        return path;
    }

    /**
     * @return a Path containing the sourcepath as set in ProjectViewer for the given project
     */
    public static Path getSourcePathForProject(String projectName) {
        String sourcepath = jEdit.getProperty("sidekick.java.pv." + projectName + ".optionalSourcepath", "");
        Path path = new Path(sourcepath);
        return path;
    }
}
