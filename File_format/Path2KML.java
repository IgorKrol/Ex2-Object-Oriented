package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import GameComponents.Pacman;
import GameComponents.Path;

public class Path2KML {

	public void writePath2KML(List<Pacman> pList, String outputpath) {
		
		//MAKE FILE WRITER
		PrintWriter pw = null;
		try 		{
			pw = new PrintWriter(new File(outputpath));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		//MAKE 'NEW LIST' FOR INSERT TO FILE LATER
		StringBuilder sb = new StringBuilder();
				//FIRST 2 ROWS:
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
				sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\">"
						+ "<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>"
						+ "</Icon></IconStyle></Style><Style id=\"yellow\">"
						+ "<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style>"
						+ "<Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder>"
						+ "<name>Wifi Networks</name>\n");
		Iterator<Pacman> pListIterator = pList.iterator();
		while(pListIterator.hasNext()) {
			Pacman singlePacman = pListIterator.next();
			Path singlePath = singlePacman.getPath();
			for(int i = 0; i < singlePath.getPathPoints().size(); i++) {
			sb.append("<Placemark>\n");
			sb.append("<name><![CDATA["+ singlePacman.getId() +"]]></name>");
			sb.append("<description><![CDATA["
					+ "Speed: <b>"+ singlePacman.getSpeed() +"</b>"
					+ "<br/>Radius <b>"+ singlePacman.getRadius()+"</b>"
					+ "]]></description>");
			String color = i==0?"#yellow":"red";
			sb.append("<styleUrl>"+color+"</styleUrl>\n");
			sb.append("<TimeStamp>\r\n" + 
					"        <when>" + (singlePath.getTimesForEachFruit().get(i))*1000 + "</when>\r\n" + 
					"      </TimeStamp>");
			sb.append("<Point>\n");
			String[] coords = singlePath.getPathPoints().get(i).toString().split(",");
			sb.append("<coordinates>"+coords[0]+","+coords[1]+","+coords[2]+"</coordinates>\n</Point>\r\n" + 
					"</Placemark>");	
			
			}
		}
		sb.append("</Folder>\r\n" + "</Document></kml>");
		pw.write(sb.toString());
		pw.close();
		
	}
	
}
