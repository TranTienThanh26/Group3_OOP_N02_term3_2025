package com.example.servingwebcontent;

public class HoaDon {

    private int idHoaDon;
    private int idKH;
    private int idBan;
    private String ngayHD;
    private int tienMonAn;
    private int tongtien;
    private String trangthai;

    public HoaDon() {
    }

    public HoaDon(int idHoaDon, int idKH, int idBan, String ngayHD, int tienMonAn, int tongtien) {
        this.idHoaDon = idHoaDon;
        this.idKH = idKH;
        this.idBan = idBan;
        this.ngayHD = ngayHD;
        this.tienMonAn = tienMonAn;
        this.tongtien = tongtien;
    }

    public HoaDon(int idHoaDon, int idKH, int idBan, String ngayHD, int tienMonAn, int tongtien, String trangthai) {
        this.idHoaDon = idHoaDon;
        this.idKH = idKH;
        this.idBan = idBan;
        this.ngayHD = ngayHD;
        this.tienMonAn = tienMonAn;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public HoaDon(int idHoaDon, String trangthai) {
        this.idHoaDon = idHoaDon;
        this.trangthai = trangthai;
    }

    // Getter - Setter

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

    // ❗ Setter này có try-catch-finally
    public void setTrangthai(String trangthai) {
        try {
            this.trangthai = trangthai;
        } catch (Exception e) {
            System.err.println("Lỗi khi set trạng thái: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi setTrangthai.");
        }
    }
}
