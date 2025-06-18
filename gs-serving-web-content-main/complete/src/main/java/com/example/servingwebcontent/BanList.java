package com.example.servingwebcontent;

import java.util.ArrayList;

public class BanList {

    ArrayList<Ban> st = new ArrayList<Ban>();

    public ArrayList<Ban> addBan(Ban ba) {
        st.add(ba);
        return st;
    }

    public ArrayList<Ban> getEditBan(String tenBan, int maBan) {
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getMaBan() == maBan) {
                System.out.print("true");
                st.get(i).setTenBan(tenBan);
            }
        }
        return st;
    }

    public ArrayList<Ban> getDeleteBan(int maBan) {
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getMaBan() == maBan) {
                st.remove(i);
                break; // tránh lỗi khi xóa phần tử trong vòng lặp
            }
        }
        return st;
    }

    // ❗ Phương thức này được thêm try-catch-finally
    public void printBanList() {
        try {
            int len = st.size();
            for (int i = 0; i < len; i++) {
                System.out.println("maBan: " + st.get(i).getMaBan() +
                                   " TenBan: " + st.get(i).getTenBan());
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi in danh sách bàn: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi printBanList.");
        }
    }
}
