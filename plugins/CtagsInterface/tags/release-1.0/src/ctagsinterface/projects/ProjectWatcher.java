package ctagsinterface.projects;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.gjt.sp.jedit.EBComponent;
import org.gjt.sp.jedit.EBMessage;
import org.gjt.sp.jedit.EditBus;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;

import projectviewer.ProjectManager;
import projectviewer.ProjectViewer;
import projectviewer.event.ProjectUpdate;
import projectviewer.event.StructureUpdate;
import projectviewer.vpt.VPTFile;
import projectviewer.vpt.VPTNode;
import projectviewer.vpt.VPTProject;
import ctagsinterface.db.TagDB;
import ctagsinterface.main.CtagsInterfacePlugin;
import ctagsinterface.options.ProjectsOptionPane;

public class ProjectWatcher implements EBComponent {

	Set<String> watched;
	
	public ProjectWatcher() {
		watched = new HashSet<String>();
		if (ProjectsOptionPane.getAutoUpdateProjects())
			updateWatchers();
		start();
	}

	public void start() {
		EditBus.addToBus(this);
	}

	public void stop() {
		EditBus.removeFromBus(this);
	}

	public void updateWatchers() {
		Vector<String> projects = ProjectsOptionPane.getProjects();
		Iterator<String> all = getProjects().iterator();
		while (all.hasNext()) {
			String project = all.next();
			if (projects.contains(project)) {
				if (! watched.contains(project)) {
					watchProject(project);
					watched.add(project);
				}
			}
			else if (watched.contains(project)) {
				unwatchProject(project);
				watched.remove(project);
			}
		}
	}

	public Vector<String> getFiles(String project) {
		ProjectManager pm = ProjectManager.getInstance();
		VPTProject p = pm.getProject(project);
		if (p == null)
			return null;
		Vector<String> files = new Vector<String>();
		Iterator<VPTNode> nodes = p.getOpenableNodes().iterator();
		while (nodes.hasNext()) {
			VPTNode node = nodes.next();
			files.add(node.getNodePath());
		}
		return files;
	}

	public Vector<String> getProjects() {
		ProjectManager pm = ProjectManager.getInstance();
		Vector<String> projects = new Vector<String>();
		Iterator<VPTProject> it = pm.getProjects().iterator();
		while (it.hasNext()) {
			VPTProject proj = it.next();
			projects.add(proj.getName());
		}
		return projects;
	}

	public String getActiveProject(View view) {
		VPTProject project = ProjectViewer.getActiveProject(view);
		if (project == null)
			return null;
		return project.getName();
	}
	
	private void watchProject(String project) {
		VPTProject proj = ProjectManager.getInstance().getProject(project);
		if (proj == null)
			return;
		watched.add(project);
	}
	private void unwatchProject(String project) {
		VPTProject proj = ProjectManager.getInstance().getProject(project);
		if (proj == null)
			return;
		watched.remove(project);
	}
	
	// ProjectListener methods
	
	public void handleFilesChanged(ProjectUpdate pu) {
		Vector<String> removed = new Vector<String>();
		Collection<VPTFile> nodes = pu.getRemovedFiles();
		for (VPTFile node: nodes)
			removed.add(node.getNodePath());
		Vector<String> added = new Vector<String>();
		nodes = pu.getAddedFiles();
		for (VPTFile node: nodes)
			added.add(node.getNodePath());
		CtagsInterfacePlugin.updateProject(pu.getProject().getName(),
			added, removed);
	}

	public void handlePropertiesChanged(ProjectUpdate pu) {
		// TODO Auto-generated method stub
	}

	public void handleStructureUpdate(StructureUpdate su) {
		if (su.getType() == StructureUpdate.Type.PROJECT_ADDED) {
			String name = su.getNode().getName();
			CtagsInterfacePlugin.insertOrigin(TagDB.PROJECT_ORIGIN, name);
			watched.add(name);
		}
		else if (su.getType() == StructureUpdate.Type.PROJECT_REMOVED) {
			Vector<String> projects = CtagsInterfacePlugin.getDB().getOrigins(
				TagDB.PROJECT_ORIGIN);
			String name = su.getNode().getName();
			if (! projects.contains(name))
				return;
			int res = JOptionPane.showConfirmDialog(jEdit.getActiveView(), 
				"Remove tag database of project '" + name + "'?",
				"CtagsInterface plugin", JOptionPane.YES_NO_OPTION);
			if (res != JOptionPane.YES_OPTION)
				return;
			CtagsInterfacePlugin.deleteOrigin(TagDB.PROJECT_ORIGIN, name);
			watched.remove(name);
		}
	}
	
	public void handleMessage(EBMessage message) {
		if (message instanceof ProjectUpdate) {
			ProjectUpdate pu = (ProjectUpdate) message;
			String name = ((ProjectUpdate) message).getProject().getName();
			if (! watched.contains(name))
				return;
			if (pu.getType() == ProjectUpdate.Type.FILES_CHANGED)
				handleFilesChanged(pu);
			else if (pu.getType() == ProjectUpdate.Type.PROPERTIES_CHANGED)
				handlePropertiesChanged(pu);
		} else if (message instanceof StructureUpdate) {
			if (! ProjectsOptionPane.getTrackProjectList())
				return;
			StructureUpdate su = (StructureUpdate) message;
			if ((su.getType() == StructureUpdate.Type.PROJECT_ADDED) ||
				(su.getType() == StructureUpdate.Type.PROJECT_REMOVED))
			{
				handleStructureUpdate(su);
			}
		}
	}

}
