package com.syit.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TicketClient {

	private static final String ticketPostUrl = "http://localhost:8383/ticket/ticketPost";
	private static final String ticketGetUrl = "http://localhost:8383/ticket/ticketGet";
	/*
	public JsonNode ticketPostClient(JsonNode node) {
		
		//1.Set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//2.Create HttpEntity with JsonNode/String and headers
		HttpEntity<String> request = new HttpEntity<String>(node.toString(), headers);
		
		//3.Create RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		
		//4.Send the POST request
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(ticketPostUrl, request, Object.class);
		
		//5.Process the response
		Object objects = responseEntity.getBody();
		
		//6.Create ObjectMapper instance
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		
		return returnObj;
		
	}
	*/
	public String ticketGetClient(String data) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(ticketGetUrl+data, String.class);
		
		String response = responseEntity.getBody();
		
		return response;
	}
}
