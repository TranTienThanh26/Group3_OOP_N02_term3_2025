package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.MonAn;
import com.example.servingwebcontent.database.monAnAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MonAnController {

    // Khởi tạo service để lấy dữ liệu từ Aiven
    private final monAnAiven monAnService = new monAnAiven();

    // Truy cập đường dẫn /menu để hiển thị thực đơn
    @GetMapping("/menu")
    public String showMenu(Model model) {
        // Lấy toàn bộ danh sách món ăn từ Aiven
        List<MonAn> allDishes = monAnService.getMonAnListFromAiven();

        // Gửi từng loại món ăn vào model
        model.addAttribute("monChinh", filterByType(allDishes, "Món chính"));
        model.addAttribute("khaiVi", filterByType(allDishes, "Khai vị"));
        model.addAttribute("trangMieng", filterByType(allDishes, "Tráng miệng"));
        model.addAttribute("doUong", filterByType(allDishes, "Đồ uống"));

        return "Menu"; // tên file Menu.html trong templates
    }

    // Hàm lọc theo loại món ăn (không phân biệt hoa thường)
    private List<MonAn> filterByType(List<MonAn> list, String type) {
        return list.stream()
                   .filter(mon -> mon.getLoaiMonAn().equalsIgnoreCase(type))
                   .collect(Collectors.toList());
    }
}
