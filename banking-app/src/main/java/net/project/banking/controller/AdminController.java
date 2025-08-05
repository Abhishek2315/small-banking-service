package net.project.banking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
	
	@GetMapping("/hello")
	public String sayhello() {
		return "hello admin";
	}
	
	@GetMapping("/byy")
	public String saybyy() {
		return "Byy admin";
	}

}
