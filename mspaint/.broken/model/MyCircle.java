package cs355.model;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.solution.Utility;

public class MyCircle extends MyShape {
//	radius
	private double r;
	
	public MyCircle(Color color, Point2D center) {
		super(color, center);
		r = 0;
	}
	
	@Override
	public boolean Contains(Point2D p, double t) {
		double d = Utility.Distance(p, new Point2D.Double(0,0));
		if (d < r) return true;
		else return false;
	}

	public void SetRadius(double radius) {
		r = radius;
	}
	
	public double GetRadius() {
		return r;
	}
}
