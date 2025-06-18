package com.example.servingwebcontent;

public class CTHD {

    private int ID_HD;
    private int ID_MonAn;
    private String tenMonAn;
    private int soluong;
    private int thanhTien;

    public int getID_HD() {
        return ID_HD;
    }

    public int getID_MonAn() {
        return ID_MonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public int getSoluong() {
        return soluong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public CTHD() {
    }

    public CTHD(int ID_HD, int ID_MonAn, String tenMonAn, int soluong, int thanhTien) {
        this.ID_HD = ID_HD;
        this.ID_MonAn = ID_MonAn;
        this.tenMonAn = tenMonAn;
        this.soluong = soluong;
        this.thanhTien = thanhTien;
    }

    // ✔ Setter có try-catch-finally
    public void setSoluong(int soluong) {
        try {
            this.soluong = soluong;
        } catch (Exception e) {
            System.err.println("Lỗi khi set số lượng: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi setSoluong.");
        }
    }

    // ✔ Các setter còn lại
    public void setID_HD(int ID_HD) {
        this.ID_HD = ID_HD;
    }

    public void setID_MonAn(int ID_MonAn) {
        this.ID_MonAn = ID_MonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
