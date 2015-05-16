package cs355.SelectionHelpers;

import java.awt.geom.Point2D;

public class SelectionOutlineTriangle extends DrawnSelectionItem {
	
	private Point2D v1;
	private Point2D v2;
	private Point2D v3;

	public SelectionOutlineTriangle(Point2D p1, Point2D p2, Point2D p3) {
		v1 = p1;
		v2 = p2;
		v3 = p3;
	}
	
	public Point2D GetV1() {
		return v1;
	}
	
	public Point2D GetV2() {
		return v2;
	}
	
	public Point2D GetV3() {
		return v3;
	}
}
