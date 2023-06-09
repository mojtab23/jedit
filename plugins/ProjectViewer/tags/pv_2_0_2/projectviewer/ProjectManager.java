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
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package projectviewer;

//{{{ Imports
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

import java.io.File;
import java.io.Writer;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

import com.microstar.xml.XmlParser;
import com.microstar.xml.HandlerBase;

import org.gjt.sp.util.Log;

import projectviewer.vpt.VPTRoot;
import projectviewer.vpt.VPTProject;
import projectviewer.config.ProjectViewerConfig;
import projectviewer.persist.OldConfigLoader;
import projectviewer.persist.ProjectPersistenceManager;
//}}}

/**
 *	This class takes care of the global project configuration, that is, the list
 *	of configured projects. This provides functionality to load the project
 *	list in the project viewer and to map project names to configuration file
 *	names.
 *
 *	@author		Marcelo Vanzin (with some code from older versions)
 *	@version	$Id$
 */
public final class ProjectManager {

	//{{{ Static members & constants

	private final static String CONFIG_FILE = "pv.xml";

	private final static String PROJECT_ROOT	= "projects";
	private final static String PROJECT_ELEMENT	= "project";
	private final static String PRJ_NAME		= "name";
	private final static String PRJ_FILE		= "file";

	private static ProjectManager manager;

	//{{{ getInstance() method
	/** Returns the project manager instance. */
	public static synchronized ProjectManager getInstance() {
		if (manager == null) {
			manager = new ProjectManager();
			try {
				manager.loadConfig();
			} catch (IOException ioe) {
				Log.log(Log.ERROR, manager, ioe);
			}
		}
		return manager;
	} //}}}

	//{{{ writeXMLHeader(String, Writer) method
	/**
	 *	Writes an XML header to the given writer. If encoding is not null,
	 *	it is written in the encoding field; else, UTF-8 is used.
	 */
	public static void writeXMLHeader(String encoding, Writer out) throws IOException {
		out.write("<?xml version=\"1.0\" encoding=\"" +
			((encoding != null) ? encoding : "UTF8") +
			"\" ?>\n\n");
	} //}}}

	//}}}

	//{{{ Constructor

	private ProjectManager() {
		projects = new TreeMap();
	}

	//}}}

	//{{{ Instance variables

	private TreeMap projects;

	//}}}

	//{{{ loadConfig() method
	/**
	 *	Reads the list of projects from disk. If the old configuration style is
	 *	found, the OldConfigLoader is called to translate to the new object
	 *	style, the data is saved, the list is cleared (so that we don't have
	 *	useless data floating around) and the new config is finally loaded
	 *	from the disk.
	 *
	 *	@throws	IOException		Shouldn't happen, but who knows...
	 */
	public void loadConfig() throws IOException {
		InputStream cfg = ProjectPlugin.getResourceAsStream(CONFIG_FILE);
		if (cfg == null) {
			Log.log(Log.NOTICE, this, "Converting old ProjectViewer configuration...");
			// Load old config style data
			OldConfigLoader.load(this);

			// save data in new style
			save();

			ProjectViewerConfig config = ProjectViewerConfig.getInstance();
			if (config.getLastProject()!= null && !projects.containsKey(config.getLastProject())) {
				config.setLastProject(null);
			}

			// clear the list
			VPTRoot.getInstance().removeAllChildren();
			projects.clear();

			// re-instantiate the InputStream
			cfg = ProjectPlugin.getResourceAsStream(CONFIG_FILE);
		}

		// OK, let's parse the config file
		try {
			XmlParser parser = new XmlParser();
			parser.setHandler(new PVConfigHandler());
			parser.parse(null, null, new InputStreamReader(cfg));
		} catch (Exception e) {
			Log.log(Log.ERROR, this, e);
		}

		// Projects loaded, add all of them to the root node
		VPTRoot root = VPTRoot.getInstance();
		for (Iterator it = projects.keySet().iterator(); it.hasNext(); ) {
			root.add(((Entry)projects.get(it.next())).project);
		}
	} //}}}

	//{{{ save() method
	/** Saves all the project data to the disk (config + each project). */
	public void save() throws IOException {
		synchronized (projects) {
			// save each project's data, if loaded
			// if not loaded, no need to save.
			for (Iterator it = projects.keySet().iterator(); it.hasNext(); ) {
				String pName = (String) it.next();
				Entry e = (Entry) projects.get(pName);
				if (e.isLoaded) {
					if (e.fileName == null) {
						e.fileName = createFileName(pName);
					}
					ProjectPersistenceManager.save(e.project, e.fileName);
				}
			}

			saveProjectList();
		}
	} //}}}

