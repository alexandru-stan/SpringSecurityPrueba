package com.example.demo.Security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

	
	//BASIC AUTH
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((e)->{
			e
			.anyRequest()
			.authenticated();
		}).httpBasic(withDefaults());			
		return http.build();
	}
	
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((e)->{
//			e
//			.anyRequest().permitAll();
//		}).httpBasic(withDefaults());			
//		return http.build();
//	}
	
	
	
	
	
	
	
		
}
