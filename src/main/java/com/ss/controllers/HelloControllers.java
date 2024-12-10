package com.ss.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloControllers {

	@GetMapping("/public/hello")
	public String publicHello() {
		return "Hello, this is a public endpoint!";
	}

	@GetMapping("/private/hello")
	public String privateHello() {
		return "Hello, this is a private endpoint!";
	}

	@GetMapping("/user/hello")
	public String userHello() {
		return "Hello, this is a USER endpoint!";
	}

	@GetMapping("/admin/hello")
	public String adminHello() {
		return "Hello, this is an ADMIN endpoint!";
	}

}
