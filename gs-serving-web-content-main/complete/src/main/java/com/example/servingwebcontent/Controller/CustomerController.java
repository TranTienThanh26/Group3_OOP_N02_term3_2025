package com.example.servingwebcontent.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.servingwebcontent.Model.NguoiDung;

@Controller
@SessionAttributes("user") // giữ thông tin đăng nhập
public class CustomerController {

    // Trang chủ sau khi đăng nhập (customer)
    @GetMapping("/CustomerHome")
    public String showCustomerHome(Model model, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null || !"Khach Hang".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "CustomerHome";
    }

    

    // Trang đặt bàn
    @GetMapping("/booking")
    public String showBookingPage(HttpSession session, Model model) {
        if (!isCustomerLoggedIn(session)) return "redirect:/login";
        model.addAttribute("user", session.getAttribute("user"));
        return "booking";
    }

    // Trang hỗ trợ
    @GetMapping("/support")
    public String showSupportPage(HttpSession session, Model model) {
        if (!isCustomerLoggedIn(session)) return "redirect:/login";
        model.addAttribute("user", session.getAttribute("user"));
        return "support";
    }

    // Trang hồ sơ cá nhân
    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null || !"Khach Hang".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "profile";
    }

    // Đăng xuất và xoá session
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // huỷ session
        return "redirect:/login";
    }

    // ✅ Hàm kiểm tra đăng nhập & vai trò khách hàng
    private boolean isCustomerLoggedIn(HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        return user != null && "Khach Hang".equalsIgnoreCase(user.getRole());
    }
}