package cs355.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;

public class MyCreations extends java.util.Observable {
	ArrayList<MyShape> shapes = new ArrayList<MyShape>();

	public MyShape GetShapeHit(Point clicked, int tolerance) {
		ListIterator<MyShape> li = shapes.listIterator(shapes.size());
		while (li.hasPrevious()) {
			MyShape s = li.previous();
			if (s.Contains(clicked, tolerance)) {
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
