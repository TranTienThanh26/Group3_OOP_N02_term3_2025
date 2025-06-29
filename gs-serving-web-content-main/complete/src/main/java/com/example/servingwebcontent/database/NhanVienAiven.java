package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienAiven {

    private Connection getConn() throws SQLException {
        return new myConnection().getConnection();
    }

    // ✅ Lấy danh sách tất cả nhân viên
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        String query = "SELECT Id_NV, TenNV, NgayVL, Sdt, ChucVu, Id_NQL, TinhTrang FROM NhanVien";

        try (
            Connection conn = getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId_NhanVien(rs.getInt("Id_NV"));
                nv.setHoTen(rs.getString("TenNV"));
                nv.setNgayVL(rs.getDate("NgayVL").toString());
                nv.setSdt(rs.getString("Sdt"));
                nv.setChucvu(rs.getString("ChucVu"));
                nv.setId_NQL(rs.getInt("Id_NQL"));
                nv.setTinhTrang(rs.getString("TinhTrang"));
                list.add(nv);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn NhanVien:");
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Thêm nhân viên mới
    public void themNhanVien(NhanVien nv) {
        String query = "INSERT INTO NhanVien (TenNV, NgayVL, Sdt, ChucVu, Id_NQL, TinhTrang) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, nv.getHoTen());
            stmt.setDate(2, Date.valueOf(nv.getNgayVL()));
            stmt.setString(3, nv.getSdt());
            stmt.setString(4, nv.getChucvu());
            stmt.setObject(5, nv.getId_NQL(), Types.INTEGER);
            stmt.setString(6, nv.getTinhTrang());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã thêm " + rows + " nhân viên.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi thêm NhanVien:");
            e.printStackTrace();
        }
    }

    // ✅ Cập nhật thông tin nhân viên
    public void capNhatNhanVien(NhanVien nv) {
        String query = "UPDATE NhanVien SET TenNV = ?, NgayVL = ?, Sdt = ?, ChucVu = ?, Id_NQL = ?, TinhTrang = ? WHERE Id_NV = ?";

        try (
            Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, nv.getHoTen());
            stmt.setDate(2, Date.valueOf(nv.getNgayVL()));
            stmt.setString(3, nv.getSdt());
            stmt.setString(4, nv.getChucvu());
            stmt.setObject(5, nv.getId_NQL(), Types.INTEGER);
            stmt.setString(6, nv.getTinhTrang());
            stmt.setInt(7, nv.getId_NhanVien());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã cập nhật " + rows + " nhân viên.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật NhanVien:");
            e.printStackTrace();
        }
    }

    // ✅ Xoá nhân viên theo ID
    public void xoaNhanVien(int id) {
        String query = "DELETE FROM NhanVien WHERE Id_NV = ?";

        try (
            Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("🗑️ Đã xoá " + rows + " nhân viên.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xoá NhanVien:");
            e.printStackTrace();
        }
    }

    // ✅ Tìm kiếm nhân viên theo tên
    public List<NhanVien> timKiemNhanVien(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        String query = "SELECT Id_NV, TenNV, NgayVL, Sdt, ChucVu, Id_NQL, TinhTrang FROM NhanVien WHERE TenNV LIKE ?";

        try (
            Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId_NhanVien(rs.getInt("Id_NV"));
                nv.setHoTen(rs.getString("TenNV"));
                nv.setNgayVL(rs.getDate("NgayVL").toString());
                nv.setSdt(rs.getString("Sdt"));
                nv.setChucvu(rs.getString("ChucVu"));
                nv.setId_NQL(rs.getInt("Id_NQL"));
                nv.setTinhTrang(rs.getString("TinhTrang"));
                list.add(nv);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi tìm kiếm NhanVien:");
            e.printStackTrace();
        }

        return list;
    }
}
