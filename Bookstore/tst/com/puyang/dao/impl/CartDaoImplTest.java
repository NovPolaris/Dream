package com.puyang.dao.impl;

import com.puyang.dao.CartDao;
import com.puyang.types.Cart;
import com.puyang.types.CartItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CartDaoImplTest {
    public static final String SKU1 = "1";
    public static final String SKU2 = "2";
    public static final String USERNAME = "unit_test";
    public static final String BOOK_NAME1 = "name";
    public static final String BOOK_NAME2 = "name";

    private CartDao cartDao;
    private CartItem cartItem;

    @BeforeEach
    public void setUp() {
        cartDao = new CartDaoImpl();
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
        cartDao.clear(USERNAME);
    }

    @Test
    public void addItem() {
        Cart cart = cartDao.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(cartItem, cart.getItems().get(0));

        cartDao.addItem(cartItem, USERNAME);
        cart = cartDao.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(SKU1, cart.getItems().get(0).getSku());
        assertEquals(Integer.valueOf(2), cart.getItems().get(0).getCount());
        assertNotEquals(cartItem, cart.getItems().get(0));
    }

    @Test
    public void deleteItem() {
        Cart cart = cartDao.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(cartItem, cart.getItems().get(0));

        cartDao.deleteItem(SKU1, USERNAME);
        cart = cartDao.queryCart(USERNAME);

        assertEquals(0, cart.getItems().size());
        assertEquals(Integer.valueOf(0), cart.getTotalCount());
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
        Cart cart = cartDao.queryCart(USERNAME);

        assertEquals(2, cart.getItems().size());

        cartDao.clear(USERNAME);
        cart = cartDao.queryCart(USERNAME);

        assertEquals(0, cart.getItems().size());
        assertEquals(Integer.valueOf(0), cart.getTotalCount());
    }

    @Test
    public void updateItem() {
        Cart cart = cartDao.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(cartItem, cart.getItems().get(0));

        cartDao.updateItem(SKU1, 10, USERNAME);
        cart = cartDao.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(SKU1, cart.getItems().get(0).getSku());
        assertEquals(Integer.valueOf(10), cart.getItems().get(0).getCount());
    }

    @Test
    public void queryForTotalCount() {
        assertEquals(1, cartDao.queryForTotalCount(USERNAME));

        CartItem newItem = CartItem.builder()
                .sku(SKU2)
                .count(10)
                .name(BOOK_NAME2)
                .price(BigDecimal.ONE)
                .totalPrice(BigDecimal.TEN)
                .build();
        cartDao.addItem(newItem, USERNAME);

        assertEquals(11, cartDao.queryForTotalCount(USERNAME));
    }
}
