package cs355.SelectionHelpers;

import java.awt.Point;

public class SelectionAnchor extends DrawnSelectionItem {
	// location
	private Point p;
	// radius
	private int r = 4;
	
	public SelectionAnchor(Point p) {
		this.p = p;
	}
	
	public Point GetPoint() {
		return p;
	}

	public void SetRadius(int radius) {
		r = radius;
	}
	
	public int GetRadius() {
		return r;
	}
}
