package com.stacksimplifly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplifly.entity.User;
import com.stacksimplifly.exceptions.UserExistsException;
import com.stacksimplifly.exceptions.UserNotFoundException;
import com.stacksimplifly.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	public User createUser(User user) throws UserExistsException {


		User existUser=userRepository.findByUsername(user.getUsername());
		
		if (existUser!=null) {
			throw new UserExistsException("User already exists in user repository");
		}
		
		
		return userRepository.save(user);
	}

	
	//
	public Optional<User> getUserById(long id) throws UserNotFoundException {

		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not Found in user repository");
		}

		return user;

	}

	public User updateUserById(User user, long id) throws UserNotFoundException {

		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found in user repository , please provide the correct user id");
		}

		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteUserById(long id) {

		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found in user repository , please provide the correct user id");
		}
		
		userRepository.deleteById(id);
	}

	public User getUserByUserName(String name) {
		return userRepository.findByUsername(name);
	}

}
