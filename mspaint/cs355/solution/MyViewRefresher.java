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
			Color shapeColor = s.GetColor();
			g2d.setColor(shapeColor);
			if (s instanceof MyLine) {
				Point start = ((MyLine)s).GetStart();
				Point end = ((MyLine)s).GetEnd();
				
				g2d.setStroke(new BasicStroke(2));
				
				int x1 = start.x;
				int y1 = start.y;
				int x2 = end.x;
				int y2 = end.y;
				g2d.drawLine(x1, y1, x2, y2);
			}
			else if (s instanceof MySquare) {
				Point tl = ((MySquare)s).GetTopLeft();
				int len = ((MySquare)s).GetLength();
				g2d.drawRect(tl.x, tl.y, len, len);
			}
		}
//		System.out.printf("Drew %d shapes.\n", shapes.size());
	}

	@Override
	public void update(Observable o, Object arg) {
		GUIFunctions.refresh();
	}

}
