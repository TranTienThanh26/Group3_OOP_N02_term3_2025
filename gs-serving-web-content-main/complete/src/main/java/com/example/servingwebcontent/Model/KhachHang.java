package com.example.servingwebcontent.Model;

public class KhachHang extends NguoiDung {
    // userID, tenHienThi, email, password, role đã được kế thừa từ NguoiDung
    private String ngayThamGia; // Ngày khách hàng tham gia
    private int doanhSo;       // Tổng doanh số đã mua
    private int diem;          // Điểm tích lũy

    // Constructor mặc định
    public KhachHang() {
        super(); // Gọi constructor mặc định của lớp cha NguoiDung
        this.setRole("khachhang");
    }

    // ✅ Constructor hợp lệ duy nhất chứa xử lý lỗi
    public KhachHang(int userID, String tenKhachHang, String email, String password,
                     String ngayThamGia, int doanhSo, int diem) {
        super(); // gọi trước để tránh lỗi constructor chain

        try {
            if (userID <= 0) throw new IllegalArgumentException("User ID phải lớn hơn 0.");
            if (tenKhachHang == null || tenKhachHang.trim().isEmpty())
                throw new IllegalArgumentException("Tên khách hàng không được để trống.");
            if (email == null || !email.contains("@"))
                throw new IllegalArgumentException("Email không hợp lệ.");
            if (password == null || password.length() < 4)
                throw new IllegalArgumentException("Mật khẩu phải có ít nhất 4 ký tự.");
            if (ngayThamGia == null || ngayThamGia.trim().isEmpty())
                throw new IllegalArgumentException("Ngày tham gia không được để trống.");
            if (doanhSo < 0) throw new IllegalArgumentException("Doanh số không được âm.");
            if (diem < 0) throw new IllegalArgumentException("Điểm không được âm.");

            // Gán giá trị nếu hợp lệ
            this.setUserID(userID);
            this.setEmail(email);
            this.setPassword(password);
            this.setRole("khachhang");
            this.setHoTen(tenKhachHang);

            this.ngayThamGia = ngayThamGia;
            this.doanhSo = doanhSo;
            this.diem = diem;

        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi tạo KhachHang: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo KhachHang đã hoàn tất.");
        }
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

    public void setDiem(int diem) {
        this.diem = diem;
    }
}
