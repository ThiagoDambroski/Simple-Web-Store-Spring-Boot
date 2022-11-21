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

import com.dambroski.webStoreProject.error.UserNotFoundException;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping
	public List<User> getUsers(){
		return service.getUsers();
	}
	
	@GetMapping("/get/{userId}")
	public User getUserById(@PathVariable(name = "userId") long userId) throws UserNotFoundException {
		return service.getUserById(userId);
	}
	
	
	@PostMapping("/post")
	public User postUser(@Valid @RequestBody User user) {
		return service.postUser(user);
	}
	
	@PutMapping("/put/{userId}")
	public void updateUser(@PathVariable(name = "userId") Long userId,@RequestBody User user) throws UserNotFoundException {
		service.updateUser(userId,user);
	}
	
	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable(name = "userId") Long userId) throws UserNotFoundException {
		service.deleteUser(userId);
	}
	
	
	@GetMapping("/email/{email}")
	public User getUserByEmail(@PathVariable(name = "email") String email) throws UserNotFoundException {
		return service.getUserByEmail(email);
	}
	
	@PutMapping("/addItemToWishList/user/{userId}/item/{itemId}")
	public void addItemToWishList(@PathVariable("userId") long userId,@PathVariable("itemId") long itemId) {
		service.addItemToWishList(userId,itemId);
		
	}
	
	@PutMapping("/removeItemFromWishlist/user/{userId}/item/{itemId}")
	public void removeItemFromWishList(@PathVariable("userId") long userId, @PathVariable("itemId") long itemId) {
		service.removeItemFromWishList(userId,itemId);
	}
}
