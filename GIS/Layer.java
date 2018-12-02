package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Layer extends ArrayList<GIS_element> implements GIS_layer {

	private ArrayList<GIS_element> layer;
	private MetaData md;

	public Layer(String description) {
		layer = new ArrayList<GIS_element>();
		md = new MetaData(description);
	}


	@Override
	public Meta_data get_Meta_data() {
		return md;
	}

	@Override
	public boolean add(GIS_element e) {
		try {
			layer.add(e);
			return true;
		} catch (Exception e2) {
			return false;
		}

	}

	@Override
	public Iterator<GIS_element> iterator(){
		return layer.iterator();
	}

	public ArrayList<GIS_element> getArray(){
		return layer;
	}

}
