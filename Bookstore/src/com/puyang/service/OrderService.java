package com.puyang.service;

import com.puyang.types.Order;
import com.puyang.types.OrderItem;

import java.util.List;

public interface OrderService {
    void createOrder();

    List<Order> showAllOrders();

    void shipOrder(Integer orderId);

    List<OrderItem> showOrderDetail(Integer orderId);

    List<Order> showMyOrders(Integer userId);

    void signOrder(Integer orderId);
}