	//{{{ saveProject(VPTProject) method
	/**
	 *	Save the project's data to the config file. Before calling this method,
	 *	ensure that the project is in the internal list of projects (i.e., if
	 *	it is a new project, call {@link #addProject(VPTProject) addProject(VPTProject)}
	 *	before calling this method).
	 */
	public void saveProject(VPTProject p) {
		Entry e = (Entry) projects.get(p.getName());
		synchronized (e) {
			if (e.fileName == null) {
				e.fileName = createFileName(p.getName());
				// since we're saving the project for the first time, let's be
				// paranoid and save all configuration along with it
				try{
					saveProjectList();
				} catch (IOException ioe) {
					Log.log(Log.ERROR, this, ioe);
				}
			}
			try {
				ProjectPersistenceManager.save(p, e.fileName);
			} catch (IOException ioe) {
				Log.log(Log.ERROR, this, ioe);
			}
			e.isLoaded = true;
		}
	} //}}}

	//{{{ removeProject(VPTProject) method
	/**
	 *	Removes the project from the internal list of projects. Removes the
	 *	project's config file (if it exists), and notifies the Viewer that
	 *	the project does not exist anymore.
	 */
	public void removeProject(VPTProject p) {
		Entry e = (Entry) projects.get(p.getName());
		if (e == null) return;

		synchronized (e) {
			if (e.fileName != null) {
				new File(ProjectPlugin.getResourcePath("projects/" + e.fileName)).delete();
				// project list changed, save "global" data.
				try{
					saveProjectList();
				} catch (IOException ioe) {
					Log.log(Log.ERROR, this, ioe);
				}
			}
			projects.remove(p.getName());
		}
		ProjectViewer.projectRemoved(this, p);
		p.removeAllChildren(); // for the GC
	} //}}}

	//{{{ renameProject(String, String) method
	/** Updates information about a project to reflect its name change. */
	public void renameProject(String oldName, String newName) {
		Entry e = (Entry) projects.remove(oldName);
		projects.put(newName, e);
		if (e.fileName != null) {
			if (!e.isLoaded) {
				String oldFname = e.fileName;
				e.fileName = createFileName(newName);
				new File(ProjectPlugin.getResourcePath("projects/" + oldFname)).renameTo(
					new File(ProjectPlugin.getResourcePath("projects/" + e.fileName)));
			} else {
				new File(ProjectPlugin.getResourcePath("projects/" + e.fileName)).delete();
			}
		}
		saveProject(e.project);
		ProjectViewer.nodeChanged(e.project);
	} //}}}

	//{{{ addProject(VPTProject) method
	/** Adds a project to the list. */
	public void addProject(VPTProject p) {
		Entry e = new Entry();
		e.project = p;
		e.isLoaded = true;
		projects.put(p.getName(), e);

		VPTRoot root = VPTRoot.getInstance();
		ProjectViewer.insertNodeInto(p, root);
		ProjectViewer.nodeStructureChangedFlat(root);
		ProjectViewer.updateProjectCombos();
		ProjectViewer.fireProjectAdded(this, p);
	} //}}}

	//{{{ getProject(String) method
	/**
	 *	Returns the project with the given name. If the project is not yet
	 *	loaded, load its configuration from the disk. It it does not exist,
	 *	return null.
	 */
	public VPTProject getProject(String name) {
		Entry e = (Entry) projects.get(name);
		if (!e.isLoaded) {
			synchronized (e) {
				if (!e.isLoaded) {
					if (e.fileName != null) {
						if (ProjectPersistenceManager.load(e.project, e.fileName) != null) {
							e.isLoaded = true;
						} else {
							Log.log(Log.WARNING, this, "Error loading project.");
						}
					} else {
						Log.log(Log.WARNING, this, "Shouldn't reach this statement!");
					}
				}
			}
		}
		return e.project;
	} //}}}

	//{{{ getProjects() method
	/**
	 *	Returns an iterator that points to the (ordered) list of project names
	 *	managed by this manager. The Iterator is read-only.
	 */
	public Iterator getProjects() {
		ArrayList lst = new ArrayList();
		for (Iterator it = projects.values().iterator(); it.hasNext(); ) {
			lst.add(((Entry)it.next()).project);
		}
		return lst.iterator();
	} //}}}

