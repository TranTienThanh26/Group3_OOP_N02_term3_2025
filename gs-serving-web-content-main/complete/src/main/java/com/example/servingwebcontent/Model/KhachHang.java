package com.example.servingwebcontent.Model;

public class KhachHang extends NguoiDung {
    private String ten;           // Tên khách hàng (tương ứng với cột Ten trong bảng KhachHang)
    private String ngayThamGia;  // Ngày khách hàng tham gia
    private int doanhSo;         // Tổng doanh số đã mua
    private int diem;            // Điểm tích lũy

    // ✅ Constructor mặc định
    public KhachHang() {
        super(); // Gọi constructor mặc định của lớp cha NguoiDung
        this.setRole("khachhang");
    }

    // ✅ Constructor đầy đủ có kiểm tra dữ liệu
    public KhachHang(int userID, String ten, String email, String password,
                     String ngayThamGia, int doanhSo, int diem) {
        super(); // gọi trước để tránh lỗi constructor chain

        try {
            if (userID <= 0) throw new IllegalArgumentException("User ID phải lớn hơn 0.");
            if (ten == null || ten.trim().isEmpty())
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

            this.ten = ten;
            this.ngayThamGia = ngayThamGia;
            this.doanhSo = doanhSo;
            this.diem = diem;

        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi tạo KhachHang: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo KhachHang đã hoàn tất.");
        }
    }

    // ✅ Getter/Setter cho tên
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    // ✅ Các getter/setter còn lại
    public String getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(String ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }

    public int getDoanhSo() {
        return doanhSo;
    }

    public void setDoanhSo(int doanhSo) {
        this.doanhSo = doanhSo;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}
