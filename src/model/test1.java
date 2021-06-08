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
		

		Camera c1 = new Camera(2, 2, 5, 60, 50);
		Room r = new Room(p1,p2,p3,p4,p5,p6,p7,p8);
		System.out.println(c1.checkCameraOnFrame(r) + "\n*****");
		Point p = new Point(2,2,0);
		System.out.println(c1.checkPointInRange(r,p) + "\n*****"); 
		System.out.println(c1.checkPointInLightField(r,p) + "\n*****");

		System.out.println(c1.getOppositeFrame().p1 + "\n"
							+ c1.getOppositeFrame().p2 + "\n"
							+ c1.getOppositeFrame().p3 + "\n"
							+ c1.getOppositeFrame().p4 + "\n");  

		System.out.println("***************************************************");
	}

}
