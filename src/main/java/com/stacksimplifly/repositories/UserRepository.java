package com.stacksimplifly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacksimplifly.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	User findByUsername(String name);
	

}
