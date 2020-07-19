package com.puyang.service.impl;

import com.puyang.dao.OrderDao;
import com.puyang.dao.impl.OrderDaoImpl;
import com.puyang.service.OrderService;
import com.puyang.types.Order;
import com.puyang.types.OrderItem;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void createOrder() {
        orderDao.createOrder();
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.showAllOrders();
    }

    @Override
    public void shipOrder(Integer orderId) {
        orderDao.shipOrder(orderId);
    }

    @Override
    public List<OrderItem> showOrderDetail(Integer orderId) {
        return orderDao.showOrderDetail(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.showMyOrders(userId);
    }

    @Override
    public void signOrder(Integer orderId) {
        orderDao.signOrder(orderId);
    }
}
