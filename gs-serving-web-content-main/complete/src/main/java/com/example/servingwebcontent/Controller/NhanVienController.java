package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.NhanVien;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhanVienController {

    // 🏠 Trang chủ nhân viên
    @GetMapping("/NhanVienHome")
    public String showNhanVienHome(HttpSession session, Model model) {
        NhanVien user = getNhanVienFromSession(session);
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        return "Admin/NhanVienHome"; // cần tạo file NhanVienHome.html
    }

   

    // 🧾 Quản lý đơn hàng
    @GetMapping("/manage/orders")
    public String manageOrders(HttpSession session, Model model) {
        NhanVien user = getNhanVienFromSession(session);
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        return "ManageOrders"; // cần tạo file ManageOrders.html
    }

    // 🪑 Quản lý bàn
    @GetMapping("/manage/tables")
    public String manageTables(HttpSession session, Model model) {
        NhanVien user = getNhanVienFromSession(session);
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        return "ManageTables"; // cần tạo file ManageTables.html
    }

    // 🚪 Đăng xuất
    @GetMapping("/logoutNhanVien")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // ✅ Hàm tiện ích kiểm tra và ép kiểu
    private NhanVien getNhanVienFromSession(HttpSession session) {
        Object obj = session.getAttribute("user");
        if (obj instanceof NhanVien nv && "Nhan Vien".equalsIgnoreCase(nv.getRole())) {
            return nv;
        }
        return null;
    }
}
