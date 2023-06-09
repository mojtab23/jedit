
/*
 * CLangBeautifierPlugin.java - jEdit plugin entry point
 * :tabSize=4:indentSize=4:noTabs=true:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 2015, Dale Anson
 *
 * The CLangBeautifier plugin is licensed under the GNU General Public License.
 */
package clangbeauty;

import java.io.File;

import org.gjt.sp.jedit.EditPlugin;
import org.gjt.sp.jedit.jEdit;

/**
 * plugin entry point.
 */
public class CLangBeautyPlugin extends EditPlugin {

    public static String getCLangFormatExe() {
        return jEdit.getProperty( "clangbeauty.clang-format.exe", "clang-format" );
    }

}
