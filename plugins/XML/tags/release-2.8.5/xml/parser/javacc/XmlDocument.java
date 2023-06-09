/*
* XmlDocument.java -- classes to represent HTML documents as parse trees
* Copyright (C) 1999 Quiotix Corporation.  
* Copyright (C) 2011 Eric Le Lay  
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License, version 2, as 
* published by the Free Software Foundation.  
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License (http://www.gnu.org/copyleft/gpl.txt)
* for more details.
*/

package xml.parser.javacc;

import java.util.*;

import javax.swing.text.Position;

import sidekick.html.parser.html.HtmlDocument;
import sidekick.util.*;
import xml.NamespaceBindings;

/**
 * Represents an XML document as a sequence of elements.  The defined
 * element types are: Tag, EndTag, TagBlock (matched tag..end tag, with the
 * intervening elements), Comment, Text, Newline, and Annotation.
 * <p/>
 * <P> The various element types are defined as nested classes within
 * XmlDocument.
 * <p>
 * danson: Modified for Beauty plugin for jEdit, added ability to handle jsp,
 * configuration settings, and so on.
 *
 * @author Brian Goetz, Quiotix
 * @see sidekick.html.parser.html.XmlVisitor
 */

public class XmlDocument {
    ElementSequence elements;
    static String NL = System.getProperty( "line.separator" );

    private static final String HTML_HEAD = "<head>" +
            "<link rel=STYLESHEET TYPE=\"text/css\" HREF=\"" +
            HtmlDocument.class.getResource( "../style.css" ) +
            "\"></head>";

    public XmlDocument( ElementSequence s ) {
        elements = s;
    }

    public void setLineSeparator( String ls ) {
        NL = ls;
    }


    public void accept( XmlVisitor v ) {
        if (v != null)
            v.visit( this );
    }

    private static String dequote( String s ) {
        if ( s == null )
            return "";
        if ( ( s.startsWith( "\"" ) && s.endsWith( "\"" ) ) || ( s.startsWith( "'" ) && s.endsWith( "'" ) ) )
            return s.substring( 1, s.length() - 1 );
        else
            return s;
    }

    // The various elements of the XmlDocument (Tag, EndTag, etc) are included
    // as nested subclasses largely for reasons of namespace control.
    // The following subclasses of XmlElement exist: Tag, EndTag, Text, Comment,
    // Newline, Annotation, TagBlock.  Also, the additional classes
    // ElementSequence, Attribute, and AttributeList are defined here as well.

    // Each subclass of XmlElement should have a visit() method in the
    // XmlVisitor class.

    /**
     * Abstract class for HTML elements.  Enforces support for Visitors.
     * danson: added support for Location and Position.  Javacc gives Location,
     * jEdit needs Position.
     */
    public static abstract class XmlElement implements SideKickElement {
        private Location startLocation = new Location( 0, 0 );
        private Location endLocation = new Location( 0, 0 );
        private Position startPosition = null;
        private Position endPosition = null;

        public abstract void accept( XmlVisitor v );

        public void setStartLocation( int line, int column ) {
            startLocation = new Location( line, column );
        }

        public void setStartLocation( Location location ) {
            startLocation = location;
        }

        public Location getStartLocation() {
            return startLocation;
        }

        public void setEndLocation( int line, int column ) {
            endLocation = new Location( line, column );
        }

        public void setEndLocation( Location location ) {
            endLocation = location;
        }

        public Location getEndLocation() {
            return endLocation;
        }
        
        public void setStartPosition(Position s) {
            startPosition = s;   
        }
        
        public Position getStartPosition() {
            return startPosition;   
        }
        
        public void setEndPosition(Position s) {
            endPosition = s;   
        }
        
        public Position getEndPosition() {
            return endPosition;   
        }
        
        public boolean equals( Object o ) {
            if ( o == null )
                return false;
            if ( ! ( o instanceof XmlElement ) ) {
                return false;
            }
            XmlElement he = ( XmlElement ) o;
            String a = he.toString();
            String b = this.toString();
            if ( a == null && b != null )
                return false;
            if ( a != null && b == null )
                return false;
            if ( a == null && b == null &&
                    he.getStartLocation().equals( this.getStartLocation() ) &&
                    he.getEndLocation().equals( this.getEndLocation() ) ) {
                return true;
            }

            if ( a.equals( b ) &&
                    he.getStartLocation().equals( this.getStartLocation() ) &&
                    he.getEndLocation().equals( this.getEndLocation() ) ) {
                return true;
            }
            return false;
        }

        public String toLongString() {
            return toString();
        }
    }

