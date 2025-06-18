package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.servingwebcontent.MonAn;

public class monAnAiven {

    public monAnAiven() {}

    public ArrayList<MonAn> getMonAnListFromAiven() {
        ArrayList<MonAn> items = new ArrayList<>();

        Connection conn = null;
        Statement sta = null;
        ResultSet setdata = null;

        try {
            try {
                // Load JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.err.println("❌ Lỗi khi load JDBC driver:");
                e.printStackTrace();
            }

            try {
                // Thiết lập kết nối – dùng biến môi trường cho bảo mật nếu cần
                String url = "jdbc:mysql://mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED";
                String user = "avnadmin";
                String pass = "AVNS_HNm9Mr2leXuYSrqITaj"; // Thay bằng biến môi trường nếu chạy thực tế

                conn = DriverManager.getConnection(url, user, pass);
                sta = conn.createStatement();

                String query = "SELECT ma_mon_an, ten_mon_an, don_gia, loai_mon_an, trang_thai, so_luong_da_ban FROM mon_an LIMIT 10";
                setdata = sta.executeQuery(query);

                while (setdata.next()) {
                    MonAn monAn = new MonAn();

                    monAn.setMaMonAn(setdata.getInt("ma_mon_an"));
                    monAn.setTenMonAn(setdata.getString("ten_mon_an"));
                    monAn.setDonGia(setdata.getDouble("don_gia"));
                    monAn.setLoaiMonAn(setdata.getString("loai_mon_an"));
                    monAn.setTrangThai(setdata.getString("trang_thai"));
                    monAn.setSoLuongDaBan(setdata.getInt("so_luong_da_ban"));

                    // In để kiểm tra
                    System.out.println("MON AN AIVEN DATA:");
                    System.out.println("Mã: " + monAn.getMaMonAn() +
                                       ", Tên: " + monAn.getTenMonAn() +
                                       ", Giá: " + monAn.getDonGia());

                    items.add(monAn);
                }

            } catch (Exception e) {
                System.err.println("❌ Lỗi khi truy vấn cơ sở dữ liệu Aiven:");
                e.printStackTrace();
            }

        } finally {
            // Đảm bảo tài nguyên được giải phóng
            try {
                if (setdata != null) setdata.close();
            } catch (Exception e) {
                System.err.println("❌ Lỗi khi đóng ResultSet: " + e.getMessage());
            }

            try {
                if (sta != null) sta.close();
            } catch (Exception e) {
                System.err.println("❌ Lỗi khi đóng Statement: " + e.getMessage());
            }

            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.err.println("❌ Lỗi khi đóng Connection: " + e.getMessage());
            }
        }

        return items;
    }
}
