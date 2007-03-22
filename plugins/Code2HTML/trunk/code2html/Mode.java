/*
 * Mode.java - jEdit editing mode
 *
 * Copyright (C) 1998, 1999, 2000 Slava Pestov
 * Copyright (C) 1999 mike dillon
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
package code2html;

//{{{ Imports
import java.util.Hashtable;

import org.gjt.sp.jedit.MiscUtilities;
import org.gjt.sp.util.Log;
import code2html.syntax.TokenMarker;

import gnu.regexp.*;
//}}}

/**
 *  An edit mode defines specific settings for editing some type of file. One
 *  instance of this class is created for each supported edit mode.
 *
 * @author     Slava Pestov
 * @version    $Id$
 * @todo       Get latest mode from the jedit trunk or better yet - link to
 *      jEdit's real Mode class (org.gjt.sp.jedit.Mode)
 */
public class Mode {
    private RE filenameRE;
    private RE firstlineRE;
    private TokenMarker marker;
    private String name;
    private Hashtable props;

    //{{{ Mode constructor
    /**
     *  Creates a new edit mode.
     *
     * @param  name  The name used in mode listings and to query mode properties
     * @see          #getProperty(String)
     */
    public Mode(String name) {
        this.name = name;
        props = new Hashtable();
    }  //}}}

    //{{{ setProperties() method
    /**
     *  Should only be called by <code>XModeHandler</code>.
     *
     * @param  props  The new properties value
     * @since         jEdit 4.0pre3
     */
    public void setProperties(Hashtable props) {
        String filenameGlob = (String) getProperty("filenameGlob");
        String firstlineGlob = (String) getProperty("firstlineGlob");
        this.props = props;
        if (filenameGlob != null) {
            props.put("filenameGlob", filenameGlob);
        }
        if (firstlineGlob != null) {
            props.put("firstlineGlob", firstlineGlob);
        }
    }  //}}}

    //{{{ setProperty() method
    /**
     *  Sets a mode property.
     *
     * @param  key    The property name
     * @param  value  The property value
     */
    public void setProperty(String key, Object value) {
        props.put(key, value);
    }  //}}}

    //{{{ setTokenMarker() method
    /**
     *  Sets the token marker for this mode. This token marker will be cloned to
     *  obtain new instances.
     *
     * @param  marker  The new token marker
     */
    public void setTokenMarker(TokenMarker marker) {
        this.marker = marker;
    }  //}}}

    //{{{ getBooleanProperty() method
    /**
     *  Returns the value of a boolean property.
     *
     * @param  key  The property name
     * @return      The boolean property value
     * @since       jEdit 2.5pre3
     */
    public boolean getBooleanProperty(String key) {
        Object value = getProperty(key);
        if ("true".equals(value) || "on".equals(value) || "yes".equals(value)) {
            return true;
        } else {
            return false;
        }
    }  //}}}

    //{{{ getName() method
    /**
     *  Returns the internal name of this edit mode.
     *
     * @return    The name value
     */
    public String getName() {
        return name;
    }  //}}}

    //{{{ getProperty() method
    /**
     *  Returns a mode property.
     *
     * @param  key  The property name
     * @return      The property value
     * @since       jEdit 2.2pre1
     */
    public Object getProperty(String key) {
        String prefix = "mode." + name + ".";

        //if(jEdit.getBooleanProperty(prefix + "customSettings"))
        //{
        String property = Main.getProperty(prefix + key);
        if (property != null && property.length() != 0) {
            Object value;
            try {
                value = new Integer(property);
            } catch (NumberFormatException nf) {
                value = property;
            }
            return value;
        }
        //}

        Object value = props.get(key);
        if (value != null) {
            return value;
        }

        String global = Main.getProperty("buffer." + key);
        if (global != null) {
            try {
                return new Integer(global);
            } catch (NumberFormatException nf) {
                return global;
            }
        } else {
            return null;
        }
    }  //}}}

    //{{{ getTokenMarker() method
    /**
     *  Returns the token marker specified with <code>setTokenMarker()</code>.
     *  Should only be called by <code>TokenMarker.getExternalRuleSet()</code>.
     *
     * @return    The token marker value
     */
    public TokenMarker getTokenMarker() {
        loadIfNecessary();
        return marker;
    }  //}}}

    //{{{ accept() method
    /**
     *  Returns if the edit mode is suitable for editing the specified file. The
     *  buffer name and first line is checked against the file name and first
     *  line globs, respectively.
     *
     * @param  fileName   The buffer's name
     * @param  firstLine  The first line of the buffer
     * @return
     * @since             jEdit 3.2pre3
     */
    public boolean accept(String fileName, String firstLine) {
        if (filenameRE != null && filenameRE.isMatch(fileName)) {
            return true;
        }

        if (firstlineRE != null && firstlineRE.isMatch(firstLine)) {
            return true;
        }

        return false;
    }  //}}}

    //{{{ init()
    /**
     *  Initializes the edit mode. Should be called after all properties are
     *  loaded and set.
     */
    public void init() {
        try {
            String filenameGlob = (String) getProperty("filenameGlob");
            if (filenameGlob != null && filenameGlob.length() != 0) {
                filenameRE = new RE(MiscUtilities.globToRE(
                    filenameGlob), RE.REG_ICASE);
            }

            String firstlineGlob = (String) getProperty("firstlineGlob");
            if (firstlineGlob != null && firstlineGlob.length() != 0) {
                firstlineRE = new RE(MiscUtilities.globToRE(
                    firstlineGlob), RE.REG_ICASE);
            }
        } catch (REException re) {
            Log.log(Log.ERROR, this, "Invalid filename/firstline"
                 + " globs in mode " + name);
            Log.log(Log.ERROR, this, re);
        }
    }  //}}}

    //{{{ loadIfNecessary() method
    /**
     *  Loads the mode from disk if it hasn't been loaded already.
     *
     * @since    jEdit 2.5pre3
     */
    public void loadIfNecessary() {
        if (marker == null) {
            ModeUtilities.loadMode(this);
        }
    }  //}}}

    //{{{ toString() method
    /**
     *  Returns a string representation of this edit mode.
     *
     * @return
     */
    public String toString() {
        return getClass().getName() + "[" + getName() + "]";
    }  //}}}

    //{{{ unsetProperty() method
    /**
     *  Unsets a mode property.
     *
     * @param  key  The property name
     * @since       jEdit 3.2pre3
     */
    public void unsetProperty(String key) {
        props.remove(key);
    }
    //}}}
}

