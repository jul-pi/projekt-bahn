package railwaystationdb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController; 

/**
 * A controller class for the RailwayStation Database.
 * Currently, it features a GET method and a POST
 * method.
 * @param id The four-character abbreviation of a 
 * {@link RailwayStation} object is the ID in the {@link RailwayStationRepository}.
 */ 
@RestController
public class RailwayStationController {
	
	private final RailwayStationRepository repository;
	
	RailwayStationController(RailwayStationRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/betriebsstelle/{id}")
	RailwayStation StationById(@PathVariable String id) {
		return repository.findById(id.toUpperCase())
				.orElseThrow(()->new RailwayStationNotFoundException(id)); 
	}
	
	@PostMapping("/register")
	public RailwayStation addRailwayStation( @RequestBody 
			RailwayStation railwayStation) { 
        return repository.save(railwayStation);
	}
}
