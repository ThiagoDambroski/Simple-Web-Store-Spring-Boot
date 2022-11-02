package com.dambroski.webStoreProject.OrderItem;

import java.util.List;

public interface OrderItemService {

	List<OrderItem> fetchAll();

	void deleteOrderItem(long orderItemId);

	void postOrderItem(OrderItem orderItem);

	void putOrderItem(OrderItem orderItem, long orderItemId);
	


}
