package com.syit.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleController {

	@GetMapping("/role")
	public List<GrantedAuthority> getUserRole(Principal principal) {
		
		List<GrantedAuthority> retrieveRoles = new ArrayList<>();
		//Retrieve the current user's role
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("userName: "+authentication.getName()); //eg.susan
		
		if(authentication != null && authentication.isAuthenticated()) {
			Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
			
			if(roles != null) {
				roles.forEach(role -> System.out.println("Role: "+role.getAuthority()));
				retrieveRoles.addAll(roles);
			}else
				System.out.println("No user authenticated.");
		}
		System.out.println("retrieveRoles: "+retrieveRoles);
		return retrieveRoles;
	}
	
	
}
