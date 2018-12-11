package Figures;

import java.awt.Point;
import java.awt.geom.Point2D;

import Geom.Point3D;

public class Figure {
	private final Point3D MAP_STARTING_POINT = new Point3D(35.20236,32.10568,0);
	private final Point3D MAP_END_POINT = new Point3D(35.21232,32.10190);
	private final Point3D MAP_FRAME_SIZE = new Point3D(35.21232-35.20236,-(32.10190-32.10568),0);
	private Point3D coords;
	private int id;
	//INIT
	public Figure(Point3D p, int id) {
		coords = p;
		this.id = id;
	}

	//(0,0) = 35.20236,32.10568
	//(BR corner) = 35.21232,32.10190
	public Point3D coordsToPixel(int bX, int bY) {
		
		//CHECK FOR BOUNDS ERROR
		if (coords.x() < MAP_STARTING_POINT.x() ||
				coords.x() > MAP_END_POINT.x() || 
				coords.y() > MAP_STARTING_POINT.y() || 
				coords.y() < MAP_END_POINT.y()) return null;
		
		//PIXELS TO MOVE
		double cX = coords.x() - MAP_STARTING_POINT.x();
		double cY = MAP_STARTING_POINT.y() - coords.y();
		
		//CORDINATES PER PIXEL
		double pixelX = MAP_FRAME_SIZE.x()/bX;
		double pixelY = MAP_FRAME_SIZE.y()/bY;
		return new Point3D(pixelX*cX, pixelY*cY);

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
