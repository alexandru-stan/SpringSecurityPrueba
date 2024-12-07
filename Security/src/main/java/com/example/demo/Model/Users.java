package com.example.demo.Model;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Users implements UserDetails {
	@Id
	 String username;
	@Column
	 String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
//	@Where(clause = "authority = 'READ'")

	@JoinTable(name="users_authorities", joinColumns= @JoinColumn(name="username"),
	inverseJoinColumns = @JoinColumn(name="authority_id"))
	private Set<Authorities> authorities;
    
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;

	}
	
	
	public Users() {
		super();
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities
			   .stream()
			   .collect(Collectors.toList());
	}
	@Override	
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

     
     
	
	
	
	
	
}
