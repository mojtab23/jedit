package columnruler;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.geom.*;

import org.gjt.sp.jedit.*;

/**
 * Description of the Class
 *
 * @author     mace
 * @created    June 8, 2003
 * @modified   $Date: 2004-02-13 21:16:42 $ by $Author: bemace $
 * @version    $Revision: 1.10 $
 */
public class Mark implements Transferable {
	static final DataFlavor MARK_FLAVOR = new DataFlavor(Mark.class,"ColumnRuler.Mark");

	private String _name;
	private Color _color;
	protected int _column = 0;
	private int size = 1;
	private boolean _visible = true;
	protected boolean guide = false;
	protected String property = null;

	public Mark(String name) {
		_name = name;
	}

	/**
	 * Creates a mark with the given name, loading the rest of the properties using the given property prefix.
	 */
	public Mark(String name, String property) {
		_name = name;
		this.property = property;
		_column = jEdit.getIntegerProperty(property+".column",0);
		_color = jEdit.getColorProperty(property+".color",Color.WHITE);
		guide = jEdit.getBooleanProperty(property+".guide",true);
		size = jEdit.getIntegerProperty(property+".size",1);
	}
	
	public Mark(String name, Color c) {
		this(name);
		_color = c;
	}

	/**
	 * Called when this mark is added to the ruler.
	 */
	public void activate(ColumnRuler ruler) {}

	/**
	 * Called when this mark is removed from the ruler.
	 */
	public void deactivate() {}

	/**
	 * Called by the ruler in response to events which will require marks to update themselves.
	 * This should prevent most marks from needing to implement EBComponent.
	 */
	public void update() {}

	/**
	 * Subclasses can override this to draw fancier guides.
	 */
	public void drawGuide(Graphics2D gfx, ColumnRuler ruler) {
		int hScroll = ruler.getTextArea().getHorizontalOffset();
		double x = getColumn()*ruler.charWidth + hScroll;
		Line2D guide;
		guide = new Line2D.Double(x,0,x,ruler.getTextArea().getHeight());
		gfx.setColor(getColor());
		gfx.draw(guide);
	}

	//{{{ Transferable impl
	public Object getTransferData(DataFlavor flavor) {
		return this;
	}

	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] df = new DataFlavor[1];
		df[0] = MARK_FLAVOR;
		return df;
	}

	public boolean isDataFlavorSupported(DataFlavor f) {
		if (f.getRepresentationClass().equals(Mark.class)) {
			return true;
		}
		return false;
	}
	//}}}

	//{{{ Accessors + Mutators

	//{{{ setColumn()
	/**
	 * Moves this mark to the given column.
	 */
	public void setColumn(int col) {
		_column = col;
		if (getProperty() != null)
			jEdit.setIntegerProperty(getProperty()+".column",col);
		if (isGuideVisible())
			jEdit.getActiveView().getTextArea().repaint();
	} //}}}

	/**
	 * Shows/Hides this mark.
	 */
	public void setVisible(boolean b) {
		_visible = b;
	}

	/**
	 * Sets the width of the mark.
	 */
	public void setSize(int s) {
		size = s;
	}

	public void setColor(Color c) {
		_color = c;
	}
	
	//{{{ setGuideVisible()
	/**
	 * Turns guide drawing of/off.
	 */
	public void setGuideVisible(boolean b) {
		guide = b;
		if (property != null) {
			jEdit.setBooleanProperty(property+".guide",b);
		}
		jEdit.getActiveView().getTextArea().repaint();
	} //}}}

	/**
	 * Sets the name of the property used to store this mark.
	 */
	public void setProperty(String propName) {
		property = propName;
	}
	
	/**
	 * Gets the name of this marker, used in tooltips.
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Gets the color to use for the mark and its guide.
	 */
	public Color getColor() {
		return _color;
	}

	/**
	 * Gets the current column of this mark.
	 */
	public int getColumn() {
		return _column;
	}

	/**
	 * Gets the width of this mark.
	 */
	public int getSize() {
		return size;
	}
	
	public String getProperty() {
		return property;
	}

	/**
	 * Returns true if the mark is currently visible.
	 */
	public boolean isVisible() {
		return _visible;
	}

	/**
	 * Returns true if the guide for this mark is visible.
	 */
	public boolean isGuideVisible() {
		if (!isVisible())
			return false;
		
		return guide;
	}
	//}}}

}

