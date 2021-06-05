package model;
import georegression.struct.point.Vector3D_F32;

public class Vector extends Vector3D_F32 {
	
	public Vector() {
		super();
	}
	public Vector(float x, float y, float z) {
		super(x,y,z);
	}
	
	public Vector(Point p1, Point p2) {
		super();
		this.setX(p1.getX() - p2.getX());
		this.setX(p1.getY() - p2.getY());
		this.setX(p1.getZ() - p2.getZ());
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
	public  Vector Directional(Vector b) {
		float n1 = (this.getY()*b.getZ() - this.getZ()*b.getY());
		float n2 = -(this.getX()*b.getZ() - this.getZ()*b.getX());
		float n3 = (this.getX()*b.getY() - this.getY()*b.getX());
		Vector n = new Vector(n1,n2,n3);
		return n;
	}
}

