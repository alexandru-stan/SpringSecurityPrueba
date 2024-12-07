package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Users;
import com.example.demo.Repo.UsersRepo;

@Service
public class UserServices implements UserDetailsService {

	
	@Autowired
	UsersRepo repo;
	PasswordEncoder encoder = new BCryptPasswordEncoder();


	public Users login(String username,String password) {

		Users user = repo.findByUsername(username);
		 
		if(user!=null && encoder.matches(password, user.getPassword())) {
			
			return user;
			
		} else {
			System.out.println("B");
			throw new BadCredentialsException("Incorrect credentials");
		}
		
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = repo.findByUsername(username);
		
		return user;
		
//		return new Users("adobe",encoder.encode("haha"),"adobe@gmail.com");
	}
	
}
