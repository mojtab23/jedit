package common.gui.pathbuilder;

/*
 * Part of the JSwat plugin for the jEdit text editor
 * Copyright (C) 2001 David Taylor
 * dtaylo11@bigpond.net.au
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

import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.*;

public class SourceFileFilter extends FileFilter {
    public boolean accept(File f) {
        if(f.isDirectory())
            return true;

        return false;
    }

    public String getDescription() {
        return "Sourcepath elements (only directories)";
    }
}

