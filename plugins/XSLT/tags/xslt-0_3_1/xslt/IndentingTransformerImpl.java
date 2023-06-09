/*
 *  IndentingTransformerImpl.java - Indents XML elements, by adding whitespace where appropriate.
 *
 *  Copyright (C) 2002, 2003 Robert McKinnon
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 *  email: robmckinnon@users.sourceforge.net
 */

package xslt;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;

/**
 * Indents elements, by adding whitespace where appropriate.
 * Does not remove blank lines between nodes.
 * Does not remove new lines within text nodes.
 * Puts element tags immediately following mixed content text on the same line as the text.
 *
 * @author Robert McKinnon - robmckinnon@users.sourceforge.net
 */
public class IndentingTransformerImpl extends IndentingTransformer {

  /** indent by this many spaces */
  private int indentAmount = 2;

  /** indent with tabs instead of spaces */
  private char indentChar;

  /** current indentation level */
  private int indentLevel = 0;

  /** true if no newlines in element */
  private boolean isSameLine = false;

  /** true if last item was non-whitespace text */
  private boolean isLastText = false;

  /** true if there is a non-whitespace text item, followed by an element start */
  private boolean isMixedContent = false;

  /** buffer to hold character data */
  private StringBuffer buffer = new StringBuffer();

  private char[] newLine = {'\n'};


  public IndentingTransformerImpl(int indentAmount, boolean indentWithTabs) {
    this.indentAmount = indentAmount;

    if(indentWithTabs) {
      this.indentChar = '\t';
    } else {
      this.indentChar = ' ';
    }
  }


  public Transformer getTransformer() {
    return null;
  }


  public void startElement(String uri, String localName, String qualifiedName, Attributes attributes) throws SAXException {
    flush();

    if(isLastText && !isMixedContent) {
      isMixedContent = true;
    }

    if(!isMixedContent) {
      indent();
    }

    super.startElement(uri, localName, qualifiedName, attributes);

    indentLevel++;

    isSameLine = true; // assume a single line of content
  }


  public void endElement(String uri, String localName, String qualifiedName) throws SAXException {
    flush();
    indentLevel--;

    if(!isMixedContent && !isSameLine && !isLastText) {
      indent();
    }

    super.endElement(uri, localName, qualifiedName);
    isLastText = false;
    isSameLine = false;
    isMixedContent = false;
  }


  public void processingInstruction(String target, String data) throws SAXException {
    flush();
    indent();
    super.processingInstruction(target, data);
  }


  public void characters(char[] chars, int start, int length) throws SAXException {
    for(int i = start; i < start + length; i++) {
      if(!Character.isWhitespace(chars[i])) {
        isLastText = true;
      }
    }

    buffer.append(chars, start, length);
  }


  public void comment(char[] chars, int start, int len) throws SAXException {
    flush();
    super.characters(newLine, 0, 1);
    super.comment(chars, start, len);
  }


  /**
   * Output white space to reflect the current indentation level
   */
  private void indent() throws SAXException {
    char[] indent = new char[indentLevel * indentAmount + 1];
    indent[0] = '\n';

    for(int i = 1; i < indent.length; i++) {
      indent[i] = indentChar;
    }

    super.characters(indent, 0, indent.length);
  }


  /**
   * Flush the buffer containing accumulated character data.
   * White space adjacent to markup is trimmed.
   */
  public void flush() throws SAXException {
    int end = buffer.length();
    int start = 0;

    if(end != 0) {
      char[] array = new char[end];
      buffer.getChars(0, end, array, 0);

      if(!isLastText) {

        boolean stripNewLineFromStart = true;

        while(start < end && Character.isWhitespace(array[start])) {
          if(Character.isSpaceChar(array[start])) {
            start++;
          } else if(stripNewLineFromStart) {
            start++;
            stripNewLineFromStart = false;
          } else {
            break;
          }
        }

        if(start < end && Character.isWhitespace(array[end - 1])) {

          while(start < end && Character.isWhitespace(array[end - 1])) {
            if(Character.isSpaceChar(array[end - 1])) {
              end--;
            } else {
              break;
            }
          }
        }

        for(int i = start; i < end; i++) {
          if(array[i] == '\n') {
            isSameLine = false;
            break;
          }
        }
      }

      super.characters(array, start, end - start);
      buffer.setLength(0);
    }

  }


  public void setDocumentLocator(Locator locator) {
  }


  public void startCDATA() throws SAXException {
    flush();
    indent();
  }


  public void notationDecl(String name, String publicId, String systemId) throws SAXException {
  }


  public void setSystemId(String systemID) {
  }


  public void startDocument() throws SAXException {
  }


  public void endCDATA() throws SAXException {
  }


  public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
  }


  public String getSystemId() {
    return null;
  }


  public void endDocument() throws SAXException {
  }


  public void startPrefixMapping(String prefix, String uri) throws SAXException {
  }


  public void endPrefixMapping(String prefix) throws SAXException {
  }


  public void skippedEntity(String name) throws SAXException {
  }


  public void setResult(Result result) throws IllegalArgumentException {
  }


  public void ignorableWhitespace(char[] chars, int i, int i1) throws SAXException {
  }


  public void endDTD() throws SAXException {
  }


  public void attributeDecl(String s, String s1, String s2, String s3, String s4) throws SAXException {
  }


  public void endEntity(String s) throws SAXException {
  }


  public void elementDecl(String s, String s1) throws SAXException {
  }


  public void startDTD(String s, String s1, String s2) throws SAXException {
  }


  public void externalEntityDecl(String s, String s1, String s2) throws SAXException {
  }


  public void startEntity(String s) throws SAXException {
  }


  public void internalEntityDecl(String s, String s1) throws SAXException {
  }

}
