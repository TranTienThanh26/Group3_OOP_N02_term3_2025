package com.example.servingwebcontent;

public class TestKhachHang {
    public static void test() {
        // Tạo một đối tượng KhachHang với đầy đủ các thuộc tính theo constructor đã định nghĩa
        // int userID, String tenKhachHang, String email, String password,
        // String ngayThamGia, int doanhSo, int diem
        KhachHang khach1 = new KhachHang(
            101,                  // userID
            "Nguyễn Văn A",       // tenKhachHang (tenHienThi)
            "nguyenvana@example.com", // email
            "pass123",            // password
            "2023-10-01",         // ngayThamGia
            5000000,              // doanhSo
            150                   // diem
        );

        System.out.println("--- Thông tin khách hàng ban đầu ---");
        System.out.println("Mã KH (UserID): " + khach1.getUserID()); // Lấy từ NguoiDung
        System.out.println("Tên (tenHienThi): " + khach1.getTenHienThi()); // Lấy từ NguoiDung
        System.out.println("Email: " + khach1.getEmail()); // Lấy từ NguoiDung
        System.out.println("Role: " + khach1.getRole()); // Lấy từ NguoiDung (sẽ là "khachhang")
        System.out.println("Ngày tham gia: " + khach1.getNgayThamGia());
        System.out.println("Doanh số: " + khach1.getDoanhSo());
        System.out.println("Điểm: " + khach1.getDiem());

        // Cập nhật một số thông tin
        khach1.setTenHienThi("Trần Thị B"); // Cập nhật tên thông qua setter của NguoiDung
        khach1.setEmail("tranb@example.com"); // Cập nhật email
        khach1.setDiem(200); // Cập nhật điểm

        System.out.println("\n--- Thông tin sau khi cập nhật ---");
        System.out.println("Tên mới (tenHienThi): " + khach1.getTenHienThi());
        System.out.println("Email mới: " + khach1.getEmail());
        System.out.println("Điểm mới: " + khach1.getDiem());

        // Test phương thức tangSoLuongDaBan (nếu có trong KhachHang, nhưng thực tế là của MonAn)
        // Lưu ý: Lớp KhachHang không có phương thức tangSoLuongDaBan.
        // Đây có thể là sự nhầm lẫn với lớp MonAn.
        // Nếu bạn muốn khách hàng "tích lũy doanh số", bạn sẽ cần một phương thức như:
        // khach1.themDoanhSo(100000);
        // và cập nhật điểm dựa trên doanh số.
        // Hiện tại, tôi sẽ không thêm phần đó vì nó không có sẵn trong lớp KhachHang của bạn.
    }
}