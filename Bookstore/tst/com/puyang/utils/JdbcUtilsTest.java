package com.puyang.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static com.puyang.utils.JdbcUtils.close;
import static org.junit.jupiter.api.Assertions.*;

class JdbcUtilsTest {
    @Test
    void getConnectionAndClose() {
        Connection connection = JdbcUtils.getConnection();
        assertNotNull(connection);
        JdbcUtils.close(connection);
    }
}