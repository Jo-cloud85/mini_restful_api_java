package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

// DAO = Data Access Object

@Component
public class UserDaoService {
	// JPA/Hibernate > Database
	// UserDaoService > Static List
	
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 0;
	
	/* Right now, the userDaoService is retrieving all the details from a static user list.
	 * A little later in the course, we'll be making use of JPA and Hibernate, and we'll be 
	 * talking to a database.*/
	static {
		users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount,"Eve", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount,"Jim", LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream()
				.filter(predicate)
				.findFirst() //returns an optional
				//.get();
				.orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		/* Basically, it is saying remove the user if a predicate matches*/
		users.removeIf(predicate);
	}
}
