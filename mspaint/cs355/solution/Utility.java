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
	
	
	public static AffineTransform ObjectToView(double zoom, Point2D topLeft, Point2D center, double rotation) {
//		object to world
//		world to view
		
		AffineTransform id = new AffineTransform();
		AffineTransform o2w = ObjectToWorld(center, rotation);
		AffineTransform w2v = WorldToView(zoom, topLeft);
		
		id.concatenate(o2w);
		id.concatenate(w2v);
		
		return id;
	}
	
	public static AffineTransform ViewToObject(double zoom, Point2D topLeft, Point2D center, double rotation) {
		// view to world
		// world to object
		
		AffineTransform id = new AffineTransform();
		AffineTransform v2w = ViewToWorld(zoom, topLeft);
		AffineTransform w2o = WorldToObject(center, rotation);
		
		id.concatenate(v2w);
		id.concatenate(w2o);
		
		return id;
	}
	
	
	private static AffineTransform ObjectToWorld(Point2D center, double rotation) {
		
		double sin = Math.sin(rotation);
		double cos = Math.cos(rotation);
		
		AffineTransform objToWorld = new AffineTransform(cos, sin, -sin, cos, center.getX(), center.getY());
		
//		objToWorld.translate(center.getX(), center.getY());
//		objToWorld.rotate(rotation);
		
		return objToWorld;
	}
	
	private static AffineTransform WorldToObject(Point2D center, double rotation) {
		
		double sin = Math.sin(rotation);
		double cos = Math.cos(rotation);
		double m02 = -cos*center.getX()-sin*center.getY();
		double m12 = sin*center.getX()-cos*center.getY();
		
		AffineTransform worldToObj = new AffineTransform(cos, -sin, sin ,cos, m02, m12);
		
//		worldToObj.rotate(-rotation);
//		worldToObj.translate(-center.getX(), -center.getY());
		
		return worldToObj;
	}
	
	private static AffineTransform WorldToView(double zoom, Point2D topLeft) {
		
		AffineTransform worldToView = new AffineTransform(zoom, 0, 0, zoom, -topLeft.getX()*zoom, -topLeft.getY()*zoom);
		return worldToView;
	}
	
	private static AffineTransform ViewToWorld(double zoom, Point2D topLeft) {
		
		AffineTransform viewToWorld = new AffineTransform(1/zoom, 0, 0, 1/zoom, topLeft.getX(), topLeft.getY());
		return viewToWorld;
	}
	
	
//	convert point objects 
	public static Point2D ObjectToView(Point2D objCoord, Point2D center, double rotation, double zoom, Point2D topLeft) {
		
		if (objCoord == null) return null;
		
		AffineTransform id = new AffineTransform();
		AffineTransform worldToView = WorldToView(zoom, topLeft);
		AffineTransform objToWorld = ObjectToWorld(center, rotation);

		id.concatenate(worldToView);
		id.concatenate(objToWorld);

		Point2D viewCoord = new Point2D.Double(0,0);
		id.transform(objCoord, viewCoord);
		
		return viewCoord;
	}
	
	public static Point2D ViewToObject(Point2D viewCoord, Point2D center, double rotation, double zoom, Point2D topLeft) {

		if (viewCoord == null) return null;
		
		AffineTransform id = new AffineTransform();
		AffineTransform viewToWorld = ViewToWorld(zoom, topLeft);
		AffineTransform worldToObj = WorldToObject(center, rotation);

		id.concatenate(viewToWorld);
		id.concatenate(worldToObj);
		
		Point2D objCoord = new Point2D.Double(0,0);
		id.transform(viewCoord, objCoord);
		
		return objCoord;
	}
}
