package com.syit.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syit.component.TicketClient;
import com.syit.model.Ticket;

@Controller
public class UpdateTicketController {

	/*
	private static final String ticketUpdateApproveUrl = "http://localhost:8282/manager/approve";
	private static final String ticketUpdateRejectUrl = "http://localhost:8282/manager/reject";
	private static final String ticketUpdateResolveUrl = "http://localhost:8282/admin/resolve";
	private static final String ticketAllViewUrl = "http://localhost:8282/viewAllTickets";
	*/
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	TicketClient ticketClient;
	
	@PostMapping("/user/update/reopen/{id}")
	public String reopenTicket(@PathVariable String id, Model model, Principal principal) {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("id", id);
		jsonMap.put("status", "REOPENED");
		jsonMap.put("actionBy", principal.getName());
		
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			model.addAttribute("message", "Failed to convert from String to Json");
			return "redirect:/user/viewUserTicket/{id}";
		}
		String result = ticketClient.updateStatus(json);
		if (result.equals("Success")) {
			model.addAttribute("message", "Successfully Reopened Ticket");
		} else {
			model.addAttribute("message", "Failed To Reopen Ticket");
		}
		return "redirect:/user/viewUserTicket/{id}";
	}
	
	@PostMapping("/user/update/close/{id}")
	public String closeTicket(@PathVariable String id, Model model, Principal principal) {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("id", id);
		jsonMap.put("status", "CLOSED");
		jsonMap.put("actionBy", principal.getName());
		
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			model.addAttribute("message", "Failed to convert from String to Json");
			return "redirect:/user/viewUserTicket/{id}";
		}
		String result = ticketClient.updateStatus(json);
		if (result.equals("Success")) {
			model.addAttribute("message", "Successfully Closed Ticket");
		} else {
			model.addAttribute("message", "Failed To Close Ticket");
		}
		return "redirect:/user/viewUserTicket/{id}";
	}
	
	
	@PostMapping("/manager/update/approve/{id}")     
	public  String updateToApprove(@PathVariable String id, Model model, Ticket ticket, Principal principal) {
		
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("status", "APPROVED");
		jsonMap.put("id", ticket.getId());
		jsonMap.put("actionBy", principal.getName());
		
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			model.addAttribute("message", "Failed to convert from String to Json");
			return "redirect:/allTicketsView";
		}
		String result = ticketClient.updateStatus(json);
		if (result.equals("Success")) {
			model.addAttribute("message", "Successfully Approved Ticket");
		} else {
			model.addAttribute("message", "Failed To Approve Ticket");
		}
		
		String resultHistory = ticketClient.ticketHistoryPostClient(ticket.getId());
		
		return "redirect:/allTicketsView";
	}
	
	@PostMapping("/manager/update/reject/{id}")     
	public  String updateToReject(@PathVariable String id, Model model, Ticket ticket, Principal principal) {
		
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("status", "REJECTED");
		jsonMap.put("id", ticket.getId());
		jsonMap.put("actionBy", principal.getName());
		
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			model.addAttribute("message", "Failed to convert from String to Json");
			return "redirect:/allTicketsView";
		}
		String result = ticketClient.updateStatus(json);
		if (result.equals("Success")) {
			model.addAttribute("message", "Successfully Rejected Ticket");
		} else {
			model.addAttribute("message", "Failed To Reject Ticket");
		}
		
		String resultHistory = ticketClient.ticketHistoryPostClient(ticket.getId());
		
		return "redirect:/allTicketsView";
	}
	
	@PostMapping("/admin/update/resolve/{id}")     
	public  String updateToResolve(@PathVariable String id, Model model, Ticket ticket, Principal principal) {
		
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("status", "RESOLVED");
		jsonMap.put("id", ticket.getId());
		jsonMap.put("assignee", principal.getName());	
		jsonMap.put("actionBy", principal.getName());
		
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			model.addAttribute("message", "Failed to convert from String to Json");
			return "redirect:/allTicketsView";
		}
		String result = ticketClient.updateStatus(json);
		if (result.equals("Success")) {
			model.addAttribute("message", "Successfully Resolved Ticket");
		} else {
			model.addAttribute("message", "Failed To Resolved Ticket");
		}
		
		String resultHistory = ticketClient.ticketHistoryPostClient(ticket.getId());
		
		return "redirect:/allTicketsView";
	}
}
