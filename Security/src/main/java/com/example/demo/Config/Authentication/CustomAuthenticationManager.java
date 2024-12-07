package com.example.demo.Config.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo.Config.Authentication.Authentications.CustomAuthentication;
import com.example.demo.Config.Authentication.Providers.KeyBasedAuthenticationProvider;
import com.example.demo.Config.Authentication.Providers.UsernameAndPasswordBasedAuthenticationProvider;

@Component
public class CustomAuthenticationManager implements AuthenticationManager{

	@Autowired
	private KeyBasedAuthenticationProvider keyBasedProvider;
	@Autowired
	private UsernameAndPasswordBasedAuthenticationProvider usernameAndPasswordBasedProvider;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		CustomAuthentication ca = (CustomAuthentication)authentication;
		
//	System.out.println(ca.getUser().getUsername() + " AAAAAAAAAAAAAA");
		
		if(	usernameAndPasswordBasedProvider.supports(authentication.getClass()) && ca.getUser() != null) {
			
			
			return usernameAndPasswordBasedProvider.authenticate(authentication);
		
		
		} else if (keyBasedProvider.supports(authentication.getClass()) &&  ca.getKey() != null) {
			
			return keyBasedProvider.authenticate(authentication);
	
		
		} else {
			
		
		
		throw new BadCredentialsException("No AuthenticationProvider has been found for the provided AuthenticationObject");
		}
	}
	
	

	
	
}
