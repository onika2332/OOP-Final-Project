package model;
import georegression.struct.line.*;
import georegression.struct.point.*;
public class Line extends LineParametric3D_F32 {
	public Line() {
		super();
	}
	
	public Line(Point p1, Point p2) {
		this();
		this.setP(p1);
		this.setSlope(p1.getX() - p2.getX(), p1.getY() - p2.getY(), p1.getZ() - p2.getZ());
	}
	
	public Line(Point p, Vector v) {
		this();
		this.setP(p);
		this.setSlope(v.getX(), v.getY(), v.getZ());
	}
	public Point getPointAtXLocation(float x) {
		 Point aPoint = (Point)this.getPoint();
		 aPoint.setX(x);
		 float t = (x - this.getP().getX()) / this.getSlopeX();
		 aPoint.setY(this.getP().getY() + t*this.getSlopeY());
		 aPoint.setZ(this.getP().getZ() + t*this.getSlopeZ());
		 return aPoint;
	}
	
	public Point getPointAtYLocation(float y) {
		 Point aPoint = (Point)this.getPoint();
		 aPoint.setY(y);
		 float t = (y - this.getP().getY()) / this.getSlopeY();
		 aPoint.setX(this.getP().getX() + t*this.getSlopeX());
		 aPoint.setZ(this.getP().getZ() + t*this.getSlopeZ());
		 return aPoint;
	}
	
	public Point getPointAtZLocation(float z) {
		 Point aPoint = (Point)this.getPoint();
		 aPoint.setZ(z);
		 float t = (z - this.getP().getZ()) / this.getSlopeZ();
		 aPoint.setY(this.getP().getY() + t*this.getSlopeY());
		 aPoint.setX(this.getP().getX() + t*this.getSlopeX());
		 return aPoint;
	}
}

