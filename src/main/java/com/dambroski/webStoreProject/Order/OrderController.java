package com.dambroski.webStoreProject.Order;

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
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService service;
	
	@GetMapping()
	public List<Order> getOrders(){
		return service.getOrders();
	}
	
	@PostMapping()
	public void postOrders(@RequestBody Order order ) throws Exception {
		service.postOrder(order);
	}
	
	@DeleteMapping("/{orderId}")
	public void deleteOrders(@PathVariable(name = "orderId") long orderId) {
		service.deleteOrder(orderId);
	}
	
	@PutMapping("/{orderId}")
	public void updateOrder(@PathVariable(name = "orderId") long orderId,@RequestBody Order order) {
		service.updateOrder(order,orderId);
	}
	
	@PutMapping("{orderId}/{enum}")
	public void updateStatusOrder(@PathVariable(name = "orderId") long orderId, 
			@PathVariable(name = "enum") OrderStatus status) {
		service.updateStatusOrder(orderId,status);
		
	}

}
