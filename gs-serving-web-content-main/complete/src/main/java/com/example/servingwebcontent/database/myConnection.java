package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class myConnection {
    private static final String JDBC_URL =
        "jdbc:mysql://mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?sslMode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_HNm9Mr2leXuYSrqITaj";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER); // Load driver
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD); // Connect
        } catch (Exception e) {
            System.err.println("❌ Kết nối Aiven thất bại:");
            e.printStackTrace();
        }
        return conn;
    }
}
