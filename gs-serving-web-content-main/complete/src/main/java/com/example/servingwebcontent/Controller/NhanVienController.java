package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.NhanVien;
import com.example.servingwebcontent.database.NhanVienAiven;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NhanVienController {

    private final NhanVienAiven nhanVienAiven = new NhanVienAiven();

    // ===============================
    // 🔒 Trang chủ nhân viên (sau đăng nhập)
    // ===============================
    @GetMapping("/NhanVienHome")
    public String showNhanVienHome(HttpSession session, Model model) {
        NhanVien user = getNhanVienFromSession(session);
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        return "Admin/NhanVienHome"; // ✅ templates/Admin/NhanVienHome.html
    }

    // ===============================
    // 🪑 Quản lý bàn (dành cho nhân viên)
    // ===============================
    @GetMapping("/manage/tables")
    public String manageTables(HttpSession session, Model model) {
        NhanVien user = getNhanVienFromSession(session);
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        return "ManageTables"; // ✅ templates/ManageTables.html
    }

    // ===============================
    // 🚪 Đăng xuất
    // ===============================
    @GetMapping("/logoutNhanVien")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // ===============================
    // 🧩 Quản lý CRUD nhân viên
    // ===============================
    @GetMapping("/nhanvien")
    public String danhSachNhanVien(Model model) {
        List<NhanVien> nhanVienList = nhanVienAiven.getAllNhanVien();
        model.addAttribute("dsNhanVien", nhanVienList);
        model.addAttribute("nvMoi", new NhanVien());
        return "Admin/nhanvien"; // ✅ templates/Admin/nhanvien.html
    }

    @GetMapping("/nhanvien/timkiem")
    public String timKiemNhanVien(@RequestParam("keyword") String keyword, Model model) {
        List<NhanVien> ketQua = nhanVienAiven.timKiemNhanVien(keyword);
        model.addAttribute("dsNhanVien", ketQua);
        model.addAttribute("nvMoi", new NhanVien());
        model.addAttribute("tuKhoa", keyword);
        return "Admin/nhanvien"; // ✅ templates/Admin/nhanvien.html
    }

    @PostMapping("/nhanvien/them")
    public String themNhanVien(@ModelAttribute("nvMoi") NhanVien nv) {
        nhanVienAiven.themNhanVien(nv);
        return "redirect:/nhanvien";
    }

    @PostMapping("/nhanvien/sua")
    public String suaNhanVien(@ModelAttribute("nvSua") NhanVien nv) {
        nhanVienAiven.capNhatNhanVien(nv);
        return "redirect:/nhanvien";
    }

    @GetMapping("/nhanvien/xoa/{id}")
    public String xoaNhanVien(@PathVariable("id") int id) {
        nhanVienAiven.xoaNhanVien(id);
        return "redirect:/nhanvien";
    }

    // ===============================
    // 📦 Hàm tiện ích lấy user từ session
    // ===============================
    private NhanVien getNhanVienFromSession(HttpSession session) {
        Object obj = session.getAttribute("user");
        if (obj instanceof NhanVien nv && "Nhan Vien".equalsIgnoreCase(nv.getRole())) {
            return nv;
        }
        return null;
    }
}
