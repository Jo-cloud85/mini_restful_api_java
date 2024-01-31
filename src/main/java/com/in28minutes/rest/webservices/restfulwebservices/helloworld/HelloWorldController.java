package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	// @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	
	// Returns a string on the url
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	// Returns a JSON response on the url
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		// Returning an instance of the HelloWorldBean class
		return new HelloWorldBean("Hello World");
	}
	
	// Path parameters
	// /users/{id}/todos/{id} => /users/2/todos/200
	// /hello-world/path-variable/{name}
	
	/* So what happens is when I type in hello-world/path-variable/Ranga, this Ranga over here would be
	 * mapped by Spring MVC to the PathVariable and we are picking the value up and returning it back in
	 * 'return new HelloWorldBean(String.format("Hello World, %s", name))'. Path parameters are nothing 
	 * but the variable values in your URLs. */
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name)); 
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		/* Locale helps us to get locale from the request header. */
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
