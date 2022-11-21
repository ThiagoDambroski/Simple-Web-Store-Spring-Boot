package com.dambroski.webStoreProject.Itens;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.error.InvalidRequestException;
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
	public void updateItem(Long itemId, Item item) throws ItemNotFoundException, InvalidRequestException {
		if(itemId == null) {
			throw new InvalidRequestException("The id can not be null");
		}
		Optional<Item> newItem = repository.findById(itemId);
		if(newItem.isEmpty()) {
			throw new ItemNotFoundException("Item " + itemId + " not found");
		}
		if(Objects.nonNull(item.getName())&& !"".equals(item.getName())) {
			newItem.get().setName(item.getName());
			
		}
		
		if(Objects.nonNull(item.getPrice())&& !"".equals(item.getPrice())) {
			newItem.get().setPrice(item.getPrice());
		}
		
		repository.save(newItem.get());
		
	}

	@Override
	public void giveItemDiscount(long id, double discount) {
		Item newItem = repository.findById(id).get();
		double intialPrice = newItem.getPrice();
		double newPrice = intialPrice  - (intialPrice * discount / 100);
		newItem.setPrice(newPrice);
		repository.save(newItem);
		
	}

	@Override
	public List<Item> getItemByPriceLimit(double limit) {
		return repository.findByLimitValue(limit);
	}


	
}
