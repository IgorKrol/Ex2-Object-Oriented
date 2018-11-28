package Coords;
import java.math.*;
import Geom.Point3D;

public class MyCoords implements coords_converter{
	
	//variables:
	private final int EARTH_RADIOS = 6371000;
	private final double PI = Math.PI;
	
	
	
	
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
																	
		Point3D gps2 = new Point3D(0,0,0);
		double difRx = Math.asin(local_vector_in_meter.x() / EARTH_RADIOS);

		gps2.setX(gps.x() - (difRx * 180/PI));
		
		double lon_norm = Math.cos(gps2.x() * 180 / PI);
		double difRy = Math.asin(local_vector_in_meter.y() / lon_norm * EARTH_RADIOS); 
		
		gps2.setY(gps.y() - (difRy * 180 / PI));
		
		gps2.setZ(local_vector_in_meter.z() + gps.z());
		return gps2;
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
		
		azimuth_elevation_dist_Calc[2] = distance3d(gps0, gps1);
		double deltY = (gps1.x() - gps0.x());
		double deltX = (gps1.y() - gps0.y());
		double alpha = Math.toDegrees(Math.atan(Math.abs(deltY/deltX)));
//		double alpha = Math.atan2(Math.abs(deltX),Math.abs(deltY));
		if (deltX>0 && deltY>0) azimuth_elevation_dist_Calc[0] = alpha;
		if (deltX<0 && deltY>0) azimuth_elevation_dist_Calc[0] = 180 - alpha;
		if (deltX<0 && deltY<0) azimuth_elevation_dist_Calc[0] = 180 + alpha;
		if (deltX>0 && deltY<0) azimuth_elevation_dist_Calc[0] = 360 - alpha;
		azimuth_elevation_dist_Calc[1] = Math.toDegrees(Math.asin((gps1.z() - gps0.z()) / azimuth_elevation_dist_Calc[2]));

		return azimuth_elevation_dist_Calc;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if (p.x() > 90 || p.x() < -90 || p.y() > 180 || p.y() < -180 || p.z() > Long.MAX_VALUE || p.z() < -450)
			return false;
		return true;
	}
	
	private double difR (double num1, double num2) {
		
		return (num1 - num2) * PI / 180;
	}


}
