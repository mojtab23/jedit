/*
 * :tabSize=4:indentSize=4:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
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

//{{{ Imports
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.jedit.msg.*;
import org.gjt.sp.util.Log;

import projectviewer.event.*;
import projectviewer.tree.*;
import projectviewer.config.ProjectViewerConfig;
//}}}

/**
 *  A Project Viewer plugin for jEdit.
 *
 * @version    $Id$
 */
public final class ProjectViewer extends JPanel 
								 implements EBComponent, Runnable, PropertyChangeListener {
									 
	//{{{ Constants
	public final static String CREATE_NEW_PROJECT = "Create new project ...";
	public final static String ALL_PROJECTS = "All projects";

	protected final static int FOLDERS_TAB = 0;
	protected final static int FILES_TAB = 1;
	protected final static int WORKING_FILES_TAB = 2;

	private final static String FOLDERS_TAB_TITLE = "Folders";
	private final static String FILES_TAB_TITLE = "Files";
	private final static String WORKING_FILES_TAB_TITLE = "Working Files";

	private final static ProjectViewerConfig config = ProjectViewerConfig.getInstance();
	//}}}

	//{{{ Static members

	/** Maps jEdit views to ProjectViewer instances. */
	private static final Hashtable viewers = new Hashtable();

	/**
	 *	Returns the ProjectViewer instance associated with the provided view,
	 *	if any; else, returns null.
	 */
	public static ProjectViewer getProjectViewer(View view) {
		return (ProjectViewer) viewers.get(view);
	}
	
	//}}}

	//{{{ Private / Protected variables
	RolloverButton removeFileBtn;
	RolloverButton removeAllFilesBtn;
	RolloverButton createProjectBtn;
	RolloverButton addFileBtn;
	RolloverButton openAllBtn;
	RolloverButton expandBtn;
	RolloverButton contractBtn;
	RolloverButton launchBrowserBtn;// this will eventually be on the context menu

	private ProjectView projectView;
	private JToolBar toolbar;
	private JTabbedPane tabs = new JTabbedPane();
	private JPanel allComponents;

	private JTree folderTree;
	private JTree fileTree;
	private JTree workingFileTree;
	private List listeners;
	private List projectListeners;

	JComboBox projectCombo;

	private View view;
	private ViewerListener vsl;
	private ProjectTreeSelectionListener tsl;
	private TreeContextMenuListener cml;
	private Launcher launcher;

	private boolean allProjectsLoaded;
	private boolean willRun = false;
	//}}}

	//{{{ Constructor
	/** Create a new <code>ProjectViewer</code>.
	 *
	 * @param  aView  Description of Parameter
	 */
	public ProjectViewer(View aView) {
		allProjectsLoaded = false;
		view = aView;
		launcher = new Launcher(view, this);
		vsl = new ViewerListener(this, launcher);
		tsl = new ProjectTreeSelectionListener(this, launcher);
		cml = new TreeContextMenuListener(this);
		listeners = new ArrayList();
		projectListeners = new ArrayList();
		loadGUI();
		enableEvents(AWTEvent.COMPONENT_EVENT_MASK);
		config.addPropertyChangeListener(this);

		setCurrentProject(ProjectManager.getInstance().getCurrentProject());
		
		tsl.stateChanged(null);
		EditBus.addToBus(this);
		viewers.put(view,this);
	}//}}}

	//{{{ getView() method
	/** Return the view that this project viewer is working with
	 *
	 * @return    The view value
	 */
	public View getView() {
		return view;
	}
	//}}}

	//{{{ setCurrentProject() method
	/** Set the project that the user is working with.
	 *
	 * @param  project  The new currentProject value
	 */
	public void setCurrentProject(Project project) {
		Log.log( Log.DEBUG, this, "setCurrentProject() project="+project);
		if(projectView != null) {
			Project cp = getCurrentProject();
			if(isAllProjects() && project == null)
				return;
			if(!isAllProjects() && project != null && project.equals(cp))
				return;
			if(cp != null) {
				Log.log( Log.DEBUG, this, "saving state of current project");
				// save the state of the previous project
				launcher.closeProject(cp);
				cp.setFolderTree(folderTree);
				// remember currently active tab
				cp.setTabState(tabs.getSelectedIndex());
			}
			projectView.deactivate();
		}

		// set up the new project
		projectView = getProjectViewFor(project);
		
		if(isAllProjects()) {
			if(!allProjectsLoaded) {
				for(Iterator i = ProjectManager.getInstance().projects(); i.hasNext(); ) {
					Project p = (Project)i.next();
					if(!p.isLoaded()) {
						p.load();
					}
				}
				allProjectsLoaded = true;
			}
		}
		else {
			project.load();
			Iterator it = projectListeners.iterator();
			while(it.hasNext())
				project.addProjectListener((ProjectListener)it.next());
			launcher.openProject(project);
			ProjectManager.getInstance().setCurrentProject(project);
		}

		// set the trees
		if(folderTree != null) {
			folderTree.getSelectionModel().setSelectionMode(projectView.getSelectionModel());
		}
		if(fileTree != null) {
			fileTree.getSelectionModel().setSelectionMode(projectView.getSelectionModel());
		}
		if(workingFileTree != null) {
			workingFileTree.getSelectionModel().setSelectionMode(projectView.getSelectionModel());
		}

		projectView.activate();

		loadProject();
	}//}}}

	//{{{ setStatus() method
	/** Set the status message.
	 *
	 * @param  msg  The new status value
	 */
	public void setStatus(Object msg) {
		view.getStatus().setMessageAndClear(msg.toString());
	}//}}}

	//{{{ isFileSelected() method
	/** Returns <code>true</code> if currently selected node of the displayed
	 * tree is a file.
	 *
	 * @return    The fileSelected value
	 */
	public boolean isFileSelected() {
		return getCurrentTree().getLastSelectedPathComponent() instanceof ProjectFile;
	}//}}}

	//{{{ getCurrentTree() method
	/** Returns the currently displayed tree, or <code>null</code> if none are
	 *  displayed.
	 *
	 * @return    The currentTree value
	 */
	public JTree getCurrentTree() {
		switch(tabs.getSelectedIndex()) {
			case 0:
				if(folderTree!=null) return folderTree;
				if(fileTree!=null) return fileTree;
				if(workingFileTree!=null) return workingFileTree;
			case 1:
				if(fileTree!=null) return fileTree;
				if(workingFileTree!=null) return workingFileTree;
			case 2:
				if(workingFileTree!=null) return workingFileTree;
		}
		Log.log( Log.DEBUG, this, "   invalid tabnumber :"+tabs.getSelectedIndex());
		return null;
	}//}}}

	//{{{ getCurrentProject() method
	/** Returns the current project.
	 *
	 * @return    The currentProject value
	 */
	public Project getCurrentProject() {
		return projectView.getCurrentProject();
	}//}}}

	//{{{ getSelectedNode() method
	/** Returns the selected node of the current tree.
	 *
	 * @return    The selectedNode value
	 */
	public Object getSelectedNode() {
		JTree curTree = getCurrentTree();
		return (curTree != null) ? curTree.getLastSelectedPathComponent() : null;
	}//}}}

	//{{{ getSelectedFile() method
	/** Returns the currently selected file in the current tree, or <code>null</code>
	 * if no nodes are selected.
	 *
	 * @return    The selectedFile value
	 */
	public ProjectFile getSelectedFile() {
		return (ProjectFile)getSelectedNode();
	}//}}}

	//{{{ getComponent() method
	/** Returns this component.
	 *
	 * @return    The component value
	 */
	public Component getComponent() {
		return this;
	}//}}}

	//{{{ getName() method
	/** Returns the name of this component.
	 *
	 * @return    The name value
	 */
	public String getName() {
		return ProjectPlugin.NAME;
	}//}}}

	//{{{ isAllProjects() method
	/** Returns <code>true</code> if the current view is 'All Projects'.
	 *
	 * @return    The allProjects value
	 */
	public boolean isAllProjects() {
		return projectView instanceof AllProjectsView;
	}//}}}

	//{{{ collapseAll() method
	/** Collapses all nodes of the current tree. */
	public void collapseAll() {
		getCurrentTree().collapseRow(0);
	}//}}}

	//{{{ expandAll() method
	/** Expands all nodes of the current tree. */
	public void expandAll() {
		expandAll(getCurrentTree());
	}//}}}

	//{{{ expandAll() method
	/** 
	 *	Expands all nodes of the specified tree.
	 *
	 * 	@param  tree  Description of Parameter
	 */
	public void expandAll(JTree tree) {
		expand(new TreePath(tree.getModel().getRoot()), tree);
	} //}}}

	//{{{ expand() method
	/**
	 *	Expand the given sub tree.
	 *
	 * @param  path  Description of Parameter
	 * @param  tree  Description of Parameter
	 */
	public void expand(TreePath path, JTree tree) {
		TreeModel model = tree.getModel();
		Object node = path.getLastPathComponent();
		if(model.isLeaf(node))
			return;
		tree.expandPath(path);

		int count = model.getChildCount(node);
		for(int i = 0; i < count; i++) {
			expand(path.pathByAddingChild(model.getChild(node, i)), tree);
		}
	}//}}}

	//{{{ showDefaultCursor() method
	/** Show the default cursor. */
	public void showDefaultCursor() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	} //}}}

	//{{{ showWaitCursor() method
	/** Show the wait cursor. */
	public void showWaitCursor() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	} //}}}

	//{{{ createFileChooser() method
	/** 
	 *	Returns a file chooser that with a starting directory relative to the
	 *	currently selected node of the current displayed tree.
	 *
	 * @return    Description of the Returned Value
	 */
	public JFileChooser createFileChooser() {
		Object node = getSelectedNode();

		String browsePath = "";
		if(node instanceof Project)
			browsePath = ((Project)node).getRoot().getPath();

		else if(node instanceof ProjectDirectory)
			browsePath = ((ProjectDirectory)node).getPath();

		else if(node instanceof ProjectFile)
			browsePath = ((ProjectFile)node).toFile().getParent();
		else if(getCurrentProject() != null)
			browsePath = getCurrentProject().getRoot().getPath();

		return new JFileChooser(browsePath);
	} //}}}

	//{{{ refresh() method
	/** Reload ProjectResources and then reparse the object tree. */
	public void refresh() {
		Log.log(Log.NOTICE, this, "refresh()");
		loadProjectCombo();
		loadProject();
	} //}}}

	//{{{ handleMessage() method
	/** 
	 *	Handle a message from the bus.
	 *
	 * @param  msg  Description of Parameter
	 */
	public void handleMessage(EBMessage msg) {
		if (msg instanceof BufferUpdate) {
			if (isAllProjects()) return;
			
			BufferUpdate update = (BufferUpdate)msg;
			if(update.getView() == view && 
					update.getWhat() == BufferUpdate.SAVED &&
					getCurrentProject().canAddInProject(update.getBuffer().getPath())
					) {
				int res = JOptionPane.showConfirmDialog(getView(),
						"Import " + update.getBuffer().getName() + " to " + getCurrentProject().getName() + "?",
						jEdit.getProperty(ProjectPlugin.NAME + ".title"),
						JOptionPane.YES_NO_OPTION);
	
				if(res != JOptionPane.YES_OPTION)
					return;
				getCurrentProject().importFile(new ProjectFile(update.getBuffer().getPath()));
			}
		}

		// deregister a view when it's closed.
		if (msg instanceof ViewUpdate) {
			ViewUpdate vu = (ViewUpdate) msg;
			if (vu.getView() == view && vu.getWhat() == ViewUpdate.CLOSED) {
				config.removePropertyChangeListener(this);
				viewers.remove(vu.getView());
				EditBus.removeFromBus(this);
			}
			return;
		}
		

	} //}}}

	//{{{ addProjectViewerListener(ProjectViewerListener) method 
	/** Add a {@link ProjectViewerListener}.
	 *
	 * @param  listener  The feature to be added to the ProjectViewerListener attribute
	 */
	public void addProjectViewerListener(ProjectViewerListener listener) {
		listeners.add(listener);
	} //}}}

	//{{{ removeProjectViewerListener(ProjectViewerListener) methods
	/** Remove a {@link ProjectViewerListener}.
	 *
	 * @param  listener  Description of Parameter
	 */
	public void removeProjectViewerListener(ProjectViewerListener listener) {
		listeners.remove(listener);
	} //}}}

	//{{{ addProjectListener(ProjectListener) method
	/** Adds a feature to the ProjectListener attribute of the ProjectViewer object
	 *
	 * @param  listener  The feature to be added to the ProjectListener attribute
	 */
	public void addProjectListener(ProjectListener listener) {
		projectListeners.add(listener);
	} //}}}

	//{{{ removeProjectListener(ProjectListener) method
	/** Description of the Method
	 *
	 * @param  listener  Description of Parameter
	 */
	public void removeProjectListener(ProjectListener listener) {
		projectListeners.remove(listener);
	} //}}}

	//{{{ enableButtonsForNode(Object) method
	/** Enable buttons for the given node.
	 *
	 * @param  node  Description of Parameter
	 */
	public void enableButtonsForNode(Object node) {
		boolean isAllNode = node instanceof String;
		openAllBtn.setEnabled(node != null && !isAllNode);
		addFileBtn.setEnabled(node != null && !isAllNode);
		//removeFileBtn	.setEnabled( node instanceof ProjectFile );
		removeFileBtn.setEnabled(node != null && !isAllNode);
		removeAllFilesBtn.setEnabled(node != null && !isAllNode);
	} //}}}

	//{{{ propertyChange() method
	/** Listens for property change events in the plugin's configuration.
	 *  Shows/Hides the toolbar and the trees, according to the user's wish.
	 *
	 * @param  evt  Description of Parameter
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		// Toolbar show/hide.
		if(evt.getPropertyName().equals(ProjectViewerConfig.SHOW_TOOLBAR_OPT)) {
			Log.log(Log.DEBUG, this, "Show/Hide toolbar");
			toolbar.setVisible(config.getShowToolBar());
			return;
		}

		// Showing/Hiding Trees?
		if(evt.getPropertyName().equals(ProjectViewerConfig.SHOW_FOLDERS_OPT) ||
				evt.getPropertyName().equals(ProjectViewerConfig.SHOW_FILES_OPT) ||
				evt.getPropertyName().equals(ProjectViewerConfig.SHOW_WFILES_OPT)) {
			if(!willRun) {
				Log.log(Log.DEBUG, this, "Scheduling tree rebuild");
				SwingUtilities.invokeLater(this);
				willRun = true;
			}
			return;
		}
	}//}}}

	//{{{ run() method
	/** "Run" method, called by the Swing runtime after a config option for one
	 *  or more of the trees has changed.
	 */
	public void run() {
		showTrees();
		willRun = false;
	}//}}}

	//{{{ getProjectViewFor(Project) method
	/** Returns project view for the given project.
	 *
	 * @param  aProject  Description of Parameter
	 * @return           The projectViewFor value
	 */
	private ProjectView getProjectViewFor(Project aProject) {
		return (aProject instanceof Project) ? (ProjectView)
				new SimpleProjectView(aProject) : new AllProjectsView(this);
	} //}}}

	//{{{ getLastProject() method
	/** Returns the last project of the previous session.
	 *
	 * @return    The lastProject value
	 */
	private Project getLastProject() {
		return ProjectManager.getInstance().getProject(ProjectPlugin.getLastProject());
	} //}}}

	//{{{ fireProjectLoaded(Project) method
	/** Fire the project loaded event.
	 *
	 * @param  project  Description of Parameter
	 */
	private void fireProjectLoaded(Project project) {
		ProjectViewerEvent evt = new ProjectViewerEvent(this, project);
		for(Iterator i = listeners.iterator(); i.hasNext(); ) {
			try {
				((ProjectViewerListener)i.next()).projectLoaded(evt);
			} catch(Throwable t) {
				Log.log(Log.WARNING, this, t);
			}
		}
	} //}}}

	//{{{ loadGUI() method
	/** loads the GUI of Project Viewer */
	private void loadGUI() {
		setLayout(new BorderLayout());

		projectCombo = new JComboBox();

		JPanel bar = new JPanel(new BorderLayout());

		toolbar = new JToolBar();
		toolbar.setVisible(config.getShowToolBar());
		toolbar.setFloatable(false);
		toolbar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);

		removeFileBtn = createButton("Minus.png", "Remove file or directory");
		//removeFileBtn =` createButton("/projectviewer/icons/RemoveFile.gif", "Remove file or directory");
		removeAllFilesBtn = createButton("BrokenImage.png", "Remove all files");
		//removeAllFilesBtn = createButton("/projectviewer/icons/RemoveAllFiles.gif", "Remove all files");
		createProjectBtn = createButton("Drive.png", "Create project");
		//createProjectBtn = createButton("/projectviewer/icons/CreateProject.gif", "Create project");
		addFileBtn = createButton("New.png", "Add files to project");
		//addFileBtn = createButton("/projectviewer/icons/AddFile.gif", "Add file to project");
		openAllBtn = createButton("Open.png", "Open all files in this project");
		//openAllBtn = createButton("/projectviewer/icons/OpenAll.gif", "Open all files in this project");
		expandBtn = createButton("ZoomIn.png", "Expand the file list");
		//expandBtn = createButton("/projectviewer/icons/Expand.gif", "Expand the file list");
		contractBtn = createButton("ZoomOut.png", "Contract the file list");
		//contractBtn = createButton("/projectviewer/icons/Contract.gif", "Contract the file list");
		launchBrowserBtn = createButton("Run.png", "Preview in Browser");
		//launchBrowserBtn = createButton("/projectviewer/icons/web.gif", "Preview in Browser");
		toolbar.add(createProjectBtn);
		toolbar.add(expandBtn);
		toolbar.add(contractBtn);
		toolbar.add(openAllBtn);
		toolbar.add(addFileBtn);
		toolbar.add(removeFileBtn);
		toolbar.add(removeAllFilesBtn);
		toolbar.add(launchBrowserBtn);
		//  set the default state of the toggle buttons...
		removeFileBtn.setEnabled(false);
		removeAllFilesBtn.setEnabled(false);
		addFileBtn.setEnabled(false);
		openAllBtn.setEnabled(false);
		expandBtn.setEnabled(true);
		contractBtn.setEnabled(true);
		launchBrowserBtn.setEnabled(true);

		bar.add(toolbar, BorderLayout.NORTH);
		bar.add(projectCombo, BorderLayout.SOUTH);

		// ok... now create a JPanel for placing the bar and the tree into and then
		// stick that into tabs...
		allComponents = new JPanel(new BorderLayout());

		allComponents.add(bar, BorderLayout.NORTH);

		// Shows the configured trees in the JTabbedPane.
		showTrees();

		allComponents.add(tabs, BorderLayout.CENTER);

		// ok.. add the bar to the tab...
		//contentPanel.getContentPane().add(bar, BorderLayout.NORTH);
		add(allComponents, BorderLayout.CENTER);

		loadProjectCombo();
		projectCombo.addItemListener(vsl);
		
		setVisible(true);
	} //}}}

	//{{{ createButton(String,String) method
	/** Create a tool bar button.
	 *
	 * @param  icon     Description of Parameter
	 * @param  tooltip  Description of Parameter
	 * @return          Description of the Returned Value
	 */
	private RolloverButton createButton(String icon, String tooltip) {
		return createButton(GUIUtilities.loadIcon(icon), tooltip);
	} //}}}

	//{{{ createButton(Icon,String) method
	/** Create a tool bar button.
	 *
	 * @param  icon     Description of Parameter
	 * @param  tooltip  Description of Parameter
	 * @return          Description of the Returned Value
	 */
	private RolloverButton createButton(Icon icon, String tooltip) {
		RolloverButton init = new RolloverButton(icon);
		Insets zeroMargin = new Insets(0, 0, 0, 0);
		init.setMargin(zeroMargin);
		init.setToolTipText(tooltip);
		init.addActionListener(vsl);
		return init;
	} //}}}

	//{{{ createTree() method
	/** Create and initialize a tree widget.
	 *
	 * @return    Description of the Returned Value
	 */
	private JTree createTree() {
		//Log.log(Log.DEBUG, this, "createTree()");
		JTree tree = new ProjectTree();
		//tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setCellRenderer(new TreeRenderer());
		tree.addTreeSelectionListener(tsl);
		tree.addMouseListener(tsl);
		tree.addMouseListener(cml);
		ToolTipManager.sharedInstance().registerComponent(tree);
		return tree;
	}//}}}

	//{{{ loadProjectCombo() method
	/** Creates an initialzed project combo and adds all known projects */
	private void loadProjectCombo() {
		vsl.pause();
		if(projectCombo.getItemCount() != 0)
			projectCombo.removeAllItems();

		projectCombo.addItem(CREATE_NEW_PROJECT);
		projectCombo.addItem(ALL_PROJECTS);

		Iterator i = ProjectManager.getInstance().projects();
		while(i.hasNext())
			projectCombo.addItem(i.next());
		vsl.resume();
	}//}}}

	//{{{ loadProject() method
	/** Load a project. */
	private void loadProject() {
		showWaitCursor();

		if(folderTree != null) {
			folderTree.setModel(projectView.getFolderViewModel());
			if(getCurrentProject() != null)
				getCurrentProject().restoreFolderTreeState(folderTree);
			folderTree.repaint();
		}
		if(fileTree != null) {
			fileTree.setModel(projectView.getFileViewModel());
			expandAll(fileTree);
			fileTree.repaint();
		}
		if(workingFileTree != null) {
			workingFileTree.setModel(projectView.getWorkingFileViewModel());
			expandAll(workingFileTree);
			workingFileTree.repaint();
		}

		removeFileBtn.setEnabled(false);
		removeAllFilesBtn.setEnabled(true);
		addFileBtn.setEnabled(true);
		openAllBtn.setEnabled(true);

		if(getCurrentProject() != null)
			tabs.setSelectedIndex(getCurrentProject().getTabState());

		vsl.pause();
		projectCombo.setSelectedItem(isAllProjects() ? (Object)ALL_PROJECTS : getCurrentProject());
		vsl.lastSelectedIndex = projectCombo.getSelectedIndex();
		vsl.resume();

		showDefaultCursor();

		if(getCurrentProject() != null)
			fireProjectLoaded(getCurrentProject());
	} //}}}

	//{{{ showTrees() method
	/** Loads the trees (folders, files, working files) into the view, deciding
	 *  what to show according to the configuration of the plugin
	 */
	private void showTrees() {
		tabs.removeChangeListener(tsl);
		tabs.removeAll();
		int count = 0;

		// Folders tree
		if(config.getShowFoldersTree()) {
			if(folderTree == null) {
				folderTree = createTree();
				if(projectView != null) {
					folderTree.setModel(projectView.getFolderViewModel());
				}
			}
			count++;
			tabs.addTab(FOLDERS_TAB_TITLE, new JScrollPane(folderTree));
		}
		else {
			folderTree = null;
		}

		// Files tree
		if(config.getShowFilesTree()) {
			if(fileTree == null) {
				fileTree = createTree();
				if(projectView != null) {
					fileTree.setModel(projectView.getFileViewModel());
				}
			}
			count++;
			tabs.addTab(FILES_TAB_TITLE, new JScrollPane(fileTree));
		}
		else {
			fileTree = null;
		}

		// Working files tree
		if(config.getShowWorkingFilesTree()) {
			if(workingFileTree == null) {
				workingFileTree = createTree();
				if(projectView != null) {
					workingFileTree.setModel(projectView.getWorkingFileViewModel());
				}
			}
			count++;
			tabs.addTab(WORKING_FILES_TAB_TITLE, new JScrollPane(workingFileTree));
		}
		else {
			workingFileTree = null;
		}

		toolbar.setVisible((count > 0) && config.getShowToolBar());
		tabs.setSelectedIndex(0);
		tabs.addChangeListener(tsl);
	}//}}}

}

