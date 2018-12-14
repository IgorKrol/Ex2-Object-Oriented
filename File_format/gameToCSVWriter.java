package File_format;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import GameComponents.Fruit;
import GameComponents.Pacman;

public class gameToCSVWriter {

	public void CSVWrite(List<Pacman> pacmans, List<Fruit> fruits, String filePath, String fileName) {

		try {
			PrintWriter pw = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();

		Iterator<Pacman> Pit = pacmans.iterator();
		sb.append("Type,Id,Lat,Lon,Alt,Speed/Weight,Radius\n");
		while(Pit.hasNext()) {							
			Pacman p = Pit.next();
			sb.append("P," + p.getId() + "," + p.getCoords().x() + "," + p.getCoords().y() + "," + p.getCoords().z() + "," 
					+ p.getSpeed() + "," + p.getRadius() + "\n");
		}

		Iterator<Fruit> Fit = fruits.iterator();
		while(Fit.hasNext()) {
			Fruit f = Fit.next();
			sb.append("F" + f.getId() + "," + f.getCoords().x() + "," + f.getCoords().y() + "," + f.getCoords().z() + ","
					+ f.getWeight() + "\n");
		}
		
		pw.write(sb.toString());
		pw.close();
	}

}
