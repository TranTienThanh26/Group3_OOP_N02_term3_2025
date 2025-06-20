package com.example.servingwebcontent.Model;
import java.util.*;

public class ListKHDH {
    private ArrayList<KHDH> ht = new ArrayList<KHDH>();

    // ✅ Khối bắt lỗi duy nhất, hợp lý
    public ArrayList<KHDH> addObject(KHDH k) {
        try {
            if (k == null) {
                throw new IllegalArgumentException("Đối tượng KHDH không được null.");
            }
            if (k.getMaKhachHang() == null || k.getMaKhachHang().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã khách hàng không hợp lệ.");
            }
            if (k.getMaMonAn() == null || k.getMaMonAn().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã món ăn không hợp lệ.");
            }

            ht.add(k);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thêm đối tượng KHDH: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi thêm đối tượng KHDH: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất xử lý thêm đối tượng KHDH.");
        }

        return ht;
    }

    public void displayList() {
        for (KHDH kh : ht) {
            System.out.println("MaKH: " + kh.getMaKhachHang());
            System.out.println("MaMonAn: " + kh.getMaMonAn());
        }
    }

    public static void displayList(List<KHDH> ht) {
        for (KHDH kh : ht) {
            System.out.println("MaKH: " + kh.getMaKhachHang());
            System.out.println("MaMonAn: " + kh.getMaMonAn());
        }
    }

    public void shortList() {
        List<KHDH> list = ht.subList(0, Math.min(2, ht.size())); // tránh IndexOutOfBounds
        displayList(list);
    }

    public ArrayList<KHDH> filterKHDHTradition(String keyword) {
        ArrayList<KHDH> newList = new ArrayList<KHDH>();

        for (KHDH k : ht) {
            if (k.getMaKhachHang().contains(keyword) || k.getMaMonAn().contains(keyword)) {
                newList.add(k);
            }
        }
        return newList;
    }

    public ArrayList<KHDH> getPaidOrdersByKhachHang(String maKhachHang) {
        ArrayList<KHDH> result = new ArrayList<>();

        for (KHDH k : ht) {
            if (k.getMaKhachHang().equals(maKhachHang) && k.isPaid()) {
                result.add(k);
            }
        }

        return result;
    }

    public List<KHDH> getHt() {
        return new ArrayList<>(ht);
    }
}
