package com.example.demo.Config.Authentication.Authentications;

import java.util.Collection;

import javax.security.auth.Subject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Model.Users;

@SuppressWarnings("serial")
public class CustomAuthentication implements Authentication {
	public CustomAuthentication(boolean authentication, String key, UserDetails user) {
		super();
		this.authentication = authentication;
		this.key = key;
		this.user = user;
	}
		private  boolean authentication;
		private final String key;
		private final UserDetails user;
	
	
	
	




	public UserDetails getUser() {
			return user;
		}

	public String getKey() {
			return key;
		}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return authentication;
	}
	
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authentication = isAuthenticated;
		
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean implies(Subject subject) {
		// TODO Auto-generated method stub
		return Authentication.super.implies(subject);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		return user.getPassword(); 
	}
	



	
	
	
}
