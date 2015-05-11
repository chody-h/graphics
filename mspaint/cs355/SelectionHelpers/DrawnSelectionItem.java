package cs355.SelectionHelpers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public class DrawnSelectionItem {

	protected Color c = new Color(0, 255, 255);	// cyan;
	protected Stroke s = new BasicStroke(1);
	protected double r = 0;
	protected Point2D center = new Point2D.Double(0, 0);
	
	public void SetColor(Color color) {
		c = color;
	}
	
	public Color GetColor() {
		return c;
	}
	
	public void SetStroke(Stroke stroke) {
		s = stroke;
	}
	
	public Stroke GetStroke() {
		return s;
	}
	
	public void SetRotation(double rotation) {
		r = rotation;
	}
	
	public double GetRotation() {
		return r;
	}
	
	public void SetCenter(Point2D center) {
		this.center = center;
	}
	
	public Point2D GetCenter() {
		return center;
	}

	public boolean Contains(Point2D p2) {
		return false;
	}
}
