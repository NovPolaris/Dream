package com.puyang.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JdbcUtilsTest {
    @Test
    void getConnectionAndCommit() {
        Connection connection = JdbcUtils.getConnection();
        assertNotNull(connection);
        JdbcUtils.commitAndClose();
    }

    @Test
    void getConnectionAndRollback() {
        Connection connection = JdbcUtils.getConnection();
        assertNotNull(connection);
        JdbcUtils.rollbackAndClose();
    }
}
