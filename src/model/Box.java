package model;

public class Box {
	// 8 location of object
	public Point p1, p2, p3, p4, p5, p6, p7, p8;
	public Box() {
		
	}
	public boolean isRectangle(Point p1, Point p2, Point p3, Point p4) {
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
	public boolean isBox(Point p1, Point p2, Point p3, Point p4,
				Point p5, Point p6, Point p7, Point p8){
		if(     isRectangle(p1, p2, p3, p4) && isRectangle(p5, p6, p7, p8)
			&&  isRectangle(p1, p5, p8, p4) && isRectangle(p3, p4, p8, p7)
			&& 	isRectangle(p2, p3, p7, p6) && isRectangle(p1, p2, p6, p5)){
			return true;
		} else {
			return false;
		}
		
	}
	public Box(Point p1, Point p2, Point p3, Point p4,
							Point p5, Point p6, Point p7, Point p8) {
		
		this();
		if(!this.isBox(p1, p2, p3, p4, p5, p6, p7, p8)) {
			System.out.println("This object isn't a box. Invalid object/room");
			return;
		}
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.p5 = p5;
		this.p6 = p6;
		this.p7 = p7;
		this.p8 = p8;	
	}

	public boolean isInside(Box bigBox) {
		if(this.p1.getX() >= bigBox.p1.getX() && this.p1.getY() >= bigBox.p1.getY() && this.p1.getZ() >= bigBox.p1.getZ()
		&& this.p7.getX() <= bigBox.p7.getX() && this.p7.getY() <= bigBox.p7.getY() && this.p7.getZ() <= bigBox.p7.getZ())
			return true;
		return false;
	}
	

}
