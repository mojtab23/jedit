package infoviewer;

import java.awt.Component;
import java.beans.PropertyChangeListener;

/**
 * Interface supported by all HelpViewer classes.
 * Currently used by @ref infoviewer.InfoViewerPlugin and @ref HelpViewer 
 * 
 * @author ezust
 * @version $id$
 */
public interface HelpViewerInterface 
{
	public void addPropertyChangeListener(PropertyChangeListener l);
	public void dispose();
	public String getBaseURL();
	public Component getComponent();
	public String getShortURL();
	public void gotoURL(String url, boolean addToHistory);
	public void queueTOCReload();
	public void setTitle(String newTitle);
}
