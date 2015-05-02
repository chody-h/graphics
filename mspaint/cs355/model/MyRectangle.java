package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyRectangle extends MyShape {
//	location of upper left corner, height, width, & accessors
	private Point anchor;
	private Point topLeft;
	private int w;
	private int h;
	
	public MyRectangle(Color color, Point a) {
		super(color);
		anchor = a;
		topLeft = a;
		w = 0;
		h = 0;
	}
	
	public void Update(Point TL, int width, int height) {
		topLeft = TL;
		w = width;
		h = height;
	}
	
	public Point GetAnchor() {
		return anchor;
	}
	
	public Point GetTopLeft() {
		return topLeft;
	}
	
	public int GetWidth() {
		return w;
	}
	
	public int GetHeight() {
		return h;
	}
}
