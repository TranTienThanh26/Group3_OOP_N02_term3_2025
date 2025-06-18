package com.example.servingwebcontent;

import java.util.ArrayList;

public class MonAnList {
    ArrayList<MonAn> st = new ArrayList<MonAn>();

    public ArrayList<MonAn> addMonAn(MonAn monAn) {
        st.add(monAn);
        return st;
    }

    public ArrayList<MonAn> getEditMonAn(String tenMonAn, int maMonAn) {
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getMaMonAn() == maMonAn) {
                System.out.print("true");
                st.get(i).setTenMonAn(tenMonAn);
            }
        }
        return st;
    }

    public ArrayList<MonAn> getDeleteMonAn(int maMonAn) {
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getMaMonAn() == maMonAn) {
                st.remove(i);
                break; // tránh lỗi ConcurrentModificationException
            }
        }
        return st;
    }

    // ❗ CHỈ phương thức này có try-catch-finally
    public void printMonAnList() {
        try {
            int len = st.size();
            for (int i = 0; i < len; i++) {
                System.out.println("Mã Món Ăn: " + st.get(i).getMaMonAn() +
                                   " Tên Món Ăn: " + st.get(i).getTenMonAn());
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi in danh sách món ăn: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi printMonAnList.");
        }
    }
}
