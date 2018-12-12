package GameComponents;

import java.awt.Point;
import java.awt.geom.Point2D;

import Geom.Point3D;

public class Figure {

	private Point3D coords;
	private int id;
	//INIT
	public Figure(Point3D p, int id) {
		coords = p;
		this.id = id;
	}
	
	public Point3D getCoords() {
		return coords;
	}

	public void setCoords(Point3D coords) {
		this.coords = coords;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
