package railwaystationdb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort; 
import org.springframework.http.HttpStatus;
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

}
