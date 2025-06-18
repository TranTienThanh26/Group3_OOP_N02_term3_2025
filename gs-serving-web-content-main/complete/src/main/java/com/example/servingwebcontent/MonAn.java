package com.example.servingwebcontent;

public class MonAn {
    private int maMonAn;
    private String tenMonAn;
    private double donGia;
    private String loaiMonAn;
    private String trangThai;
    private int soLuongDaBan;

    public MonAn() {
        this.soLuongDaBan = 0;
    }

    public MonAn(int maMonAn, String tenMonAn, double donGia, String loaiMonAn) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
        this.loaiMonAn = loaiMonAn;
        this.trangThai = "Dang kinh doanh";
        this.soLuongDaBan = 0;
    }

    public MonAn(int maMonAn, String tenMonAn, double donGia, String loaiMonAn, String trangThai) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
        this.loaiMonAn = loaiMonAn;
        this.trangThai = trangThai;
        this.soLuongDaBan = 0;
    }

    public MonAn(int maMonAn, String tenMonAn, double donGia, String loaiMonAn, String trangThai, int soLuongDaBan) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
        this.loaiMonAn = loaiMonAn;
        this.trangThai = trangThai;
        this.soLuongDaBan = soLuongDaBan;
    }

    // Getters
    public int getMaMonAn() { return maMonAn; }
    public String getTenMonAn() { return tenMonAn; }
    public double getDonGia() { return donGia; }
    public String getLoaiMonAn() { return loaiMonAn; }
    public String getTrangThai() { return trangThai; }
    public int getSoLuongDaBan() { return soLuongDaBan; }

    // Setters
    public void setMaMonAn(int maMonAn) { this.maMonAn = maMonAn; }
    public void setTenMonAn(String tenMonAn) { this.tenMonAn = tenMonAn; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public void setLoaiMonAn(String loaiMonAn) { this.loaiMonAn = loaiMonAn; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public void setSoLuongDaBan(int soLuongDaBan) { this.soLuongDaBan = soLuongDaBan; }

    // ❗ Phương thức tiện ích có try-catch-finally
    public void tangSoLuongDaBan(int soLuongThem) {
        try {
            this.soLuongDaBan += soLuongThem;
        } catch (Exception e) {
            System.err.println("Lỗi khi tăng số lượng đã bán: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi tangSoLuongDaBan.");
        }
    }
}
