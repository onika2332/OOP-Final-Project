package controller;

import model.Room;
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
		if(r.isBox(p1,p2,p3,p4,p5,p6,p7,p8)) {
				//chuong trinh chinh:
			System.out.println(r.getWalls());
			System.out.println(r.getHeight());
			System.out.println(r.getWidth());
			System.out.println(r.getLength());
		} else {
			// ket thuc
		}
	}

}
