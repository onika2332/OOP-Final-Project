package model;
import georegression.struct.point.Point3D_F32;
public class Point extends Point3D_F32 {
	public State state;
	public static final long serialVersionUID = 30;
	public Point() {
		super();
	}
	
	public Point(float x, float y, float z) {
		super(x,y,z);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	// point can be inside or onside
	public boolean isInsideObject(Box box) {
		if(this.getX() < box.p1.getX() || this.getY() < box.p1.getY() ||this.getZ() < box.p1.getZ())
			return false;
		else if(this.getX() > box.p7.getX() || this.getY() > box.p7.getY() ||this.getZ() > box.p7.getZ())
			return false;
		
		return true;
	}
	
}

