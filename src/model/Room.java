package model;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Room extends Box {
	// we use length, width, height for count volume of room ( = length * width * height )
	private float length;
	private float width;
	private float height;
	List<Frame> walls = new ArrayList<Frame>();
	List<Object> objects = new ArrayList<Object>();
	public List<Frame> getWalls() {
		return walls;
	}

	public void setObjectsInRoom(Object obj) {
			this.objects.add(obj);
	}
	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Room() {
		super();
	}
	public Plane roomPlane(Vector v, Point p1) {
		float A = v.getX();
		float B = v.getY();
		float C = v.getZ();
		float D = (float)(v.getX()*p1.getX() 
						+ v.getY()*p1.getY() 
						+ v.getZ()*p1.getZ());
		Plane p = new Plane(A,B,C,D);
		return p;
	}
	public Room(Point p1, Point p2, Point p3, Point p4,
				Point p5, Point p6, Point p7, Point p8) {
		super(p1,p2,p3,p4,p5,p6,p7,p8);
		
		Frame p1234 = new Frame(p1, p2, p3, p4);
		Frame p5678 =  new Frame(p5, p6, p7, p8);
		Frame p1584 = new Frame(p1, p5, p8, p4);
		Frame p2376 = new Frame(p2, p3, p7, p6);
		Frame p1265 = new Frame(p1, p2, p6, p5);
		Frame p4378 = new Frame(p4, p3, p7, p8);
		
		walls.add(p1234);
		walls.add(p5678);
		walls.add(p1584);
		walls.add(p2376);
		walls.add(p1265);
		walls.add(p4378);
		
		Vector p12 = new Vector(p1,p2);
		Vector p41 = new Vector(p4,p1);
		Vector p15 = new Vector(p1,p5);
		setHeight(p12.vectoLeng());
		setWidth(p41.vectoLeng());
		setLength(p15.vectoLeng());
		
	}

	public boolean checkObjectInsideRoom() {
		Iterator<Object> iter = objects.iterator();
		while(iter.hasNext()) {
			Object obj = iter.next();
			if(!obj.isInside(this)) {
				System.out.println("Object isn't inside the room. Invalid");
				return false;
			}
		}
		return true;
	}

	public void setStateForAllPoints() {
		for(float i = p1.getX(); i <= p7.getX(); i+= 0.01) {
			for(float j = p1.getY(); i <= p7.getY(); j+= 0.01) {
				for(float k = p1.getZ(); i <= p7.getZ(); k+= 0.01) {
					// create point
					Point p = new Point(i,j,k);

					// set state for point
					Iterator<Object> iter = objects.iterator();
					while(iter.hasNext()) {
						Object obj = iter.next();
						if(p.isInsideObject(obj))
							if(obj.checkPointOnSide(p))
								p.setState(State.Available);
							else
								p.setState(State.Hidden);
						else 
							p.setState(State.Available);
					}
				}
			}
		}		
	}
	
}