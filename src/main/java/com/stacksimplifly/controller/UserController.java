package com.stacksimplifly.controller;

import java.util.List;
import java.util.Optional;


import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplifly.entity.User;
import com.stacksimplifly.exceptions.UserExistsException;
import com.stacksimplifly.exceptions.UserNameNotFoundException;
import com.stacksimplifly.exceptions.UserNotFoundException;
import com.stacksimplifly.service.UserService;

@Validated
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	//this method will create the user into data base.
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@Validated @RequestBody User user,UriComponentsBuilder uriBuilder) {
		System.out.println("user" + user);
		try {
			userService.createUser(user);
			HttpHeaders httpHeader=new HttpHeaders();
			
			httpHeader.setLocation(uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			
			return new ResponseEntity<Void>(httpHeader,HttpStatus.CREATED);
		} catch (UserExistsException e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			
		}
	}

	@PutMapping("/users/{id}")
	public User updateUserById(@RequestBody User user, @PathVariable long id) {

		try {
			return userService.updateUserById(user, id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}

	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable long id) {

		userService.deleteUserById(id);
	}

	@GetMapping("/byusername/{name}")
	public User getUserByUserName(@PathVariable String name) throws UserNameNotFoundException {

		User user=userService.getUserByUserName(name);
		
		if(user==null)
			throw new UserNameNotFoundException("User Name "+name+" Not found in user repository");
		
		return user;
	}

	
	
	
}
