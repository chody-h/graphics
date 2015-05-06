package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyTriangle extends MyShape {
//	location of each vertex  & accessors
	private Point v1;
	private Point v2;
	private Point v3;

	public MyTriangle(Color color, Point start) {
		// start and center are equivalent when a triangle is created
		super(color, start);
		v1 = start;
		v2 = start;
		v3 = start;
	}

	private void UpdateCenter() {
		int x = (v1.x + v2.x + v3.x)/3;
		int y = (v1.y + v2.y + v3.y)/3;
		Point center = new Point(x, y);
		SetCenter(center);
	}
	
	public void SetV2(Point vertex2) {
		v2 = vertex2;
	}
	
	public void SetV3(Point vertex3) {
		v3 = vertex3;
		UpdateCenter();
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
