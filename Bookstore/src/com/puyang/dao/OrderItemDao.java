package com.puyang.dao;

import com.puyang.types.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
