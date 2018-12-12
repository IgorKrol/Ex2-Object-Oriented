package Geom;

public class Point2D implements Geom_element {
	private double x,y;
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double x() {
		return x;
	}
	public double y() {
		return y;
	}
	public Point2D difference(Point2D p2) {
		return new Point2D(Math.abs(x-p2.x()), Math.abs(y-p2.y()));
	}
	@Override
	public double distance3D(Point3D p) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double distance2D(Point3D p) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
