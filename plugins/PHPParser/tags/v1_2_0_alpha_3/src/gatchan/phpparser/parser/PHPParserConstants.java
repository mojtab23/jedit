/* Generated By:JavaCC: Do not edit this line. PHPParserConstants.java */
package gatchan.phpparser.parser;

public interface PHPParserConstants {

  int EOF = 0;
  int PHPSTARTSHORT = 1;
  int PHPSTARTLONG = 2;
  int PHPECHOSTART = 3;
  int PHPEND = 4;
  int SINGLE_LINE_COMMENT = 20;
  int CLASS = 25;
  int FUNCTION = 26;
  int VAR = 27;
  int IF = 28;
  int ELSEIF = 29;
  int ELSE = 30;
  int ARRAY = 31;
  int BREAK = 32;
  int LIST = 33;
  int PRINT = 34;
  int ECHO = 35;
  int INCLUDE = 36;
  int REQUIRE = 37;
  int INCLUDE_ONCE = 38;
  int REQUIRE_ONCE = 39;
  int GLOBAL = 40;
  int STATIC = 41;
  int CASE = 42;
  int CONST = 43;
  int CONTINUE = 44;
  int _DEFAULT = 45;
  int DO = 46;
  int EXTENDS = 47;
  int FOR = 48;
  int NEW = 49;
  int RETURN = 50;
  int SWITCH = 51;
  int WHILE = 52;
  int ENDWHILE = 53;
  int ENDSWITCH = 54;
  int ENDIF = 55;
  int ENDFOR = 56;
  int ENDFOREACH = 57;
  int FOREACH = 58;
  int AS = 59;
  int CLASSACCESS = 60;
  int STATICCLASSACCESS = 61;
  int ARRAYASSIGN = 62;
  int DEFINE = 63;
  int GOTO = 64;
  int NULL = 65;
  int SUPER = 66;
  int THIS = 67;
  int TRUE = 68;
  int FALSE = 69;
  int STRING = 70;
  int OBJECT = 71;
  int BOOL = 72;
  int BOOLEAN = 73;
  int REAL = 74;
  int DOUBLE = 75;
  int FLOAT = 76;
  int INT = 77;
  int INTEGER = 78;
  int AT = 79;
  int BANG = 80;
  int TILDE = 81;
  int HOOK = 82;
  int COLON = 83;
  int OR_OR = 84;
  int AND_AND = 85;
  int PLUS_PLUS = 86;
  int MINUS_MINUS = 87;
  int PLUS = 88;
  int MINUS = 89;
  int STAR = 90;
  int SLASH = 91;
  int BIT_AND = 92;
  int BIT_OR = 93;
  int BIT_XOR = 94;
  int REMAINDER = 95;
  int LSHIFT = 96;
  int RSIGNEDSHIFT = 97;
  int RUNSIGNEDSHIFT = 98;
  int _ORL = 99;
  int XOR = 100;
  int _ANDL = 101;
  int INTEGER_LITERAL = 102;
  int DECIMAL_LITERAL = 103;
  int HEX_LITERAL = 104;
  int OCTAL_LITERAL = 105;
  int FLOATING_POINT_LITERAL = 106;
  int EXPONENT = 107;
  int STRING_LITERAL = 108;
  int STRING_1 = 109;
  int STRING_2 = 110;
  int STRING_3 = 111;
  int HEREDOCSTARTTOKEN = 112;
  int DOLLAR = 113;
  int IDENTIFIER = 114;
  int LETTER = 115;
  int DIGIT = 116;
  int SPECIAL = 117;
  int LPAREN = 118;
  int RPAREN = 119;
  int LBRACE = 120;
  int RBRACE = 121;
  int LBRACKET = 122;
  int RBRACKET = 123;
  int SEMICOLON = 124;
  int COMMA = 125;
  int DOT = 126;
  int GT = 127;
  int LT = 128;
  int EQUAL_EQUAL = 129;
  int LE = 130;
  int GE = 131;
  int NOT_EQUAL = 132;
  int DIF = 133;
  int BANGDOUBLEEQUAL = 134;
  int TRIPLEEQUAL = 135;
  int ASSIGN = 136;
  int PLUSASSIGN = 137;
  int MINUSASSIGN = 138;
  int STARASSIGN = 139;
  int SLASHASSIGN = 140;
  int ANDASSIGN = 141;
  int ORASSIGN = 142;
  int XORASSIGN = 143;
  int DOTASSIGN = 144;
  int REMASSIGN = 145;
  int TILDEEQUAL = 146;
  int LSHIFTASSIGN = 147;
  int RSIGNEDSHIFTASSIGN = 148;

