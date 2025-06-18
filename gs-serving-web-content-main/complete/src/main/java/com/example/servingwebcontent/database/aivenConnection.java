package com.example.servingwebcontent.database;

import java.sql.*;

public class aivenConnection {

    public void aivenConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Lấy thông tin từ biến môi trường
            String dbUrl = System.getenv("AIVEN_DB_URL");
            String dbUser = System.getenv("AIVEN_DB_USER");
            String dbPassword = System.getenv("AIVEN_DB_PASSWORD");

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            Statement sta = conn.createStatement();
            ResultSet reset = sta.executeQuery("SELECT * FROM user");
            System.out.println("Display data from database: ");
            while (reset.next()) {
                String userID = reset.getString("userId");
                String username = reset.getString("username");
                String address = reset.getString("address");
                System.out.println(userID + " " + username + " " + address);
            }

            reset.close();
            sta.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error in database connection");
            e.printStackTrace();
        }
    }
}
