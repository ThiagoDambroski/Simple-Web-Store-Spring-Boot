 package com.dambroski.webStoreProject.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.error.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repository;

	@Override
	public List<User> getUsers() {
		return repository.findAll();
	}
	@Override
	public User getUserById(long userId) throws UserNotFoundException {
		Optional<User> user = repository.findById(userId);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User " + userId + " Not found");
		}
		return user.get();
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundException {
		Optional<User> user = repository.findByUserEmail(email);
		if(user.isEmpty()) {
			throw new UserNotFoundException("Email " + email + " not found");
		}
		return user.get();
	}

	@Override
	public void newUser(@Valid User user) {
		repository.save(user);
		
	}

	@Override
	public void updateUser(Long userId, User user) throws UserNotFoundException {
		Optional<User> newUser = repository.findById(userId);
		if(newUser.isEmpty()) {
			throw new UserNotFoundException("User " + userId + " Not found");
		}
		
		if(Objects.nonNull(user.getName()) && !"".equals(user.getName())) {
			newUser.get().setName(user.getName());
		}
		
		newUser.get().setEmail(user.getEmail());
		
		repository.save(newUser.get());
	}

	@Override
	public void deleteUser(Long userId) throws UserNotFoundException {
		Optional<User> user = repository.findById(userId);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User " + userId + " not found");
		}
		repository.deleteById(userId);
		
	}

	

}
