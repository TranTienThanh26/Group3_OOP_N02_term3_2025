package com.example.servingwebcontent.Model;

public class NhanVien extends NguoiDung {
    private int id_NhanVien;   // Mã nhân viên
    private String ngayVL;     // Ngày vào làm
    private String sdt;        // Số điện thoại
    private String chucvu;     // Chức vụ của nhân viên
    private int id_NQL;        // Mã người quản lý trực tiếp
    private String tinhTrang;  // Tình trạng làm việc
    private String tenNhanVien; // ✅ Tên nhân viên

    // ✅ Constructor mặc định
    public NhanVien() {
        super();
        this.setRole("nhanvien");
    }

    // ✅ Constructor đầy đủ có xử lý lỗi và gán tên nhân viên
    public NhanVien(int userID, String email, String password,
                    int id_NhanVien, String ngayVL, String sdt,
                    String chucvu, int id_NQL, String tinhTrang, String tenNhanVien) {
        super(userID, email, password, "nhanvien");

        try {
            if (ngayVL == null || ngayVL.trim().isEmpty()) {
                throw new IllegalArgumentException("Ngày vào làm không được để trống.");
            }
            if (sdt == null || !sdt.matches("\\d{10,11}")) {
                throw new IllegalArgumentException("Số điện thoại phải là 10-11 chữ số.");
            }
            if (chucvu == null || chucvu.trim().isEmpty()) {
                throw new IllegalArgumentException("Chức vụ không được để trống.");
            }
            if (id_NQL <= 0) {
                throw new IllegalArgumentException("ID người quản lý phải lớn hơn 0.");
            }
            if (tinhTrang == null || tinhTrang.trim().isEmpty()) {
                throw new IllegalArgumentException("Tình trạng làm việc không hợp lệ.");
            }
            if (tenNhanVien == null || tenNhanVien.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên nhân viên không được để trống.");
            }

            this.id_NhanVien = id_NhanVien;
            this.ngayVL = ngayVL;
            this.sdt = sdt;
            this.chucvu = chucvu;
            this.id_NQL = id_NQL;
            this.tinhTrang = tinhTrang;
            this.tenNhanVien = tenNhanVien; // ✅ Gán tên nhân viên

        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi tạo NhanVien: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo NhanVien hoàn tất.");
        }
    }

    // ✅ Getters
    public int getId_NhanVien() {
        return id_NhanVien;
    }

    public String getNgayVL() {
        return ngayVL;
    }

    public String getSdt() {
        return sdt;
    }

    public String getChucvu() {
        return chucvu;
    }

    public int getId_NQL() {
        return id_NQL;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    // ✅ Setters
    public void setId_NhanVien(int id_NhanVien) {
        this.id_NhanVien = id_NhanVien;
    }

    public void setNgayVL(String ngayVL) {
        this.ngayVL = ngayVL;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public void setId_NQL(int id_NQL) {
        this.id_NQL = id_NQL;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }
}
