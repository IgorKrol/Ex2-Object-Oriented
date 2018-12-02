package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Project extends ArrayList<GIS_layer> implements GIS_project {

	private ArrayList<GIS_layer> proj;
	private MetaData md;

	public Project(String description) {
		proj = new ArrayList<GIS_layer>();
		md = new MetaData(description);
	}


	@Override
	public Meta_data get_Meta_data() {
		return md;
	}

	@Override
	public boolean add(GIS_layer l) {
		try {
			proj.add(l);
			return true;
		} catch (Exception e2) {
			return false;
		}

	}

	@Override
	public Iterator<GIS_layer> iterator(){
		return proj.iterator();
	}

	public ArrayList<GIS_layer> getArray(){
		return proj;
	}

}
