package cs355.solution;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import cs355.Point3D;

public class Testing {

	public static void main(String[] args) {
//		// top right corner of square in obj coords
//		Point2D objCoord = new Point2D.Double(10, 10);
//		// center of square in world coords
//		Point2D center = new Point2D.Double(100, 80);
//		// rotation of square
//		double rotation = Math.PI / 4;
//		// zoom of view
//		double zoom = 2;
//		// location of view
//		Point2D topLeft = new Point2D.Double(50, 60);
//		
//		Point2D viewCoord = Utility.ObjectToView(objCoord, center, rotation, zoom, topLeft);
//		System.out.println(viewCoord);
//		
//		
//		// user clicks in view - does it hit square?
//		viewCoord = new Point2D.Double(90, 70);
//		
//		objCoord = Utility.ViewToObject(viewCoord, center, rotation, zoom, topLeft);
//		System.out.println(objCoord);
		
		
		
		
		
//		// camera's location
//		Point3D loc = new Point3D(20, 5, -40);
//		// camera's rotation
//		double rot = Math.PI/6;
//		// world to camera matrix
//		WorldToCamera w2c = new WorldToCamera(loc, rot);
//		System.out.printf("%2.2f  %2.2f  %2.2f  %2.2f\n"
//				+ "%2.2f  %2.2f  %2.2f  %2.2f\n"
//				+ "%2.2f  %2.2f  %2.2f  %2.2f\n"
//				+ "%2.2f  %2.2f  %2.2f  %2.2f\n", 
//				w2c.m11, w2c.m12, w2c.m13, w2c.m14,
//				w2c.m21, w2c.m22, w2c.m23, w2c.m24,
//				w2c.m31, w2c.m32, w2c.m33, w2c.m34,
//				w2c.m41, w2c.m42, w2c.m43, w2c.m44);
//		// world point
//		Vector3D world = new Vector3D(5, 6, 7, 1);
//		// camera point
//		Vector3D camera = Matrix3D.MatrixTimesVector(w2c, world);
//		System.out.printf("\n%2.2f  %2.2f  %2.2f  %2.2f\n", camera.v1, camera.v2, camera.v3, camera.v4);

		
		
		
		
		// clip testing
		double fov = 60*Math.PI/180;
		double ratio = 9.0/16.0;
		double n = 10;
		double f = 1000;
		// matrix
		ClipMatrix c = new ClipMatrix(fov, ratio, n, f);
		System.out.printf("%2.2f  %2.2f  %2.2f  %2.2f\n"
		+ "%2.2f  %2.2f  %2.2f  %2.2f\n"
		+ "%2.2f  %2.2f  %2.2f  %2.2f\n"
		+ "%2.2f  %2.2f  %2.2f  %2.2f\n", 
		c.m11, c.m12, c.m13, c.m14,
		c.m21, c.m22, c.m23, c.m24,
		c.m31, c.m32, c.m33, c.m34,
		c.m41, c.m42, c.m43, c.m44);
		// camera-space point
		Vector3D v = new Vector3D(5, -5, 50, 1);
		// convert to clip-space
		Vector3D clip = Matrix3D.MatrixTimesVector(c, v);
		System.out.printf("\n%2.2f  %2.2f  %2.2f  %2.2f\n", clip.v1, clip.v2, clip.v3, clip.v4);
		// do the divide
		clip = new Vector3D(clip.v1/clip.v4, clip.v2/clip.v4, clip.v3/clip.v4, clip.v4/clip.v4);
		System.out.printf("\n%2.2f  %2.2f  %2.2f  %2.2f\n", clip.v1, clip.v2, clip.v3, clip.v4);
		// convert to screen space
		double width = 1920;
		double height = 1080;
		Point2D ptSrc = new Point2D.Double(clip.v1, clip.v2);
		System.out.println(ptSrc.toString());
		AffineTransform a = new AffineTransform(width/2, 0, 0, -height/2, width/2, height/2);
		a.transform(ptSrc, ptSrc);
		System.out.println(ptSrc.toString());		
	}

}
