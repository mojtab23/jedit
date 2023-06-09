package net.sourceforge.phpdt.internal.compiler.ast;

import java.util.List;

/**
 * a binary expression is a combination of two expressions with an operator.
 * 
 * @author Matthieu Casanova
 */
public final class BinaryExpression extends OperatorExpression {

  /** The left expression. */
  private final Expression left;
  /** The right expression. */
  private final Expression right;

  /**
   * Create a binary expression.
   * 
   * @param left     the left expression
   * @param right    the right expression
   * @param operator an operator taken in the {@link OperatorExpression} interface
   */
  public BinaryExpression(final Expression left,
                          final Expression right,
                          final int operator) {
    super(operator, left.sourceStart, right.sourceEnd);
    this.left = left;
    this.right = right;
  }

  public String toStringExpression() {
    final String leftString = left.toStringExpression();
    final String operatorString = operatorToString();
    final String rightString = right.toStringExpression();
    final StringBuffer buff = new StringBuffer(leftString.length() + operatorString.length() + rightString.length());
    buff.append(leftString);
    buff.append(operatorString);
    buff.append(rightString);
    return buff.toString();
  }

  /**
   * Get the variables from outside (parameters, globals ...)
   * 
   * @param list the list where we will put variables
   */
  public void getOutsideVariable(final List list) {}

  /**
   * get the modified variables.
   * 
   * @param list the list where we will put variables
   */
  public void getModifiedVariable(final List list) {
    left.getModifiedVariable(list);
    right.getModifiedVariable(list);
  }

  /**
   * Get the variables used.
   * 
   * @param list the list where we will put variables
   */
  public void getUsedVariable(final List list) {
    left.getUsedVariable(list);
    right.getUsedVariable(list);
  }

}
