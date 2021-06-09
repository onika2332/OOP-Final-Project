package controller;

import model.Room;
import model.Object;
import model.Point;

public class testRoom{

	public static void main(String[] args){
		Point p1 = new Point(0,0,0);
		Point p2 = new Point(4,0,0);
		Point p3 = new Point(4,3,0);
		Point p4 = new Point(0,3,0);
		Point p5 = new Point(0,0,2);
		Point p6 = new Point(4,0,2);
		Point p7 = new Point(4,3,2);
		Point p8 = new Point(0,3,2);
		Room r  = new Room(p1,p2,p3,p4,p5,p6,p7,p8);

		Point v1 = new Point(0,0,0);
		Point v2 = new Point(1,0,0);
		Point v3 = new Point(1,1,0);
		Point v4 = new Point(0,1,0);
		Point v5 = new Point(0,0,1);
		Point v6 = new Point(1,0,1);
		Point v7 = new Point(1,1,1);
		Point v8 = new Point(0,1,1);
		Object obj1 = new Object(v1, v2, v3, v4, v5, v6, v7, v8, r);
		System.out.println(r.getObjects().size());
		
		Point _v1 = new Point(1,1,0);
		Point _v2 = new Point(2,1,0);
		Point _v3 = new Point(2,2,0);
		Point _v4 = new Point(1,2,0);
		Point _v5 = new Point(1,1,1);
		Point _v6 = new Point(2,1,1);
		Point _v7 = new Point(2,2,1);
		Point _v8 = new Point(1,2,1);
		Object obj2 = new Object(_v1, _v2, _v3, _v4, _v5, _v6, _v7, _v8, r);
		
		System.out.println("******************************");
		
		System.out.println("******************************");
		System.out.println(obj2.isInside(r));

		System.out.println(r.getObjects().size());

		System.out.println(r.getHeight() * r.getLength() * r.getWidth() * 8000); // correct

		r.setStateForAllPoints();
		System.out.println(r.getAvailablePoint().size());
		System.out.println(r.getHiddenPoint().size());

		// obj1 : 1 1 1 --> 20 20 20 --> 400*5 point of object are Available, hidden --> 8000 - 2000 = 6000
		// obj2 : 1 1 1 --> 20 20 20 --> 400*5 point of object are Available, hidden --> 8000 - 2000 = 6000
		// total hidden is 12000
		// total point : 4 * 3 * 2 * 8000 = 192000 point

		


		


	}

}
