package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Users;

public interface UsersRepo extends JpaRepository<Users,String> {

	
	Users findPasswordByUsername(String username);
	
	Users findByUsername(String username);
}
