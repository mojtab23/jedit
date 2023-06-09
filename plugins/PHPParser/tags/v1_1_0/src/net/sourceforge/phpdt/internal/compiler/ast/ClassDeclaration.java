package net.sourceforge.phpdt.internal.compiler.ast;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.phpdt.internal.compiler.parser.Outlineable;
import net.sourceforge.phpdt.internal.compiler.parser.OutlineableWithChildren;


/**
 * This class is my ClassDeclaration declaration for php.
 * It is similar to org.eclipse.jdt.internal.compiler.ast.TypeDeclaration
 * It directly extends AstNode because a class cannot appear anywhere in php
 *
 * @author Matthieu Casanova
 */
public final class ClassDeclaration extends Statement implements OutlineableWithChildren {

  /**
   * The name of the class.
   */
  private final String name;
  /**
   * The superclass.
   */
  private String superclass;

  public int declarationSourceStart;
  public int declarationSourceEnd;
  public int bodyStart;
  public int bodyEnd;
  /**
   * The methods of the class.
   */
  private final ArrayList methods = new ArrayList();
  /**
   * The constructor of the class.
   */
  private MethodDeclaration constructor;
  /**
   * The fields of the class.
   */
  private final ArrayList fields = new ArrayList();

  private final Object parent;
  /**
   * The outlineable children (those will be in the node array too.
   */
  private final ArrayList children = new ArrayList();


  /**
   * Create a class giving starting and ending offset.
   *
   * @param sourceStart starting offset
   * @param sourceEnd   ending offset
   */
  public ClassDeclaration(final Object parent,
                          final String name,
                          final String superclass,
                          final int sourceStart,
                          final int sourceEnd,
                          final int beginLine,
                          final int endLine,
                          final int beginColumn,
                          final int endColumn) {
    super(sourceStart, sourceEnd,beginLine,endLine,beginColumn,endColumn);
    this.parent = parent;
    this.name = name;
    this.superclass = superclass;
  }
  /**
   * Create a class giving starting and ending offset.
   *
   * @param sourceStart starting offset
   * @param sourceEnd   ending offset
   */
  public ClassDeclaration(final Object parent,
                          final String name,
                          final int sourceStart,
                          final int sourceEnd,
                          final int beginLine,
                          final int endLine,
                          final int beginColumn,
                          final int endColumn) {
    super(sourceStart, sourceEnd,beginLine,endLine,beginColumn,endColumn);
    this.parent = parent;
    this.name = name;
  }

  /**
   * Add a method to the class.
   *
   * @param method the method declaration
   */
  public void addMethod(final MethodDeclaration method) {
    methods.add(method);
    add(method);
    if (method.name.equals(name)) {
      constructor = method;
    }
  }

  public void addField(final FieldDeclaration var) {
    for (int i = 0; i < var.vars.length; i++) {
      final VariableDeclaration c = var.vars[i];
      children.add(c);
    }
    fields.add(var);
  }

  public boolean add(final Outlineable o) {
    return children.add(o);
  }

  /**
   * Tell if the class has a constructor.
   *
   * @return a boolean
   */
  public boolean hasConstructor() {
    return constructor != null;
  }

  /**
   * Return the class as String.
   *
   * @param tab how many tabs before the class
   * @return the code of this class into String
   */
  public String toString(final int tab) {
    return tabString(tab) + toStringHeader() + toStringBody(tab);
  }

  /**
   * Return the body of the class as String.
   *
   * @param tab how many tabs before the body of the class
   * @return the body as String
   */
  private String toStringBody(final int tab) {
    final StringBuffer buff = new StringBuffer(" {");//$NON-NLS-1$
    if (fields != null) {
      for (int i = 0; i < fields.size(); i++) {
        final FieldDeclaration field = (FieldDeclaration) fields.get(i);
        buff.append("\n"); //$NON-NLS-1$
        buff.append(field.toString(tab + 1));
        buff.append(";");//$NON-NLS-1$
      }
    }
    for (int i = 0; i < methods.size(); i++) {
      final MethodDeclaration o = (MethodDeclaration) methods.get(i);
      buff.append("\n");//$NON-NLS-1$
      buff.append(o.toString(tab + 1));
    }
    buff.append("\n").append(tabString(tab)).append("}"); //$NON-NLS-2$ //$NON-NLS-1$
    return buff.toString();
  }

  /**
   * Return the header of the class as String.
   *
   * @return the header of the class
   */
  private String toStringHeader() {
    final StringBuffer buff = new StringBuffer("class ").append(name);//$NON-NLS-1$
    if (superclass != null) {
      buff.append(" extends "); //$NON-NLS-1$
      buff.append(superclass);
    }
    return buff.toString();
  }

  public Object getParent() {
    return parent;
  }

  public Outlineable get(final int index) {
    return (Outlineable) children.get(index);
  }

  public int size() {
    return children.size();
  }

  public String toString() {
    final StringBuffer buff = new StringBuffer(name);
    if (superclass != null) {
      buff.append(":"); //$NON-NLS-1$
      buff.append(superclass);
    }
    return buff.toString();
  }

  public List getList() {
    return children;
  }

  /**
   * Get the variables from outside (parameters, globals ...)
   *
   * @param list the list where we will put variables
   */
  public void getOutsideVariable(final List list) {
  }

  /**
   * get the modified variables.
   *
   * @param list the list where we will put variables
   */
  public void getModifiedVariable(final List list) {
  }

  /**
   * Get the variables used.
   *
   * @param list the list where we will put variables
   */
  public void getUsedVariable(final List list) {
  }
}
