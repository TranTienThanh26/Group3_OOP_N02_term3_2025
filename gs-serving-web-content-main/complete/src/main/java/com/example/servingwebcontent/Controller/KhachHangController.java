package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.KhachHang;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KhachHangController {

    // Hiển thị form nhập thông tin khách hàng
    @GetMapping("/khachhang-datmon")
    public String hienThiForm(Model model) {
        model.addAttribute("khachHang", new KhachHang()); // mẫu trống
        return "KhachHangdatmon";
    }

    // Xử lý khi người dùng submit form
    @PostMapping("/submit-khachhang")
    public String xuLyForm(
            @RequestParam("userID") int userID,
            @RequestParam("tenKhachHang") String tenKhachHang,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("ngayThamGia") String ngayThamGia,
            @RequestParam("doanhSo") int doanhSo,
            @RequestParam("diem") int diem,
            Model model) {

        KhachHang khachHang = new KhachHang(userID, tenKhachHang, email, password, ngayThamGia, doanhSo, diem);

        // Nếu userID <= 0 hoặc lỗi nào đó => constructor không gán thông tin đầy đủ
        if (khachHang.getUserID() <= 0 || khachHang.getEmail() == null) {
            model.addAttribute("message", "❌ Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
        } else {
            model.addAttribute("message", "✅ Thông tin khách hàng đã được ghi nhận.");
        }

        model.addAttribute("khachHang", khachHang);
        return "KhachHangdatmon";
    }
}
