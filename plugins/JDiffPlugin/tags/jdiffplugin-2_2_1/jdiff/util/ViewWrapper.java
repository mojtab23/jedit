/*
 * Copyright (c) 2008, Dale Anson
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

package jdiff.util;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.View;

/**
 * This class is to interact with some private methods in the View class, in
 * particular, to get and set the split configuration of the view so the
 * configuration can be restored after a diff.
 * TODO: this is a hack.  The View class has been fixed so access to get/setSplitConfig
 * are both public now.  I'm leaving this code in place for the moment, but it isn't
 * called from anywhere.  This class and PrivilegedAccessor can both be removed
 * after the release of jEdit 4.3pre17.
 */
public class ViewWrapper {

    // the view to wrap
    private View view;

    /**
     * @param view the view to wrap
     */
    public ViewWrapper(View view) {
        this.view = view;
    }

    public void setSplitConfig(Buffer buffer, String config) {
        try {
            Object[] args = new Object[]{buffer, config};
            Class[] classTypes = new Class[]{Buffer.class, String.class};
            PrivilegedAccessor.getMethod( view, "setSplitConfig", classTypes ).invoke( view, args );
        }
        catch(Exception e) {    // NOPMD
            // ignored
            e.printStackTrace();
        }
    }

    public String getSplitConfig() {
        try {
            return (String)PrivilegedAccessor.invokeMethod(view, "getSplitConfig", null);
        }
        catch(Exception e) {
            return null;
        }
    }
}