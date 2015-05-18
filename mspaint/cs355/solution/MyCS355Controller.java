package cs355.solution;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.SelectionHelpers.*;
import cs355.model.*;

public class MyCS355Controller implements cs355.CS355Controller {
	
//	all drawn shapes - this is the model
	private MyCreations shapes = new MyCreations();
	
//	UI state
	private Color currentColor = new Color(255,255,255);
	private int currentButton = BUTTONS.LINE;
	private double zoom = 1;
	private Point2D topLeft = new Point2D.Double(-250,-250);
	
//	number of points the user has drawn of a triangle
	private int numTriangleVertices = 0;
	
//	start point when the user first clicks
	private Point2D anchor;
	
//	selection stuff
	private MyShape selectedShape = null;
	private Color selectedColor = null;
	ArrayList<DrawnSelectionItem> handles = new ArrayList<DrawnSelectionItem>();
	private DrawnSelectionItem selectedAnchor = null;
	
//	static items
	private static int tolerance = 4;
	private static double minZoom = 0.25;
	private static double maxZoom = 4;
	
	public void DrawpadPressed(Point2D start)	 {
		anchor = start;
		MyShape s = null;
		switch (currentButton) {
			case BUTTONS.LINE:
				s = new MyLine(currentColor, start);
				break;
			case BUTTONS.SQUARE:
				s = new MySquare(currentColor, start);
				break;
			case BUTTONS.RECTANGLE:
				s = new MyRectangle(currentColor, start);
				break;
			case BUTTONS.CIRCLE:
				s = new MyCircle(currentColor, start);
				break;
			case BUTTONS.ELLIPSE:
				s = new MyEllipse(currentColor, start);
				break;
			case BUTTONS.TRIANGLE:
				if (numTriangleVertices == 0) {
					s = new MyTriangle(currentColor, start);
					numTriangleVertices = 1;
				}
				else {
					s = shapes.Pop();
					if (numTriangleVertices == 1) {
						((MyTriangle)s).SetV2(start);
						numTriangleVertices = 2;
					}
					else {
						((MyTriangle)s).SetV3(start);
						numTriangleVertices = 0;
					}
				}
				break;
			case BUTTONS.SELECT:
				selectedAnchor = GetHandleHit(start);
				if (selectedAnchor != null) {
					// TODO?
					System.out.println(selectedAnchor.getClass());
				}
				else {
					selectedShape = shapes.GetShapeHit(start, tolerance);
					if (selectedShape != null) {
						selectedColor = selectedShape.GetColor();
						SetSelectionItems();
						GUIFunctions.changeSelectedColor(selectedColor);
						shapes.SomethingChanged();
					}
					else {
						Unselect();
					}
				}
				return;
			default:
				break;
		}
		shapes.Push(s);
	}
	
