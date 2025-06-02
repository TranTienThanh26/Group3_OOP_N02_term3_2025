import java.util.ArrayList;
import java.util.Scanner;

public class testHoaDon {

    static ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void hienThiDanhSach() {
        System.out.println("\n--- DANH SÁCH HÓA ĐƠN ---");
        if (danhSachHoaDon.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống.");
            return;
        }
        for (HoaDon hd : danhSachHoaDon) {
            System.out.println("ID Hóa Đơn: " + hd.getIdHoaDon());
            System.out.println("ID Khách Hàng: " + hd.getIdKH());
            System.out.println("ID Bàn: " + hd.getIdBan());
            System.out.println("Ngày Hóa Đơn: " + hd.getNgayHD());
            System.out.println("Tiền Món Ăn: " + hd.getTienMonAn());
            System.out.println("Tổng Tiền: " + hd.getTongtien());
            System.out.println("Trạng Thái: " + hd.getTrangthai());
            System.out.println("-------------------------");
        }
    }

    public static void themHoaDon() {
        System.out.print("Nhập ID Hóa Đơn: ");
        int idHoaDon = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập ID Khách Hàng: ");
        int idKH = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập ID Bàn: ");
        int idBan = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập Ngày Hóa Đơn (yyyy-mm-dd): ");
        String ngayHD = scanner.nextLine();

        System.out.print("Nhập Tiền Món Ăn: ");
        int tienMonAn = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập Tổng Tiền: ");
        int tongTien = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập Trạng Thái: ");
        String trangThai = scanner.nextLine();

        HoaDon hoaDon = new HoaDon(idHoaDon, idKH, idBan, ngayHD, tienMonAn, tongTien, trangThai);
        danhSachHoaDon.add(hoaDon);

        System.out.println("Đã thêm hóa đơn mới!");
    }

    public static void suaHoaDon() {
        System.out.print("Nhập ID Hóa Đơn cần sửa: ");
        int idHoaDon = scanner.nextInt();
        scanner.nextLine();

        boolean timThay = false;
        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getIdHoaDon() == idHoaDon) {
                System.out.print("Nhập Trạng Thái mới: ");
                String trangThai = scanner.nextLine();
                hd.setTrangthai(trangThai);
                System.out.println("Đã cập nhật hóa đơn.");
                timThay = true;
                break;
            }
        }
        if (!timThay) {
            System.out.println("Không tìm thấy hóa đơn có ID " + idHoaDon + ".");
        }
    }

    public static void xoaHoaDon() {
        System.out.print("Nhập ID Hóa Đơn cần xóa: ");
        int idHoaDon = scanner.nextInt();
        scanner.nextLine();

        boolean daXoa = false;
        for (int i = 0; i < danhSachHoaDon.size(); i++) {
            if (danhSachHoaDon.get(i).getIdHoaDon() == idHoaDon) {
                danhSachHoaDon.remove(i);
                System.out.println("Đã xóa hóa đơn có ID " + idHoaDon + ".");
                daXoa = true;
                break;
            }
        }
        if (!daXoa) {
            System.out.println("Không tìm thấy hóa đơn có ID " + idHoaDon + " để xóa.");
        }
    }

    public static void tinhTongDoanhThu() {
        long tongDoanhThu = 0;
        for (HoaDon hd : danhSachHoaDon) {
            if ("Đã thanh toán".equalsIgnoreCase(hd.getTrangthai())) {
                tongDoanhThu += hd.getTongtien();
            }
        }
        System.out.println("\n--- TỔNG DOANH THU ---");
        System.out.println("Tổng doanh thu từ các hóa đơn đã thanh toán: " + tongDoanhThu + " VND");
    }
 // Phương thức mới để khởi tạo dữ liệu mẫu cho hóa đơn
 public static void khoiTaoDuLieuMauHoaDon() {
    if (danhSachHoaDon.isEmpty()) { // Chỉ khởi tạo nếu danh sách đang trống
        danhSachHoaDon.add(new HoaDon(1, 101, 1, "2025-05-20", 200000, 180000, "Đã thanh toán"));
        danhSachHoaDon.add(new HoaDon(2, 102, 2, "2025-05-21", 150000, 150000, "Chờ thanh toán"));
        danhSachHoaDon.add(new HoaDon(3, 303, 3, "2025-05-22", 50000, 50000, "Đang xử lý"));
        danhSachHoaDon.add(new HoaDon(4, 104, 4, "2025-05-23", 100000, 100000, "Đã thanh toán"));
        System.out.println("Đã khởi tạo dữ liệu hóa đơn mẫu.");
    }
}
 public static void test() {
        
        int luaChon;
        do {
            System.out.println("\n--- MENU HÓA ĐƠN ---");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm hóa đơn");
            System.out.println("3. Sửa hóa đơn (cập nhật trạng thái)");
            System.out.println("4. Xóa hóa đơn");
            System.out.println("5. Tính tổng doanh thu");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            luaChon = scanner.nextInt();
            scanner.nextLine();

            switch (luaChon) {
                case 1:
                    hienThiDanhSach();
                    break;
                case 2:
                    themHoaDon();
                    break;
                case 3:
                    suaHoaDon();
                    break;
                case 4:
                    xoaHoaDon();
                    break;
                case 5:
                    tinhTongDoanhThu();
                    break;
                case 0:
                    System.out.println("Đang thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (luaChon != 0);
    }
}