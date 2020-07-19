package com.puyang.service.impl;

import com.puyang.dao.BookDao;
import com.puyang.dao.CartDao;
import com.puyang.dao.OrderDao;
import com.puyang.dao.OrderItemDao;
import com.puyang.dao.UserDao;
import com.puyang.dao.impl.BookDaoImpl;
import com.puyang.dao.impl.CartDaoImpl;
import com.puyang.dao.impl.OrderDaoImpl;
import com.puyang.dao.impl.OrderItemDaoImpl;
import com.puyang.dao.impl.UserDaoImpl;
import com.puyang.service.OrderService;
import com.puyang.types.Book;
import com.puyang.types.Cart;
import com.puyang.types.CartItem;
import com.puyang.types.Order;
import com.puyang.types.OrderItem;
import com.puyang.types.OrderStatus;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    private final OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private final CartDao cartDao = new CartDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = generateOrderId(userId);
        Order order = new Order(orderId, LocalDateTime.now(), cart.getTotalPrice(), OrderStatus.未付款, userId);
        orderDao.saveOrder(order);

        saveOrderItems(cart, orderId);
        cartDao.clear(userDao.queryUserByUserId(userId).getUsername());
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public void shipOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, OrderStatus.已发货);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public void signOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, OrderStatus.已签收);
    }

    private String generateOrderId(Integer userId) {
        return System.currentTimeMillis() + "" + userId;
    }

    private void saveOrderItems(Cart cart, String orderId) {
        for (CartItem cartItem : cart.getItems()) {
            Book book = bookDao.queryBookById(cartItem.getBookId());
            if (book.getStock() < cartItem.getCount()) {
                throw new IllegalArgumentException("库存不足！需求：" + cartItem.getCount() + "，已有库存：" + book.getStock());
            }
            bookDao.updateBook(Book.builder()
                    .id(book.getId())
                    .author(book.getAuthor())
                    .name(book.getName())
                    .price(book.getPrice())
                    .sales(book.getSales() + cartItem.getCount())
                    .stock(book.getStock() - cartItem.getCount())
                    .imgPath(book.getImgPath())
                    .build());
            orderItemDao.saveOrderItem(OrderItem.builder()
                    .name(cartItem.getName())
                    .orderId(orderId)
                    .count(cartItem.getCount())
                    .price(cartItem.getPrice())
                    .totalPrice(cartItem.getTotalPrice())
                    .build());
        }
    }
}
