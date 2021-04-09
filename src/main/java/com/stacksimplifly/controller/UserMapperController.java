package com.stacksimplifly.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplifly.dto.UsermmDto;
import com.stacksimplifly.entity.User;
import com.stacksimplifly.exceptions.UserNotFoundException;
import com.stacksimplifly.service.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserMapperController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/{id}")
	public UsermmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}

		User user = userOptional.get();

		UsermmDto userMDto = modelMapper.map(user, UsermmDto.class);

		return userMDto;
	}

}
