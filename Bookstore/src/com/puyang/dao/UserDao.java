package com.puyang.dao;

import com.puyang.types.User;

public interface UserDao {
    User queryUserByUsername(String username);

    User queryUserByUsernameAndPassword(String username, String password);

    int saveUser(User user);

    User queryUserByUserId(Integer userId);
}
