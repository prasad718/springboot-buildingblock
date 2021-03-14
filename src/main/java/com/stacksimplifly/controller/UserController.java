package com.stacksimplifly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplifly.entity.User;
import com.stacksimplifly.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		System.out.println("user" + user);
		return userService.createUser(user);
	}

	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable long id) {

		return userService.getUserById(id);
	}

	@PutMapping("/users/{id}")
	public User updateUserById(@RequestBody User user, @PathVariable long id) {

		return userService.updateUserById(user, id);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable long id) {

		userService.deleteUserById(id);
	}

	@GetMapping("/byusername/{name}")
	public User getUserByUserName(@PathVariable String name) {

		return userService.getUserByUserName(name);
	}

}
