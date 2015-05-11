package cs355.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

public class MySquare extends MyShape {
//	location of length of a side & accessors
	private int l;
	
	public MySquare(Color color, Point center) {
		super(color, center);
		l = 0;
	}
	
	@Override
	public boolean Contains(Point2D p, int t) {
		boolean x = false;
		if (-(l+1)/2 < p.getX() && p.getX() < (l+1)/2) x = true;
			
		boolean y = false;
		if (-(l+1)/2 < p.getY() && p.getY() < (l+1)/2) y = true;
		
		return x && y;
	}
	
	public void SetLength(int length) {
		l = length;
	}
		
	public int GetLength() {
		return l;
	}
}
