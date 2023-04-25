package com.dambroski.webStoreProject.Order;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.Itens.Item;
import com.dambroski.webStoreProject.Itens.ItemRepository;
import com.dambroski.webStoreProject.OrderItem.OrderItem;
import com.dambroski.webStoreProject.OrderItem.OrderItemRepository;
import com.dambroski.webStoreProject.User.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository repository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	OrderItemRepository orderItemRepository;

	@Override
	public List<Order> getOrders() {
		return repository.findAll();
	}

	@Override
	public void postOrder(Order order,Long userId) throws Exception {
		if(order.getUser() == null) {
			order.setUser(userRepository.findById(userId).get());
		}
		if(order.getItens() == null) {
			order.setItens(new HashSet<OrderItem>());
			for (Long id : order.getIdItens()) {
				order.getItens().add(orderItemRepository.findById(id).get());
			}
		}
		
		
		Set<OrderItem> list = order.getItens();
		for (OrderItem orderItem : list) {
			if (orderItem.getItem().getStock() < orderItem.getQuantity()) {
				throw new Exception("this not enough itens in stock");
			} else {
				Item newItem = orderItem.getItem();
				newItem.setStock(newItem.getStock() - orderItem.getQuantity());
				itemRepository.save(newItem);
			}
			repository.save(order);

		}
		;

	}

	@Override
	public void deleteOrder(long orderId) {
		Order newOrder = repository.findById(orderId).get();
		Set<OrderItem> list = newOrder.getItens();
		for (OrderItem orderItem : list) {
			Item newItem = orderItem.getItem();
			newItem.setStock(newItem.getStock() + orderItem.getQuantity());
			itemRepository.save(newItem);
			orderItemRepository.deleteById(orderItem.getOrderItemId());
			
		}
		
		repository.deleteById(orderId);
		

	}

	@Override
	public void updateOrder(Order order, long orderId) {
		Order oldOrder = repository.findById(orderId).get();
		order.setItens(new HashSet<OrderItem>());
		for (Long id : order.getIdItens()) {
			order.getItens().add(orderItemRepository.findById(id).get());
		}
		Set<OrderItem> listNewItem = order.getItens();
		Set<OrderItem> listOldItem = oldOrder.getItens();

		if (Objects.nonNull(order.getIdItens())) {

			oldOrder.setItens(order.getItens());
		}

		for (OrderItem orderItem : listOldItem) {
			for (OrderItem orderItem2 : listNewItem) {
				if (orderItem.getIdItem() == orderItem2.getIdItem()) {
					int difference = orderItem2.getQuantity() - orderItem.getQuantity();
					Item newItem = orderItem.getItem();
					newItem.setStock(newItem.getStock() - difference);
					itemRepository.save(newItem);
				}
			}
		}
		if (Objects.nonNull(order.getDate())) {
			oldOrder.setDate(order.getDate());
		}

		if (Objects.nonNull(order.getStatus())) {
			oldOrder.setStatus(order.getStatus());
		}
		repository.save(oldOrder);

	}

	@Override
	public void updateStatusOrder(long orderId, OrderStatus status) {
		Order newOrder = repository.findById(orderId).get();
		newOrder.setStatus(status);
		repository.save(newOrder);

	}

}
