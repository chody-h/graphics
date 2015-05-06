package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyCircle extends MyShape {
//	radius & accessors
	private int r;
	
	public MyCircle(Color color, Point center) {
		super(color, center);
		r = 0;
	}
	
	public void Update(Point center, int radius) {
		SetCenter(center);
		r = radius;
	}

	public void SetRadius(int radius) {
		r = radius;
	}
	
	public int GetRadius() {
		return r;
	}
}
