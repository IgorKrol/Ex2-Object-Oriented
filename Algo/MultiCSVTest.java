package Algo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import File_format.Csv2kml;

class MultiCSVTest {

	@Test
	void testMultiCSV() {
		Csv2kml c2k = new Csv2kml();
		c2k.Csv2Kml("C:\\Users\\Amit\\Desktop\\assignments\\OOP\\third assignment\\Ex2\\data\\WigleWifi_20171201110209.csv", "C:\\Users\\Amit\\Desktop\\assignments\\OOP\\third assignment\\Ex2\\data\\WigleWifi_20171201110209.kml");
		c2k.Csv2Kml("C:\\Users\\Amit\\Desktop\\assignments\\OOP\\third assignment\\Ex2\\data\\WigleWifi_20171203085618.csv", "C:\\Users\\Amit\\Desktop\\assignments\\OOP\\third assignment\\Ex2\\data\\WigleWifi_20171203085618.kml");
		
		MultiCSV test = new MultiCSV("test");
		test.MultiCSV("C:\\Users\\Amit\\Desktop\\assignments\\OOP\\third assignment\\Ex2\\data");
		
		String path = "C:\\Users\\Amit\\Desktop\\assignments\\OOP\\third assignment\\Ex2\\data\\WigleWifi_20171201110209.kml";
		File file = new File(path);
		
		assertTrue(file.exists());
	}


}
