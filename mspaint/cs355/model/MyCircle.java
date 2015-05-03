package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyCircle extends MyShape {
//	center, radius, & accessors
	private Point c;
	private Point a;
	private int r;
	
	public MyCircle(Color color, Point anchor) {
		super(color);
		a = anchor;
		c = anchor;
		r = 0;
	}
	
	public void Update(Point center, int radius) {
		c = center;
		r = radius;
	}
	
	public Point GetCenter() {
		return c;
	}
	
	public Point GetAnchor() {
		return a;
	}
	
	public int GetRadius() {
		return r;
	}
	
	public int GetDiameter() {
		return r*2;
	}
}
