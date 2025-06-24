package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.KhachHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangAiven {

    private static final String JDBC_URL =
        "jdbc:mysql://mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_HNm9Mr2leXuYSrqITaj";

    // ===========================
    // 🔍 Tiện ích tra cứu đơn giản
    // ===========================

    // ✅ Trả về MaKhachHang từ UserID
    public static int layMaKhachHangTheoUserID(int userId) {
        int maKH = -1;
        String query = "SELECT MaKhachHang FROM KhachHang WHERE UserID = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    maKH = rs.getInt("MaKhachHang");
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn MaKhachHang: " + e.getMessage());
        }

        return maKH;
    }

    // ✅ Kiểm tra tồn tại UserID trong bảng KhachHang
    public static boolean tonTaiKhachHang(int userId) {
        String query = "SELECT COUNT(*) FROM KhachHang WHERE UserID = ?";
        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi kiểm tra tồn tại khách hàng: " + e.getMessage());
        }
        return false;
    }

    // ✅ Lấy tên khách hàng theo UserID
    public static String layTenKhachHang(int userId) {
        String ten = null;
        String query = "SELECT TenKH FROM KhachHang WHERE UserID = ?";
        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ten = rs.getString("TenKH");
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy tên khách hàng: " + e.getMessage());
        }
        return ten;
    }

    // ===========================
    // 📦 CRUD Khách Hàng
    // ===========================

    // ✅ Lấy danh sách tất cả khách hàng
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> list = new ArrayList<>();
        String query = "SELECT UserID, TenKH, Email, MatKhau, NgayThamGia, Diem FROM KhachHang";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setUserID(rs.getInt("UserID"));
                kh.setHoTen(rs.getString("TenKH"));
                kh.setEmail(rs.getString("Email"));
                kh.setPassword(rs.getString("MatKhau"));
                kh.setNgayThamGia(rs.getDate("NgayThamGia").toString());
                kh.setDiem(rs.getInt("Diem"));
                list.add(kh);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn KhachHang:");
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Thêm khách hàng mới
    public void themKhachHang(KhachHang kh) {
        String query = "INSERT INTO KhachHang (UserID, TenKH, Email, MatKhau, NgayThamGia, Diem) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, kh.getUserID());
            stmt.setString(2, kh.getHoTen());
            stmt.setString(3, kh.getEmail());
            stmt.setString(4, kh.getPassword());
            stmt.setDate(5, Date.valueOf(kh.getNgayThamGia()));
            stmt.setInt(6, kh.getDiem());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã thêm " + rows + " khách hàng.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi thêm KhachHang:");
            e.printStackTrace();
        }
    }

    // ✅ Cập nhật thông tin khách hàng
    public void capNhatKhachHang(KhachHang kh) {
        String query = "UPDATE KhachHang SET TenKH = ?, Email = ?, MatKhau = ?, NgayThamGia = ?, Diem = ? WHERE UserID = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, kh.getHoTen());
            stmt.setString(2, kh.getEmail());
            stmt.setString(3, kh.getPassword());
            stmt.setDate(4, Date.valueOf(kh.getNgayThamGia()));
            stmt.setInt(5, kh.getDiem());
            stmt.setInt(6, kh.getUserID());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã cập nhật " + rows + " khách hàng.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật KhachHang:");
            e.printStackTrace();
        }
    }

    // ✅ Xoá khách hàng theo ID
    public void xoaKhachHang(int userID) {
        String query = "DELETE FROM KhachHang WHERE UserID = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, userID);
            int rows = stmt.executeUpdate();
            System.out.println("🗑️ Đã xoá " + rows + " khách hàng.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xoá KhachHang:");
            e.printStackTrace();
        }
    }

    // ✅ Tìm kiếm khách hàng theo tên
    public List<KhachHang> timKiemKhachHang(String keyword) {
        List<KhachHang> list = new ArrayList<>();
        String query = "SELECT UserID, TenKH, Email, MatKhau, NgayThamGia, Diem FROM KhachHang WHERE TenKH LIKE ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setUserID(rs.getInt("UserID"));
                kh.setHoTen(rs.getString("TenKH"));
                kh.setEmail(rs.getString("Email"));
                kh.setPassword(rs.getString("MatKhau"));
                kh.setNgayThamGia(rs.getDate("NgayThamGia").toString());
                kh.setDiem(rs.getInt("Diem"));
                list.add(kh);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi tìm kiếm KhachHang:");
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Tìm khách hàng theo UserID
    public KhachHang timKhachTheoUserID(int userID) {
        String query = "SELECT UserID, TenKH, Email, MatKhau, NgayThamGia, Diem FROM KhachHang WHERE UserID = ?";
        KhachHang kh = null;

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                kh = new KhachHang();
                kh.setUserID(rs.getInt("UserID"));
                kh.setHoTen(rs.getString("TenKH"));
                kh.setEmail(rs.getString("Email"));
                kh.setPassword(rs.getString("MatKhau"));
                kh.setNgayThamGia(rs.getDate("NgayThamGia").toString());
                kh.setDiem(rs.getInt("Diem"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi tìm khách theo ID:");
            e.printStackTrace();
        }

        return kh;
    }
}
