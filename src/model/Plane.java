package model;
import georegression.struct.plane.PlaneGeneral3D_F32;
import georegression.struct.point.Point3D_F32;
import georegression.struct.point.Vector3D_F32;
public class Plane extends PlaneGeneral3D_F32 {
	public Plane() {
		super();
	}
	
	public Plane(float A, float B, float C, float D) {
		super(A,B,C,D);
	}
	public Vector getNormalVector() {
		return new Vector(this.getA(), this.getB(), this.getC());
	}
	
	public boolean checkPoint(float x, float y, float z) {
		return this.getD() == this.getA() * x 
								+ this.getB() * y
								+ this.getC() * z;
	}
	
	
}

