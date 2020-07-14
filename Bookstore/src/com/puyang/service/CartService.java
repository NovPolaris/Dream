package com.puyang.service;

import com.puyang.pojo.Cart;
import com.puyang.pojo.CartItem;

public interface CartService {
    void addItem(CartItem cartItem, String username);

    void deleteItem(String sku, String username);

    void clear(String username);

    void updateItem(String sku, Integer count, String username);

    Cart queryCart(String username);

    long queryForTotalCount(String username);
}
