package GameComponents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import File_format.CSVReader;
import Geom.Point2D;
import Geom.Point3D;
import Resourses.Map;

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

	
	void addPacman(Point2D p, Point2D frameSizePixels) {
		
		int id = generatePacmanID(pacmans);
		Map m = new Map();
		Point3D pCoords = m.PixelToCoords(p, frameSizePixels);
		pacmans.add(new Pacman(pCoords, id, 1, 1));
		
	}

	int generatePacmanID(List <Pacman> pArr) {

		int id;
		int maxID = - 1;
		Iterator<Pacman> it = pArr.iterator();
		Figure f;
		
		if(it.hasNext()) {								// if arr isnt empty, search for max id
			f = it.next();  
			maxID = f.getId();
			
			while(it.hasNext()) {
				f=it.next();
				if(f.getId() > maxID) {
					maxID = f.getId();
				}
			}
		}	
		id = maxID + 1;									// if arr was empty, take id 0, if it wasnt, take highest id number + 1 since it must be available.

		return id;
	}

	void addFruit(String str) {

		String[] fields = str.split(",");

		int id = Integer.parseInt(fields[1]);
		Point3D point = new Point3D(Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));
		int weight = Integer.parseInt(fields[4]); 

		fruits.add(new Fruit(point, id, weight));
	}

	void addFruit(Point2D p, Point2D frameSizePixels) {
		
		int id = generateFruitID(fruits);
		Map m = new Map();
		Point3D pCoords = m.PixelToCoords(p, frameSizePixels);
		pacmans.add(new Pacman(pCoords, id, 1, 1));
		
	}

	int generateFruitID(List <Fruit> fArr) {

		int id;
		int maxID = - 1;
		Iterator<Fruit> it = fArr.iterator();
		Figure f;
		
		if(it.hasNext()) {								// if arr isnt empty, search for max id
			f = it.next();  
			maxID = f.getId();
			
			while(it.hasNext()) {
				f=it.next();
				if(f.getId() > maxID) {
					maxID = f.getId();
				}
			}
		}	
		id = maxID + 1;									// if arr was empty, take id 0, if it wasnt, take highest id number + 1 since it must be available.

		return id;
	}
}
