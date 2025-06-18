package com.example.servingwebcontent;
public class Ban {

    private int maBan;     // Mã bàn
    private String tenBan;   // Tên bàn
    private String trangThai; // Trạng thái bàn (Ví dụ: Trống, Đã đặt, Đang phục vụ)

    public Ban() {
    }

    public Ban(int maBan, String tenBan) {
        this.maBan = maBan;
        this.tenBan = tenBan;
    }

    public Ban(int maBan, String tenBan, String trangThai) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.trangThai = trangThai;
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
        try {
            this.trangThai = trangThai;
        } catch (Exception e) {
            System.err.println("Lỗi khi gán trạng thái bàn: " + e.getMessage());
        } finally {
            System.out.println("Thực hiện xong setTrangThai.");
        }
    }
}