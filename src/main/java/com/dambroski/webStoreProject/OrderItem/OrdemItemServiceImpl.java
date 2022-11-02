package com.dambroski.webStoreProject.OrderItem;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.Itens.Item;
import com.dambroski.webStoreProject.Itens.ItemRepository;

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
	public void postOrderItem(OrderItem orderItem) {
		if (orderItem.getItem() == null) {
			Item newItem = itemRepository.findById(orderItem.getIdItem()).get();
			orderItem.setItem(newItem);
		}

		System.out.println("foi");

		System.out.println("foi2");
		

		repository.save(orderItem);

	}

	@Override
	public void putOrderItem(OrderItem orderItem, long orderItemId) {
		OrderItem oldOrderItem = repository.findById(orderItemId).get();
		if (Objects.nonNull(orderItem.getItem())) {
			oldOrderItem.setItem(orderItem.getItem());
		}
		if (Objects.nonNull(orderItem.getQuantity())) {
			oldOrderItem.setQuantity(orderItem.getQuantity());
		}

		repository.save(oldOrderItem);

	}

}
