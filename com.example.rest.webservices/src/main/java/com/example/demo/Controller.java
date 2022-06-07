package com.example.demo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController()
public class Controller {
	@Autowired
	private UserDaoService daoService;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(user.getId()).toUri();
		 daoService.saveUser(user) ;
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping()
	public List<User> getUsers() {
		return daoService.findAll();
	}

	@RequestMapping("/{userId}")
	public User getUser(@PathVariable int userId) throws UserNotFoundException {
		User user = daoService.findUserById(userId);
		if(user == null) {
			throw new UserNotFoundException("User not found".concat(String.valueOf(userId)));
		}
		return user;
	}
}
