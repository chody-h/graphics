package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MySquare extends MyShape {
//	location of upper left corner and length of a side & accessors
	private Point start;
	private int length;
	
	public MySquare(Color color, Point s) {
		super(color);
		start = s;
		length = 0;
	}
}