	public void DrawpadDraggedReleased(Point2D updated) {
		if (currentButton == BUTTONS.SELECT) {
			if (selectedShape == null) return;
			
			if (selectedAnchor != null) {
				if (selectedAnchor instanceof RotationAnchor) {
					double angle = Utility.Angle(anchor, selectedShape.GetCenter(), updated);
					double oldAngle = selectedShape.GetRotation();
					selectedShape.SetRotation(angle+oldAngle);
					anchor = updated;
					SetSelectionItems();
					shapes.SomethingChanged();
				}
				return;
			}
			else {			
				double dx = updated.getX() - anchor.getX();
				double dy = updated.getY() - anchor.getY();
				Point2D oldCenter = selectedShape.GetCenter();
				Point2D newCenter = new Point2D.Double(oldCenter.getX() + dx, oldCenter.getY() + dy);
				selectedShape.SetCenter(newCenter);
				SetSelectionItems();
				shapes.SomethingChanged();
				anchor = updated;
				return;
			}
		}
		
		
		MyShape s = shapes.Pop();
		if (s instanceof MyLine) {
			((MyLine) s).SetEnd(updated);
		}
		else if (s instanceof MySquare) {			
			double lenX = Math.abs(updated.getX()-anchor.getX());
			double lenY = Math.abs(updated.getY()-anchor.getY());
			double length = Math.min(lenX, lenY);
			
			double x = (updated.getX() < anchor.getX()) ? anchor.getX()-length/2 : anchor.getX()+length/2;
			double y = (updated.getY() < anchor.getY()) ? anchor.getY()-length/2 : anchor.getY()+length/2;
			Point2D center = new Point2D.Double(x, y);
			
			s.SetCenter(center);
			((MySquare) s).SetLength((int)length);
		}
		else if (s instanceof MyRectangle) {	
			double w = Math.abs(updated.getX()-anchor.getX());
			double h = Math.abs(updated.getY()-anchor.getY());
			
			double x = (updated.getX() < anchor.getX()) ? anchor.getX()-w/2 : anchor.getX()+w/2;
			double y = (updated.getY() < anchor.getY()) ? anchor.getY()-h/2 : anchor.getY()+h/2;
			Point2D center = new Point2D.Double(x, y);
			
			s.SetCenter(center);
			((MyRectangle) s).SetWidth((int)w);
			((MyRectangle) s).SetHeight((int)h);
		}
		else if (s instanceof MyCircle) {
			double lenX = Math.abs(updated.getX()-anchor.getX());
			double lenY = Math.abs(updated.getY()-anchor.getY());
			double r = Math.min(lenX, lenY) / 2;
			
			double x = (updated.getX() < anchor.getX()) ? anchor.getX()-r : anchor.getX()+r;
			double y = (updated.getY() < anchor.getY()) ? anchor.getY()-r : anchor.getY()+r;
			Point2D center = new Point2D.Double((int)x, (int)y);
			
			s.SetCenter(center);
			((MyCircle) s).SetRadius((int)r);
		}
		else if (s instanceof MyEllipse) {
			double w = Math.abs(updated.getX()-anchor.getX());
			double h = Math.abs(updated.getY()-anchor.getY());
			
			double x = (updated.getX() < anchor.getX()) ? anchor.getX()-w/2 : anchor.getX()+w/2;
			double y = (updated.getY() < anchor.getY()) ? anchor.getY()-h/2 : anchor.getY()+h/2;
			Point2D center = new Point2D.Double(x, y);
			
			s.SetCenter(center);
			((MyEllipse) s).SetWidth((int)w);
			((MyEllipse) s).SetHeight((int)h);
		}
		else if (s instanceof MyTriangle) {
//			pass
//			all logic in "DrawpadReleased()"
		}
		shapes.Push(s);
	}
	
	private void Unselect() {
		selectedShape = null;
		handles.clear();
		GUIFunctions.changeSelectedColor(currentColor);
		shapes.SomethingChanged();
	}
	
	public MyShape GetSelectedShape() {
		return selectedShape;
	}
	
	public ArrayList<MyShape> GetShapes() {
		return shapes.GetShapes();
	}
	
//	stores all selection items relative to the center of the selected object
	public void SetSelectionItems() {
		handles = new ArrayList<DrawnSelectionItem>();
		
		if (selectedShape instanceof MyLine) {
			Point2D s = ((MyLine) selectedShape).GetRelativeStart();
			SelectionAnchor a = new SelectionAnchor(s);
			handles.add(a);
			Point2D e = ((MyLine) selectedShape).GetRelativeEnd();
			a = new SelectionAnchor(e);
			handles.add(a);
		}
		else if (selectedShape instanceof MySquare) {
			double halfLength = ((MySquare) selectedShape).GetLength() / 2;

			Point2D p = new Point2D.Double(-halfLength, -halfLength);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(-halfLength, halfLength);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(halfLength, -halfLength);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(halfLength, halfLength);
			handles.add(new SelectionAnchor(p));
			
			boolean isOval = false;
			SelectionOutline o = new SelectionOutline(halfLength*2, halfLength*2, isOval);
			handles.add(o);
			
			p = new Point2D.Double(0, -2*halfLength);
			handles.add(new RotationAnchor(p));
		}
		else if (selectedShape instanceof MyRectangle) {
			double a = ((MyRectangle) selectedShape).GetWidth()/2;
			double b = ((MyRectangle) selectedShape).GetHeight()/2;

			Point2D p = new Point2D.Double(-a, -b);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(-a, b);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(a, -b);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(a, b);
			handles.add(new SelectionAnchor(p));
			
			boolean isOval = false;
			SelectionOutline o = new SelectionOutline(a*2, b*2, isOval);
			handles.add(o);
			
			p = new Point2D.Double(0, -2*b);
			handles.add(new RotationAnchor(p));
		}
		else if (selectedShape instanceof MyCircle) {
			double r = ((MyCircle) selectedShape).GetRadius();

			Point2D p = new Point2D.Double(-r, -r);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(-r, r);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(r, -r);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(r, r);
			handles.add(new SelectionAnchor(p));
			
			boolean isOval = true;
			SelectionOutline o = new SelectionOutline(r*2-1, r*2-1, isOval);
			handles.add(o);
			
//			rotating a circle is useless
//			p = new Point(0, -2*r);
//			handles.add(new RotationAnchor(p));
		}
		else if (selectedShape instanceof MyEllipse) {
			double a = ((MyEllipse) selectedShape).GetWidth()/2;
			double b = ((MyEllipse) selectedShape).GetHeight()/2;

			Point2D p = new Point2D.Double(-a, -b);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(-a, b);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(a, -b);
			handles.add(new SelectionAnchor(p));
			p = new Point2D.Double(a, b);
			handles.add(new SelectionAnchor(p));
			
			boolean isOval = true;
			SelectionOutline o = new SelectionOutline(a*2-1, b*2-1, isOval);
			handles.add(o);
			
			p = new Point2D.Double(0, -2*b);
			handles.add(new RotationAnchor(p));
		}
		else if (selectedShape instanceof MyTriangle) {
			
			Point2D p1 = ((MyTriangle) selectedShape).GetVertex1();
			handles.add(new SelectionAnchor(p1));
			Point2D p2 = ((MyTriangle) selectedShape).GetVertex2();
			handles.add(new SelectionAnchor(p2));
			Point2D p3 = ((MyTriangle) selectedShape).GetVertex3();
			handles.add(new SelectionAnchor(p3));
			
			SelectionOutlineTriangle o = new SelectionOutlineTriangle(p1, p2, p3);
			handles.add(o);
			
			double xr = 3*(p1.getX())/2;
			double yr = 3*(p1.getY())/2;
			Point2D rotation = new Point2D.Double(xr, yr);
			handles.add(new RotationAnchor(rotation));
		}
	}
	
