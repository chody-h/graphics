package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MySquare extends MyShape {
//	location of length of a side & accessors
	private int l;
	
	public MySquare(Color color, Point center) {
		super(color, center);
		l = 0;
	}
	
	@Override
	public boolean Contains(Point p, int t) {
		Point center = super.p;

		boolean x = false;
		if (center.x-(l+1)/2 < p.x && p.x < center.x+(l+1)/2) x = true;
			
		boolean y = false;
		if (center.y-(l+1)/2 < p.y && p.y < center.y+(l+1)/2) y = true;
		
		return x && y;
	}
	
	public void SetLength(int length) {
		l = length;
	}
		
	public int GetLength() {
		return l;
	}
}
