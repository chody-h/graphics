package cs355.solution;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Observable;
import java.util.Stack;

import cs355.GUIFunctions;
import cs355.model.*;

public class MyViewRefresher implements cs355.ViewRefresher, java.util.Observer {

	private MyCS355Controller contr;
	
	public MyViewRefresher(MyCS355Controller c) {
		contr = c;
		contr.AddObserver(this);
	}

	@Override
	public void refreshView(Graphics2D g2d) {
		Stack<MyShape> shapes = contr.GetShapes();
		for (MyShape s : shapes) {
			if (s instanceof MyLine) {
				Color shapeColor = s.GetColor();
				Point start = ((MyLine)s).GetStart();
				Point end = ((MyLine)s).GetEnd();
				
				g2d.setColor(shapeColor);
				
				int x1 = start.x;
				int y1 = start.y;
				int x2 = end.x;
				int y2 = end.y;
				g2d.drawLine(x1, y1, x2, y2);
			}
		}
		System.out.printf("Drew %d shapes.\n", shapes.size());
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("UPDATING!!");
		GUIFunctions.refresh();
	}

}
