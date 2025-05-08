package com.syit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.syit.domain.Employee;


@Service
public interface EmployeeService {

	public List<Employee> findAll();
	public Employee save(Employee e);
	public Employee findByEmpId(long eId);
	public Employee findByName(String employeeName);
	public void deleteByEmpId(long eId);
}
