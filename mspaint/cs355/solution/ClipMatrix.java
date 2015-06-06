package cs355.solution;

public class ClipMatrix extends Matrix3D {
	
	public ClipMatrix(double fov, double aspectRatioYtoX, double n, double f) {
		m43 = 1;
		
		m22 = 1/Math.tan(fov/2);
		m11 = m22 * aspectRatioYtoX;
		m33 = (f+n)/(f-n);
		m34 = (-2*n*f)/(f-n);
	}

}
