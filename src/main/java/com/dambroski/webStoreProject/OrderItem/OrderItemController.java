package com.dambroski.webStoreProject.OrderItem;

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

@RestController
@RequestMapping("api/itemOrder")
public class OrderItemController {
	
	@Autowired
	OrderItemService service;
	
	@GetMapping
	public List<OrderItem> fetchAll(){
		return service.fetchAll();
	}
	
	@PostMapping
	public void postOrderItem(@RequestBody OrderItem orderItem) {
		service.postOrderItem(orderItem);
		
	}
	
	@DeleteMapping("{orderItemId}")
	public void deleteOrderItem(@PathVariable(name = "orderItemId") long orderItemId) {
		service.deleteOrderItem(orderItemId);
	}
	
	@PutMapping("{orderItemId}")
	public void putOrderItem(@PathVariable(name = "orderItemId") long orderItemId,@RequestBody OrderItem orderItem) {
		service.putOrderItem(orderItem,orderItemId);
	}
}
