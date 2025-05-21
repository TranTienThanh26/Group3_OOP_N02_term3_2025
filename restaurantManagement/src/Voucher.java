public class Voucher {

    // Các thuộc tính
    private String ma;           // Mã
    private String moTa;         // Mô tả
    private int phanTramGiam;    // Phần trăm giảm
    private String loaiMenu;     // Loại menu áp dụng 
    private int soLuong;         // Số lượng
    private int diemDoi;         // Số điểm để đổi

    // Constructor mặc định
    public Voucher() {
    }

    // Constructor đầy đủ tham số
    public Voucher(String ma, String moTa, int phanTramGiam, String loaiMenu, int soLuong, int diemDoi) {
        this.ma = ma;
        this.moTa = moTa;
        this.phanTramGiam = phanTramGiam;
        this.loaiMenu = loaiMenu;
        this.soLuong = soLuong;
        this.diemDoi = diemDoi;
    }

    // Getter và Setter
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public String getLoaiMenu() {
        return loaiMenu;
    }

    public void setLoaiMenu(String loaiMenu) {
        this.loaiMenu = loaiMenu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDiemDoi() {
        return diemDoi;
    }

    public void setDiemDoi(int diemDoi) {
        this.diemDoi = diemDoi;
    }

}
