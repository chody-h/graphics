package cs355.solution;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Utility {
	public static double Distance(Point2D a, Point2D b) {
		double x = a.getX()-b.getX();
		double y = a.getY()-b.getY();
		return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
	}
	
//	a is start, b is pivot, c is end
	public static double Angle(Point2D a, Point2D b, Point2D c) {
	    double angle1 = Math.atan2(a.getY() - b.getY(), a.getX() - b.getX());
	    double angle2 = Math.atan2(c.getY() - b.getY(), c.getX() - b.getX());

	    return angle2 - angle1; 
	}
	
	public static void ClearTransformation(Graphics2D g2d) {
		g2d.setTransform(new AffineTransform());
	}
	
	
	
//	convert graphics objects
	public static void ObjectToWorld(Graphics2D g2d, Point2D center, double rotation) {
		
		double sin = Math.sin(rotation);
		double cos = Math.cos(rotation);
		
		AffineTransform objToWorld = new AffineTransform(cos, sin, -sin, cos, center.getX(), center.getY());
		
//		objToWorld.translate(center.getX(), center.getY());
//		objToWorld.rotate(rotation);
		
		System.out.println("Delete me!");
		g2d.setTransform(objToWorld);
	}
	
	public static void WorldToObject(Graphics2D g2d, Point2D center, double rotation) {
		
		double sin = Math.sin(rotation);
		double cos = Math.cos(rotation);
		double m02 = -cos*center.getX()-sin*center.getY();
		double m12 = sin*center.getX()-cos*center.getY();
		
		AffineTransform worldToObj = new AffineTransform(cos, -sin, sin ,cos, m02, m12);
		
//		worldToObj.rotate(-rotation);
//		worldToObj.translate(-center.getX(), -center.getY());
		
		System.out.println("Delete me!");
		g2d.setTransform(worldToObj);
	}
	
	
//	convert point objects 
	public static void ObjectToWorld(Point2D objCoord, Point2D worldCoord, Point2D center, double rotation) {
		
		if (worldCoord == null || objCoord == null) return;
		
		double sin = Math.sin(rotation);
		double cos = Math.cos(rotation);
		
		AffineTransform objToWorld = new AffineTransform(cos, sin, -sin, cos, center.getX(), center.getY());
		
//		objToWorld.translate(center.getX(), center.getY());
//		objToWorld.rotate(rotation);
		
		System.out.println("Delete me!");
		objToWorld.transform(objCoord, worldCoord);
	}
	
	public static void WorldToObject(Point2D worldCoord, Point2D objCoord, Point2D center, double rotation) {

		if (worldCoord == null || objCoord == null) return;
		
		double sin = Math.sin(rotation);
		double cos = Math.cos(rotation);
		double m02 = -cos*center.getX()-sin*center.getY();
		double m12 = sin*center.getX()-cos*center.getY();
		
		AffineTransform worldToObj = new AffineTransform(cos, -sin, sin ,cos, m02, m12);
		
//		worldToObj.rotate(-rotation);
//		worldToObj.translate(-center.getX(), -center.getY());
		
		System.out.println("Delete me!");
		worldToObj.transform(worldCoord, objCoord);
	}
}
