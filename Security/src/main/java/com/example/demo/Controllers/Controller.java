package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.UserServices;

@RestController
@RequestMapping("/users")
public class Controller {

	@Autowired
	UserServices service;
	
	@GetMapping("/key")
	public String key(){

		
		return "Welcome!";
	}
	
	@GetMapping("/basic")
	public Object[] basic() {
		var u = SecurityContextHolder.getContext().getAuthentication();
		return u.getAuthorities().toArray();
	}
	
	
	@PostMapping("/modify/{communityId}")
	public void modify( String communityId) {
		
		
	}
	
}
