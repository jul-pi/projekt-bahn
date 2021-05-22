package railwaystationdb;

public class RailwayStationNotFoundException extends RuntimeException { 

	public RailwayStationNotFoundException(String id) {
		super("Could not find station with code " + id + ".");
	}
}
