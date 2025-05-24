import java.util.ArrayList;
import java.util.Scanner;

public class testBan {

    static ArrayList<Ban> danhSachBan = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void hienThiDanhSach() {
        System.out.println("\n--- DANH SÁCH BÀN ---");
        for (Ban ban : danhSachBan) {
            System.out.println("Mã bàn: " + ban.getMaBan());
            System.out.println("Tên bàn: " + ban.getTenBan());
            System.out.println("Trạng thái: " + ban.getTrangThai());
            System.out.println();
        }
    }

    public static void themBan() {
        System.out.print("Nhập mã bàn: ");
        int maBan = scanner.nextInt(); scanner.nextLine(); // consume newline

        System.out.print("Nhập tên bàn: ");
        String tenBan = scanner.nextLine();

        System.out.print("Nhập trạng thái: ");
        String trangThai = scanner.nextLine();

        danhSachBan.add(new Ban(maBan, tenBan, trangThai));
        System.out.println("✅ Đã thêm bàn mới!");
    }

    public static void suaBan() {
        System.out.print("Nhập mã bàn cần sửa: ");
        int maBan = scanner.nextInt(); scanner.nextLine();

        for (Ban ban : danhSachBan) {
            if (ban.getMaBan() == maBan) {
                System.out.print("Nhập tên bàn mới: ");
                String tenBan = scanner.nextLine();
                System.out.print("Nhập trạng thái mới: ");
                String trangThai = scanner.nextLine();

                ban.setTenBan(tenBan);
                ban.setTrangThai(trangThai);
                System.out.println("✅ Đã cập nhật bàn.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy bàn với mã đã nhập.");
    }

    public static void xoaBan() {
        System.out.print("Nhập mã bàn cần xóa: ");
        int maBan = scanner.nextInt(); scanner.nextLine();

        for (int i = 0; i < danhSachBan.size(); i++) {
            if (danhSachBan.get(i).getMaBan() == maBan) {
                danhSachBan.remove(i);
                System.out.println("✅ Đã xóa bàn.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy bàn.");
    }

    public static void test() {
        // Khởi tạo sẵn vài bàn
        danhSachBan.add(new Ban(1, "Bàn 1", "Trống"));
        danhSachBan.add(new Ban(2, "Bàn 2", "Đã đặt"));
        danhSachBan.add(new Ban(3, "Bàn 3", "Đang sử dụng"));

        int luaChon;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Hiển thị danh sách bàn");
            System.out.println("2. Thêm bàn mới");
            System.out.println("3. Sửa bàn");
            System.out.println("4. Xóa bàn");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            luaChon = scanner.nextInt(); scanner.nextLine();

            switch (luaChon) {
                case 1 -> hienThiDanhSach();
                case 2 -> themBan();
                case 3 -> suaBan();
                case 4 -> xoaBan();
                case 0 -> System.out.println("👉 Thoát chương trình.");
                default -> System.out.println("❗ Lựa chọn không hợp lệ.");
            }

        } while (luaChon != 0);
    }

    public static void main(String[] args) {
        test();
    }
}
