package com.dambroski.webStoreProject.Itens;

import java.util.List;

import com.dambroski.webStoreProject.error.InvalidRequestException;
import com.dambroski.webStoreProject.error.ItemNotFoundException;

public interface ItemService {
	
	public List<Item> fetchAll();
	
	public Item postItem(Item item);

	public void deleteById(Long itemId) throws ItemNotFoundException;

	public Item updateItem(Long itemId, Item item) throws ItemNotFoundException, InvalidRequestException;

	public Item getItemById(Long itemId) throws ItemNotFoundException;

	public List<Item> getItemByName(String name) throws ItemNotFoundException;

	public Item giveItemDiscount(long id, double discount);

	public List<Item> getItemByPriceLimit(double limit);

	public Item addCategory(long id, long categoryId);

	public Item removeCategory(long id, long categoryId);

	
}
