/* Generated By:JJTree: Do not edit this line. ASTFunctionDeclaration.java */

package sidekick.ecmascript.parser;

import java.util.*;

import sidekick.ecmascript.parser.EcmaScript;
import sidekick.ecmascript.parser.EcmaScriptVisitor;

public class ASTFunctionDeclaration extends SimpleNode {

    private Map locals;

    public ASTFunctionDeclaration( int id ) {
        super( id );
    }

    public ASTFunctionDeclaration( EcmaScript p, int id ) {
        super( p, id );
    }

    public boolean isVisible() {
        return true;
    }

    /** Accept the visitor. * */
    @Override
    public Object jjtAccept( EcmaScriptVisitor visitor, Object data ) {
        return visitor.visit( this, data );
    }

    public void setLocals( List localsStack ) {
        locals = new HashMap();

        Iterator iter = localsStack.iterator();

        while ( iter.hasNext() ) {
            Map stackItem = ( Map ) iter.next();

            locals.putAll( stackItem );
        }
    }

    public Map getLocals() {
        return locals;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append( jjtGetChild( 0 ).toString() );
        sb.append( "(" );
        if ( hasChildren() ) {
            SimpleNode params = (SimpleNode)jjtGetChild(1);
            if (params != null && params.hasChildren()) {
                for (Iterator it = params.getChildren().iterator(); it.hasNext(); ) {
                    SimpleNode param = (SimpleNode)it.next();
                    sb.append(param.toString());
                    if (it.hasNext()) {
                        sb.append(", ");
                    }
                }
            }
        }
        sb.append( ")" );
        return sb.toString();
    }

}
