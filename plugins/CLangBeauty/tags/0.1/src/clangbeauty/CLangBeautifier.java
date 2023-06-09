
/*
 * CLangBeautifier.java - clang-format command-line
 * :tabSize=4:indentSize=4:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 2015, Dale Anson
 *
 * The CLangBeautifier plugin is licensed under the GNU General Public License.
 */
package clangbeauty;

import java.io.File;

import java.util.Arrays;
import java.util.List;

import org.gjt.sp.jedit.jEdit;

import outerbeauty.OuterBeautifier;

/**
 * Execute clang-format in pipe mode
 **/
public class CLangBeautifier extends OuterBeautifier {

	@Override
	protected List<String> getCommandLine() {
		String exe = CLangBeautyPlugin.getCLangFormatExe();
		return Arrays.asList( exe, "-style=file");
	}
	
	@Override
	protected File getWorkingDirectory() {
		String bufferPath = jEdit.getActiveView().getEditPane().getBuffer().getPath();
		File file = new File(bufferPath);
		return file.getParentFile(); 
	}
}
