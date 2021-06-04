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
	
	public boolean checkParallel(Vector v) {
		float ratio1 = this.getX() / v.getX();
		float ratio2 = this.getY() / v.getY();
		float ratio3 = this.getZ() / v.getZ();
		
		return (ratio1 == ratio2) && (ratio1 == ratio3);
	}
	
	
}

