package com.syit.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syit.component.TicketClient;
import com.syit.model.Ticket;

import jakarta.servlet.http.HttpSession;



@RestController
//@RequestMapping("/api")
public class TicketController {
	
	private final Path fileStorageLocation;

    @Autowired
    public TicketController() throws IOException {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }
       
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	TicketClient ticketClient;
	
	@PostMapping(value="/addTicket")
	public ResponseEntity<String> uploadTicket(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("ticketData") String ticketDataJson) {  //"ticketData" comes from jQuery

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("userName: "+authentication.getName()); //eg.susan
		
		JsonNode ticketData;
		
        try {
            // Parse JSON string to JsonNode
            ticketData = objectMapper.readTree(ticketDataJson);

            // Access fields in JsonNode
            String title = ticketData.get("title").asText();
            String description = ticketData.get("description").asText();
            String priority = ticketData.get("priority").asText();
            String category = ticketData.get("category").asText();
            //String date = ticketData.get("creationDate").asText();

            // Log the data
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Priority: " + priority);
            System.out.println("Category: " + category);
            //System.out.println("CreationDate: " + date);
           
            Ticket ticket = new Ticket();
            ticket.setTitle(title);
            ticket.setDescription(description);
            ticket.setPriority(priority);
            ticket.setCategory(category);
            //ticket.setCreationDate(new Date(date));
            ticket.setCreatedBy(authentication.getName());
            ticket.setStatus("OPEN");
            ticket.setAssignee(null);
            
            List<String> attachFileName = new ArrayList<>();
            List<String> fileNameStr = new ArrayList<>();
            
            // Handle file upload (optional save)
            for(MultipartFile file : files) {
	            if (!file.isEmpty()) {
	            	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	            	attachFileName.add(fileName);
	            	
	                //String fileName = file.getOriginalFilename();
	                System.out.println("File uploaded: " + fileName);
	                Path targetLocation = this.fileStorageLocation.resolve(fileName);
	                fileNameStr.add(targetLocation.toString());
	                System.out.println("Path: "+targetLocation.toString());
	                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	                //file.transferTo(new java.io.File("uploads\\" + fileName));
	            }
            }
            ticket.setFileAttachementPath(attachFileName);
           
            ResponseEntity<String> response = ticketClient.ticketPostClient(ticket);
           return ResponseEntity.ok("Ticket uploaded successfully!"+response.getBody());
  
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing ticket");
            
        }
    }
	
	@GetMapping("/user/viewTicket/{name}")
	public List<Ticket> getTicketsFromMicroservice(@PathVariable String name){		
		return ticketClient.ticketGetClient(name);
	}

	@GetMapping("/viewAllTickets")
	public ResponseEntity<String> allTickets(){
		return ticketClient.ticketGetAllClient();
	}
	/*
	@PutMapping("/manager/approve")
	public void updateStatusApp(@RequestBody Ticket ticket) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("userName: "+authentication.getName()); //eg.susan
		ticket.setAssignee(authentication.getName());
		//
		ticket.setStatus("APPROVED");
		ticketClient.updateStatus(ticket);
	} 
	
	@PostMapping("/manager/approve")
	public ResponseEntity<String> updateStatusApp(@RequestBody Ticket ticket) {
		
		ticket.setStatus("APPROVED");
		return ticketClient.updateStatus(ticket);
	} 
	
	
	@PutMapping("/manager/reject")
	public void updateStatusRej(@RequestBody Ticket ticket, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("userName: "+authentication.getName()); //eg.susan
		ticket.setAssignee(authentication.getName());
		ticket.setStatus("REJECTED");
		ticketClient.updateStatus(ticket);
	}
	
	@PutMapping("/admin/resolve")
	public void updateStatusRes(@RequestBody Ticket ticket, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("userName: "+authentication.getName()); //eg.susan
		ticket.setAssignee(authentication.getName());
		ticket.setStatus("RESOLVED");
		ticketClient.updateStatus(ticket);
	}
	*/
}
/*
modelAttribute: Binds the form to your model class (ticket).
method="post": Specifies the HTTP method for form submission.
enctype="multipart/form-data": Crucial for handling file uploads.
path="fileAttachementPathh": Binds the file input to the fileAttachementPath field in your model. 
*/
/* This method is definitely fine, runs well! However, I wanna divided it in two part
private static final String ticketGetUrl = "http://localhost:8383/ticket/ticketGetByName/";
@GetMapping("/user/viewTicket/{name}")
public List<Ticket> getTicketsFromMicroservice(@PathVariable String name){
	
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
*/