    /**
     * XML start tag.  Stores the tag name and a list of tag attributes.
     */
    public static class Tag extends XmlElement {
        public String tagStart = "<";
        public String tagName;
        public AttributeList attributeList;
        public String tagEnd = ">";
        public boolean emptyTag = false;
    	public String namespace;
    	/** namespace -> prefix */
    	public NamespaceBindings namespaceBindings;

        
        public Tag( String name, AttributeList a ) {
            tagName = name;
            attributeList = a;
            // keep the attributes in document order
            //sortAttributes();
        }

        public Tag( String tagStart, String name, AttributeList a, String tagEnd ) {
            this.tagStart = tagStart;
            tagName = name;
            attributeList = a;
            this.tagEnd = tagEnd;
            // keep the attributes in document order
            //sortAttributes();
        }

        public void sortAttributes() {
            Collections.sort(attributeList.attributes, new Comparator() {
                    public int compare(Object a, Object b) {
                        return ((Attribute)a).name.compareTo(((Attribute)b).name);   
                    }
            });
            
        }
        
        public void setEmpty( boolean b ) {
            emptyTag = b;
        }

        public void accept( XmlVisitor v ) {
            v.visit( this );
        }

        public boolean hasAttribute( String name ) {
            return attributeList.contains( name );
        }

        public boolean hasAttributeValue( String name ) {
            return attributeList.hasValue( name );
        }

        public String getAttributeValue( String name ) {
            return attributeList.getValue( name );
        }

        public int getLength() {
            int length = 0;
            for ( Iterator iterator = attributeList.attributes.iterator(); iterator.hasNext(); ) {
                Attribute attribute = ( Attribute ) iterator.next();
                length += 1 + ( attribute.getLength() );
            }
            return length + tagName.length() + ( emptyTag ? 1 : 0 );
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append( tagName );
            for ( Iterator iterator = attributeList.attributes.iterator(); iterator.hasNext(); ) {
                Attribute attribute = ( Attribute ) iterator.next();
                s.append( " " );
                s.append( attribute.toString() );
            }
            return s.toString();
        }

        public String toLongString() {
            StringBuffer s = new StringBuffer();
            if ( tagName.equals( "html" ) ) {
                /* deal with a Swing thing -- I can't add "<html>" directly
                because Swing thinks it's the start of html formatted text */
                s.append( "<html><b>html" );
                return s.toString();
            }
            
            // really do want html formatting for the following
            s.append( "<html>").append(HTML_HEAD).append("<body><b>" );     
            if ( tagStart.length() > 1 && !tagStart.endsWith( ":" ) ) {
                s.append( tagStart.substring( 1 ) );
                s.append( " " );  // got a jsp tag
            }
            s.append( tagName );
            s.append( "</b><br><table>" );
            for ( Iterator iterator = attributeList.attributes.iterator(); iterator.hasNext(); ) {
                Attribute attribute = ( Attribute ) iterator.next();
                s.append( "<tr><td>" ).append( attribute.name ).append( "</td><td>" ).append( attribute.value ).append( "</td></tr>" );
            }
            s.append( "</table></body></html>" );
            return s.toString();
        }
    }

    /**
     * Html end tag.  Stores only the tag name.  These are not displayed in
     * HtmlSideKick.
     */
    public static class EndTag extends XmlElement {
        public String tagName;

        public EndTag( String t ) {
            tagName = t;
        }

        public void accept( XmlVisitor v ) {
            v.visit( this );
        }

        public int getLength() {
            return 3 + tagName.length();
        }

        public String toString() {
            return "</" + tagName + ">";
        }
    }

    /**
     * A tag block is a composite structure consisting of a start tag
     * a sequence of HTML elements, and a matching end tag.
     */
    public static class TagBlock extends XmlElement {
        public Tag startTag;
        public EndTag endTag;
        public ElementSequence body;

        public TagBlock( Tag t, ElementSequence b, EndTag et ) {
            startTag = t;
            endTag = et;
            body = b;
        }

        public TagBlock( String name, AttributeList aList, ElementSequence b ) {
            startTag = new Tag( name, aList );
            endTag = new EndTag( name );
            body = b;
        }

        public void accept( XmlVisitor v ) {
            v.visit( this );
        }

        public String toString() {
            return startTag.toString();
        }

        public String toLongString() {
            return startTag.toLongString();
        }
    }

    /**
     * HTML comments.  These are not displayed in HtmlSideKick -- at least, not
     * currently.  It might be an option later on.
     */
    public static class Comment extends XmlElement {
        public String comment;

        public Comment( String c ) {
            comment = c;
        }

        public void accept( XmlVisitor v ) {
            v.visit( this );
        }

        public int getLength() {
            return 3 + comment.length();
        }

