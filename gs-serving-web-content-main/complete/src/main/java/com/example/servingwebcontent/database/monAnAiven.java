package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.MonAn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class monAnAiven {

    private Connection getConn() throws SQLException {
        return new myConnection().getConnection();
    }

    // ✅ Lấy danh sách món ăn
    public List<MonAn> getMonAnListFromAiven() {
        List<MonAn> items = new ArrayList<>();
        String query = "SELECT MaMonAn, TenMonAn, DonGia, LoaiMonAn, TrangThai, SoLuongDaBan, HinhAnh FROM MonAn";

        try (
            Connection conn = getConn();
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
                monAn.setHinhAnh(rs.getString("HinhAnh"));
                items.add(monAn);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn MonAn từ Aiven:");
            e.printStackTrace();
        }

        return items;
    }

    // ✅ Thêm món ăn mới
    public void themMonAn(MonAn monAn) {
        String query = "INSERT INTO MonAn (TenMonAn, DonGia, LoaiMonAn, TrangThai, SoLuongDaBan, HinhAnh) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, monAn.getTenMonAn());
            stmt.setDouble(2, monAn.getDonGia());
            stmt.setString(3, monAn.getLoaiMonAn());
            stmt.setString(4, monAn.getTrangThai());
            stmt.setInt(5, monAn.getSoLuongDaBan());
            stmt.setString(6, monAn.getHinhAnh());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã thêm " + rows + " món ăn mới.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi thêm MonAn:");
            e.printStackTrace();
        }
    }

    // ✅ Cập nhật món ăn
    public void update(MonAn monAn) {
        String query = "UPDATE MonAn SET TenMonAn = ?, DonGia = ?, LoaiMonAn = ?, TrangThai = ?, SoLuongDaBan = ?, HinhAnh = ? WHERE MaMonAn = ?";

        try (
            Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, monAn.getTenMonAn());
            stmt.setDouble(2, monAn.getDonGia());
            stmt.setString(3, monAn.getLoaiMonAn());
            stmt.setString(4, monAn.getTrangThai());
            stmt.setInt(5, monAn.getSoLuongDaBan());
            stmt.setString(6, monAn.getHinhAnh());
            stmt.setInt(7, monAn.getMaMonAn());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã cập nhật " + rows + " món ăn.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật MonAn:");
            e.printStackTrace();
        }
    }

    // ✅ Xoá món ăn theo ID
    public void deleteById(int id) {
        String query = "DELETE FROM MonAn WHERE MaMonAn = ?";

        try (
            Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("🗑️ Đã xoá " + rows + " món ăn.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xoá MonAn:");
            e.printStackTrace();
        }
    }

    // ✅ Tìm món ăn theo tên
    public MonAn timMonAnTheoTen(String tenMonAn) {
        String query = "SELECT MaMonAn, TenMonAn, DonGia, LoaiMonAn, TrangThai, SoLuongDaBan, HinhAnh FROM MonAn WHERE TenMonAn = ? LIMIT 1";

        try (
            Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, tenMonAn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(rs.getInt("MaMonAn"));
                monAn.setTenMonAn(rs.getString("TenMonAn"));
                monAn.setDonGia(rs.getDouble("DonGia"));
                monAn.setLoaiMonAn(rs.getString("LoaiMonAn"));
                monAn.setTrangThai(rs.getString("TrangThai"));
                monAn.setSoLuongDaBan(rs.getInt("SoLuongDaBan"));
                monAn.setHinhAnh(rs.getString("HinhAnh"));
                return monAn;
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi tìm món ăn theo tên:");
            e.printStackTrace();
        }

        return null;
    }

    // ✅ Tìm món ăn theo ID
    public MonAn timMonAnTheoId(int id) {
        String query = "SELECT MaMonAn, TenMonAn, DonGia, LoaiMonAn, TrangThai, SoLuongDaBan, HinhAnh FROM MonAn WHERE MaMonAn = ?";

        try (
            Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(rs.getInt("MaMonAn"));
                monAn.setTenMonAn(rs.getString("TenMonAn"));
                monAn.setDonGia(rs.getDouble("DonGia"));
                monAn.setLoaiMonAn(rs.getString("LoaiMonAn"));
                monAn.setTrangThai(rs.getString("TrangThai"));
                monAn.setSoLuongDaBan(rs.getInt("SoLuongDaBan"));
                monAn.setHinhAnh(rs.getString("HinhAnh"));
                return monAn;
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi tìm món ăn theo ID:");
            e.printStackTrace();
        }

        return null;
    }
}
