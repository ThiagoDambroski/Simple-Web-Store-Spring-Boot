package com.dambroski.webStoreProject.Itens;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dambroski.webStoreProject.error.InvalidRequestException;
import com.dambroski.webStoreProject.error.ItemNotFoundException;

@RestController
@RequestMapping("/api/item")
public class ItemController {
	
	@Autowired
	ItemService service;
	
	@GetMapping
	public List<Item> allItens(){
		return service.fetchAll();
	}
	@GetMapping("/get/{itemId}")
	public Item getItemById(@PathVariable(value = "itemId") Long itemId) throws ItemNotFoundException {
		return service.getItemById(itemId);
	}
	
	@GetMapping("/name/{name}")
	public List<Item> getItemByName(@PathVariable(value = "name") String name) throws ItemNotFoundException {
		return service.getItemByName(name);
	}
	
	@GetMapping("/filter/priceLimit/{priceLimit}")
	public List<Item> getItemByPriceLimit(@PathVariable("priceLimit") double limit){
		return service.getItemByPriceLimit(limit);
		
	}
	
	@PostMapping("/post")
	public Item postItem(@RequestBody Item item) {
		return service.postItem(item);
	}
	
	@DeleteMapping("/delete/{itemId}")
	public void deleteItem(@PathVariable(value = "itemId") Long itemId) throws ItemNotFoundException {
		service.deleteById(itemId);
	}
	
	@PutMapping("/put/{itemId}")
	public void putItem(@PathVariable(value = "itemId") Long itemId, @RequestBody Item item) throws ItemNotFoundException
	, InvalidRequestException {
		service.updateItem(itemId,item);
	}
	
	
	
	@PutMapping("/giveDiscount/{itemId}/{discountPercentage}")
	public void giveItemUpdate(@PathVariable(value = "itemId") long id, @PathVariable(value = "discountPercentage") 
			double discount) {
		service.giveItemDiscount(id,discount);
		
	}
	
	
	
	

}
