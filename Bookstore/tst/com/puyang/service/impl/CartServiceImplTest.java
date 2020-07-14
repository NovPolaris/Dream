package com.puyang.service.impl;

import com.puyang.pojo.Cart;
import com.puyang.pojo.CartItem;
import com.puyang.service.CartService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CartServiceImplTest {
    public static final String SKU1 = "1";
    public static final String SKU2 = "2";
    public static final String USERNAME = "unit_test";
    public static final String BOOK_NAME1 = "name";
    public static final String BOOK_NAME2 = "name";

    private CartService cartService;
    private CartItem cartItem;

    @BeforeEach
    public void setUp() {
        cartService = new CartServiceImpl();
        cartItem = CartItem.builder()
                .sku(SKU1)
                .count(1)
                .name(BOOK_NAME1)
                .price(BigDecimal.ONE)
                .totalPrice(BigDecimal.ONE)
                .build();
        cartService.addItem(cartItem, USERNAME);
    }

    @AfterEach
    public void tearDown() {
        cartService.clear(USERNAME);
    }

    @Test
    public void addItem() {
        Cart cart = cartService.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(cartItem, cart.getItems().get(0));

        cartService.addItem(cartItem, USERNAME);
        cart = cartService.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(SKU1, cart.getItems().get(0).getSku());
        assertEquals(Integer.valueOf(2), cart.getItems().get(0).getCount());
        assertNotEquals(cartItem, cart.getItems().get(0));
    }

    @Test
    public void deleteItem() {
        Cart cart = cartService.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(cartItem, cart.getItems().get(0));

        cartService.deleteItem(SKU1, USERNAME);
        cart = cartService.queryCart(USERNAME);

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
        cartService.addItem(cartItem1, USERNAME);
        Cart cart = cartService.queryCart(USERNAME);

        assertEquals(2, cart.getItems().size());

        cartService.clear(USERNAME);
        cart = cartService.queryCart(USERNAME);

        assertEquals(0, cart.getItems().size());
        assertEquals(Integer.valueOf(0), cart.getTotalCount());
    }

    @Test
    public void updateItem() {
        Cart cart = cartService.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(cartItem, cart.getItems().get(0));

        cartService.updateItem(SKU1, 10, USERNAME);
        cart = cartService.queryCart(USERNAME);

        assertEquals(1, cart.getItems().size());
        assertEquals(SKU1, cart.getItems().get(0).getSku());
        assertEquals(Integer.valueOf(10), cart.getItems().get(0).getCount());
    }

    @Test
    public void queryForTotalCount() {
        assertEquals(1, cartService.queryForTotalCount(USERNAME));

        CartItem newItem = CartItem.builder()
                .sku(SKU2)
                .count(10)
                .name(BOOK_NAME2)
                .price(BigDecimal.ONE)
                .totalPrice(BigDecimal.TEN)
                .build();
        cartService.addItem(newItem, USERNAME);

        assertEquals(11, cartService.queryForTotalCount(USERNAME));
    }
}