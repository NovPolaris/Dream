package com.puyang.dao.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.OrderDao;
import com.puyang.types.Order;
import com.puyang.types.OrderStatus;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql = "insert into t_order(order_id, create_time, price, status, user_id) values (?, ?, ?, ?, ?)";
        update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus().toString(), order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select * from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public void changeOrderStatus(String orderId, OrderStatus orderStatus) {
        String sql = "update t_order set status = ? where order_id = ?";
        update(sql, orderStatus.toString(), orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select * from t_order where user_id = ?";
        return queryForList(Order.class, sql, userId);
    }
}
