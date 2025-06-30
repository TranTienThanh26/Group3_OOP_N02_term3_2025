package com.example.servingwebcontent.Model;

public class KhachHang extends NguoiDung {
    private String ten;           // ⚠️ Giữ nguyên tên biến là 'ten'
    private String ngayThamGia;
    private int doanhSo;
    private int diem;

    public KhachHang() {
        super();
        this.setRole("khachhang");
    }

    public KhachHang(int userID, String ten, String email, String password,
                     String ngayThamGia, int doanhSo, int diem) {
        super();

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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

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

    public int getUserID() {
        return super.getUserID();
    }

    public void setUserID(int userID) {
        super.setUserID(userID);
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public String getRole() {
        return super.getRole();
    }

    public void setRole(String role) {
        super.setRole(role);
    }
}
