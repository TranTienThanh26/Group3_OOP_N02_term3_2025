import java.util.Scanner;
import java.util.ArrayList;

public class testQLKH {

    // Hàm test chính thức để kiểm tra chức năng
    public static void test() {
        // 1. Tạo một đối tượng ListQLKH để quản lý danh sách khách hàng
        ListQLKH quanLyKhachHang = new ListQLKH();

        // 2. Thêm một số khách hàng mẫu vào danh sách
        System.out.println("--- Khởi tạo dữ liệu khách hàng ---");
        quanLyKhachHang.addObject(new QLKH("KH001", "Nguyễn Văn A", "Nam"));
        quanLyKhachHang.addObject(new QLKH("KH002", "Trần Thị B", "Nữ"));
        quanLyKhachHang.addObject(new QLKH("KH001", "Nguyễn Văn A", "Nam"));
        quanLyKhachHang.addObject(new QLKH("KH003", "Lê Minh C", "Nam"));
        quanLyKhachHang.addObject(new QLKH("KH001", "Nguyễn Văn A", "Nam")); // KH001 có thể xuất hiện nhiều lần
        quanLyKhachHang.addObject(new QLKH("KH004", "Phạm Thị D", "Nữ"));
        System.out.println("Đã thêm 6 khách hàng mẫu.\n");

        // Hiển thị toàn bộ danh sách khách hàng
        System.out.println("--- Danh sách tất cả các khách hàng đã đặt món ---");
        quanLyKhachHang.displayList();
        System.out.println("-----------------------------------------------\n");

        // 3. Nhập Mã Khách Hàng từ bàn phím để tìm kiếm thông tin khách hàng
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vui lòng nhập Mã Khách Hàng bạn muốn tìm (ví dụ: KH001, KH002): ");
        String inputMaKhachHang = scanner.nextLine();
        System.out.println("\n");

        // 4. Lọc và hiển thị thông tin các khách hàng có mã trùng
        // Dùng phương thức filterQLKHTradition để tìm kiếm theo mã khách hàng
        ArrayList<QLKH> danhSachKhachHang = quanLyKhachHang.filterQLKHTradition(inputMaKhachHang);

        if (danhSachKhachHang.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng với Mã Khách Hàng: " + inputMaKhachHang);
        } else {
            System.out.println("--- Thông tin khách hàng với Mã Khách Hàng '" + inputMaKhachHang + "': ---");
            // Hiển thị thông tin của các khách hàng có mã khách hàng trùng khớp
            for (QLKH ql : danhSachKhachHang) {
                System.out.println(ql); // Hiển thị tên và giới tính
            }
        }

        // Đóng Scanner để tránh rò rỉ tài nguyên
        scanner.close();
        System.out.println("\n--- KẾT THÚC KIỂM TRA TƯƠNG TÁC ---");
    }

    // Phương thức main để chạy hàm test
    public static void main(String[] args) {
        // Chạy hàm test
        test();
    }
}