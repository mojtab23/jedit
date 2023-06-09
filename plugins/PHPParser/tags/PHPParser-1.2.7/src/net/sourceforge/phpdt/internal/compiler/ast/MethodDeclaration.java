/*
* MethodDeclaration.java
* :tabSize=8:indentSize=8:noTabs=false:
* :folding=explicit:collapseFolds=1:
*
* Copyright (C) 2003, 2009 Matthieu Casanova
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
package net.sourceforge.phpdt.internal.compiler.ast;

//{{{ Imports
import gatchan.phpparser.parser.PHPParseMessageEvent;
import gatchan.phpparser.parser.PHPParser;
import gatchan.phpparser.project.itemfinder.PHPItem;
import net.sourceforge.phpdt.internal.compiler.ast.declarations.VariableUsage;
import net.sourceforge.phpdt.internal.compiler.parser.Outlineable;
import net.sourceforge.phpdt.internal.compiler.parser.OutlineableWithChildren;
import sidekick.IAsset;

import javax.swing.*;
import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//}}}

/**
* A Method declaration.
*
* @author Matthieu Casanova
*/
public class MethodDeclaration extends Expression implements OutlineableWithChildren, IAsset
{
	private MethodHeader methodHeader;

	private Statement[] statements;

	private int bodyLineStart;
	private int bodyColumnStart;
	private int bodyLineEnd;
	private int bodyColumnEnd;

	/** Tell if the method is a class constructor. */
	private boolean isConstructor;

	/** The parent object. */
	private transient OutlineableWithChildren parent;
	/** The outlineable children (those will be in the node array too. */
	private final List children = new ArrayList();

	private transient Position start;
	private transient Position end;
	private static Icon icon;

	/** The variables assigned in code. This is used during code completion. */
	private transient List assignedVariablesInCode;
	private static final long serialVersionUID = 8471570829959168564L;


	//{{{ MethodDeclaration constructor
	public MethodDeclaration(OutlineableWithChildren parent, MethodHeader methodHeader)
	{
		sourceStart = methodHeader.getSourceStart();
		beginLine = methodHeader.getBeginLine();
		beginColumn = methodHeader.getBeginColumn();
		endLine = methodHeader.getEndLine();
		endColumn = methodHeader.getEndColumn();
		this.parent = parent;
		this.methodHeader = methodHeader;
	} //}}}

	//{{{ toString() methods
	/**
	 * Return method into String, with a number of tabs
	 *
	 * @param tab the number of tabs
	 *
	 * @return the String containing the method
	 */
	public String toString(int tab)
	{
		StringBuilder buff = new StringBuilder(200);
		buff.append(methodHeader.toString(tab));
		buff.append(toStringStatements(tab + 1));
		return buff.toString();
	}

	@Override
	public String toStringExpression()
	{
		return toString(0);
	}

	public String toString()
	{
		return methodHeader.toString();
	} //}}}

	//{{{ toStringStatements() method
	/**
	 * Return the statements of the method into Strings
	 *
	 * @param tab the number of tabs
	 *
	 * @return the String containing the statements
	 */
	private String toStringStatements(int tab)
	{
		StringBuilder buff = new StringBuilder(" {");
		if (statements != null)
		{
			for (int i = 0; i < statements.length; i++)
			{
				buff.append('\n').append(statements[i].toString(tab));
				if (!(statements[i] instanceof Block))
				{
					buff.append(';');
				}
			}
		}
		buff.append('\n').append(tabString(tab == 0 ? 0 : tab - 1)).append('}');
		return buff.toString();
	} //}}}

	//{{{ setParent() method
	public void setParent(OutlineableWithChildren parent)
	{
		this.parent = parent;
	} //}}}

	//{{{ getParent() method
	public OutlineableWithChildren getParent()
	{
		return parent;
	} //}}}

	//{{{ add() method
	public boolean add(Outlineable o)
	{
		return children.add(o);
	} //}}}

	//{{{ get() method
	public Outlineable get(int index)
	{
		return (Outlineable) children.get(index);
	} //}}}

	//{{{ size() method
	public int size()
	{
		return children.size();
	} //}}}

	//{{{ getOutsideVariable() method
	/**
	 * Get the variables from outside (parameters, globals ...)
	 *
	 * @param list the list where we will put variables
	 */
	public void getOutsideVariable(List list)
	{
	} //}}}

	//{{{ getModifiedVariable() method
	/**
	 * get the modified variables.
	 *
	 * @param list the list where we will put variables
	 */
	public void getModifiedVariable(List list)
	{
	} //}}}

	//{{{ getUsedVariable() method
	/**
	 * This method will analyze the code.
	 *
	 * @param list the list where we will put variables
	 */
	public void getUsedVariable(List list)
	{
	} //}}}

	//{{{ getGlobalVariable() method
	/**
	 * Get global variables (not parameters).
	 *
	 * @param list the list where I will put the variables
	 */
	private void getGlobalVariable(List list)
	{
		if (statements != null)
		{
			for (int i = 0; i < statements.length; i++)
			{
				statements[i].getOutsideVariable(list);
			}
		}
	} //}}}

