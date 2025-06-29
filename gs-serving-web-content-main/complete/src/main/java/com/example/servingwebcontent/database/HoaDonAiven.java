package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.CartItem;
import com.example.servingwebcontent.Model.HoaDon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonAiven {

    public List<HoaDon> getDanhSachHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        String query = "SELECT * FROM HoaDon";

        try (
            Connection conn = new myConnection().getConnection();
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

    public List<HoaDon> getHoaDonTheoKhachHang(int idKH) {
        List<HoaDon> list = new ArrayList<>();
        String query = "SELECT * FROM HoaDon WHERE IDKH = ? ORDER BY NgayHD DESC";

        try (
            Connection conn = new myConnection().getConnection();
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

    public int themHoaDon(HoaDon hoaDon) {
        String query = "INSERT INTO HoaDon (IDKH, IDBan, NgayHD, TienMonAn, TongTien, TrangThai, dsMonAn) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, hoaDon.getIdKH());
            stmt.setInt(2, hoaDon.getIdBan());
            stmt.setString(3, hoaDon.getNgayHD());
            stmt.setInt(4, hoaDon.getTienMonAn());
            stmt.setInt(5, hoaDon.getTongtien());
            stmt.setString(6, hoaDon.getTrangthai());
    
            String dsMonAnJson = new ObjectMapper().writeValueAsString(hoaDon.getDsMonAn());
            stmt.setString(7, dsMonAnJson);
    
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int idMoi = rs.getInt(1);
                    hoaDon.setIdHoaDon(idMoi);
                    System.out.println("✅ [LOG] Đã tạo hóa đơn mới: HD=" + hoaDon.getIdHoaDon() + ", KH=" + hoaDon.getIdKH() + ", Bàn=" + hoaDon.getIdBan());
                    System.out.println("✅ Đã thêm hóa đơn với ID: " + idMoi);
                    return idMoi;
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi thêm HoaDon:");
            e.printStackTrace();
        }
    
        return -1;
    }
    

    public void capNhatHoaDon(HoaDon hoaDon) {
        String query = "UPDATE HoaDon SET IDKH = ?, IDBan = ?, NgayHD = ?, TienMonAn = ?, TongTien = ?, TrangThai = ?, dsMonAn = ? WHERE IDHoaDon = ?";

        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, hoaDon.getIdKH());
            stmt.setInt(2, hoaDon.getIdBan());
            stmt.setString(3, hoaDon.getNgayHD());
            stmt.setInt(4, hoaDon.getTienMonAn());
            stmt.setInt(5, hoaDon.getTongtien());
            stmt.setString(6, hoaDon.getTrangthai());

            String dsMonAnJson = new ObjectMapper().writeValueAsString(hoaDon.getDsMonAn());
            stmt.setString(7, dsMonAnJson);

            stmt.setInt(8, hoaDon.getIdHoaDon());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Đã cập nhật " + rows + " hóa đơn.");
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi cập nhật HoaDon:");
            e.printStackTrace();
        }
    }

    public void xoaChiTietHoaDonTheoIdHD(int idHD) {
        String sql = "DELETE FROM CTHD WHERE ID_HD = ?";
        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idHD);
            int rows = stmt.executeUpdate();
            System.out.println("🗑️ Đã xoá " + rows + " chi tiết hóa đơn.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xoá chi tiết hóa đơn:");
            e.printStackTrace();
        }
    }

    public void xoaHoaDon(int idHoaDon) {
        xoaChiTietHoaDonTheoIdHD(idHoaDon);

        String query = "DELETE FROM HoaDon WHERE IDHoaDon = ?";

        try (
            Connection conn = new myConnection().getConnection();
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

    private HoaDon taoHoaDonTuResultSet(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setIdHoaDon(rs.getInt("IDHoaDon"));
        hd.setIdKH(rs.getInt("IDKH"));
        hd.setIdBan(rs.getInt("IDBan"));

        Timestamp timestamp = rs.getTimestamp("NgayHD");
        String ngayHD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        hd.setNgayHD(ngayHD);

        hd.setTienMonAn(rs.getInt("TienMonAn"));
        hd.setTongtien(rs.getInt("TongTien"));
        hd.setTrangthai(rs.getString("TrangThai"));

        String dsMonAnStr = rs.getString("dsMonAn");
        if (dsMonAnStr != null && !dsMonAnStr.isEmpty()) {
            try {
                List<CartItem> ds = new ObjectMapper().readValue(
                    dsMonAnStr,
                    new TypeReference<List<CartItem>>() {}
                );
                hd.setDsMonAn(ds);
            } catch (Exception e) {
                System.err.println("❌ Lỗi khi đọc dsMonAn từ JSON:");
                e.printStackTrace();
            }
        }

        return hd;
    }

    public List<HoaDon> getDanhSachHoaDonKhongTrung() {
        List<HoaDon> list = new ArrayList<>();
        String query = "SELECT DISTINCT IDHoaDon, IDKH, IDBan, NgayHD, TienMonAn, TongTien, TrangThai, dsMonAn FROM HoaDon ORDER BY NgayHD DESC";

        try (
            Connection conn = new myConnection().getConnection();
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
            Connection conn = new myConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return taoHoaDonTuResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
