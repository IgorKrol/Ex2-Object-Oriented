package File_format;

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
import java.util.ArrayList;

	public class CSVReader {
	
	    public ArrayList<String> CSVRead(String csvFile)
	    {
	    	ArrayList<String> fileByRows = new ArrayList<String>(); 
	        String line = "";
	        String cvsSplitBy = ",";

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
	        {
	            while ((line = br.readLine()) != null) 
	            {
	            	fileByRows.add(line);
	            }

	        } catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
			return fileByRows;

	    }

	}
