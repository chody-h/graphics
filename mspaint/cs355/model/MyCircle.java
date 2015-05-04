package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyCircle extends MyShape {
//	center, radius, & accessors
	private Point c;
	private int r;
	
	public MyCircle(Color color, Point center) {
		super(color);
		c = center;
		r = 0;
	}
	
	public void Update(Point center, int radius) {
		c = center;
		r = radius;
	}
	
	public Point GetCenter() {
		return c;
	}
	
	public int GetRadius() {
		return r;
	}
}
