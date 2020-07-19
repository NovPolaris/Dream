package com.puyang.dao.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.OrderDao;
import com.puyang.types.Order;
import com.puyang.types.OrderItem;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void createOrder() {

    }

    @Override
    public List<Order> showAllOrders() {
        String sql = "select * from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public void shipOrder(Integer orderId) {
        String sql = "update t_order set status = '已发货' where order_id = ?";
        update(sql, orderId);
    }

    @Override
    public List<OrderItem> showOrderDetail(Integer orderId) {
        String sql = "select * from t_order_item where order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        String sql = "select * from t_order where user_id = ?";
        return queryForList(Order.class, sql, userId);
    }

    @Override
    public void signOrder(Integer orderId) {
        String sql = "update t_order set status = '已签收' where order_id = ?";
        update(sql, orderId);
    }
}
