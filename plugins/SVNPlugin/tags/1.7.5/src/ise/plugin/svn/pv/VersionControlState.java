
package ise.plugin.svn.pv;

import projectviewer.config.VersionControlService;
import projectviewer.vpt.VPTFile;
import projectviewer.vpt.VPTNode;
import projectviewer.vpt.VPTProject;
import projectviewer.importer.ImporterFileFilter;
import projectviewer.event.ProjectUpdate;
import projectviewer.event.ViewerUpdate;
import projectviewer.ProjectViewer;

import java.io.*;
import java.util.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import org.gjt.sp.jedit.EditBus;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.OptionGroup;
import org.gjt.sp.jedit.OptionPane;
import org.gjt.sp.jedit.EBComponent;
import org.gjt.sp.jedit.EBMessage;
import org.gjt.sp.jedit.io.VFSFile;
import org.gjt.sp.jedit.msg.BufferUpdate;
import org.gjt.sp.util.ThreadUtilities;

import ise.plugin.svn.PVHelper;
import ise.plugin.svn.SVNPlugin;
import ise.plugin.svn.command.Info;
import ise.plugin.svn.command.Status;
import ise.plugin.svn.io.*;
import ise.plugin.svn.gui.PVSVNOptionPane;

import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusType;

/**
 * Provide version control icons for file status to ProjectViewer.
 */
public class VersionControlState implements VersionControlService, EBComponent {

    // version control states for Subversion
    public static final int NONE = 0;    // no state
    public static final int LOCAL_MOD = 1;    // modified
    public static final int LOCAL_ADD = 2;    // added
    public static final int LOCAL_RM = 3;    // missing
    public static final int NEED_UPDATE = 4;    // out of date
    public static final int CONFLICT = 5;    // conflicted
    public static final int DELETED = 6;    // deleted
    public static final int IGNORED = 7;    // ignored
    public static final int LOCKED = 8;    // locked
    public static final int UNVERSIONED = 9;    // unversioned
    public static final int NORMAL = 10;    // normal

    public static final int UNKNOWN = -1;

    // icon definitions for the various states
    public static final Icon NORMAL_ICON = createIcon( "ise/plugin/svn/gui/icons/normal.png" );
    public static final Icon ADDED_ICON = createIcon( "ise/plugin/svn/gui/icons/added.png" );
    public static final Icon CONFLICT_ICON = createIcon( "ise/plugin/svn/gui/icons/conflict.png" );
    public static final Icon DELETED_ICON = createIcon( "ise/plugin/svn/gui/icons/deleted.png" );
    public static final Icon IGNORED_ICON = createIcon( "ise/plugin/svn/gui/icons/ignored.png" );
    public static final Icon LOCKED_ICON = createIcon( "ise/plugin/svn/gui/icons/locked.png" );
    public static final Icon MODIFIED_ICON = createIcon( "ise/plugin/svn/gui/icons/modified.png" );
    public static final Icon OUTOFDATE_ICON = createIcon( "ise/plugin/svn/gui/icons/outofdate.png" );
    public static final Icon READONLY_ICON = createIcon( "ise/plugin/svn/gui/icons/readonly.png" );
    public static final Icon UNVERSIONED_ICON = createIcon( "ise/plugin/svn/gui/icons/unversioned.png" );

    private static Icon createIcon( String name ) {
        return new ImageIcon( VersionControlState.class.getClassLoader().getResource( name ) );
    }

    private static Status command = new Status();

    // hashtable rather than hashmap as synchronization is needed. This caches
    // the last known status of a file.
    private static Hashtable<String, FileStatus> cache = new Hashtable<String, FileStatus>();

    private static class SingletonHolder {
        public static final VersionControlState instance = new VersionControlState();
    }

    private VersionControlState() {
        EditBus.addToBus( this );
    }

    public static VersionControlState getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * This method should return an integer identifying the current
     * state of the given file.
     *
     * This method will be called every time the file's tree node needs
     * to be repainted, so it shouldn't take long to return. It's
     * extremely encouraged that implementations do some sort of caching
     * to make this method return quickly.
     *
     * @param f  The file.
     *
     * @return A service-specific identifier for the file state.
     */
    public int getNodeState( VPTNode f ) {
        if ( f == null ) {
            return UNKNOWN;
        }
        return getNodeState( f.getNodePath() );
    }

