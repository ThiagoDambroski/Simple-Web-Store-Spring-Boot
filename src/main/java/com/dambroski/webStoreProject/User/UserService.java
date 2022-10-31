package com.dambroski.webStoreProject.User;

import java.util.List;

import javax.validation.Valid;

public interface UserService {
	
	public List<User> getUsers();

	public void newUser(@Valid User user);

	public void updateUser(Long userId, User user);

	public void deleteUser(Long userId);

}
