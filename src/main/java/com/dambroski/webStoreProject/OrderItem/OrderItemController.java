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
@RequestMapping("/api/orderItem")
public class OrderItemController {
	
	@Autowired
	OrderItemService service;
	
	@GetMapping("/getAll")
	public List<OrderItem> fetchAll(){
		return service.fetchAll();
	}
	
	@PostMapping("/post/{itemId}")
	public OrderItem postOrderItem(@RequestBody OrderItem orderItem,@PathVariable("itemId") long itemId) {
		return service.postOrderItem(orderItem,itemId);
		
	}
	
	@DeleteMapping("/deleteById/{orderItemId}")
	public void deleteOrderItem(@PathVariable(name = "orderItemId") long orderItemId) {
		service.deleteOrderItem(orderItemId);
	}
	
	@PutMapping("/changeQuantity/{orderItemId}")
	public OrderItem putOrderItem(@PathVariable(name = "orderItemId") long orderItemId,@RequestBody OrderItem orderItem) {
		return service.putOrderItem(orderItem,orderItemId);
	}
}
