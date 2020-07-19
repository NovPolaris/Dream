package com.puyang.service.impl;

import com.puyang.dao.CartDao;
import com.puyang.dao.impl.CartDaoImpl;
import com.puyang.types.Cart;
import com.puyang.types.CartItem;
import com.puyang.service.CartService;

public class CartServiceImpl implements CartService {
    private final CartDao cartDao = new CartDaoImpl();

    @Override
    public void addItem(CartItem cartItem, String username) {
        cartDao.addItem(cartItem, username);
    }

    @Override
    public void deleteItem(Integer bookId, String username) {
        cartDao.deleteItem(bookId, username);
    }

    @Override
    public void clear(String username) {
        cartDao.clear(username);
    }

    @Override
    public void updateItem(Integer bookId, Integer count, String username) {
        cartDao.updateItem(bookId, count, username);
    }

    @Override
    public Cart queryCart(String username) {
        return cartDao.queryCart(username);
    }

    @Override
    public long queryForTotalCount(String username) {
        return cartDao.queryForTotalCount(username);
    }
}
