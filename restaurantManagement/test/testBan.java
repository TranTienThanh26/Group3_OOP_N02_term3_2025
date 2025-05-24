public class testBan {
   
    public static void test() {
        Ban ban = new Ban(1, "Bàn 1", "Trống");
        System.out.println("Mã bàn: " + ban.getMaBan());
        System.out.println("Tên bàn: " + ban.getTenBan());
        System.out.println("Trạng thái: " + ban.getTrangThai());
    }
}