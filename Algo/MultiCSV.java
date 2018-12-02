package Algo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import File_format.CSVReader;
import GIS.Element;
import GIS.GIS_project;
import GIS.Layer;
import GIS.Project;

public class MultiCSV {

	GIS_project p;
	CSVReader cr = new CSVReader();
	public MultiCSV(String name) {
		p = new Project(name);
	}

	
	public void MultiCSV(String path) {

		File dir = new File(path);
		File[] directoryListing = dir.listFiles();

		for (File file: directoryListing) {
			if (file.isDirectory()) {
				MultiCSV(file.toString());
			}
			else {
				for (File child : directoryListing) {
					String str = child.getPath();
					if(str.contains(".csv")) {

						ArrayList<String> rows = cr.CSVRead(str);
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

						p.add(l);
					}// if contains .csv
				}//for directoryListing
			}//else
		}//main for
	}
	
	public GIS_project getProject() {
		return p;
	}
}
