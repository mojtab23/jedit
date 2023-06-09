/* Generated By:JavaCC: Do not edit this line. XmlParserConstants.java */
package xml.parser.javacc;

public interface XmlParserConstants {

  int EOF = 0;
  int ALPHA_CHAR = 1;
  int NUM_CHAR = 2;
  int ALPHANUM_CHAR = 3;
  int IDENTIFIER_CHAR = 4;
  int IDENTIFIER = 5;
  int QUOTED_STRING_NB = 6;
  int QUOTED_STRING = 7;
  int WHITESPACE = 8;
  int NEWLINE = 9;
  int QUOTE = 10;
  int EOL = 11;
  int COMMENT_START = 12;
  int ENDTAG_START = 13;
  int TAG_START = 14;
  int DECL_START = 15;
  int PCDATA = 16;
  int TAG_NAME = 18;
  int LST_ERROR = 19;
  int ATTR_NAME = 21;
  int TAG_END = 22;
  int TAG_SLASHEND = 23;
  int ATTR_EQ = 24;
  int IMPLICIT_TAG_END = 25;
  int LIT_ERROR = 26;
  int ATTR_VAL = 28;
  int LAV_ERROR = 29;
  int COMMENT_END = 30;
  int DASH = 31;
  int COMMENT_EOL = 32;
  int COMMENT_WORD = 33;
  int DECL_ANY = 34;
  int DECL_END = 35;

  int LexDecl = 0;
  int LexComment = 1;
  int LexAttrVal = 2;
  int LexInTag = 3;
  int LexStartTag = 4;
  int DEFAULT = 5;

  String[] tokenImage = {
    "<EOF>",
    "<ALPHA_CHAR>",
    "<NUM_CHAR>",
    "<ALPHANUM_CHAR>",
    "<IDENTIFIER_CHAR>",
    "<IDENTIFIER>",
    "<QUOTED_STRING_NB>",
    "<QUOTED_STRING>",
    "<WHITESPACE>",
    "<NEWLINE>",
    "<QUOTE>",
    "<EOL>",
    "<COMMENT_START>",
    "<ENDTAG_START>",
    "<TAG_START>",
    "\"<!\"",
    "<PCDATA>",
    "<token of kind 17>",
    "<TAG_NAME>",
    "<LST_ERROR>",
    "<token of kind 20>",
    "<ATTR_NAME>",
    "\">\"",
    "\"/>\"",
    "\"=\"",
    "\"<\"",
    "<LIT_ERROR>",
    "<token of kind 27>",
    "<ATTR_VAL>",
    "<LAV_ERROR>",
    "<COMMENT_END>",
    "\"-\"",
    "<COMMENT_EOL>",
    "<COMMENT_WORD>",
    "<DECL_ANY>",
    "\">\"",
  };

}
