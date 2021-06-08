package model;

public class Frame {
    // this is 4 point(vertex) of the frame(frame is square or rectangle)
    // the frame has a center like (x,y,z) and the 1/2 side = a. If that we have
    // p1 : left, bottom ; p2 : left, top ; p3 : right, top ; p4 : right, bottom
    Point p1, p2, p3,p4; 
    Plane ownPlane;
    public Plane getOwnPlane() {
        return ownPlane;
    }

    public void setOwnPlane(Plane ownPlane) {
        this.ownPlane = ownPlane;
    }

    public Frame() {

    }

    public Frame(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;

        this.ownPlane = new Plane(p1, p2, p3);
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    public Point getP4() {
        return p4;
    }

    public void setP4(Point p4) {
        this.p4 = p4;
    }

    public boolean checkPointInsideFrame(Point p) {
        if(this.ownPlane.checkPoint(p.getX(), p.getY(), p.getZ())) {
            if(this.ownPlane.getA() != 0) {
                // the normal is like (1,0,0), so we access y and z
                if(this.p1.getY() > p.getY() || this.p3.getY() < p.getY())
                    return false;
                if(this.p1.getZ() > p.getZ() || this.p3.getZ() < p.getZ())
                    return false;
                else
                    return true;
            } else if(this.ownPlane.getB() != 0) {
                // the normal is like (0,1,0), so we access x and z
                if(this.p1.getX() > p.getX() || this.p3.getX() < p.getX())
                    return false;
                if(this.p1.getZ() > p.getZ() || this.p3.getZ() < p.getZ())
                    return false;
                else
                    return true;
            } else {
                // the normal is like (0,0,1), so we access y and z
                if(this.p1.getY() > p.getY() || this.p3.getY() < p.getY())
                    return false;
                if(this.p1.getX() > p.getX() || this.p3.getX() < p.getX())
                    return false;
                else
                    return true;
            }
        } else return false;
    }    
}
