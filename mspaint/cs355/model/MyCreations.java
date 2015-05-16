package cs355.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.ListIterator;

import cs355.solution.Utility;

public class MyCreations extends java.util.Observable {
	ArrayList<MyShape> shapes = new ArrayList<MyShape>();

	public MyShape GetShapeHit(Point2D worldCoords, int tolerance) {
		ListIterator<MyShape> li = shapes.listIterator(shapes.size());
		while (li.hasPrevious()) {
			MyShape s = li.previous();
			Point2D coord = new Point2D.Double(0, 0);
			// if it's a line, keep coord in world coordinates
			if (s instanceof MyLine) coord = new Point2D.Double(worldCoords.getX(), worldCoords.getY());
			// otherwise, convert it to object coordinates
			else Utility.WorldToObject(worldCoords, coord, s.GetCenter(), s.GetRotation());
			if (s.Contains(coord, tolerance)) {
				return s;
			}
		}
		
		return null;
	}
	
	public void Push(MyShape s) {
		if (s.equals(null)) return;
		shapes.add(s);
		
		SomethingChanged();
	}
	
	public MyShape Pop() {
		if (shapes.size() <= 0) return null;
		
		SomethingChanged();
		
		return shapes.remove(shapes.size()-1);
	}
	
	public ArrayList<MyShape> GetShapes() {
		return shapes;
	}
	
	public void SomethingChanged() {
		setChanged();
		notifyObservers();
	}
}
