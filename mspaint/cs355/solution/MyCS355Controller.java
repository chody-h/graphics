package cs355.solution;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Stack;

import cs355.GUIFunctions;
import cs355.model.*;

public class MyCS355Controller implements cs355.CS355Controller {
	
	private MyCreations shapes = new MyCreations();
	private Color currentColor = new Color(255,255,255);
	private int currentButton = BUTTONS.LINE;
	private int numTriangleVertices = 0;
	private Point anchor;
	
	public void DrawShape(Point start) {
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
//				TODO
				break;
			default:
				break;
		}
		shapes.Push(s);
	}
	
	public void UpdateShape(Point updated) {
		MyShape s = shapes.Pop();
		if (s instanceof MyLine) {
			((MyLine)s).Update(updated);
		}
		else if (s instanceof MySquare) {			
			int lenX = Math.abs(updated.x-anchor.x);
			int lenY = Math.abs(updated.y-anchor.y);
			double length = Math.min(lenX, lenY);
			
			double x = (updated.x < anchor.x) ? anchor.x-length/2 : anchor.x+length/2;
			double y = (updated.y < anchor.y) ? anchor.y-length/2 : anchor.y+length/2;
			Point center = new Point((int)x, (int)y);
			
			((MySquare)s).Update(center, (int)length);
		}
		else if (s instanceof MyRectangle) {	
			double w = Math.abs(updated.x-anchor.x);
			double h = Math.abs(updated.y-anchor.y);
			
			double x = (updated.x < anchor.x) ? anchor.x-w/2 : anchor.x+w/2;
			double y = (updated.y < anchor.y) ? anchor.y-h/2 : anchor.y+h/2;
			Point center = new Point((int)x, (int)y);
			
			((MyRectangle)s).Update(center, (int)w, (int)h);
		}
		else if (s instanceof MyCircle) {
			int lenX = Math.abs(updated.x-anchor.x);
			int lenY = Math.abs(updated.y-anchor.y);
			double r = Math.min(lenX, lenY) / 2;
			
			double x = (updated.x < anchor.x) ? anchor.x-r : anchor.x+r;
			double y = (updated.y < anchor.y) ? anchor.y-r : anchor.y+r;
			Point center = new Point((int)x, (int)y);
			
			((MyCircle)s).Update(center, (int)r);
		}
		else if (s instanceof MyEllipse) {
			double w = Math.abs(updated.x-anchor.x);
			double h = Math.abs(updated.y-anchor.y);
			
			double x = (updated.x < anchor.x) ? anchor.x-w/2 : anchor.x+w/2;
			double y = (updated.y < anchor.y) ? anchor.y-h/2 : anchor.y+h/2;
			Point center = new Point((int)x, (int)y);
			
			((MyEllipse)s).Update(center, (int)w, (int)h);
		}
		else if (s instanceof MyTriangle) {
//			pass
//			all logic in "DrawShape()"
		}
		shapes.Push(s);
	}
	
	public Stack<MyShape> GetShapes() {
		return shapes.GetShapes();
	}
	
	public void AddObserver(MyViewRefresher vr) {
		shapes.addObserver(vr);
	}

	@Override
	public void colorButtonHit(Color c) {
		GUIFunctions.changeSelectedColor(c);
		currentColor = c;
	}

	@Override
	public void triangleButtonHit() {
		currentButton = BUTTONS.TRIANGLE;
	}

	@Override
	public void squareButtonHit() {
		currentButton = BUTTONS.SQUARE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
	}

	@Override
	public void rectangleButtonHit() {
		currentButton = BUTTONS.RECTANGLE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
	}

	@Override
	public void circleButtonHit() {
		currentButton = BUTTONS.CIRCLE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
	}

	@Override
	public void ellipseButtonHit() {
		currentButton = BUTTONS.ELLIPSE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
	}

	@Override
	public void lineButtonHit() {
		currentButton = BUTTONS.LINE;
		if (numTriangleVertices != 0) {
			shapes.Pop();
			numTriangleVertices = 0;
			GUIFunctions.refresh();
		}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zoomOutButtonHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hScrollbarChanged(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vScrollbarChanged(int value) {
		// TODO Auto-generated method stub
		
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
