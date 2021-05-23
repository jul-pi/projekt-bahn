package railwaystationdb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class DatabaseApplicationTests {
	 
	private int port = 9099; 

	@Autowired
	private RailwayStationController controller;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void requestIfAvailableStationShouldReturnOK() throws Exception {
		
		ResponseEntity<String> response = restTemplate.
				getForEntity("http://localhost:" + port + "/betriebsstelle/aamp", String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK)); 
	}
	
	@Test
	public void checkIfEntryIsCorrect() throws Exception { 
		
		ResponseEntity<String> response = restTemplate.
				getForEntity("http://localhost:" + port + "/betriebsstelle/aamp", String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode name = root.path("Kurzname"); 
		assertTrue(name.asText().equals("Anckelmannsplatz"));
	}

}
