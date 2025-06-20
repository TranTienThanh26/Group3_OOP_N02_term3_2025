package com.example.servingwebcontent.database;

import java.sql.*;

import com.example.servingwebcontent.Model.NguoiDung;

public class NguoiDungAiven {

    private static final String JDBC_URL =
        "jdbc:mysql://mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_HNm9Mr2leXuYSrqITaj";

    public NguoiDung login(String email, String matKhau) {
        String sql = "SELECT * FROM NguoiDung WHERE Email = ? AND TrangThai = 'Active'";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pst = conn.prepareStatement(sql)
        ) {
            pst.setString(1, email.trim());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("MatKhau");

                System.out.println(">>> DEBUG Email: " + email);
                System.out.println(">>> DEBUG DB Password: " + storedPassword);
                System.out.println(">>> DEBUG Input Password: " + matKhau.trim());

                if (matKhau.trim().equals(storedPassword)) {
                    System.out.println("✅ Đăng nhập thành công!");

                    NguoiDung user = new NguoiDung();
                    user.setUserID(rs.getInt("UserID"));
                    user.setEmail(rs.getString("Email"));
                    user.setPassword(storedPassword);
                    user.setRole(rs.getString("VaiTro"));
                    return user;
                } else {
                    System.out.println("❌ Mật khẩu không khớp.");
                }
            } else {
                System.out.println("❌ Không tìm thấy người dùng hoặc trạng thái không phải 'Active'.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn MySQL:");
            e.printStackTrace();
        }

        return null;
    }
}
