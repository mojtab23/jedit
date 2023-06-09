package nativebrowser;

import org.gjt.sp.jedit.View;

/*
 * NativeBrowserActions.java
 * part of the NativeBrowser plugin for the jEdit text editor
 * Copyright (C) 2010 Francois Rey
 * jedit at francois . rey . name
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
 *
 * $Id$
 */

interface NativeBrowserActions {
	void toggleMenuBar();
	void home();
	void renderBuffer(View view);
	void setHomepage(View view, String sURL);
	void browse(View view, String sURL);
	void forward();
	void back();
	void reload();
}
