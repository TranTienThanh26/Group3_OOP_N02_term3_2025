package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.Ban;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BanAiven {

    private final myConnection db = new myConnection(); // Dùng class kết nối riêng

    public List<Ban> getBanListFromAiven() {
        List<Ban> banList = new ArrayList<>();
        String query = "SELECT MaBan, TenBan, TrangThai FROM Ban";

        try (
            Connection conn = db.getConnection();
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

    public void themBan(Ban ban) {
        String query = "INSERT INTO Ban (TenBan, TrangThai) VALUES (?, ?)";

        try (
            Connection conn = db.getConnection();
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

    public void updateBan(Ban ban) {
        String query = "UPDATE Ban SET TenBan = ?, TrangThai = ? WHERE MaBan = ?";

        try (
            Connection conn = db.getConnection();
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

    public void deleteById(int maBan) {
        String query = "DELETE FROM Ban WHERE MaBan = ?";

        try (
            Connection conn = db.getConnection();
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

    public void capNhatTrangThai(int maBan, String trangThaiMoi) {
        String query = "UPDATE Ban SET TrangThai = ? WHERE MaBan = ?";

        try (
            Connection conn = db.getConnection();
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

    public List<Ban> timBanTheoTen(String keyword) {
        List<Ban> result = new ArrayList<>();
        String query = "SELECT MaBan, TenBan, TrangThai FROM Ban WHERE TenBan LIKE ?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ban ban = new Ban();
                ban.setMaBan(rs.getInt("MaBan"));
                ban.setTenBan(rs.getString("TenBan"));
                ban.setTrangThai(rs.getString("TrangThai"));
                result.add(ban);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi tìm kiếm bàn:");
            e.printStackTrace();
        }

        return result;
    }

    public int getMaBanTheoTen(String tenBan) {
        List<Ban> danhSachBan = getBanListFromAiven();
        for (Ban b : danhSachBan) {
            if (b.getTenBan().equalsIgnoreCase(tenBan)) {
                return b.getMaBan();
            }
        }
        return -1;
    }
}
