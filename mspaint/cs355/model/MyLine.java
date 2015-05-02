package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyLine extends MyShape {
//	two endpoints & accessors
	private Point start;
	private Point end;
	
	public MyLine(Color color, Point s, Point e) {
		super(color);
		start = s;
		end = e;
	}
	
	public void UpdateEnd(Point e) {
		end = e;
	}
	
	public Point GetStart() {
		return start;
	}
	
	public Point GetEnd() {
		return end;
	}
}
