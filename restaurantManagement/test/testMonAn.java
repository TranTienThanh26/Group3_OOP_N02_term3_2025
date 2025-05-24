import java.util.ArrayList;
import java.util.Scanner;

public class testMonAn {
    static ArrayList<MonAn> danhSachMonAn = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
   
    public static void hienThiDanhSach() {
        System.out.println("\n--- DANH SÁCH MÓN ĂN ---");
        for(MonAn mon : danhSachMonAn){
            System.out.println("Mã món ăn: " + mon.getMaMonAn());
            System.out.println("Tên: " + mon.getTenMonAn());
            System.out.println("Giá: " + mon.getDonGia());
            System.out.println("Loại món ăn: " + mon.getLoaiMonAn());
            System.out.println("Trạng thái: " + mon.getTrangThai());
    }
}

public static void themMonAn(){
    System.out.print("Nhập mã món ăn: ");
    int maMonAn = scanner.nextInt(); scanner.nextLine(); // consume newline

    System.out.print("Nhập tên món ăn: ");
    String tenMonAn = scanner.nextLine();

    System.out.print("Nhập giá món ăn: ");
    double donGia = scanner.nextDouble(); scanner.nextLine();

    System.out.print("Nhập loại món ăn: ");
    String loaiMonAn = scanner.nextLine();

    System.out.print("Nhập trạng thái món ăn: ");
    String trangThai = scanner.nextLine();

    danhSachMonAn.add(new MonAn(maMonAn, tenMonAn, donGia, loaiMonAn, trangThai));
    System.out.println("Đã thêm món ăn mới!");
}

public static void suaMonAn() {
    System.out.print("Nhập mã món ăn cần sửa: ");
    int maMonAn = scanner.nextInt(); scanner.nextLine();

    for (MonAn mon : danhSachMonAn) {
        if (mon.getMaMonAn() == maMonAn) {
            System.out.print("Nhập tên món ăn mới: ");
            String tenMonAn = scanner.nextLine();
            System.out.print("Nhập giá món ăn mới: ");
            double donGia = scanner.nextDouble(); scanner.nextLine();
            System.out.print("Nhập loại món ăn mới: ");
            String loaiMonAn = scanner.nextLine();
            System.out.print("Nhập trạng thái mới: ");
            String trangThai = scanner.nextLine();

            mon.setTenMonAn(tenMonAn);
            mon.setLoaiMonAn(loaiMonAn);
            mon.setDonGia(donGia);
            mon.setTrangThai(trangThai);
            System.out.println("Đã cập nhật món ăn.");
            return;
        }
    }
    System.out.println("Không tìm thấy món ăn với mã đã nhập.");
}

public static void xoaMonAn() {
    System.out.print("Nhập mã món ăn cần xóa: ");
    int maMonAn = scanner.nextInt(); scanner.nextLine();

    for (int i = 0; i < danhSachMonAn.size(); i++) {
        if (danhSachMonAn.get(i).getMaMonAn() == maMonAn) {
            danhSachMonAn.remove(i);
            System.out.println("Đã xóa món ăn.");
            return;
        }
    }
    System.out.println("Không tìm thấy món ăn.");
}

public static void test() {
    // Khởi tạo sẵn vài món ăn
    danhSachMonAn.add(new MonAn(1, "Phở", 50000, "Món chính", "Đang kinh doanh"));
    danhSachMonAn.add(new MonAn(2, "Bánh mì", 20000, "Khai vị", "Đang kinh doanh"));
    danhSachMonAn.add(new MonAn(3, "Trà sữa", 30000, "Tráng miệng", "Ngừng kinh doanh"));

   int luaChon;
    do {
        System.out.println("\n--- MENU QUẢN LÝ MÓN ĂN ---");
        System.out.println("1. Hiển thị danh sách món ăn");
        System.out.println("2. Thêm món ăn");
        System.out.println("3. Sửa món ăn");
        System.out.println("4. Xóa món ăn");
        System.out.println("5. Thoát");
        System.out.print("Chọn một tùy chọn: ");
        luaChon = scanner.nextInt(); scanner.nextLine();

        switch (luaChon) {
            case 1:
                hienThiDanhSach();
                break;
            case 2:
                themMonAn();
                break;
            case 3:
                suaMonAn();
                break;
            case 4:
                xoaMonAn();
                break;
            case 5:
                System.out.println("Thoát chương trình.");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
        }
    } while (luaChon != 5);
        System.out.println("Cảm ơn bạn đã sử dụng chương trình quản lý món ăn!");
    }
}


