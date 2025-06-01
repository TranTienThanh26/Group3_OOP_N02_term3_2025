public class testCTHD {
    public static void test() {
        // Tạo đối tượng CTHD
        CTHD cthd = new CTHD(1, 101, "Phở Bò", 2, 80000);

        // In ra thông tin chi tiết hóa đơn
        System.out.println("ID Hóa Đơn: " + cthd.getID_HD());
        System.out.println("ID Món Ăn: " + cthd.getID_MonAn());
        System.out.println("Tên Món Ăn: " + cthd.getTenMonAn());
        System.out.println("Số Lượng: " + cthd.getSoluong());
        System.out.println("Thành Tiền: " + cthd.getThanhTien());
    }
}
