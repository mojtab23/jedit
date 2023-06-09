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



package buildtools;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.io.File;


/**
Provides a way to determine if files in a directory have changed.

    
@author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>    
@version $Id$
*/

public class FileChangeMonitor {


    /**
    A list of files and their modification times.  The 
    
    */
    private Hashtable files         = new Hashtable();
    private String    directory     = null;
    private String[]  extensions    = new String[0];
    
    
    public class MonitoredFile {
        
        private String  filename        = "";
        private File    file            = null;
        private long    lastModified;
        
        public MonitoredFile(String filename) {
            this.filename = filename;
            this.file = new File(filename);
        }

        public String getFilename() {
            return this.filename;
        }

        public File getFile() {
            return this.file;
        }
        
        public long lastModified() {
            return this.lastModified; 
        }
        
        public void check() {
            this.lastModified = this.getFile().lastModified();
        }

        public boolean isModified() {
            if (this.lastModified() != this.getFile().lastModified()) {
                return true;
            } else {
                return false;
            }
        }
            

    }


    /**
    Looks at the given directory and all files underneath only matching the 
    given extensions.
    
    @author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>    
    @version $Id$
    */
    public FileChangeMonitor(String directory, String[] extensions) {
        
        this.directory = directory;
        this.extensions = extensions;
        this.check();

    }
    
    
    /**
    Returns the files that have been changed for this monitor.
    
    @author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>    
    @version $Id$
    */
    public String[] getChangedFiles() {
        Vector v = new Vector();

        
        Enumeration e = this.files.elements();

        while(e.hasMoreElements()) {
            MonitoredFile file = (MonitoredFile)e.nextElement();            
            if ( file.isModified() ) {
                v.addElement(file.getFilename());
            }

        }


        String[] changed = new String[v.size()];


        v.copyInto(changed);


        return changed;

    }
    
    /**
    Returns all files that are being monitored.
    
    @author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>    
    @version $Id$
    */
    public String[] getAllFiles() {
        Vector v = new Vector();
        
        Enumeration elements = this.files.elements();
        while(elements.hasMoreElements()) {
            MonitoredFile file = (MonitoredFile)elements.nextElement();
            v.addElement( file.getFilename() );
        }

        String[] files = new String[v.size()];
        v.copyInto(files);
        
        return files;

    }

    
    
    /**
    Checks the new modification times of registered files.  And sets their 
    modification time to their new value.  If any files were deleted, they are 
    removed from the list, if any files are created, they are added to the list.
    
    @author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>    
    @version $Id$
    */
    public void check() {
        
        //right now dont' to any fancy merge.  Just get the files and that is it.
        //in the future we can try to figure out which files are new, etc.
        
        String[] files = FileUtils.getFilesFromExtension( this.directory, this.extensions);

        Hashtable newfiles = new Hashtable();
        
        
        for (int i = 0; i < files.length; ++i) {

            MonitoredFile storedFile = (MonitoredFile)this.files.get(files[i]);
            if (storedFile != null) {
                storedFile.check();
                newfiles.put(storedFile.getFilename(), storedFile);
                continue;
            }
            
            MonitoredFile file = new MonitoredFile( files[i] );
            newfiles.put(files[i], file);
            
        }
        
        //reset the current hashtable set of tiles.
        this.files = newfiles;

        

    }
    
    

}
