package gatchan.jedit.lucene;

import java.util.HashMap;
import java.io.File;
import java.lang.reflect.Constructor;


public class IndexFactory
{
	private static HashMap<String, Class> indexes = new HashMap<String, Class>();

	static
	{
		register("File-based index", IndexImpl.class);
		register("Line-based index", LineIndexImpl.class);
	}

	// Returns the type name of the given index
	public static String getType(Index index)
	{
		Class c = index.getClass();
		for (String type : indexes.keySet())
		{
			if (indexes.get(type) == c)
				return type;
		}
		return null;
	}

	public static void register(String name, Class cls)
	{
		indexes.put(name, cls);
	}

	public static String[] getIndexNames()
	{
		String[] names = new String[indexes.size()];
		indexes.keySet().toArray(names);
		return names;
	}

	public static Index createIndex(String type, String name, File path)
	{
		Class c = indexes.get(type);
		if (c == null)
			return null;
		Index index = null;
		try
		{
			Constructor constructor = c.getConstructor(String.class, File.class);
			index = (Index) constructor.newInstance(name, path);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return index;
	}
}
