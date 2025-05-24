import java.util.ArrayList;
import java.util.Scanner;

public class testMonAn {
    static ArrayList<MonAn> danhSachMonAn = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void hienThiDanhSach() {
        System.out.println("\n--- DANH SACH MON AN ---");
        for (MonAn mon : danhSachMonAn) {
            System.out.println("Ma mon an: " + mon.getMaMonAn());
            System.out.println("Ten: " + mon.getTenMonAn());
            System.out.println("Gia: " + mon.getDonGia());
            System.out.println("Loai mon an: " + mon.getLoaiMonAn());
            System.out.println("Trang thai: " + mon.getTrangThai());
        }
    }

    public static void themMonAn() {
        System.out.print("Nhap ma mon an: ");
        int maMonAn = scanner.nextInt(); scanner.nextLine();

        System.out.print("Nhap ten mon an: ");
        String tenMonAn = scanner.nextLine();

        System.out.print("Nhap gia mon an: ");
        double donGia = scanner.nextDouble(); scanner.nextLine();

        System.out.print("Nhap loai mon an: ");
        String loaiMonAn = scanner.nextLine();

        System.out.print("Nhap trang thai mon an: ");
        String trangThai = scanner.nextLine();

        danhSachMonAn.add(new MonAn(maMonAn, tenMonAn, donGia, loaiMonAn, trangThai));
        System.out.println("Da them mon an moi!");
    }

    public static void suaMonAn() {
        System.out.print("Nhap ma mon an can sua: ");
        int maMonAn = scanner.nextInt(); scanner.nextLine();

        for (MonAn mon : danhSachMonAn) {
            if (mon.getMaMonAn() == maMonAn) {
                System.out.print("Nhap ten mon an moi: ");
                String tenMonAn = scanner.nextLine();
                System.out.print("Nhap gia mon an moi: ");
                double donGia = scanner.nextDouble(); scanner.nextLine();
                System.out.print("Nhap loai mon an moi: ");
                String loaiMonAn = scanner.nextLine();
                System.out.print("Nhap trang thai moi: ");
                String trangThai = scanner.nextLine();

                mon.setTenMonAn(tenMonAn);
                mon.setLoaiMonAn(loaiMonAn);
                mon.setDonGia(donGia);
                mon.setTrangThai(trangThai);
                System.out.println("Da cap nhat mon an.");
                return;
            }
        }
        System.out.println("Khong tim thay mon an voi ma da nhap.");
    }

    public static void xoaMonAn() {
        System.out.print("Nhap ma mon an can xoa: ");
        int maMonAn = scanner.nextInt(); scanner.nextLine();

        for (int i = 0; i < danhSachMonAn.size(); i++) {
            if (danhSachMonAn.get(i).getMaMonAn() == maMonAn) {
                danhSachMonAn.remove(i);
                System.out.println("Da xoa mon an.");
                return;
            }
        }
        System.out.println("Khong tim thay mon an.");
    }

    public static void main(String[] args) {
        danhSachMonAn.add(new MonAn(1, "Pho", 50000, "Mon chinh", "Dang kinh doanh"));
        danhSachMonAn.add(new MonAn(2, "Banh mi", 20000, "Khai vi", "Dang kinh doanh"));
        danhSachMonAn.add(new MonAn(3, "Tra sua", 30000, "Trang mieng", "Ngung kinh doanh"));

        int luaChon;
        do {
            System.out.println("\n--- MENU QUAN LY MON AN ---");
            System.out.println("1. Hien thi danh sach mon an");
            System.out.println("2. Them mon an");
            System.out.println("3. Sua mon an");
            System.out.println("4. Xoa mon an");
            System.out.println("5. Thoat");
            System.out.print("Chon mot tuy chon: ");
            luaChon = scanner.nextInt(); scanner.nextLine();

            switch (luaChon) {
                case 1 -> hienThiDanhSach();
                case 2 -> themMonAn();
                case 3 -> suaMonAn();
                case 4 -> xoaMonAn();
                case 5 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le, vui long thu lai.");
            }
        } while (luaChon != 5);

    }
    public static void main(String[] args) {
        test();
}
}