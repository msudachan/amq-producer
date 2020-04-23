package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gas/v1/")
public class Controller {

	// private DemoService service;

	@Autowired
	private Publisher publisher;

//	@Autowired
//	public Controller(DemoService service) {
//		this.service = service;
//	}
	@GetMapping("amqdemo")
	public String getDetails(@RequestParam String firstName, @RequestParam String lastName) {
		System.out.println("firstname: " + firstName + "lastname: " + lastName);
		Company company = new Company(firstName, lastName);
		publisher.send(company);
		return "success";

	}

}
