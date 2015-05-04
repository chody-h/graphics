package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyLine extends MyShape {
//	two endpoints & accessors
	private Point s;
	private Point e;
	
	public MyLine(Color color, Point start) {
		super(color);
		s = start;
		e = start;
	}
	
	public void Update(Point end) {
		e = end;
	}
	
	public Point GetStart() {
		return s;
	}
	
	public Point GetEnd() {
		return e;
	}
}
