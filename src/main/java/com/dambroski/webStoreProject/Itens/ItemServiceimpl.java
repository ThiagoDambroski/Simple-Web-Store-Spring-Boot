package com.dambroski.webStoreProject.Itens;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceimpl implements ItemService{
	
	@Autowired
	ItemRepository repository;

	@Override
	public List<Item> fetchAll() {
		return repository.findAll();
	}

	@Override
	public Item postItem(Item item) {
		return repository.save(item);
	}

	@Override
	public void deleteById(Long itemId) {
		repository.deleteById(itemId);
		
	}

	@Override
	public void updateItem(Long itemId, Item item) {
		Item newItem = repository.findById(itemId).get();
		
		if(Objects.nonNull(item.getName())&& !"".equals(item.getName())) {
			newItem.setName(item.getName());
		}
		
		if(Objects.nonNull(item.getPrice())&& !"".equals(item.getPrice())) {
			newItem.setPrice(item.getPrice());
		}
		
		repository.save(newItem);
		
	}

	@Override
	public Item getItemById(Long itemId) {
		
		return repository.findById(itemId).get();
	}
	
}
