package cs355.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

public class MyRectangle extends MyShape {
//	location of height, width, & accessors
	private int w;
	private int h;
	
	public MyRectangle(Color color, Point center) {
		super(color, center);
		w = 0;
		h = 0;
	}
	
	@Override
	public boolean Contains(Point2D p, int t) {
		boolean x = false;
		if (-w/2 < p.getX() && p.getX() < w/2) x = true;
			
		boolean y = false;
		if (-h/2 < p.getY() && p.getY() < h/2) y = true;
		
		return x && y;
	}

	public void SetWidth(int width) {
		w = width;
	}

	public void SetHeight(int height) {
		h = height;
	}
	
	public int GetWidth() {
		return w;
	}
	
	public int GetHeight() {
		return h;
	}
}
