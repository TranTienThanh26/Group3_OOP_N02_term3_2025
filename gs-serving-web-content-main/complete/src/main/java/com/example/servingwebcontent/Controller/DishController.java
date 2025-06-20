package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.MonAn;
import com.example.servingwebcontent.database.monAnAiven;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DishController {

    private final monAnAiven monAnService = new monAnAiven();

    // Mapping đường dẫn /manage/menu cho admin
    @GetMapping("/manage/menu")
    public String showAdminDishPage(Model model) {
        List<MonAn> all = monAnService.getMonAnListFromAiven();

        model.addAttribute("monChinh", filterByType(all, "Món chính"));
        model.addAttribute("khaiVi", filterByType(all, "Khai vị"));
        model.addAttribute("trangMieng", filterByType(all, "Tráng miệng"));
        model.addAttribute("doUong", filterByType(all, "Đồ uống"));

        // Trả về giao diện thực đơn cho admin
        return "Admin/Dish";
    }

    private List<MonAn> filterByType(List<MonAn> list, String type) {
        return list.stream()
                   .filter(mon -> mon.getLoaiMonAn().equalsIgnoreCase(type))
                   .collect(Collectors.toList());
    }
}