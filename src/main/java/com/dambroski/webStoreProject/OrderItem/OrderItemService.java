package com.dambroski.webStoreProject.OrderItem;

import java.util.List;

public interface OrderItemService {

	List<OrderItem> fetchAll();

	void deleteOrderItem(long orderItemId);

	OrderItem postOrderItem(OrderItem orderItem, long itemId);

	OrderItem putOrderItem(OrderItem orderItem, long orderItemId);
	


}
