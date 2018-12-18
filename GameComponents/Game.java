package GameComponents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import File_format.CSVReader;
import Geom.Point2D;
import Geom.Point3D;
import Resourses.Map;

public class Game {

	private List<Pacman> pacmans;
	private List<Fruit> fruits;
	
	/**
	 * Function for game deep copy
	 * @param g = Game object
	 * @return deep copied Game object
	 */
	public Game Copy(Game g) {
		Game newGame = new Game();
		//Copy pacmans to new GAME
		Iterator<Pacman> pm = g.getPacmans().iterator();
		Pacman newPacman;
		while (pm.hasNext()) {
			newPacman = pm.next();
			newGame.getPacmans().add(newPacman);
		}
		//Copy fruits to new GAME
		Iterator<Fruit> fru = g.getFruits().iterator();
		Fruit newFruit;
		while (fru.hasNext()) {
			newFruit = fru.next();
			newGame.getFruits().add(newFruit);
		}
		
		return newGame;

	}

	public List<Pacman> getPacmans(){
		return pacmans;
	}

	public List<Fruit> getFruits(){
		return fruits;
	}

	public Game() {
		pacmans = new ArrayList<Pacman>();
		fruits = new ArrayList<Fruit>();
	}
	/**
	 * Initiating game with csv file path.
	 * @param csvFile
	 */
	public Game (String csvFile) {
		//LIST INIT:
		pacmans = new ArrayList<Pacman>();
		fruits = new ArrayList<Fruit>();
		
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

	/**
	 * function adds pacman with String parameter
	 * @param str String
	 */
	public void addPacman(String str) {

		String[] fields = str.split(",");

		int id = Integer.parseInt(fields[1]);
		Point3D point = new Point3D(Double.parseDouble(fields[3]), Double.parseDouble(fields[2]));
		double speed = Double.parseDouble(fields[4]); 
		double radius = Double.parseDouble(fields[5]); 

		pacmans.add(new Pacman(point, id, speed, radius));
	}

	/** 
	 * funtion adds pacman with mouse click
	 * @param p = pixel point on screen
	 * @param frameSizePixels = frame current size
	 */
	public void addPacman(Point2D p, Point2D frameSizePixels) {

		int id = generatePacmanID(pacmans);
		Map m = new Map();
		Point3D pCoords = m.PixelToCoords(p, frameSizePixels);
		pacmans.add(new Pacman(pCoords, id, 1, 1));

	}
	/**
	 * function generates ids for pacmans, USED WHEN adding new pacman
	 * @param pArr
	 * @return
	 */
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
	/**
	 * Function adds fruits with String parameter
	 * @param str String
	 */
	public void addFruit(String str) {

		String[] fields = str.split(",");

		int id = Integer.parseInt(fields[1]);
		Point3D point = new Point3D(Double.parseDouble(fields[3]), Double.parseDouble(fields[2]));
		int weight = Integer.parseInt(fields[4]); 

		fruits.add(new Fruit(point, id, weight));
	}
	/**
	 * Function adds new fruit with mouse click
	 * @param p = pixel point in frame
	 * @param frameSizePixels = frame size
	 */
	public void addFruit(Point2D p, Point2D frameSizePixels) {

		int id = generateFruitID(fruits);
		Map m = new Map();
		Point3D pCoords = m.PixelToCoords(p, frameSizePixels);
		pacmans.add(new Pacman(pCoords, id, 1, 1));

	}
	/**
	 * Function generates fruit id
	 * @param fArr
	 * @return
	 */
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
	/**
	 * pacman iterator
	 * @return
	 */
	public Iterator<Pacman> iteratorP(){
		return this.pacmans.iterator();
	}
	
	public Iterator<Fruit> iteratorF(){
		return this.fruits.iterator();
	}
}
