package model;
import georegression.struct.point.Point3D_F32;
import model.Point;
public class AbstractObject {
	// 8 location of object
	Point p1, p2, p3, p4, p5, p6, p7, p8;
	
	public AbstractObject() {
		
	}
	public AbstractObject(Point p1, Point p2, Point p3, Point p4,
							Point p5, Point p6, Point p7, Point p8) {
		this();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.p5 = p5;
		this.p6 = p6;
		this.p7 = p7;
		this.p8 = p8;
		
	}
	

}
