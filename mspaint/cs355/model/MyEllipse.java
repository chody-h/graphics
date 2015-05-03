package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyEllipse extends MyShape{
//	center, height, width, & accessors
	private Point c;
	private Point a;
	private int w;
	private int h;
	
	public MyEllipse(Color color, Point anchor) {
		super(color);
		a = anchor;
		c = anchor;
		w = 0;
		h = 0;
	}
	
	public void Update(Point center, int width, int height) {
		c = center;
		w = width;
		h = height;
	}
	
	public Point GetCenter() {
		return c;
	}
	
	public Point GetAnchor() {
		return a;
	}
	
	public int GetWidth() {
		return w;
	}
	
	public int GetHeight() {
		return h;
	}
}
