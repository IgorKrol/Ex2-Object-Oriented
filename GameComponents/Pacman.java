package GameComponents;

import java.util.ArrayList;
import java.util.List;

import Geom.Point3D;

public class Pacman extends Figure{

	private double speed;
	private double radius;
	private double orientation = 0;
	
	public Pacman(Point3D p, int id, double speed, double radius) {
		super(p, id);
		this.speed = speed;
		this.radius = radius;
	}
		
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void move(Point3D distanation) {
		
	}

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
	
	

}
