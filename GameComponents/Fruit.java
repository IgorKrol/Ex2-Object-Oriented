package GameComponents;

import Geom.Point3D;

public class Fruit extends Figure {

	private double weight;
	private boolean eaten = false;

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public Fruit(Point3D p, int id, double w) {
		super(p, id);
		weight = w;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String toString() {
		return "F:" + getCoords().toString();
	}
	


}
