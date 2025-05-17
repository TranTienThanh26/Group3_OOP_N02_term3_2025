public class KhachHang {

    private int maKhachHang;
    private String ten;
    private String ngayThamGia;
    private int doanhSo;
    private int diem;

    public KhachHang() {
    }

    public KhachHang(int maKhachHang, String ten, String ngayThamGia, int doanhSo, int diem) {
        this.maKhachHang = maKhachHang;
        this.ten = ten;
        this.ngayThamGia = ngayThamGia;
        this.doanhSo = doanhSo;
        this.diem = diem;
    }

    public int getMaKhachHang() {
        return maKhachHang;
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

    public int getDoanhSo() {
        return doanhSo;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}
