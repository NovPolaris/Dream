package com.puyang.dao.impl;

import com.puyang.dao.UserDao;
import com.puyang.pojo.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    public static final String SQL_DELETE = "delete from t_user where username = 'unit_test'";
    public static final User USER = User.builder()
            .username("unit_test")
            .password("password")
            .email("email")
            .build();

    private UserDao userDao;
    private BaseDao baseDao;

    @BeforeEach
    public void setUp() {
        baseDao = new UserDaoImpl();
        userDao = new UserDaoImpl();
    }

    @Test
    public void queryUserByUsername() {
        assertNull(userDao.queryUserByUsername("unit_test"));
        userDao.saveUser(USER);
        assertEquals(USER, userDao.queryUserByUsername("unit_test"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        assertNull(userDao.queryUserByUsernameAndPassword("unit_test", "password"));
        userDao.saveUser(USER);
        assertEquals(USER, userDao.queryUserByUsernameAndPassword("unit_test", "password"));
    }

    @AfterEach
    public void tearDown() {
        baseDao.update(SQL_DELETE);
    }
}