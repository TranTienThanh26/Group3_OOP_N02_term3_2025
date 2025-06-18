package com.example.servingwebcontent;

public class KhachHang extends NguoiDung {
    // userID, tenHienThi, email, password, role đã được kế thừa từ NguoiDung
    private String ngayThamGia; // Ngày khách hàng tham gia
    private int doanhSo;        // Tổng doanh số đã mua
    private int diem;           // Điểm tích lũy

    // Constructor mặc định
    public KhachHang() {
        super(); // Gọi constructor mặc định của lớp cha NguoiDung
        this.setRole("khachhang");
    }

    // Constructor có tham số đầy đủ cho KhachHang
    public KhachHang(int userID, String tenKhachHang, String email, String password,
                     String ngayThamGia, int doanhSo, int diem) {
        super(userID, tenKhachHang, email, password, "khachhang");
        this.ngayThamGia = ngayThamGia;
        this.doanhSo = doanhSo;
        this.diem = diem;
    }

    // Getters
    public String getNgayThamGia() {
        return ngayThamGia;
    }

    public int getDoanhSo() {
        return doanhSo;
    }

    public int getDiem() {
        return diem;
    }

    // Setters
    public void setNgayThamGia(String ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }

    public void setDoanhSo(int doanhSo) {
        this.doanhSo = doanhSo;
    }

    // ❗Chỉ setter này có try-catch-finally
    public void setDiem(int diem) {
        try {
            this.diem = diem;
        } catch (Exception e) {
            System.err.println("Lỗi khi set điểm tích lũy: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi setDiem.");
        }
    }
}
