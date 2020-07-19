package com.puyang.service;

import com.puyang.types.Cart;
import com.puyang.types.Order;
import com.puyang.types.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    String createOrder(Cart cart, Integer userId) throws SQLException;

    List<Order> showAllOrders() throws SQLException;

    void shipOrder(String orderId) throws SQLException;

    List<OrderItem> showOrderDetail(String orderId) throws SQLException;

    List<Order> showMyOrders(Integer userId) throws SQLException;

    void signOrder(String orderId) throws SQLException;
}
