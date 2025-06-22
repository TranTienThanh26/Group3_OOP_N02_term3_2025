// File: src/main/java/com/example/servingwebcontent/Controller/BanAnViewController.java
package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BanAnViewController {

    @GetMapping("/manage/orders")
    public String hienThiBanAn() {
        return "Admin/BanAn"; // ✅ View trong thư mục templates/Admin/BanAn.html
    }
}