	//{{{ getAssignedVariableInCode() method
	/**
	 * get the modified variables.
	 *
	 * @return the assigned variables in code.
	 */
	private List getAssignedVariableInCode()
	{
		if (assignedVariablesInCode == null)
		{
			assignedVariablesInCode = new ArrayList(50);
			if (statements != null)
			{
				for (int i = 0; i < statements.length; i++)
				{
					statements[i].getModifiedVariable(assignedVariablesInCode);
				}
			}
		}
		return assignedVariablesInCode;
	} //}}}

	//{{{ getUsedVariableInCode() method
	/**
	 * Get the variables used.
	 *
	 * @param list the list where I will put the variables
	 */
	private void getUsedVariableInCode(List list)
	{
		if (statements != null)
		{
			for (int i = 0; i < statements.length; i++)
			{
				statements[i].getUsedVariable(list);
			}
		}
	} //}}}

	//{{{ getAssignedVariableInCode
	/**
	 * Returns the last variable assignation with the given name before the line and column.
	 *
	 * @param name   the name of the variable
	 * @param line   the line
	 * @param column the column
	 *
	 * @return a variable usage or null
	 */
	public VariableUsage getAssignedVariableInCode(String name, int line, int column)
	{
		List assignedVariablesInCode = getAssignedVariableInCode();
		VariableUsage found = null;
		for (int i = 0; i < assignedVariablesInCode.size(); i++)
		{
			VariableUsage variableUsage = (VariableUsage) assignedVariablesInCode.get(i);
			if (variableUsage.getEndLine() > line || (variableUsage.getEndLine() == line && variableUsage.getBeginColumn() > column))
			{
				// We do not need variables declared after the given line
				break;
			}
			if (variableUsage.getName().equals(name) && (found == null || found.isDeclaredBefore(variableUsage)))
			{
				found = variableUsage;
			}
		}
		return found;
	} //}}}

	//{{{ isVariableDeclaredBefore() method
	private static boolean isVariableDeclaredBefore(List list, VariableUsage var)
	{
		String name = var.getName();
		int pos = var.getSourceStart();
		for (int i = 0; i < list.size(); i++)
		{
			VariableUsage variableUsage = (VariableUsage) list.get(i);
			if (variableUsage.getName().equals(name) && variableUsage.getSourceStart() < pos)
			{
				return true;
			}
		}
		return false;
	} //}}}

	//{{{ analyzeCode() method
	/** This method will analyze the code. */
	public void analyzeCode(PHPParser parser)
	{
		methodHeader.analyzeCode(parser);
		if (statements != null)
		{
			for (int i = 0; i < statements.length; i++)
			{
				statements[i].analyzeCode(parser);
			}
		}

		List globalsVars = new ArrayList();
		getGlobalVariable(globalsVars);
		List modifiedVars = getAssignedVariableInCode();
		List parameters = new ArrayList(methodHeader.getArgumentsCount());
		methodHeader.getParameters(parameters);

		List declaredVars = new ArrayList(globalsVars.size() + modifiedVars.size() + parameters.size());
		declaredVars.addAll(globalsVars);
		declaredVars.addAll(modifiedVars);
		declaredVars.addAll(parameters);

		List usedVars = new ArrayList();
		getUsedVariableInCode(usedVars);
		List readOrWriteVars = new ArrayList(modifiedVars.size() + usedVars.size());
		readOrWriteVars.addAll(modifiedVars);
		readOrWriteVars.addAll(usedVars);

		//look for used variables that were not declared before
		findUnusedParameters(parser, readOrWriteVars, parameters);
		findUnknownUsedVars(parser, usedVars, declaredVars);
	} //}}}

	//{{{ findUnusedParameters() methos
	/**
	 * This method will add a warning on all unused parameters.
	 *
	 * @param parser     the php parser
	 * @param vars       the used variable list
	 * @param parameters the declared variable list
	 */
	private static void findUnusedParameters(PHPParser parser, List vars, List parameters)
	{
		for (int i = 0; i < parameters.size(); i++)
		{
			VariableUsage param = (VariableUsage) parameters.get(i);
			if (!isVariableInList(param.getName(), vars))
			{
				parser.fireParseMessage(new PHPParseMessageEvent(PHPParser.WARNING,
										 PHPParseMessageEvent.MESSAGE_UNUSED_PARAMETERS,
										 parser.getPath(),
										 "warning, the parameter " + param.getName() + " seems to be never used in your method",
										 param.getSourceStart(),
										 param.getSourceEnd(),
										 param.getBeginLine(),
										 param.getEndLine(),
										 param.getBeginColumn(),
										 param.getEndColumn()));
			}
		}
	} //}}}

