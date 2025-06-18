package com.example.servingwebcontent;

public class NhanVien extends NguoiDung {
    // userID, tenHienThi, email, password, role đã được kế thừa từ NguoiDung
    private String ngayVL;    // Ngày vào làm
    private String sdt;       // Số điện thoại
    private String chucvu;    // Chức vụ của nhân viên
    private int id_NQL;       // Mã người quản lý trực tiếp
    private String tinhTrang; // Tình trạng làm việc

    // Constructor mặc định
    public NhanVien() {
        super();
        this.setRole("nhanvien");
    }

    // Constructor đầy đủ
    public NhanVien(int userID, String tenNV, String email, String password,
                    String ngayVL, String sdt, String chucvu, int id_NQL, String tinhTrang) {
        super(userID, tenNV, email, password, "nhanvien");
        this.ngayVL = ngayVL;
        this.sdt = sdt;
        this.chucvu = chucvu;
        this.id_NQL = id_NQL;
        this.tinhTrang = tinhTrang;
    }

    // Getters
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

    // Setters
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

    // ❗ Setter duy nhất có try-catch-finally
    public void setTinhTrang(String tinhTrang) {
        try {
            this.tinhTrang = tinhTrang;
        } catch (Exception e) {
            System.err.println("Lỗi khi gán tình trạng làm việc: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi setTinhTrang.");
        }
    }
}
