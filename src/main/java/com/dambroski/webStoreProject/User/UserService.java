package com.dambroski.webStoreProject.User;

import java.util.List;

import javax.validation.Valid;

import com.dambroski.webStoreProject.error.UserNotFoundException;

public interface UserService {
	
	public List<User> getUsers();

	public User postUser(@Valid User user);

	public void updateUser(Long userId, User user) throws UserNotFoundException;

	public void deleteUser(Long userId) throws UserNotFoundException;

	public User getUserById(long userId) throws UserNotFoundException;

	public User getUserByEmail(String email) throws UserNotFoundException;

	public void addItemToWishList(long userId, long itemId);

	public void removeItemFromWishList(long userId, long itemId);

}
