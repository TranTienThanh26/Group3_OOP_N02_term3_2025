package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.NguoiDung;
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
        NguoiDung user = db.login(email, password);

        if (user != null) {
            session.setAttribute("user", user);

            if ("Nhan Vien".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin";
            } else if ("Khach Hang".equalsIgnoreCase(user.getRole())) {
                return "redirect:/CustomerHome";
            } else {
                model.addAttribute("error", "⚠️ Vai trò không hợp lệ.");
                return "login";
            }
        } else {
            model.addAttribute("error", "❌ Sai email hoặc mật khẩu.");
            return "login";
        }
    }
}
