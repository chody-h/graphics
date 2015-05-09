package cs355.SelectionHelpers;

import java.awt.geom.Point2D;

public class RotationAnchor extends DrawnSelectionItem {
	// location
	private Point2D p;
	// radius
	private int r = 4;
	
	public RotationAnchor(Point2D p) {
		this.p = p;
	}

	public Point2D GetPoint() {
		return p;
	}
	
	public void SetRadius(int radius) {
		r = radius;
	}
	
	public int GetRadius() {
		return r;
	}
	
}
