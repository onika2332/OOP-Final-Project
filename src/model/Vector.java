package model;
import georegression.struct.point.Point3D_F32;
import georegression.struct.point.Vector3D_F32;

public class Vector extends Vector3D_F32 {
	
	public Vector() {
		super();
	}
	public Vector(float x, float y, float z) {
		super(x,y,z);
	}
	
	public Vector(Point3D_F32 p1, Point3D_F32 p2) {
		super(p1,p2);
	}
	
	public boolean isParallel(Vector v) {
		float ratio1 = this.getX() / v.getX();
		float ratio2 = this.getY() / v.getY();
		float ratio3 = this.getZ() / v.getZ();
		
		return (ratio1 == ratio2) && (ratio1 == ratio3);
	}
	public boolean isPerpendicular(Vector v) {
		return 0 == ( this.getX()*v.getX() 
					+ this.getY()*v.getY() 
					+ this.getZ()*v.getZ());
	}
	public float vectoLeng() {
		float leng = (float) Math.sqrt(this.getX()*this.getX() 
									 + this.getY()*this.getY() 
									 + this.getZ()*this.getZ());
		return leng;
	}
	public  Vector Directional(Vector a, Vector b) {
		float n1 = (a.getY()*b.getZ() - a.getZ()*b.getY());
		float n2 = -(a.getX()*b.getZ() - a.getZ()*b.getX());
		float n3 = (a.getX()*b.getY() - a.getY()*b.getX());
		Vector n = new Vector(n1,n2,n3);
		return n;
	}
}

