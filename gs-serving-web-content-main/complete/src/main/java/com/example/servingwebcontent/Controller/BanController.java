package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.Ban;
import com.example.servingwebcontent.database.BanAiven;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ban")
@CrossOrigin(origins = "*") // Cho phép gọi API từ frontend khác
public class BanController {

    private final BanAiven banAiven = new BanAiven();

    // ✅ Lấy danh sách tất cả bàn
    @GetMapping
    public List<Ban> getDanhSachBan() {
        return banAiven.getBanListFromAiven();
    }

    // ✅ Đặt bàn: cập nhật trạng thái "\u0110\u00e3 \u0111\u1eb7t"
    @PostMapping("/datban")
    public ResponseEntity<String> datBan(@RequestBody Map<String, Integer> request) {
        Integer maBan = request.get("maBan");

        if (maBan == null) {
            return ResponseEntity.badRequest().body("Thi\u1ebfu m\u00e3 b\u00e0n");
        }

        try {
            banAiven.capNhatTrangThai(maBan, "\u0110\u00e3 \u0111\u1eb7t");
            return ResponseEntity.ok("\u2705 \u0110\u1eb7t b\u00e0n th\u00e0nh c\u00f4ng");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\u274c L\u1ed7i khi \u0111\u1eb7t b\u00e0n");
        }
    }

    // ✅ Hu\u1ef7 b\u00e0n theo t\u00ean: c\u1eadp nh\u1eadt tr\u1ea1ng th\u00e1i v\u1ec1 "Tr\u1ed1ng"
    @PostMapping("/huyban")
    public ResponseEntity<String> huyBan(@RequestBody Map<String, String> request) {
        String tenBan = request.get("tenBan");

        if (tenBan == null || tenBan.isEmpty()) {
            return ResponseEntity.badRequest().body("Thi\u1ebfu t\u00ean b\u00e0n");
        }

        try {
            List<Ban> danhSach = banAiven.getBanListFromAiven();
            for (Ban b : danhSach) {
                if (tenBan.equalsIgnoreCase(b.getTenBan())) {
                    banAiven.capNhatTrangThai(b.getMaBan(), "Tr\u1ed1ng");
                    return ResponseEntity.ok("\u2705 \u0110\u00e3 hu\u1ef7 b\u00e0n: " + tenBan);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("\u274c Kh\u00f4ng t\u00ecm th\u1ea5y b\u00e0n: " + tenBan);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\u274c L\u1ed7i khi hu\u1ef7 b\u00e0n");
        }
    }
}
