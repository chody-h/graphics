package cs355.SelectionHelpers;

import java.awt.Point;


public class SelectionOutlineTriangle extends DrawnSelectionItem {
	
	private Point v1;
	private Point v2;
	private Point v3;

	public SelectionOutlineTriangle(Point p1, Point p2, Point p3) {
		v1 = p1;
		v2 = p2;
		v3 = p3;
	}
	
	public Point GetV1() {
		return v1;
	}
	
	public Point GetV2() {
		return v2;
	}
	
	public Point GetV3() {
		return v3;
	}
}
