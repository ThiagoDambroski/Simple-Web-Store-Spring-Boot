 package com.dambroski.webStoreProject.User;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repository;

	@Override
	public List<User> getUsers() {
		return repository.findAll();
	}

	@Override
	public void newUser(@Valid User user) {
		repository.save(user);
		
	}

	@Override
	public void updateUser(Long userId, User user) {
		User newUser = repository.findById(userId).get();
		
		if(Objects.nonNull(user.getName()) && !"".equals(user.getName())) {
			newUser.setName(user.getName());
		}
		
		newUser.setEmail(user.getEmail());
		
		repository.save(newUser);
	}

	@Override
	public void deleteUser(Long userId) {
		repository.deleteById(userId);
		
	}

	@Override
	public User getUserById(long userId) {
		return repository.findById(userId).get();
	}

	@Override
	public User getUserByEmail(String email) {
		List<User> list = repository.findAll();
		User returnUser = null;
		for (User user : list) {
			System.out.println(user.getEmail());
			if(user.getEmail().equals(email)) {
				returnUser = user;
			}
		}
		return returnUser;
	}

}
