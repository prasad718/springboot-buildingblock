package com.stacksimplifly.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	
	
	//simple method
	//GET
	//URI --/helloworld
	//@RequestMapping(method=RequestMethod.GET,path="/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		
		return "Hello World-1";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		
		return new UserDetails("Durgaprasad", "Kovvuru", "Chennai");
	}
	
	
}
