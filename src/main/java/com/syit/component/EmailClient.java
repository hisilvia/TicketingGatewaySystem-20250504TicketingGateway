package com.syit.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.syit.model.EmailRequest;
import com.syit.model.Ticket;

@Component
public class EmailClient {

	private static final String EmailPostUrl = "http://localhost:8484/api/notification";
	
public static ResponseEntity<String> emailSendClient(EmailRequest eq) {
		
		//1.Set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//2.Create HttpEntity with JsonNode/String and headers
		HttpEntity<EmailRequest> request = new HttpEntity<>(eq, headers);
		
		//3.Create RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		
		//4.Send the POST request
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(EmailPostUrl, request, String.class);
	
		return responseEntity;
		
	}
}