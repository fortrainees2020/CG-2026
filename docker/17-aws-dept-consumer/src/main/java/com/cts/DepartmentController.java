package com.cts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("api/dept")
public class DepartmentController {
	@Autowired
	DepartmentFeignClient dFeignClient;

	private static final Department[] depts = {
			 new Department(10, "Sales3"),
			 new Department(20, "HR3")
	 };
	 
	@RequestMapping("/departments")
	 public Department[] getDepartments() {
		return depts;
		}
	
	@GetMapping("/department/{id}")
	 public Department getDepartmentById(@PathVariable int id) {
		for(int i=0;i<2;i++)
		{		if(depts[i].getDid()==id)
				return depts[i];
		}
		return null;
		}
	//---------------Feign client call-----------------
	   
	@GetMapping("/allemp")
		public  ResponseEntity<String> getEmployees() {
		 return ResponseEntity.ok().body(dFeignClient.employeeResponse());
		}	
	}