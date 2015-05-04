package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyEllipse extends MyShape{
//	center, height, width, & accessors
	private Point c;
	private int w;
	private int h;
	
	public MyEllipse(Color color, Point center) {
		super(color);
		c = center;
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
	
	public int GetWidth() {
		return w;
	}
	
	public int GetHeight() {
		return h;
	}
}
