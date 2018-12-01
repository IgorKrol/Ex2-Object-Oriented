package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Layer extends ArrayList<GIS_element> implements GIS_layer {
	
	private ArrayList<GIS_element> layer;
	
	
	public Layer() {
	layer = new ArrayList<GIS_element>();
	}


	@Override
	public Meta_data get_Meta_data() {
		return null;
	}

}
