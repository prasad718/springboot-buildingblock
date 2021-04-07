package com.stacksimplifly.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplifly.entity.User;
import com.stacksimplifly.exceptions.UserNotFoundException;
import com.stacksimplifly.service.UserService;

@RestController
@RequestMapping("/jacksonfilter/users")
public class UserMappingJacksonController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {

		try {

			Optional<User> userOptional = userService.getUserById(id);

			User user = userOptional.get();

			Set<String> fields = new HashSet<>();
			fields.add("userid");
			fields.add("username");
			fields.add("ssn");
			fields.add("firstname");

			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));

			MappingJacksonValue mapper = new MappingJacksonValue(user);

			mapper.setFilters(filterProvider);

			return mapper;

		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		}
	}

	@GetMapping("/params/{id}")
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields) {

		try {

			Optional<User> userOptional = userService.getUserById(id);

			User user = userOptional.get();

			/*
			 * Set<String> fields = new HashSet<>(); fields.add("userid");
			 * fields.add("username"); fields.add("ssn"); fields.add("firstname");
			 */
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));

			MappingJacksonValue mapper = new MappingJacksonValue(user);

			mapper.setFilters(filterProvider);

			return mapper;

		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		}
	}

}
