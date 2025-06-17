// Class chứa thông tin về bàn gồm: Mã Bàn, Tên Bàn, Trạng thái Ban
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
        this.trangThai = trangThai;
    }
}