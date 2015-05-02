package cs355.model;

import java.util.ArrayList;
import java.util.Stack;

public class MyCreations extends java.util.Observable {
	Stack<MyShape> shapes = new Stack<MyShape>();
	
	public void Push(MyShape s) {
		if (s.equals(null)) return;
		shapes.push(s);
	}
	
	public MyShape Pop() {
		return shapes.pop();
	}
	
	public Stack<MyShape> GetShapes() {
		return shapes;
	}
}
