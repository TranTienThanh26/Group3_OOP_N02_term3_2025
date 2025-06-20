package com.example.servingwebcontent.Model;

import java.util.ArrayList;

public class MonAnList {
    private ArrayList<MonAn> st = new ArrayList<MonAn>();

    // ✅ Khối bắt lỗi duy nhất và hợp lý
    public ArrayList<MonAn> addMonAn(MonAn monAn) {
        try {
            if (monAn == null) {
                throw new IllegalArgumentException("Món ăn không được null.");
            }
            if (monAn.getMaMonAn() <= 0) {
                throw new IllegalArgumentException("Mã món ăn phải lớn hơn 0.");
            }
            if (monAn.getTenMonAn() == null || monAn.getTenMonAn().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên món ăn không được để trống.");
            }

            st.add(monAn);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thêm món ăn: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi thêm món ăn: " + e.getMessage());
        } finally {
            System.out.println("Xử lý thêm món ăn đã hoàn tất.");
        }

        return st;
    }

    public ArrayList<MonAn> getEditMonAn(String tenMonAn, int maMonAn) {
        for (MonAn mon : st) {
            if (mon.getMaMonAn() == maMonAn) {
                System.out.println("true");
                mon.setTenMonAn(tenMonAn);
                break;
            }
        }
        return st;
    }

    public ArrayList<MonAn> getDeleteMonAn(int maMonAn) {
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getMaMonAn() == maMonAn) {
                st.remove(i);
                break; // tránh lỗi khi xóa phần tử trong vòng lặp
            }
        }
        return st;
    }

    public void printMonAnList() {
        for (MonAn mon : st) {
            System.out.println("Mã Món Ăn: " + mon.getMaMonAn() + " | Tên Món Ăn: " + mon.getTenMonAn());
        }
    }

    // Getter trả về bản sao để bảo vệ dữ liệu gốc
    public ArrayList<MonAn> getDanhSach() {
        return new ArrayList<>(st);
    }
}
