package cs355.solution;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.HouseModel;
import cs355.Point3D;
import cs355.WireFrame;
import cs355.SelectionHelpers.*;
import cs355.model.*;

public class MyCS355Controller implements cs355.CS355Controller {
	
//	all drawn shapes - this is the model
	private MyCreations shapes = new MyCreations();
	
//	UI state
	private Color currentColor = new Color(255,255,255);
	private int currentButton = BUTTONS.LINE;
	private double zoom = 1;
	private Point2D topLeft = new Point2D.Double(0,0);
	private boolean updatingScrollBars = false;
	
//	3D state
	private WireFrame model = new HouseModel();
	private boolean model3d = false;
	private Point3D home = new Point3D(0, 6, 20);
	private float rot_home = 0;
	private Point3D myLocation = new Point3D(home.x, home.y, home.z);
	private float rot = rot_home;
	private float speed = 0.3f;
	
//	number of points the user has drawn of a triangle
	private int numTriangleVertices = 0;
	
//	start point when the user first clicks
	private Point2D anchor;
	
//	selection stuff
	private MyShape selectedShape = null;
	private Color selectedColor = null;
	private ArrayList<DrawnSelectionItem> handles = new ArrayList<DrawnSelectionItem>();
	private DrawnSelectionItem selectedAnchor = null;
	
//	static items
	private static int tolerance = 4;
	private static double minZoom = 0.25;
	private static double maxZoom = 4;
	
