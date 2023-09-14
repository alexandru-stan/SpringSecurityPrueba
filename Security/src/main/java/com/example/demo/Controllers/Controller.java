package com.example.demo.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

	
	@GetMapping("/home")
	public String HelloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/mod")
	@PreAuthorize("hasAuthority('ROLE_MOD')")
	public String Mod() {
		return "OK, you are a mod";
	}
	
	@GetMapping("/admin")
	public String Admin() {
		return "OK, you are an admin";
	}
	
	
}
