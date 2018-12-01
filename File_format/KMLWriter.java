package File_format;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;

import GIS.Element;
import GIS.GIS_element;
import GIS.Layer;

public class KMLWriter 
{

	public void KMLWrite(Layer l, String outputPath)
	{
		//		String fileName = ;
		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(outputPath));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}

		StringBuilder sb = new StringBuilder();
		//FIRST 2 ROWS:
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\">"
				+ "<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>"
				+ "</Icon></IconStyle></Style><Style id=\"yellow\">"
				+ "<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style>"
				+ "<Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder>"
				+ "<name>Wifi Networks</name>\n");

		Iterator<GIS_element> li = l.iterator();
		while(li.hasNext()) {
			GIS_element e = li.next();
			String[] info = e.getData().toString().split(",");

			sb.append("<Placemark>\n");
			sb.append("<name><![CDATA["+ info[1] +"]]></name>");
			sb.append("<description><![CDATA["
					+ "MAC: <b>"+ info[0] +"</b>"
					+ "<br/>AuthMode: <b>"+ info[2]+"</b>"
					+ "<br/>Date: <b>"+ info[3] +"</b>"
					+ "<br/>Timestamp: <b>"+ e.getData().getUTC()+"</b>"
					+ "<br/>Channel: <b>"+info[4]+"</b>"
					+ "<br/>RSSI: <b>"+info[5]+"</b>"
					+ "<br/>AccuracyMeters: <b>"+info[6]+"</b>"
					+ "<br/>Type: <b>"+info[7]+"</b>"
					+ "]]></description>");
			sb.append("<styleUrl>#red</styleUrl>\n");
			sb.append("<Point>\n");
			sb.append("<coordinates>"+e.getGeom().toString()+"</coordinates>\n</Point>\r\n" + 
					"</Placemark>");			
			
		}
		sb.append("</Folder>\r\n" + "</Document></kml>");
		pw.write(sb.toString());
		pw.close();
	}
}






