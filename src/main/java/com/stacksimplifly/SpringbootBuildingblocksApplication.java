package com.stacksimplifly;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
//This srping boot entry point
public class SpringbootBuildingblocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBuildingblocksApplication.class, args);
	}

	@Bean
	public LocaleResolver localResolver() {
		
		AcceptHeaderLocaleResolver localResolver=new AcceptHeaderLocaleResolver();
		
		localResolver.setDefaultLocale(Locale.US);
		
		return localResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messgeSource() {
		
		ResourceBundleMessageSource messgeSource=new ResourceBundleMessageSource();
		
		messgeSource.setBasename("messages");
		
		return messgeSource;
	}
	
	
	
	
	
}
