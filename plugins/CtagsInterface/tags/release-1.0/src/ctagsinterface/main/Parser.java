package ctagsinterface.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public class Parser {
	
	String tagFileDir;
	HashMap<String, String> sourcePathMap;
	
	interface TagHandler {
		void processTag(Tag t);
	}
	
	void parseTagFile(String tagFile, TagHandler handler) {
		if (tagFile == null || tagFile.length() == 0)
			return;
		tagFileDir = new File(tagFile).getAbsoluteFile().getParent();
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(tagFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			String line;
			while ((line = in.readLine()) != null) {
				Tag t = parse(line);
				if (t == null)
					continue;
				handler.processTag(t);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setSourcePathMapping(HashMap<String, String> map) {
		sourcePathMap = map;
	}
	
	private Tag parse(String line) {
		Hashtable<String, String> info =
			new Hashtable<String, String>();
		if (line.endsWith("\n") || line.endsWith("\r"))
			line = line.substring(0, line.length() - 1);
		// Find the end of the pattern (pattern may include "\t")
		int idx = line.lastIndexOf(";\"\t");
		if (idx < 0)
			return null;
		// Fixed fields (tag, file, pattern/line number)
		String fields[] = line.substring(0, idx).split("\t", 3);
		if (fields.length < 3)
			return null;
		String file = fields[1];
		if (! new File(file).isAbsolute())
			file = tagFileDir + "/" + fields[1];
		if (sourcePathMap != null) {
			String target = sourcePathMap.get(file);
			if (target != null) {
				file = target;
			}
		}
		Tag t = new Tag(fields[0], file, fields[2]);
		// Extensions
		fields = line.substring(idx + 3).split("\t");
		for (int i = 0; i < fields.length; i++)
		{
			String pair[] = fields[i].split(":", 2);
			if (pair.length == 1)	// e.g. file:
				info.put(pair[0], "");
			else if (pair.length == 2)
				info.put(pair[0], pair[1]);
		}
		t.setExtensions(info);
		return t;
	}
}
