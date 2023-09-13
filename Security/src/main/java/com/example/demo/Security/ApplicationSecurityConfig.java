package com.example.demo.Security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

	
	//BASIC AUTH
	// SE NECESITA AUTENTICACIÓN BASIC
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(e->{
			e.requestMatchers("/home").permitAll()
			.anyRequest()
			.authenticated();
		}).httpBasic(withDefaults());
	
			
		
		
		return http.build();
	}	
	

	
	
	
	
// PERMITE EL ACCESO A CUALQUIER USUARIO SIN NECESIDAD DE AUTENTICARSE
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((e)->{
//			e
//			.anyRequest().permitAll();
//		});			
//		return http.build();
//	}
	
	
	
	
	@Bean 
	public UserDetailsService userDetails(PasswordEncoder encoder) {
		
		
		UserDetails user = User.withUsername("alex")
						.password(encoder.encode("stan"))
						.roles("USER")
						.build();
		
		UserDetails mod = User.withUsername("mod")
				.password(encoder.encode("mod"))
				.roles("MOD")
				.build();
		
	return new InMemoryUserDetailsManager(user,mod);
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
		
}
