package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.Ban;
import com.example.servingwebcontent.database.BanAiven;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BanController {

    private final BanAiven banAiven = new BanAiven();

    // ✅ Lấy danh sách bàn
    @GetMapping("/api/ban")
    public List<Ban> getDanhSachBan() {
        return banAiven.getBanListFromAiven();
    }

    // ✅ Đặt bàn: cập nhật trạng thái "Đã đặt"
    @PostMapping("/api/ban/datban")
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

    // ✅ Huỷ bàn: cập nhật lại trạng thái "Trống"
    @PostMapping("/api/ban/huyban")
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
