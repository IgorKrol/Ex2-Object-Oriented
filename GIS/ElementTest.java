package GIS;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class ElementTest {

	@Test
	void test() {		
		
//		Date date = new Date();
//		String strr = "";
//		long lo = date.getDate();
		
		String[] str = {"amit", "igor", "OOP", "ex2", "arielwifi", "2018-02-12 17:18:23","50", "60", "70", "end", "this"};
		Element el = new Element(str);
		
		String pExpected = "50,60,70";
		String mdExpected = "amit,igor,OOP,ex2,arielwifi,2018-02-12 17:18:23,end,this";
		
		assertEquals(pExpected, (el.getGeom()).toString());			
		assertEquals(mdExpected, (el.getData()).toString());
		
		
//		MetaData md = new MetaData("amit", "igor", "OOP", "ex2", "arielwifi", "2018-02-12 17:18:23", "end", "this");
//		Point3D p = new Point3D(50, 60, 70);
//		Element el = new Element(md, p);
		
		

	}
	

}
