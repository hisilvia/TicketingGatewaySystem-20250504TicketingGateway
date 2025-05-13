package com.syit.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syit.model.Ticket;

@Controller
public class TicketController {

	private final Path fileStorageLocation;

    @Autowired
    public TicketController() throws IOException {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }
    
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String ticketPostUrl = "http://localhost:8383/ticketPost";
	private static final String ticketGetUrl = "http://localhost:8383/ticketGet";
	
	@GetMapping(value="/getTicket")
	public String getHotelForm(Model model) {
		
		model.addAttribute("ticket", new Ticket() );
		model.addAttribute("message: "+ "submitTicket successfully");
		
		return "ticketForm";
	}
	
	@PostMapping(value="/submitTicket")
	public String submitTicketForm(@ModelAttribute("ticket") Ticket ticket, 
			@RequestParam("file") MultipartFile file,Model model) throws IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        model.addAttribute("message", "Uploaded successfully: " + fileName);
        
		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode ticketJsonNode = objectMapper.valueToTree(ticket);
			
			HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<JsonNode> requestEntity = new HttpEntity<>(ticketJsonNode, headers);

            JsonNode response = restTemplate.postForObject(ticketPostUrl, requestEntity, JsonNode.class);

            if (response != null) {
                System.out.println("Response from /ticketGateway/addTicket: " + response);
            }

            model.addAttribute("message", "Ticket submitted successfully!");			
			
		}catch(Exception e) {
			model.addAttribute("message", "Failed to submit ticket: " + e.getMessage());
			
			e.printStackTrace();
		}		
		return "ticketForm";
		//return "ticketResult";  // refers to ticketResult.jsp
	}
	
	@GetMapping(value="/getTicketFromMicroservice")
	public String ticketGetClient(String data) {
		
		RestTemplate restTemplate= new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(ticketGetUrl+data, String.class);
		String response = responseEntity.getBody();
	
		return response;
	}	
}
