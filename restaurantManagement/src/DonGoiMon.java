public class DonGoiMon {

    private int maDon;
    private int maBan;
    private int maKH;
    private String thoiGianGoi;
    private String trangThai; // Ví dụ: Mới, Đã xác nhận, Đang chuẩn bị, Đã xong, Hủy

    public DonGoiMon() {
    }

    public DonGoiMon(int maDon, int maBan, int maKH, String thoiGianGoi, String trangThai) {
        this.maDon = maDon;
        this.maBan = maBan;
        this.maKH = maKH;
        this.thoiGianGoi = thoiGianGoi;
        this.trangThai = trangThai;
    }

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getThoiGianGoi() {
        return thoiGianGoi;
    }

    public void setThoiGianGoi(String thoiGianGoi) {
        this.thoiGianGoi = thoiGianGoi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}