package cs355.model;

import java.awt.Color;
import java.awt.geom.Point2D;

public class MyEllipse extends MyShape{
//	height, width
	private double w;
	private double h;
	
	public MyEllipse(Color color, Point2D center) {
		super(color, center);
		w = 0;
		h = 0;
	}
	
	@Override
	public boolean Contains(Point2D p, double t) {
		double a = Math.pow(p.getX(), 2);
		double b = Math.pow(w/2, 2);
		double c = Math.pow(p.getY(), 2);
		double d = Math.pow(h/2, 2);
		if (a/b + c/d <= 1) return true;
		else return false;
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
