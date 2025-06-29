package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.*;
import com.example.servingwebcontent.database.CTHDAiven;
import com.example.servingwebcontent.database.HoaDonAiven;
import com.example.servingwebcontent.database.KhachHangAiven;
import com.example.servingwebcontent.database.NguoiDungAiven;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("user") // giữ thông tin đăng nhập
public class CustomerController {

    private final HoaDonAiven hoaDonDB = new HoaDonAiven();
    private final CTHDAiven cthdAiven = new CTHDAiven();
    private final NguoiDungAiven nguoiDungDB = new NguoiDungAiven();

    // Trang chủ sau khi đăng nhập (customer)
    @GetMapping("/CustomerHome")
    public String showCustomerHome(Model model, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null || !"Khach Hang".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        session.removeAttribute("cart");
        model.addAttribute("user", user);
        return "Customer/CustomerHome";
    }

    // Trang hồ sơ cá nhân (hiển thị hóa đơn + chi tiết)
    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null || !"Khach Hang".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);

        int idKH = KhachHangAiven.layMaKhachHangTheoUserID(user.getUserID());
        List<HoaDon> danhSach = hoaDonDB.getDanhSachHoaDonKhongTrung()
                .stream()
                .filter(hd -> hd.getIdKH() == idKH)
                .collect(Collectors.toList());

        for (HoaDon hd : danhSach) {
            List<CTHD> dsCT = cthdAiven.getCTHDByHoaDonID(hd.getIdHoaDon());
            List<CartItem> items = new ArrayList<>();
            for (CTHD ct : dsCT) {
                CartItem item = new CartItem(ct.getID_MonAn(), ct.getTenMonAn(), ct.getDonGia(), ct.getSoluong());
                items.add(item);
            }
            hd.setDsMonAn(items);
        }

        model.addAttribute("dsHoaDon", danhSach);
        return "Customer/profile";
    }

    // Cập nhật mật khẩu người dùng
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam String newPassword, HttpSession session, Model model) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        boolean success = nguoiDungDB.capNhatMatKhau(user.getUserID(), newPassword);
        if (success) {
            user.setPassword(newPassword);
            session.setAttribute("user", user);
            model.addAttribute("successMessage", "Đổi mật khẩu thành công.");
        } else {
            model.addAttribute("errorMessage", "Đổi mật khẩu thất bại.");
        }
        return "redirect:/profile";
    }

    // Cập nhật email người dùng
    @PostMapping("/updateEmail")
    public String updateEmail(@RequestParam String newEmail, HttpSession session, Model model) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        boolean success = nguoiDungDB.capNhatEmail(user.getUserID(), newEmail);
        if (success) {
            user.setEmail(newEmail);
            session.setAttribute("user", user);
            model.addAttribute("successMessage", "Đổi email thành công.");
        } else {
            model.addAttribute("errorMessage", "Đổi email thất bại.");
        }
        return "redirect:/profile";
    }

    // Trang hỗ trợ
    @GetMapping("/support")
    public String showSupportPage(HttpSession session, Model model) {
        if (!isCustomerLoggedIn(session)) return "redirect:/login";
        model.addAttribute("user", session.getAttribute("user"));
        return "support";
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