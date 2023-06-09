/* Generated By:JavaCC: Do not edit this line. HtmlParser.java */
package beauty.parsers.html;

public class HtmlParser implements HtmlParserConstants {

  final static String NL = System.getProperty("line.separator");

  private static String getTokenText(Token first, Token cur) {
    Token t;
    StringBuffer sb = new StringBuffer();

    for (t=first; t != cur.next; t = t.next) {
      if (t.specialToken != null) {
        Token tt=t.specialToken;
        while (tt.specialToken != null)
          tt = tt.specialToken;
        for (; tt != null; tt = tt.next)
          sb.append(tt.image);
      }
      sb.append(t.image);
    }
    return sb.toString();
  }

  public static void main(String[] args) throws ParseException {
      if (args.length == 0) {
        return;
      }
      try {
        String filename = args[0];
        HtmlParser parser = new HtmlParser(new java.io.FileReader(filename));
        HtmlDocument document = parser.HtmlDocument();
        //doc.accept(new HtmlDumper(System.out));

            document.setLineSeparator("\n");
            document.accept(new HtmlCollector());
            document.accept(new HtmlScrubber(HtmlScrubber.DEFAULT_OPTIONS | HtmlScrubber.TRIM_SPACES));
            HtmlFormatter formatter = new HtmlFormatter();
            formatter.setRightMargin(1024);
            formatter.setLineSeparator("\n");
            formatter.setIndent(3);
            document.accept(formatter);
            System.out.println(formatter.toString());
      }
      catch(Exception e) {
          e.printStackTrace();
      }
    System.exit(0);
  }

