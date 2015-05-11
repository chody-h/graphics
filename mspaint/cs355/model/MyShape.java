package cs355.model;

import java.awt.Color;
import java.awt.geom.Point2D;

// 	generic shape class
public class MyShape {
//	color, center position, rotation angle (in radians)
	protected Color col;
	protected Point2D c;
	protected double r;
	
	public MyShape(Color color, Point2D center) {
		col = color;
		c = center;
		r = 0;
	}
	
	// handled by extension classes
	public boolean Contains(Point2D p, int t) {
		return false;
	}

	public void SetColor(Color color) {
		col = color;
	}

	public void SetCenter(Point2D center) {
		c = center;
	}

	public void SetRotation(double angle) {
		r = angle;
	}
	
	public Color GetColor() {
		return col;
	}

	public Point2D GetCenter() {
		return c;
	}

	public double GetRotation() {
		return r;
	}
}
