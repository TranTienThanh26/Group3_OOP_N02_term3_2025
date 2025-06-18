package com.example.servingwebcontent;

import java.util.*;

public class ListKHDH {
    ArrayList<KHDH> ht = new ArrayList<KHDH>();

    public ArrayList<KHDH> addObject(KHDH k) {
        ht.add(k);
        return ht;
    }

    // ❗ Phiên bản duy nhất có try-catch-finally
    public void displayList() {
        try {
            for (int i = 0; i < ht.size(); i++) {
                System.out.println("MaKH: " + ht.get(i).maKhachHang);
                System.out.println("MaMonAn: " + ht.get(i).maMonAn);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị danh sách KHDH: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi displayList (instance).");
        }
    }

    // Phiên bản static KHÔNG có try-catch-finally
    public static void displayList(List<KHDH> ht) {
        for (int i = 0; i < ht.size(); i++) {
            System.out.println("MaKH: " + ht.get(i).maKhachHang);
            System.out.println("MaMonAn: " + ht.get(i).maMonAn);
        }
    }

    public void shortList() {
        List<KHDH> list = ht.subList(0, Math.min(2, ht.size())); // an toàn với size < 2
        ListKHDH.displayList(list);
    }

    public ArrayList<KHDH> filterKHDHTradition(String keyword) {
        ArrayList<KHDH> newList = new ArrayList<KHDH>();
        for (KHDH k : ht) {
            if ((k.maKhachHang).contains(keyword) || (k.maMonAn).contains(keyword)) {
                newList.add(k);
            }
        }
        return newList;
    }

    public ArrayList<KHDH> getPaidOrdersByKhachHang(String maKhachHang) {
        ArrayList<KHDH> result = new ArrayList<>();
        for (KHDH k : ht) {
            if (k.maKhachHang.equals(maKhachHang) && k.isPaid) {
                result.add(k);
            }
        }
        return result;
    }

    // Getter trả bản sao của danh sách
    public List<KHDH> getHt() {
        return new ArrayList<>(ht);
    }
}
