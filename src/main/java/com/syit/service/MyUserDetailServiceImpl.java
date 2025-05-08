
package com.syit.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.syit.domain.Role;
import com.syit.domain.Employee;



@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	EmployeeService employeeService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeService.findByName(username);
		
		if(employee == null) {
			throw new UsernameNotFoundException(username+"not found.");
		}
		
		/*
		Set<GrantedAuthority> ga = employee.getRoles().stream()
				.map((role) -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName()))
				.collect(Collectors.toSet());
		
		System.out.println(employee.getName());
		return new org.springframework.security.core.userdetails.User(username, employee.getPassword(), ga);
		*/
		Set<GrantedAuthority> ga = new HashSet<>();
		Collection<Role> roles = employee.getRoles();
		for (Role role : roles) {
			System.out.println("role.getRoleName()" + role.getRoleName());
			ga.add(new SimpleGrantedAuthority(role.getRoleName()));
		}	
		return new org.springframework.security.core.userdetails.User(employee.getName(), employee.getPassword(), ga);
		
	}

}
