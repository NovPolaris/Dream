package com.puyang.dao.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.UserDao;
import com.puyang.pojo.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    public static final String SQL_DELETE = "delete from t_user where username = 'unit_test'";
    public static final String SQL_DELETE1 = "delete from t_user where username = 'unit_test1'";
    public static final String SQL_DELETE2 = "delete from t_user where username = 'unit_test2'";
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

    @Test
    public void checkId() {
        User user1 = User.builder()
                .username("unit_test1")
                .password("password")
                .email("email")
                .build();
        User user2 = User.builder()
                .username("unit_test2")
                .password("password")
                .email("email")
                .build();
        userDao.saveUser(user1);
        userDao.saveUser(user2);
        User actual1 = userDao.queryUserByUsernameAndPassword("unit_test1", "password");
        User actual2 = userDao.queryUserByUsernameAndPassword("unit_test2", "password");
        assertNotEquals(actual1.getId(), actual2.getId());
        baseDao.update(SQL_DELETE1);
        baseDao.update(SQL_DELETE2);
    }

    @AfterEach
    public void tearDown() {
        baseDao.update(SQL_DELETE);
    }
}
