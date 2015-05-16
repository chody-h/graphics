package cs355.model;

import java.awt.Color;
import java.awt.geom.Point2D;

public class MySquare extends MyShape {
//	length of a side
	private double l;
	
	public MySquare(Color color, Point2D center) {
		super(color, center);
		l = 0;
	}
	
	@Override
	public boolean Contains(Point2D p, double t) {
		boolean x = false;
		if (-(l+1)/2 < p.getX() && p.getX() < (l+1)/2) x = true;
			
		boolean y = false;
		if (-(l+1)/2 < p.getY() && p.getY() < (l+1)/2) y = true;
		
		return x && y;
	}
	
	public void SetLength(double length) {
		l = length;
	}
		
	public double GetLength() {
		return l;
	}
}
