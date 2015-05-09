package cs355.solution;

import java.awt.geom.Point2D;

public class Utility {
	public static double Distance(Point2D a, Point2D b) {
		double x = a.getX()-b.getX();
		double y = a.getY()-b.getY();
		return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
	}
	
	public static double Angle(Point2D a, Point2D b, Point2D c) {
	    double angle1 = Math.atan2(a.getY() - b.getY(), a.getX() - b.getX());
	    double angle2 = Math.atan2(c.getY() - b.getY(), c.getX() - b.getX());

	    return angle1 - angle2; 
	}
}
