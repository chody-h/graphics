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
	
	
	// get the actual affine transform objects
	public static AffineTransform ObjectToView(double zoom, Point2D topLeft, Point2D center, double rotation) {
//		world to view
//		object to world
		
		AffineTransform id = new AffineTransform();
		AffineTransform w2v = WorldToView(zoom, topLeft);
		AffineTransform o2w = ObjectToWorld(center, rotation);

		id.concatenate(w2v);
		id.concatenate(o2w);
		
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
		
		return objToWorld;
	}
	
	private static AffineTransform WorldToObject(Point2D center, double rotation) {
		
		double sin = Math.sin(rotation);
		double cos = Math.cos(rotation);
		double m02 = -cos*center.getX()-sin*center.getY();
		double m12 = sin*center.getX()-cos*center.getY();
		
		AffineTransform worldToObj = new AffineTransform(cos, -sin, sin ,cos, m02, m12);
		
		return worldToObj;
	}
	
	// needs to be used to draw lines
	public static AffineTransform WorldToView(double zoom, Point2D topLeft) {
		
		AffineTransform worldToView = new AffineTransform(zoom, 0, 0, zoom, -topLeft.getX()*zoom, -topLeft.getY()*zoom);
		return worldToView;
	}
	
	private static AffineTransform ViewToWorld(double zoom, Point2D topLeft) {
		
		AffineTransform viewToWorld = new AffineTransform(1/zoom, 0, 0, 1/zoom, topLeft.getX(), topLeft.getY());
		return viewToWorld;
	}
	
	
	
	
//	convert point objects directly
	public static Point2D ObjectToView(Point2D objCoord, Point2D center, double rotation, double zoom, Point2D topLeft) {
		
		if (objCoord == null) return null;
		
		AffineTransform o2v = ObjectToView(zoom, topLeft, center, rotation);

		Point2D viewCoord = new Point2D.Double(0,0);
		o2v.transform(objCoord, viewCoord);
		
		return viewCoord;
	}
	
	public static Point2D ViewToObject(Point2D viewCoord, Point2D center, double rotation, double zoom, Point2D topLeft) {

		if (viewCoord == null) return null;
		
		AffineTransform v2o = ViewToObject(zoom, topLeft, center, rotation);
		
		Point2D objCoord = new Point2D.Double(0,0);
		v2o.transform(viewCoord, objCoord);
		
		return objCoord;
	}
	
	public static Point2D ObjectToWorld(Point2D objCoord, Point2D center, double rotation) {
		
		if (objCoord == null) return null;
		
		AffineTransform o2w = ObjectToWorld(center, rotation);
		
		Point2D worldCoord = new Point2D.Double(0,0);
		o2w.transform(objCoord, worldCoord);
		
		return worldCoord;
	}
	
	public static Point2D WorldToObject(Point2D worldCoord, Point2D center, double rotation) {
		
		 if (worldCoord == null) return null;
		 
		 AffineTransform w2o = WorldToObject(center, rotation);
		 
		 Point2D objCoord = new Point2D.Double(0,0);
		 w2o.transform(worldCoord, objCoord);
		 
		 return objCoord;
	}
	
	public static Point2D ViewToWorld(Point2D viewCoord, double zoom, Point2D topLeft) {
		
		if (viewCoord == null) return null;
		
		AffineTransform v2w = ViewToWorld(zoom, topLeft);
		
		Point2D worldCoord = new Point2D.Double(0,0);
		v2w.transform(viewCoord, worldCoord);
		
		return worldCoord;
	}
}
