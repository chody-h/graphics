package cs355.model;

import java.awt.Color;
import java.awt.geom.Point2D;

public class MyLine extends MyShape {
//	two endpoints & accessors
	private Point2D s;
	private Point2D e;
	private double[] normal;
	// p*n=d
	private double d;
	
	public MyLine(Color color, Point2D start) {
		// start, end, and center are all equivalent when the line is created.
		super(color, start);
		s = start;
		e = start;
	}
	
	@Override
	public boolean Contains(Point2D p, double t) {
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
	
//	needs to change start and end too, because I store the end points in object coordinates
	@Override
	public void SetCenter(Point2D center) {
		Point2D oldCenter = super.GetCenter();
		double dx = center.getX() - oldCenter.getX();
		double dy = center.getY() - oldCenter.getY();
		Point2D newStart = new Point2D.Double(s.getX()+dx, s.getY()+dy);
		s = newStart;
		Point2D newEnd = new Point2D.Double(e.getX()+dx, e.getY()+dy);
		e = newEnd;
	}

	public void SetStart(Point2D start) {
		s = start;
		RecalculateCenter();
		RecalculateNormal();
	}

	public void SetEnd(Point2D end) {
		e = end;
		RecalculateCenter();
		RecalculateNormal();
	}
	
	public Point2D GetStart() {
		return s;
	}
	
	public Point2D GetEnd() {
		return e;
	}
	
	public Point2D GetRelativeStart() {
		Point2D ret = new Point2D.Double(s.getX()-super.GetCenter().getX(), s.getY()-super.GetCenter().getY());
		return ret;
	}
	
	public Point2D GetRelativeEnd() {
		Point2D ret = new Point2D.Double(e.getX()-super.GetCenter().getX(), e.getY()-super.GetCenter().getY());
		return ret;
	}
	
	private void RecalculateCenter() {
		double x = (e.getX()+s.getX())/2;
		double y = (e.getY()+s.getY())/2;
		super.SetCenter(new Point2D.Double(x, y));
	}
	
	private void RecalculateNormal() {
		normal = new double[2];
		normal[1] =  e.getX() - s.getX();
		normal[0] = -e.getY() + s.getY();
 		double normalLength = Math.pow(Math.pow(normal[0], 2) + Math.pow(normal[1], 2), 0.5);
 		normal[0] = normal[0]/normalLength;
 		normal[1] = normal[1]/normalLength;
		d = Math.abs(s.getX()*normal[0] + s.getY()*normal[1]);
	}
}
