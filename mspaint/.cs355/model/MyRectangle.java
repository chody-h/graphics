package cs355.model;

import java.awt.Color;
import java.awt.geom.Point2D;

public class MyRectangle extends MyShape {
//	height, width
	private double w;
	private double h;
	
	public MyRectangle(Color color, Point2D center) {
		super(color, center);
		w = 0;
		h = 0;
	}
	
	@Override
	public boolean Contains(Point2D p, double t) {
		boolean x = false;
		if (-w/2 < p.getX() && p.getX() < w/2) x = true;
			
		boolean y = false;
		if (-h/2 < p.getY() && p.getY() < h/2) y = true;
		
		return x && y;
	}

	public void SetWidth(double width) {
		w = width;
	}

	public void SetHeight(double height) {
		h = height;
	}
	
	public double GetWidth() {
		return w;
	}
	
	public double GetHeight() {
		return h;
	}
}
