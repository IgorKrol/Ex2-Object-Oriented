package File_format;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.Element;
import GIS.Layer;

public class Csv2kml {

	private CSVReader cr = new CSVReader();
	private KMLWriter kw = new KMLWriter();

	public void Csv2Kml(String fileName, String outputPath) {

		ArrayList<String> rows = cr.CSVRead(fileName);
		Iterator<String> line = rows.iterator();
		//FIRST LIKE IS DESCRIPTION,SKIP SECOND LINE.
		Layer l = new Layer(line.next());
		line.next();

		while (line.hasNext())
		{
			String s = line.next();
			String[] element_data = s.split(",");
			l.add(new Element(element_data));
		}



	}

}
