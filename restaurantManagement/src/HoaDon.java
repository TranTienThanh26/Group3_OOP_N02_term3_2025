public class HoaDon {

    public int getIdHoaDon() {
        return idHoaDon;
    }

    // Setter cho idHoaDon
    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdKH() {
        return idKH;
    }

    // Setter cho idKH
    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public int getIdBan() {
        return idBan;
    }

    // Setter cho idBan
    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public String getNgayHD() {
        return ngayHD;
    }

    // Setter cho ngayHD
    public void setNgayHD(String ngayHD) {
        this.ngayHD = ngayHD;
    }

    public int getTienMonAn() {
        return tienMonAn;
    }

    // Setter cho tienMonAn
    public void setTienMonAn(int tienMonAn) {
        this.tienMonAn = tienMonAn;
    }

    public int getTongtien() {
        return tongtien;
    }

    // Setter cho tongtien
    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    // Setter cho trangthai
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public HoaDon() {
    }

    public HoaDon(int idHoaDon, int idKH, int idBan, String ngayHD, int tienMonAn, int tongtien) {
        this.idHoaDon = idHoaDon;
        this.idKH = idKH;
        this.idBan = idBan;
        this.ngayHD = ngayHD;
        this.tienMonAn = tienMonAn;
        this.tongtien = tongtien;
    }

    public HoaDon(int idHoaDon, int idKH, int idBan, String ngayHD, int tienMonAn,
            int tongtien, String trangthai) {
        this.idHoaDon = idHoaDon;
        this.idKH = idKH;
        this.idBan = idBan;
        this.ngayHD = ngayHD;
        this.tienMonAn = tienMonAn;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

     public HoaDon(int idHoaDon, String trangthai){
        this.idHoaDon = idHoaDon;
        this.trangthai = trangthai;
    }

    public int idHoaDon;
    private int idKH;
    private int idBan;
    private String ngayHD;
    private int tienMonAn;
    public int tongtien;
    public String trangthai;
}