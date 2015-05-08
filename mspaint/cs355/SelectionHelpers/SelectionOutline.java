package cs355.SelectionHelpers;

import java.awt.Point;

public class SelectionOutline extends DrawnSelectionItem {
	// top left location
	private Point p;
	// width
	private int w;
	// height
	private int h;
	// T - oval, F - rectangle
	private boolean o;
	
	public SelectionOutline(Point center, int width, int height, boolean isOval) {
		p = new Point(center.x-width/2, center.y-height/2);
		w = width;
		h = height;
		o = isOval;
	}
	
	public Point GetPoint() {
		return p;
	}
	
	public int GetWidth() {
		return w;
	}
	
	public int GetHeight() {
		return h;
	}
	
	public boolean IsOval() {
		return o;
	}
}
