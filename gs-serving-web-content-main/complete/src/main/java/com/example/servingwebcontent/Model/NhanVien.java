package com.example.servingwebcontent.Model;

public class NhanVien extends NguoiDung {

    private int id_NhanVien;
    private String ngayVL;
    private String sdt;
    private String chucvu;
    private int id_NQL;
    private String tinhTrang;

    public NhanVien() {
        super();
        this.setRole("nhanvien");
    }

    public NhanVien(int userID, String tenNhanVien, String email, String password,
                    int id_NhanVien, String ngayVL, String sdt,
                    String chucvu, int id_NQL, String tinhTrang) {
        super(); // Gọi constructor cha

        try {
            if (userID <= 0) throw new IllegalArgumentException("User ID phải lớn hơn 0.");
            if (tenNhanVien == null || tenNhanVien.trim().isEmpty())
                throw new IllegalArgumentException("Tên nhân viên không được để trống.");
            if (email == null || !email.contains("@"))
                throw new IllegalArgumentException("Email không hợp lệ.");
            if (password == null || password.length() < 4)
                throw new IllegalArgumentException("Mật khẩu phải có ít nhất 4 ký tự.");
            if (ngayVL == null || ngayVL.trim().isEmpty())
                throw new IllegalArgumentException("Ngày vào làm không được để trống.");
            if (sdt == null || !sdt.matches("\\d{10,11}"))
                throw new IllegalArgumentException("Số điện thoại phải là 10-11 chữ số.");
            if (chucvu == null || chucvu.trim().isEmpty())
                throw new IllegalArgumentException("Chức vụ không được để trống.");
            if (id_NQL <= 0)
                throw new IllegalArgumentException("Mã quản lý phải lớn hơn 0.");
            if (tinhTrang == null || tinhTrang.trim().isEmpty())
                throw new IllegalArgumentException("Tình trạng làm việc không được để trống.");

            this.setUserID(userID);
            this.setEmail(email);
            this.setPassword(password);
            this.setRole("nhanvien");
            this.setHoTen(tenNhanVien); // ✅ Gán tên nhân viên vào hoTen dùng chung

            this.id_NhanVien = id_NhanVien;
            this.ngayVL = ngayVL;
            this.sdt = sdt;
            this.chucvu = chucvu;
            this.id_NQL = id_NQL;
            this.tinhTrang = tinhTrang;

        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi tạo NhanVien: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo NhanVien hoàn tất.");
        }
    }

    // Getters/setters cho các trường riêng
    public int getId_NhanVien() {
        return id_NhanVien;
    }

    public void setId_NhanVien(int id_NhanVien) {
        this.id_NhanVien = id_NhanVien;
    }

    public String getNgayVL() {
        return ngayVL;
    }

    public void setNgayVL(String ngayVL) {
        this.ngayVL = ngayVL;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public int getId_NQL() {
        return id_NQL;
    }

    public void setId_NQL(int id_NQL) {
        this.id_NQL = id_NQL;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
