package com.puyang.dao.impl;

import com.google.common.primitives.Ints;
import com.puyang.dao.BaseDao;
import com.puyang.dao.CartDao;
import com.puyang.pojo.Cart;
import com.puyang.pojo.CartItem;
import com.puyang.pojo.Page;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class CartDaoImpl extends BaseDao implements CartDao {
    @Override
    public void addItem(CartItem cartItem, String username) {
        String sql_query = "select * from t_cart where sku = ? and username = ?";
        String sql_insert = "insert into t_cart(sku, name, username, count, price, total_price) values (?, ?, ?, ?, ?, ?)";
        String sql_update = "update t_cart set count = ?, total_price = ? where sku = ? and username = ?";

        CartItem item = queryForOne(CartItem.class, sql_query, cartItem.getSku(), username);

        if (item == null) {
            update(sql_insert, cartItem.getSku(), cartItem.getName(), username, cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice());
        } else {
            int totalCount = item.getCount() + cartItem.getCount();
            update(sql_update, totalCount, cartItem.getPrice().multiply(new BigDecimal(totalCount)), cartItem.getSku(), username);
        }
    }

    @Override
    public void deleteItem(String sku, String username) {
        String sql = "delete from t_cart where sku = ? and username = ?";
        update(sql, sku, username);
    }

    @Override
    public void clear(String username) {
        String sql = "delete from t_cart where username = ?";
        update(sql, username);
    }

    @Override
    public void updateItem(String sku, Integer count, String username) {
        String sql_query = "select * from t_cart where sku = ? and username = ?";
        String sql_update = "update t_cart set count = ?, total_price = ? where sku = ? and username = ?";

        CartItem item = queryForOne(CartItem.class, sql_query, sku, username);

        if (item == null) {
            return;
        }

        update(sql_update, count, item.getPrice().multiply(new BigDecimal(count)), sku, username);
    }

    @Override
    public Cart queryCart(String username) {
        String sql_query = "select * from t_cart where username = ?";

        List<CartItem> cartItems = queryForList(CartItem.class, sql_query, username);

        if (cartItems == null || cartItems.size() == 0) {
            return Cart.builder()
                    .username(username)
                    .totalCount(0)
                    .totalPrice(BigDecimal.ZERO)
                    .items(Collections.emptyList())
                    .build();
        }

        int totalCount = cartItems.stream().mapToInt(CartItem::getCount).sum();
        BigDecimal totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Cart.builder()
                .username(username)
                .totalCount(totalCount)
                .totalPrice(totalPrice)
                .items(cartItems)
                .build();
    }

    @Override
    public long queryForTotalCount(String username) {
        String sql = "select sum(count) from t_cart where username = ?";
        if (queryForSingleValue(sql, username) == null) {
            return 0;
        }
        return ((BigDecimal)queryForSingleValue(sql, username)).longValue();
    }
}
