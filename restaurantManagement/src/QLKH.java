class QLKH {
    private String maKhachHang; // Mã khách hàng
    private String tenKhachHang; // Tên khách hàng
    private String gioiTinh; // Giới tính khách hàng

    // Hàm tạo đối tượng QLKH
    public QLKH(String maKhachHang, String tenKhachHang, String gioiTinh) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.gioiTinh = gioiTinh;
    }

    // Lấy mã khách hàng
    public String getMaKhachHang() {
        return maKhachHang;
    }

    // Lấy tên khách hàng
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    // Lấy giới tính khách hàng
    public String getGioiTinh() {
        return gioiTinh;
    }

    // Phương thức toString() để hiển thị thông tin tên và giới tính khách hàng
    @Override
    public String toString() {
        return "Tên: " + tenKhachHang + " - Giới tính: " + gioiTinh; // Chỉ hiển thị tên và giới tính
    }
}