	//{{{ isLoaded(String) method
	/**
	 *	Returns whether a project is loaded or not.
	 *
	 *	@param	pName	The project's name.
	 *	@return	If the project was loaded from disk.
	 *	@throws NullPointerException	If the project does not exist.
	 */
	public boolean isLoaded(String pName) {
		return ((Entry)projects.get(pName)).isLoaded;
	} //}}}

	//{{{ hasProject(String) method
	/** Returns whether a project with the given name exists. */
	public boolean hasProject(String name) {
		return projects.containsKey(name);
	} //}}}

	//{{{ Private Stuff

	//{{{ createFileName(String) method
	/**
	 *	Crates an unique file name where to save a project's configuration
	 *	based on the project's name.
	 */
	 private String createFileName(String projName) {
		String illegalChars = " /:\\\"'";
		String substitutes  = "_---__";

		StringBuffer fName = new StringBuffer(projName);
		for (int i = 0; i < fName.length(); i++) {
			int idx = illegalChars.indexOf(fName.charAt(i));
			if (idx != -1) {
				fName.setCharAt(i, substitutes.charAt(idx));
			}
		}

		File f = new File(ProjectPlugin.getResourcePath("projects" + File.separator + fName.toString() + ".xml"));
		int cntr = 0;
		while (f.exists()) {
			cntr++;
			f = new File(ProjectPlugin.getResourcePath("projects" + File.separator + fName.toString() + "_" + cntr + ".xml"));
		}

		return f.getName();
	} //}}}

	//{{{ saveProjectList() method
	/**
	 *	Saves the "global" data for the projects: the list of projects and
	 *	the file names where each project data is stored.
	 */
	private void saveProjectList() throws IOException {
		// save the global configuration
		OutputStream outs = ProjectPlugin.getResourceAsOutputStream(CONFIG_FILE);
		OutputStreamWriter out = new OutputStreamWriter(outs, "UTF-8");
		writeXMLHeader("UTF-8", out);
		out.write("<" + PROJECT_ROOT + ">\n");
		for (Iterator it = projects.keySet().iterator(); it.hasNext(); ) {
			String pName = (String) it.next();
			Entry e = (Entry) projects.get(pName);
			if (e.fileName != null) {
				out.write("<");
				out.write(PROJECT_ELEMENT);
				out.write(" ");
				out.write(PRJ_NAME);
				out.write("=\"");
				for (int i = 0; i < pName.length(); i++) {
					switch (pName.charAt(i)) {
						case '<':
							out.write("&lt;");
							break;
						case '>':
							out.write("&gt;");
							break;
						case '&':
							out.write("&amp;");
							break;
						case '"':
							out.write("&quot;");
							break;
						case '\'':
							out.write("&apos;");
							break;
						default:
							out.write(pName.charAt(i));
					}
				}
				out.write("\" ");
				out.write(PRJ_FILE);
				out.write("=\"");
				for (int i = 0; i < e.fileName.length(); i++) {
					switch (e.fileName.charAt(i)) {
						case '<':
							out.write("&lt;");
							break;
						case '>':
							out.write("&gt;");
							break;
						case '&':
							out.write("&amp;");
							break;
						case '"':
							out.write("&quot;");
							break;
						case '\'':
							out.write("&apos;");
							break;
						default:
							out.write(e.fileName.charAt(i));
					}
				}
				out.write("\"/>\n");
			}
		}
		out.write("</" + PROJECT_ROOT + ">\n");
		out.flush();
		out.close();
	} //}}}

	//{{{ PVSAXHandler class
	/**	SAX handler that takes care of reading the configuration file. */
	private class PVConfigHandler extends HandlerBase {

		private HashMap attrs = new HashMap();

		//{{{ attribute(String, String, boolean) method
		public void attribute(String name, String value, boolean specified) {
			attrs.put(name, value);
		} //}}}

		//{{{ startElement() method
		/** Reads "project" elements and adds them to the list. */
		public void startElement(String qName) {
			if (qName.equals(PROJECT_ELEMENT)) {
				String pName = (String) attrs.get(PRJ_NAME);
				Entry e = new Entry();
				e.fileName = (String) attrs.get(PRJ_FILE);
				e.isLoaded = false;
				e.project = new VPTProject(pName);
				projects.put(pName, e);
				attrs.clear();
			} else if (!qName.equals(PROJECT_ROOT)) {
				Log.log(Log.WARNING, this, "Unknown node in config file: " + qName);
			}
		} //}}}

	} //}}}

	//{{{ Entry class
	/** Holds info for a project in the internal map. */
	private static class Entry {

		public VPTProject	project;
		public String		fileName = null;
		public boolean		isLoaded = false;

	} //}}}

	//}}}

}

