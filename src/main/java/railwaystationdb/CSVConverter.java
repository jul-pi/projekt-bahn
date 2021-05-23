package railwaystationdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;

/**
 * A tool used to convert the input from a CSV file into
 * a list of objects.
 */
@Component
public class CSVConverter {
	/**
	 * Returns a List of {@link RailwayStation} using a 
	 * CSV file.  
	 * @param CSVResource The file location of the CSV file as a {@link ClassPathResource}. 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @returns a List of {@link RailwayStation} corresponding 
	 * to the entries in the file.
	 */ 
	
	public static List<RailwayStation> readFromCSV(ClassPathResource CSVResource) 
			throws FileNotFoundException, IOException {
		
		FileReader reader = new FileReader(CSVResource.getFile());
		List<RailwayStation> RailwayStationData = new ArrayList<RailwayStation>();
		
		RailwayStationData = new CsvToBeanBuilder<RailwayStation>(reader)
				.withType(RailwayStation.class)
				.withSeparator(';')
				.build()
				.parse();
		
		return RailwayStationData;
	} 
}
