package com.puyang.bjpowernode.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class Test01 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com/puyang/bjpowernode/jdbc/jdbc");
        try {
            Class.forName(resourceBundle.getString("database"));
            String url = resourceBundle.getString("url");
            String userName = resourceBundle.getString("userName");
            String password = resourceBundle.getString("password");
            connection = DriverManager.getConnection(url, userName, password);

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from dept");

            while (resultSet.next()) {
                String number = resultSet.getString("deptno");
                String name = resultSet.getString("dname");
                String location = resultSet.getString("loc");
                System.out.println(number + " " + name + " " + location);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
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
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
