/*
 * jEdit - Programmer's Text Editor
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright © 2011 jEdit contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.kpouer.jedit.remotecontrol.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.gjt.sp.jedit.msg.BufferChanging;

/**
 * @author Matthieu Casanova
 */
public class BufferSetMessageConverter implements Converter
{
	@Override
	public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext)
	{
		BufferSetMessage bufferSetMessage = (BufferSetMessage) o;
		hierarchicalStreamWriter.startNode("buffer");
		marshallingContext.convertAnother(bufferSetMessage.getBuffer());
		hierarchicalStreamWriter.endNode();
		hierarchicalStreamWriter.startNode("index");
		marshallingContext.convertAnother(bufferSetMessage.getIndex());
		hierarchicalStreamWriter.endNode();
		hierarchicalStreamWriter.startNode("newIndex");
		marshallingContext.convertAnother(bufferSetMessage.getNewIndex());
		hierarchicalStreamWriter.endNode();
		hierarchicalStreamWriter.startNode("what");
		marshallingContext.convertAnother(bufferSetMessage.getWhat());
		hierarchicalStreamWriter.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext)
	{
		return null;
	}

	@Override
	public boolean canConvert(Class aClass)
	{
		return BufferSetMessage.class.equals(aClass);
	}
}
