package sidekick;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import sidekick.*;

/**
 * SourceAsset: extends sidekick.Asset, provides the nodes for a structure tree
 *
 * @author     Martin Raspe
 * @created    Oct 15, 2005
 * @modified   $Date$ by $Author$
 * @version    $Revision$
 */
public class SourceAsset extends Asset {
	private String shortDesc;
	private String longDesc;
	private Icon _icon;
	private int _lineNo;
	private int _type;

	public SourceAsset() {
		super("SourceAsset");
		}

	public SourceAsset(String name, int lineNo, Position start) {
		super(name);
		this.start = start;
		this.end = start;
		this.shortDesc = name;
		this.longDesc = "Line: " + lineNo;
		_lineNo = lineNo;
		}

	public int getLineNo() {
		return _lineNo;
		}
	
	public Icon getIcon() {
		return _icon;
		}
	
	public void setIcon(ImageIcon icon) {
		_icon = icon;
		}
	
	public int get_type() {
		return _type;
		}

	public String getShortString() {
		return shortDesc;
		}

	public String getLongString() {
		return longDesc;
		}

	public void setShort(String shortDesc) {
		this.shortDesc = shortDesc;
		}

	public void setLong(String longDesc) {
		this.longDesc = longDesc;
		}

	public String toString() {
		return shortDesc + " " + longDesc;
		}

}
