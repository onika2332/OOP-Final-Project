package model;
import georegression.struct.point.Point3D_F32;
public class Point extends Point3D_F32 {
	public State state;
	
	public Point() {
		super();
	}
	
	public Point(float x, float y, float z) {
		super(x,y,z);
		this.state = State.Available;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
//	public static Point getPointAt(float x, float y, float z) {
//		if( Point(x,y,z) == null ) {
//			Point p = new Point(x,y,z);
//			return p;
//		} else 
//			return Point(x,y,z);
//	}
}

