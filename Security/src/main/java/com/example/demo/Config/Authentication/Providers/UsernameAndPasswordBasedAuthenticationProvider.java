package com.example.demo.Config.Authentication.Providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.Config.Authentication.Authentications.CustomAuthentication;
import com.example.demo.Services.UserServices;
@Component
public class UsernameAndPasswordBasedAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserServices userServices;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("dentro");
		UserDetails dbUser = userServices.loadUserByUsername(authentication.getName());
		System.out.println("HA ENTRAO");
		CustomAuthentication ca = (CustomAuthentication) authentication;
		System.out.println("dbuser pass "+ dbUser.getPassword()+" dbuser user "+dbUser.getUsername());
		if(passwordEncoder.matches(ca.getPassword(), dbUser.getPassword())) {
			System.out.println("LA CONTRASEÑA MATCHEA");
			return new CustomAuthentication(true,null,dbUser);
		
		}
		System.out.println("NO MATCH");
		return null;
		
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
	
		boolean result = CustomAuthentication.class.equals(authentication);
		return result;
		
	}
	


}
