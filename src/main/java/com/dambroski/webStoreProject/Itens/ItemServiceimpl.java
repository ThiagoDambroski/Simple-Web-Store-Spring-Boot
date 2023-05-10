package com.dambroski.webStoreProject.Itens;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.Category.Category;
import com.dambroski.webStoreProject.Category.CategoryRepository;
import com.dambroski.webStoreProject.error.CategoryNotFoundException;
import com.dambroski.webStoreProject.error.InvalidRequestException;
import com.dambroski.webStoreProject.error.ItemDontHaveCategoryException;
import com.dambroski.webStoreProject.error.ItemNotFoundException;

@Service
public class ItemServiceimpl implements ItemService{
	
	@Autowired
	ItemRepository repository;
	
	@Autowired
	CategoryRepository categoryRepository;

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
	public Item updateItem(Long itemId, Item item) throws ItemNotFoundException, InvalidRequestException {
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
		
		if(Objects.nonNull(item.getPrice())&& !(item.getPrice() == 0.0) ) {
			newItem.get().setPrice(item.getPrice());
		}
		
		return repository.save(newItem.get());
		
	}

	@Override
	public Item giveItemDiscount(long id, double discount) {
		Item newItem = repository.findById(id).get();
		double intialPrice = newItem.getPrice();
		double newPrice = intialPrice  - (intialPrice * discount / 100);
		newItem.setPrice(newPrice);
		return repository.save(newItem);
		
	}

	@Override
	public List<Item> getItemByPriceLimit(double limit) {
		return repository.findByLimitValue(limit);
	}

	@Override
	public Item addCategory(long id, long categoryId) {
		Optional<Item> newItem = repository.findById(id);
		if(newItem.isEmpty()) {
			throw new ItemNotFoundException("Item " + id + " not found");
		}
		Optional<Category> category = categoryRepository.findById(categoryId);
		if(category.isEmpty()) {
			throw new CategoryNotFoundException("Category " + id + "not found");
		}
		Item item = newItem.get();
		Category cat = category.get();
		
		cat.getItens().add(item);
		categoryRepository.save(cat);
		
		return repository.save(item);
	}

	@Override
	public Item removeCategory(long id, long categoryId) {
		Optional<Item> newItem = repository.findById(id);
		if(newItem.isEmpty()) {
			throw new ItemNotFoundException("Item id: " + id + " not found");
		}
		Optional<Category> category = categoryRepository.findById(categoryId);
		if(category.isEmpty()) {
			throw new CategoryNotFoundException("Category id: " + id + "not found");
		}
		Item item = newItem.get();
		Category cat = category.get();
		
		if(!cat.getItens().contains(item)) {
			throw new ItemDontHaveCategoryException("Item dont have this category");
		}
		
		cat.getItens().remove(item);
		categoryRepository.save(cat);
		return repository.save(item);
	}


	
}
