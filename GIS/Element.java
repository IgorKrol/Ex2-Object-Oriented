package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class Element implements GIS_element {

	private MetaData md;
	private Point3D p;

	public Element(String[] a) {
		md = new MetaData(a[0], a[1], a[2], a[3], a[4], a[5], a[9], a[10]);
		p = new Point3D(a[6]+","+a[7]+","+a[8]);
	}

	@Override
	public Geom_element getGeom() {

		return p;
	}

	@Override
	public Meta_data getData() {
		return md;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}

}
