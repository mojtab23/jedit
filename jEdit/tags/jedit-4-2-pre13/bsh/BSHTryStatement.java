/*****************************************************************************
 *                                                                           *
 *  This file is part of the BeanShell Java Scripting distribution.          *
 *  Documentation and updates may be found at http://www.beanshell.org/      *
 *                                                                           *
 *  Sun Public License Notice:                                               *
 *                                                                           *
 *  The contents of this file are subject to the Sun Public License Version  *
 *  1.0 (the "License"); you may not use this file except in compliance with *
 *  the License. A copy of the License is available at http://www.sun.com    * 
 *                                                                           *
 *  The Original Code is BeanShell. The Initial Developer of the Original    *
 *  Code is Pat Niemeyer. Portions created by Pat Niemeyer are Copyright     *
 *  (C) 2000.  All Rights Reserved.                                          *
 *                                                                           *
 *  GNU Public License Notice:                                               *
 *                                                                           *
 *  Alternatively, the contents of this file may be used under the terms of  *
 *  the GNU Lesser General Public License (the "LGPL"), in which case the    *
 *  provisions of LGPL are applicable instead of those above. If you wish to *
 *  allow use of your version of this file only under the  terms of the LGPL *
 *  and not to allow others to use your version of this file under the SPL,  *
 *  indicate your decision by deleting the provisions above and replace      *
 *  them with the notice and other provisions required by the LGPL.  If you  *
 *  do not delete the provisions above, a recipient may use your version of  *
 *  this file under either the SPL or the LGPL.                              *
 *                                                                           *
 *  Patrick Niemeyer (pat@pat.net)                                           *
 *  Author of Learning Java, O'Reilly & Associates                           *
 *  http://www.pat.net/~pat/                                                 *
 *                                                                           *
 *****************************************************************************/


package bsh;

import java.util.Vector;

class BSHTryStatement extends SimpleNode
{
	BSHTryStatement(int id)
	{
		super(id);
	}

	public Object eval( CallStack callstack, Interpreter interpreter)  
		throws EvalError
	{
		BSHBlock tryBlock = ((BSHBlock)jjtGetChild(0));

		Vector catchParams = new Vector();
		Vector catchBlocks = new Vector();

		int nchild = jjtGetNumChildren();
		Node node = null;
		int i=1;
		while((i < nchild) && ((node = jjtGetChild(i++)) instanceof BSHFormalParameter))
		{
			catchParams.addElement(node);
			catchBlocks.addElement(jjtGetChild(i++));
			node = null;
		}
		// finaly block
		BSHBlock finallyBlock = null;
		if(node != null)
			finallyBlock = (BSHBlock)node;

// Why both of these?

		TargetError target = null;
		Throwable thrown = null;
		Object ret = null;

		/*
			Evaluate the contents of the try { } block and catch any resulting
			TargetErrors generated by the script.
			We save the callstack depth and if an exception is thrown we pop
			back to that depth before contiuing.  The exception short circuited
			any intervening method context pops.

			Note: we the stack info... what do we do with it?  append
			to exception message?
		*/
		int callstackDepth = callstack.depth();
		try {
			ret = tryBlock.eval(callstack, interpreter);
		}
		catch( TargetError e ) {
			target = e;
			String stackInfo = "Bsh Stack: ";
			while ( callstack.depth() > callstackDepth )
				stackInfo += "\t" + callstack.pop() +"\n";
		}

		// unwrap the target error
		if ( target != null )
			thrown = target.getTarget();

		
		// If we have an exception, find a catch
		if (thrown != null) 
		{
			int n = catchParams.size();
			for(i=0; i<n; i++)
			{
				// Get catch block
				BSHFormalParameter fp = 
					(BSHFormalParameter)catchParams.elementAt(i);

				// Should cache this subject to classloader change message
				// Evaluation of the formal parameter simply resolves its
				// type via the specified namespace.. it doesn't modify the
				// namespace.
				fp.eval( callstack, interpreter );

				if ( fp.type == null && interpreter.getStrictJava() )
					throw new EvalError(
						"(Strict Java) Untyped catch block", this, callstack );

				// If the param is typed check assignability
				if ( fp.type != null ) 
					try {
						thrown = (Throwable)Types.getAssignableForm(
							thrown, fp.type);
					} catch( UtilEvalError e ) {
						/* 
							Catch the mismatch and continue to try the next
							Note: this is innefficient, should have an 
							isAssignableFrom() that doesn't throw 
						*/
						continue;
					}

				// Found match, execute catch block
				BSHBlock cb = (BSHBlock)(catchBlocks.elementAt(i));

				// Prepare to execute the block.
				// We must create a new BlockNameSpace to hold the catch
				// parameter and swap it on the stack after initializing it.

				NameSpace enclosingNameSpace = callstack.top();
				BlockNameSpace cbNameSpace = 
					new BlockNameSpace( enclosingNameSpace );

				try {
					if ( fp.type == BSHFormalParameter.UNTYPED )
						// set an untyped variable directly in the block
						cbNameSpace.setBlockVariable( fp.name, thrown );
					else
						// set a typed variable (directly in the block)
						cbNameSpace.setTypedVariable(
							fp.name, fp.type, thrown, false);
				} catch ( UtilEvalError e ) {
					throw new InterpreterError(
						"Unable to set var in catch block namespace." );
				}

				// put cbNameSpace on the top of the stack
				callstack.swap( cbNameSpace );
				try {
					ret = cb.eval( callstack, interpreter );
				} finally {
					// put it back
					callstack.swap( enclosingNameSpace );
				}

				target = null;  // handled target
				break;
			}
		}

		// evaluate finally block
		if(finallyBlock != null)
			ret = finallyBlock.eval(callstack, interpreter);

		// exception fell through, throw it upward...
		if(target != null)
			throw target;

		if(ret instanceof ReturnControl)
			return ret;
		else	
			return Primitive.VOID;
	}
}
