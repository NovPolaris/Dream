package com.puyang.dao;

import com.puyang.pojo.Cart;
import com.puyang.pojo.CartItem;

public interface CartDao {
    void addItem(CartItem cartItem, String username);

    void deleteItem(String sku, String username);

    void clear(String username);

    void updateItem(String sku, Integer count, String username);

    Cart getCart(String username);
}
