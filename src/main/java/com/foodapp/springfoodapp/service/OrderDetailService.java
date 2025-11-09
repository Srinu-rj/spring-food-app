package com.foodapp.springfoodapp.service;

import com.foodapp.springfoodapp.entiry.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
	List<OrderDetails> saveOrders(List<OrderDetails> orderDetails);

	List<OrderDetails> getOrders();


    String deleteorder(Integer orderId);

	OrderDetails saveOrder(OrderDetails details);

    OrderDetails updateOrder(OrderDetails updateOrderDetails, int id);
}
