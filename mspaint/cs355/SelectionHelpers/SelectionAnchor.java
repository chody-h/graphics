package cs355.SelectionHelpers;

import java.awt.Point;
import java.awt.geom.Point2D;

import cs355.solution.Utility;

public class SelectionAnchor extends DrawnSelectionItem {
	// location
	private Point2D p;
	// radius
	private int r = 4;
	
	public SelectionAnchor(Point2D p) {
		this.p = p;
	}
	
	public boolean Contains(Point p) {
		if (Utility.Distance(this.p, p) <= r) return true;
		return false;
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
