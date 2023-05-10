package com.dambroski.webStoreProject.OrderItem;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.Itens.Item;
import com.dambroski.webStoreProject.Itens.ItemRepository;
import com.dambroski.webStoreProject.error.ItemNotFoundException;
import com.dambroski.webStoreProject.error.NotEnoughItemsException;

@Service
public class OrdemItemServiceImpl implements OrderItemService {

	@Autowired
	OrderItemRepository repository;

	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<OrderItem> fetchAll() {
		return repository.findAll();
	}

	@Override
	public void deleteOrderItem(long orderItemId) {
		repository.deleteById(orderItemId);

	}

	@Override
	public OrderItem postOrderItem(OrderItem orderItem,long itemId) {
		Optional<Item> opItem = itemRepository.findById(itemId);
		if(opItem.isEmpty()) {
			throw new ItemNotFoundException("Item not found");
		}
		if(opItem.get().getStock() < orderItem.getQuantity()) {
			throw new NotEnoughItemsException("Not enough items in stock");
		}
		orderItem.setItem(opItem.get());
		
		return repository.save(orderItem);

	}

	@Override
	public OrderItem putOrderItem(OrderItem orderItem, long orderItemId) {
		
		Optional<OrderItem> opItem = repository.findById(orderItemId);
		if(opItem.isEmpty()) {
			throw new ItemNotFoundException("Item not found");
		}
		
		OrderItem oldOrderItem = opItem.get();
	
		if (Objects.nonNull(orderItem.getQuantity())) {
			oldOrderItem.setQuantity(orderItem.getQuantity());
		}

			
		return repository.save(oldOrderItem);
		
	

	}

}
