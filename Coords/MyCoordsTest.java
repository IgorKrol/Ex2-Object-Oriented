package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class MyCoordsTest {

	@Test
	void test() {
		
		MyCoords mc = new MyCoords();

//		Point3D p1 = new Point3D(170, 120,670);
//		Point3D p2 = new Point3D(100, 170,650);
		Point3D p1 = new Point3D(32.103315,35.209039,670);
		Point3D p2 = new Point3D(32.106352,35.205225,650);
		
		double[] azimuth_elevation_dist = mc.azimuth_elevation_dist(p1, p2);
		
		for (double d : azimuth_elevation_dist) {
			System.out.println(d + ",");
		}
	}

}