    public int getNodeState( String path ) {
        if ( path == null ) {
            return UNKNOWN;
        }
        FileStatus rtn = checkModified( path );
        if ( rtn.status != NORMAL ) {
            rtn = getStatus( path );
        }
        return rtn.status;
    }

    // gets the status of the file by calling svn, updates the cache with the
    // current status.
    FileStatus getStatus( String path ) {
        File file = new File( path );
        SVNStatus status = command.getStatus( file );
        if ( status == null ) {
            return new FileStatus( new Date().getTime(), NONE );
        }
        SVNStatusType type = status.getContentsStatus();
        FileStatus rtn = cache.get( path );
        if ( rtn == null ) {
            rtn = new FileStatus( file.lastModified(), UNKNOWN );
            cache.put( path, rtn );
        }
        rtn.timestamp = file.lastModified();
        if ( SVNStatusType.STATUS_ADDED.equals( type ) ) {
            rtn.status = LOCAL_ADD;
        } else if ( SVNStatusType.STATUS_CONFLICTED.equals( type ) ) {
            rtn.status = CONFLICT;
        } else if ( SVNStatusType.STATUS_DELETED.equals( type ) ) {
            rtn.status = DELETED;
        } else if ( SVNStatusType.STATUS_IGNORED.equals( type ) ) {
            rtn.status = DELETED;
        } else if ( status.isLocked() ) {
            rtn.status = LOCKED;
        } else if ( SVNStatusType.STATUS_MISSING.equals( type ) ) {
            rtn.status = LOCAL_RM;
        } else if ( SVNStatusType.STATUS_MODIFIED.equals( type ) ) {
            rtn.status = LOCAL_MOD;
        } else if ( SVNStatusType.STATUS_UNVERSIONED.equals( type ) ) {
            rtn.status = UNVERSIONED;
        } else if ( SVNStatusType.STATUS_NORMAL.equals( type ) ) {
            rtn.status = NORMAL;
        } else {
            rtn.status = NONE;
        }
        return rtn;
    }

    // check if the file has been changed by checking the timestamp
    FileStatus checkModified( String path ) {
        // attempt to load the status cache. On start up, the project loaded message
        // is sent before this plugin is loaded, so the initial cache loading doesn't
        // happen from handleMessage. The only time the cache size should be 0 is on
        // start up.
        if ( cache.size() == 0 ) {
            VPTProject project = PVHelper.getProjectForFile( path );
            if ( project != null ) {
                final String projectName = project.getName();
                ThreadUtilities.runInBackground(new Runnable(){
                        public void run() {
                            loadCache( projectName );
                        }
                });
            }
        }
        FileStatus status = cache.get( path );
        File f = new File( path );
        if ( status == null ) {
            status = new FileStatus( f.lastModified(), NORMAL );
            cache.put( path, status );
        } else {
            long lastModified = f.lastModified();
            if ( lastModified != status.timestamp ) {
                status.timestamp = lastModified;
                status.status = UNKNOWN;
            }
        }
        return status;
    }

    /**
     * This should return the status icon to be used to represent the
     * given state.
     *
     * @param state The value retrieved from {@link #getFileState(VPTFile)}.
     *
     * @return The icon for the given state, or null for no icon.
     */
    public Icon getIcon( int state ) {
        switch ( state ) {
            case LOCAL_MOD:
                return MODIFIED_ICON;
            case NEED_UPDATE:
                return OUTOFDATE_ICON;
            case CONFLICT:
                return CONFLICT_ICON;
            case DELETED:
            case LOCAL_RM:
                return DELETED_ICON;
            case LOCKED:
                return LOCKED_ICON;
            case IGNORED:
                return IGNORED_ICON;
            case NORMAL:
                return NORMAL_ICON;
            case LOCAL_ADD:
                return ADDED_ICON;
            case UNVERSIONED:
            case NONE:
                return UNVERSIONED_ICON;
            default:
                return null;
        }
    }

