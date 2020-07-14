package com.puyang.service.impl;

import com.puyang.dao.CartDao;
import com.puyang.dao.impl.CartDaoImpl;
import com.puyang.pojo.Cart;
import com.puyang.pojo.CartItem;
import com.puyang.service.CartService;

public class CartServiceImpl implements CartService {
    private final CartDao cartDao = new CartDaoImpl();

    @Override
    public void addItem(CartItem cartItem, String username) {
        cartDao.addItem(cartItem, username);
    }

    @Override
    public void deleteItem(String sku, String username) {
        cartDao.deleteItem(sku, username);
    }

    @Override
    public void clear(String username) {
        cartDao.clear(username);
    }

    @Override
    public void updateItem(String sku, Integer count, String username) {
        cartDao.updateItem(sku, count, username);
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
