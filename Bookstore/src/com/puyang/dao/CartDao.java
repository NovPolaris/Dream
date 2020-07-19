package com.puyang.dao;

import com.puyang.types.Cart;
import com.puyang.types.CartItem;

public interface CartDao {
    void addItem(CartItem cartItem, String username);

    void deleteItem(String sku, String username);

    void clear(String username);

    void updateItem(String sku, Integer count, String username);

    Cart queryCart(String username);

    long queryForTotalCount(String username);
}
