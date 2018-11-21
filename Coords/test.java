package Coords;

import Geom.Point3D;

public class test {

	public static void main(String[] args) {
		
		Point3D p1 = new Point3D(33.1033, 35.209039, 670.0);
		Point3D p2 = new Point3D(32.103316, 35.209009, 500.0);
		
		double testDistance = MyCoords.distance3d(p1,p2);
		
	}

}
