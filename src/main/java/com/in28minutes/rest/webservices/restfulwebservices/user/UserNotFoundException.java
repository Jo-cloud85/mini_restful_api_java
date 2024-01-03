package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* If you extend exception our exception will become a checked exception and I would 
 * want to avoid checked exceptions most of the times.*/

@ResponseStatus(code = HttpStatus.NOT_FOUND) // this gives us the 404 error if user not found
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String message) {
		super(message);
	}

}