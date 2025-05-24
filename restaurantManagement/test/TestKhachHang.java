public class TestKhachHang {
    public static void test() {
     
        KhachHang khach1 = new KhachHang(101, "Nguyễn Văn A", "2023-10-01", 5000000, 150);

        System.out.println("Thông tin khách hàng ban đầu:");
        System.out.println("Mã KH: " + khach1.getMaKhachHang());
        System.out.println("Tên: " + khach1.getTen());
        System.out.println("Ngày tham gia: " + khach1.getNgayThamGia());
        System.out.println("Doanh số: " + khach1.getDoanhSo());
        System.out.println("Điểm: " + khach1.getDiem());

        khach1.setTen("Trần Thị B");
        khach1.setDiem(200);

        System.out.println("\nThông tin sau khi cập nhật:");
        System.out.println("Tên mới: " + khach1.getTen());
        System.out.println("Điểm mới: " + khach1.getDiem());
    }
}
