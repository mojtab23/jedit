/*
 * $Revision$
 * $Date$
 * $Author$
 *
 * Copyright (C) 2001 C. Scott Willy
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

 package cswilly.spell;

import java.util.*;

/**
 * Models the result of a spell check of a single word.
 *<p>
  *   Features of a spell check result are:</p>
 *   <ul>
 *     <li>contains the result of checking one&nbsp; word</li>
 *     <li>has the original word</li>
 *     <li>has a type of result (i.e. OK, ERROR, NONE, SUGGESTION)</li>
 *     <li>has a list of suggested replacement words (if type is SUGGESTION)</li>
 *     <li>has an offset into the line where the original word was found</li>
 *   </ul>
 *   <p>The possible result types are:
 *   <dl>
 *     <dt>OK</dt>
 *     <dd>the original word was found in the dictionary.</dd>
 *     <dt>ERROR</dt>
 *     <dd>Internal error (e.g. aspell process died, wrong version of aspell, phase
 *       of moon wrong).</dd>
 *     <dt>NONE</dt>
 *     <dd>the original word was not found in the dictionary and no suggestions were
 *       found.</dd>
 *     <dt>SUGGESTION</dt>
 *     <dd>the original word was not found in the dictionary and one or more
 *       suggested replacements were found.</dd>
 *   </dl>
 */
public
class Result
{
  private  int     _offset;
  private  Type    _type;
  private  List<String>    _suggestions;
  private  String  _originalWord;

  public static final Type ERROR      = new Type( "Error" );
  public static final Type OK         = new Type( "OK " );
  public static final Type NONE       = new Type( "None" );
  public static final Type SUGGESTION = new Type( "Suggestion" );


  public Result( String line )
  {
    if( line == null || line.length() <= 0 )
      processError( line );
    else if( line.charAt( 0 ) == '*' )
      processOk( line );
    else if( line.charAt( 0 ) == '&' )
      processSuggestion( line );
    else if( line.charAt( 0 ) == '#' )
      processNone( line );
    else
      processError( line );
  }

  public Result(int offset, Type type, List<String> suggestions, String originalWord)
  {
	  _offset = offset;
	  _type = type;
	  _suggestions = suggestions;
	  _originalWord = originalWord;
  }
  
  public
  int getOffset()
  {
    return _offset;
  }

  public void setType(Type t){
	  if(Result.OK == t)_suggestions = null;
	  _type = t;
  }
  
  public
  Type getType()
  {
    return _type;
  }

  public
  List<String> getSuggestions()
  {
    return _suggestions;
  }

  public
  String getOriginalWord()
  {
    return _originalWord;
  }

  public boolean equals(Object o){
	  if(o==null)return false;
	  if(o==this)return true;
	  if(!(o instanceof Result))return false;
	  
	  Result other = (Result)o;
	  return(_offset == other._offset
		     && ((_type == null && other._type == null) || _type.equals(other._type))
			 && ((_originalWord == null && other._originalWord == null) || _originalWord.equals(other._originalWord))
		     && ((_suggestions == null && other._suggestions == null) || _suggestions.equals(other._suggestions))
	  );
  }
  public
  String toString()
  {
    StringBuffer buff = new StringBuffer();
    buff.append( "[type:" );
    buff.append( _type );
    buff.append( ",originalWord:" );
    buff.append( _originalWord );
    buff.append( ",offset:" );
    buff.append( _offset );
    buff.append( ",suggestions:" );
    buff.append( _suggestions );
    return buff.toString();
  }

  private
  void processError( String line )
  {
    _offset = 0;
    _type = ERROR;
    _suggestions = new ArrayList();
    _originalWord = "";
  }

  private
  void processOk( String line )
  {
    _offset = 0;
    _type = OK;
    _suggestions = new ArrayList();
    _originalWord = "";
  }

  private
  void processNone( String line )
  {
    _type = NONE;
    _suggestions = new ArrayList<String>();

    StringTokenizer st = new StringTokenizer( line );
    st.nextToken(); // skip '#'
    _originalWord = st.nextToken();
    _offset = Integer.parseInt( st.nextToken() );
  }

  private
  void processSuggestion( String line )
  {

    _type = SUGGESTION;

    StringTokenizer st = new StringTokenizer( line );
    st.nextToken(); // skip '#'
    _originalWord = st.nextToken();
    int count = Integer.parseInt( st.nextToken().trim() );
    _suggestions = new ArrayList( count );
    _offset = Integer.parseInt( st.nextToken( ":" ).trim() );

    st = new StringTokenizer( st.nextToken( ":" ), "," );
    while( st.hasMoreTokens() )
    {
      String suggestion = st.nextToken().trim();
      _suggestions.add( suggestion );
    }
  }

  private static
  class Type
  {
    private final String _typeName;
    Type( String typeName )
    {
      _typeName = typeName;
    }

	public boolean equals(Object o){
		if(o==null)return false;
		if(o==this)return true;
		if(!(o instanceof Type))return false;
		Type t = (Type) o;
		return ((_typeName == null && t._typeName == null)
			   || _typeName.equals(t._typeName)); 
	}
	
    public
    String toString()
    {
      return _typeName;
    }
  }
}
