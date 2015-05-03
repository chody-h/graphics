package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyTriangle extends MyShape {
//	location of each vertex  & accessors
	private Point v1;
	private Point v2;
	private Point v3;
	private int numPoints;

	public MyTriangle(Color color, Point start) {
		super(color);
		v1 = start;
		v2 = start;
		v3 = start;
		numPoints = 1;
	}
	
	public void UpdateV2(Point vertex2) {
		v2 = vertex2;
		numPoints = 2;
	}
	
	public void UpdateV3(Point vertex3) {
		v3 = vertex3;
		numPoints = 3;
	}
	
	public Point GetVertex1() {
		return v1;
	}
	
	public Point GetVertex2() {
		return v2;
	}
	
	public Point GetVertex3() {
		return v3;
	}
	
	public int GetNumPoints() {
		return numPoints;
	}
	
	public int[] GetXPoints() {
		int[] x = new int[3];
		x[0] = v1.x;
		x[1] = v2.x;
		x[2] = v3.x;
		return x;
	}
	
	public int[] GetYPoints() {
		int[] y = new int[3];
		y[0] = v1.y;
		y[1] = v2.y;
		y[2] = v3.y;
		return y;
	}
}
