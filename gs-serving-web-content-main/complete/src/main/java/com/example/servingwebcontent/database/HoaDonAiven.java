package com.example.servingwebcontent.database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.Model.HoaDon;

public class HoaDonAiven {

    private static final String JDBC_URL =
        "jdbc:mysql://mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_HNm9Mr2leXuYSrqITaj";

    // ✅ Lấy danh sách tất cả hóa đơn
    public List<HoaDon> getDanhSachHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        String query = "SELECT * FROM HoaDon";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                HoaDon hd = taoHoaDonTuResultSet(rs);
                list.add(hd);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn HoaDon từ Aiven:");
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Lấy danh sách hóa đơn theo khách hàng
    public List<HoaDon> getHoaDonTheoKhachHang(int idKH) {
        List<HoaDon> list = new ArrayList<>();
        String query = "SELECT * FROM HoaDon WHERE IDKH = ? ORDER BY NgayHD DESC";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, idKH);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoaDon hd = taoHoaDonTuResultSet(rs);
                list.add(hd);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi truy vấn HoaDon theo IDKH:");
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Thêm hóa đơn mới và trả về ID được tạo
    public int themHoaDon(HoaDon hoaDon) {
        String query = "INSERT INTO HoaDon (IDKH, IDBan, NgayHD, TienMonAn, TongTien, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, hoaDon.getIdKH());
            stmt.setInt(2, hoaDon.getIdBan());
            stmt.setString(3, hoaDon.getNgayHD());
            stmt.setInt(4, hoaDon.getTienMonAn());
            stmt.setInt(5, hoaDon.getTongtien());
            stmt.setString(6, hoaDon.getTrangthai());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int idMoi = rs.getInt(1);
                    hoaDon.setIdHoaDon(idMoi);
                    System.out.println("✅ Đã thêm hóa đơn với ID: " + idMoi);
                    return idMoi;
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi thêm HoaDon:");
            e.printStackTrace();
        }

        return -1;
    }

    // ✅ Cập nhật hóa đơn
    public void capNhatHoaDon(HoaDon hoaDon) {
        String query = "UPDATE HoaDon SET IDKH = ?, IDBan = ?, NgayHD = ?, TienMonAn = ?, TongTien = ?, TrangThai = ? WHERE IDHoaDon = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, hoaDon.getIdKH());
            stmt.setInt(2, hoaDon.getIdBan());
            stmt.setString(3, hoaDon.getNgayHD());
            stmt.setInt(4, hoaDon.getTienMonAn());
            stmt.setInt(5, hoaDon.getTongtien());
            stmt.setString(6, hoaDon.getTrangthai());
            stmt.setInt(7, hoaDon.getIdHoaDon());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã cập nhật " + rows + " hóa đơn.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật HoaDon:");
            e.printStackTrace();
        }
    }

    // ✅ Xoá chi tiết hóa đơn theo ID hóa đơn
    public void xoaChiTietHoaDonTheoIdHD(int idHD) {
        String sql = "DELETE FROM CTHD WHERE ID_HD = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idHD);
            int rows = stmt.executeUpdate();
            System.out.println("🗑️ Đã xoá " + rows + " chi tiết hóa đơn.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xoá chi tiết hóa đơn:");
            e.printStackTrace();
        }
    }

    // ✅ Xoá hóa đơn theo ID (xoá cả CTHD trước)
    public void xoaHoaDon(int idHoaDon) {
        xoaChiTietHoaDonTheoIdHD(idHoaDon);

        String query = "DELETE FROM HoaDon WHERE IDHoaDon = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, idHoaDon);
            int rows = stmt.executeUpdate();
            System.out.println("🗑️ Đã xoá " + rows + " hóa đơn.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xoá HoaDon:");
            e.printStackTrace();
        }
    }

    // ✅ Hàm hỗ trợ: Tạo đối tượng HoaDon từ ResultSet (format ngayHD)
    private HoaDon taoHoaDonTuResultSet(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setIdHoaDon(rs.getInt("IDHoaDon"));
        hd.setIdKH(rs.getInt("IDKH"));
        hd.setIdBan(rs.getInt("IDBan"));

        // ✅ Format Timestamp -> String "yyyy-MM-dd HH:mm:ss"
        Timestamp timestamp = rs.getTimestamp("NgayHD");
        String ngayHD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        hd.setNgayHD(ngayHD);

        hd.setTienMonAn(rs.getInt("TienMonAn"));
        hd.setTongtien(rs.getInt("TongTien"));
        hd.setTrangthai(rs.getString("TrangThai"));
        return hd;
    }

    // ✅ Lấy danh sách hóa đơn không trùng lặp
    public List<HoaDon> getDanhSachHoaDonKhongTrung() {
        List<HoaDon> list = new ArrayList<>();
        String query = "SELECT DISTINCT IDHoaDon, IDKH, IDBan, NgayHD, TienMonAn, TongTien, TrangThai " +
                       "FROM HoaDon ORDER BY NgayHD DESC";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                HoaDon hd = taoHoaDonTuResultSet(rs);
                list.add(hd);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy danh sách hóa đơn không trùng:");
            e.printStackTrace();
        }

        return list;
    }
    public HoaDon timHoaDonTheoId(int id) {
        String sql = "SELECT * FROM HoaDon WHERE IDHoaDon = ?";
        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHoaDon(rs.getInt("IDHoaDon"));
                hd.setIdKH(rs.getInt("IDKH"));
                hd.setIdBan(rs.getInt("IDBan"));
                hd.setNgayHD(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("NgayHD")));
                hd.setTienMonAn(rs.getInt("TienMonAn"));
                hd.setTongtien(rs.getInt("TongTien"));
                hd.setTrangthai(rs.getString("TrangThai"));
                return hd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    
}