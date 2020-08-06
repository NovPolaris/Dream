package com.puyang.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static final String CONFIGURATION_PATH = "configuration/property/jdbc.properties";
    private static final Properties PROPERTIES = new Properties();
    private static final ThreadLocal<Connection> connection = new ThreadLocal<>();

    static {
        try {
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream(CONFIGURATION_PATH);
            PROPERTIES.load(inputStream);
            Class.forName(PROPERTIES.getProperty("driverClassName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = connection.get();
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(PROPERTIES.getProperty("url"),
                        PROPERTIES.getProperty("username"),
                        PROPERTIES.getProperty("password"));
                connection.set(conn);
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void commitAndClose() {
        Connection conn = connection.get();
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        connection.remove();
    }

    public static void rollbackAndClose() {
        Connection conn = connection.get();
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        connection.remove();
    }
}
