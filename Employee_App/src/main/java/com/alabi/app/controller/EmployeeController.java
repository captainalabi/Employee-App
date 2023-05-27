package com.alabi.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alabi.app.entity.Employee;
import com.alabi.app.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeController(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@GetMapping({"/list-employees", "list", "/"})
	public ModelAndView listTmployees() {
		
		ModelAndView mav = new ModelAndView("list-employees");
		List <Employee> employee = employeeRepository.findAll(); 
		mav.addObject("employee", employee);
		
		return mav;
	}
	
	@GetMapping("/addnewemployee")
	public ModelAndView addEmployeeForm() {
		
		ModelAndView mav = new ModelAndView("register-employee-form");
		Employee employee = new Employee();
		
		mav.addObject("employee", employee);
		
		return mav;
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		
		employeeRepository.save(employee);
		
		return "redirect:list-employees";
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
		
		ModelAndView mav = new ModelAndView("register-employee-form");
		Employee employee = employeeRepository.findById(employeeId).get();
		mav.addObject("employee", employee);
		return mav;
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		
		employeeRepository.deleteById(employeeId);
		return "redirect:list";
	}
}
