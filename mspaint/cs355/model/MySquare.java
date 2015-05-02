package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MySquare extends MyShape {
//	location of upper left corner and length of a side & accessors
	private Point anchor;
	private Point topLeft;
	private int length;
	
	public MySquare(Color color, Point a) {
		super(color);
		anchor = a;
		topLeft = a;
		length = 0;
	}
	
	public void Update(Point TL, int len) {
		topLeft = TL;
		length = len;
	}
	
	public Point GetAnchor() {
		return anchor;
	}
	
	public Point GetTopLeft() {
		return topLeft;
	}
	
	public int GetLength() {
		return length;
	}
}
