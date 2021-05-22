package railwaystationdb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * A tool used to convert the input from a CSV file into
 * a list of objects.
 */
@Component
public class CSVConverter {
	/**
	 * Returns a List of {@link RailwayStation} by scanning a 
	 * CSV file. If a line does not meet the necessary format
	 * to be converted into an object, it is skipped. 
	 * @param path The file location of the CSV file. 
	 * @returns a List of {@link RailwayStation} corresponding 
	 * to the entries in the file.
	 */
	public static List<RailwayStation> readFromCSV(ClassPathResource CSVResource) { 
		
		final Logger log = LoggerFactory.getLogger(CSVConverter.class);
		List<RailwayStation> RailwayStationData = new ArrayList<RailwayStation>();
		
		try { 
			Scanner scanner = new Scanner(CSVResource.getFile()); 
			
		    while (scanner.hasNextLine()) { 
		    	String row = scanner.nextLine();
		    	RailwayStation tempRailwayStation = RailwayStation.tryParseString(
	    				getEntriesFromLine(row));
		    	
		    	if (tempRailwayStation != null) {
		    		RailwayStationData.add(tempRailwayStation); 
		    	}
		    	else {
		    		log.warn("Could not parse due to invalid string length: "+row); 
		    	}
		    } 
		} catch (IOException e) {
			
		    e.printStackTrace();
		    
		} 
	    return RailwayStationData;
	} 
	
	public static Resource loadEmployeesWithClassPathResource() {
	    return new ClassPathResource("resources/table.csv");
	}

	private static String[] getEntriesFromLine(String line) {  
		return line.split(";", -1);
	}
}
