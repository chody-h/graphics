package cs355.SelectionHelpers;

import java.awt.geom.Point2D;

import cs355.solution.Utility;

public class SelectionAnchor extends DrawnSelectionItem {
	// radius
	private int r = 4;
	
	public SelectionAnchor(Point2D p) {
		center = p;
	}
	
	@Override
	public boolean Contains(Point2D p) {
		if (Utility.Distance(center, p) <= r) return true;
		return false;
	}

	public void SetRadius(int radius) {
		r = radius;
	}
	
	public int GetRadius() {
		return r;
	}
}
