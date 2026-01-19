package com.cts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Employee;

@RestController
//@RequestMapping("/api/emp")
public class EmployeeController {

	@RequestMapping("/employees")
	 public List<Employee> getEmployees() {
		 List<Employee> empList= new ArrayList<Employee>();
		 empList.add(new Employee(10, 100,"Robert3"));
		 empList.add(new Employee(10,200,"Albert3"));
		 empList.add(new Employee(20,300,"Gini3"));
		 return empList; 
}
}
	