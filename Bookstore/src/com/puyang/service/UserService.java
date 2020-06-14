package com.puyang.service;

import com.puyang.pojo.User;

public interface UserService {
    void registerUser(User user);

    User login(User user);

    boolean existsUsername(String username);
}
