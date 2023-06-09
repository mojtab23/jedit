package columnruler;

import java.awt.*;

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.msg.*;

/**
 * Description of the Class
 *
 * @author    mace
 * @version   $Revision: 1.3 $ modified $Date: 2004-02-09 01:12:36 $ by $Author: bemace $
 */
public class WrapMark extends DynamicMark implements EBComponent {
	private ColumnRuler ruler;
	private Buffer _buffer;

	public WrapMark(Buffer b) {
		super("Wrap Marker");
		setBuffer(b);
		setSize(3);
	}

	public void activate(ColumnRuler ruler) {
		this.ruler = ruler;
		EditBus.addToBus(this);
	}

	public void deactivate() {
		EditBus.removeFromBus(this);
	}

	public void handleMessage(EBMessage msg) {
		if (msg instanceof PropertiesChanged) {
			_column = _buffer.getIntegerProperty("maxLineLen", 0);
			ruler.repaint();
		}
		if (msg instanceof EditPaneUpdate) {
			EditPaneUpdate epu = (EditPaneUpdate) msg;
			if (epu.getWhat().equals(epu.BUFFER_CHANGED) || epu.getWhat().equals(epu.CREATED)) {
				update();
				ruler.repaint();
			}
		}
	}

	public void update() {
		setBuffer(ruler.getTextArea().getBuffer());
	}

	public void setColumn(int col) {
		super.setColumn(col);
		_buffer.setIntegerProperty("maxLineLen", col);
		_buffer.propertiesChanged();
	}

	public void setBuffer(Buffer b) {
		_buffer = b;
		_column = _buffer.getIntegerProperty("maxLineLen", 0);
	}

	public Color getColor() {
		return ruler.getTextArea().getPainter().getWrapGuideColor();
	}

	/**
	 * The wrap guide is provided by jEdit's core, so it never needs to be drawn by the mark.
	 * The wrap mark merely serves as a convenient way to change the wrap column.
	 */
	public boolean isGuideVisible() {
		return false;
	}
}

