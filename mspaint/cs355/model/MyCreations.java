package cs355.model;

import java.util.Stack;

public class MyCreations extends java.util.Observable {
	Stack<MyShape> shapes = new Stack<MyShape>();
	
	public void Push(MyShape s) {
		if (s.equals(null)) return;
		shapes.push(s);
		
		setChanged();
		notifyObservers();
	}
	
	public MyShape Pop() {
		if (shapes.size() <= 0) return null;
		
		setChanged();
		notifyObservers();
		
		return shapes.pop();
	}
	
	public Stack<MyShape> GetShapes() {
		return shapes;
	}
}
