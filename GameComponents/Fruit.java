package GameComponents;

import Geom.Point3D;

public class Fruit extends Figure {

	private static int fruitCounters = 0;
	private int weight;

	public Fruit(Point3D p, int id, int w) {
		super(p, id);
		weight = w;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String toString() {
		return "F:" + getCoords().toString();
	}
	


}
