package Threads;

import java.util.Iterator;

import Algo.ShortestPathAlgo;
import Coords.MyCoords;
import GUI.MyFrame;
import GameComponents.Game;
import GameComponents.Path;
import Geom.Point3D;
import Resourses.Map;

public class MyThread extends Thread {
	private Game myGame;
	private MyFrame myFrame;
	private int rotation = 1000;
	private ShortestPathAlgo spa;
	private MyCoords mc = new MyCoords();

	public MyThread(String Name, Game g, ShortestPathAlgo spa) {
		super(Name);
		myGame = myGame.Copy(g);
		myFrame.setVisible(true);
		this.spa = spa;
	}


	public void run() {
	//	Point3D startPath;
	//	startPath should be a point of the first pacman who needs to move	
		
		Iterator<Point3D> itr = p.iterator();				// iterator for path some path chosen
		Point3D newP;
//		spa.getGameWithPaths();						// need to use spa class to transform the calcs to live actions

		while(itr.hasNext()) {
			newP = itr.next();
			
			for(int i = 0; i < rotation; i++) {
				movePac(startPath, newP);
				
				try {
					Thread.sleep(500);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// after this loop we need to set startPath with a new point of another pacman (or the same one if he's the quickest)
		}
	}
	
	private synchronized void movePac(Point3D startPoint, Point3D desPoint) {
		
		Point3D vector = mc.vector3D(startPoint, desPoint);
		vector.setX(vector.x()/rotation);
		vector.setY(vector.y()/rotation);
		startPoint = mc.add(startPoint, vector);
		
	}
}
