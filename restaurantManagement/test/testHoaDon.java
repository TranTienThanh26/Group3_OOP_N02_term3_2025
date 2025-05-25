import java.util.ArrayList;
import java.util.Scanner;

public class testHoaDon {

    static ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void hienThiDanhSach() {
        System.out.println("\n--- DANH SACH HOA DON ---");
        for (HoaDon hd : danhSachHoaDon) {
            System.out.println("ID Hoa Don: " + hd.getIdHoaDon());
            System.out.println("ID Khach Hang: " + hd.getIdKH());
            System.out.println("ID Ban: " + hd.getIdBan());
            System.out.println("Ngay Hoa Don: " + hd.getNgayHD());
            System.out.println("Tien Mon An: " + hd.getTienMonAn());
            System.out.println("Code Voucher: " + hd.getCode_voucher());
            System.out.println("Tien Giam: " + hd.getTienGiam());
            System.out.println("Tong Tien: " + hd.getTongtien());
            System.out.println("Trang Thai: " + hd.getTrangthai());
            System.out.println();
        }
    }

    public static void themHoaDon() {
        System.out.print("Nhap ID Hoa Don: ");
        int idHoaDon = scanner.nextInt(); scanner.nextLine();

        System.out.print("Nhap ID Khach Hang: ");
        int idKH = scanner.nextInt(); scanner.nextLine();

        System.out.print("Nhap ID Ban: ");
        int idBan = scanner.nextInt(); scanner.nextLine();

        System.out.print("Nhap Ngay Hoa Don (yyyy-mm-dd): ");
        String ngayHD = scanner.nextLine();

        System.out.print("Nhap Tien Mon An: ");
        int tienMonAn = scanner.nextInt(); scanner.nextLine();

        System.out.print("Nhap Code Voucher (neu co): ");
        String codeVoucher = scanner.nextLine();

        System.out.print("Nhap Tien Giam: ");
        int tienGiam = scanner.nextInt(); scanner.nextLine();

        System.out.print("Nhap Tong Tien: ");
        int tongTien = scanner.nextInt(); scanner.nextLine();

        System.out.print("Nhap Trang Thai: ");
        String trangThai = scanner.nextLine();

        HoaDon hoaDon = new HoaDon(idHoaDon, idKH, idBan, ngayHD, tienMonAn, codeVoucher, tienGiam, tongTien, trangThai);
        danhSachHoaDon.add(hoaDon);

        System.out.println("Da them hoa don moi!");
    }

    public static void suaHoaDon() {
        System.out.print("Nhap ID Hoa Don can sua: ");
        int idHoaDon = scanner.nextInt(); scanner.nextLine();

        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getIdHoaDon() == idHoaDon) {
                System.out.print("Nhap Trang Thai moi: ");
                String trangThai = scanner.nextLine();
                hd.trangthai = trangThai;
                System.out.println("Da cap nhat hoa don.");
                return;
            }
        }
        System.out.println("Khong tim thay hoa don.");
    }

    public static void xoaHoaDon() {
        System.out.print("Nhap ID Hoa Don can xoa: ");
        int idHoaDon = scanner.nextInt(); scanner.nextLine();

        for (int i = 0; i < danhSachHoaDon.size(); i++) {
            if (danhSachHoaDon.get(i).getIdHoaDon() == idHoaDon) {
                danhSachHoaDon.remove(i);
                System.out.println("Da xoa hoa don.");
                return;
            }
        }
        System.out.println("Khong tim thay hoa don.");
    }

    public static void test() {
        // Khoi tao san vai hoa don
        danhSachHoaDon.add(new HoaDon(1, 101, 1, "2025-05-20", 200000, "KM10", 20000, 180000, "Da thanh toan"));
        danhSachHoaDon.add(new HoaDon(2, 102, 2, "2025-05-21", 150000, "", 0, 150000, "Cho thanh toan"));
        danhSachHoaDon.add(new HoaDon(3, "Dang xu ly"));

        int luaChon;
        do {
            System.out.println("\n--- MENU HOA DON ---");
            System.out.println("1. Hien thi danh sach hoa don");
            System.out.println("2. Them hoa don");
            System.out.println("3. Sua hoa don (cap nhat trang thai)");
            System.out.println("4. Xoa hoa don");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            luaChon = scanner.nextInt(); scanner.nextLine();

            switch (luaChon) {
                case 1 -> hienThiDanhSach();
                case 2 -> themHoaDon();
                case 3 -> suaHoaDon();
                case 4 -> xoaHoaDon();
                case 0 -> System.out.println("Dang thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (luaChon != 0);
    }

    public static void main(String[] args) {
        test();
    }
}