        public String toString() {
            return comment;
        }
    }

    /**
     * Plain text.  These are not displayed in HtmlSideKick -- at least, not
     * currently.  It might be an option later on.
     */
    public static class Text extends XmlElement {
        public String text;

        public Text( String t ) {
            text = t;
        }

        public void accept( XmlVisitor v ) {
            v.visit( this );
        }

        public int getLength() {
            return text.length();
        }

        public String toString() {
            return text;
        }
    }

    /**
     * End of line indicator.
     */
    public static class Newline extends XmlElement {

        public void accept( XmlVisitor v ) {
            v.visit( this );
        }

        public int getLength() {
            return NL.length();
        }

        public String toString() {
            return NL;
        }
    }

    /**
     * A sequence of HTML elements.
     */
    public static class ElementSequence {
        private List elements;

        public ElementSequence( int n ) {
            elements = new ArrayList( n );
        }

        public ElementSequence() {
            elements = new ArrayList();
        }

        public void addElement( XmlElement o ) {
            elements.add( o );
        }

        public int size() {
            return elements.size();
        }

        public Iterator iterator() {
            return elements.iterator();
        }
        
        public Object getElementAt(int i) {
            if (elements != null && elements.size() > 0) {
                return elements.get(i);   
            }
            return null;
        }

        public void setElements( List coll ) {
            elements.clear();
            elements.addAll( coll );
        }
    }

    /**
     * Annotations.  These are not part of the HTML document, but
     * provide a way for HTML-processing applications to insert
     * annotations into the document.  These annotations can be used by
     * other programs or can be brought to the user's attention at a
     * later time.  For example, the HtmlCollector might insert an
     * annotation to indicate that there is no corresponding start tag
     * for an end tag.
     */
    public static class Annotation extends XmlElement {
        String type, text;

        public Annotation( String type, String text ) {
            this.type = type;
            this.text = text;
        }

        public void accept( XmlVisitor v ) {
            v.visit( this );
        }

        public int getLength() {
            return 14 + type.length() + text.length();
        }

        public String toString() {
            return "<!--NOTE(" + type + ") " + text + "-->";
        }
    }

    public static class Attribute extends XmlElement {
        public String name, value;
        public boolean hasValue;
        private Location valueStartLocation = new Location( 0, 0 );

        public Attribute( String n ) {
            name = n;
            hasValue = false;
        }

        public Attribute( String n, String v ) {
            name = n;
            value = v;
            hasValue = true;
        }

        /**
         * @return name, may be empty, won't be null
         */
        public String getName() {
            return name == null ? "" : name;
        }

        /**
         * @return value, may be empty, won't be null        
         */
        public String getValue() {
            return value == null ? "" : value;
        }

        public int getLength() {
            return ( hasValue ? name.length() + 1 + value.length() : name.length() );
        }

        public String toString() {
            return ( hasValue ? name + "=" + value : name );
        }
        
        public void setValueStartLocation( int line, int column ) {
            valueStartLocation = new Location( line, column );
        }

        public void setValueStartLocation( Location location ) {
            valueStartLocation = location;
        }
        
        public Location getValueStartLocation() {
        	return valueStartLocation;
        }

        public void accept(XmlVisitor visitor){ /*NOP*/ }
    }

    public static class AttributeList {
        public List<Attribute> attributes = new ArrayList<Attribute>();

        public void addAttribute( Attribute a ) {
            attributes.add( a );
        }

        public boolean contains( String name ) {
            for ( Iterator<Attribute> iterator = attributes.iterator(); iterator.hasNext(); ) {
                Attribute attribute = iterator.next();
                if ( attribute.name.equalsIgnoreCase( name ) )
                    return true;
            }
            return false;
        }

        public boolean hasValue( String name ) {
            for ( Iterator<Attribute> iterator = attributes.iterator(); iterator.hasNext(); ) {
                Attribute attribute = iterator.next();
                if ( attribute.name.equalsIgnoreCase( name ) && attribute.hasValue )
                    return true;
            }
            return false;
        }

        public String getValue( String name ) {
            for ( Iterator<Attribute> iterator = attributes.iterator(); iterator.hasNext(); ) {
                Attribute attribute = iterator.next();
                if ( attribute.name.equalsIgnoreCase( name ) && attribute.hasValue )
                    return dequote( attribute.value );
            }
            return null;
        }
    }

    private static String trimBrackets( CharSequence cs ) {
        String s = cs.toString();
        if ( s.startsWith( "<" ) ) {
            s = s.substring( 1 );
        }
        if ( s.endsWith( ">" ) ) {
            s = s.substring( 0, s.length() - 1 );
        }
        return s;
    }
}
