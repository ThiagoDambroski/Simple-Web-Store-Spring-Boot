package com.dambroski.webStoreProject.Order;

import java.util.List;

public interface OrderService {
	
	public List<Order> getOrders();

	public void postOrder(Order order, Long userId) throws Exception;

	public void deleteOrder(long orderId);

	public void updateOrder(Order order, long orderId);

	public void updateStatusOrder(long orderId, OrderStatus status);

}
