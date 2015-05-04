package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MySquare extends MyShape {
//	location of upper left corner and length of a side & accessors
	private Point tl;
	private int l;
	
	public MySquare(Color color, Point topLeft) {
		super(color);
		tl = topLeft;
		l = 0;
	}
	
	public void Update(Point topLeft, int length) {
		tl = topLeft;
		l = length;
	}
	
	public Point GetTopLeft() {
		return tl;
	}
	
	public int GetLength() {
		return l;
	}
}
