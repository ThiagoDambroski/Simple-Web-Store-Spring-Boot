package com.dambroski.webStoreProject.Itens;

import java.util.List;

public interface ItemService {
	
	public List<Item> fetchAll();
	
	public Item postItem(Item item);

	public void deleteById(Long itemId);

	public void updateItem(Long itemId, Item item);

	public Item getItemById(Long itemId);

	public List<Item> getItemByName(String name);

	
}
