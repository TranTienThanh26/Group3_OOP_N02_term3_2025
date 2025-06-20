package com.example.servingwebcontent.Model;

public class CTHD {

    private int ID_HD;
    private int ID_MonAn;
    private String tenMonAn;
    private int soluong;
    private int thanhTien;

    public int getID_HD() {
        return ID_HD;
    }

    public int getID_MonAn() {
        return ID_MonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public int getSoluong() {
        return soluong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public CTHD() {
    }

    public CTHD(int ID_HD, int ID_MonAn, String tenMonAn, int soluong, int thanhTien) {
        try {
            if (ID_HD <= 0) {
                throw new IllegalArgumentException("ID hóa đơn phải > 0.");
            }
            if (ID_MonAn <= 0) {
                throw new IllegalArgumentException("ID món ăn phải > 0.");
            }
            if (tenMonAn == null || tenMonAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên món ăn không được để trống.");
            }
            if (soluong <= 0) {
                throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
            }
            if (thanhTien < 0) {
                throw new IllegalArgumentException("Thành tiền không được âm.");
            }

            this.ID_HD = ID_HD;
            this.ID_MonAn = ID_MonAn;
            this.tenMonAn = tenMonAn;
            this.soluong = soluong;
            this.thanhTien = thanhTien;

        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi khởi tạo CTHD: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo đối tượng CTHD hoàn tất.");
        }
    }
}
