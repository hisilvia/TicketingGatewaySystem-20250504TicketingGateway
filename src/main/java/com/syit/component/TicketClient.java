package com.syit.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syit.model.Ticket;

@Component
public class TicketClient {

	private static final String ticketPostUrl = "http://localhost:8383/ticket/ticketPost";
	private static final String ticketGetUrl = "http://localhost:8383/ticket/ticketGet";
	
	public static ResponseEntity<String> ticketPostClient(Ticket t) {
		
		//1.Set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//2.Create HttpEntity with JsonNode/String and headers
		HttpEntity<Ticket> request = new HttpEntity<>(t, headers);
		
		//3.Create RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		
		//4.Send the POST request
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(ticketPostUrl, request, String.class);
	
		return responseEntity;
		
	}
	
	public String ticketGetClient(String data) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(ticketGetUrl+data, String.class);
		
		String response = responseEntity.getBody();
		
		return response;
	}
}
