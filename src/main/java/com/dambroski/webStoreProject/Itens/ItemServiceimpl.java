package com.dambroski.webStoreProject.Itens;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.error.ItemNotFoundException;

@Service
public class ItemServiceimpl implements ItemService{
	
	@Autowired
	ItemRepository repository;

	@Override
	public List<Item> fetchAll() {
		return repository.findAll();
	}
	
	@Override
	public Item getItemById(Long itemId) throws ItemNotFoundException {
		Optional<Item> item = repository.findById(itemId);
		if(item.isEmpty()) {
			throw new ItemNotFoundException("Itemid " + itemId + " not found");
		}
		return repository.findById(itemId).get();
	}

	@Override
	public List<Item> getItemByName(String name) throws ItemNotFoundException {
		List<Item> list = repository.findByNameLike(name);
		if(list.isEmpty()) {
			throw new ItemNotFoundException("Item " + name + " not found");
		}
		return list;
	}

	@Override
	public Item postItem(Item item) {
		return repository.save(item);
	}

	@Override
	public void deleteById(Long itemId) throws ItemNotFoundException {
		Optional<Item> item = repository.findById(itemId);
		if(item.isEmpty()) {
			throw new ItemNotFoundException("Item " + itemId + " not found");
		}
		repository.deleteById(itemId);
		
	}

	@Override
	public void updateItem(Long itemId, Item item) throws ItemNotFoundException {
		Optional<Item> newItem = repository.findById(itemId);
		if(Objects.nonNull(item.getName())&& !"".equals(item.getName())) {
			newItem.get().setName(item.getName());
			
		}
		
		if(Objects.nonNull(item.getPrice())&& !"".equals(item.getPrice())) {
			newItem.get().setPrice(item.getPrice());
		}
		
		repository.save(newItem.get());
		
	}


	
}
