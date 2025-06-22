// File: src/main/java/com/example/servingwebcontent/Controller/BanAnController.java
package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.Ban;
import com.example.servingwebcontent.database.BanAiven;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class BanAnController {

    private final BanAiven banAiven = new BanAiven();

    // ✅ 1. Giao diện chính: http://localhost:8080/ban
    @GetMapping("/ban")
    public String hienThiBanAn() {
        return "Admin/BanAn"; // Tệp HTML trong templates/Admin/BanAn.html
    }

    // ✅ 2. API: Lấy danh sách bàn
    @GetMapping("/ban/api")
    @ResponseBody
    public List<Ban> getDanhSachBan() {
        return banAiven.getBanListFromAiven();
    }

    // ✅ 3. API: Đặt bàn
    @PostMapping("/ban/api/datban")
    @ResponseBody
    public ResponseEntity<String> datBan(@RequestBody Map<String, Integer> request) {
        Integer maBan = request.get("maBan");
        if (maBan == null) {
            return ResponseEntity.badRequest().body("Thiếu mã bàn");
        }
        try {
            banAiven.capNhatTrangThai(maBan, "Đã đặt");
            return ResponseEntity.ok("Đặt bàn thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi đặt bàn");
        }
    }

    // ✅ 4. API: Huỷ bàn
    @PostMapping("/ban/api/huyban")
    @ResponseBody
    public ResponseEntity<String> huyBan(@RequestBody Map<String, String> request) {
        String tenBan = request.get("tenBan");
        if (tenBan == null || tenBan.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Thiếu tên bàn");
        }
        try {
            List<Ban> danhSach = banAiven.getBanListFromAiven();
            for (Ban b : danhSach) {
                if (b.getTenBan().equalsIgnoreCase(tenBan)) {
                    banAiven.capNhatTrangThai(b.getMaBan(), "Trống");
                    return ResponseEntity.ok("Đã huỷ bàn thành công");
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy bàn phù hợp");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi huỷ bàn");
        }
    }
}
