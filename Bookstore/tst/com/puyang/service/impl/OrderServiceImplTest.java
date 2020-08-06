package com.puyang.service.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.OrderDao;
import com.puyang.dao.impl.OrderDaoImpl;
import com.puyang.service.OrderService;
import com.puyang.types.Order;
import com.puyang.types.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderServiceImplTest {
    private static final int USER_ID = 1;
    private static final String ORDER_ID = "orderId";
    private static final BigDecimal PRICE = BigDecimal.TEN;
    private static final LocalDateTime CREATE_TIME = LocalDateTime.now().withNano(0);

    private OrderDao orderDao;
    private BaseDao baseDao;
    private Order order;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderDao = new OrderDaoImpl();
        baseDao = new OrderDaoImpl();
        orderService = new OrderServiceImpl();

        order = Order.builder()
                .orderId(ORDER_ID)
                .userId(USER_ID)
                .status(OrderStatus.未付款)
                .price(PRICE)
                .createTime(CREATE_TIME)
                .build();
        orderDao.saveOrder(order);
    }

    @AfterEach
    public void tearDown() {
        String sql = "delete from t_order where user_id = ?";
        baseDao.update(sql, USER_ID);
    }

    @Test
    public void showAllOrders() {
        String sql = "select count(*) from t_order";
        List<Order> orders = orderService.showAllOrders();
        assertEquals((long)baseDao.queryForSingleValue(sql), orders.size());
        assertTrue(orders.contains(order));
    }

    @Test
    public void shipOrder() {
        orderService.shipOrder(ORDER_ID);
        Order expectedOrder = Order.builder()
                .orderId(ORDER_ID)
                .userId(USER_ID)
                .status(OrderStatus.已发货)
                .price(PRICE)
                .createTime(CREATE_TIME)
                .build();
        String sql = "select * from t_order where user_id = ?";
        List<Order> orders = baseDao.queryForList(Order.class, sql, USER_ID);
        assertEquals(1, orders.size());
        assertEquals(expectedOrder, orders.get(0));
    }

    @Test
    public void showMyOrders() {
        List<Order> orders = orderService.showMyOrders(USER_ID);
        assertEquals(1, orders.size());
        assertEquals(order, orders.get(0));
    }

    @Test
    public void signOrder() {
        orderService.signOrder(ORDER_ID);
        String sql = "select * from t_order where user_id = ?";
        List<Order> orders = baseDao.queryForList(Order.class, sql, USER_ID);

        Order expectedOrder = Order.builder()
                .orderId(ORDER_ID)
                .userId(USER_ID)
                .status(OrderStatus.已签收)
                .price(PRICE)
                .createTime(CREATE_TIME)
                .build();
        assertEquals(1, orders.size());
        assertEquals(expectedOrder, orders.get(0));
    }
}