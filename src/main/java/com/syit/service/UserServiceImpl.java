
package com.syit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.syit.domain.Role;
import com.syit.domain.Employee;
import com.syit.repository.RoleRepository;
import com.syit.repository.EmployeeRepository;



@Service
public class UserServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	//@Autowired
	//AuthenticationManager authenticationManager;
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee save(Employee e) {
		HashSet<Role> roleSet = new HashSet<>();
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(e.getPassword());
		e.setPassword(hashedPassword);
		
		
		Role employeeRole = roleRepository.findById((long) 1).orElse(null);
		roleSet.add(employeeRole);
		e.setRoles(roleSet);
		
		//e.setRoles(roleRepository.findByRoleName("USER"));
		
		//List<Role> roles = new ArrayList<Role>();
		
		Employee employee = employeeRepository.save(e);
		return employee;
	}

	@Override
	public Employee findByEmpId(long eId) {
		Optional<Employee> e = employeeRepository.findById(eId);
		if(e.isPresent()) {
			return e.get();
		}else
			throw new RuntimeException("Did not find employee id - " + eId);
		//return null;
	}

	@Override
	public void deleteByEmpId(long uId) {
		employeeRepository.deleteById(uId);
		
	}

	@Override
	public Employee findByName(String employeeName) {
		return employeeRepository.findByName(employeeName);
	}

}
