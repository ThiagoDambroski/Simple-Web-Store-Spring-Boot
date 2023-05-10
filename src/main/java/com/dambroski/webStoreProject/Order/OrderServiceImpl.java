package com.dambroski.webStoreProject.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.Itens.Item;
import com.dambroski.webStoreProject.Itens.ItemRepository;
import com.dambroski.webStoreProject.OrderItem.OrderItem;
import com.dambroski.webStoreProject.OrderItem.OrderItemRepository;
import com.dambroski.webStoreProject.User.User;
import com.dambroski.webStoreProject.User.UserRepository;
import com.dambroski.webStoreProject.error.CanNotPaidCancelledOrderException;
import com.dambroski.webStoreProject.error.OrderItemNotFoundException;
import com.dambroski.webStoreProject.error.OrderNotFoundException;
import com.dambroski.webStoreProject.error.UserNotFoundException;

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
	public Order postOrder(Order order,long userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new UserNotFoundException("User " + userId + " Not found");
		}
		order.setUser(user.get());
		
		if(order.getItens() == null) {
			order.setItens(new HashSet<OrderItem>());
			for (Long id : order.getItensId()) {
				Optional<OrderItem> opOrderItem = orderItemRepository.findById(id);
				if(opOrderItem.isEmpty()) {
					throw new OrderItemNotFoundException("Order Item id: " + id + " notFound");
				}
				order.getItens().add(opOrderItem.get());
			}
		}
		
		
		Set<OrderItem> list = order.getItens();
		for (OrderItem orderItem : list) {
			Item newItem = orderItem.getItem();
			newItem.setStock(newItem.getStock() - orderItem.getQuantity());
			itemRepository.save(newItem);
			

		};
		order.setStatus(OrderStatus.PROCESSING);
		order.setDate(new Date());
		return repository.save(order);

	}


	@Override
	public Order cancelOrder(long orderId) {
		Order order = repository.findById(orderId)
				.orElseThrow(() -> new OrderItemNotFoundException("Order Not Found"));
		
		List<Item> itemsToUpadate = order.getItens().stream()
				.map(orderItem -> {
					Item item = orderItem.getItem();
					item.setStock(orderItem.getQuantity() + item.getStock());
					return item;
				})
				.collect(Collectors.toList());
		
		
		itemRepository.saveAll(itemsToUpadate);
		
		order.setStatus(OrderStatus.CANCELLED);
		
		return repository.save(order);
	}


	@Override
	public Order paidOrder(long orderId) {
		Order order = repository.findById(orderId)
				.orElseThrow(()-> new OrderNotFoundException("Order Not found") );
		
		if(order.getStatus() == OrderStatus.CANCELLED) {
			throw new CanNotPaidCancelledOrderException("This Order is Already cancelled");
		}
		
		order.setStatus(OrderStatus.PAID);
		return repository.save(order);
	}

	
}
