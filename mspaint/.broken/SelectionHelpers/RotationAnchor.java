package cs355.SelectionHelpers;

import java.awt.geom.Point2D;

import cs355.solution.Utility;

public class RotationAnchor extends DrawnSelectionItem {
	// radius
	private double r = 4;
	
	public RotationAnchor(Point2D p) {
		center = p;
	}
	
	@Override
	public boolean Contains(Point2D p) {
		if (Utility.Distance(center, p) <= r) return true;
		return false;
	}
	
	public void SetRadius(double radius) {
		r = radius;
	}
	
	public double GetRadius() {
		return r;
	}
	
}
