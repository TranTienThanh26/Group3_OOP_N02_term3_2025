package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.Model.MonAn;

public class monAnAiven {

    private static final String JDBC_URL =
        "jdbc:mysql://mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_HNm9Mr2leXuYSrqITaj";

    public List<MonAn> getMonAnListFromAiven() {
        List<MonAn> items = new ArrayList<>();

        String query = "SELECT MaMonAn, TenMonAn, DonGia, LoaiMonAn, TrangThai, SoLuongDaBan, HinhAnh FROM MonAn";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(rs.getInt("MaMonAn"));
                monAn.setTenMonAn(rs.getString("TenMonAn"));
                monAn.setDonGia(rs.getDouble("DonGia"));
                monAn.setLoaiMonAn(rs.getString("LoaiMonAn"));
                monAn.setTrangThai(rs.getString("TrangThai"));
                monAn.setSoLuongDaBan(rs.getInt("SoLuongDaBan"));
                monAn.setHinhAnh(rs.getString("HinhAnh")); // 👈 Thêm dòng này

                // In kiểm tra
                System.out.printf("📦 Món ăn: ID=%d, Tên=%s, Giá=%.2f, Hình=%s\n",
                        monAn.getMaMonAn(), monAn.getTenMonAn(), monAn.getDonGia(), monAn.getHinhAnh());

                items.add(monAn);
            }

        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn MonAn từ Aiven:");
            e.printStackTrace();
        }

        return items;
    }
}
