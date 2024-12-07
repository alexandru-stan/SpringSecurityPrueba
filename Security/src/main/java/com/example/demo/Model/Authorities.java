package com.example.demo.Model;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Authorities  implements GrantedAuthority{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int authorityId;
	@Column
	String authority;
	
	@JsonIgnore
	@Transient
	@ManyToMany(mappedBy ="authorities")
	private Set<Users> users;
	
	
	public int getAuthorityId() {
		return authorityId;
	}
	public Authorities() {
	
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Authorities(int authorityId, String authority) {
		super();
		this.authorityId = authorityId;
		this.authority = authority;
	}
	public Set<Users> getUsers() {
		return users;
	}
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Authorities [authorityID=" + authorityId + ", authority=" + authority + ", users=" + users
				+ "]";
	}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authority;
	}
	
	
	
	
	
}
