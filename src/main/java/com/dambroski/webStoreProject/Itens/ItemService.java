package com.dambroski.webStoreProject.Itens;

import java.util.List;

import com.dambroski.webStoreProject.error.InvalidRequestException;
import com.dambroski.webStoreProject.error.ItemNotFoundException;

public interface ItemService {
	
	public List<Item> fetchAll();
	
	public Item postItem(Item item);

	public void deleteById(Long itemId) throws ItemNotFoundException;

	public void updateItem(Long itemId, Item item) throws ItemNotFoundException, InvalidRequestException;

	public Item getItemById(Long itemId) throws ItemNotFoundException;

	public List<Item> getItemByName(String name) throws ItemNotFoundException;

	
}
