// File: testMonAn.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class testMonAn {
    public static ArrayList<MonAn> danhSachMonAn = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    // nextMaMonAn sẽ là mã tiếp theo có thể sử dụng, ban đầu là 1
    static int nextMaMonAn = 1;

    // --- CÁC HÀM TIỆN ÍCH HỖ TRỢ ---

    // Hàm tiện ích để đọc số nguyên từ người dùng với xử lý lỗi
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ ký tự xuống dòng
                return value;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Nhập không hợp lệ. Vui lòng nhập một số nguyên.");
                scanner.nextLine(); // Đọc bỏ dòng nhập lỗi
            }
        }
    }

    // Hàm tiện ích để hiển thị danh sách món ăn (dùng chung cho hiển thị và tìm kiếm)
    private static void displayMonAnList(List<MonAn> list, String title) {
        if (list.isEmpty()) {
            System.out.println(title + " trống.");
            return;
        }
        System.out.println("\n--- " + title.toUpperCase() + " ---");
        System.out.println(String.format("%-6s | %-25s | %-10s | %-15s | %-15s | %s",
                "Mã", "Tên Món", "Giá (VNĐ)", "Loại Món", "Tình Trạng", "Đã bán"));
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (MonAn mon : list) {
            System.out.printf("Mã: %-4d | Tên: %-25s | Giá: %-10.0f | Loại: %-15s | Tình trạng: %s | Đã bán: %d\n",
                    mon.getMaMonAn(), mon.getTenMonAn(), mon.getDonGia(),
                    mon.getLoaiMonAn(), mon.getTrangThai(), mon.getSoLuongDaBan());
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    // --- CÁC CHỨC NĂNG CHÍNH ---

    public static void khoiTaoDuLieuMau() {
        danhSachMonAn.clear();
        nextMaMonAn = 1; // Luôn đặt lại về 1 khi khởi tạo dữ liệu mẫu

        // Khi thêm món ăn mẫu, sử dụng nextMaMonAn++ để cấp mã và tăng nextMaMonAn
        danhSachMonAn.add(new MonAn(nextMaMonAn++, "Phở Bò Đặc Biệt", 60000, "Mon chinh", "Dang kinh doanh", 15));
        danhSachMonAn.add(new MonAn(nextMaMonAn++, "Bánh Mì Pate", 20000, "Khai vi", "Dang kinh doanh", 25));
        danhSachMonAn.add(new MonAn(nextMaMonAn++, "Trà Sữa Trân Châu", 30000, "Do uong", "Ngung kinh doanh", 0));
        danhSachMonAn.add(new MonAn(nextMaMonAn++, "Cơm Gà Xối Mỡ", 65000, "Mon chinh", "Dang kinh doanh", 10));
        danhSachMonAn.add(new MonAn(nextMaMonAn++, "Nem Rán", 45000, "Khai vi", "Dang kinh doanh", 20));
        danhSachMonAn.add(new MonAn(nextMaMonAn++, "Cà Phê Sữa Đá", 25000, "Do uong", "Dang kinh doanh", 18));
        danhSachMonAn.add(new MonAn(nextMaMonAn++, "Chè Trôi Nước", 35000, "Trang mieng", "Dang kinh doanh", 12));
        danhSachMonAn.add(new MonAn(nextMaMonAn++, "Nước Cam Ép", 30000, "Do uong", "Dang kinh doanh", 8));
        danhSachMonAn.add(new MonAn(nextMaMonAn++, "Kem Vani", 28000, "Trang mieng", "Dang kinh doanh", 7));

        // Đã bỏ dòng: nextMaMonAn = timMaMonAnLonNhat() + 1;
        System.out.println("✅ Đã khởi tạo dữ liệu mẫu.");
    }

    public static void hienThiDanhSachChiTiet() {
        displayMonAnList(danhSachMonAn, "DANH SÁCH MÓN ĂN CHI TIẾT");
    }

    public static void themMonAn() {
        int maMonAn = nextMaMonAn; // Sử dụng mã tiếp theo có sẵn
        System.out.println("Mã món ăn mới: " + maMonAn);

        System.out.print("Nhập tên món ăn: ");
        String tenMonAn = scanner.nextLine();

        for (MonAn mon : danhSachMonAn) {
            if (mon.getTenMonAn().equalsIgnoreCase(tenMonAn)) {
                System.out.println("⚠️ Món ăn với tên '" + tenMonAn + "' đã tồn tại. Không thể thêm.");
                return;
            }
        }

        System.out.print("Nhập giá món ăn: ");
        double donGia = 0;
        try {
            donGia = scanner.nextDouble();
            if (donGia <= 0) {
                System.out.println("⚠️ Giá phải là số dương. Thao tác thêm bị hủy.");
                scanner.nextLine();
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("⚠️ Giá không hợp lệ. Vui lòng nhập số.");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        System.out.print("Nhập loại món ăn (Ví dụ: Món Chính, Đồ Uống, Tráng Miệng): ");
        String loaiMonAn = scanner.nextLine();

        String trangThai = "";
        while (true) {
            int choiceHang = getIntInput("Nhập tình trạng còn hàng (1: Đang kinh doanh, 0: Ngừng kinh doanh): ");
            if (choiceHang == 1) {
                trangThai = "Dang kinh doanh";
                break;
            } else if (choiceHang == 0) {
                trangThai = "Ngung kinh doanh";
                break;
            } else {
                System.out.println("⚠️ Lựa chọn không hợp lệ. Vui lòng nhập 1 hoặc 0.");
            }
        }

        danhSachMonAn.add(new MonAn(maMonAn, tenMonAn, donGia, loaiMonAn, trangThai, 0));
        nextMaMonAn++; // Tăng mã cho lần thêm tiếp theo
        System.out.println("✅ Đã thêm món ăn mới: " + tenMonAn);
    }

    public static void suaMonAn() {
        int maMonAn = getIntInput("Nhập mã món ăn cần sửa: ");

        MonAn monCanSua = null;
        for (MonAn mon : danhSachMonAn) {
            if (mon.getMaMonAn() == maMonAn) {
                monCanSua = mon;
                break;
            }
        }

        if (monCanSua == null) {
            System.out.println("❌ Không tìm thấy món ăn với mã đã nhập.");
            return;
        }

        System.out.println("Đang sửa món ăn: " + monCanSua.getTenMonAn());

        System.out.print("Nhập tên món ăn mới (Enter để giữ nguyên '" + monCanSua.getTenMonAn() + "'): ");
        String tenMonAnMoi = scanner.nextLine();
        if (tenMonAnMoi.isEmpty()) {
            tenMonAnMoi = monCanSua.getTenMonAn();
        } else {
            for (MonAn mon : danhSachMonAn) {
                if (mon.getTenMonAn().equalsIgnoreCase(tenMonAnMoi) && mon.getMaMonAn() != maMonAn) {
                    System.out.println("⚠️ Tên món ăn '" + tenMonAnMoi + "' đã tồn tại cho món khác. Giữ nguyên tên cũ.");
                    tenMonAnMoi = monCanSua.getTenMonAn();
                    break;
                }
            }
        }

        System.out.print("Nhập giá món ăn mới (Enter để giữ nguyên '" + monCanSua.getDonGia() + "'): ");
        String giaStr = scanner.nextLine();
        double donGiaMoi = monCanSua.getDonGia();
        if (!giaStr.isEmpty()) {
            try {
                double parsedGia = Double.parseDouble(giaStr);
                if (parsedGia <= 0) {
                    System.out.println("⚠️ Giá phải là số dương. Giữ nguyên giá cũ.");
                } else {
                    donGiaMoi = parsedGia;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Giá không hợp lệ. Giữ nguyên giá cũ.");
            }
        }

        System.out.print("Nhập loại món ăn mới (Enter để giữ nguyên '" + monCanSua.getLoaiMonAn() + "'): ");
        String loaiMonAnMoi = scanner.nextLine();
        if (loaiMonAnMoi.isEmpty()) loaiMonAnMoi = monCanSua.getLoaiMonAn();

        String trangThaiMoi = monCanSua.getTrangThai();
        while (true) {
            System.out.print("Nhập tình trạng còn hàng mới (Hiện tại: " + monCanSua.getTrangThai() + ") (1: Đang kinh doanh, 0: Ngừng kinh doanh): ");
            String choiceHangStr = scanner.nextLine();
            if (choiceHangStr.isEmpty()) {
                break;
            }
            try {
                int choiceHang = Integer.parseInt(choiceHangStr);
                if (choiceHang == 1) {
                    trangThaiMoi = "Dang kinh doanh";
                    break;
                } else if (choiceHang == 0) {
                    trangThaiMoi = "Ngung kinh doanh";
                    break;
                } else {
                    System.out.println("⚠️ Lựa chọn không hợp lệ. Vui lòng nhập 1 hoặc 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Nhập không hợp lệ. Vui lòng nhập số (1 hoặc 0).");
            }
        }

        monCanSua.setTenMonAn(tenMonAnMoi);
        monCanSua.setLoaiMonAn(loaiMonAnMoi);
        monCanSua.setDonGia(donGiaMoi);
        monCanSua.setTrangThai(trangThaiMoi);
        System.out.println("✅ Đã cập nhật món ăn có mã: " + maMonAn);
    }

    public static void xoaMonAn() {
        int maMonAn = getIntInput("Nhập mã món ăn cần xóa: ");

        MonAn monCanXoa = null;
        for (MonAn mon : danhSachMonAn) {
            if (mon.getMaMonAn() == maMonAn) {
                monCanXoa = mon;
                break;
            }
        }

        if (monCanXoa != null) {
            danhSachMonAn.remove(monCanXoa);
            System.out.println("✅ Đã xóa món ăn có mã: " + maMonAn);
            // Sau khi xóa, không cần điều chỉnh nextMaMonAn vì nó chỉ cấp mã tăng dần
            // và giả định mã không được tái sử dụng.
        } else {
            System.out.println("❌ Không tìm thấy món ăn với mã đã nhập.");
        }
    }

    public static void hienThiTop5BestSeller() {
        if (danhSachMonAn.isEmpty()) {
            System.out.println("📋 Menu trống, không thể xác định món best seller.");
            return;
        }

        List<MonAn> sortedList = new ArrayList<>(danhSachMonAn);
        Collections.sort(sortedList, Comparator.comparingInt(MonAn::getSoLuongDaBan).reversed());

        System.out.println("\n--- TOP 5 MÓN ĂN BEST SELLER HÔM NAY ---");

        boolean hasSoldItems = false;
        for (MonAn mon : sortedList) {
            if (mon.getSoLuongDaBan() > 0) {
                hasSoldItems = true;
                break;
            }
        }

        if (!hasSoldItems) {
            System.out.println("Hiện chưa có món ăn nào được bán trong ngày.");
            System.out.println("----------------------------------------");
            return;
        }

        int count = 0;
        for (MonAn mon : sortedList) {
            if (mon.getSoLuongDaBan() > 0 && mon.getTrangThai().equalsIgnoreCase("Dang kinh doanh")) {
                System.out.printf("🥇 %d. %-25s (Đã bán: %d suất)\n", (count + 1), mon.getTenMonAn(), mon.getSoLuongDaBan());
                count++;
                if (count >= 5) {
                    break;
                }
            }
        }
        System.out.println("----------------------------------------");
    }

    // Hàm test() chính, chỉ còn các chức năng CRUD
    public static void test() {
        int luaChon;
        while (true) {
            System.out.println("\n--- MENU QUẢN LÝ MÓN ĂN ---");
            System.out.println("1. Thêm món ăn");
            System.out.println("2. Sửa món ăn");
            System.out.println("3. Xóa món ăn");
            System.out.println("0. Quay lại Menu chính");
            luaChon = getIntInput("Chọn một tùy chọn: ");

            switch (luaChon) {
                case 1 -> themMonAn();
                case 2 -> suaMonAn();
                case 3 -> xoaMonAn();
                case 0 -> {
                    System.out.println("Quay lại Menu chính.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        }
    }

    
}