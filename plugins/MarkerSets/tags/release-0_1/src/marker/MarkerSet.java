package marker;

import java.awt.Color;
import java.util.Collections;
import java.util.Vector;

import marker.MarkerSetsPlugin.Event;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.msg.BufferUpdate;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.jedit.visitors.JEditVisitorAdapter;
import org.gjt.sp.util.SyntaxUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MarkerSet {

	private static final String COLOR_ATTR = "color";
	private static final String NAME_ATTR = "name";
	private String name;
	private Vector<FileMarker> markers;
	private Color color;
	
	public MarkerSet(String name) {
		this.name = name;
		color = Color.black;
		markers = new Vector<FileMarker>();
	}
	public MarkerSet(Element node) {
		markers = new Vector<FileMarker>();
		importXml(node);
	}
	public void setColor(Color c) { color = c; }
	public Color getColor() { return color; }
	
	public String getName() { return name; }
	
	// Returns true if the marker was added, false if removed
	public boolean toggle(FileMarker marker) {
		if (markers.contains(marker))
		{
			remove(marker);
			return false;
		}
		add(marker);
		return true;
	}
	
	public void add(FileMarker marker) {
		if (markers.contains(marker))
			return;
		markers.add(marker);
		MarkerSetsPlugin.notifyChange(Event.MARKER_ADDED, marker, this);
		MarkerSetsPlugin.saveState();
		repaintAllTextAreas();
	}
	
	public void remove(FileMarker marker) {
		markers.remove(marker);
		MarkerSetsPlugin.notifyChange(Event.MARKER_REMOVED, marker, this);
		MarkerSetsPlugin.saveState();
		repaintAllTextAreas();
	}
	
	public void handleBufferUpdate(BufferUpdate bu) {
		Object event = bu.getWhat();
		if ((event != BufferUpdate.LOADED) && (event != BufferUpdate.CLOSED))
			return;
		Buffer b = bu.getBuffer();
		Vector<FileMarker> pathMarkers = getMarkersFor(b.getPath());
		for (FileMarker marker: pathMarkers)
		{
			if (event == BufferUpdate.LOADED)
				marker.createPosition(b);
			else
				marker.removePosition();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Vector<FileMarker> getMarkers() {
		Vector<FileMarker> items = new Vector<FileMarker>(markers);
		Collections.sort(items);
		return items;
	}
	
	public FileMarker getMarkerFor(String path, int line)
	{
		for (int i = 0; i < markers.size(); i++)
		{
			FileMarker marker = markers.get(i);
			if (marker.file.equals(path) && marker.getLine() == line)
				return marker;
		}
		return null;
	}
	
	public Vector<FileMarker> getMarkersFor(String path)
	{
		Vector<FileMarker> pathMarkers = new Vector<FileMarker>();
		for (int i = 0; i < markers.size(); i++)
		{
			FileMarker marker = markers.get(i);
			if (marker.file.equals(path))
				pathMarkers.add(marker);
		}
		return pathMarkers;
	}
	
	public void importXml(Element node)
	{
		name = node.getAttribute(NAME_ATTR);
		color = SyntaxUtilities.parseColor(node.getAttribute(COLOR_ATTR),
			Color.black);
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			if (child instanceof Element)
				markers.add(new FileMarker((Element) child));
		}
	}
	public void exportXml(Element parent)
	{
		Document doc = parent.getOwnerDocument();
		Element setNode = doc.createElement("MarkerSet");
		parent.appendChild(setNode);
		setNode.setAttribute(NAME_ATTR, getName());
		setNode.setAttribute(COLOR_ATTR,
			SyntaxUtilities.getColorHexString(color));
		for (int i = 0; i < markers.size(); i++)
			markers.get(i).exportXml(setNode);
	}

	private void repaintAllTextAreas()
	{
		jEdit.visit(new JEditVisitorAdapter() {
			@Override
			public void visit(JEditTextArea textArea) {
				textArea.repaint();
			}
		});
	}
}
