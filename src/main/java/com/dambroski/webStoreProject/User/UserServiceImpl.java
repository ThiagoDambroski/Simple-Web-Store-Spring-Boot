 package com.dambroski.webStoreProject.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.Itens.Item;
import com.dambroski.webStoreProject.Itens.ItemRepository;
import com.dambroski.webStoreProject.error.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<User> getUsers() {
		return repository.findAll();
	}
	@Override
	public User getUserById(long userId) throws UserNotFoundException {
		User user = repository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User " + userId + " Not found"));		
			
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundException {
		User user = repository.findByEmail(email);
		if(user.equals(null)) {
			throw new UserNotFoundException("User not found");
		}
		
		return user;
	}

	@Override
	public User postUser(@Valid User user) {
		return repository.save(user);
		
	}

	@Override
	public User updateUser(Long userId, User user) throws UserNotFoundException {
		Optional<User> newUser = repository.findById(userId);
		if(newUser.isEmpty()) {
			throw new UserNotFoundException("User " + userId + " Not found");
		}
		
		if(Objects.nonNull(user.getName()) && !"".equals(user.getName())) {
			newUser.get().setName(user.getName());
		}
		if(Objects.nonNull(user.getEmail()) && !"".equals(user.getEmail())) {
		
			newUser.get().setEmail(user.getEmail());
		}
		
		return repository.save(newUser.get());
	}

	@Override
	public void deleteUser(Long userId) throws UserNotFoundException {
		Optional<User> user = repository.findById(userId);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User " + userId + " not found");
		}
		repository.deleteById(userId);
		
	}
	
	@Override
	public User addItemToWishList(long userId, long itemId) {
		Item item = itemRepository.findById(itemId).get();
		User user = repository.findById(userId).get();
		
		user.getWhishList().add(item);
		return repository.save(user);
		
	}
	@Override
	public void removeItemFromWishList(long userId, long itemId) {
		User user = repository.findById(userId).get();
		int n = 0;
		for (Item item : user.getWhishList()) {
			if(item.getItemId() == itemId) {
				break;
			}
			n++;
		}
		user.getWhishList().remove(n);
		repository.save(user);
		
	}

	

}
