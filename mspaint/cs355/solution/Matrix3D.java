package cs355.solution;

public class Matrix3D {

	double m11 = 0;	double m12 = 0; double m13 = 0; double m14 = 0;
	double m21 = 0; double m22 = 0; double m23 = 0; double m24 = 0;
	double m31 = 0;	double m32 = 0; double m33 = 0; double m34 = 0;
	double m41 = 0; double m42 = 0; double m43 = 0; double m44 = 0;

	public static Vector3D MatrixTimesVector(Matrix3D A, Vector3D V) {
		double v1 = A.m11*V.v1 + A.m12*V.v2 + A.m13*V.v3 + A.m14*V.v4;
		double v2 = A.m21*V.v1 + A.m22*V.v2 + A.m23*V.v3 + A.m24*V.v4;
		double v3 = A.m31*V.v1 + A.m32*V.v2 + A.m33*V.v3 + A.m34*V.v4;
		double v4 = A.m41*V.v1 + A.m42*V.v2 + A.m43*V.v3 + A.m44*V.v4;
		
		return new Vector3D(v1, v2, v3, v4);
	}

}
