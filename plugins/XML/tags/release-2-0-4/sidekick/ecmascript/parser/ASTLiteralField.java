/* Generated By:JJTree: Do not edit this line. ASTLiteralField.java */

package sidekick.ecmascript.parser;

public class ASTLiteralField extends SimpleNode {
  public ASTLiteralField(int id) {
    super(id);
  }

  public ASTLiteralField(EcmaScript p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
