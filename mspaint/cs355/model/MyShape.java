package cs355.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

// 	generic shape class
public class MyShape {
//	color, center position, rotation angle (in radians)
//	accessor methods for color
	protected Color c;
	protected Point p;
	protected double r;
	
	public MyShape(Color color, Point center) {
		c = color;
		p = center;
		r = 0;
	}
	
	public boolean Contains(Point2D p, int t) {
		return false;
	}

	public void SetColor(Color color) {
		c = color;
	}

	public void SetCenter(Point center) {
		p = center;
	}

	public void SetRotation(double angle) {
		r = angle;
	}
	
	public Color GetColor() {
		return c;
	}

	public Point GetCenter() {
		return p;
	}

	public double GetRotation() {
		return r;
	}
}
