package com.example.servingwebcontent.database;

import java.sql.*;

import com.example.servingwebcontent.Model.*;

public class NguoiDungAiven {

    private Connection getConn() throws SQLException {
        return new myConnection().getConnection();
    }

    public NguoiDung login(String email, String matKhau) {
        String sql = "SELECT * FROM NguoiDung WHERE Email = ? AND TrangThai = 'Active'";

        try (
            Connection conn = getConn();
            PreparedStatement pst = conn.prepareStatement(sql)
        ) {
            pst.setString(1, email.trim());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("MatKhau");

                if (matKhau.trim().equals(storedPassword)) {
                    int userID = rs.getInt("UserID");
                    String role = rs.getString("VaiTro");

                    if ("Khach Hang".equalsIgnoreCase(role)) {
                        return getKhachHangByID(userID, conn, email, storedPassword);
                    } else if ("Nhan Vien".equalsIgnoreCase(role)) {
                        return getNhanVienByID(userID, conn, email, storedPassword);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn MySQL:");
            e.printStackTrace();
        }

        return null;
    }

    private KhachHang getKhachHangByID(int userID, Connection conn, String email, String password) throws SQLException {
        String query = "SELECT * FROM KhachHang WHERE UserID = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, userID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setUserID(userID);
                kh.setEmail(email);
                kh.setPassword(password);
                kh.setRole("Khach Hang");
                kh.setHoTen(rs.getString("TenKH"));
                kh.setNgayThamGia(rs.getString("NgayThamGia"));
                kh.setDoanhSo(rs.getInt("DoanhSo"));
                kh.setDiem(rs.getInt("Diem"));
                return kh;
            }
        }
        return null;
    }

    private NhanVien getNhanVienByID(int userID, Connection conn, String email, String password) throws SQLException {
        String query = "SELECT * FROM NhanVien WHERE UserID = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, userID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setUserID(userID);
                nv.setEmail(email);
                nv.setPassword(password);
                nv.setRole("Nhan Vien");
                nv.setHoTen(rs.getString("TenNV"));
                nv.setId_NhanVien(rs.getInt("Id_NV"));
                nv.setNgayVL(rs.getString("NgayVL"));
                nv.setSdt(rs.getString("Sdt"));
                nv.setChucvu(rs.getString("ChucVu"));
                nv.setId_NQL(rs.getInt("Id_NQL"));
                nv.setTinhTrang(rs.getString("TinhTrang"));
                return nv;
            }
        }
        return null;
    }

    // ✅ Cập nhật mật khẩu
    public boolean capNhatMatKhau(int userId, String matKhauMoi) {
        String sql = "UPDATE NguoiDung SET MatKhau = ? WHERE UserID = ?";
        try (
            Connection conn = getConn();
            PreparedStatement pst = conn.prepareStatement(sql)
        ) {
            pst.setString(1, matKhauMoi.trim());
            pst.setInt(2, userId);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật mật khẩu:");
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Cập nhật email
    public boolean capNhatEmail(int userId, String emailMoi) {
        String sql = "UPDATE NguoiDung SET Email = ? WHERE UserID = ?";
        try (
            Connection conn = getConn();
            PreparedStatement pst = conn.prepareStatement(sql)
        ) {
            pst.setString(1, emailMoi.trim());
            pst.setInt(2, userId);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật email:");
            e.printStackTrace();
            return false;
        }
    }
}
