package cs355.solution;

import java.awt.Point;

public class Utility {
	public static double Distance(Point a, Point b) {
		double x = Math.abs(a.x-b.x);
		double y = Math.abs(a.y-b.y);
		return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
	}
}
