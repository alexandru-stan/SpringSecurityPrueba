package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.Config.Authentication.CustomAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

		
//SECURITY FILTER THAT ALLOWS TO ACCESS "LOGIN" WITH NO RESTRICTION, BUT FOR EVERYTHING ELSE YOU NEED TO BE AUTHENTICATED. 
//THE BASIC AUTH SYSTEM WILL LOOK FOR A USERDETAILSSERVICE BEAN, WHICH I'VE ALREADY DEFINED, AND WILL LOAD THE USER BY USERNAME.
//IF THE USER IS FOUND, WILL USE THE PASSWORD ENCODER TO COMPARE THE PASSWORDS.
	
	
	private final CustomAuthenticationFilter customAuthenticationFilter;
	
	
	
	 public SecurityConfig(CustomAuthenticationFilter customAuthenticationFilter) {
		super();
		this.customAuthenticationFilter = customAuthenticationFilter;
	}

		@Bean
		    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http.csrf(csrf -> csrf.disable());
            http
                    .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeHttpRequests(auth -> {

                        auth
                                .anyRequest()
                                .authenticated();

                    });


//            http.httpBasic(withDefaults());is
			
			 
			 
			 System.out.println("A");
	
	        return http.build();
	    }
		 
//		 @Bean
//		 UserDetailsService userDetailsService() {
//			 return new UserServices();	
//		 }
	 
		
	 	@Bean
	   PasswordEncoder passwordEncoder(){
		
	 		return new BCryptPasswordEncoder();
	 		
	 }
	
	
}
