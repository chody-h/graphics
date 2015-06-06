package cs355.solution;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import cs355.GUIFunctions;
import cs355.Line3D;
import cs355.Point3D;
import cs355.WireFrame;
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

		double zoom = contr.GetZoom();
		float stroke = (float) (1 / zoom);
		g2d.setStroke(new BasicStroke(stroke));
		
		// draw all shapes
		ArrayList<MyShape> shapes = contr.GetShapes();
		for (MyShape s : shapes) {
			
			Color shapeColor = s.GetColor();
			g2d.setColor(shapeColor);
			
			// apply the appropriate transformation
			if (s instanceof MyLine) {
				AffineTransform w2v = Utility.WorldToView(zoom, contr.GetTopLeft());
				g2d.transform(w2v);
			}
			else {
				Point2D c = s.GetCenter();
				double r = s.GetRotation();
				AffineTransform o2v = Utility.ObjectToView(zoom, contr.GetTopLeft(), c, r);
				g2d.transform(o2v);
			}
			
			// draw the shape
			if (s instanceof MyLine) {
				Point2D b = ((MyLine)s).GetStart();
				Point2D e = ((MyLine)s).GetEnd();

//				float stroke = (float) (1 / zoom);
//				g2d.setStroke(new BasicStroke(stroke));
				
				Line2D l = new Line2D.Double(b, e);
				g2d.draw(l);
			}
			else if (s instanceof MySquare) {
				
				double l = ((MySquare)s).GetLength();
				Rectangle2D r = new Rectangle2D.Double(-l/2, -l/2, l, l);
				g2d.fill(r);
			}
			else if (s instanceof MyRectangle) {
				
				double w = ((MyRectangle)s).GetWidth();
				double h = ((MyRectangle)s).GetHeight();
				Rectangle2D r = new Rectangle2D.Double(-w/2, -h/2, w, h);
				g2d.fill(r);
			}
			else if (s instanceof MyCircle) {
				
				double r = ((MyCircle)s).GetRadius();
				Ellipse2D e = new Ellipse2D.Double(-r, -r, r*2, r*2);
				g2d.fill(e);
			}
			else if (s instanceof MyEllipse) {
				
				double w = ((MyEllipse)s).GetWidth();
				double h = ((MyEllipse)s).GetHeight();
				Ellipse2D e = new Ellipse2D.Double(-w/2, -h/2, w, h);
				g2d.fill(e);
			}
			else if (s instanceof MyTriangle) {
				
				Point2D v1 = ((MyTriangle) s).GetVertex1();
				Point2D v2 = ((MyTriangle) s).GetVertex2();
				Point2D v3 = ((MyTriangle) s).GetVertex3();
				int nPoints = 3;
				if (v3.getX() == v1.getX() && v3.getY() == v1.getY()) {
					if (v2.getX() == v1.getX() && v2.getY() == v1.getY())
						nPoints = 1;
					else
						nPoints = 2;
				}
				if (nPoints < 3) {
//					float stroke = (float) (1 / zoom);
//					g2d.setStroke(new BasicStroke(stroke));
					
					g2d.draw(new Line2D.Double(v1, v2));
				}
				else {
					Path2D triangle = new Path2D.Double();
					triangle.moveTo(v1.getX(), v1.getY());
					triangle.lineTo(v2.getX(), v2.getY());
					triangle.lineTo(v3.getX(), v3.getY());
					triangle.lineTo(v1.getX(), v1.getY());
					g2d.fill(triangle);
				}
			}
			
			Utility.ClearTransformation(g2d);
		}
		
		// draw the selection stuff
		MyShape selected = contr.GetSelectedShape();
		if (selected != null) {

			double rotation = selected.GetRotation();
			Point2D center = selected.GetCenter();
			AffineTransform o2v = Utility.ObjectToView(zoom, contr.GetTopLeft(), center, rotation);
			g2d.transform(o2v);
			
			ArrayList<DrawnSelectionItem> handles = contr.GetSelectionHandles();
			if (handles.size() > 0) {
				for (DrawnSelectionItem i : handles) {
					
					g2d.setColor(i.GetColor());
	//				float stroke = (float) (1 / zoom);
	//				g2d.setStroke(new BasicStroke(stroke));
					
					if (i instanceof SelectionAnchor) {
						Point2D p = i.GetCenter();
						double r = ((SelectionAnchor) i).GetRadius()/zoom;
						Ellipse2D.Double anchor = new Ellipse2D.Double(p.getX()-r, p.getY()-r, r*2, r*2);
						g2d.draw(anchor);
					}
					else if (i instanceof RotationAnchor) {
						Point2D p = i.GetCenter();
						double r = ((RotationAnchor) i).GetRadius()/zoom;
						Ellipse2D.Double anchor = new Ellipse2D.Double(p.getX()-r, p.getY()-r, r*2, r*2);
						g2d.draw(anchor);
					}
					else if (i instanceof SelectionOutline) {
						double w = ((SelectionOutline) i).GetWidth();
						double h = ((SelectionOutline) i).GetHeight();
						Point2D tl = new Point2D.Double(-w/2, -h/2);
						if (((SelectionOutline) i).IsOval()) {
							Ellipse2D o = new Ellipse2D.Double(tl.getX(), tl.getY(), w, h);
							g2d.draw(o);
						}
						else {
							Rectangle2D r = new Rectangle2D.Double(tl.getX(), tl.getY(), w, h);
							g2d.draw(r);
						}
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
		
		
//		draw the 3d model
		if (contr.Is3DModelOff()) return;
		// camera transformation
		Point3D loc = contr.GetCameraLocation();
		double rot = contr.GetCameraRotation();
		Matrix3D w2c = new WorldToCamera(loc, rot);
		
		// clip transformation
		double fov = 60 * Math.PI/180;
		double ratio = 1.0;
		double n = 10;
		double f = 1000;
		ClipMatrix clip = new ClipMatrix(fov, ratio, n, f);
		
		// screen transformation
		double width = 500;
		double height = 500;
		AffineTransform screen = new AffineTransform(width/2, 0, 0, -height/2, width/2, height/2);
		
		Utility.ClearTransformation(g2d);
		g2d.setStroke(new BasicStroke(1));
		WireFrame house = contr.GetHouseModel();
		Iterator<Line3D> it = house.getLines();
		while (it.hasNext()) {
			Line3D l = it.next();
			
			// world coordinates
			Point3D s = l.start;
			Point3D e = l.end;
			
			// convert world to camera
			Vector3D s_camera = Matrix3D.MatrixTimesVector(w2c, s);
			Vector3D e_camera = Matrix3D.MatrixTimesVector(w2c, e);
			
			// convert camera to clip space
			Vector3D s_clipspace = Matrix3D.MatrixTimesVector(clip, s_camera);
			Vector3D e_clipspace = Matrix3D.MatrixTimesVector(clip, e_camera);
			// perform clip testing
			if (Matrix3D.ClipTest(s_clipspace, e_clipspace)) {
				// convert clip space to screen space
				Point2D start = new Point2D.Double(s_clipspace.v1/s_clipspace.v4, s_clipspace.v2/s_clipspace.v4);
				Point2D end   = new Point2D.Double(e_clipspace.v1/e_clipspace.v4, e_clipspace.v2/e_clipspace.v4);
				screen.transform(start, start);
				screen.transform(end,   end  );
				
				// draw
				Line2D line = new Line2D.Double(start, end);
				g2d.draw(line);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		GUIFunctions.refresh();
	}

}
