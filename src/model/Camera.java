package model;
import java.lang.Math;
import java.util.Iterator;

public class Camera extends Point {
	private int angle; // attribute ANGLE
	private float range; // attribute range
	private Plane ownPlane; // the plane where camera on ( ceiling or wall)
	private Frame shadow; // this is shadow of camera on the opposite plane
	private Frame oppositeFrame; // the opposite plane of which own camera, own the shadow
	private Point projectionPoint; // the project of camera ( point ) is in opposite plane 
	
	
	public void setAngle(int angle) {
		this.angle = angle;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public void setShadow(Point p1, Point p2, Point p3, Point p4) {
		this.shadow = new Frame(p1, p2, p3, p4);
	}
	
	public Frame getShadow() {
		return this.shadow;
	}
	// How can we set the oppositeFrame for camera inside constructor, we need push Room object as a parameter??????
	public void setOppositeFrame(Room room) {
		if(this.ownPlane.getD() == 0 ) {
			if(this.ownPlane.getA() != 0) {
				this.oppositeFrame = new Frame(room.p3, room.p4, room.p8, room.p7);
			} else if(this.ownPlane.getB() != 0) {
				this.oppositeFrame = new Frame(room.p2, room.p3, room.p7, room.p6);
			} else {
				this.oppositeFrame = new Frame(room.p5, room.p6, room.p7, room.p8);
			}
		} else {
			if(this.ownPlane.getA() != 0) {
				this.oppositeFrame = new Frame(room.p1, room.p2, room.p6, room.p5);
			} else if(this.ownPlane.getB() != 0) {
				this.oppositeFrame = new Frame(room.p1, room.p4, room.p8, room.p5);
			} else {
				this.oppositeFrame = new Frame(room.p1, room.p2, room.p3, room.p4);
			}
		}
	}

	public Frame getOppositeFrame() {
		return this.oppositeFrame;
	}
	
	public int getAngle() {
		return angle;
	}
	
	
	public float getRange() {
		return range;
	}
	
	public void setProjectionPoint(Point p) {
		this.projectionPoint = p;
	}

	public Point getProjectionPoint() {
		return this.projectionPoint;
	}
	// get the plane own camera
	public Plane getOwnPlane() {
		return ownPlane;
	}
	// create camera by its location (x,y,z) and angle, range
	public Camera(float x, float y, float z, int angle, int range) {
		super(x,y,z);
		this.angle = angle;
		this.range = range;
	}

	//check if camera on the wall or on the celling or not
	public boolean checkCameraOnFrame(Room room) {
		Iterator<Frame> iter1 = room.walls.iterator();
		while(iter1.hasNext()) {
			Frame curFrame = iter1.next();
			if(curFrame.checkPointInsideFrame((Point)this)) {
				this.ownPlane = curFrame.ownPlane;
				this.setOppositeFrame(room);
				return true;
			}	
		}
		return false;
	}
	//determine the shadow of camera in the opposite plane
	public void setShadow(Room room) {
		this.setOppositeFrame(room);
		this.shadow = new Frame();
		// case "The camera's range < distance of 2 plane" hasn't been solved
		Point center;
		if(this.ownPlane.getD() != 0) {
			if(this.ownPlane.getA() != 0) {
				Line aLine = new Line(this, this.ownPlane.getNormalVector());
				center = aLine.getPointAtXLocation(0);
				if(this.getRange() >= ( this.ownPlane.getD() / this.ownPlane.getA() ))
					this.range = this.ownPlane.getD()/this.ownPlane.getA();
			} else if(this.ownPlane.getB() != 0) {
				Line aLine = new Line(this, this.ownPlane.getNormalVector());
				center = aLine.getPointAtYLocation(0);
				if(this.getRange() >= ( this.ownPlane.getD() / this.ownPlane.getB() ))
					this.range = this.ownPlane.getD()/this.ownPlane.getB();
			} else {
				Line aLine = new Line(this, this.ownPlane.getNormalVector());
				center = aLine.getPointAtZLocation(0);
				if(this.getRange() >= ( this.ownPlane.getD() / this.ownPlane.getC() ))
					this.range = this.ownPlane.getD()/this.ownPlane.getC();
			}
		} else {
			// D = 0
			if(this.ownPlane.getA() != 0) {
				if(this.range >= room.getLength()) 
					this.range = room.getLength();
				Line aLine = new Line(this, this.ownPlane.getNormalVector());
				center = aLine.getPointAtXLocation(room.getLength());
			} else if(this.ownPlane.getB() != 0) {
				if(this.range >= room.getWidth()) 
					this.range = room.getWidth();
				Line aLine = new Line(this, this.ownPlane.getNormalVector());
				center = aLine.getPointAtYLocation(room.getWidth());
			} else {
				Line aLine = new Line(this, this.ownPlane.getNormalVector());
				center = aLine.getPointAtZLocation(room.getHeight());
				if(this.range >= room.getHeight()) 
					this.range = room.getHeight();
			}
		}
		this.setProjectionPoint(center);
		double tanAlpha = (float)Math.tan(Math.toRadians(this.angle/2));
		float temp = (float)(this.range*tanAlpha);
		if(this.ownPlane.getA() != 0) {
			Point p1 = new Point(center.getX(), center.getY() - temp, center.getZ() - temp);
			Point p2 = new Point(center.getX(), center.getY() - temp, center.getZ() + temp);
			Point p3 = new Point(center.getX(), center.getY() + temp, center.getZ() + temp);
			Point p4 = new Point(center.getX(), center.getY() + temp, center.getZ() - temp);
			this.setShadow(p1, p2, p3, p4);
		} else if(this.ownPlane.getB() != 0) {
			Point p1 = new Point(center.getX() - temp, center.getY(), center.getZ() - temp);
			Point p2 = new Point(center.getX() - temp, center.getY(), center.getZ() + temp);
			Point p3 = new Point(center.getX() + temp, center.getY(), center.getZ() + temp);
			Point p4 = new Point(center.getX() + temp, center.getY(), center.getZ() - temp);
			this.setShadow(p1, p2, p3, p4);
		} else {
			Point p1 = new Point(center.getX() - temp, center.getY() - temp, center.getZ());
			Point p2 = new Point(center.getX() - temp, center.getY() + temp, center.getZ());
			Point p3 = new Point(center.getX() + temp, center.getY() + temp, center.getZ());
			Point p4 = new Point(center.getX() + temp, center.getY() - temp, center.getZ());
			this.setShadow(p1, p2, p3, p4);
		}
	}
	// check a point in range of camera or not
	public boolean checkPointInRange(Room room, Point aPoint) {
		this.setShadow(room);
		// System.out.println(this.getShadow().p1 + "\n"
		// 					+ this.getShadow().p2 + "\n"
		// 					+ this.getShadow().p3 + "\n"
		// 					+ this.getShadow().p4 + "\n"); 
		float checkVar = this.oppositeFrame.getOwnPlane().getA()*aPoint.getX()
						+ this.oppositeFrame.getOwnPlane().getB()*aPoint.getY()
						+ this.oppositeFrame.getOwnPlane().getC()*aPoint.getZ();
		// System.out.println(checkVar);
		if( this.oppositeFrame.getOwnPlane().getD() == checkVar ) {
			// case : aPoint is in the oppositePlane 
			if(!this.shadow.checkPointInsideFrame(aPoint)) 
					return false;
			else 
					return true;
		} else {
			Line aLine = new Line();
			aLine.setP(aPoint);
			float slopeA = this.x - aPoint.getX();
			float slopeB = this.y - aPoint.getY();
			float slopeC = this.z - aPoint.getZ();
			
			aLine.setSlope(slopeA, slopeB, slopeC);
			float ts = this.oppositeFrame.getOwnPlane().getD() - (this.oppositeFrame.getOwnPlane().getA()*aPoint.getX()
																+ this.oppositeFrame.getOwnPlane().getB()*aPoint.getY()
																+ this.oppositeFrame.getOwnPlane().getC()*aPoint.getZ());
			
			float ms = slopeA*this.oppositeFrame.getOwnPlane().getA()
					+ slopeB*this.oppositeFrame.getOwnPlane().getB()
					+ slopeC*this.oppositeFrame.getOwnPlane().getC();
			
			float t = ts / ms;
			Point intersection = new Point(aPoint.getX() + slopeA*t,
											aPoint.getY() + slopeB*t,
											aPoint.getZ() + slopeC*t);
			if(this.oppositeFrame.getOwnPlane().getA() != 0) {
				if(!this.shadow.checkPointInsideFrame(intersection)) 
					return false;
				else 
					return true;
			} else if(this.oppositeFrame.getOwnPlane().getB() != 0) {
				if(!this.shadow.checkPointInsideFrame(intersection)) 
					return false;
				else 
					return true;
			} else {
				if(!this.shadow.checkPointInsideFrame(intersection)) 
					return false;
				else 
					return true;
			}
		}
	}

	// true : state ---> Light
	// false : state is still Available ( for traverse with next camera)
	public boolean checkPointInLightField(Room room, Point aPoint) {
		if(this.checkPointInRange(room, aPoint)) {
			// Create a line of 2 point : camera & point in parameter
			Line aLine = new Line(this, aPoint);

			// find the normal vector to determined x or y or z (++ or --)
			if(this.ownPlane.getA() != 0) {
				if(aPoint.getX() > this.getX()) {
					for(float m = this.getX() + 0.01f; m < aPoint.getX(); m++)
						if(aLine.getPointAtXLocation(m).getState() == State.OnSide)
							return false;
				} else {
					for(float m = this.getX() - 0.01f; m > aPoint.getX(); m--)
						if(aLine.getPointAtXLocation(m).getState() != State.OnSide)
							return false;
				}
			} else if(this.ownPlane.getB() != 0) {
				if(aPoint.getY() > this.getY()) {
					for(float m = this.getY() + 0.01f; m < aPoint.getY(); m++)
						if(aLine.getPointAtYLocation(m).getState() != State.OnSide)
							return false;
				} else {
					for(float m = this.getY() - 0.01f; m > aPoint.getY(); m--)
						if(aLine.getPointAtYLocation(m).getState() != State.OnSide)
							return false;
				}
			} else {
				if(aPoint.getZ() > this.getZ()) {
					for(float m = this.getZ() + 0.01f; m < aPoint.getZ(); m++)
						if(aLine.getPointAtZLocation(m).getState() == State.OnSide)
							return false;
				} else {
					for(float m = this.getZ() - 0.01f; m > aPoint.getZ(); m--)
						if(aLine.getPointAtZLocation(m).getState() != State.OnSide)
							return false;
				}
			}
			aPoint.state = State.Light;
			return true;
		}
		return false;
	}
	
}
