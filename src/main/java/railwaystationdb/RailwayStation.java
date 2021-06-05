package railwaystationdb;

import java.time.LocalDate;

import javax.persistence.Entity; 
import javax.persistence.Id;
 
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

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
	private int UIC;
	
	@CsvBindByName(column = "RB")
	private int rb;

	@CsvDate(value = "yyyyMMdd") 
	@CsvBindByName(column = "gültig von")
	private LocalDate eligibleSince;

	@CsvDate(value = "yyyyMMdd")
	@CsvBindByName(column = "gültig bis")
	private LocalDate eligibleUntil;
	
	@CsvBindByName(column = "Netz-Key")
	private String netzKey;
	
	@CsvBindByName(column = "Fpl-rel")
	private String scheduleRelevance;
	
	@CsvBindByName(column = "Fpl-Gr")
	private String scheduleLimit; 
	
	public RailwayStation() {}  
	
	public RailwayStation(String abbreviation) {
		this.abbreviation = abbreviation;
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
	
	//@JsonIgnore
	public String getAbbreviation() {
		return this.abbreviation;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
}
