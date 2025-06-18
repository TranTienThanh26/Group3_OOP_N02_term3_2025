package com.example.servingwebcontent;

import java.util.ArrayList;

public class HoaDonList {
    ArrayList<HoaDon> st = new ArrayList<HoaDon>();

    public ArrayList<HoaDon> addHoaDon(HoaDon hoaDon) {
        st.add(hoaDon);
        return st;
    }

    public ArrayList<HoaDon> getEditHoaDOn(int idHoaDon, int tongtien) {
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getIdHoaDon() == idHoaDon) {
                System.out.print("true");
                st.get(i).setTongtien(tongtien);
            }
        }
        return st;
    }

    public ArrayList<HoaDon> getDeleteHoaDon(int idHoaDon) {
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getIdHoaDon() == idHoaDon) {
                st.remove(i);
                break; // tránh ConcurrentModificationException
            }
        }
        return st;
    }

    // ❗Phương thức này được thêm try-catch-finally
    public void printHoaDonList() {
        try {
            int len = st.size();
            for (int i = 0; i < len; i++) {
                System.out.println("Mã Hóa Đơn: " + st.get(i).getIdHoaDon() +
                                   " Tổng Tiền Ăn: " + st.get(i).getTongtien());
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi in danh sách hóa đơn: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi printHoaDonList.");
        }
    }
}
