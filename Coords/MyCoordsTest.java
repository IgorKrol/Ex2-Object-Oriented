package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class MyCoordsTest {

	@Test				// The method distance3d
	void test() {

		MyCoords mc = new MyCoords();
		Point3D p1 = new Point3D(32.103315,35.209039,670);
		Point3D p2 = new Point3D(32.106352,35.205225,650);

		double distanceActual = mc.distance3d(p1, p2);
		double distanceExpected = 493.0523318;
		
		assertTrue((distanceExpected - distanceActual) < 0.15);

	}

	@Test				// The method isValid (Point)
	void test2() {
		
		MyCoords mc = new MyCoords();
		Point3D p1 = new Point3D(32.103315,35.209039,670);
		Point3D p2 = new Point3D(500,35.205225,650);
		Point3D p3 = new Point3D(20, -400, 20);
		
		boolean isTrue = mc.isValid_GPS_Point(p1);
		assertTrue(isTrue);
		
		isTrue = mc.isValid_GPS_Point(p2);
		assertTrue(!(isTrue));
		
		isTrue = mc.isValid_GPS_Point(p3);
		assertTrue(!(isTrue));
	}
	
}
