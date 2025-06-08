import java.util.Scanner;
import java.util.ArrayList;
public class testKHDH {

    public static void test() {
        // 1. Tạo một đối tượng ListKHDH để quản lý danh sách các món ăn đã chọn
        ListKHDH quanLyMonAn = new ListKHDH();
        Scanner scanner = new Scanner(System.in);

        // 2. Thêm một số đơn hàng/món ăn mẫu
        // Constructor của KHDH: maKhachHang, maMonAn, ten, isPaid
        System.out.println("--- Khởi tạo dữ liệu món ăn đã chọn ---");
        quanLyMonAn.addObject(new KHDH("KH001", "Pho Cuon", "Nguyễn Văn A", false));
        quanLyMonAn.addObject(new KHDH("KH002", "Banh Xeo", "Trần Thị B", true));
        quanLyMonAn.addObject(new KHDH("KH001", "Goi Cuon", "Nguyễn Văn A", false));
        quanLyMonAn.addObject(new KHDH("KH003", "Che Thai", "Lê Văn C", true));
        quanLyMonAn.addObject(new KHDH("KH001", "Tra Da", "Nguyễn Văn A", true));
        quanLyMonAn.addObject(new KHDH("KH004", "Com Ga", "Phạm Thị D", false));
        quanLyMonAn.addObject(new KHDH("KH002", "Ca Phe Sua Da", "Trần Thị B", true));
        System.out.println("Đã thêm 7 mục đặt món mẫu.\n");

        // Hiển thị toàn bộ danh sách
        System.out.println("--- Danh sách tất cả các mục đặt món hiện có ---");
        // displayList() của ListKHDH sẽ gọi displayKHDH() của KHDH, in đầy đủ thông tin
        quanLyMonAn.displayList();
        System.out.println("-----------------------------------------------\n");

        // --- Yêu cầu 1: In danh sách món ăn đã chọn của khách hàng (theo Mã KH, Tên, hoặc Món Ăn) ---
        System.out.print("1. Vui lòng nhập Mã Khách Hàng): ");
        String inputKeywordTimKiem = scanner.nextLine();
        System.out.println();

        ArrayList<KHDH> ketQuaTimKiem = quanLyMonAn.filterKHDHTradition(inputKeywordTimKiem);

        if (ketQuaTimKiem.isEmpty()) {
            System.out.println("-> Không tìm thấy kết quả nào cho từ khóa: " + inputKeywordTimKiem);
        } else {
            System.out.println("--- Các món ăn đã chọn của '" + inputKeywordTimKiem + "': ---");
            // Phương thức này sẽ in đầy đủ thông tin KHDH, bao gồm tên khách hàng
            ListKHDH.displayList(ketQuaTimKiem);
        }
        System.out.println("\n-----------------------------------------------\n");

        // --- Yêu cầu 2: Kiểm tra món ăn đã được đặt hay chưa (đã thanh toán) theo Mã Khách Hàng ---
        System.out.print("2. Vui lòng nhập Mã Khách Hàng để kiểm tra các đơn đã THANH TOÁN (ví dụ: KH001, KH002): ");
        String inputMaKhachHangDaThanhToan = scanner.nextLine();
        System.out.println();

        ArrayList<KHDH> paidOrders = quanLyMonAn.getPaidOrdersByKhachHang(inputMaKhachHangDaThanhToan);

        if (paidOrders.isEmpty()) {
            System.out.println("-> Không tìm thấy món ăn đã thanh toán nào cho Mã Khách Hàng: " + inputMaKhachHangDaThanhToan);
        } else {
            System.out.println("--- Các món ăn đã THANH TOÁN của Khách Hàng '" + inputMaKhachHangDaThanhToan + "': ---");
            // Phương thức này sẽ in đầy đủ thông tin KHDH, bao gồm tên khách hàng
            ListKHDH.displayList(paidOrders);
        }
        System.out.println("\n-----------------------------------------------\n");

        // --- Yêu cầu 3: Hiển thị thông tin chi tiết từng đơn hàng (bao gồm tên khách hàng) ---
        System.out.println("3. --- Tổng quan TẤT CẢ các đơn hàng và thông tin chi tiết khách hàng ---");
        System.out.println("-----------------------------------------------\n");

        // Duyệt qua danh sách các đơn hàng và in ra thông tin cụ thể (chỉ một dòng mỗi đơn hàng)
        for (KHDH order : quanLyMonAn.ht) { // Truy cập trực tiếp thuộc tính ht của ListKHDH
            System.out.println(" | Tên KH: " + order.ten); // Lấy trường 'ten' từ KHDH
                               
                               
        }
        System.out.println("\n-----------------------------------------------\n");

        scanner.close();
        System.out.println("\n--- KẾT THÚC CHƯƠNG TRÌNH KIỂM TRA ---");
    }

   
    
}