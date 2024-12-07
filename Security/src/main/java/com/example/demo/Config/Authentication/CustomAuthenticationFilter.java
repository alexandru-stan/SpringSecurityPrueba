package com.example.demo.Config.Authentication;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Config.Authentication.Authentications.CustomAuthentication;
import com.example.demo.Model.Users;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class CustomAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	CustomAuthenticationManager customAuthenticationManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		// 1. create an authentication object which is not yet authenticated 
		// 2. delegate the authentication object to the manager
		// 3. get back the authentication from the manager
		// 4. if the authentication is authenticated then send the request to the next filter
		String key = String.valueOf(request.getHeader("key"));
		String authHeader = request.getHeader("Authorization");
		if(!key.isEmpty() && !key.equals("null") && !key.isBlank()) {
			System.out.println(key.length());
			CustomAuthentication ca = new CustomAuthentication(false,key, null);
			var a = customAuthenticationManager.authenticate(ca);

			if(a.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(a);
				
				  chain.doFilter(request, response);
			
			}  
		} else if(authHeader != null && authHeader.startsWith("Basic")) {
			  	String base64Credentials = authHeader.substring(6);
			 	byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
			    String decodedString = new String(decodedBytes);
			    String[] credentials = decodedString.split(":", 2);
			    String username = credentials[0];
			    String password = credentials[1];
			    System.out.println(username+password);
			    
			CustomAuthentication ca = new CustomAuthentication(false,null,new Users(username,password));
			
			var a = customAuthenticationManager.authenticate(ca);
			if(a.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(a);
				System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
				chain.doFilter(request, response);
			}
			
		} else {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
	        response.getWriter().write("Authentication Failed");
			
		}
		
		
		
	
		
		
      
	}
		

		
		
		
		
	}

	
	
	

