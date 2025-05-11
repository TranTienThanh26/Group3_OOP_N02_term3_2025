public class testMonAn {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        MonAn mon = new MonAn(1, "Cơm Gà", 45000, "Chính", "Đang bán");
        System.out.println("Mã món ăn: " + mon.getMaMonAn());
        System.out.println("Tên: " + mon.getTenMonAn());
        System.out.println("Giá: " + mon.getDonGia());
        System.out.println("Trạng thái: " + mon.getTrangThai());
    }
}
