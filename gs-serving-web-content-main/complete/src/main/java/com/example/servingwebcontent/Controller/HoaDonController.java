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

    // 1. Hiển thị danh sách hóa đơn
    @GetMapping("/hoadon")
    public String hienThiHoaDon(Model model, HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("user");
        if (nguoiDung == null) return "redirect:/login";

        int idKH = KhachHangAiven.layMaKhachHangTheoUserID(nguoiDung.getUserID());
        if (idKH == -1) return "redirect:/login?error=nokhachhang";

        // Lấy danh sách hóa đơn của khách hàng
        List<HoaDon> danhSach = hoaDonDB.getDanhSachHoaDonKhongTrung()
                .stream()
                .filter(hd -> hd.getIdKH() == idKH)
                .collect(Collectors.toCollection(ArrayList::new));

        // Lấy danh sách hóa đơn tạm trong session (chưa thanh toán)
        Object dsTamRaw = session.getAttribute("dsHoaDonTam");
        List<HoaDon> dsHoaDonTam = new ArrayList<>();
        if (dsTamRaw instanceof List<?>) {
            for (Object obj : (List<?>) dsTamRaw) {
                if (obj instanceof HoaDon hdSession) {
                    dsHoaDonTam.add(hdSession);
                }
            }
        }

        // Map idHoaDon -> HoaDon trong session để tra cứu nhanh
        Map<Integer, HoaDon> mapTam = dsHoaDonTam.stream()
                .collect(Collectors.toMap(HoaDon::getIdHoaDon, hd -> hd));

        // Với từng hóa đơn, nếu trạng thái "Thành công" thì lấy chi tiết món ăn từ CTHD,
        // còn chưa thanh toán thì lấy dsMonAn trong session
        for (HoaDon hd : danhSach) {
            if ("Thành công".equalsIgnoreCase(hd.getTrangthai())) {
                // Lấy chi tiết món ăn từ bảng CTHD
                List<CTHD> cthdList = cthdAiven.getDsMonAnTheoHoaDon(hd.getIdHoaDon());
                List<CartItem> dsMonAn = cthdList.stream().map(cthd -> {
                    CartItem item = new CartItem();
                    item.setMaMonAn(cthd.getID_MonAn());
                    item.setTenMonAn(cthd.getTenMonAn());
                    item.setSoLuong(cthd.getSoluong());
                    item.setDonGia(cthd.getDonGia());
                    item.setThanhTien(cthd.getThanhTien());
                    return item;
                }).collect(Collectors.toList());
                hd.setDsMonAn(dsMonAn);
            } else {
                // Nếu chưa thanh toán, lấy danh sách món ăn từ session nếu có
                if (mapTam.containsKey(hd.getIdHoaDon())) {
                    HoaDon hdTam = mapTam.get(hd.getIdHoaDon());
                    if (hdTam.getDsMonAn() != null && !hdTam.getDsMonAn().isEmpty()) {
                        hd.setDsMonAn(hdTam.getDsMonAn());
                    }
                }
            }
            if (hd.getDsMonAn() == null) {
                hd.setDsMonAn(new ArrayList<>());
            }
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonHoaDon = mapper.writeValueAsString(danhSach);

            model.addAttribute("dsHoaDon", danhSach);
            model.addAttribute("jsonDsHoaDon", jsonHoaDon);

            return "Customer/HoaDon";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/hoadon?error=json";
        }
    }

    // 2. Tạo hóa đơn
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
            if (idMoi > 0) {
                hoaDon.setIdHoaDon(idMoi);

                // Lưu chi tiết món ăn vào bảng CTHD
                for (CartItem item : cart) {
                    CTHD cthd = new CTHD(idMoi, item.getMaMonAn(), item.getTenMonAn(),
                            item.getSoLuong(), item.getDonGia(), item.getThanhTien());
                    cthdAiven.insertCTHD(cthd);
                }
            }

            // Lưu hóa đơn tạm trong session
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

    // 3. Xóa hóa đơn
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

    // 4. Thanh toán hóa đơn
    @GetMapping("/hoadon/thanhtoan")
    public String thanhToanHoaDon(@RequestParam("id") int id, HttpSession session, Model model) {
        HoaDon hd = hoaDonDB.timHoaDonTheoId(id);
        if (hd == null) return "redirect:/hoadon?error=notfound";

        System.out.println("➡️ Thanh toán hóa đơn ID: " + id);

        // Lấy dsMonAn từ session (dsHoaDonTam) nếu có
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

        System.out.println("✅ Cập nhật trạng thái hóa đơn thành công.");

        // Cập nhật lại chi tiết hóa đơn trong bảng CTHD (có thể thêm hoặc cập nhật nếu cần)
        // Nếu bạn muốn xóa hết chi tiết cũ rồi thêm lại, có thể gọi xóa rồi insert
        // Đây là ví dụ xóa rồi thêm lại:
        cthdAiven.deleteCTHDTheoHoaDon(id);  // Bạn cần thêm hàm này trong CTHDAiven để xóa chi tiết cũ
        for (CartItem item : hd.getDsMonAn()) {
            CTHD cthd = new CTHD(id, item.getMaMonAn(), item.getTenMonAn(),
                    item.getSoLuong(), item.getDonGia(), item.getThanhTien());
            cthdAiven.insertCTHD(cthd);
        }

        // Reset giỏ hàng và bàn chọn trong session nếu có
        session.removeAttribute("cart");
        session.removeAttribute("selectedTable");

        // Thêm tên người dùng vào model để hiển thị trong giao diện
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("user");
        if (nguoiDung != null) {
            model.addAttribute("tenNguoiDung", nguoiDung.getHoTen());
        }
        model.addAttribute("hoaDon", hd);
        return "Customer/CTHD";
    }
}
