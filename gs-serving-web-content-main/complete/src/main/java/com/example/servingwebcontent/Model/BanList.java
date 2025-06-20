package com.example.servingwebcontent.Model;

import java.util.ArrayList;

public class BanList {

    ArrayList<Ban> st = new ArrayList<Ban>();

    public ArrayList<Ban> addBan(Ban ba) {
        try {
            if (ba == null) {
                throw new IllegalArgumentException("Đối tượng bàn không được null.");
            }
            if (ba.getTenBan() == null || ba.getTenBan().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên bàn không hợp lệ.");
            }

            st.add(ba);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thêm bàn: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi thêm bàn: " + e.getMessage());
        } finally {
            System.out.println("Kết thúc xử lý thêm bàn.");
        }

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
                break; // Thêm break để tránh ConcurrentModificationException
            }
        }

        return st;
    }

    public void printBanList() {
        int len = st.size();
        for (int i = 0; i < len; i++) {
            System.out.println("maBan: " + st.get(i).getMaBan() + " TenBan: " + st.get(i).getTenBan());
        }
    }
}
