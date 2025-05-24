public class MonAn {

    private int maMonAn;
    private String tenMonAn;  // Tên món ăn
    private double donGia;     // Đơn giá
    private String loaiMonAn;   // Loại món ăn (ví dụ: Khai vị, Món chính, Tráng miệng)
    private String trangThai;  // Trạng thái của món ăn (Đang kinh doanh, Ngừng kinh doanh)

    public MonAn() {
    }

    public MonAn(int maMonAn, String tenMonAn, double donGia, String loaiMonAn) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
        this.loaiMonAn = loaiMonAn;
    }

    public MonAn(int maMonAn, String tenMonAn, double donGia, String loaiMonAn, String trangThai) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
        this.loaiMonAn = loaiMonAn;
        this.trangThai = trangThai;
    }

    public int getMaMonAn() {
        return maMonAn;
    }
    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getLoaiMonAn() {
        return loaiMonAn;
    }

    public void setLoaiMonAn(String loaiMonAn) {
        this.loaiMonAn = loaiMonAn;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}