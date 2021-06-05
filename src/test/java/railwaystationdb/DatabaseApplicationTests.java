package railwaystationdb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections; 

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort; 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
 
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DatabaseApplicationTests {
	
    @LocalServerPort
    private int serverPort;

	@Autowired
	private RailwayStationController controller;

	private TestRestTemplate restTemplate = new TestRestTemplate(); 
	

	private String SHORT_NAME = "Short Name";
	private String LONG_NAME = "Long Name";
	private String ABBREVIATION = "TEST";

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void requestIfAvailableStationReturnsOK() throws Exception {
		
		ResponseEntity<String> response = restTemplate.
				getForEntity("http://localhost:" + serverPort + 
						"/betriebsstelle/aamp", String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));  
	}
	
	@Test
	public void requestIfAvailableStationReturnsNotFound() throws Exception {
		
		ResponseEntity<String> response = restTemplate.
				getForEntity("http://localhost:" + serverPort + 
						"/betriebsstelle/0000", String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));  
	}
	
	@Test
	public void checkIfEntryIsCorrect() throws Exception { 
		
		ResponseEntity<String> response = restTemplate.
				getForEntity("http://localhost:" + serverPort + 
						"/betriebsstelle/aamp", String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode name = root.path("Kurzname"); 
		assertTrue(name.asText().equals("Anckelmannsplatz"));
	}
	
 
	@Test
	public void testIfPostWorks() throws Exception {
		
		RailwayStation testStation = new RailwayStation(ABBREVIATION); 
		testStation.setShortName(SHORT_NAME);
		testStation.setName(LONG_NAME); 

		ObjectMapper mapper = new ObjectMapper(); 
		JsonNode jsonNode = mapper.valueToTree(testStation);  
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); 
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		ResponseEntity<String> response = restTemplate
				.postForEntity("http://localhost:"+serverPort+"/register", jsonNode, String.class);  
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}
	
	@Test void testIfPostWorksByCheckingIfAvailablePerGet() throws Exception { 
		
		testIfPostWorks();
		
		ResponseEntity<String> response2 = restTemplate.
				getForEntity("http://localhost:" + serverPort + 
						"/betriebsstelle/" + ABBREVIATION, String.class); 
		
		assertTrue(response2.getStatusCode().equals(HttpStatus.OK)); 
		 
		ObjectMapper mapper2 = new ObjectMapper(); 
		JsonNode name2 = mapper2.readTree(response2.getBody()).path("Kurzname"); 
		assertTrue(name2.asText().equals(SHORT_NAME));
	}

}
