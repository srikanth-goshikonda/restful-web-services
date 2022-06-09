package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(user.getId()).toUri();
		daoService.saveUser(user);
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		return daoService.findAll();
	}

	@RequestMapping("/users/{userId}")
	public EntityModel<User> getUser(@PathVariable int userId) throws UserNotFoundException {
		User user = daoService.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("User not found".concat(String.valueOf(userId)));
		}
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).getUsers());
		return model.add(builder.withRel("all-users"));
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId) throws UserNotFoundException {
		User user = daoService.deleteById(userId);
		if (user == null) {
			throw new UserNotFoundException("User not found".concat(String.valueOf(userId)));
		}
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
}
