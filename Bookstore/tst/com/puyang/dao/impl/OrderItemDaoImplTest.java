package com.puyang.dao.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.OrderDao;
import com.puyang.dao.OrderItemDao;
import com.puyang.types.Order;
import com.puyang.types.OrderItem;
import com.puyang.types.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemDaoImplTest {
    private static final int USER_ID = 1;
    private static final String NAME = "unit_test";
    private static final String ORDER_ID = "orderId";
    private static final BigDecimal PRICE = BigDecimal.TEN;
    private static final LocalDateTime CREATE_TIME = LocalDateTime.now().withNano(0);
    public static final String DELETE_ORDER_ITEMS = "delete from t_order_item where order_id = ?";
    public static final String DELETE_ORDER = "delete from t_order where user_id = ?";

    private OrderItem orderItem;
    private BaseDao baseDao;
    private OrderItemDao orderItemDao;

    @BeforeEach
    public void setUp() {
        baseDao = new OrderItemDaoImpl();
        orderItemDao = new OrderItemDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        Order order = Order.builder()
                .orderId(ORDER_ID)
                .userId(USER_ID)
                .status(OrderStatus.未付款)
                .price(PRICE)
                .createTime(CREATE_TIME)
                .build();
        orderDao.saveOrder(order);

        orderItem = OrderItem.builder()
                .orderId(ORDER_ID)
                .price(BigDecimal.TEN)
                .count(1)
                .name(NAME)
                .totalPrice(BigDecimal.TEN)
                .build();
        orderItemDao.saveOrderItem(orderItem);
    }

    @AfterEach
    public void tearDown() {
        baseDao.update(DELETE_ORDER_ITEMS, ORDER_ID);
        baseDao.update(DELETE_ORDER, USER_ID);
    }

    @Test
    public void saveOrderItem() {
        String sql = "select * from t_order_item where name = 'unit_test'";
        List<OrderItem> orderItems = baseDao.queryForList(OrderItem.class, sql);

        assertEquals(1, orderItems.size());
        assertEquals(orderItem, orderItems.get(0));
    }

    @Test
    public void queryOrderItemsByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId(ORDER_ID);

        assertEquals(1, orderItems.size());
        assertEquals(orderItem, orderItems.get(0));
    }
}
