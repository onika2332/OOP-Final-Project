package model;
import java.util.ArrayList;
import java.util.List;
public class Object extends Box {
	
	// list of point on the top plane of object, we only need 4 point was the frame of plane, e.x 5,6,7,8
	Frame topFrame;  // is FRAME 5,6,7,8
	
	// 4 side plane
	List<Frame> sideFrame = new ArrayList<Frame>();
	
	// When init object, we assign reference for each point of object ( Available : on plane, InsideObject : hidden )
	
	public Object(Point p1, Point p2, Point p3, Point p4,
					Point p5, Point p6, Point p7, Point p8,
					Room room) {
		super(p1,p2,p3,p4,p5,p6,p7,p8);
		
		this.topFrame = new Frame(p5, p6, p7, p8);
		Frame p1584 = new Frame(p1, p5, p8, p4);
		Frame p2376 = new Frame(p2, p3, p7, p6);
		Frame p1265 = new Frame(p1, p2, p6, p5);
		Frame p3487 = new Frame(p4, p3, p7, p8);

		this.sideFrame.add(p1265);
		this.sideFrame.add(p1584);
		this.sideFrame.add(p2376);
		this.sideFrame.add(p3487);

		if(this.isInside(room))
			room.setObjectsInRoom(this);
		else 
			System.out.println("This object is invalid. Not inside the room");

	}
		
	public boolean checkPointOnFloor(Point p) {
		if(this.topFrame.checkPointInsideFrame(p))
			return false;
		for (Frame frame : sideFrame) {
			if(frame.checkPointInsideFrame(p))
				return false;
		}
		return true;
	}

	public boolean checkOnSide(Point p) {
		if(this.topFrame.checkPointInsideFrame(p))
			return true;
		for (Frame frame : sideFrame) {
			if(frame.checkPointInsideFrame(p))
				return true;
		}
		return false;
	}
	
	
}
