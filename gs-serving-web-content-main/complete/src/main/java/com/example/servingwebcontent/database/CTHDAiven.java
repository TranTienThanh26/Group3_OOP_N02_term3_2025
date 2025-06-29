package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.CTHD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CTHDAiven {

    // ✅ Hàm thêm chi tiết hóa đơn
    public void insertCTHD(CTHD cthd) {
        String sql = "INSERT INTO CTHD (ID_HD, ID_MonAn, TenMonAn, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?, ?)";
        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, cthd.getID_HD());
            stmt.setInt(2, cthd.getID_MonAn());
            stmt.setString(3, cthd.getTenMonAn());
            stmt.setInt(4, cthd.getSoluong());
            stmt.setInt(5, cthd.getDonGia());
            stmt.setInt(6, cthd.getThanhTien());
            stmt.executeUpdate();
            System.out.println("✅ Đã lưu chi tiết hóa đơn.");
        } catch (Exception e) {
            System.err.println("❌ Lỗi lưu chi tiết hóa đơn:");
            e.printStackTrace();
        }
    }

    // ✅ Hàm lấy danh sách món ăn theo ID hóa đơn
    public List<CTHD> getDsMonAnTheoHoaDon(int idHoaDon) {
        List<CTHD> list = new ArrayList<>();
        String query = "SELECT * FROM CTHD WHERE ID_HD = ?";

        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, idHoaDon);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CTHD cthd = new CTHD(
                    rs.getInt("ID_HD"),
                    rs.getInt("ID_MonAn"),
                    rs.getString("TenMonAn"),
                    rs.getInt("SoLuong"),
                    rs.getInt("DonGia"),
                    rs.getInt("ThanhTien")
                );
                list.add(cthd);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy CTHD theo ID_HD: " + idHoaDon);
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Alias cho controller dùng (fix lỗi getCTHDByHoaDonID không tồn tại)
    public List<CTHD> getCTHDByHoaDonID(int idHoaDon) {
        return getDsMonAnTheoHoaDon(idHoaDon);
    }

    // ✅ Hàm xoá chi tiết hóa đơn theo ID_HD
    public void deleteCTHDTheoHoaDon(int idHoaDon) {
        String sql = "DELETE FROM CTHD WHERE ID_HD = ?";
        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idHoaDon);
            int rows = stmt.executeUpdate();
            System.out.println("🗑️ Đã xoá " + rows + " chi tiết hóa đơn cho ID_HD = " + idHoaDon);
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xoá chi tiết hóa đơn theo ID_HD:");
            e.printStackTrace();
        }
    }
}
