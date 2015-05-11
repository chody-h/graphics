package cs355.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

import cs355.solution.Utility;

public class MyTriangle extends MyShape {
//	location of each vertex  & accessors
	private Point2D v1;
	private Point2D v2;
	private Point2D v3;

	public MyTriangle(Color color, Point start) {
		// start and center are equivalent when a triangle is created
		super(color, start);
		Point2D zero = new Point2D.Double(0, 0);
		v1 = zero;
		v2 = zero;
		v3 = zero;
	}
	
	public void SetV2(Point2D vertex2) {
		v2 = vertex2;

		// convert vertices to world coords
		Utility.ObjectToWorld(v1, v1, super.GetCenter(), super.GetRotation());
		
		// update the center
		super.p = new Point2D.Double((v1.getX()+v2.getX())/2, (v1.getY()+v2.getY())/2);
		
		// update the verticies
		Utility.WorldToObject(v1, v1, super.GetCenter(), super.GetRotation());
		Utility.WorldToObject(v2, v2, super.GetCenter(), super.GetRotation());
	}
	
	public void SetV3(Point vertex3) {
		v3 = vertex3;

		// convert vertices to world coords
		Utility.ObjectToWorld(v1, v1, super.GetCenter(), super.GetRotation());
		Utility.ObjectToWorld(v2, v2, super.GetCenter(), super.GetRotation());
		
		// update the center
		super.p = new Point2D.Double((v1.getX()+v2.getX()+v3.getX())/3, (v1.getY()+v2.getY()+v3.getY())/3);
		
		// update the verticies
		Utility.WorldToObject(v1, v1, super.GetCenter(), super.GetRotation());
		Utility.WorldToObject(v2, v2, super.GetCenter(), super.GetRotation());
		Utility.WorldToObject(v3, v3, super.GetCenter(), super.GetRotation());
	}
	
	@Override
	public boolean Contains(Point2D p, int t) {
//		TODO: fix this

		double onetotwo = (p.getX() - v1.getX()) * -(v2.getY()-v1.getY()) + (p.getY() - v1.getY()) * (v2.getX()-v1.getX());
		double twotothree = (p.getX() - v2.getX()) * -(v3.getY()-v2.getY()) + (p.getY() - v2.getY()) * (v3.getX()-v2.getX());
		double threetoone = (p.getX() - v3.getX()) * -(v1.getY()-v3.getY()) + (p.getY() - v3.getY()) * (v1.getX()-v3.getX());
		if (onetotwo >= 0 && twotothree >= 0 && threetoone >= 0) return true;
		if (onetotwo <= 0 && twotothree <= 0 && threetoone <= 0) return true;
		return false;
	}
	
	// changes the vertices when center changes
	@Override
	public void SetCenter(Point2D center) {
//		Point2D oldCenter = super.p;
//		double dx = center.getX() - oldCenter.getX();
//		double dy = center.getY() - oldCenter.getY();
//		v1 = new Point2D.Double(v1.getX()+dx, v1.getY()+dy);
//		v2 = new Point2D.Double(v2.getX()+dx, v2.getY()+dy);
//		v3 = new Point2D.Double(v3.getX()+dx, v3.getY()+dy);
		super.p = center;
	}
	
	public Point2D GetVertex1() {
		return v1;
	}
	
	public Point2D GetVertex2() {
		return v2;
	}
	
	public Point2D GetVertex3() {
		return v3;
	}
}
