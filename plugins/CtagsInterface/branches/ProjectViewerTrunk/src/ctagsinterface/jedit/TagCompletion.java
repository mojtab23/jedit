package ctagsinterface.jedit;

import java.awt.Component;
import java.awt.Point;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.CompletionPopup;
import org.gjt.sp.jedit.gui.CompletionPopup.Candidates;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.jedit.textarea.TextAreaPainter;

import superabbrevs.SuperAbbrevs;
import ctagsinterface.db.Query;
import ctagsinterface.db.TagDB;
import ctagsinterface.main.CtagsInterfacePlugin;
import ctagsinterface.main.Tag;

public class TagCompletion {

	private View view;
	private String prefix;
	
	public static void complete(View view, String prefix)
	{
		final TagCompletion completion = new TagCompletion(view,
			prefix);
		final Vector<Tag> tags = completion.getCompletions();
		if (tags == null || tags.isEmpty())
			return;
		if (tags.size() > 1)
		{
			JEditTextArea ta = view.getTextArea();
			int caret = ta.getCaretPosition();
			Point location = ta.offsetToXY(caret - prefix.length());
			TextAreaPainter painter = ta.getPainter();
			location.y += painter.getFontMetrics().getHeight();
			SwingUtilities.convertPointToScreen(location, painter);
			CompletionPopup popup = new CompletionPopup(view,
				location);
			TagCandidates candidates = completion.new TagCandidates(tags);
			popup.reset(candidates, true);
		}
		else
			completion.complete(tags.get(0));
	}
	
	private class TagCandidates implements Candidates
	{
		private Vector<Tag> tags;
		private DefaultListCellRenderer renderer;
		
		@SuppressWarnings("serial")
		public TagCandidates(final Vector<Tag> tags)
		{
			this.tags = tags;
			renderer = new DefaultListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(JList list,
						Object value, int index, boolean isSelected,
						boolean cellHasFocus)
				{
					super.getListCellRendererComponent(list,
						null, index, isSelected, cellHasFocus);
					setText(getCompletionString(tags.get(index)));
					return this;
				}
				
			};
		}
		public void complete(int index)
		{
			TagCompletion.this.complete(tags.get(index));
		}
		public Component getCellRenderer(JList list, int index,
				boolean isSelected, boolean cellHasFocus)
		{
			return renderer.getListCellRendererComponent(list,
				null, index, isSelected, cellHasFocus);
		}
		public String getDescription(int index)
		{
			return null;
		}
		public int getSize()
		{
			return tags.size();
		}
		public boolean isValid()
		{
			return true;
		}
	}
	
	public TagCompletion(View view, String prefix)
	{
		this.view = view;
		this.prefix = prefix;
	}
	
	public String getCompletionString(Tag tag)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(tag.getName());
		String signature = tag.getExtension("signature");
		if (signature != null && signature.length() > 0)
			sb.append(signature + " ");
		StringBuffer details = new StringBuffer();
		String namespace = tag.getNamespace();
		if (namespace != null && namespace.length() > 0)
			details.append(namespace);
		String kind = tag.getKind();
		if (kind != null && kind.length() > 0)
		{
			if (details.length() > 0)
				details.append(" ");
			details.append(tag.getKind());
		}
		if (details.length() > 0)
			sb.append(" (" + details.toString() + ")");
		return sb.toString();
	}
	
	public Vector<Tag> getCompletions() {
		Query q = CtagsInterfacePlugin.getBasicScopedTagQuery(view);
		TagDB db = CtagsInterfacePlugin.getDB();
		q.addCondition(db.field(TagDB.TAGS_TABLE, TagDB.TAGS_NAME) +
			" LIKE " + TagDB.quote(prefix + '%'));
		Vector<Tag> tags = CtagsInterfacePlugin.query(q);
		return tags;
	}
	public String createAbbrev(String signature)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < signature.length(); i++)
		{
			char c = signature.charAt(i);
			switch (c) {
			case ',':
				sb.append("}");
				// fall through
			case '(':
				sb.append(c);
				sb.append("${");
				sb.append(i + 1);
				sb.append(":");
				break;
			case ')':
				sb.append("}");
				// fall through
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}
	public void complete(Tag tag) {
		Buffer b = view.getBuffer();
		String s = tag.getName();
		b.insert(view.getTextArea().getCaretPosition(),
				s.substring(prefix.length()));
		// Check if a parametrized abbreviation is needed
		String sig = tag.getExtension("signature");
		if (sig == null || sig.length() == 0)
			return;
		String abbrev = createAbbrev(sig);
		SuperAbbrevs.expandAbbrev(view, abbrev, null);
	}
	
}
