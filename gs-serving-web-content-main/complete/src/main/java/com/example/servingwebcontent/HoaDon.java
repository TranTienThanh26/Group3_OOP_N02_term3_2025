package com.example.servingwebcontent;

public class HoaDon {

    private int idHoaDon;
    private int idKH;
    private int idBan;
    private String ngayHD;
    private int tienMonAn;
    private int tongtien;
    private String trangthai;

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

    public HoaDon(int idHoaDon, String trangthai) {
        this.idHoaDon = idHoaDon;
        this.trangthai = trangthai;
    }

    // ✅ Constructor chính có bắt lỗi và kiểm tra hợp lệ
    public HoaDon(int idHoaDon, int idKH, int idBan, String ngayHD, int tienMonAn,
                  int tongtien, String trangthai) {
        try {
            if (idHoaDon <= 0) throw new IllegalArgumentException("ID hóa đơn phải lớn hơn 0.");
            if (idKH <= 0) throw new IllegalArgumentException("ID khách hàng phải lớn hơn 0.");
            if (idBan <= 0) throw new IllegalArgumentException("ID bàn phải khác không.");
            if (ngayHD == null || ngayHD.trim().isEmpty()) throw new IllegalArgumentException("Ngày hóa đơn không được trống.");
            if (tienMonAn < 0) throw new IllegalArgumentException("Tiền món ăn không được âm.");
            if (tongtien < 0) throw new IllegalArgumentException("Tổng tiền không được âm.");
            if (trangthai == null || trangthai.trim().isEmpty()) throw new IllegalArgumentException("Trạng thái không hợp lệ.");

            this.idHoaDon = idHoaDon;
            this.idKH = idKH;
            this.idBan = idBan;
            this.ngayHD = ngayHD;
            this.tienMonAn = tienMonAn;
            this.tongtien = tongtien;
            this.trangthai = trangthai;

        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi tạo hóa đơn: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo đối tượng HoaDon (đầy đủ) hoàn tất.");
        }
    }
}
