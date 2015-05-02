package cs355.solution;

import java.awt.Graphics2D;
import java.util.Stack;

import cs355.model.MyShape;

public class MyViewRefresher implements cs355.ViewRefresher {

	private MyCS355Controller contr;
	
	public MyViewRefresher(MyCS355Controller c) {
		contr = c;
	}

	@Override
	public void refreshView(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Stack<MyShape> shapes = contr.GetShapes();
		for (MyShape s : shapes) {
			// TODO draw the shape on the canvas??
		}
	}

}
