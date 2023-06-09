package net.sourceforge.phpdt.internal.compiler.ast;

import net.sourceforge.phpdt.internal.compiler.ast.declarations.VariableUsage;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import gatchan.phpparser.project.itemfinder.PHPItem;

import javax.swing.*;

import org.gjt.sp.jedit.GUIUtilities;

/** @author Matthieu Casanova */
public class MethodHeader extends Statement implements PHPItem, Serializable {

  /** The path of the file containing this class. */
  private String path;

  /** The name of the method. */
  private String name;

  /** Indicate if the method returns a reference. */
  private boolean reference;

  /** The arguments. */
  private ArrayList arguments;

  private String cachedToString;

  private transient Icon icon;

  public MethodHeader() {
  }

  public MethodHeader(String path,
                      String name,
                      boolean reference,
                      ArrayList arguments,
                      int sourceStart,
                      int sourceEnd,
                      int beginLine,
                      int endLine,
                      int beginColumn,
                      int endColumn) {
    super(sourceStart, sourceEnd, beginLine, endLine, beginColumn, endColumn);
    this.path = path;
    this.name = name;
    this.reference = reference;
    this.arguments = arguments;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    if (cachedToString == null) {
      final StringBuffer buff = new StringBuffer(100);
      if (reference) buff.append('&');
      buff.append(name);
      buff.append('(');
      if (arguments != null) {
        for (int i = 0; i < arguments.size(); i++) {
          final FormalParameter o = (FormalParameter) arguments.get(i);
          buff.append(o.toStringExpression());
          if (i != (arguments.size() - 1)) {
            buff.append(", ");
          }
        }
      }
      buff.append(')');
      cachedToString = buff.toString();
    }
    return cachedToString;
  }

  public String toString(int tab) {
    return tabString(tab) + toString();
  }

  public void getOutsideVariable(List list) {
  }

  public void getModifiedVariable(List list) {
  }

  public void getUsedVariable(List list) {
  }

  public int getArgumentsCount() {
    return arguments.size();
  }

  public void getParameters(List list) {
    if (arguments != null) {
      for (int i = 0; i < arguments.size(); i++) {
        final FormalParameter variable = (FormalParameter) arguments.get(i);
        final VariableUsage variableUsage = new VariableUsage(variable.getName(),
                                                              variable.getSourceStart(),
                                                              variable.getSourceEnd(),
                                                              variable.getBeginLine(),
                                                              variable.getEndLine(),
                                                              variable.getBeginColumn(),
                                                              variable.getEndColumn());
        list.add(variableUsage);
      }
    }
  }

  public String getPath() {
    return path;
  }

  public int getItemType() {
    return METHOD;
  }

  public Icon getIcon() {
    if (icon == null) {
      icon = GUIUtilities.loadIcon(ClassHeader.class.getResource("/gatchan/phpparser/icons/method.png").toString());
    }
    return icon;
  }
}
