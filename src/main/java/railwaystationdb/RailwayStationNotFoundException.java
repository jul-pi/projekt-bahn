package railwaystationdb;

public class RailwayStationNotFoundException extends RuntimeException { 

	/**
	 * Serial Version Unique ID
	 */
	private static final long serialVersionUID = 1L;

	public RailwayStationNotFoundException(String id) {
		super("Could not find station with code " + id + ".");
	}
}
