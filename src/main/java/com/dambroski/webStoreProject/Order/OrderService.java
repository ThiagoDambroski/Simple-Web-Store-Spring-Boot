package com.dambroski.webStoreProject.Order;

import java.util.List;

public interface OrderService {
	
	public List<Order> getOrders();

	public Order postOrder(Order order, long userId) throws Exception;


	public Order cancelOrder(long orderId);

	public Order paidOrder(long orderId);

}
