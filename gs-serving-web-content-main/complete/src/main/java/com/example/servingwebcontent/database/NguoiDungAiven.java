package com.example.servingwebcontent.database;

import java.sql.*;

import com.example.servingwebcontent.Model.*;

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

                    int userID = rs.getInt("UserID");
                    String role = rs.getString("VaiTro");

                    if ("Khach Hang".equalsIgnoreCase(role)) {
                        return getKhachHangByID(userID, conn, email, storedPassword);
                    } else if ("Nhan Vien".equalsIgnoreCase(role)) {
                        return getNhanVienByID(userID, conn, email, storedPassword);
                    } else {
                        System.out.println("⚠️ Vai trò không hợp lệ: " + role);
                    }
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
                kh.setHoTen(rs.getString("TenKH")); // ✅ sửa lại đúng cột
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
                nv.setHoTen(rs.getString("TenNV")); // ✅ sửa lại đúng cột
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
}