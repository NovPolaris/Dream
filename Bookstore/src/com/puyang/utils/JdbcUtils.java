package com.puyang.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
    private static final String CONFIGURATION_PATH = "jdbc.properties";
    private static final Properties PROPERTIES = new Properties();

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
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(PROPERTIES.getProperty("url"),
                    PROPERTIES.getProperty("username"),
                    PROPERTIES.getProperty("password"));
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

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
