package gatchan.jedit.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;
import org.gjt.sp.jedit.io.VFSFile;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.util.IOUtilities;
import org.gjt.sp.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LineIndexImpl extends IndexImpl
{
	public LineIndexImpl(String name, File path)
	{
		super(name, path);
	}

	protected void addDocument(VFSFile file, Object session)
	{
		Log.log(Log.DEBUG, this, "Index:"+getName() + " add " + file);
		BufferedReader reader = null;
		try
		{
			writer.deleteDocuments(new Term("_path", file.getPath()));
			LucenePlugin.CENTRAL.removeFile(file.getPath(), getName());
			reader = new BufferedReader(new InputStreamReader(
				file.getVFS()._createInputStream(session, file.getPath(), false,
				jEdit.getActiveView())));
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null)
			{
				Document doc = new Document();
				doc.add(new Field("path", file.getPath(), Field.Store.NO, Field.Index.ANALYZED));
				doc.add(new Field("_path", file.getPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				i++;
				doc.add(new Field("line", String.valueOf(i), Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("content", line, Field.Store.YES, Field.Index.ANALYZED));
				writer.addDocument(doc);
			}
			LucenePlugin.CENTRAL.addFile(file.getPath(), getName());
		}
		catch (IOException e)
		{
			Log.log(Log.ERROR, this, "Unable to read file " + path, e);
		}
		finally
		{
			IOUtilities.closeQuietly(reader);
		}
	}

	@Override
	protected Result getResultInstance()
	{
		return new LineResult();
	}
}
