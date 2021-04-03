package com.stacksimplifly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplifly.entity.Order;
import com.stacksimplifly.entity.User;
import com.stacksimplifly.exceptions.UserNotFoundException;
import com.stacksimplifly.repositories.OrderRepository;
import com.stacksimplifly.repositories.UserRepository;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userId}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		
		List<Order> allOrders= userOptional.get().getOrderList();
		
		return CollectionModel.of(allOrders);
	}

		
	
}
