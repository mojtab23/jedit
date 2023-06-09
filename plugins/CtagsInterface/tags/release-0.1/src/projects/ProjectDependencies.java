package projects;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.gjt.sp.jedit.AbstractOptionPane;
import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.MiscUtilities;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.gui.RolloverButton;

import projectviewer.config.ProjectOptions;
import projectviewer.vpt.VPTProject;
import ctags.CtagsInterfacePlugin;

@SuppressWarnings("serial")
public class ProjectDependencies extends AbstractOptionPane {

	private static final String PROJECT_DEPENDENCY = "projectDependency";
	private static final String TREE_DEPENDENCY = "treeDependency";
	JList projects;
	JList trees;
	DefaultListModel projectsModel;
	DefaultListModel treesModel;
	public ProjectDependencies() {
		super("CtagsInterface-ProjectDependencies");
	}

	private interface DependencyAsker {
		String getDependency();
	}
	protected void _init() {
		projectsModel = getListModel(PROJECT_DEPENDENCY);
		projects = createList("Projects:", projectsModel, new DependencyAsker () {
			public String getDependency() {
				return showProjectSelectionDialog();
			}
		});
		addSeparator();
		treesModel = getListModel(TREE_DEPENDENCY);
		trees = createList("Trees:", treesModel, new DependencyAsker () {
			public String getDependency() {
				return showSourceTreeSelectionDialog();
			}
		});
	}

	private void setListModel(String propertyName, DefaultListModel model) {
		Vector<String> list = new Vector<String>();
		for (int i = 0; i < model.size(); i++)
			list.add((String) model.getElementAt(i));
		setListProperty(propertyName, list);
	}
	private DefaultListModel getListModel(String propertyName) {
		Vector<String> list = getListProperty(propertyName);
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i < list.size(); i++)
			model.addElement(list.get(i));
		return model;
	}

	private Vector<String> getListProperty(String propertyName) {
		Vector<String> list = new Vector<String>();
		VPTProject project = ProjectOptions.getProject();
		int i = 0;
		while (true) {
			String value = project.getProperty(propertyName + i);
			if (value == null)
				break;
			list.add(value);
			i++;
		}
		return list;
	}
	private void setListProperty(String propertyName, Vector<String> list) {
		VPTProject project = ProjectOptions.getProject();
		for (int i = 0; i < list.size(); i++)
			project.setProperty(propertyName + i, list.get(i));
		for (int i = list.size(); true; i++) {
			String prop = propertyName + i;
			if (project.getProperty(prop) == null)
				break;
			project.removeProperty(prop);
		}
	}
	
	private JList createList(String title, final DefaultListModel model, final DependencyAsker da) {
		addComponent(new JLabel(title));
		final JList list = new JList(model);
		addComponent(new JScrollPane(list), GridBagConstraints.HORIZONTAL);
		JPanel buttons = new JPanel();
		JButton add = new RolloverButton(GUIUtilities.loadIcon("Plus.png"));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = da.getDependency();
				if (s != null) {
					int index = list.getSelectedIndex();
					model.add(index + 1, s);
					list.setSelectedIndex(index + 1);
				}
			}
		});
		JButton remove = new RolloverButton(GUIUtilities.loadIcon("Minus.png"));
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if (index >= 0) {
					model.removeElementAt(index);
					if (index < model.size())
						list.setSelectedIndex(index);
					else if (! model.isEmpty())
						list.setSelectedIndex(model.size() - 1);
				}
			}
		});
		buttons.add(add);
		buttons.add(remove);
		addComponent(buttons);
		return list;
	}

	private String showProjectSelectionDialog() {
		ProjectWatcher pw = CtagsInterfacePlugin.getProjectWatcher();
		String project = pw.getActiveProject(jEdit.getActiveView());
		Vector<String> nameVec = pw.getProjects();
		nameVec.remove(project);
		String [] names = new String[nameVec.size()];
		nameVec.toArray(names);
		String selected = (String) JOptionPane.showInputDialog(this, "Select project:",
			"Projects", JOptionPane.QUESTION_MESSAGE, null, names, names[0]);
		return selected;
	}
	private String showSourceTreeSelectionDialog() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Select root of source tree");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int ret = fc.showOpenDialog(this);
		if (ret != JFileChooser.APPROVE_OPTION)
			return null;
		String dir = fc.getSelectedFile().getAbsolutePath();
		return MiscUtilities.resolveSymlinks(dir);
	}
	protected void _save() {
		setListModel(PROJECT_DEPENDENCY, projectsModel);
		setListModel(TREE_DEPENDENCY, treesModel);
	}
}
