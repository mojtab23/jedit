package ftp;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import org.gjt.sp.jedit.AbstractOptionPane;
import common.gui.FileTextField;
import org.gjt.sp.jedit.jEdit;


/** FTP option pane */

@SuppressWarnings("serial")
public class OptionPane extends AbstractOptionPane implements ActionListener {
	
	
	JCheckBox storePasswords;	
	JCheckBox useKeyFile;
	FileTextField keyFile;
	JCheckBox enableCompression;
	
	public OptionPane() {
		super("ftp");
	}
	protected void _init() {						
		storePasswords = new JCheckBox(jEdit.getProperty("options.ftp.savePasswords"),
			jEdit.getBooleanProperty("vfs.ftp.storePassword"));
		storePasswords.addActionListener(this);
		addComponent(storePasswords);
		
		useKeyFile = new JCheckBox(jEdit.getProperty("options.ftp.useKeyFile"));
		useKeyFile.setToolTipText(jEdit.getProperty("options.ftp.useKeyFile.tooltip"));
		useKeyFile.setSelected(jEdit.getBooleanProperty("ftp.useKeyFile"));
		useKeyFile.addActionListener(this);
				
		keyFile = new FileTextField(jEdit.getProperty("ftp.passKeyFile"), false);
		keyFile.setToolTipText(jEdit.getProperty("options.ftp.useKeyFile.tooltip"));
		addComponent(useKeyFile , keyFile);
		
		keyFile.getTextField().setEnabled(useKeyFile.isSelected());
		useKeyFile.setEnabled(storePasswords.isSelected());
		
		
		enableCompression = new JCheckBox(jEdit.getProperty("options.sftp.enableCompression"),
				jEdit.getBooleanProperty("vfs.sftp.compression"));
		addComponent(enableCompression);
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == storePasswords) {
			boolean sel = storePasswords.isSelected();
			useKeyFile.setEnabled(sel);
			keyFile.getTextField().setEnabled(sel);			
		}
		if (e.getSource() == useKeyFile) {
			keyFile.getTextField().setEnabled(useKeyFile.isSelected());
		}
	}
	
	protected void _save() {		

		jEdit.setBooleanProperty("ftp.useKeyFile", useKeyFile.isSelected());
		if (useKeyFile.isSelected()) {
			jEdit.setProperty("ftp.passKeyFile", keyFile.getTextField().getText());
		}
		jEdit.setBooleanProperty("vfs.ftp.storePassword", storePasswords.isSelected());
		jEdit.setBooleanProperty("vfs.sftp.compression", enableCompression.isSelected());
	}
}

