package railwaystationdb; 
 
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
public class RailwayStationDBLoader { 
	
	ClassPathResource CSVResource = new ClassPathResource("table.csv"); 
	
	@Bean
	CommandLineRunner initDatabase(RailwayStationRepository repository) {
		return args -> { 
			repository.saveAll(CSVConverter.readFromCSV(CSVResource));
			};
	  }
	
} 