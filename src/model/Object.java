package model;
import georegression.struct.point.Point3D_F32;
import java.util.List;
import java.util.ArrayList;
public class Object extends AbstractObject {
	
	// list of point on the top plane of object, we only need 4 point was the frame of plane, e.x 5,6,7,8
	List<Point> topPlane;
	
	// list of point on the 4 side plane of object, we only need 4 point was the frame of plane, e.x 1,2,3,4
	List<Point> bottomPlane;
	
	// 4 side plane
	List<List<Point>> sidePlane;
	
	// When init object, we assign reference for each point of object ( Available : on plane, InsideObject : hidden )
	
	public Object(Point p1, Point p2, Point p3, Point p4,
					Point p5, Point p6, Point p7, Point p8) {
		super(p1,p2,p3,p4,p5,p6,p7,p8);
		// for() loop to assign point inside the object box is state.insideobject
		
		for(float i = p1.getX(); i <= p7.getX(); i+= 0.01) {
			for(float j = p1.getY(); i <= p7.getY(); j+= 0.01) {
				for(float k = p1.getZ(); i <= p7.getZ(); k+= 0.01) {
					if(i == p1.getX() || i == p7.getX()) {
						Point p = new Point(i,j,k);
						p.setState(State.Available);
					} else if(j == p1.getY() || j == p7.getY()) {
						Point p = new Point(i,j,k);
						p.setState(State.Available);
					} else if(k == p1.getZ() || k == p7.getZ()) {
						if((k == p1.getZ()) && (i != p1.getX()) && (i != p3.getX()) && (j != p1.getY()) && (j != p3.getY())) {
							Point p = new Point(i,j,k);
							p.setState(State.InsideObject);
						} else {
							Point p = new Point(i,j,k);
							p.setState(State.Available);
						}			
					} else {
						Point p = new Point(i,j,k);
						p.setState(State.InsideObject);
					}
				}
			}
		}
	}
		
	
	
	
}
