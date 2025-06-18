package com.example.servingwebcontent;

public class KHDH {
    private String maKhachHang;
    private String maMonAn;
    private String ten;
    private boolean isPaid;

    // Constructor có bắt lỗi
    public KHDH(String maKhachHang, String maMonAn, String ten, boolean isPaid) {
        try {
            if (maKhachHang == null || maKhachHang.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã khách hàng không được để trống.");
            }
            if (maMonAn == null || maMonAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã món ăn không được để trống.");
            }
            if (ten == null || ten.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên không được để trống.");
            }

            this.maKhachHang = maKhachHang;
            this.maMonAn = maMonAn;
            this.ten = ten;
            this.isPaid = isPaid;

        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi khởi tạo KHDH: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo KHDH hoàn tất.");
        }
    }

    // Getters
    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getMaMonAn() {
        return maMonAn;
    }

    public String getTen() {
        return ten;
    }

    public boolean isPaid() {
        return isPaid;
    }

    // Setters
    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setMaMonAn(String maMonAn) {
        this.maMonAn = maMonAn;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    // Hiển thị thông tin
    public void displayKHDH() {
        System.out.println("User ID: " + maKhachHang);
        System.out.println("MonAn ID: " + maMonAn);
        System.out.println("Tên món ăn: " + ten);
        System.out.println("Đã thanh toán: " + (isPaid ? "✔" : "✘"));
    }
}
