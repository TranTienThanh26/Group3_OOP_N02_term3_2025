package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.CTHD;
import com.example.servingwebcontent.Model.CartItem;
import com.example.servingwebcontent.Model.HoaDon;
import com.example.servingwebcontent.Model.NguoiDung;
import com.example.servingwebcontent.database.BanAiven;
import com.example.servingwebcontent.database.CTHDAiven;
import com.example.servingwebcontent.database.HoaDonAiven;
import com.example.servingwebcontent.database.KhachHangAiven;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HoaDonController {

    private final HoaDonAiven hoaDonDB = new HoaDonAiven();
    private final BanAiven banAiven = new BanAiven();
    private final CTHDAiven cthdAiven = new CTHDAiven();

    // ✅ 1. Hiển thị danh sách hóa đơn
    @GetMapping("/hoadon")
    public String hienThiHoaDon(Model model, HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("user");
        if (nguoiDung == null) return "redirect:/login";

        int idKH = KhachHangAiven.layMaKhachHangTheoUserID(nguoiDung.getUserID());
        if (idKH == -1) return "redirect:/login?error=nokhachhang";

        List<HoaDon> danhSach = hoaDonDB.getDanhSachHoaDonKhongTrung()
                .stream()
                .filter(hd -> hd.getIdKH() == idKH)
                .collect(Collectors.toCollection(ArrayList::new));

        Set<Integer> idsDaCo = danhSach.stream()
                .map(HoaDon::getIdHoaDon)
                .collect(Collectors.toSet());

        Object dsTamRaw = session.getAttribute("dsHoaDonTam");
        List<Integer> idHoaDonTam = new ArrayList<>();

        if (dsTamRaw instanceof List<?>) {
            for (Object obj : (List<?>) dsTamRaw) {
                if (obj instanceof HoaDon hdSession) {
                    int id = hdSession.getIdHoaDon();

                    if (idsDaCo.contains(id)) {
                        for (HoaDon hdDB : danhSach) {
                            if (hdDB.getIdHoaDon() == id && hdSession.getDsMonAn() != null && !hdSession.getDsMonAn().isEmpty()) {
                                hdDB.setDsMonAn(hdSession.getDsMonAn());
                                idHoaDonTam.add(id);
                                break;
                            }
                        }
                    } else {
                        if (hdSession.getDsMonAn() == null) {
                            hdSession.setDsMonAn(new ArrayList<>());
                        }
                        danhSach.add(hdSession);
                        idsDaCo.add(id);
                        if (!hdSession.getDsMonAn().isEmpty()) {
                            idHoaDonTam.add(id);
                        }
                    }
                }
            }
        }

        for (HoaDon hd : danhSach) {
            if (hd.getDsMonAn() == null) {
                hd.setDsMonAn(new ArrayList<>());
            }
        }

        model.addAttribute("dsHoaDon", danhSach);
        model.addAttribute("dsHoaDonTamID", idHoaDonTam);
        model.addAttribute("dsHoaDonJson", danhSach);

        return "Customer/HoaDon";
    }

    // ✅ 2. Tạo hóa đơn
    @PostMapping("/taohoadon")
    public String taoHoaDon(
            HttpSession session,
            @RequestParam("tenBan") String tenBan,
            @RequestParam("cartJson") String cartJson
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<CartItem> cart = Arrays.asList(mapper.readValue(cartJson, CartItem[].class));
            if (cart == null || cart.isEmpty()) return "redirect:/cart?error=empty";

            int idBan = banAiven.getMaBanTheoTen(tenBan);
            if (idBan == -1) return "redirect:/cart?error=ban";

            NguoiDung nguoiDung = (NguoiDung) session.getAttribute("user");
            if (nguoiDung == null) return "redirect:/login";

            int idKH = KhachHangAiven.layMaKhachHangTheoUserID(nguoiDung.getUserID());
            if (idKH == -1) return "redirect:/cart?error=nokhachhang";

            int tongTien = cart.stream().mapToInt(CartItem::getThanhTien).sum();
            String ngayHD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            HoaDon hoaDon = new HoaDon(0, idKH, idBan, ngayHD, tongTien, tongTien, "Chưa thanh toán");
            hoaDon.setDsMonAn(cart);

            int idMoi = hoaDonDB.themHoaDon(hoaDon);
            if (idMoi > 0) hoaDon.setIdHoaDon(idMoi);

            List<HoaDon> dsTam = new ArrayList<>();
            Object old = session.getAttribute("dsHoaDonTam");
            if (old instanceof List<?>) {
                for (Object o : (List<?>) old) {
                    if (o instanceof HoaDon) dsTam.add((HoaDon) o);
                }
            }
            dsTam.add(hoaDon);
            session.setAttribute("dsHoaDonTam", dsTam);

            return "redirect:/hoadon?success";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/cart?error=exception";
        }
    }

    // ✅ 3. Xóa hóa đơn
    @GetMapping("/hoadon/xoa/{id}")
    public String xoaHoaDon(@PathVariable("id") int id, HttpSession session) {
        hoaDonDB.xoaHoaDon(id);

        Object raw = session.getAttribute("dsHoaDonTam");
        if (raw instanceof List<?>) {
            List<HoaDon> tam = new ArrayList<>();
            for (Object o : (List<?>) raw) {
                if (o instanceof HoaDon hd && hd.getIdHoaDon() != id) {
                    tam.add(hd);
                }
            }
            session.setAttribute("dsHoaDonTam", tam);
        }

        return "redirect:/hoadon?deleted";
    }

    // ✅ 4. Thanh toán hóa đơn + lưu chi tiết
    @GetMapping("/hoadon/thanhtoan")
    public String thanhToanHoaDon(@RequestParam("id") int id, HttpSession session, Model model) {
        HoaDon hd = hoaDonDB.timHoaDonTheoId(id);
        if (hd == null) return "redirect:/hoadon?error=notfound";
    
        // ✅ In ra ID hóa đơn đang xử lý
        System.out.println("➡️ Thanh toán hóa đơn ID: " + id);
    
        // Lấy danh sách món ăn từ session
        Object tamList = session.getAttribute("dsHoaDonTam");
        if (tamList instanceof List<?>) {
            for (Object o : (List<?>) tamList) {
                if (o instanceof HoaDon h && h.getIdHoaDon() == id) {
                    hd.setDsMonAn(h.getDsMonAn());
                    break;
                }
            }
        }
    
        hd.setTrangthai("Thành công");
        hoaDonDB.capNhatHoaDon(hd);
    
        System.out.println("✅ Cập nhật trạng thái hóa đơn thành công. Bắt đầu lưu chi tiết...");
    
        for (CartItem item : hd.getDsMonAn()) {
            int idMonAn = item.getMaMonAn();
            String ten = item.getTenMonAn();
            int sl = item.getSoLuong();
            int gia = item.getDonGia();
            int thanhTien = item.getThanhTien();
    
            // ✅ In chi tiết từng món
            System.out.printf("🧾 CTHD -> HD: %d | MA: %d | Tên: %s | SL: %d | ĐG: %d | TT: %d\n",
                    id, idMonAn, ten, sl, gia, thanhTien);
    
            CTHD cthd = new CTHD(id, idMonAn, ten, sl, gia, thanhTien);
            cthdAiven.insertCTHD(cthd);
        }
    
        model.addAttribute("hoaDon", hd);
        return "Customer/CTHD";
}
    }
    