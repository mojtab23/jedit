/*
 * :tabSize=8:indentSize=4:noTabs=true:maxLineLen=0:
 *
 * Copyright (c) 2001 Dirk Moebius.
 *
 * IntegerStack.java - a wrapper class for a typesafe stack.
 *
 * Part of the C++ to Java port of AStyle, maintained by
 * Dirk Moebius (dmoebius@gmx.net).
 *
 * The "Artistic Style" project, including all files needed to compile it,
 * is free software; you can redistribute it and/or use it and/or modify it
 * under the terms of EITHER the "Artistic License" OR
 * the GNU Library General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 *  version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of EITHER the "Artistic License" or
 * the GNU Library General Public License along with this program.
 */


package astyle.util;


import java.util.Stack;


/**
 * This is a stack of integers.
 */
public class IntegerStack extends Stack {

    public IntegerStack() {
        super();
    }


    /**
     * same as push()
     * @see java.util.Stack#push
     */
    public void push_back(int i) {
        super.push(new Integer(i));
    }


    /**
     * same as pop()
     * @see java.util.Stack#pop
     */
    public int pop_back() {
        if (empty())
            throw new Error("empty stack");
        return ((Integer)super.pop()).intValue();
    }


    /**
     * same as peek()
     * @see java.util.Stack#peek
     */
    public int back() {
        if (empty())
            throw new Error("empty stack");
        return ((Integer)super.peek()).intValue();
    }


    /**
     * same as elementAt()
     * @see java.util.Vector#elementAt
     */
    public int at(int i) {
        return ((Integer)super.elementAt(i)).intValue();
    }


    /**
     * increase the value stored at back()
     */
    public void incBack() {
        int v = back();
        elementData[elementCount - 1] = new Integer(v + 1);
    }


    /**
     * decrease the value stored at back()
     */
    public void decBack() {
        int v = back();
        elementData[elementCount - 1] = new Integer(v - 1);
    }


    public IntegerStack getClone() {
        return (IntegerStack) clone();
    }

}

