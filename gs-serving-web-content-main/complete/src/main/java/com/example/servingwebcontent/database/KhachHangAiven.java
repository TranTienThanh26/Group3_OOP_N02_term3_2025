package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.KhachHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangAiven {

    // ✅ Trả về MaKhachHang từ UserID
    public static int layMaKhachHangTheoUserID(int userId) {
        int maKH = -1;
        String query = "SELECT MaKhachHang FROM KhachHang WHERE UserID = ?";

        try (
            Connection conn = new myConnection().getConnection();
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

    public static boolean tonTaiKhachHang(int userId) {
        String query = "SELECT COUNT(*) FROM KhachHang WHERE UserID = ?";
        try (
            Connection conn = new myConnection().getConnection();
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

    public static String layTenKhachHang(int userId) {
        String ten = null;
        String query = "SELECT TenKH FROM KhachHang WHERE UserID = ?";
        try (
            Connection conn = new myConnection().getConnection();
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

    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> list = new ArrayList<>();
        String query = "SELECT kh.UserID, nd.Email, nd.MatKhau, nd.VaiTro, kh.TenKH, kh.NgayThamGia, kh.Diem " +
                       "FROM KhachHang kh JOIN NguoiDung nd ON kh.UserID = nd.UserID";

        try (
            Connection conn = new myConnection().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setUserID(rs.getInt("UserID"));
                kh.setTen(rs.getString("TenKH"));
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

    public void themKhachHang(KhachHang kh) {
       String queryNguoiDung = "INSERT INTO NguoiDung (UserID, Email, MatKhau, VaiTro) VALUES (?, ?, ?, 'Khach Hang')";
        String queryKhachHang = "INSERT INTO KhachHang (UserID, TenKH, NgayThamGia, Diem) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmtNguoiDung = conn.prepareStatement(queryNguoiDung);
            PreparedStatement stmtKhachHang = conn.prepareStatement(queryKhachHang)
        ) {
            stmtNguoiDung.setInt(1, kh.getUserID());
            stmtNguoiDung.setString(2, kh.getEmail());
            stmtNguoiDung.setString(3, kh.getPassword());
            stmtNguoiDung.executeUpdate();

            stmtKhachHang.setInt(1, kh.getUserID());
            stmtKhachHang.setString(2, kh.getTen());
            stmtKhachHang.setDate(3, Date.valueOf(kh.getNgayThamGia()));
            stmtKhachHang.setInt(4, kh.getDiem());
            int rows = stmtKhachHang.executeUpdate();

            System.out.println("✅ Đã thêm " + rows + " khách hàng.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi thêm KhachHang:");
            e.printStackTrace();
        }
    }

    public void capNhatKhachHang(KhachHang kh) {
        String queryNguoiDung = "UPDATE NguoiDung SET Email = ?, MatKhau = ? WHERE UserID = ?";
        String queryKhachHang = "UPDATE KhachHang SET TenKH = ?, NgayThamGia = ?, Diem = ? WHERE UserID = ?";

        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmtND = conn.prepareStatement(queryNguoiDung);
            PreparedStatement stmtKH = conn.prepareStatement(queryKhachHang)
        ) {
            stmtND.setString(1, kh.getEmail());
            stmtND.setString(2, kh.getPassword());
            stmtND.setInt(3, kh.getUserID());
            stmtND.executeUpdate();

            stmtKH.setString(1, kh.getTen());
            stmtKH.setDate(2, Date.valueOf(kh.getNgayThamGia()));
            stmtKH.setInt(3, kh.getDiem());
            stmtKH.setInt(4, kh.getUserID());
            stmtKH.executeUpdate();

            System.out.println("✅ Đã cập nhật thông tin khách hàng thành công.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật KhachHang:");
            e.printStackTrace();
        }
    }

    public void xoaKhachHang(int userID) {
        String queryKhachHang = "DELETE FROM KhachHang WHERE UserID = ?";
        String queryNguoiDung = "DELETE FROM NguoiDung WHERE UserID = ?";

        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmtKH = conn.prepareStatement(queryKhachHang);
            PreparedStatement stmtND = conn.prepareStatement(queryNguoiDung)
        ) {
            stmtKH.setInt(1, userID);
            stmtKH.executeUpdate();

            stmtND.setInt(1, userID);
            stmtND.executeUpdate();

            System.out.println("🗑️ Đã xoá khách hàng và người dùng liên quan.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xoá KhachHang:");
            e.printStackTrace();
        }
    }

    public List<KhachHang> timKiemKhachHang(String keyword) {
        List<KhachHang> list = new ArrayList<>();
        String query = "SELECT nd.UserID, nd.Email, nd.MatKhau, nd.VaiTro, kh.TenKH, kh.NgayThamGia, kh.Diem " +
                       "FROM KhachHang kh JOIN NguoiDung nd ON kh.UserID = nd.UserID " +
                       "WHERE kh.TenKH LIKE ? OR nd.Email LIKE ? OR CAST(nd.UserID AS CHAR) LIKE ?";

        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            String likeKeyword = "%" + keyword + "%";
            stmt.setString(1, likeKeyword);
            stmt.setString(2, likeKeyword);
            stmt.setString(3, likeKeyword);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setUserID(rs.getInt("UserID"));
                kh.setTen(rs.getString("TenKH"));
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

    public KhachHang timKhachTheoUserID(int userID) {
        String query = "SELECT nd.UserID, nd.Email, nd.MatKhau, nd.VaiTro, kh.TenKH, kh.NgayThamGia, kh.Diem " +
                       "FROM KhachHang kh JOIN NguoiDung nd ON kh.UserID = nd.UserID " +
                       "WHERE nd.UserID = ?";
        KhachHang kh = null;

        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                kh = new KhachHang();
                kh.setUserID(rs.getInt("UserID"));
                kh.setTen(rs.getString("TenKH"));
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
