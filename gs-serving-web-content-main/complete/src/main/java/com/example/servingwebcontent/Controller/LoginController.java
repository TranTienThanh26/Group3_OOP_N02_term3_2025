package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.NguoiDung;
import com.example.servingwebcontent.Model.NhanVien;
import com.example.servingwebcontent.Model.KhachHang;
import com.example.servingwebcontent.database.KhachHangAiven;
import com.example.servingwebcontent.database.NguoiDungAiven;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("nguoiDung") NguoiDung nguoiDung,
                               Model model, HttpSession session) {

        String email = nguoiDung.getEmail();
        String password = nguoiDung.getPassword();

        NguoiDungAiven db = new NguoiDungAiven();
        NguoiDung user = db.login(email, password); // Trả về NhanVien hoặc KhachHang

        if (user != null) {
            // 👉 Gán tên hiển thị dùng chung
            if ("Nhan Vien".equalsIgnoreCase(user.getRole()) && user instanceof NhanVien nv) {
                user.setHoTen(nv.getHoTen());
            } else if ("Khach Hang".equalsIgnoreCase(user.getRole())) {
                KhachHang kh = new KhachHangAiven().timKhachTheoUserID(user.getUserID());
                if (kh != null) {
                    user.setHoTen(kh.getHoTen());
                }
            }

            // 👉 Lưu user vào session
            session.setAttribute("user", user);

            // 👉 Điều hướng theo vai trò
            return switch (user.getRole().toLowerCase()) {
                case "nhan vien" -> "redirect:/NhanVienHome";
                case "khach hang" -> "redirect:/CustomerHome";
                default -> {
                    model.addAttribute("error", "⚠️ Vai trò không hợp lệ.");
                    yield "login";
                }
            };
        } else {
            model.addAttribute("error", "❌ Sai email hoặc mật khẩu.");
            return "login";
        }
    }
}
