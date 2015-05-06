package cs355.model;

import java.awt.Color;
import java.awt.Point;

// 	generic shape class
public class MyShape {
//	color, center position, rotation angle (in radians)
//	accessor methods for color
	private Color c;
	private Point p;
	private double r;
	
	public MyShape(Color color, Point center) {
		c = color;
		p = center;
		r = 0;
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