  int DEFAULT = 0;
  int PHPPARSING = 1;
  int IN_SINGLE_LINE_COMMENT = 2;
  int IN_VARIABLE = 3;
  int IN_FORMAL_COMMENT = 4;
  int IN_MULTI_LINE_COMMENT = 5;

  String[] tokenImage = {
    "<EOF>",
    "\"<?\"",
    "\"<?php\"",
    "\"<?=\"",
    "\"?>\"",
    "<token of kind 5>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\"//\"",
    "\"#\"",
    "<token of kind 18>",
    "\"/*\"",
    "<SINGLE_LINE_COMMENT>",
    "<token of kind 21>",
    "\"*/\"",
    "\"*/\"",
    "<token of kind 24>",
    "\"class\"",
    "\"function\"",
    "\"var\"",
    "\"if\"",
    "\"elseif\"",
    "\"else\"",
    "\"array\"",
    "\"break\"",
    "\"list\"",
    "\"print\"",
    "\"echo\"",
    "\"include\"",
    "\"require\"",
    "\"include_once\"",
    "\"require_once\"",
    "\"global\"",
    "\"static\"",
    "\"case\"",
    "\"const\"",
    "\"continue\"",
    "\"default\"",
    "\"do\"",
    "\"extends\"",
    "\"for\"",
    "\"new\"",
    "\"return\"",
    "\"switch\"",
    "\"while\"",
    "\"endwhile\"",
    "\"endswitch\"",
    "\"endif\"",
    "\"endfor\"",
    "\"endforeach\"",
    "\"foreach\"",
    "\"as\"",
    "\"->\"",
    "\"::\"",
    "\"=>\"",
    "\"define\"",
    "\"goto\"",
    "\"null\"",
    "\"super\"",
    "\"this\"",
    "\"true\"",
    "\"false\"",
    "\"string\"",
    "\"object\"",
    "\"bool\"",
    "\"boolean\"",
    "\"real\"",
    "\"double\"",
    "\"float\"",
    "\"int\"",
    "\"integer\"",
    "\"@\"",
    "\"!\"",
    "\"~\"",
    "\"?\"",
    "\":\"",
    "\"||\"",
    "\"&&\"",
    "\"++\"",
    "\"--\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"&\"",
    "\"|\"",
    "\"^\"",
    "\"%\"",
    "\"<<\"",
    "\">>\"",
    "\">>>\"",
    "\"OR\"",
    "\"XOR\"",
    "\"AND\"",
    "<INTEGER_LITERAL>",
    "<DECIMAL_LITERAL>",
    "<HEX_LITERAL>",
    "<OCTAL_LITERAL>",
    "<FLOATING_POINT_LITERAL>",
    "<EXPONENT>",
    "<STRING_LITERAL>",
    "<STRING_1>",
    "<STRING_2>",
    "<STRING_3>",
    "\"<<<\"",
    "\"$\"",
    "<IDENTIFIER>",
    "<LETTER>",
    "<DIGIT>",
    "<SPECIAL>",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\";\"",
    "\",\"",
    "\".\"",
    "\">\"",
    "\"<\"",
    "\"==\"",
    "\"<=\"",
    "\">=\"",
    "\"!=\"",
    "\"<>\"",
    "\"!==\"",
    "\"===\"",
    "\"=\"",
    "\"+=\"",
    "\"-=\"",
    "\"*=\"",
    "\"/=\"",
    "\"&=\"",
    "\"|=\"",
    "\"^=\"",
    "\".=\"",
    "\"%=\"",
    "\"~=\"",
    "\"<<=\"",
    "\">>=\"",
  };

}
