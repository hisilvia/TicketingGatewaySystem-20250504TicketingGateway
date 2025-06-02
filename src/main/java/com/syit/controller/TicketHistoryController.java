package com.syit.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syit.component.TicketClient;
import com.syit.model.TicketHistory;

@Controller
public class TicketHistoryController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	TicketClient ticketClient;
	
	@GetMapping("/ticketHistory/{id}")
	public String viewTicketHistory(@PathVariable long id, Model model) {
		String node = ticketClient.ticketHistoryPostClient(id);
		System.out.println("node: "+node);
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<TicketHistory> ticketHistoryList = null;
		try {
			ticketHistoryList = mapper.readValue(node, new TypeReference<List<TicketHistory>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("ticketHistoryList", ticketHistoryList);
		
		return "viewTicketHistory";
	}
}
