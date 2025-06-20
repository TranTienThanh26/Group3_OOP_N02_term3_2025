package com.example.servingwebcontent.Model;

import java.util.ArrayList;

public class HoaDonList {
    ArrayList<HoaDon> st = new ArrayList<HoaDon>();

    // ✅ Khối try-catch-finally đặt duy nhất tại đây
    public ArrayList<HoaDon> addHoaDon(HoaDon hoaDon) {
        try {
            if (hoaDon == null) {
                throw new IllegalArgumentException("Đối tượng hóa đơn không được null.");
            }
            if (hoaDon.getIdHoaDon() <= 0) {
                throw new IllegalArgumentException("ID hóa đơn không được để trống.");
            }
            st.add(hoaDon);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi thêm hóa đơn: " + e.getMessage());
        } finally {
            System.out.println("Xử lý thêm hóa đơn đã hoàn tất.");
        }

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
                break; // thêm break để tránh lỗi ConcurrentModificationException
            }
        }
        return st;
    }

    public void printHoaDonList() {
        for (int i = 0; i < st.size(); i++) {
            System.out.println("Mã Hóa Đơn: " + st.get(i).getIdHoaDon() + " | Tổng Tiền Ăn: " + st.get(i).getTongtien());
        }
    }
}
