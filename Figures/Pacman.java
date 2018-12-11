package Figures;

import Geom.Point3D;

public class Pacman extends Figure{

	private double speed;
	private double radios;
	private double orientation = 0;
	
	public Pacman(Point3D p, int id, double speed, double radios) {
		super(p, id);
		this.speed = speed;
		this.radios = radios;
	}
	
	public void move(Point3D distanation) {
		
	}
	
	

}
