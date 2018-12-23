package Threads;

import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import Coords.MyCoords;
import GUI.MyFrame;
import GUI.MyFrame.JPanelBG;
import GameComponents.Fruit;
import GameComponents.Pacman;
import GameComponents.Path;
import Geom.Point2D;
import Geom.Point3D;

public class AnimationThread extends Thread {
	boolean notDone = true;
	List<Pacman> pacmans;
	List<Fruit> fruits;
	JPanelBG bg;
	/**
	 * this function moves all points single tick.
	 * each point moves fraction of distance to next point.
	 */
	private void movePoints() {
		int counter=0;
		Iterator<Pacman> pi = pacmans.iterator();
		MyCoords mc = new MyCoords();
		Point2D eaten;
		//move all points
		while (pi.hasNext()) {
			Pacman p = pi.next();
			ArrayList<Point3D> path = p.getPath().getPathPoints();
			double dis;		//distance
			Point3D vec;	//vector
			int rot;	//rotations
			boolean left,up;	//is point left?up?
//			System.out.println(path.size());
			if(path.size()>1) {
				//calc points distance and vector
				dis = mc.distance3d(path.get(0),path.get(1));
				vec = mc.vector3D(path.get(0),path.get(1));
				//rotations between points should be distance/speed (which gives time)
				rot = (int)(dis/10/p.getSpeed());
				double x,y;
				//is p left/up to next point?
				left = (p.getCoords().x() <= path.get(1).x())? true: false;
				up = (p.getCoords().y() >= path.get(1).y())? true: false;
				
				//USELESS CODE: just reduce vector/rotations from point coords. 
				x = left? p.getCoords().x() - (vec.x()/rot) : p.getCoords().x() - (vec.x()/rot);
				y = up? p.getCoords().y() - (vec.y()/rot) : p.getCoords().y() - (vec.y()/rot);
//				System.out.println(x + ", " + y);
				//set cords for p
				p.setCoords(new Point3D(x,y));
				//if p passed next point, remove first point in path.
				if (p.getCoords().x() <= path.get(1).x() && !left ||
						p.getCoords().x() >= path.get(1).x() && left ||
						p.getCoords().y() <= path.get(1).y() && up ||
						p.getCoords().y() >= path.get(1).y() && !up) {
					p.getPath().removeP(0);
					p.setCoords(new Point3D(p.getPath().getPathPoints().get(0)));
					isFruitEaten(p.getCoords().x(), p.getCoords().y());
				}
			}
			
			//if all paths left with single point, end thread.
			else counter++;
			if (counter == pacmans.size()) notDone = false;
		}
	}
	
	// CHECKS IF FRUIT IS EATEN IN ORDER TO PAINT ON IT WITH X LATER
	private void isFruitEaten(double x, double y) {
		Iterator<Fruit> itF = fruits.iterator();
		while(itF.hasNext()) {
			Fruit f = itF.next();
			if(Math.abs((f.getCoords().x()- x))<0.000001 
					&& Math.abs((f.getCoords().y()- y))<0.000001 
					&& f.isEaten() == false) {
				f.setEaten(true);
				break;
			}
		}
	}
	
	/**
	 * init
	 * @param pacs = pacmans list
	 * @param bg = graphics
	 */
	public AnimationThread(List<Pacman> pacs, List<Fruit> fruits, JPanelBG bg) {
		pacmans = pacs;
		this.fruits = fruits;
		this.bg = bg;

	}

	public void run(){
		while(notDone) {
			try {
				sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			movePoints();
			bg.repaint();
		}

	}


}
