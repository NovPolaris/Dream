package com.puyang.dao.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.CartDao;
import com.puyang.pojo.Cart;
import com.puyang.pojo.CartItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CartDaoImplTest {
    public static final String SKU1 = "1";
    public static final String SKU2 = "2";
    public static final String USERNAME = "unit_test";
    public static final String BOOK_NAME1 = "name";
    public static final String BOOK_NAME2 = "name";
    public static final String SQL_DELETE = "delete from t_cart where username = ?";

    private CartDao cartDao;
    private BaseDao baseDao;
    private CartItem cartItem;

    @BeforeEach
    public void setUp() {
        cartDao = new CartDaoImpl();
        baseDao = new CartDaoImpl();
        cartItem = CartItem.builder()
                .sku(SKU1)
                .count(1)
                .name(BOOK_NAME1)
                .price(BigDecimal.ONE)
                .totalPrice(BigDecimal.ONE)
                .build();
        cartDao.addItem(cartItem, USERNAME);
    }

    @AfterEach
    public void tearDown() {
        baseDao.update(SQL_DELETE, USERNAME);
    }

    @Test
    public void addItem() {
        Cart cart = cartDao.getCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(cartItem, cart.getItems().get(0));

        cartDao.addItem(cartItem, USERNAME);
        cart = cartDao.getCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(SKU1, cart.getItems().get(0).getSku());
        assertEquals(Integer.valueOf(2), cart.getItems().get(0).getCount());
        assertNotEquals(cartItem, cart.getItems().get(0));
    }

    @Test
    public void deleteItem() {
        Cart cart = cartDao.getCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(cartItem, cart.getItems().get(0));

        cartDao.deleteItem(SKU1, USERNAME);
        cart = cartDao.getCart(USERNAME);

        assertNull(cart);
    }

    @Test
    public void clear() {
        CartItem cartItem1 = CartItem.builder()
                .sku(SKU2)
                .count(1)
                .name(BOOK_NAME2)
                .price(BigDecimal.TEN)
                .totalPrice(BigDecimal.TEN)
                .build();
        cartDao.addItem(cartItem1, USERNAME);
        Cart cart = cartDao.getCart(USERNAME);

        assertEquals(2, cart.getItems().size());

        cartDao.clear(USERNAME);
        cart = cartDao.getCart(USERNAME);

        assertNull(cart);
    }

    @Test
    public void updateItem() {
        Cart cart = cartDao.getCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(cartItem, cart.getItems().get(0));

        cartDao.updateItem(SKU1, 10, USERNAME);
        cart = cartDao.getCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(SKU1, cart.getItems().get(0).getSku());
        assertEquals(Integer.valueOf(10), cart.getItems().get(0).getCount());
    }
}
