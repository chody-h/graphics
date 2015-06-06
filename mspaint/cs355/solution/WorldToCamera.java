package cs355.solution;

import cs355.Point3D;

// this class ONLY rotates around the y-axis
public class WorldToCamera extends Matrix3D {

	double m11 = 0;	double m12 = 0; double m13 = 0; double m14 = 0;
	double m21 = 0; double m22 = 0; double m23 = 0; double m24 = 0;
	double m31 = 0;	double m32 = 0; double m33 = 0; double m34 = 0;
	double m41 = 0; double m42 = 0; double m43 = 0; double m44 = 0;

	// rotation in RADIANS
	public WorldToCamera(Point3D location, double rotation) {
		double r11 = Math.cos(rotation); double r12 = 0; double r13 = Math.sin(rotation); double r14 = 0;
		double r21 = 0; double r22 = 1; double r23 = 0; double r24 = 0;
		double r31 = -Math.sin(rotation); double r32 = 0; double r33 = Math.cos(rotation); double r34 = 0;
		double r41 = 0; double r42 = 0; double r43 = 0; double r44 = 1;
		
		double t11 = 1; double t12 = 0; double t13 = 0; double t14 = -location.x;
		double t21 = 0; double t22 = 1; double t23 = 0; double t24 = -location.y;
		double t31 = 0; double t32 = 0; double t33 = 1; double t34 = -location.z;
		double t41 = 0; double t42 = 0; double t43 = 0; double t44 = 1;

		m11 = r11*t11 + r12*t21 + r13*t31 + r14*t41;
		m12 = r11*t12 + r12*t22 + r13*t32 + r14*t42;
		m13 = r11*t13 + r12*t23 + r13*t33 + r14*t43;
		m14 = r11*t14 + r12*t24 + r13*t34 + r14*t44;
		
		m21 = r21*t11 + r22*t21 + r23*t31 + r24*t41;
		m22 = r21*t12 + r22*t22 + r23*t32 + r24*t42;
		m23 = r21*t13 + r22*t23 + r23*t33 + r24*t43;
		m24 = r21*t14 + r22*t24 + r23*t34 + r24*t44;

		m31 = r31*t11 + r32*t21 + r33*t31 + r34*t41;
		m32 = r31*t12 + r32*t22 + r33*t32 + r34*t42;
		m33 = r31*t13 + r32*t23 + r33*t33 + r34*t43;
		m34 = r31*t14 + r32*t24 + r33*t34 + r34*t44;

		m41 = r41*t11 + r42*t21 + r43*t31 + r44*t41;
		m42 = r41*t12 + r42*t22 + r43*t32 + r44*t42;
		m43 = r41*t13 + r42*t23 + r43*t33 + r44*t43;
		m44 = r41*t14 + r42*t24 + r43*t34 + r44*t44;
	}

}
