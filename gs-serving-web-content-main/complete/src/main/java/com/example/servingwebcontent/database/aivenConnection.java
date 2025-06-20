package com.example.servingwebcontent.database;

import java.sql.*;

public class aivenConnection {

    public void aivenConn() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Thông tin kết nối đến Aiven MySQL
            String url = "jdbc:mysql://mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED";
            String user = "avnadmin";
            String password = "AVNS_HNm9Mr2leXuYSrqITaj";

            // Thiết lập kết nối
            conn = DriverManager.getConnection(url, user, password);

            // Truy vấn bảng NguoiDung
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM NguoiDung");

            System.out.println("📦 Danh sách người dùng:");
            while (rs.next()) {
                String userId = rs.getString("UserID");
                String email = rs.getString("Email");
                String role = rs.getString("VaiTro");

                System.out.printf("UserID: %s | Email: %s | Vai trò: %s\n", userId, email, role);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("❌ Lỗi kết nối hoặc truy vấn:");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("❗ Lỗi khi đóng tài nguyên:");
                e.printStackTrace();
            }
        }
    }
}