    /**
     * Returns the class identifying the plugin. This is used to check
     * whether there are version control-specific option panes / groups
     * to be added to a project's option dialog.
     *
     * @return The main plugin class for this service.
     */
    public Class getPlugin() {
        return ise.plugin.svn.SVNPlugin.class;
    }

    /**
     * Called when a user removes the version control association with a
     * project (either by not choosing a version control service or a
     * different one). This allows the service to clean up any
     * configuration data associated with the service from the project's
     * properties.
     *
     * @param proj The project.
     */
    public void dissociate( VPTProject project ) {
        if ( project != null ) {
            deleteCache( project.getName() );
        }
    }

    /**
     * This method should return the option pane to be shown. As with
     * regular jEdit option panes, the label to be shown in the dialog
     * should be defined by the "option.[pane_name].label" property.
     *
     * @param project The project that will be edited.
     *
     * @return An OptionPane instance, or null for no option pane.
     */
    public OptionPane getOptionPane( VPTProject project ) {
        return new PVSVNOptionPane( project.getName() );
    }

    /**
     * This should return an OptionGroup to be shown. As with regular
     * jEdit option groups, the label to be shown in the dialog
     * should be defined by the "option.[group_name].label" property.
     *
     * @param project The project that will be edited.
     *
     * @return null
     */
    public OptionGroup getOptionGroup( VPTProject project ) {
        return null;
    }

    /**
     * Returns a file filter to be shown as an option when the user
     * imports files into a project backed by this version control
     * service.
     *
     * @return An ImporterFileFilter for SVN.
     */
    public ImporterFileFilter getFilter() {
        return new ImporterFileFilter() {
            public String getRecurseDescription() {
                return jEdit.getProperty( "ips.Use_SVN_entries", "Use SVN entries" );
            }

            @Override
            public boolean accept( String path ) {
                if ( path == null ) {
                    return false;
                }
                File file = new File( path );
                if ( !file.exists() ) {
                    return false;
                }
                return Info.isWorkingCopy( file );
            }

            public boolean accept( VFSFile file ) {
                if ( file == null) {
                    return false;   
                }
                return accept( file.getPath() );
            }

            public String getDescription() {
                return jEdit.getProperty( "ips.Import_files_in_under_SVN_version_control_only.", "Import files in under SVN version control only." );
            }
        };
    }

    class FileStatus {
        // timestamp that the status of the file was last checked
        public long timestamp;

        // int representing the status of the file last time it was checked
        public int status;

        public FileStatus( Long lastModified, int status ) {
            timestamp = lastModified;
            this.status = status;
        }
    }

    public void handleMessage( EBMessage msg ) {
        if ( msg == null ) {
            return;
        }
        if ( msg instanceof BufferUpdate ) {
            // update status for single file
            BufferUpdate bu = ( BufferUpdate ) msg;
            Object what = bu.getWhat();
            if ( BufferUpdate.DIRTY_CHANGED.equals( what ) || BufferUpdate.LOADED.equals( what ) || BufferUpdate.SAVED.equals( what ) ) {
                getStatus( bu.getBuffer().getPath() );
            }
        } else if ( msg instanceof ProjectUpdate ) {
            // update status for added files
            ProjectUpdate pu = ( ProjectUpdate ) msg;
            if ( ProjectUpdate.Type.FILES_CHANGED.equals( pu.getType() ) ) {
                Collection<VPTFile> files = pu.getAddedFiles();
                if (files != null) {
                    for ( VPTFile file : files ) {
                        if (file != null) {
                            getStatus( file.getNodePath() );
                        }
                    }
                }
            }
        } else if ( msg instanceof ViewerUpdate ) {
            // reload cache with new project files
            final ViewerUpdate vu = ( ViewerUpdate ) msg;
            if ( ViewerUpdate.Type.PROJECT_LOADED.equals( vu.getType() ) ) {
                String projectName = vu.getNode().getName();
                boolean loaded = loadCache( projectName );
                if ( ! loaded ) {
                    ThreadUtilities.runInBackground( new Runnable() {
                        public void run() {
                            reloadCache( vu.getViewer() );
                        }
                    } );
                }
            } else if ( ViewerUpdate.Type.PROJECT_UNLOADING.equals( vu.getType() ) ) {
                String projectName = vu.getNode().getName();
                saveCache( projectName );
            }
        }
    }

