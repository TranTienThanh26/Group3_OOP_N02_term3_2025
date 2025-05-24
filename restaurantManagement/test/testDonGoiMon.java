// test/TestDonGoiMon.java
public class testDonGoiMon {
   

    public static void test() {
        DonGoiMon don = new DonGoiMon(123, 5, 10, "2025-05-11 20:35:00", "Đang chờ");
        System.out.println("Mã đơn: " + don.getMaDon());
        System.out.println("Mã bàn: " + don.getMaBan());
        System.out.println("Trạng thái: " + don.getTrangThai());
    }
}