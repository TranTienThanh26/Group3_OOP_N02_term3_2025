package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.KhachHang;
import com.example.servingwebcontent.database.KhachHangAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/khachhang")
public class KhachHangController {

    private final KhachHangAiven khachHangAiven = new KhachHangAiven();

    // ✅ Hiển thị danh sách + form thêm
    @GetMapping
    public String danhSachKhachHang(Model model) {
        List<KhachHang> danhSach = khachHangAiven.getAllKhachHang();
        model.addAttribute("danhSachKhachHang", danhSach);
        model.addAttribute("khachHang", new KhachHang()); // dùng cho form thêm
        return "Admin/khachhang"; // ⚠️ Đã sửa: khớp với templates/Admin/khachhang.html
    }

    // ✅ Xử lý thêm hoặc cập nhật
    @PostMapping("/luu")
    public String luuKhachHang(@ModelAttribute KhachHang khachHang) {
        if (khachHangAiven.timKhachTheoUserID(khachHang.getUserID()) == null) {
            khachHangAiven.themKhachHang(khachHang);
        } else {
            khachHangAiven.capNhatKhachHang(khachHang);
        }
        return "redirect:/khachhang";
    }

    // ✅ Nạp dữ liệu khách hàng vào form (dùng chung Admin/khachhang.html)
    @GetMapping("/sua/{id}")
    public String hienFormSua(@PathVariable("id") int id, Model model) {
        List<KhachHang> danhSach = khachHangAiven.getAllKhachHang();
        model.addAttribute("danhSachKhachHang", danhSach);
        model.addAttribute("khachHang", khachHangAiven.timKhachTheoUserID(id));
        return "Admin/khachhang"; // ⚠️ sửa lại
    }

    // ✅ Xoá khách hàng
    @GetMapping("/xoa/{id}")
    public String xoaKhachHang(@PathVariable("id") int id) {
        khachHangAiven.xoaKhachHang(id);
        return "redirect:/khachhang";
    }

    // ✅ Tìm kiếm khách hàng theo tên (hiển thị trong Admin/khachhang.html)
    @GetMapping("/tim")
    public String timKiem(@RequestParam("keyword") String keyword, Model model) {
        List<KhachHang> ketQua = khachHangAiven.timKiemKhachHang(keyword);
        model.addAttribute("danhSachKhachHang", ketQua);
        model.addAttribute("khachHang", new KhachHang()); // giữ form thêm
        model.addAttribute("tuKhoa", keyword);
        return "Admin/khachhang"; // ⚠️ sửa lại
    }
}
