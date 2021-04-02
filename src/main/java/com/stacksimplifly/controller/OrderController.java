package com.stacksimplifly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		return userOptional.get().getOrderList();
	}

	@PostMapping("{userId}/orders")
	public void createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user=userOptional.get();
		order.setUser(user);
		orderRepository.save(order);
	}

	@GetMapping("{userId}/orders/{orderId}")
	public Optional<Order> getOrderByOrderId(@PathVariable Long userId,@PathVariable Long orderId) throws UserNotFoundException {
	
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		Optional<Order> orderOptional = orderRepository.findById(orderId);
		if (!orderOptional.isPresent()) {
			throw new UserNotFoundException("Order Not Found");
		}
		
		
		return orderRepository.findById(orderId);
		
		
	}
	
	
}
