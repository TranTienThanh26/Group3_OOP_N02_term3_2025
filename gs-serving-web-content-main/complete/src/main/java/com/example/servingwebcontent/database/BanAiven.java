package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.Ban;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BanAiven {

    private static final String JDBC_URL =
        "jdbc:mysql://mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_HNm9Mr2leXuYSrqITaj";

    // ✅ Lấy danh sách tất cả bàn ăn
    public List<Ban> getBanListFromAiven() {
        List<Ban> banList = new ArrayList<>();
        String query = "SELECT MaBan, TenBan, TrangThai FROM Ban";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                Ban ban = new Ban();
                ban.setMaBan(rs.getInt("MaBan"));
                ban.setTenBan(rs.getString("TenBan"));
                ban.setTrangThai(rs.getString("TrangThai"));
                banList.add(ban);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn Ban từ Aiven:");
            e.printStackTrace();
        }

        return banList;
    }

    // ✅ Thêm bàn mới
    public void themBan(Ban ban) {
        String query = "INSERT INTO Ban (TenBan, TrangThai) VALUES (?, ?)";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, ban.getTenBan());
            stmt.setString(2, ban.getTrangThai());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã thêm " + rows + " bàn mới.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi thêm Ban:");
            e.printStackTrace();
        }
    }

    // ✅ Cập nhật thông tin bàn
    public void updateBan(Ban ban) {
        String query = "UPDATE Ban SET TenBan = ?, TrangThai = ? WHERE MaBan = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, ban.getTenBan());
            stmt.setString(2, ban.getTrangThai());
            stmt.setInt(3, ban.getMaBan());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã cập nhật " + rows + " bàn.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật Ban:");
            e.printStackTrace();
        }
    }

    // ✅ Xoá bàn theo ID
    public void deleteById(int maBan) {
        String query = "DELETE FROM Ban WHERE MaBan = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, maBan);
            int rows = stmt.executeUpdate();
            System.out.println("🗑️ Đã xoá " + rows + " bàn.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xoá Ban:");
            e.printStackTrace();
        }
    }

    // ✅ Cập nhật trạng thái bàn riêng (đặt bàn)
    public void capNhatTrangThai(int maBan, String trangThaiMoi) {
        String query = "UPDATE Ban SET TrangThai = ? WHERE MaBan = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, trangThaiMoi);
            stmt.setInt(2, maBan);
            int rows = stmt.executeUpdate();
            System.out.println("🔁 Đã cập nhật trạng thái " + rows + " bàn.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật trạng thái Ban:");
            e.printStackTrace();
        }
    }
}