    // loads the status cache by starting with a project and recursing through
    // each child item in the project root.  
    void reloadCache( ProjectViewer pv ) {
        if ( pv == null ) {
            return;
        }
        VPTNode root = pv.getRoot();
        reloadCache( root );
    }

    void reloadCache( TreeNode node ) {
        if ( node == null ) {
            return;
        }
        getNodeState( ( VPTNode ) node );
        for ( int i = 0; i < node.getChildCount(); i++ ) {
            reloadCache( node.getChildAt( i ) );
        }
    }

    // saves status cache to disk. Only save the non-normal and non-unknown statuses.
    // Normal is the default, unknown just means the file hasn't been used in a while.
    // Unknowns can assumed to be normal.
    void saveCache( String projectName ) {
        File storageDir = SVNPlugin.getPluginHomeDir();
        if ( storageDir != null ) {
            try {
                File cacheDir = new File( storageDir, projectName );
                if ( !cacheDir.exists() ) {
                    cacheDir.mkdir();
                }
                File cacheFile = new File( cacheDir, "statusCache.obj" );
                if ( cacheFile.exists() ) {
                    cacheFile.delete();
                }
                FileOutputStream fileOut = new FileOutputStream( cacheFile, false );
                ObjectOutputStream objectOut = new ObjectOutputStream( new BufferedOutputStream(fileOut, 64 * 1024));
                for ( String key : cache.keySet() ) {
                    FileStatus fs = cache.get( key );
                    if ( fs != null && fs.status != NORMAL && fs.status != UNKNOWN ) {
                        objectOut.writeObject( key );
                        objectOut.writeLong( fs.timestamp );
                        objectOut.writeInt( fs.status );
                    }
                }
                objectOut.close();
            } catch ( Exception ignored ) {                // NOPMD
            }
        }
    }

    // loads the status cache from disk if possible
    boolean loadCache( String projectName ) {
        File storageDir = SVNPlugin.getPluginHomeDir();
        if ( storageDir != null ) {
            ObjectInputStream objectIn = null;
            try {
                File cacheDir = new File( storageDir, projectName );
                if ( !cacheDir.exists() ) {
                    return false;
                }
                File cacheFile = new File( cacheDir, "statusCache.obj" );
                if ( !cacheFile.exists() ) {
                    return false;
                }
                InputStream fileIn = new BufferedInputStream(new FileInputStream( cacheFile ), Math.min(64 * 1024, (int)cacheFile.length()));
                objectIn = new ObjectInputStream( fileIn );
                cache = new Hashtable<String, FileStatus>();
                while ( true ) {
                    String key = ( String ) objectIn.readObject();
                    long timestamp = objectIn.readLong();
                    int status = objectIn.readInt();
                    cache.put( key, new FileStatus( timestamp, status ) );
                    // loop ends on EOFException. Seems a little lame...
                }
            } catch ( EOFException eof ) {
                // this is expected and indicates the cache was fully read from disk
                return true;
            } catch ( Exception ignored ) {
                return false;
            } finally {
                try {
                    if (objectIn != null) {
                        objectIn.close();
                    }
                } catch ( Exception ignored ) {                    // NOPMD
                }
            }

        }
        return false;
    }

    // removes any files associated with this project
    void deleteCache( String projectName ) {
        File storageDir = SVNPlugin.getPluginHomeDir();
        if ( storageDir != null ) {
            try {
                File cacheDir = new File( storageDir, projectName );
                if ( cacheDir.exists() ) {
                    File[] files = cacheDir.listFiles();
                    for ( File file : files ) {
                        file.delete();
                    }
                    cacheDir.delete();
                }
            } catch ( Exception ignored ) {                // NOPMD
            }
        }
    }
}
