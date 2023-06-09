/**
 * BeanShellEvaluator.java - Sql Plugin
 * Copyright (C) 2001 Sergey V. Udaltsov
 * svu@users.sourceforge.net
 *
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
package sql.preprocessors;

import sql.*;

/**
 *  Description of the Class
 *
 * @author     svu
 * @created    22 ������� 2002 �.
 */
public class BeanShellEvaluator extends Preprocessor
{
  /**
   *Description of the Method
   *
   * @param  text  Description of Parameter
   * @return       Description of the Returned Value
   * @since
   */
  public String doProcess( String text )
  {
    return (String) org.gjt.sp.jedit.BeanShell.eval( view, text, false );
  }
}

