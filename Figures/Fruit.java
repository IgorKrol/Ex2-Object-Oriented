package Figures;

import Geom.Point3D;

public class Fruit extends Figure {
	
	private static int fruitCounters = 0;
	private int weight;
	
	public Fruit(Point3D p, int id, int w) {
		super(p, id);
		weight = w;
	}
	

}
