package GameComponents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import File_format.CSVReader;
import Geom.Point3D;

public class Game {
	
	private List<Pacman> pacmans = new ArrayList<Pacman>();
	private List<Fruit> fruits = new ArrayList<Fruit>();
	
	public Game (String csvFile) {
		
		// read the file and devides it to rows
		CSVReader cr = new CSVReader();
		ArrayList<String> rows = cr.CSVRead(csvFile);
		Iterator<String> line = rows.iterator();
		line.next();
		
		// sorts whether its a pacman or a fruit which is then added to the appropriate list.
		while(line.hasNext()) {
			String s = line.next();
			
			if(s.startsWith("P")) {				// therefore a pacman
				addPacman(s);	
			}
			
			else { 								// a fruit
				addFruit(s);
			}
		}
		
	}
	
	
	void addPacman(String str) {
		
		String[] fields = str.split(",");
		
		int id = Integer.parseInt(fields[1]);
		Point3D point = new Point3D(Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));
		double speed = Double.parseDouble(fields[4]); 
		double radius = Double.parseDouble(fields[5]); 
		
		pacmans.add(new Pacman(point, id, speed, radius));
	}
	
	void addPacman(Point2D p) {
		
	}
	
	void addFruit(String str) {
		
		String[] fields = str.split(",");
		
		int id = Integer.parseInt(fields[1]);
		Point3D point = new Point3D(Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));
		int weight = Integer.parseInt(fields[4]); 
		
		fruits.add(new Fruit(point, id, weight));
	}
	
	void addFruit(Point2D p) {
		
	}

}
