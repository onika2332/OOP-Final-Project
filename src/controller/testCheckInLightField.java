package controller;
import model.Point;
import model.Room;
import model.Camera;

public class testCheckInLightField {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(0,0,0);
		Point p2 = new Point(4,0,0);
		Point p3 = new Point(4,3,0);
		Point p4 = new Point(0,3,0);
		Point p5 = new Point(0,0,2);
		Point p6 = new Point(4,0,2);
		Point p7 = new Point(4,3,2);
		Point p8 = new Point(0,3,2);
		
		Room r = new Room( p1, p2, p3, p4, p5, p6, p7, p8);
		System.out.println("Begin");
		System.out.println("Hey, this state is:" + p1.getState());
		//Camera cam1 = new Camera(2, 2, 2, 90, 10); // valid
		//Camera cam2 = new Camera(2, 4, 2, 90, 10); // invalid
		
		//Point p10 = new Point(2,2,1);
		//p10.setState(State.Available);
		//cam1.checkPointInRange(p10);
		
		//Point p11 = new Point(2,2,1);
		//cam1.checkPointInRange(p11);
		//cam1.checkPointInLightField(p11);
	}

}
