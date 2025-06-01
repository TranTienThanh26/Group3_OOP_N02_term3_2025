import java.util.ArrayList;
import java.util.Scanner;

public class testBan {

    static ArrayList<Ban> danhSachBan = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void hienThiDanhSach() {
        System.out.println("\n--- DANH SACH BAN ---");
        for (Ban ban : danhSachBan) {
            System.out.println("Ma ban: " + ban.getMaBan());
            System.out.println("Ten ban: " + ban.getTenBan());
            System.out.println("Trang thai: " + ban.getTrangThai());
            System.out.println();
        }
    }

    public static void themBan() {
        System.out.print("Nhap ma ban: ");
        int maBan = scanner.nextInt(); scanner.nextLine();

        System.out.print("Nhap ten ban: ");
        String tenBan = scanner.nextLine();

        System.out.print("Nhap trang thai: ");
        String trangThai = scanner.nextLine();

        danhSachBan.add(new Ban(maBan, tenBan, trangThai));
        System.out.println("Da them ban moi!");
    }

    public static void suaBan() {
        System.out.print("Nhap ma ban can sua: ");
        int maBan = scanner.nextInt(); scanner.nextLine();

        for (Ban ban : danhSachBan) {
            if (ban.getMaBan() == maBan) {
                System.out.print("Nhap ten ban moi: ");
                String tenBan = scanner.nextLine();
                System.out.print("Nhap trang thai moi: ");
                String trangThai = scanner.nextLine();

                ban.setTenBan(tenBan);
                ban.setTrangThai(trangThai);
                System.out.println("Da cap nhat ban.");
                return;
            }
        }
        System.out.println("Khong tim thay ban voi ma da nhap.");
    }

    public static void xoaBan() {
        System.out.print("Nhap ma ban can xoa: ");
        int maBan = scanner.nextInt(); scanner.nextLine();

        for (int i = 0; i < danhSachBan.size(); i++) {
            if (danhSachBan.get(i).getMaBan() == maBan) {
                danhSachBan.remove(i);
                System.out.println("Da xoa ban.");
                return;
            }
        }
        System.out.println("Khong tim thay ban.");
    }

    public static void test() {
        // Khoi tao san vai ban
        danhSachBan.add(new Ban(1, "Ban 1", "Trong"));
        danhSachBan.add(new Ban(2, "Ban 2", "Da dat"));
        danhSachBan.add(new Ban(3, "Ban 3", "Dang su dung"));

        int luaChon;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Hien thi danh sach ban");
            System.out.println("2. Them ban moi");
            System.out.println("3. Sua ban");
            System.out.println("4. Xoa ban");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            luaChon = scanner.nextInt(); scanner.nextLine();

            switch (luaChon) {
                case 1 -> hienThiDanhSach();
                case 2 -> themBan();
                case 3 -> suaBan();
                case 4 -> xoaBan();
                case 0 -> System.out.println("Dang thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }

        } while (luaChon != 0);
    }

    
    }

