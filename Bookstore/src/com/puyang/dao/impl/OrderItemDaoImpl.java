package com.puyang.dao.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.OrderItemDao;
import com.puyang.types.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(name, count, price, total_price, order_id) values(?, ?, ?, ?, ?)";
        update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select * from t_order_item where order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
