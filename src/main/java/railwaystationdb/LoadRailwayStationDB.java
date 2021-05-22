package railwaystationdb;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
 
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * This class loads the database from a CSV file into
 * a {@link RailwayStationRepository} consisting of
 * {@link RailwayStation} objects. 
 */
@Configuration
public class LoadRailwayStationDB { 
	
	ClassPathResource CSVResource = new ClassPathResource("table.csv");
	
	@Bean
	CommandLineRunner initDatabase(RailwayStationRepository repository) {
		return args -> {
	    	List<RailwayStation> RailwayStations = CSVConverter
	    			.readFromCSV(CSVResource);
	    	repository.saveAll(RailwayStations); 
	    };
	  }
} 