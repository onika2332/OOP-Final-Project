package model;
import georegression.struct.point.Point3D_F32;
import model.Point;
public class AbstractObject {
	// 8 location of object
	Point p1, p2, p3, p4, p5, p6, p7, p8;
	
	public AbstractObject() {
		
	}
	public boolean checkRectangle(Point p1, Point p2, Point p3, Point p4) {
		Vector p12 = new Vector(p1,p2);
		Vector p23 = new Vector(p2,p3);
		Vector p34 = new Vector(p3,p4);
		Vector p41 = new Vector(p4,p1);
		
		if((p12.isPerpendicular(p23)) && (p23.isPerpendicular(p34)) && (p34.isPerpendicular(p41))) {
			return true;
		}else {
			return false;
		}
	}
	public boolean checkObject(Point p1, Point p2, Point p3, Point p4,
				Point p5, Point p6, Point p7, Point p8){
		if(     checkRectangle(p1, p2, p3, p4) && checkRectangle(p5, p6, p7, p8)
			&&  checkRectangle(p1, p5, p8, p4) && checkRectangle(p3, p4, p8, p7)
			&& 	checkRectangle(p2, p3, p7, p6) && checkRectangle(p1, p2, p6, p5)){
			return true;
		} else {
			return false;
		}
		
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
