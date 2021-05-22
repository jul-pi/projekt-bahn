package railwaystationdb;

import javax.persistence.Entity; 
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty; 

/**
 * Represents a railway station.
 */
@Entity 
public class RailwayStation { 

	@Id private String abbreviation; 
	private String name;
	private String shortName;
	private String type;
	private String status;
	private String primaryLocationCode;
	private String UIC;
	private String rb;
	private String eligibleSince;
	private String eligibleUntil;
	private String netzKey;
	private String scheduleRelevance;
	private String scheduleLimit;
	
	private final static int INPUT_LENGTH = 13;
	
	public RailwayStation() {}
	
	/**
	 * Constructor for a RailwayStation based on a given 
	 * string array. This array needs to contain exactly
	 * 13 elements.
	 * @params params a string containing the parameters
	 */
	private RailwayStation(String[] params) {
		this.abbreviation = params[0];
		this.name = params[1];
		this.shortName = params[2];
		this.type = params[3];
		this.status = params[4];
		this.primaryLocationCode = params[5];
		this.UIC = params[6];
		this.rb = params[7];
		this.eligibleSince = params[8];
		this.eligibleUntil = params[9];
		this.netzKey = params[10];
		this.scheduleRelevance = params[11];
		this.scheduleLimit = params[12];
	}  
	
	/**
	 * This method tries to create an instance of RailwayStation
	 * given the input string array. If this is not possible, a
	 * null reference is returned.
	 * @param params a string array which contains the parameters.
	 * @return a RailwayStation object based on the string  
	 * parameters.
	 */
	public static RailwayStation tryParseString(String[] params) {
		if (params.length == INPUT_LENGTH) {
			return new RailwayStation(params);
		}
		return null;
	}
	
	@JsonProperty("Typ")
	public String getType() {
		return this.type;
	} 
	
	@JsonProperty("Name")
	public String getName() {
		return this.name;
	}
	
	@JsonProperty("Kurzname")
	public String getShortName() {
		return this.shortName;
	}

	@Override
	public String toString() {
		return "RailwayStation [abbreviation=" + abbreviation + ", name=" + name + ", shortName=" + shortName
				+ ", type=" + type + ", status=" + status + ", primaryLocationCode=" + primaryLocationCode + ", UIC="
				+ UIC + ", rb=" + rb + ", eligibleSince=" + eligibleSince + ", eligibleUntil=" + eligibleUntil
				+ ", netzKey=" + netzKey + ", scheduleRelevance=" + scheduleRelevance + ", scheduleLimit="
				+ scheduleLimit + "]";
	} 
}
