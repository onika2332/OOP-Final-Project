package model;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("***************************************************");
		
		Point p1 = new Point(0,0,0);
		Point p2 = new Point(4,0,0);
		Point p3 = new Point(4,3,0);
		Point p4 = new Point(0,3,0);
		Point p5 = new Point(0,0,5);
		Point p6 = new Point(4,0,5);
		Point p7 = new Point(4,3,5);
		Point p8 = new Point(0,3,5);
		

		Camera c1 = new Camera(2, 2, 5, 90, 50);
		Room r = new Room(p1,p2,p3,p4,p5,p6,p7,p8);
		System.out.println(c1.checkCameraOnFrame(r) + "\n11111111111");
		Point p = new Point(1,2,0);
		System.out.println(c1.checkPointInRange(r,p) + "\n2222222222222222222"); 
		System.out.println(c1.checkPointInLightField(r,p) + "\n33333333333333333333");


		Point v1 = new Point(-3,-3,0);
		Point v2 = new Point(-3,7,0);
		Point v3 = new Point(7,7,0);
		Point v4 = new Point(7,-3,0);
		Frame f = new Frame(v1, v2, v3, v4);
		Point v5 = new Point(7,7,0);
		System.out.println(f.checkPointInsideFrame(v5));

		System.out.println("***************************************************");
	}

}
