package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyRectangle extends MyShape {
//	location of upper left corner, height, width, & accessors
	private Point tl;
	private int w;
	private int h;
	
	public MyRectangle(Color color, Point topLeft) {
		super(color);
		tl = topLeft;
		w = 0;
		h = 0;
	}
	
	public void Update(Point topLeft, int width, int height) {
		tl = topLeft;
		w = width;
		h = height;
	}
	
	public Point GetTopLeft() {
		return tl;
	}
	
	public int GetWidth() {
		return w;
	}
	
	public int GetHeight() {
		return h;
	}
}
