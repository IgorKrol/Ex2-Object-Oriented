package GameComponents;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

public class Path {

	ArrayList<Point3D> pacManPath;

	public Path() {
		pacManPath = new ArrayList<Point3D>();
	}

	public Path(Path p) {
		Iterator<Point3D> ip = p.iterator();
		while(ip.hasNext())
			pacManPath.add(ip.next());
	}

	public Path copy() {
		Path newPath = new Path();
		for (Point3D point3d : pacManPath) {
			newPath.add(point3d);
		}
		return newPath;
	}
	/**
	 * Calculates path's distance thru all points.
	 * @return double distance
	 */
	public double distance() {
		double distance = 0;
		Iterator<Point3D> ip = pacManPath.iterator();
		if (!ip.hasNext()) return distance;
		
		Point3D a = ip.next();
		while(ip.hasNext()) {
			Point3D b = ip.next();
			distance+= a.distance3D(b);
			a = b;
		}
		return distance;
	}

	public void add(Point3D point) {
		pacManPath.add(point);
	}

	public Iterator<Point3D> iterator(){
		return pacManPath.iterator();
	}
	/**
	 * toString method
	 * @return String (x,y,z),(x,y,z)...
	 */
	public String toString() {
		Iterator<Point3D> ip = pacManPath.iterator();
		if (!ip.hasNext()) return "";
		String s = ip.next().toString();
		while(ip.hasNext()) {
			s+=","+ ip.next().toString();
		}
		return s;
		
	}


}
