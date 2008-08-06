package myDoggy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.SettingsXML.Saver;
import org.gjt.sp.jedit.gui.DockableWindowManager.DockingLayout;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyDoggyDockingLayout extends DockingLayout {

	private ToolWindowManager wm = null;
	private int index = -1;
	private String filename = null;
	
	public MyDoggyDockingLayout() {
	}
	
	public String getPersistenceFilename() {
		return filename;
	}
	public void setIndex(int i) {
		index = i;
	}
	public void setWindowManager(ToolWindowManager twm) {
		wm = twm;
	}
	@Override
	public DefaultHandler getPerspectiveHandler() {
		return new DefaultHandler() {
			@Override
			public void startElement(String uri, String localName, String name,
					Attributes attributes) throws SAXException {
				if (name.equals("MYDOGGY")) {
					filename = attributes.getValue("FILE");
					if (filename == null)
						return;
				}
			}
		};
	}

	private String getFilename() {
		if (index == -1)
			return "mydoggy.def";
		return jEdit.getSettingsDirectory() + File.separator + "mydoggy." + index;
	}
	@Override
	public void savePerspective(Saver out, String lineSep) throws IOException {
		String filename = getFilename();
		out.write("<MYDOGGY FILE=\"");
		out.write(filename);
		out.write("\" />");
		out.write(lineSep);
		FileOutputStream outputStream = new FileOutputStream(filename);
		wm.getPersistenceDelegate().save(outputStream);
		outputStream.close();
	}

}
