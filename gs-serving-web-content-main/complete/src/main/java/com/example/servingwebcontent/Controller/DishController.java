package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.MonAn;
import com.example.servingwebcontent.database.monAnAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/manage/menu")
public class DishController {

    private final monAnAiven monAnService = new monAnAiven();

    @GetMapping
    public String showAdminDishPage(@RequestParam(value = "keyword", required = false) String keyword,
                                    Model model) {
        List<MonAn> all = monAnService.getMonAnListFromAiven();
        boolean isSearch = false;

        // Tạo danh sách theo loại
        List<MonAn> monChinh = filterByType(all, "Món chính");
        List<MonAn> khaiVi = filterByType(all, "Khai vị");
        List<MonAn> trangMieng = filterByType(all, "Tráng miệng");
        List<MonAn> doUong = filterByType(all, "Đồ uống");

        if (keyword != null && !keyword.trim().isEmpty()) {
            isSearch = true;
            String lower = keyword.toLowerCase();

            monChinh = monChinh.stream()
                    .filter(mon -> mon.getTenMonAn().toLowerCase().contains(lower))
                    .collect(Collectors.toList());

            khaiVi = khaiVi.stream()
                    .filter(mon -> mon.getTenMonAn().toLowerCase().contains(lower))
                    .collect(Collectors.toList());

            trangMieng = trangMieng.stream()
                    .filter(mon -> mon.getTenMonAn().toLowerCase().contains(lower))
                    .collect(Collectors.toList());

            doUong = doUong.stream()
                    .filter(mon -> mon.getTenMonAn().toLowerCase().contains(lower))
                    .collect(Collectors.toList());

            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("isSearch", isSearch);
        model.addAttribute("monChinh", monChinh);
        model.addAttribute("khaiVi", khaiVi);
        model.addAttribute("trangMieng", trangMieng);
        model.addAttribute("doUong", doUong);

        return "Admin/Dish";
    }

    @PostMapping("/update")
    public String updateDish(@ModelAttribute MonAn monAn,
                             @RequestParam(value = "hinhAnhFile", required = false) MultipartFile hinhAnhFile) {
        if (hinhAnhFile != null && !hinhAnhFile.isEmpty()) {
            String fileName = saveImage(hinhAnhFile);
            if (fileName != null) {
                monAn.setHinhAnh(fileName);
            }
        }
        monAnService.update(monAn);
        return "redirect:/manage/menu";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable("id") int id) {
        monAnService.deleteById(id);
        return "redirect:/manage/menu";
    }

    @PostMapping("/add")
    public String addDish(@RequestParam("tenMonAn") String tenMonAn,
                          @RequestParam("donGia") double donGia,
                          @RequestParam("loaiMonAn") String loaiMonAn,
                          @RequestParam(value = "hinhAnhFile", required = false) MultipartFile hinhAnhFile) {

        MonAn newMon = new MonAn();
        newMon.setTenMonAn(tenMonAn);
        newMon.setDonGia(donGia);
        newMon.setLoaiMonAn(loaiMonAn);
        newMon.setTrangThai("Đang kinh doanh");
        newMon.setSoLuongDaBan(0);

        String fileName = (hinhAnhFile != null && !hinhAnhFile.isEmpty()) ? saveImage(hinhAnhFile) : "default.jpg";
        newMon.setHinhAnh(fileName);

        monAnService.themMonAn(newMon);
        return "redirect:/manage/menu";
    }

    private List<MonAn> filterByType(List<MonAn> list, String type) {
        return list.stream()
                .filter(mon -> mon.getLoaiMonAn().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    private String saveImage(MultipartFile file) {
        try {
            String uploadDir = "C:/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);
            file.transferTo(saveFile);

            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
