package File_format;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.Element;

public class Csv2kml {
	CSVReader cr = new CSVReader();
	public void Csv2Kml(String fileName) {
			
		ArrayList<String> rows = cr.CSVRead(fileName);
		Iterator<String> line = rows.iterator();
		line.next();
		line.next();
		while (line.hasNext())
		 {
			String s = line.next();
			String[] element_data = s.split(",");
			Element e = new Element(element_data);
			
		}
		
	}
	
}
