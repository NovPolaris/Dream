package com.puyang.dao;

import com.puyang.types.Cart;
import com.puyang.types.CartItem;

public interface CartDao {
    void addItem(CartItem cartItem, String username);

    void deleteItem(Integer bookId, String username);

    void clear(String username);

    void updateItem(Integer bookId, Integer count, String username);

    Cart queryCart(String username);

    long queryForTotalCount(String username);
}
