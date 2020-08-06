package com.puyang.service;

import com.puyang.types.Cart;
import com.puyang.types.Order;
import com.puyang.types.OrderItem;

import java.util.List;

public interface OrderService {
    String createOrder(Cart cart, Integer userId);

    List<Order> showAllOrders();

    void shipOrder(String orderId);

    List<OrderItem> showOrderDetail(String orderId);

    List<Order> showMyOrders(Integer userId);

    void signOrder(String orderId);
}
