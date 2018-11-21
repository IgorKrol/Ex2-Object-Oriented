package Coords;
import java.math.*;
import Geom.Point3D;

public class MyCoords implements coords_converter{
	
	//variables:
	private final int EARTH_RADIOS = 6371000;
	private final double PI = Math.PI;
	
	
	
	
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		
		return null;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double dis = 0; //distance between 2 points
		
		double difRx = difR(gps0.x(), gps1.x());
		double latM = Math.sin(difRx) * EARTH_RADIOS;
		
		double difRy = difR(gps0.y(), gps1.y());
		double lon_norm = Math.cos(gps1.x() * PI/180);
		
		double lonM =  Math.sin(difRy) * lon_norm * EARTH_RADIOS;
		dis = Math.sqrt(latM*latM + lonM*lonM);
		return dis;
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		Point3D vector = new Point3D(difR(gps0.x(), gps1.x()) ,difR(gps0.y(), gps1.y()) ,gps1.z() - gps0.z()); 
		
		return vector;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] azimuth_elevation_dist_Calc = new double[3];
		
		return azimuth_elevation_dist_Calc;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private double difR (double num1, double num2) {
		
		return (num1 - num2) * PI / 180;
	}

}
