package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.CTHD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CTHDAiven {

    private static final String JDBC_URL = "jdbc:mysql://mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_HNm9Mr2leXuYSrqITaj";

    public void insertCTHD(CTHD cthd) {
        String sql = "INSERT INTO CTHD (ID_HD, ID_MonAn, TenMonAn, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?, ?)";  // ✅ thêm DonGia
        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, cthd.getID_HD());
            stmt.setInt(2, cthd.getID_MonAn());
            stmt.setString(3, cthd.getTenMonAn());
            stmt.setInt(4, cthd.getSoluong());
            stmt.setInt(5, cthd.getDonGia());      // ✅ cần có dòng này
            stmt.setInt(6, cthd.getThanhTien());
            stmt.executeUpdate();
            System.out.println("✅ Đã lưu chi tiết hóa đơn.");
        } catch (Exception e) {
            System.err.println("❌ Lỗi lưu chi tiết hóa đơn:");
            e.printStackTrace();
        }
    }
    
    }

