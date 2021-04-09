package com.stacksimplifly.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplifly.entity.User;
import com.stacksimplifly.entity.UserMsDto;
import com.stacksimplifly.exceptions.UserNotFoundException;
import com.stacksimplifly.mapper.UserMapper;
import com.stacksimplifly.repositories.UserRepository;

@Validated
@RestController
@RequestMapping(value="/mapstruct/users")
public class UserMapStructController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserMsDto> getAllUserDtos() {
		return userMapper.userToUserMsDtos(userRepository.findAll());
	}

	
	
	
}
