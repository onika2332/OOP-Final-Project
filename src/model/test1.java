package model;

public class test1 {

	public static void main(String[] args) {
		System.out.println("***************************************************");
		
		Point p1 = new Point(0,0,0);
		Point p2 = new Point(4,0,0);
		Point p3 = new Point(4,3,0);
		Point p4 = new Point(0,3,0);
		Point p5 = new Point(0,0,5);
		Point p6 = new Point(4,0,5);
		Point p7 = new Point(4,3,5);
		Point p8 = new Point(0,3,5);

		Point p = new Point(0,0,0);
		Camera c1 = new Camera(2,2,5,30,50);
		Room r = new Room(p1,p2,p3,p4,p5,p6,p7,p8);

		System.out.println(c1.checkCameraOnFrame(r) + "\n11111111111");
		System.out.println(c1.checkPointInRange(r,p) + "\n2222222222222222222"); 
		System.out.println(c1.checkPointInLightField(r,p) + "\n33333333333333333333");
		// this method change "z" value of lighted point????????????????????????/
		// Line aLine = new Line(c1, p);
		// System.out.println(c1.getX() + "|"
		// 					+ c1.getY() + "|"
		// 					+ c1.getZ() + "|");
		// System.out.println(p.getX() + "|"
		// 					+ p.getY() + "|"
		// 					+ p.getZ() + "|");
		// System.out.println(aLine.getSlopeX() + "|"
		// 					+ aLine.getSlopeY() + "|"
		// 					+ aLine.getSlopeZ() + "|");

		System.out.println("***************************************************");
	}

}
