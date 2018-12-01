package GIS;

import Geom.Point3D;
import java.sql.Time;
import java.util.Date;
public class MetaData implements Meta_data {
	
	private String description;
	//FOR ELEMENTS: mac, ssid, authMode, firstSeen, channel, rssi, accuracyMeters, type;
	private long utc;
	
	public MetaData(String mac, String ssid, String authMode, String firstSeen, String channel, String rssi, String accuracyMeters, String type) {
	
		this.description = mac+","+ssid+","+authMode+","+firstSeen+","+channel+","+rssi+","+accuracyMeters+","+type;
		java.sql.Timestamp ts = java.sql.Timestamp.valueOf(firstSeen);
		utc = ts.getTime();
		
	}
	
	public MetaData(String d) {
		description = d;
		Date date = new Date();
		utc = date.getTime();
	}
	@Override
	public String toString() {
		return description;
	}
	@Override
	public long getUTC() {
		return utc;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
