package com.syit.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syit.domain.Role;
import com.syit.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public Set<Role> getDefaultRole() { //A collection set<> that contains no duplicate elements
		//Role role = roleRepository.findById((long) 1).orElse(null);
		
		Role role = roleRepository.findByRoleName("USER");
		Set<Role> setRole = new HashSet<>();
		setRole.add(role);
		return setRole;
	}
}
