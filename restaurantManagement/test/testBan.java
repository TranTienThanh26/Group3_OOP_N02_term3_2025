public class testBan {

    public static void test() {
        Ban ban1 = new Ban(1, "Bàn 1", "Trống");
        Ban ban2 = new Ban(2, "Bàn 2", "Đã đặt");
        Ban ban3 = new Ban(3, "Bàn 3", "Đang sử dụng");
        Ban ban4 = new Ban(4, "Bàn 4", "Trống");

        System.out.println("--- DANH SÁCH BÀN ---");

        System.out.println("Mã bàn: " + ban1.getMaBan());
        System.out.println("Tên bàn: " + ban1.getTenBan());
        System.out.println("Trạng thái: " + ban1.getTrangThai());

        System.out.println();

        System.out.println("Mã bàn: " + ban2.getMaBan());
        System.out.println("Tên bàn: " + ban2.getTenBan());
        System.out.println("Trạng thái: " + ban2.getTrangThai());

        System.out.println();

        System.out.println("Mã bàn: " + ban3.getMaBan());
        System.out.println("Tên bàn: " + ban3.getTenBan());
        System.out.println("Trạng thái: " + ban3.getTrangThai());

        System.out.println();

        System.out.println("Mã bàn: " + ban4.getMaBan());
        System.out.println("Tên bàn: " + ban4.getTenBan());
        System.out.println("Trạng thái: " + ban4.getTrangThai());
    }

    public static void main(String[] args) {
        test();
    }
}
