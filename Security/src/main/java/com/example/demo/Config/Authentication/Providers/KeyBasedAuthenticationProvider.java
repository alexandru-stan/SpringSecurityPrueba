package com.example.demo.Config.Authentication.Providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo.Config.Authentication.Authentications.CustomAuthentication;

@Component
public class KeyBasedAuthenticationProvider implements AuthenticationProvider {

	@Value("${secret.key}")
	String key;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		CustomAuthentication ca = (CustomAuthentication) authentication;
		String headerKey = ca.getKey();
		
		if(key.equals(headerKey)) {
			return  new CustomAuthentication(true, null,null);
			
		} else {
			throw new BadCredentialsException("The provided authentication key is incorrect");
		}
		
	
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		//THIS IS HOW THE AUTHENTICATION MANAGER WILL DECIDE TO USE THIS PROVIDER WHEN DEALING WITH A CUSTOMAUTHENTICATION OBJECT
		return CustomAuthentication.class.equals(authentication);
	}

	//	
//	public boolean isValid(Authentication authentication) {
//	
//	CustomAuthentication parsedAuth = 	(CustomAuthentication) authentication;
//	
//	return parsedAuth.getKey().length()>0;
//	
//	}

	
	
}
