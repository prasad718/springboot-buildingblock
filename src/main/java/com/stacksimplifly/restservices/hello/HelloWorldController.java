package com.stacksimplifly.restservices.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
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
	
	@GetMapping("/hello-i18")
	public String getInternalizationinI18Format(@RequestHeader (name="Accept-Language",required=false) String locale) {
		return messageSource.getMessage("label.hello", null,new Locale (locale));
	}

	@GetMapping("/hello-i182")
	public String getInternalizationinI18Format2() {
		return messageSource.getMessage("label.hello", null,LocaleContextHolder.getLocale());
	}
	
}
