package cs355.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

public class MyLine extends MyShape {
//	two endpoints & accessors
	private Point2D s;
	private Point2D e;
	private double[] normal;
	
	public MyLine(Color color, Point start) {
		// start, end, and center are all equivalent when the line is created.
		super(color, start);
		s = start;
		e = start;
	}
	
	@Override
	public boolean Contains(Point2D p, int t) {
		// TODO: move this paragraph to the class itself
		normal = new double[2];
		normal[1] =  e.getX() - s.getX();
		normal[0] = -e.getY() + s.getY();
 		double normalLength = Math.pow(Math.pow(normal[0], 2) + Math.pow(normal[1], 2), 0.5);
 		normal[0] = normal[0]/normalLength;
 		normal[1] = normal[1]/normalLength;
		double d = Math.abs(s.getX()*normal[0] + s.getY()*normal[1]);
		
		double dist = Math.abs(p.getX()*normal[0] + p.getY()*normal[1]) - d;
		dist = Math.abs(dist);
		
		if (dist <= t) {
			// closest point to p ON the line
			double x = p.getX() - dist*normal[0];
			double y = p.getY() - dist*normal[1];
			if (((s.getX() < x && x < e.getX()) || (e.getX() < x && x < s.getX())) && ((s.getY() < y && y < e.getY()) || (e.getY() < y && y < s.getY()))) {
				return true;
			}
		}
		return false;
	}
	
//	needs to change start and end too
	@Override
	public void SetCenter(Point2D center) {
		Point2D oldCenter = super.p;
		double dx = center.getX() - oldCenter.getX();
		double dy = center.getY() - oldCenter.getY();
		Point2D newStart = new Point2D.Double(s.getX()+dx, s.getY()+dy);
		s = newStart;
		Point2D newEnd = new Point2D.Double(e.getX()+dx, e.getY()+dy);
		e = newEnd;
	}

	public void SetStart(Point start) {
		s = start;
		RecalculateCenter();
	}

	public void SetEnd(Point end) {
		e = end;
		RecalculateCenter();
	}
	
	public Point2D GetStart() {
		return s;
	}
	
	public Point2D GetEnd() {
		return e;
	}
	
	public Point2D GetRelativeStart() {
		Point2D ret = new Point2D.Double(s.getX()-p.getX(), s.getY()-p.getY());
		return ret;
	}
	
	public Point2D GetRelativeEnd() {
		Point2D ret = new Point2D.Double(e.getX()-p.getX(), e.getY()-p.getY());
		return ret;
	}
	
	private void RecalculateCenter() {
		double x = (e.getX()+s.getX())/2;
		double y = (e.getY()+s.getY())/2;
		super.p = new Point2D.Double(x, y);
	}
}
