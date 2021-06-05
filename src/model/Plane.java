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
	
	public Plane(Point p1, Point p2, Point p3) {
		this();
		Vector v12 = new Vector(p1, p2);
		Vector v23 = new Vector(p2, p3);

		Vector n = v12.Directional(v23);

		this.setA(n.getX());
		this.setB(n.getY());
		this.setC(n.getZ());
		this.setD(n.getX()*p1.getX() + n.getY()*p1.getY() + n.getZ()*p1.getZ());
	}
	
}

