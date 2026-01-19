package com.cts;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.netflix.ribbon.proxy.annotation.Http.Header;

import feign.Headers;
@Headers("Content-Type: application/json")
//@FeignClient(name = "emp-ws", url = "${feign.url}")

@FeignClient(name = "emp-ws", url = "${EMPLOYEE_SERVICE:http://localhost:7000}")
//@FeignClient(name = "emp-ws", url ="http://localhost:7000")
public interface DepartmentFeignClient {	
	@GetMapping("/api/emp/employees") // it means call /products mapping from service whose name id "product-ws
	String employeeResponse();
}
// EMPLOYEE_SERVICE=http://33.25.56.120:7000
//http://localhost:7000/api/emp/employees