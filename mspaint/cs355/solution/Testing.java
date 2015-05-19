package cs355.solution;

import java.awt.geom.Point2D;

public class Testing {

	public static void main(String[] args) {
		// top right corner of square in obj coords
		Point2D objCoord = new Point2D.Double(10, 10);
		// center of square in world coords
		Point2D center = new Point2D.Double(100, 80);
		// rotation of square
		double rotation = Math.PI / 4;
		// zoom of view
		double zoom = 2;
		// location of view
		Point2D topLeft = new Point2D.Double(50, 60);
		
		Point2D viewCoord = Utility.ObjectToView(objCoord, center, rotation, zoom, topLeft);
		System.out.println(viewCoord);
		
		
		// user clicks in view - does it hit square?
		viewCoord = new Point2D.Double(90, 70);
		
		objCoord = Utility.ViewToObject(viewCoord, center, rotation, zoom, topLeft);
		System.out.println(objCoord);
	}

}
