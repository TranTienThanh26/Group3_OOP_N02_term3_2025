package com.example.servingwebcontent.Model;

public class Ban {

    private int maBan;       // Mã bàn
    private String tenBan;   // Tên bàn
    private String trangThai; // Trạng thái bàn (Ví dụ: Trống, Đã đặt, Đang phục vụ)

    public Ban() {
    }

    public Ban(int maBan, String tenBan) {
        this.maBan = maBan;
        this.tenBan = tenBan;
    }

    public Ban(int maBan, String tenBan, String trangThai) {
        try {
            if (maBan <= 0) {
                throw new IllegalArgumentException("Mã bàn phải lớn hơn 0.");
            }
            if (tenBan == null || tenBan.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên bàn không được để trống.");
            }
            if (trangThai == null || trangThai.trim().isEmpty()) {
                throw new IllegalArgumentException("Trạng thái không được để trống.");
            }

            this.maBan = maBan;
            this.tenBan = tenBan;
            this.trangThai = trangThai;

        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi khởi tạo bàn: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo Ban (3 tham số) đã hoàn tất.");
        }
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
