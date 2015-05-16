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
	
	public static void ObjectToWorld(Graphics2D g2d, Point2D center, double rotation) {
		AffineTransform objToWorld = new AffineTransform();
		objToWorld.translate(center.getX(), center.getY());
		objToWorld.rotate(rotation);
		g2d.setTransform(objToWorld);
	}
	
	public static void ObjectToWorld(Point2D objCoord, Point2D worldCoord, Point2D center, double rotation) {
		if (worldCoord == null || objCoord == null) return;
		AffineTransform objToWorld = new AffineTransform();
		objToWorld.translate(center.getX(), center.getY());
		objToWorld.rotate(rotation);
		objToWorld.transform(objCoord, worldCoord);
	}
	
	public static void WorldToObject(Graphics2D g2d, Point2D center, double rotation) {
		AffineTransform worldToObj = new AffineTransform();
		worldToObj.rotate(-rotation);
		worldToObj.translate(-center.getX(), -center.getY());
		g2d.setTransform(worldToObj);
	}
	
	public static void WorldToObject(Point2D worldCoord, Point2D objCoord, Point2D center, double rotation) {
		if (worldCoord == null || objCoord == null) return;
		AffineTransform objToWorld = new AffineTransform();
		objToWorld.rotate(-rotation);
		objToWorld.translate(-center.getX(), -center.getY());
		objToWorld.transform(worldCoord, objCoord);
	}
}
