package com.example.servingwebcontent;

public class KhachHang extends NguoiDung {
    // userID, tenHienThi, email, password, role đã được kế thừa từ NguoiDung
    private String ngayThamGia; // Ngày khách hàng tham gia
    private int doanhSo;       // Tổng doanh số đã mua
    private int diem;          // Điểm tích lũy

    // Constructor mặc định
    public KhachHang() {
        super(); // Gọi constructor mặc định của lớp cha NguoiDung
        // Mặc định vai trò cho khách hàng
        this.setRole("khachhang");
    }

    // Constructor có tham số đầy đủ cho KhachHang
    public KhachHang(int userID, String tenKhachHang, String email, String password,
                     String ngayThamGia, int doanhSo, int diem) {
        // Gọi constructor của lớp cha NguoiDung để khởi tạo các thuộc tính tài khoản
        super(userID, tenKhachHang, email, password, "khachhang"); // Vai trò mặc định là "khachhang"
        this.ngayThamGia = ngayThamGia;
        this.doanhSo = doanhSo;
        this.diem = diem;
    }

    // Getters riêng của KhachHang
    // (Các getters từ NguoiDung như getUserID(), getEmail(), getTenHienThi() vẫn có thể dùng)
    public String getNgayThamGia() {
        return ngayThamGia;
    }

    public int getDoanhSo() {
        return doanhSo;
    }

    public int getDiem() {
        return diem;
    }

    // Setters riêng của KhachHang
    public void setNgayThamGia(String ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }

    public void setDoanhSo(int doanhSo) {
        this.doanhSo = doanhSo;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    
}