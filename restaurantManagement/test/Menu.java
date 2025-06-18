
package com.example.servingwebcontent;
import java.util.InputMismatchException;
import java.util.Scanner;

// Import TestMonAn if it exists in the same package or another package
import com.example.servingwebcontent.TestMonAn;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static TestMonAn testMonAn = new TestMonAn(); // Thêm khai báo và khởi tạo testMonAn

    public static void showMenu() {
        testMonAn.khoiTaoDuLieuMau(); // Khởi tạo dữ liệu mẫu khi chương trình bắt đầu
        testHoaDon.khoiTaoDuLieuMauHoaDon(); // Khởi tạo dữ liệu mẫu cho hóa đơn
        int choice;

        while (true) {
            System.out.println("\n========= MENU CHÍNH NHÀ HÀNG =========");
            System.out.println("1. Hiển thị danh sách món ăn hôm nayMón Ăn (Thêm/Sửa/Xóa)"); // Cập nhật mô tả: bỏ "Tìm kiếm"
            System.out.println("2. Hiển thị Top 5 món ăn Best SellerBàn Ăn");
            System.out.println("3. Tính tổng doanh thu");
            System.out.println("4. Món Ăn (Thêm/Sửa/Xóa)");
            System.out.println("5. Bàn Ăn (Thêm/Sửa/Xóa)");
            System.out.println("6. Hóa Đơn (Thêm/Sửa/Xóa)");
            // Cập nhật mô tả: bỏ "Tính tổng doanh thu"
            System.out.println("0. Thoát");
            System.out.print("👉 Chọn chức năng: ");

            try {
                choice = sc.nextInt();
                sc.nextLine(); // Đọc bỏ ký tự xuống dòng
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Lựa chọn không hợp lệ. Vui lòng nhập một số.");
                sc.nextLine(); // Đọc bỏ dòng nhập lỗi
                continue; // Tiếp tục vòng lặp để yêu cầu nhập lại
            }

            switch (choice) {
                case 1:
                testMonAn.hienThiDanhSachChiTiet(); // Hiển thị danh sách chi tiết món ăn
                break;
                case 2:
                testMonAn.hienThiTop5BestSeller(); // Hiển thị top 5 món best seller
                break;
                case 3:
                testHoaDon.tinhTongDoanhThu(); // Gọi menu con của quản lý món ăn
                break;
                case 4:
                testMonAn.test();; // Nếu có lớp testBan, sẽ gọi ở đây
                break;
                case 5:
                testBan.test(); // Nếu có lớp testHoaDon, sẽ gọi ở đây
                break;
                case 6:
                testHoaDon.test(); // Tính tổng doanh thu từ các hóa đơn đã thanh toán
                break;
                case 0:
                    System.out.println("👋 Thoát chương trình. Tạm biệt!");
                    sc.close(); // Đóng scanner trước khi thoát
                    return; // Thoát khỏi phương thức showMenu
                default:
                    System.out.println("⚠️ Vui lòng chọn đúng từ 0 đến 4."); // Cập nhật dải lựa chọn
            }
        }
    }

    
    }
