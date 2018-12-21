package Coords;

import java.util.ArrayList;
import java.util.Iterator;

import GUI.MyFrame;
import GameComponents.Path;
import Geom.Point2D;
import Geom.Point3D;
import Resourses.Map;

/**
 * this class responsible for moving point with path
 * @author igork
 *
 */
public class Move {
	private Path thisPath;
	private MyCoords myCoords;
	private MyFrame frame;
	private Point2D mainP;
	private ArrayList<Point2D> path;
	private Point2D mVec;
	private int mSpeed;
	private Map m;
	Point2D frameSizePixels;
	private int index;
	private int moveCounter = 0;
	private boolean done = false;
	
	public Move(Path p) {
		index = 1;
		m = new Map();
		myCoords = new MyCoords();
		frameSizePixels = frame.FrameSize();
		Iterator<Point3D> pIte = p.iterator();
		path = new ArrayList<Point2D>();
		mainP = m.CoordsToPixel(pIte.next(), frameSizePixels);
		path.add(mainP);
		while(pIte.hasNext()) {
			path.add(m.CoordsToPixel(pIte.next(), frameSizePixels));
		}
	}
	/**
	 * updates moving info
	 */
	public void moveInfo(Point2D p1, Point2D p2) {
		Point3D gps0 = m.PixelToCoords(p1, frameSizePixels);
		Point3D gps1 = m.PixelToCoords(p2, frameSizePixels);
		Point3D p3DVec = myCoords.vector3D(gps0, gps1);
		mVec = new Point2D(p3DVec.x(),p3DVec.y());
		double dis = myCoords.distance3d(gps0, gps1);
		double time = thisPath.getTime(index) - thisPath.getTime(index-1);
		index++;
		mSpeed = (int)(dis/time);
		moveCounter = 0;
		
	}
	public int getMSpeed() {
		return mSpeed;
	}
	/**
	 * calculates mainP position
	 * @return new mainP position
	 */
	public Point2D movePoint() {
		if (done) return path.get(0);
		moveCounter++;
		if (moveCounter >= mSpeed) {
			path.remove(0);
			try {
			moveInfo(path.get(0), path.get(1));
			
			}
			catch (IndexOutOfBoundsException e) {
				done = true;
				return path.get(0);
			}
		}
		mainP = new Point2D(mainP.x() + (mVec.x()/mSpeed), mainP.y() + (mVec.y()/mSpeed));
		
		return mainP;
		
	}

}
