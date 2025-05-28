package com.syit.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	

	@GetMapping(value="/")
	public String homePage(Model model, Principal principal) {
		model.addAttribute("message1", "UserName");
		model.addAttribute("message2", principal.getName());
			
		return "home";  // refers to home.jsp
	}
	
	@GetMapping(value="/user")
	public String userPage(Model model, Principal principal) {
		model.addAttribute("message1", "empName");
		model.addAttribute("message2", principal.getName());
			
		return "userDashboard";  
	}
	
	@GetMapping(value="/manager")
	public String managerPage(Model model, Principal principal) {
		model.addAttribute("message1", "empName");
		model.addAttribute("message2", principal.getName());
			
		return "managerDashboard";  
	}
	
	@GetMapping(value="/admin")
	public String adminPage(Model model, Principal principal) {
		model.addAttribute("message1", "empName");
		model.addAttribute("message2", principal.getName());
			
		return "adminDashboard";  
	}
	
	
	

}
