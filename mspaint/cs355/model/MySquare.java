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
	
	public void Update(Point center, int length) {
		SetCenter(center);
		l = length;
	}

	public void SetLength(int length) {
		l = length;
	}
		
	public int GetLength() {
		return l;
	}
}
