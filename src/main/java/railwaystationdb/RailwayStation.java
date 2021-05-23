package railwaystationdb;

import javax.persistence.Entity; 
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName; 

/**
 * Represents a railway station.
 */
@Entity 
public class RailwayStation { 
	
	@CsvBindByName(column = "Abk", required = true)
	@Id 
	private String abbreviation; 
	
	@CsvBindByName(column = "Name", required = true)
	private String name;
	
	@CsvBindByName(column = "Kurzname", required = true)
	private String shortName;
	
	@CsvBindByName(column = "Typ")
	private String type;
	
	@CsvBindByName(column = "Betr-Zust")
	private String status;
	
	@CsvBindByName(column = "Primary location code")
	private String primaryLocationCode;
	
	@CsvBindByName(column = "UIC")
	private String UIC;
	
	@CsvBindByName(column = "RB")
	private String rb;
	
	@CsvBindByName(column = "gültig von")
	private String eligibleSince;
	
	@CsvBindByName(column = "gültig bis")
	private String eligibleUntil;
	
	@CsvBindByName(column = "Netz-Key")
	private String netzKey;
	
	@CsvBindByName(column = "Fpl-rel")
	private String scheduleRelevance;
	
	@CsvBindByName(column = "Fpl-Gr")
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
