package cs355.solution;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import cs355.GUIFunctions;
import cs355.SelectionHelpers.*;
import cs355.model.*;

public class MyViewRefresher implements cs355.ViewRefresher, java.util.Observer {

	// anti-aliasing = personal taste. nothing to do with labs.
	private static boolean AA_ON = true;
	private MyCS355Controller contr;
	
	public MyViewRefresher(MyCS355Controller c) {
		contr = c;
		contr.AddObserver(this);
	}

	@Override
	public void refreshView(Graphics2D g2d) {
		if (AA_ON) g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// draw all shapes
		ArrayList<MyShape> shapes = contr.GetShapes();
		for (MyShape s : shapes) {
			
			Color shapeColor = s.GetColor();
			g2d.setColor(shapeColor);
			
			if (s instanceof MyLine) {
				Utility.ClearTransformation(g2d);
			}
			else {
				Point c = s.GetCenter();
				double r = s.GetRotation();
				Utility.ObjectToWorld(g2d, c, r);
			}
			
			if (s instanceof MyLine) {
				Point b = ((MyLine)s).GetStart();
				Point e = ((MyLine)s).GetEnd();
				
				g2d.setStroke(new BasicStroke(1));
				
				int x1 = b.x;
				int y1 = b.y;
				int x2 = e.x;
				int y2 = e.y;
				g2d.drawLine(x1, y1, x2, y2);
			}
			else if (s instanceof MySquare) {
				
				int l = ((MySquare)s).GetLength();
				g2d.fillRect(0-l/2, 0-l/2, l, l);
			}
			else if (s instanceof MyRectangle) {
				
				int w = ((MyRectangle)s).GetWidth();
				int h = ((MyRectangle)s).GetHeight();
				g2d.fillRect(0-w/2, 0-h/2, w, h);
			}
			else if (s instanceof MyCircle) {
				
				int r = ((MyCircle)s).GetRadius();
				g2d.fillOval(0-r, 0-r, r*2, r*2);
			}
			else if (s instanceof MyEllipse) {
				
				int w = ((MyEllipse)s).GetWidth();
				int h = ((MyEllipse)s).GetHeight();
				g2d.fillOval(0-w/2, 0-h/2, w, h);
			}
			else if (s instanceof MyTriangle) {
				
				int[] xPoints = ((MyTriangle) s).GetRelativeXPoints();
				int[] yPoints = ((MyTriangle) s).GetRelativeYPoints();
				int nPoints = 3;
				if (xPoints[2] == xPoints[0] && yPoints[2] == yPoints[0]) {
					if (xPoints[1] == xPoints[0] && yPoints[1] == yPoints[0])
						nPoints = 1;
					else
						nPoints = 2;
				}
				if (nPoints < 3) {
					g2d.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
				}
				else g2d.fillPolygon(xPoints, yPoints, nPoints);
			}
		}
		
		// draw the selection stuff
		MyShape selected = contr.GetSelectedShape();
		if (selected == null) return;

		double rotation = contr.GetSelectedShape().GetRotation();
		Point center = contr.GetSelectedShape().GetCenter();
		Utility.ObjectToWorld(g2d, center, rotation);
		
		ArrayList<DrawnSelectionItem> handles = contr.GetSelectionHandles();
		if (handles.size() > 0) {
			for (DrawnSelectionItem i : handles) {
				
				g2d.setColor(i.GetColor());
				g2d.setStroke(new BasicStroke(1));
				
				if (i instanceof SelectionAnchor) {
					Point2D p = i.GetCenter();
					int r = ((SelectionAnchor) i).GetRadius();
					Ellipse2D.Double anchor = new Ellipse2D.Double(p.getX()-r, p.getY()-r, r*2, r*2);
					g2d.draw(anchor);
				}
				else if (i instanceof RotationAnchor) {
					Point2D p = i.GetCenter();
					int r = ((RotationAnchor) i).GetRadius();
					Ellipse2D.Double anchor = new Ellipse2D.Double(p.getX()-r, p.getY()-r, r*2, r*2);
					g2d.draw(anchor);
				}
				else if (i instanceof SelectionOutline) {
					Point tl = ((SelectionOutline) i).GetTopLeft();
					int w = ((SelectionOutline) i).GetWidth();
					int h = ((SelectionOutline) i).GetHeight();
					if (((SelectionOutline) i).IsOval())
						g2d.drawOval(tl.x, tl.y, w, h);
					else
						g2d.drawRect(tl.x, tl.y, w, h);
				}
				else if (i instanceof SelectionOutlineTriangle) {
					Point2D v1 = ((SelectionOutlineTriangle) i).GetV1();
					Point2D v2 = ((SelectionOutlineTriangle) i).GetV2();
					Point2D v3 = ((SelectionOutlineTriangle) i).GetV3();
					Line2D l1 = new Line2D.Double(v1, v2);
					Line2D l2 = new Line2D.Double(v2, v3);
					Line2D l3 = new Line2D.Double(v3, v1);
					g2d.draw(l1);
					g2d.draw(l2);
					g2d.draw(l3);
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		GUIFunctions.refresh();
	}

}
