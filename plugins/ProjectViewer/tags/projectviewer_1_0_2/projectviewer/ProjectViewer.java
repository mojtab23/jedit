/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more detaProjectTreeSelectionListenerils.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package projectviewer;

import java.awt.*;
import java.awt.event.*;
//import java.net.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.jedit.msg.*;
import org.gjt.sp.util.Log;

import projectviewer.event.*;
import projectviewer.tree.*;


/**
 * A Project Viewer plugin for jEdit.
 *
 * @author <A HREF="mailto:burton@relativity.yi.org">Kevin A. Burton</A>
 * @version $Revision$
 */
public class ProjectViewer
  extends JPanel
  implements DockableWindow, EBComponent
{

  public final static String ALL_PROJECTS = "All Projects";
            
  private final static String FOLDERS_TAB_TITLE          = "Folders";
  private final static String FILES_TAB_TITLE            = "Files";
  private final static String WORKING_FILES_TAB_TITLE    = "Working Files";
            
  private final static int FOLDERS_TAB        = 0;
  private final static int FILES_TAB          = 1;
  private final static int WORKING_FILES_TAB  = 2;
  
  private ProjectView projectView;
  private JTabbedPane tabs = new JTabbedPane();

  private JTree folderTree;
  private JTree fileTree;
  private JTree workingFileTree;
  private List listeners;
    
  private JComboBox projectCombo;
  private JLabel status = new JLabel(" ");
  
  JButton deleteProjectBtn;
  JButton removeFileBtn;
  JButton removeAllFilesBtn;
  JButton createProjectBtn;
  JButton addFileBtn;
  JButton importFilesBtn;
  JButton openAllBtn;
  JButton expandBtn;
  JButton contractBtn;
  JButton configBtn;

  private View view;

  private ViewerListener vsl;
  private ProjectTreeSelectionListener tsl;
  private Launcher launcher;
  
  
  /**
   * Create a new <code>ProjectViewer</code>.
   */
  public ProjectViewer(View aView) {
    view = aView;
    launcher = new Launcher( view, this );
    vsl = new ViewerListener(this, launcher);
    tsl = new ProjectTreeSelectionListener(this, launcher);
    listeners = new ArrayList();
    loadGUI();
    setCurrentProject( getLastProject() );
    enableEvents( AWTEvent.COMPONENT_EVENT_MASK );
  }

  /**
   * Returns <code>true</code> if currently selected node of the displayed
   * tree is a file.
   */
  public boolean isFileSelected() {
    return getCurrentTree().getLastSelectedPathComponent() instanceof ProjectFile;
  }
  
  /**
   * Returns the currently displayed tree, or <code>null</code> if none are
   * displayed.
   */
  public JTree getCurrentTree() {
    switch( tabs.getSelectedIndex() ) {
      case FOLDERS_TAB:
        return folderTree;
        
      case FILES_TAB:
        return this.fileTree;
        
      case WORKING_FILES_TAB:
        return this.workingFileTree;
      
      default:
        return null;
    }
  }
  
  /**
   * Returns the current project.
   */
  public Project getCurrentProject() {
    return projectView.getCurrentProject();
  }

  /**
   * Set the project that the user is working with.
   */
  public void setCurrentProject( Project project ) {
    if ( projectView != null ) {
      if ( !isAllProjects() && getCurrentProject().equals( project ) )
        return;
      
      if ( isAllProjects() && project == null )
        return;
      projectView.deactivate();
      launcher.closeProject( getCurrentProject() );
    }
    
    projectView = getProjectViewFor( project );  
    projectView.activate();
    loadProject();
  }

  /**
   * Returns the selected node of the current tree.
   */
  public Object getSelectedNode() {
    return getCurrentTree().getLastSelectedPathComponent();
  }

  /**
   * Returns the currently selected file in the current tree, or <code>null</code>
   * if no nodes are selected. 
   */
  public ProjectFile getSelectedFile() {
    return (ProjectFile) getSelectedNode();
  }
    
  /**
   * Returns this component.
   */
  public Component getComponent() {
    return this;
  }

  /**
   * Set the status message.
   */
  public void setStatus(Object msg) {
    status.setText(msg.toString());
  }

  /**
   * Returns the name of this component.
   */
  public String getName() {
    return ProjectPlugin.NAME;
  }

  /**
   * Show the wait cursor.
   */
  public void showWaitCursor() {
    setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
  }
  
  /**
   * Collapses all nodes of the current tree.
   */
  public void collapseAll() {
    getCurrentTree().collapseRow( 0 );
  }
  
  /**
   * Expands all nodes of the current tree.
   */
  public void expandAll() {
    expandAll( getCurrentTree() );
  }
  
  /**
   * Expands all nodes of the specified tree.
   */
  public void expandAll( JTree tree ) {
    expand( new TreePath( tree.getModel().getRoot() ), tree );
  }
  
  /**
   * Expand the given sub tree.
   */
  public void expand( TreePath path, JTree tree ) {
    TreeModel model = tree.getModel();
    Object node = path.getLastPathComponent();
    if ( model.isLeaf( node ) ) return;
    tree.expandPath( path );
    
    int count = model.getChildCount( node );
    for ( int i=0; i<count; i++ ) {
      expand( path.pathByAddingChild( model.getChild( node, i ) ), tree );
    }
  }
  
  /**
   * Show the default cursor.
   */
  public void showDefaultCursor() {
    setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
  }
  
  /**
   * Returns a file chooser that with a starting directory relative to the
   * currently selected node of the current displayed tree.
   */
  public JFileChooser createFileChooser() {
    Object node = getSelectedNode();
    
    String browsePath = "";
    if ( node instanceof Project )
      browsePath = ( (Project) node ).getRoot().getPath();
      
    else if ( node instanceof ProjectDirectory )
      browsePath = ( (ProjectDirectory) node ).getPath();
      
    else if ( node instanceof ProjectFile )
      browsePath = ( (ProjectFile) node ).toFile().getParent();
    else if ( getCurrentProject() != null )
      browsePath = getCurrentProject().getRoot().getPath();
      
    return new JFileChooser( browsePath );
  }
  
  /**
   * Reload ProjectResources and then reparse the object tree.
   */
  public void refresh() {
    loadProjectCombo();
    loadProject();
  }

  /**
   * Return the view that this project viewer is working with
   */
  public View getView() {
    return this.view;
  }
  
  /**
   * Returns <code>true</code> if the current view is 'All Projects'.
   */
  public boolean isAllProjects() {
    return projectView instanceof AllProjectsView;
  }
  
  /**
   * Handle a message from the bus.
   */
  public void handleMessage( EBMessage msg ) {
    if ( isAllProjects() || !(msg instanceof BufferUpdate) ) return;
    
    BufferUpdate update = (BufferUpdate) msg;
    if ( update.getWhat().equals(BufferUpdate.DIRTY_CHANGED) &&
         !update.getBuffer().isDirty() &&
         getCurrentProject().canAddInProject( update.getBuffer().getPath() )
       )
    {
      int res = JOptionPane.showConfirmDialog( getView(),
        "Import " + update.getBuffer().getName() + " to " + getCurrentProject().getName() +"?",
        jEdit.getProperty( ProjectPlugin.NAME + ".title" ),
        JOptionPane.YES_NO_OPTION );
      
      if ( res != JOptionPane.YES_OPTION ) return;
      getCurrentProject().importFile( new ProjectFile( update.getBuffer().getPath() ) );
    }
  }
  
  /**
   * Add a {@link ProjectViewerListener}.
   */
  public void addProjectViewerListener( ProjectViewerListener listener ) {
    listeners.add( listener );
  }
  
  /**
   * Remove a {@link ProjectViewerListener}.
   */
  public void removeProjectViewerListener( ProjectViewerListener listener ) {
    listeners.remove( listener );
  }

  /**
   * Enable buttons for the given node. 
   */
  public void enableButtonsForNode( Object node ) {
    boolean isAllNode = node instanceof String;
    removeFileBtn    .setEnabled( node instanceof ProjectFile );
    openAllBtn       .setEnabled( node != null && !isAllNode );
    importFilesBtn   .setEnabled( node != null && !isAllNode );
    removeAllFilesBtn.setEnabled( node != null && !isAllNode );
    addFileBtn       .setEnabled( node != null && !isAllNode );
    deleteProjectBtn .setEnabled( node != null && !isAllNode );
  }
  
  /**
   * Process component events.
   */
  protected void processComponentEvent( ComponentEvent evt ) {
    if ( evt.getID() == ComponentEvent.COMPONENT_HIDDEN )
      EditBus.removeFromBus(this);
    else if ( evt.getID() == ComponentEvent.COMPONENT_SHOWN )
      EditBus.addToBus(this);
    super.processComponentEvent( evt );
  }
  
  /**
   * Fire the project loaded event.
   */
  private void fireProjectLoaded( Project project ) {
    ProjectViewerEvent evt = new ProjectViewerEvent( this, project );
    for ( Iterator i = listeners.iterator(); i.hasNext(); ) {
      try {
        ( (ProjectViewerListener) i.next() ).projectLoaded( evt );
      } catch ( Throwable t ) {
        Log.log( Log.WARNING, this, t );
      }
    }
  }
  
  /**
   * loads the GUI of Project Viewer
   */
  private void loadGUI() {
    setLayout(new BorderLayout());
        
    projectCombo = new JComboBox();
        
    folderTree = createTree();
    fileTree = createTree();
    workingFileTree = createTree();
      
    JPanel bar = new JPanel(new BorderLayout());
           
    JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));

    deleteProjectBtn  = createButton("/projectviewer/icons/DeleteProject.gif", "Delete project");
    removeFileBtn     = createButton("/projectviewer/icons/RemoveFile.gif", "Remove file");
    removeAllFilesBtn = createButton("/projectviewer/icons/RemoveAllFiles.gif", "Remove all files");
    createProjectBtn  = createButton("/projectviewer/icons/CreateProject.gif", "Create project");
    addFileBtn        = createButton("/projectviewer/icons/AddFile.gif", "Add file to project");
    importFilesBtn    = createButton("/projectviewer/icons/Import.gif", "Import files into this project");
    openAllBtn        = createButton("/projectviewer/icons/OpenAll.gif", "Open all files in this project");
    expandBtn         = createButton("/projectviewer/icons/Expand.gif", "Expand the file list");
    contractBtn       = createButton("/projectviewer/icons/Contract.gif", "Contract the file list");
    configBtn         = createButton("/projectviewer/icons/Config.gif", "Configure Project Viewer");        

    toolbar.add(createProjectBtn);
    toolbar.add(deleteProjectBtn);
    toolbar.add(expandBtn);
    toolbar.add(contractBtn);
    toolbar.add(openAllBtn);
    toolbar.add(addFileBtn);
    toolbar.add(importFilesBtn);
    toolbar.add(removeFileBtn);
    toolbar.add(removeAllFilesBtn);

    //  set the default state of the toggle buttons...
    removeFileBtn     .setEnabled(false);
    removeAllFilesBtn .setEnabled(false);
    addFileBtn        .setEnabled(false);
    deleteProjectBtn  .setEnabled(false);
    importFilesBtn    .setEnabled(false);        
    openAllBtn        .setEnabled(false);
    expandBtn         .setEnabled(true);
    contractBtn       .setEnabled(true);
        
    bar.add(toolbar, BorderLayout.NORTH);
    bar.add(projectCombo, BorderLayout.SOUTH);

    // ok... now create a JPanel for placing the bar and the tree into and then
    // stick that into tabs...
    JPanel allComponents = new JPanel(new BorderLayout());

    allComponents.add(bar, BorderLayout.NORTH);
    
    tabs.addChangeListener( tsl );
    
    tabs.addTab(FOLDERS_TAB_TITLE, new JScrollPane( folderTree ));
    tabs.addTab(FILES_TAB_TITLE, new JScrollPane( fileTree ));
    tabs.addTab(WORKING_FILES_TAB_TITLE, new JScrollPane( workingFileTree ));

    allComponents.add(tabs, BorderLayout.CENTER);

    // ok.. add the bar to the tab...
    //contentPanel.getContentPane().add(bar, BorderLayout.NORTH);
    add(allComponents, BorderLayout.CENTER);
    add(status, BorderLayout.SOUTH);
        
    loadProjectCombo();
    projectCombo.addItemListener( vsl );
    setVisible(true);
  }
  
  /**
   * Returns project view for the given project.
   */
  private ProjectView getProjectViewFor( Project aProject ) {
    return ( aProject instanceof Project ) ? (ProjectView)
      new SimpleProjectView( aProject ) : new AllProjectsView( this );
  }
  
  /**
   * Returns the last project of the previous session.
   */
  private Project getLastProject() {
    return ProjectManager.getInstance().getProject( ProjectPlugin.getLastProject() );
  }
  
  /**
   * Create a tool bar button.
   */
  private JButton createButton(String icon, String tooltip) {
    JButton init = new JButton( new ImageIcon(getClass().getResource( icon )) );

    Insets zeroMargin = new Insets(0, 0, 0, 0);
    init.setMargin(zeroMargin);
    init.setToolTipText( tooltip );
    init.addActionListener(vsl);
    return init;
  }

  /**
   * Create and initialize a tree widget.
   */
  private JTree createTree() {
    JTree tree = new ProjectTree();
    tree.getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
    tree.setCellRenderer( new TreeRenderer() );
    tree.addTreeSelectionListener( tsl );
    tree.addMouseListener( tsl );
    ToolTipManager.sharedInstance().registerComponent(tree);
    return tree;
  }
    
  /**
   * Creates an initialzed project combo and adds all known projects
   */
  private void loadProjectCombo() {
    vsl.pause();
    if ( projectCombo.getItemCount() != 0 )
      projectCombo.removeAllItems();
    projectCombo.addItem(ALL_PROJECTS);

    Iterator i = ProjectManager.getInstance().projects();
    while ( i.hasNext() )
      projectCombo.addItem( i.next() );
    vsl.resume();
  }
    
  /**
   * Load a project.
   */
  private void loadProject() {
    showWaitCursor();        
    
    folderTree      .setModel( projectView.getFolderViewModel()       );
    fileTree        .setModel( projectView.getFileViewModel()         );
    workingFileTree .setModel( projectView.getWorkingFileViewModel()  );

    expandAll( fileTree );
    expandAll( workingFileTree );        
    
    removeFileBtn     .setEnabled(false);
    removeAllFilesBtn .setEnabled(true);
    addFileBtn        .setEnabled(true);
    deleteProjectBtn  .setEnabled(true);
    importFilesBtn    .setEnabled(true);
    openAllBtn        .setEnabled(true);
    
    vsl.pause();
    projectCombo.setSelectedItem( isAllProjects() ?
      (Object) ALL_PROJECTS : getCurrentProject() );
    vsl.resume();
    
    showDefaultCursor();
    
    if ( getCurrentProject() != null )
      fireProjectLoaded( getCurrentProject() );
  }

}

