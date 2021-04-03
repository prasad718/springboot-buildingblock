package com.stacksimplifly.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplifly.entity.Order;
import com.stacksimplifly.entity.User;
import com.stacksimplifly.exceptions.UserNotFoundException;
import com.stacksimplifly.repositories.UserRepository;
import com.stacksimplifly.service.UserService;

@Validated
@RestController
@RequestMapping(value="/hateoas/users")
public class UserHateOasController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserRepository userRepository;


	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			
			Optional<User> user1= userService.getUserById(id);
			
			User user=user1.get();
			
			Long userId=user.getUserid();
			
			EntityModel<User> resource=EntityModel.of(user);
		
			resource.add(WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel());
			return resource;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}

	}

	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		
		List<User> userList=userService.getAllUsers();
		
		for(User user: userList) {
		
		long userId=user.getUserid();
		
		Link link=WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
		
		user.add(link);
		
		CollectionModel<Order> orders= WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId);
		
		Link orderLink=WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
		user.add(orderLink);
		
		}
		
		Link selfLink= WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		
		
		
		CollectionModel<User>  resource=CollectionModel.of(userList,selfLink);
		
		return resource;
		
	}

	
	
	
}
