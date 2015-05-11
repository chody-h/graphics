package cs355.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

import cs355.solution.Utility;

public class MyCircle extends MyShape {
//	radius & accessors
	private int r;
	
	public MyCircle(Color color, Point center) {
		super(color, center);
		r = 0;
	}
	
	@Override
	public boolean Contains(Point2D p, int t) {
		double d = Utility.Distance(p, new Point(0,0));
		if (d < r) return true;
		else return false;
	}

	public void SetRadius(int radius) {
		r = radius;
	}
	
	public int GetRadius() {
		return r;
	}
}
