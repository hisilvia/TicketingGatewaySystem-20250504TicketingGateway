package com.syit.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syit.component.TicketClient;


@RestController
@RequestMapping("/api")
public class TicketController {
	
	private static final String ticketPostUrl = "http://localhost:8383/ticket/ticketPost";
	private final Path fileStorageLocation;

    @Autowired
    public TicketController() throws IOException {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }
       
	@Autowired
	ObjectMapper objectMapper;
	
	@PostMapping(value="/addTicket")
	public ResponseEntity<String> uploadTicket(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("ticketData") String ticketDataJson) {  //"ticketData" comes from jQuery

		JsonNode ticketData;
		
        try {
            // Parse JSON string to JsonNode
            ticketData = objectMapper.readTree(ticketDataJson);

            // Access fields in JsonNode
            String title = ticketData.get("title").asText();
            String description = ticketData.get("description").asText();
            String createdBy = ticketData.get("createdBy").asText();
            String assignee = ticketData.get("assignee").asText();
            String priority = ticketData.get("priority").asText();
            String status = ticketData.get("status").asText();
            String category = ticketData.get("category").asText();
            String date = ticketData.get("creationDate").asText();
            

            // Log the data
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("CreatedBy: " + createdBy);
            System.out.println("Assignee: " + assignee);
            System.out.println("Priority: " + priority);
            System.out.println("Status: " + status);
            System.out.println("Category: " + category);
            System.out.println("CreationDate: " + date);
            
            /*
            //Prepare the request body for the second API
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("title", title);
            body.add("description", description);
            body.add("createdBy", createdBy);
            body.add("assignee", assignee);
            body.add("priority", priority);
            body.add("status", status);
            //body.add("category", category);            
            */
            List<String> fileNameStr = new ArrayList<>();
            
            // Handle file upload (optional save)
            for(MultipartFile file : files) {
	            if (!file.isEmpty()) {
	            	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	            	//fileNameStr.add(fileName);
	            	
	                //String fileName = file.getOriginalFilename();
	                System.out.println("File uploaded: " + fileName);
	                Path targetLocation = this.fileStorageLocation.resolve(fileName);
	                fileNameStr.add(targetLocation.toString());
	                System.out.println("Path: "+targetLocation.toString());
	                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	                //file.transferTo(new java.io.File("uploads\\" + fileName));
	            }
            }       
            //body.add("fileAttachementPath", fileNameStr);
            /*
            //Set the headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            */
            //Use RestTemplate to forward the request
            RestTemplate restTemplate = new RestTemplate();
            //ResponseEntity<String> response = restTemplate.postForEntity(ticketPostUrl, requestEntity, String.class);
            ResponseEntity<String> response = restTemplate.postForEntity(ticketPostUrl, ticketData, String.class);
            
            return ResponseEntity.ok("Ticket uploaded successfully!"+response.getBody());
            //return "Ticket uploaded successfully!"+response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing ticket");
            //return "Error processing ticket";
        }
    }
	
	
	
	
	/*
	@Autowired
	TicketClient ticketClient;
	
	
	@PostMapping(value="/addTicket")
	public JsonNode addTicket(@RequestBody JsonNode node) {
		System.out.println("TicketingGateway-controller-title: "+node.get("title"));
		return ticketClient.ticketPostClient(node);
	}
	*/
	/*
	@GetMapping(value="/getTicketFromMicroservice")
	public String ticketGetClient(String data) {
		
		RestTemplate restTemplate= new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(ticketGetUrl+data, String.class);
		String response = responseEntity.getBody();
	
		return response;
	}	
	 */
}
/*
modelAttribute: Binds the form to your model class (ticket).
method="post": Specifies the HTTP method for form submission.
enctype="multipart/form-data": Crucial for handling file uploads.
path="fileAttachementPathh": Binds the file input to the fileAttachementPath field in your model.
  
  
  
*/
/*
public String submitTicketForm(@ModelAttribute("ticket") Ticket ticket, 
		@RequestParam("file") MultipartFile file, Model model) throws IOException {

	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Path targetLocation = this.fileStorageLocation.resolve(fileName);
    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

    model.addAttribute("message", "Uploaded successfully: " + fileName);
    System.out.println("fileName: "+fileName);
    
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
	//return "ticketForm";
	return "ticketResult";  // refers to ticketResult.jsp
}
*/
