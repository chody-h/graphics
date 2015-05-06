package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyLine extends MyShape {
//	two endpoints & accessors
	private Point s;
	private Point e;
	
	public MyLine(Color color, Point start) {
		// start, end, and center are all equivalent when the line is created.
		super(color, start);
		s = start;
		e = start;
	}
	
	public void Update(Point end) {
		int avgX = (end.x - s.x)/2;
		int avgY = (end.y - s.y)/2;
		Point center = new Point(avgX, avgY);
		SetCenter(center);

		e = end;
	}

	public void SetStart(Point start) {
		s = start;
	}

	public void SetEnd(Point end) {
		e = end;
	}
	
	public Point GetStart() {
		return s;
	}
	
	public Point GetEnd() {
		return e;
	}
}
