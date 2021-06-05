package model;
import georegression.struct.point.Point3D_F32;
import georegression.struct.line.LineParametric3D_F32;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Camera extends Point {
	private int angle; // attribute ANGLE
	private float range; // attribute range
	private Plane ownPlane; // the plane where camera on ( ceiling or wall)
	private List<Point> shadow; // this is shadow of camera on the opposite plane
	private Plane oppositePlane; // the opposite plane of which own camera, own the shadow
	private Point projectionPoint; // the project of camera ( point ) is in opposite plane 
	// get Angle of camera
	public int getAngle() {
		return angle;
	}
	
	// get range
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
		Iterator<Frame> iter = room.walls.iterator();
		while(iter.hasNext()) {
			Frame curFrame = iter.next();
			if(curFrame.checkPointInsideFrame((Point)this)) {
				return true;
			}	
		}
		return false;
	}
	//determine the shadow of camera in the opposite plane
	public List<Point> getShadow(Room room) {
		this.shadow = new ArrayList<Point>();
		// case "The camera's range < distance of 2 plane" hasn't been solved
		Point center;
		if(this.ownPlane.getD() != 0) {
			if(this.getRange() >= this.ownPlane.getD())
				this.range = this.ownPlane.getD();
			this.oppositePlane = new Plane(this.ownPlane.getA(),
											this.ownPlane.getB(),
											this.ownPlane.getC(),
											0);
			if(this.ownPlane.getA() != 0) {
				center = new Point(0, this.getY(), this.getZ());
			} else if(this.ownPlane.getB() != 0) {
				center = new Point(this.getX(),0, this.getZ());
			} else {
				center = new Point(this.getX(),this.getY(),0);
			}
		} else {
			// D = 0
			if(this.ownPlane.getA() != 0) {
				center = new Point(room.getLength(), this.getY(), this.getZ());
				if(this.range >= room.getLength()) 
					this.range = room.getLength();
				this.oppositePlane = new Plane(this.ownPlane.getA(),
												this.ownPlane.getB(),
												this.ownPlane.getC(),
												room.getLength());
			} else if(this.ownPlane.getB() != 0) {
				center = new Point(this.getX(),room.getWidth(), this.getZ());
				if(this.range >= room.getWidth()) 
					this.range = room.getWidth();
				this.oppositePlane = new Plane(this.ownPlane.getA(),
												this.ownPlane.getB(),
												this.ownPlane.getC(),
												room.getWidth());
			} else {
				center = new Point(this.getX(),this.getY(),room.getHeight());
				if(this.range >= room.getHeight()) 
					this.range = room.getHeight();
				this.oppositePlane = new Plane(this.ownPlane.getA(),
												this.ownPlane.getB(),
												this.ownPlane.getC(),
												room.getHeight());
			}
		}
		this.setProjectionPoint(center);
		float tanA = (float)Math.tan(Math.toRadians(this.angle));
		float temp = this.range*(tanA/2);
		if(this.ownPlane.getA() != 0) {
			Point p1 = new Point(center.getX(), center.getY() - temp, center.getZ() - temp);
			Point p2 = new Point(center.getX(), center.getY() - temp, center.getZ() + temp);
			Point p3 = new Point(center.getX(), center.getY() + temp, center.getZ() - temp);
			Point p4 = new Point(center.getX(), center.getY() + temp, center.getZ() + temp);
			this.shadow.add(p1);
			this.shadow.add(p2);
			this.shadow.add(p3);
			this.shadow.add(p4);
		} else if(this.ownPlane.getB() != 0) {
			Point p1 = new Point(center.getX() - temp, center.getY(), center.getZ() - temp);
			Point p2 = new Point(center.getX() - temp, center.getY(), center.getZ() + temp);
			Point p3 = new Point(center.getX() + temp, center.getY(), center.getZ() - temp);
			Point p4 = new Point(center.getX() + temp, center.getY(), center.getZ() + temp);
			this.shadow.add(p1);
			this.shadow.add(p2);
			this.shadow.add(p3);
			this.shadow.add(p4);
		} else {
			Point p1 = new Point(center.getX() - temp, center.getY() - temp, center.getZ());
			Point p2 = new Point(center.getX() - temp, center.getY() + temp, center.getZ());
			Point p3 = new Point(center.getX() + temp, center.getY() - temp, center.getZ());
			Point p4 = new Point(center.getX() + temp, center.getY() + temp, center.getZ());
			this.shadow.add(p1);
			this.shadow.add(p2);
			this.shadow.add(p3);
			this.shadow.add(p4);
		}
		return this.shadow;
	}
	// check a point in range of camera or not
	public boolean checkPointInRange(Point aPoint) {
		float checkVar = this.oppositePlane.getA()*aPoint.getX()
						+ this.oppositePlane.getB()*aPoint.getY()
						+ this.oppositePlane.getC()*aPoint.getZ();
		if( this.oppositePlane.getD() == checkVar ) {
			// case : aPoint is in the oppositePlane 
			
			if(this.oppositePlane.getA() != 0) {
				if(aPoint.getY() < this.shadow.get(0).getY()
				|| aPoint.getZ() < this.shadow.get(0).getZ()
				|| aPoint.getY() > this.shadow.get(2).getY()
				|| aPoint.getZ() > this.shadow.get(2).getZ()) {
					aPoint.state = State.Hidden;
					return false;
				} else {
					aPoint.state = State.InsideCamera;
					return true;
				}
			} else if(this.oppositePlane.getB() != 0) {
				if(aPoint.getX() < this.shadow.get(0).getX()
				|| aPoint.getZ() < this.shadow.get(0).getZ()
				|| aPoint.getX() > this.shadow.get(2).getX()
				|| aPoint.getZ() > this.shadow.get(2).getZ()) {
					aPoint.state = State.Hidden;
					return false;
				} else {
					aPoint.state = State.InsideCamera;
					return true;
				}
			} else {
				if(aPoint.getY() < this.shadow.get(0).getY()
				|| aPoint.getX() < this.shadow.get(0).getX()
				|| aPoint.getY() > this.shadow.get(2).getY()
				|| aPoint.getX() > this.shadow.get(2).getX()) {
					aPoint.state = State.Hidden;
					return false;
				} else {
					aPoint.state = State.InsideCamera;
					return true;
				}
			}
		} else {
			Line aLine = new Line();
			aLine.setP(aPoint);
			float slopeA = this.x - aPoint.getX();
			float slopeB = this.y - aPoint.getY();
			float slopeC = this.z - aPoint.getZ();
			
			aLine.setSlope(slopeA, slopeB, slopeC);
			float ts = this.oppositePlane.getD() - (this.oppositePlane.getA()*aPoint.getX()
											+ this.oppositePlane.getB()*aPoint.getY()
											+ this.oppositePlane.getC()*aPoint.getZ());
			
			float ms = slopeA*this.oppositePlane.getA()
					+ slopeB*this.oppositePlane.getB()
					+ slopeC*this.oppositePlane.getC();
			
			float t = ts / ms;
			Point intersection = new Point(aPoint.getX() + slopeA*t,
											aPoint.getY() + slopeB*t,
											aPoint.getZ() + slopeC*t);
			if(this.oppositePlane.getA() != 0) {
				if(intersection.getY() < this.shadow.get(0).getY()
				|| intersection.getZ() < this.shadow.get(0).getZ()
				|| intersection.getY() > this.shadow.get(2).getY()
				|| intersection.getZ() > this.shadow.get(2).getZ()) {
					intersection.state = State.Hidden;
					return false;
				} else {
					intersection.state = State.InsideCamera;
					return true;
				}
			} else if(this.oppositePlane.getB() != 0) {
				if(intersection.getX() < this.shadow.get(0).getX()
				|| intersection.getZ() < this.shadow.get(0).getZ()
				|| intersection.getX() > this.shadow.get(2).getX()
				|| intersection.getZ() > this.shadow.get(2).getZ()) {
					intersection.state = State.Hidden;
					return false;
				} else {
					intersection.state = State.InsideCamera;
					return true;
				}
			} else {
				if(intersection.getY() < this.shadow.get(0).getY()
				|| intersection.getX() < this.shadow.get(0).getX()
				|| intersection.getY() > this.shadow.get(2).getY()
				|| intersection.getX() > this.shadow.get(2).getX()) {
					intersection.state = State.Hidden;
					return false;
				} else {
					intersection.state = State.InsideCamera;
					return true;
				}
			}
		}
	}
	public boolean checkPointInLightField(Point aPoint) {
		if(this.checkPointInRange(aPoint)) {
			Line aLine = new Line();
			aLine.setP(aPoint);
			aLine.setSlope(aPoint.getX() - this.getX(), aPoint.getY() - this.getY(), aPoint.getZ() - this.getZ());
			
			if(this.ownPlane.getA() != 0) {
				if(aPoint.getX() > this.getX()) {
					for(float m = this.getX() + 0.01f; m < aPoint.getX(); m++)
						if(aLine.getPointAtXLocation(m).getState() != State.Available)
							return false;
				} else {
					for(float m = this.getX() - 0.01f; m < aPoint.getX(); m--)
						if(aLine.getPointAtXLocation(m).getState() != State.Available)
							return false;
				}
			} else if(this.ownPlane.getB() != 0) {
				if(aPoint.getY() > this.getY()) {
					for(float m = this.getY() + 0.01f; m < aPoint.getY(); m++)
						if(aLine.getPointAtYLocation(m).getState() != State.Available)
							return false;
				} else {
					for(float m = this.getY() - 0.01f; m < aPoint.getY(); m--)
						if(aLine.getPointAtYLocation(m).getState() != State.Available)
							return false;
				}
			} else {
				if(aPoint.getZ() > this.getZ()) {
					for(float m = this.getZ() + 0.01f; m < aPoint.getZ(); m++)
						if(aLine.getPointAtZLocation(m).getState() == State.Hidden || aLine.getPointAtZLocation(m).getState() == State.OnSide)
							// alpha : Avalable, Hidden, InsideCamera, OnSide
							// Hidden : aPoint -> hidden
							// OnSide: aPoint -> hidden
							return false;
				} else {
					for(float m = this.getZ() - 0.01f; m < aPoint.getZ(); m--)
						if(aLine.getPointAtZLocation(m).getState() != State.Available)
							return false;
				}
			}
			return true;
		}
		return false;
	}
	
}
