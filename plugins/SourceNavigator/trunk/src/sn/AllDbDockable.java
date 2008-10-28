package sn;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.gjt.sp.jedit.View;

import sn.SourceNavigatorPlugin.DbDescriptor;

@SuppressWarnings("serial")
public class AllDbDockable extends JPanel {

	private HashMap<DbDescriptor, DbDockable> dockables;
	private JPanel current;
	private JComboBox dbSelection;
	private View view;
	private JPanel empty;
	
	public AllDbDockable(View view) {
		super(new BorderLayout());
		this.view = view;
		dockables = new HashMap<DbDescriptor, DbDockable>();
		JPanel p = new JPanel(new BorderLayout());
		add(p, BorderLayout.NORTH);
		JLabel l = new JLabel("Select database:");
		p.add(l, BorderLayout.WEST);
		dbSelection = new JComboBox(SourceNavigatorPlugin.getDbDescriptors());
		dbSelection.insertItemAt("None", 0);
		dbSelection.setSelectedIndex(0);
		p.add(dbSelection, BorderLayout.CENTER);
		dbSelection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setDatabase(e.getItem());
			}
		});
	}
	
	private void setDatabase(Object item) {
		if (current != null) {
			remove(current);
		}
		if (item instanceof DbDescriptor) {
			DbDescriptor desc = (DbDescriptor) item;
			current = dockables.get(item);
			if (current == null) {
				DbDockable dockable = new DbDockable(
					view, desc.db, desc.columns, desc.fileCol, desc.lineCol);
				dockables.put(desc, dockable);
				current = dockable;
			}
		} else {
			if (empty == null)
				empty = new JPanel();
			current = empty;
		}
		add(current, BorderLayout.CENTER);
		validate();
	}
			
}
