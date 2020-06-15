package com.puyang.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    public static final String CONFIGURATION_PATH = "jdbc.properties";

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream(CONFIGURATION_PATH);
            properties.load(inputStream);
            Class.forName(properties.getProperty("driverClassName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
