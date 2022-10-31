package com.dambroski.webStoreProject.Order;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.Itens.Item;
import com.dambroski.webStoreProject.Itens.ItemRepository;
import com.dambroski.webStoreProject.User.User;
import com.dambroski.webStoreProject.User.UserRepository;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderRepository repository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<Order> getOrders() {
		return repository.findAll();
	}

	@Override
	public void postOrder(Order order) throws Exception {
		User user = userRepository.findById(order.getUserNum()).get();
		order.setUser(user);
		List<OrderItem> list = order.getItens();
		for (OrderItem orderItem : list) {
			if(orderItem.getItem().getStock() < orderItem.getQuantity()) {
				throw new Exception("this not enough itens in stock");
			}else {
				Item newItem = orderItem.getItem();
				newItem.setStock(newItem.getStock() - orderItem.getQuantity());
				itemRepository.save(newItem);
			}
		repository.save(order);
			
		};
		
	}

	@Override
	public void deleteOrder(long orderId) {
		Order newOrder= repository.findById(orderId).get();
		List<OrderItem> list = newOrder.getItens();                                                
		for (OrderItem orderItem : list) {
			Item newItem = orderItem.getItem();
			newItem.setStock(newItem.getStock() + orderItem.getQuantity());
		}
		repository.deleteById(orderId);
		
	}

	@Override
	public void updateOrder(Order order, long orderId) {
		Order newOrder = repository.findById(orderId).get();
		List<OrderItem> listItem = order.getItens(); 
		List<OrderItem> listNewItem = newOrder.getItens();
		for (OrderItem orderItem : listNewItem) {
			for (OrderItem orderItem2 : listItem) {
				if(orderItem.getOrderItemId() == orderItem2.getOrderItemId()) {
					int difference = orderItem.getQuantity() - orderItem2.getQuantity();
					Item newItem = orderItem.getItem();
					newItem.setStock(newItem.getStock() - difference);
					itemRepository.save(newItem);
				}
			}
		}
		if(Objects.nonNull(order.getDate())){
			newOrder.setDate(order.getDate());	
		}
		if(Objects.nonNull(order.getItens())) {
			newOrder.setItens(order.getItens());
		}
		if(Objects.nonNull(order.getStatus())) {
			newOrder.setStatus(order.getStatus());
		}
		
		repository.save(newOrder);
		
		
		
	}

	@Override
	public void updateStatusOrder(long orderId, OrderStatus status) {
		Order newOrder = repository.findById(orderId).get();
		newOrder.setStatus(status);
		repository.save(newOrder);
		
	}
	
	
	

}
