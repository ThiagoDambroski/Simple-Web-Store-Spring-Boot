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
	
	@GetMapping("/getAll")
	public List<Order> getOrders(){
		return service.getOrders();
	}
	
	@PostMapping("/post/{userId}")
	public Order postOrders(@RequestBody Order order,@PathVariable("userId") long userId ) throws Exception {
		return service.postOrder(order,userId);
	}

	
	@PutMapping("cancelOrder/{orderId}")
	public Order cancelOrder(@PathVariable("orderId") long orderId) {
		return service.cancelOrder(orderId);
	}
	
	@PutMapping("paidOrder/{orderId}")
	public Order paidOrder(@PathVariable("orderId") long orderId) {
		return service.paidOrder(orderId);
	}

}