	public void DrawpadPressed(Point2D start)	 {
		start = Utility.ViewToWorld(start, zoom, topLeft);
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
					selectedShape = shapes.GetShapeHit(start, tolerance/zoom);
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
		updated = Utility.ViewToWorld(updated, zoom, topLeft);
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
			((MySquare) s).SetLength(length);
		}
		else if (s instanceof MyRectangle) {	
			double w = Math.abs(updated.getX()-anchor.getX());
			double h = Math.abs(updated.getY()-anchor.getY());
			
			double x = (updated.getX() < anchor.getX()) ? anchor.getX()-w/2 : anchor.getX()+w/2;
			double y = (updated.getY() < anchor.getY()) ? anchor.getY()-h/2 : anchor.getY()+h/2;
			Point2D center = new Point2D.Double(x, y);
			
			s.SetCenter(center);
			((MyRectangle) s).SetWidth(w);
			((MyRectangle) s).SetHeight(h);
		}
		else if (s instanceof MyCircle) {
			double lenX = Math.abs(updated.getX()-anchor.getX());
			double lenY = Math.abs(updated.getY()-anchor.getY());
			double r = Math.min(lenX, lenY) / 2;
			
			double x = (updated.getX() < anchor.getX()) ? anchor.getX()-r : anchor.getX()+r;
			double y = (updated.getY() < anchor.getY()) ? anchor.getY()-r : anchor.getY()+r;
			Point2D center = new Point2D.Double(x, y);
			
			s.SetCenter(center);
			((MyCircle) s).SetRadius(r);
		}
		else if (s instanceof MyEllipse) {
			double w = Math.abs(updated.getX()-anchor.getX());
			double h = Math.abs(updated.getY()-anchor.getY());
			
			double x = (updated.getX() < anchor.getX()) ? anchor.getX()-w/2 : anchor.getX()+w/2;
			double y = (updated.getY() < anchor.getY()) ? anchor.getY()-h/2 : anchor.getY()+h/2;
			Point2D center = new Point2D.Double(x, y);
			
			s.SetCenter(center);
			((MyEllipse) s).SetWidth(w);
			((MyEllipse) s).SetHeight(h);
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
	
	public WireFrame GetHouseModel() {
		return model;
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
		Point2D p2 = Utility.WorldToObject(p, selectedShape.GetCenter(), selectedShape.GetRotation());
		for (DrawnSelectionItem i : handles) {
			if (i.Contains(p2, zoom)) {
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
		updatingScrollBars = true;
			Point2D oldCenter = CalcViewCenter();
			zoom = (zoom*2 > maxZoom) ? zoom : zoom*2;
			topLeft = CalcViewTopLeft(oldCenter);
			ZoomChanged();
		updatingScrollBars = false;
		GUIFunctions.refresh();
	}

	@Override
	public void zoomOutButtonHit() {
		updatingScrollBars = true;
			Point2D oldCenter = CalcViewCenter();
			zoom = (zoom/2 < minZoom) ? zoom : zoom/2;
			topLeft = CalcViewTopLeft(oldCenter);
			ZoomChanged();
		updatingScrollBars = false;
		GUIFunctions.refresh();
	}

	@Override
	public void hScrollbarChanged(int value) {
		if (updatingScrollBars) return;
		topLeft = new Point2D.Double(value, topLeft.getY());
		GUIFunctions.refresh();
	}

	@Override
	public void vScrollbarChanged(int value) {
		if (updatingScrollBars) return;
		topLeft = new Point2D.Double(topLeft.getX(), value);
		GUIFunctions.refresh();
	}

	@Override
	public void toggle3DModelDisplay() {
		model3d = !model3d;
		GUIFunctions.refresh();
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {
		// don't do anything if the 3d model isn't turned on
		if (!model3d) return;
		
		while (iterator.hasNext()) {
			char key = (char)iterator.next().intValue();
			System.out.println(key);
		
	//		a	Move left
			if (key == 'A') {
				myLocation.x -= 1*speed * Math.sin(rot+Math.PI/2);
				myLocation.z += 1*speed * Math.cos(rot+Math.PI/2);
			}
	//		d	Move right
			if (key == 'D') {
				myLocation.x += 1*speed * Math.sin(rot+Math.PI/2);
				myLocation.z -= 1*speed * Math.cos(rot+Math.PI/2);
			}
	//		w	Move forward
			if (key == 'W') {
				myLocation.x += 1*speed * Math.sin(rot);
				myLocation.z -= 1*speed * Math.cos(rot);
			}
	//		s	Move backward
			if (key == 'S') {
				myLocation.x -= 1*speed * Math.sin(rot);
				myLocation.z += 1*speed * Math.cos(rot);
			}
	//		q	Turn left
			if (key == 'Q') {
				rot = (float) ((rot - 3 * Math.PI/180 * speed) % (2*Math.PI));
			}
	//		e	Turn right
			if (key == 'E') {
				rot = (float) ((rot + 3 * Math.PI/180 * speed) % (2*Math.PI));
			}
	//		r	Move up
			if (key == 'R') {
				myLocation.y += 1*speed;
			}
	//		f	Move down
			if (key == 'F') {
				myLocation.y -= 1*speed;
			}
	//		h	Return to the original “home” position and orientation
			if (key == 'H') {
				myLocation = new Point3D(home.x, home.y, home.z);
				rot = rot_home;
			}
		}
		GUIFunctions.refresh();
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
	
	public double GetZoom() {
		return zoom;
	}
	
	public Point2D GetTopLeft() {
		return topLeft;
	}
	
	public void InitializeView() {
		
        int min = 0;
        int max = 2048;
        int span = (int) (max/(4*zoom));
        int posit = max/2 - span/2;
        
        GUIFunctions.setHScrollBarMin(min);
        GUIFunctions.setHScrollBarMax(max);
        GUIFunctions.setHScrollBarKnob(span);
        GUIFunctions.setHScrollBarPosit(posit);
        
        GUIFunctions.setVScrollBarMin(min);
        GUIFunctions.setVScrollBarMax(max);
        GUIFunctions.setVScrollBarKnob(span);
        GUIFunctions.setVScrollBarPosit(posit);
        
        topLeft = new Point2D.Double(posit, posit);
	}
	
	private void ZoomChanged() {
//		int min = 0;
		int max = 2048;
		int span = (int) (max/(4*zoom));
		
		GUIFunctions.setHScrollBarPosit(0);
		GUIFunctions.setHScrollBarKnob(span);
		GUIFunctions.setHScrollBarPosit((int) topLeft.getX());

		GUIFunctions.setVScrollBarPosit(0);
		GUIFunctions.setVScrollBarKnob(span);
		GUIFunctions.setVScrollBarPosit((int) topLeft.getY());
	}
	
	private Point2D CalcViewCenter() {
		
//		int min = 0;
		int max = 2048;
		double span = max/(4*zoom);
//		double posit = max/2 - span/2;
        
        double x = topLeft.getX()+span/2;
        double y = topLeft.getY()+span/2;
        
        return new Point2D.Double(x,y);
	}
	
	private Point2D CalcViewTopLeft(Point2D center) {

		int min = 0;
		int max = 2048;
		double span = max/(4*zoom);
//		double posit = max/2 - span/2;
		
		double x = center.getX()-span/2;
		double y = center.getY()-span/2;

		if (x < min) {
			x = min;
		}
		else if (x+span > max) {
			x = max-span;
		}
		
		if (y < min) {
			y = min;
		}
		else if (y+span > max) {
			y = max-span;
		}
		
		return new Point2D.Double(x, y);
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




	public Point3D GetCameraLocation() {
		return myLocation;
	}
	
	public double GetCameraRotation() {
		return rot;
	}
}