	public DrawnSelectionItem GetHandleHit(Point2D p) {
		if (selectedShape == null) return null;
		Point2D p2 = new Point2D.Double(0,0);
		Utility.WorldToObject(p, p2, selectedShape.GetCenter(), selectedShape.GetRotation());
		for (DrawnSelectionItem i : handles) {
			if (i.Contains(p2)) {
				return i;
			}
		}
		return null;
	}
	
	public ArrayList<DrawnSelectionItem> GetSelectionHandles() {
		return handles;
	}
	
	public void AddObserver(MyViewRefresher vr) {
		shapes.addObserver(vr);
	}

	@Override
	public void colorButtonHit(Color c) {
		GUIFunctions.changeSelectedColor(c);
		if (selectedShape != null) {
			selectedShape.SetColor(c);
			shapes.SomethingChanged();
		}
		else {
			currentColor = c;
		}
	}

	@Override
	public void triangleButtonHit() {
		currentButton = BUTTONS.TRIANGLE;
		Unselect();
	}

	@Override
	public void squareButtonHit() {
		currentButton = BUTTONS.SQUARE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
		Unselect();
	}

	@Override
	public void rectangleButtonHit() {
		currentButton = BUTTONS.RECTANGLE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
		Unselect();
	}

	@Override
	public void circleButtonHit() {
		currentButton = BUTTONS.CIRCLE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
		Unselect();
	}

	@Override
	public void ellipseButtonHit() {
		currentButton = BUTTONS.ELLIPSE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
		Unselect();
	}

	@Override
	public void lineButtonHit() {
		currentButton = BUTTONS.LINE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
		Unselect();
	}

	@Override
	public void selectButtonHit() {
		currentButton = BUTTONS.SELECT;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
	}

	@Override
	public void zoomInButtonHit() {
		zoom = (zoom*2 > maxZoom) ? zoom : zoom*2;
		GUIFunctions.refresh();
	}

	@Override
	public void zoomOutButtonHit() {
		zoom = (zoom/2 < minZoom) ? zoom : zoom/2;
		GUIFunctions.refresh();
	}

	@Override
	public void hScrollbarChanged(int value) {
		topLeft = new Point2D.Double(value, topLeft.getY());
		GUIFunctions.refresh();
	}

	@Override
	public void vScrollbarChanged(int value) {
		topLeft = new Point2D.Double(topLeft.getX(), value);
		GUIFunctions.refresh();
	}

	@Override
	public void toggle3DModelDisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doEdgeDetection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSharpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doMedianBlur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUniformBlur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doChangeContrast(int contrastAmountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doChangeBrightness(int brightnessAmountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doLoadImage(BufferedImage openImage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleBackgroundDisplay() {
		// TODO Auto-generated method stub
		
	}
	
	private class BUTTONS {
		private static final int LINE = 0;
		private static final int SQUARE = 1;
		private static final int RECTANGLE = 2;
		private static final int CIRCLE = 3;
		private static final int ELLIPSE = 4;
		private static final int TRIANGLE = 5;
		private static final int SELECT = 6;
	}

}
