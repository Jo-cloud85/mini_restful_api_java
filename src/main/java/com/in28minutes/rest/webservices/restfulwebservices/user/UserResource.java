package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;

	//constructor
	public UserResource(UserDaoService service) {
		this.service = service;
	}

	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	// http://localhost:8080/users
	
	// EntityModel
	// WebMVCLinkBuilder

	// GET /users using EntityModel
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		/* It is a builder to ease building Link instances pointing to Spring MVC.
		 * So basically what we are doing is we are using the WebMvcLinkBuilder link to method
		 * to create a link pointing to the controller method - retrieveAllUsers().*/
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		/* "all-users is just the name I give to specify the relationship/link between the 
		 * resources and the web mvc link."*/
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	//POST /users
	/* @RequestBody is an annotation that indicates that a method parameter should be bound
	 * to the body of the web request. In the web request which we send in, we will send a 
	 * body along with it. And in the body of the request, we will have all the details of 
	 * the user. And that would be mapped to the User bean. */
	// @PostMapping("/users")
	// public void createUser(@RequestBody User user) {
	// 	service.save(user);
	// }
	
	@PostMapping("/users")
	/* Using ResponseEntity class for this method to be able to return a response back.*/
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = service.save(user);

		/* URI location is a HTTP header that allows us to return a URL of a created resource. 
		 * ServletUriComponentsBuilder.fromCurrentRequest() will set up the URL of the current
		 * request. */
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						/* Here, to the URI of the current request, what we want to do is to 
						 * add a path, /id, and we want to replace this variable which is 
						 * present in here with the ID of the created user. And we want to 
						 * convert it to a URI and return it back. So we have a location that 
						 * is returned back and we are returning the location as part of our 
						 * ResponseEntity. */
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
}
