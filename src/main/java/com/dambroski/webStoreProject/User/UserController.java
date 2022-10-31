package com.dambroski.webStoreProject.User;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping
	public List<User> getUsers(){
		return service.getUsers();
	}
	
	@PostMapping
	public void newUser(@Valid @RequestBody User user) {
		service.newUser(user);
	}
	
	@PutMapping("/{userId}")
	public void updateUser(@PathVariable(name = "userId") Long userId,@RequestBody User user) {
		service.updateUser(userId,user);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable(name = "userId") Long userId) {
		service.deleteUser(userId);
	}
}
