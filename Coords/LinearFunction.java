package Coords;

import Geom.Point2D;

	//this function represent linear function: y-y1 = m(x-x1)
public class LinearFunction {
	int x,y;
	private double m;
	
	public LinearFunction(Point2D ps, Point2D pd){
		m = (ps.y()-pd.y())/(ps.x()-pd.x());
		x = (int)pd.x();
		y = (int)pd.y();
	}
	
	public Point2D XY(Point2D ps) {
		Point2D xy;
		
		
		return xy;
	}
	
	
}
