package com.example.servingwebcontent.Model;

public class MonAn {
    private int maMonAn;
    private String tenMonAn;
    private double donGia;
    private String loaiMonAn;
    private String trangThai;
    private int soLuongDaBan;
    private String hinhAnh;

    // Constructor mặc định
    public MonAn() {
        this.soLuongDaBan = 0;
    }

    // Constructor cơ bản
    public MonAn(int maMonAn, String tenMonAn, double donGia, String loaiMonAn) {
        this(maMonAn, tenMonAn, donGia, loaiMonAn, "Dang kinh doanh", 0, "");
    }

    // Constructor có trạng thái
    public MonAn(int maMonAn, String tenMonAn, double donGia, String loaiMonAn, String trangThai) {
        this(maMonAn, tenMonAn, donGia, loaiMonAn, trangThai, 0, "");
    }

    // ✅ Constructor đầy đủ
    public MonAn(int maMonAn, String tenMonAn, double donGia, String loaiMonAn, String trangThai, int soLuongDaBan, String hinhAnh) {
        try {
            if (maMonAn <= 0) throw new IllegalArgumentException("Mã món ăn phải lớn hơn 0.");
            if (tenMonAn == null || tenMonAn.trim().isEmpty())
                throw new IllegalArgumentException("Tên món ăn không được để trống.");
            if (donGia < 0) throw new IllegalArgumentException("Đơn giá không được âm.");
            if (loaiMonAn == null || loaiMonAn.trim().isEmpty())
                throw new IllegalArgumentException("Loại món ăn không hợp lệ.");
            if (soLuongDaBan < 0) throw new IllegalArgumentException("Số lượng đã bán không được âm.");

            this.maMonAn = maMonAn;
            this.tenMonAn = tenMonAn;
            this.donGia = donGia;
            this.loaiMonAn = loaiMonAn;
            this.trangThai = (trangThai != null && !trangThai.trim().isEmpty()) ? trangThai : "Dang kinh doanh";
            this.soLuongDaBan = soLuongDaBan;
            this.hinhAnh = (hinhAnh != null) ? hinhAnh : "";

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Lỗi khi tạo MonAn: " + e.getMessage());
        } finally {
            System.out.println("✅ Khởi tạo MonAn hoàn tất.");
        }
    }

    // Getters
    public int getMaMonAn() { return maMonAn; }
    public String getTenMonAn() { return tenMonAn; }
    public double getDonGia() { return donGia; }
    public String getLoaiMonAn() { return loaiMonAn; }
    public String getTrangThai() { return trangThai; }
    public int getSoLuongDaBan() { return soLuongDaBan; }
    public String getHinhAnh() { return hinhAnh; }

    // Setters
    public void setMaMonAn(int maMonAn) { this.maMonAn = maMonAn; }
    public void setTenMonAn(String tenMonAn) { this.tenMonAn = tenMonAn; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public void setLoaiMonAn(String loaiMonAn) { this.loaiMonAn = loaiMonAn; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public void setSoLuongDaBan(int soLuongDaBan) { this.soLuongDaBan = soLuongDaBan; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }

    // Tiện ích tăng số lượng bán
    public void tangSoLuongDaBan(int soLuongThem) {
        this.soLuongDaBan += soLuongThem;
    }
}