  final public HtmlDocument HtmlDocument() throws ParseException {
  HtmlDocument.ElementSequence s;
    s = ElementSequence();
    jj_consume_token(0);
    {if (true) return new HtmlDocument(s);}
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.ElementSequence ElementSequence() throws ParseException {
  HtmlDocument.ElementSequence s = new HtmlDocument.ElementSequence();
  HtmlDocument.HtmlElement h;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EOL:
      case TAG_START:
      case ENDTAG_START:
      case COMMENT_START:
      case DECL_START:
      case PCDATA:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      h = Element();
                  s.addElement(h);
    }
    {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.HtmlElement Element() throws ParseException {
  HtmlDocument.HtmlElement e;
  Token text;
    if (jj_2_1(2)) {
      e = Tag();
                            {if (true) return e;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDTAG_START:
        e = EndTag();
                            {if (true) return e;}
        break;
      case COMMENT_START:
        e = CommentTag();
                            {if (true) return e;}
        break;
      case DECL_START:
        e = DeclTag();
                            {if (true) return e;}
        break;
      default:
        jj_la1[1] = jj_gen;
        if (jj_2_2(2)) {
          e = ScriptBlock();
                             {if (true) return e;}
        } else if (jj_2_3(2)) {
          e = StyleBlock();
                             {if (true) return e;}
        } else if (jj_2_4(2)) {
          jj_consume_token(TAG_START);
          text = jj_consume_token(LST_ERROR);
                            {if (true) return new HtmlDocument.Text("<" + text.image);}
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case PCDATA:
            text = jj_consume_token(PCDATA);
                            {if (true) return new HtmlDocument.Text(text.image);}
            break;
          case EOL:
            jj_consume_token(EOL);
                            {if (true) return new HtmlDocument.Newline();}
            break;
          default:
            jj_la1[2] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.Attribute Attribute() throws ParseException {
  HtmlDocument.Attribute a;
  Token t;
  String value=null;
    //t1=<ATTR_NAME> [ <ATTR_EQ> t2=<ATTR_VAL> ]
          t = jj_consume_token(ATTR_NAME);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ATTR_EQ:
      jj_consume_token(ATTR_EQ);
      value = AttributeValue();
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
        if (value == null)
          {if (true) return new HtmlDocument.Attribute(t.image);}
        else
          {if (true) return new HtmlDocument.Attribute(t.image, value);}
    throw new Error("Missing return statement in function");
  }

  final public String AttributeValue() throws ParseException {
    StringBuffer content = new StringBuffer();
    Token t = null;
    HtmlDocument.HtmlElement inner_tag = null;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOUBLE_QUOTE:
        jj_consume_token(DOUBLE_QUOTE);
        label_2:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case UNPARSED_TEXT_NO_DOUBLE_QUOTES:
          case EL_EXPRESSION_IN_ATTRIBUTE:
          case VALUE_BINDING_IN_ATTRIBUTE:
          case JSP_EXPRESSION_IN_ATTRIBUTE:
          case JSP_TAG_IN_ATTRIBUTE:
            ;
            break;
          default:
            jj_la1[4] = jj_gen;
            break label_2;
          }
          t = QuoteIndependentAttributeValueContent();
                        if (t != null) {
                            content.append(t.image);
                        }
                        else if (inner_tag != null) {
                            content.append(inner_tag.toString());
                        }
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ENDING_DOUBLE_QUOTE:
          jj_consume_token(ENDING_DOUBLE_QUOTE);
          break;
        case DOLLAR_OR_HASH_DOUBLE_QUOTE:
          t = jj_consume_token(DOLLAR_OR_HASH_DOUBLE_QUOTE);
                                                            content.append(t.image.substring(0, 1));
          break;
        default:
          jj_la1[5] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ATTR_VAL:
          t = jj_consume_token(ATTR_VAL);
                                 content.append( t.image );
          break;
        default:
          jj_la1[6] = jj_gen;
          ;
        }
        break;
      case SINGLE_QUOTE:
        jj_consume_token(SINGLE_QUOTE);
        label_3:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case UNPARSED_TEXT_NO_SINGLE_QUOTES:
          case UNPARSED_TEXT_NO_DOUBLE_QUOTES:
          case EL_EXPRESSION_IN_ATTRIBUTE:
          case VALUE_BINDING_IN_ATTRIBUTE:
          case JSP_EXPRESSION_IN_ATTRIBUTE:
          case JSP_TAG_IN_ATTRIBUTE:
            ;
            break;
          default:
            jj_la1[7] = jj_gen;
            break label_3;
          }
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case UNPARSED_TEXT_NO_SINGLE_QUOTES:
            t = jj_consume_token(UNPARSED_TEXT_NO_SINGLE_QUOTES);
            break;
          case UNPARSED_TEXT_NO_DOUBLE_QUOTES:
          case EL_EXPRESSION_IN_ATTRIBUTE:
          case VALUE_BINDING_IN_ATTRIBUTE:
          case JSP_EXPRESSION_IN_ATTRIBUTE:
          case JSP_TAG_IN_ATTRIBUTE:
            t = QuoteIndependentAttributeValueContent();
            break;
          default:
            jj_la1[8] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
                        content.append(t.image);
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ENDING_SINGLE_QUOTE:
          jj_consume_token(ENDING_SINGLE_QUOTE);
          break;
        case DOLLAR_OR_HASH_SINGLE_QUOTE:
          t = jj_consume_token(DOLLAR_OR_HASH_SINGLE_QUOTE);
                                                         content.append(t.image.substring(0, 1));
          break;
        default:
          jj_la1[9] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
            {if (true) return content.toString();}
    } catch (Exception e) {
        e.printStackTrace();
    }
    throw new Error("Missing return statement in function");
  }

/**
 * Partial content of an attribute value that can contain all quotes.
 * This groups EL expressions, value bindings, and JSP expressions.
 */
  final public Token QuoteIndependentAttributeValueContent() throws ParseException {
        Token t;
    try {
      if (jj_2_5(2)) {
        t = jj_consume_token(EL_EXPRESSION_IN_ATTRIBUTE);
      } else if (jj_2_6(2)) {
        t = jj_consume_token(VALUE_BINDING_IN_ATTRIBUTE);
      } else if (jj_2_7(2)) {
        t = jj_consume_token(JSP_EXPRESSION_IN_ATTRIBUTE);
      } else if (jj_2_8(2)) {
        t = jj_consume_token(JSP_TAG_IN_ATTRIBUTE);
      } else if (jj_2_9(2)) {
        t = jj_consume_token(UNPARSED_TEXT_NO_DOUBLE_QUOTES);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
          {if (true) return t;}
    } catch (Exception e) {
        e.printStackTrace();
    }
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.AttributeList AttributeList() throws ParseException {
  HtmlDocument.AttributeList alist = new HtmlDocument.AttributeList();
  HtmlDocument.Attribute a;
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATTR_NAME:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_4;
      }
      a = Attribute();
                      alist.addAttribute(a);
    }
    {if (true) return alist;}
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.HtmlElement Tag() throws ParseException {
  Token t, et;
  HtmlDocument.AttributeList alist = null;
  Token firstToken = getToken(1);
  HtmlDocument.HtmlElement rtn_tag = null;
  Token st = null;
  String tag_name;
  String tag_start;
    try {
      st = jj_consume_token(TAG_START);
      t = jj_consume_token(TAG_NAME);
      alist = AttributeList();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TAG_END:
        et = jj_consume_token(TAG_END);
        break;
      case TAG_PERCENTEND:
        et = jj_consume_token(TAG_PERCENTEND);
        break;
      case TAG_SLASHEND:
        et = jj_consume_token(TAG_SLASHEND);
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
        HtmlDocument.Tag tag = new HtmlDocument.Tag(st.image, t.image, alist, et.image);
        if (st.image.startsWith("<%") || t.image.indexOf(":") > 0) {
            tag.setIsJspTag(true);
        }
        if (et.kind == TAG_SLASHEND) {
          tag.setEmpty(true);
        }
        rtn_tag = tag;
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    token_source.SwitchTo(DEFAULT);
    String s = getTokenText(firstToken, getNextToken());
    HtmlDocument.Text tag = new HtmlDocument.Text(s);
    rtn_tag = tag;
    } finally {
    {if (true) return rtn_tag;}
    }
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.ElementSequence BlockContents() throws ParseException {
  Token t;
  StringBuffer s = new StringBuffer();
  HtmlDocument.ElementSequence e = new HtmlDocument.ElementSequence();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BLOCK_EOL:
      case BLOCK_LBR:
      case BLOCK_WORD:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BLOCK_EOL:
        jj_consume_token(BLOCK_EOL);
      if (s.length() > 0) {
        e.addElement(new HtmlDocument.Text(s.toString()));
        s.setLength(0);
      };
      e.addElement(new HtmlDocument.Newline());
        break;
      case BLOCK_WORD:
        t = jj_consume_token(BLOCK_WORD);
                       s.append(t.image);
        break;
      case BLOCK_LBR:
        t = jj_consume_token(BLOCK_LBR);
                       s.append(t.image);
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    if (s.length() > 0)
      e.addElement(new HtmlDocument.Text(s.toString()));
    // danson, removed next line, it causes an extra blank line to be inserted
    // in script and style blocks
    //e.addElement(new HtmlDocument.Newline());
    {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.HtmlElement ScriptBlock() throws ParseException {
  HtmlDocument.AttributeList alist;
  HtmlDocument.ElementSequence e;
  Token firstToken = getToken(1);
    try {
      jj_consume_token(TAG_START);
      jj_consume_token(TAG_SCRIPT);
      alist = AttributeList();
      jj_consume_token(TAG_END);
      token_source.SwitchTo(LexScript);
      e = BlockContents();
      jj_consume_token(SCRIPT_END);
      {if (true) return new HtmlDocument.TagBlock("SCRIPT", alist, e);}
    } catch (ParseException ex) {
    token_source.SwitchTo(DEFAULT);
    String s = getTokenText(firstToken, getNextToken());
    {if (true) return new HtmlDocument.Text(s);}
    }
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.HtmlElement StyleBlock() throws ParseException {
  HtmlDocument.AttributeList alist;
  HtmlDocument.ElementSequence e;
  Token firstToken = getToken(1);
    try {
      jj_consume_token(TAG_START);
      jj_consume_token(TAG_STYLE);
      alist = AttributeList();
      jj_consume_token(TAG_END);
      token_source.SwitchTo(LexStyle);
      e = BlockContents();
      jj_consume_token(STYLE_END);
      {if (true) return new HtmlDocument.TagBlock("STYLE", alist, e);}
    } catch (ParseException ex) {
    token_source.SwitchTo(DEFAULT);
    String s = getTokenText(firstToken, getNextToken());
    {if (true) return new HtmlDocument.Text(s);}
    }
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.HtmlElement EndTag() throws ParseException {
  Token t;
  Token firstToken = getToken(1);
    try {
      jj_consume_token(ENDTAG_START);
      t = jj_consume_token(TAG_NAME);
      jj_consume_token(TAG_END);
      {if (true) return new HtmlDocument.EndTag(t.image);}
    } catch (ParseException ex) {
    token_source.SwitchTo(DEFAULT);
    String s = getTokenText(firstToken, getNextToken());
    {if (true) return new HtmlDocument.Text(s);}
    }
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.Comment CommentTag() throws ParseException {
  Token t, comment_start, comment_end = null;
  StringBuffer s = new StringBuffer();
    comment_start = jj_consume_token(COMMENT_START);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DASH:
      case COMMENT_EOL:
      case COMMENT_WORD:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DASH:
        t = jj_consume_token(DASH);
               s.append(t.image);
        break;
      case COMMENT_EOL:
        jj_consume_token(COMMENT_EOL);
                       s.append(NL);
        break;
      case COMMENT_WORD:
        t = jj_consume_token(COMMENT_WORD);
                         s.append(t.image);
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 0:
      jj_consume_token(0);
      break;
    case COMMENT_END:
      comment_end = jj_consume_token(COMMENT_END);
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return new HtmlDocument.Comment(comment_start.image + s.toString() + (comment_end == null ? "" : comment_end.image));}
    throw new Error("Missing return statement in function");
  }

  final public HtmlDocument.Comment DeclTag() throws ParseException {
  Token t;
    jj_consume_token(DECL_START);
    t = jj_consume_token(DECL_ANY);
    jj_consume_token(DECL_END);
    {if (true) return new HtmlDocument.Comment(t.image);}
    throw new Error("Missing return statement in function");
  }

  final private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  final private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  final private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  final private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  final private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  final private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  final private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  final private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  final private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  final private boolean jj_3_7() {
    if (jj_scan_token(JSP_EXPRESSION_IN_ATTRIBUTE)) return true;
    return false;
  }

  final private boolean jj_3_6() {
    if (jj_scan_token(VALUE_BINDING_IN_ATTRIBUTE)) return true;
    return false;
  }

  final private boolean jj_3_9() {
    if (jj_scan_token(UNPARSED_TEXT_NO_DOUBLE_QUOTES)) return true;
    return false;
  }

  final private boolean jj_3R_7() {
    if (jj_scan_token(TAG_START)) return true;
    if (jj_scan_token(TAG_NAME)) return true;
    return false;
  }

  final private boolean jj_3_8() {
    if (jj_scan_token(JSP_TAG_IN_ATTRIBUTE)) return true;
    return false;
  }

  final private boolean jj_3_4() {
    if (jj_scan_token(TAG_START)) return true;
    if (jj_scan_token(LST_ERROR)) return true;
    return false;
  }

  final private boolean jj_3_5() {
    if (jj_scan_token(EL_EXPRESSION_IN_ATTRIBUTE)) return true;
    return false;
  }

  final private boolean jj_3_3() {
    if (jj_3R_9()) return true;
    return false;
  }

  final private boolean jj_3_2() {
    if (jj_3R_8()) return true;
    return false;
  }

  final private boolean jj_3R_9() {
    if (jj_scan_token(TAG_START)) return true;
    if (jj_scan_token(TAG_STYLE)) return true;
    return false;
  }

  final private boolean jj_3R_8() {
    if (jj_scan_token(TAG_START)) return true;
    if (jj_scan_token(TAG_SCRIPT)) return true;
    return false;
  }

  final private boolean jj_3_1() {
    if (jj_3R_7()) return true;
    return false;
  }

  public HtmlParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  public boolean lookingAhead = false;
  private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[18];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_0();
      jj_la1_1();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x1f8000,0xe0000,0x108000,0x40000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x4000000,0x38000000,0x0,0x0,0x0,0x0,0x1,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x3d00,0x280,0x4000,0x3d20,0x3d20,0x50,0x6,0x0,0x0,0x3800000,0x3800000,0x70000,0x70000,0x8000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[9];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  public HtmlParser(java.io.InputStream stream) {
     this(stream, null);
  }
  public HtmlParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new HtmlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public HtmlParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new HtmlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public HtmlParser(HtmlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(HtmlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  final private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Enumeration e = jj_expentries.elements(); e.hasMoreElements();) {
        int[] oldentry = (int[])(e.nextElement());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.addElement(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[58];
    for (int i = 0; i < 58; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 18; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 58; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

  final private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 9; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  final private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
