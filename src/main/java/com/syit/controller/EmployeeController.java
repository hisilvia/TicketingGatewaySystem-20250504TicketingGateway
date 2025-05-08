package com.syit.controller;


import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.syit.domain.Employee;
import com.syit.domain.Role;
import com.syit.service.EmployeeService;
import com.syit.service.RoleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("employee")  //???
public class EmployeeController {
	
	@Autowired EmployeeService employeeService;
	@Autowired RoleService roleService;
	
	//Cannot remove it!==>  ???
	@GetMapping("/fetchUser")
	public String fetchUserPage(Principal principal) {
		return "fetchUser";
	}
		
		
	@GetMapping(value = "/login")
	public String login(@RequestParam(required = false) String logout, @RequestParam(required = false) String error,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		
		String message = "";
		if (error != null) {
			message = "Invalid Credentials";
		}
		if (logout != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
			}
			message = "Logout";
			return "login";
		}
		model.addAttribute("Message", message);
		return "login";

	}

	@GetMapping(value = "/accessDeniedPage")
	public String accessDenied(Principal principal, Model model) {
		String message = principal.getName() + ", Unauthorised access";
		model.addAttribute("Message", message);
		return "accessDeniedPage";

	}
	
	
	@PostMapping(value = "/signup")
	public String signup(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String department, @RequestParam String project, @RequestParam long managerId) {
		Employee employee = new Employee();
	
		employee.setName(username);
		employee.setEmail(email);
		employee.setPassword(password);
		employee.setDepartment(department);
		employee.setProject(project);
		employee.setManagerId(managerId);
		

		//Create a Role
		Set<Role> role = roleService.getDefaultRole();
		employee.setRoles(role);
		
		
		employeeService.save(employee);
		return "login";

	}
	
	@GetMapping("/register")
	public String register() {
		return "signup";
	}
	
	@GetMapping(value = "/employee/{username}")
	@ResponseBody
	public String getEmpByEmpname(@PathVariable String employeename) {
		return employeeService.findByName(employeename).getEmail();

	} 
	  
	  
	 

	@GetMapping("/employeeProfile")
	public String userProfile(Principal principal) {
		
		if(principal != null) {
			System.out.println("Logged In User Is: "+ principal.getName());
			return "employeeProfile";
		}
		return "redirect:NotFound";
	}
}
