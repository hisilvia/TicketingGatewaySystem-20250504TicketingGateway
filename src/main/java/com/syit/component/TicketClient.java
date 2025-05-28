package com.syit.component;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syit.model.Ticket;

@Component
public class TicketClient {

	private static final String ticketPostUrl = "http://localhost:8383/ticket/ticketPost";
	private static final String ticketGetUrl = "http://localhost:8383/ticket/ticketGetByName/";
	private static final String ticketGetAllUrl = "http://localhost:8383/ticket/ticketGetAll";
	private static final String ticketUpdateStatusUrl = "http://localhost:8383/ticket/updateTicket";
	
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
	
	//https://www.youtube.com/watch?v=rp0H85kWZf4
	public List<Ticket> ticketGetClient(String name) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Ticket>> response = restTemplate.exchange(
				ticketGetUrl+name,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Ticket>>() {}
				);
		
		List<Ticket> tickets = response.getBody();
		
		return tickets;
	}
	
	public ResponseEntity<String> ticketGetAllClient() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//if you're using spring security or token
		//headers.add("Authorization", headerValue);
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> response =restTemplate.exchange(ticketGetAllUrl, HttpMethod.GET, entity, String.class);
		
		return response;
	}
	
	/*  Wroks fine, but restTemplate.put() return empty. So cannot use it on button
	public void updateStatus(Ticket ticket) {	
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(ticketUpdateStatusUrl, ticket);
	}
	*/
	
	//public ResponseEntity<String> updateStatus(Ticket t) {
	public String updateStatus(String node) {	
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//Create HttpEntity with JsonNode/String and headers
		HttpEntity<String> request = new HttpEntity<>(node, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(ticketUpdateStatusUrl, request, String.class);
			return "Success";
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			System.err.println("Error response: " + e.getResponseBodyAsString());
			return "Failed";
		}
	}

	/*
	public ResponseEntity<String> updateStatus(Ticket ticket) {	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);	
		HttpEntity<Ticket> entity = new HttpEntity<>(ticket, headers);
		RestTemplate restTemplate = new RestTemplate();
		
		
		ResponseEntity<String> response =restTemplate.exchange(ticketUpdateStatusUrl, HttpMethod.PUT, entity, String.class);
		
		return response;
	}
	*/
}
