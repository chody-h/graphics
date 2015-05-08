package cs355.model;

import java.awt.Color;
import java.awt.Point;

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
	public boolean Contains(Point p, int t) {
		Point center = super.p;

		boolean x = false;
		if (center.x-w/2 < p.x && p.x < center.x+w/2) x = true;
			
		boolean y = false;
		if (center.y-h/2 < p.y && p.y < center.y+h/2) y = true;
		
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
