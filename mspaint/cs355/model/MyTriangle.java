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
	
	@Override
	public boolean Contains(Point p, int t) {
//		TODO: fix this
//		double alpha = ((v2.y-v3.y)*(p.x-v3.x)+(v3.x-v2.x)*(p.y-v3.y))/((v2.y-v3.y)*(v1.x-v3.x)+(v3.x-v2.x)*(v1.y-v3.y));
//		double beta = ((v3.y-v1.y)*(p.x-v3.x)+(v1.x-v3.x)*(p.y-v3.y))/((v2.y-v3.y)*(v1.x-v3.x)+(v3.x-v2.x)*(v1.y-v3.y));
//		double gamma = 1 - alpha - beta;
//		if (alpha >= 0 && beta >= 0 && gamma >= 0) return true;
//		else return false;

		int onetotwo = (p.x - v1.x) * -(v2.y-v1.y) + (p.y - v1.y) * (v2.x-v1.x);
		int twotothree = (p.x - v2.x) * -(v3.y-v2.y) + (p.y - v2.y) * (v3.x-v2.x);
		int threetoone = (p.x - v3.x) * -(v1.y-v3.y) + (p.y - v3.y) * (v1.x-v3.x);
		if (onetotwo >= 0 && twotothree >= 0 && threetoone >= 0) return true;
		if (onetotwo <= 0 && twotothree <= 0 && threetoone <= 0) return true;
		return false;
	}
	
	public void SetV2(Point vertex2) {
		v2 = vertex2;
	}
	
	public void SetV3(Point vertex3) {
		v3 = vertex3;
		UpdateCenter();
	}
	
	// changes the vertices when center changes
	@Override
	public void SetCenter(Point center) {
		Point oldCenter = super.p;
		int dx = center.x - oldCenter.x;
		int dy = center.y - oldCenter.y;
		v1 = new Point(v1.x+dx, v1.y+dy);
		v2 = new Point(v2.x+dx, v2.y+dy);
		v3 = new Point(v3.x+dx, v3.y+dy);
		super.p = center;
	}

	// changes the center when verticies change
	private void UpdateCenter() {
		int x = (v1.x + v2.x + v3.x)/3;
		int y = (v1.y + v2.y + v3.y)/3;
		super.p = new Point(x, y);
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
	
	public int[] GetRelativeXPoints() {
		int[] x = new int[3];
		x[0] = p.x - v1.x;
		x[1] = p.x - v2.x;
		x[2] = p.x - v3.x;
		return x;
	}
	
	public int[] GetRelativeYPoints() {
		int[] y = new int[3];
		y[0] = p.x - v1.y;
		y[1] = p.x - v2.y;
		y[2] = p.x - v3.y;
		return y;
	}
}
