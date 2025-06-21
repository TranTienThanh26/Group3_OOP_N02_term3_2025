package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Model.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    // 🛒 Thêm món vào giỏ hàng
    @PostMapping("/cart/add")
    @ResponseBody
    public ResponseEntity<String> addToCart(@RequestBody CartItem item, HttpSession session) {
        if (item.getSoLuong() <= 0 || item.getTenMonAn() == null || item.getDonGia() <= 0) {
            return ResponseEntity.badRequest().body("❌ Dữ liệu món ăn không hợp lệ.");
        }

        List<CartItem> cart = getCartFromSession(session);
        boolean found = false;

        for (CartItem c : cart) {
            if (c.getTenMonAn().equals(item.getTenMonAn())) {
                c.setSoLuong(c.getSoLuong() + item.getSoLuong());
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(item);
        }

        session.setAttribute("cart", cart);
        return ResponseEntity.ok("✅ Đã thêm vào giỏ hàng");
    }

    // 👁️ Hiển thị giỏ hàng
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = getCartFromSession(session);
        int total = cart.stream().mapToInt(CartItem::getThanhTien).sum();

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "Customer/Cart";
    }

    // 🔢 Tổng số lượng món
    @GetMapping("/cart/total")
    @ResponseBody
    public int getCartTotal(HttpSession session) {
        List<CartItem> cart = getCartFromSession(session);
        return cart.stream().mapToInt(CartItem::getSoLuong).sum();
    }

    // ✏️ Cập nhật số lượng món
    @PostMapping("/cart/update")
    public String updateItem(
            @RequestParam String tenMonAn,
            @RequestParam int soLuong,
            HttpSession session
    ) {
        List<CartItem> cart = getCartFromSession(session);
        for (CartItem item : cart) {
            if (item.getTenMonAn().equals(tenMonAn)) {
                item.setSoLuong(soLuong);
                break;
            }
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    // ❌ Xoá món khỏi giỏ hàng
    @PostMapping("/cart/remove")
    public String removeItem(
            @RequestParam String tenMonAn,
            HttpSession session
    ) {
        List<CartItem> cart = getCartFromSession(session);
        cart.removeIf(item -> item.getTenMonAn().equals(tenMonAn));
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    // 📦 Helper: Lấy danh sách giỏ hàng từ session
    private List<CartItem> getCartFromSession(HttpSession session) {
        Object obj = session.getAttribute("cart");
        List<CartItem> cart = new ArrayList<>();

        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                if (o instanceof CartItem cartItem) {
                    cart.add(cartItem);
                }
            }
        }
        return cart;
    }
}
