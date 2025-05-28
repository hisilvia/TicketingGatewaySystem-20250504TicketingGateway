package com.syit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.syit.model.Ticket;

@Controller
public class ViewTicketController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value="/user/viewUserTicket")
	public String showTicket(Model model, String currentUserName) {
		
		//Retrieve the current userName
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		currentUserName = authentication.getName();
		System.out.println("currentUserName: "+currentUserName);
		
		String apiUrl = "http://localhost:8282/user/viewTicket/";
		
		ResponseEntity<List<Ticket>> response = restTemplate.exchange(
                apiUrl+currentUserName,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ticket>>() {}
        );

        List<Ticket> tickets = response.getBody();
		model.addAttribute("ticketList", tickets);
		
		return "viewUserTicket";
	}
	
	@GetMapping(value="/allTicketsView")
	public String showAllTickets(Model model) {
	
		String apiUrl = "http://localhost:8282/viewAllTickets";
		
		ResponseEntity<List<Ticket>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ticket>>() {}
        );

        List<Ticket> tickets = response.getBody();
		model.addAttribute("ticketList", tickets);
		//model.addAttribute("file", Ticket.fileAttachementPath[]);
		
		return "allTicketsView";
	}	
}


