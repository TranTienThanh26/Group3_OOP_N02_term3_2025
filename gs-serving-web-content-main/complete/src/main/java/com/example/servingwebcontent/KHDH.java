package com.example.servingwebcontent;

public class KHDH {
    public String maKhachHang;
    public String maMonAn;
    public String ten;
    public boolean isPaid;

    public KHDH(String maKhachHang, String maMonAn, String ten, boolean isPaid) {
        this.maKhachHang = maKhachHang;
        this.maMonAn = maMonAn;
        this.ten = ten;
        this.isPaid = isPaid;
    }

    // ❗Chỉ phương thức này có try-catch-finally
    public void displayKHDH() {
        try {
            System.out.println("User ID: " + maKhachHang);
            System.out.println("MonAn ID: " + maMonAn);
            System.out.println("Ten: " + ten);
            System.out.println("Đã thanh toán: " + (isPaid ? "Có" : "Chưa"));
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị thông tin KHDH: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi displayKHDH.");
        }
    }
}
