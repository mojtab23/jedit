package net.sourceforge.phpdt.internal.compiler.ast;

import gatchan.phpparser.project.itemfinder.PHPItem;
import gatchan.phpparser.parser.PHPParser;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import org.gjt.sp.jedit.GUIUtilities;

/**
 * The ClassHeader is that : class ClassName [extends SuperClassName].
 *
 * @author Matthieu Casanova
 * @version $Id$
 */
public class ClassHeader extends AstNode implements PHPItem, Serializable {
  /** The path of the file containing this class. */
  private String path;

  /** The name of the class. */
  private String className;

  private String nameLowerCase;

  /** The name of the superclass. */
  private String superClassName;

  /** The implemented interfaces. It could be null. */
  private List interfaceNames;

  /** The methodsHeaders of the class. */
  private final List methodsHeaders = new ArrayList();

  /** A bitmask of modifiers from {@link Modifiers}. */
  private int modifiers;
  /**
   * The fields of the class.
   * It contains {@link FieldDeclaration}
   */
  private final List fields = new ArrayList();

  private static transient Icon icon;

  private transient String cachedToString;
  private static final long serialVersionUID = -5005330765043209914L;

  public ClassHeader() {
  }

  public ClassHeader(String path,
                     String className,
                     String superClassName,
                     List interfaceNames,
                     int sourceStart,
                     int sourceEnd,
                     int beginLine,
                     int endLine,
                     int beginColumn,
                     int endColumn) {
    super(sourceStart, sourceEnd, beginLine, endLine, beginColumn, endColumn);
    this.path = path;
    this.className = className;
    this.superClassName = superClassName;
    this.interfaceNames = interfaceNames;
  }

  public String toString(int tab) {
    StringBuffer buff = new StringBuffer(200);
    buff.append(tabString(tab));
    buff.append("class ");
    buff.append(className);
    if (superClassName != null) {
      buff.append(" extends ");
      buff.append(superClassName);
    }
    if (interfaceNames != null)
    {
      buff.append(" implements ");
      for (int i = 0; i < interfaceNames.size(); i++) {
        if (i != 0)
          buff.append(", ");
        buff.append(interfaceNames.get(i));
      }
    }
    return buff.toString();
  }

  public String toString() {
    if (cachedToString == null) {
      StringBuffer buff = new StringBuffer(200);
      buff.append(className);
      if (superClassName != null) {
        buff.append(':');
        buff.append(superClassName);
      }
      cachedToString = buff.toString();
    }
    return cachedToString;
  }

  public void getOutsideVariable(List list) {
  }

  public void getModifiedVariable(List list) {
  }

  public void getUsedVariable(List list) {
  }

  public void addModifier(int modifier)
  {
    modifiers |= modifier;
  }

  /**
   * Returns the name of the class.
   *
   * @return the name of the class
   */
  public String getName() {
    return className;
  }

  public String getNameLowerCase() {
    if (nameLowerCase == null) {
      nameLowerCase = className.toLowerCase();
    }
    return nameLowerCase;
  }

  /**
   * Returns the name of the superclass.
   *
   * @return the name of the superclass
   */
  public String getSuperClassName() {
    return superClassName;
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof ClassHeader)) return false;
    return ((ClassHeader) obj).getName().equals(className);
  }

  public String getPath() {
    return path;
  }

  public Icon getIcon() {
    if (icon == null) {
      icon = GUIUtilities.loadIcon(ClassHeader.class.getResource("/gatchan/phpparser/icons/class.png").toString());
    }
    return icon;
  }

  /**
   * Add a method to the class.
   *
   * @param method the method declaration
   */
  public void addMethod(MethodHeader method) {
    methodsHeaders.add(method);
  }

  /**
   * Add a method to the class.
   *
   * @param field the method declaration
   */
  public void addField(FieldDeclaration field) {
    fields.add(field);
  }

  public List getMethodsHeaders() {
    return methodsHeaders;
  }

  /**
   * Returns the list of the field of this class.
   * It contains {@link FieldDeclaration}
   *
   * @return the list of fields of the class
   */
  public List getFields() {
    return fields;
  }

  public int getItemType() {
    return CLASS;
  }

  public void analyzeCode(PHPParser parser) {
  }

  public List getInterfaceNames() {
    return interfaceNames;
  }
}
