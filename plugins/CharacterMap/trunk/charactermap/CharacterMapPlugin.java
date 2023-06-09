/*
 * CharacterMapPlugin.java
 *
 * Copyright (C) 2000, 2003 Slava Pestov
 * Copyright (C) 2003 Mark Wickens
 * Copyright (C) 2003 Mike Dillon
 * Copyright (C) 2012 Max Funk
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
package charactermap;

import org.gjt.sp.jedit.EditPlugin;

/**
 * The character map plugin core class.
 * @see org.gjt.sp.jedit.EditPlugin
 *
 * @author   Slava Pestov
 * @author   Mark Wickens
 * @author   Mike Dillon
 * @author   Max Funk
 * @version  1.3
 */
public class CharacterMapPlugin extends EditPlugin
{
	/** Name used in dockables.xml and in menu actions */
	public static final String NAME = "character-map";
	/** Name prefix used for property settings */
	public static final String NAME_PREFIX = "character-map.";
	/** Name prefix used for option propery settings */
	public static final String OPTION_PREFIX = "options.character-map.";
}
