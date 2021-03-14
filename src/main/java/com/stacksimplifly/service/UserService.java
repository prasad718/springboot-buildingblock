package com.stacksimplifly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplifly.entity.User;
import com.stacksimplifly.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	public User createUser(User user) {

		return userRepository.save(user);
	}

	public Optional<User> getUserById(long id) {

		return userRepository.findById(id);
	}

	public User updateUserById(User user, long id) {

		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteUserById(long id) {

		boolean isPresent = userRepository.findById(id).isPresent();

		if (isPresent)
			userRepository.deleteById(id);
	}

	public User getUserByUserName(String name) {
		return userRepository.findByUsername(name);
	}

}
