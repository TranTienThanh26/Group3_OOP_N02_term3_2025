package com.example.servingwebcontent;

public class testNhanVien {
    public static void test() {
        // 1. Khởi tạo một đối tượng NhanVien với các thông tin cơ bản
        NhanVien nv = new NhanVien(
            201,                        // userID
            "Lê Văn C",                 // tenNV (tenHienThi)
            "levanc@example.com",       // email
            "pass_nv",                  // password
            "2022-05-15",               // ngayVL
            "0987654321",               // sdt
            "Quản lý",                  // chucvu
            100,                        // id_NQL
            "Đang làm việc"             // tinhTrang
        );

        // 2. Hiển thị thông tin ban đầu của nhân viên
        System.out.println("\nThông tin nhân viên ban đầu:");
        System.out.println("Tên: " + nv.getTenHienThi());
        System.out.println("Chức vụ: " + nv.getChucvu());
        System.out.println("Tình trạng: " + nv.getTinhTrang());
        System.out.println("Role (mặc định): " + nv.getRole()); // Kiểm tra vai trò mặc định

        // 3. Cập nhật một số thông tin của nhân viên
        nv.setChucvu("Trưởng phòng");
        nv.setTinhTrang("Đã nghỉ việc");
        nv.setSdt("0123456789"); // Cập nhật thêm SĐT

        // 4. Hiển thị thông tin sau khi cập nhật
        System.out.println("\nThông tin sau khi cập nhật:");
        System.out.println("Tên: " + nv.getTenHienThi()); // Tên không đổi
        System.out.println("Chức vụ mới: " + nv.getChucvu());
        System.out.println("Tình trạng mới: " + nv.getTinhTrang());
        System.out.println("Số điện thoại mới: " + nv.getSdt());

       
    }
}