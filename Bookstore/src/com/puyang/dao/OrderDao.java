package com.puyang.dao;

import com.puyang.types.Order;
import com.puyang.types.OrderStatus;

import java.util.List;

public interface OrderDao {
    void saveOrder(Order order);

    List<Order> queryOrders();

    void changeOrderStatus(String orderId, OrderStatus orderStatus);

    List<Order> queryOrdersByUserId(Integer userId);
}
