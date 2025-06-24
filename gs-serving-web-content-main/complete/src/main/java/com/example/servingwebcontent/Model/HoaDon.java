package com.example.servingwebcontent.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HoaDon implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idHoaDon;
    private int idKH;
    private int idBan;
    private String ngayHD;
    private int tienMonAn;
    private int tongtien;
    private String trangthai;
    private List<CartItem> dsMonAn = new ArrayList<>();

    // ✅ Getter - Setter
    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public String getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(String ngayHD) {
        this.ngayHD = ngayHD;
    }

    public int getTienMonAn() {
        return tienMonAn;
    }

    public void setTienMonAn(int tienMonAn) {
        this.tienMonAn = tienMonAn;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public List<CartItem> getDsMonAn() {
        return dsMonAn;
    }

    public void setDsMonAn(List<CartItem> dsMonAn) {
        this.dsMonAn = dsMonAn;
    }

    // ✅ Constructor mặc định
    public HoaDon() {
    }

    // ✅ Constructor cơ bản
    public HoaDon(int idHoaDon, int idKH, int idBan, String ngayHD, int tienMonAn, int tongtien) {
        this.idHoaDon = idHoaDon;
        this.idKH = idKH;
        this.idBan = idBan;
        this.ngayHD = ngayHD;
        this.tienMonAn = tienMonAn;
        this.tongtien = tongtien;
    }

    // ✅ Constructor đơn giản
    public HoaDon(int idHoaDon, String trangthai) {
        this.idHoaDon = idHoaDon;
        this.trangthai = trangthai;
    }

    // ✅ Constructor đầy đủ có kiểm tra nhẹ và log
    public HoaDon(int idHoaDon, int idKH, int idBan, String ngayHD, int tienMonAn,
                  int tongtien, String trangthai) {

        this.idHoaDon = idHoaDon;
        this.idKH = idKH;
        this.idBan = idBan;
        this.ngayHD = ngayHD;
        this.tienMonAn = tienMonAn;
        this.tongtien = tongtien;
        this.trangthai = trangthai;

        // ⚠️ Log kiểm tra hợp lệ (không throw)
        if (idHoaDon <= 0) System.out.println("⚠️ [LOG] ID hóa đơn không hợp lệ (có thể là hóa đơn mới).");
        if (idKH <= 0) System.out.println("⚠️ [LOG] ID khách hàng không hợp lệ.");
        if (idBan <= 0) System.out.println("⚠️ [LOG] ID bàn không hợp lệ.");
        if (ngayHD == null || ngayHD.trim().isEmpty()) System.out.println("⚠️ [LOG] Ngày hóa đơn bị trống.");
        if (tienMonAn < 0) System.out.println("⚠️ [LOG] Tiền món ăn âm.");
        if (tongtien < 0) System.out.println("⚠️ [LOG] Tổng tiền âm.");
        if (trangthai == null || trangthai.trim().isEmpty()) System.out.println("⚠️ [LOG] Trạng thái rỗng.");

        System.out.println("✅ [LOG] Đã tạo hóa đơn mới: HD=" + idHoaDon + ", KH=" + idKH + ", Bàn=" + idBan);
    }
}