	//{{{ isVariableInList() method
	/**
	 * Tell if the list of VariableUsage contains a variable named by the name given.
	 *
	 * @param name the variable name
	 * @param list the list of VariableUsage
	 *
	 * @return true if the variable is in the list false otherwise
	 */
	private static boolean isVariableInList(String name, List list)
	{
		for (int i = 0; i < list.size(); i++) {
			if (((VariableUsage) list.get(i)).getName().equals(name))
			{
				return true;
			}
		}
		return false;
	} //}}}

	//{{{ findUnknownUsedVars() method
	/**
	 * This method will add a warning on all used variables in a method that aren't declared before.
	 *
	 * @param parser       the php parser
	 * @param usedVars     the used variable list
	 * @param declaredVars the declared variable list
	 */
	private static void findUnknownUsedVars(PHPParser parser, List usedVars, List declaredVars)
	{
		Set list = new HashSet(usedVars.size());
		for (int i = 0; i < usedVars.size(); i++)
		{
			VariableUsage variableUsage = (VariableUsage) usedVars.get(i);
			if ("this".equals(variableUsage.getName())) continue; // this is a special variable
			if (!list.contains(variableUsage.getName()) && !isVariableDeclaredBefore(declaredVars, variableUsage))
			{
				list.add(variableUsage.getName());
				parser.fireParseMessage(new PHPParseMessageEvent(PHPParser.WARNING,
										 PHPParseMessageEvent.MESSAGE_VARIABLE_MAY_BE_UNASSIGNED,
										 parser.getPath(),
										 "warning, usage of a variable that seems to be unassigned yet : " + variableUsage.getName(),
										 variableUsage.getSourceStart(),
										 variableUsage.getSourceEnd(),
										 variableUsage.getBeginLine(),
										 variableUsage.getEndLine(),
										 variableUsage.getBeginColumn(),
										 variableUsage.getEndColumn()));
			}
		}
	} //}}}

	//{{{ getName() method
	public String getName()
	{
		return methodHeader.getName();
	} //}}}

	//{{{ getMethodHeader() method
	public MethodHeader getMethodHeader()
	{
		return methodHeader;
	} //}}}

	//{{{ setStatements() method
	public void setStatements(Statement[] statements)
	{
		this.statements = statements;
	} //}}}

	//{{{ getBodyLineStart() method
	public int getBodyLineStart()
	{
		return bodyLineStart;
	} //}}}

	//{{{ setBodyLineStart() method
	public void setBodyLineStart(int bodyLineStart)
	{
		this.bodyLineStart = bodyLineStart;
	} //}}}

	//{{{ getBodyColumnStart() method
	public int getBodyColumnStart()
	{
		return bodyColumnStart;
	} //}}}

	//{{{ setBodyColumnStart() method
	public void setBodyColumnStart(int bodyColumnStart)
	{
		this.bodyColumnStart = bodyColumnStart;
	} //}}}

	//{{{ getBodyLineEnd() method
	public int getBodyLineEnd()
	{
		return bodyLineEnd;
	} //}}}

	//{{{ setBodyLineEnd() method
	public void setBodyLineEnd(int bodyLineEnd)
	{
		this.bodyLineEnd = bodyLineEnd;
		setEndLine(bodyLineEnd);
	} //}}}

	//{{{ getBodyColumnEnd() method
	public int getBodyColumnEnd()
	{
		return bodyColumnEnd;
	} //}}}

	//{{{ setBodyColumnEnd() method
	public void setBodyColumnEnd(int bodyColumnEnd)
	{
		this.bodyColumnEnd = bodyColumnEnd;
		setEndColumn(bodyColumnEnd);
	} //}}}

	//{{{ getItemType() method
	public int getItemType()
	{
		return PHPItem.METHOD;
	} //}}}

	//{{{ getIcon() method
	public Icon getIcon()
	{
		if (icon == null)
		{
			icon = new ImageIcon(MethodDeclaration.class.getResource("/gatchan/phpparser/icons/method.png"));
		}
		return icon;
	} //}}}

	//{{{ getStart() method
	public Position getStart()
	{

		return start;
	} //}}}

	//{{{ setStart() method
	public void setStart(Position start)
	{
		this.start = start;
	} //}}}

	//{{{ getEnd() method
	public Position getEnd()
	{
		return end;
	} //}}}

	//{{{ setEnd() method
	public void setEnd(Position end)
	{
		this.end = end;
	} //}}}

	//{{{ getShortString() method
	public String getShortString()
	{
		return toString();
	} //}}}

	//{{{ getLongString() method
	public String getLongString()
	{
		return toString();
	} //}}}

	//{{{ setName() method
	public void setName(String name)
	{
	} //}}}

	//{{{ expressionAt() method
	public Expression expressionAt(int line, int column)
	{
		if (methodHeader.isAt(line, column))
			return methodHeader.expressionAt(line, column);
		for (int i = 0; i < statements.length; i++)
		{
			Statement statement = statements[i];
			if (statement.isAt(line, column))
				return statement.expressionAt(line, column);
		}
		return null;
	} //}}}
}
