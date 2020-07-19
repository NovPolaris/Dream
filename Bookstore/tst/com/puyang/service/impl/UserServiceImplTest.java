package com.puyang.service.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.impl.UserDaoImpl;
import com.puyang.types.User;
import com.puyang.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceImplTest {
    public static final String SQL_DELETE = "delete from t_user where username = 'unit_test'";
    public static final User USER = User.builder()
            .username("unit_test")
            .password("password")
            .email("email")
            .build();

    private BaseDao userDao;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userDao = new UserDaoImpl();
        userService = new UserServiceImpl();
    }

    @Test
    public void registerUser() {
        assertFalse(userService.existsUsername("unit_test"));
        userService.registerUser(USER);
        assertTrue(userService.existsUsername("unit_test"));
    }

    @Test
    public void login() {
        assertNull(userService.login(USER));
        userService.registerUser(USER);
        assertNotNull(userService.login(USER));
    }

    @AfterEach
    public void tearDown() {
        userDao.update(SQL_DELETE);
    }
}
