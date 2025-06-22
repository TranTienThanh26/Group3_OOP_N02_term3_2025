package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.Ban;
import com.example.servingwebcontent.database.BanAiven;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BanAnController {

    private final BanAiven banAiven = new BanAiven();

    // ✅ 1. Giao diện chính
    @GetMapping("/ban")
    public String hienThiBanAn() {
        return "Admin/BanAn"; // Giao diện HTML: templates/Admin/BanAn.html
    }

    // ✅ 2. API: Lấy toàn bộ danh sách bàn
    @GetMapping("/ban/api")
    @ResponseBody
    public List<Ban> getDanhSachBan() {
        return banAiven.getBanListFromAiven();
    }

    // ✅ 3. API: Thêm bàn mới
    @PostMapping("/ban/api/them")
    @ResponseBody
    public ResponseEntity<String> themBan(@RequestBody Ban banMoi) {
        try {
            banAiven.themBan(banMoi);
            return ResponseEntity.ok("✅ Thêm bàn thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Lỗi khi thêm bàn");
        }
    }

    // ✅ 4. API: Sửa thông tin bàn (tên + trạng thái)
    @PutMapping("/ban/api/sua")
    @ResponseBody
    public ResponseEntity<String> suaBan(@RequestBody Ban banCapNhat) {
        try {
            banAiven.updateBan(banCapNhat);
            return ResponseEntity.ok("✅ Cập nhật bàn thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Lỗi khi cập nhật bàn");
        }
    }

    // ✅ 5. API: Xoá bàn
    @DeleteMapping("/ban/api/xoa/{maBan}")
    @ResponseBody
    public ResponseEntity<String> xoaBan(@PathVariable int maBan) {
        try {
            banAiven.deleteById(maBan);
            return ResponseEntity.ok("🗑️ Đã xoá bàn thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Lỗi khi xoá bàn");
        }
    }

    // ✅ 6. API: Tìm kiếm bàn theo tên (tuỳ chọn nếu frontend cần)
    @GetMapping("/ban/api/timkiem")
    @ResponseBody
    public ResponseEntity<List<Ban>> timKiemBan(@RequestParam("keyword") String keyword) {
        try {
            List<Ban> ketQua = banAiven.timBanTheoTen(keyword);
            return ResponseEntity.ok(ketQua);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}