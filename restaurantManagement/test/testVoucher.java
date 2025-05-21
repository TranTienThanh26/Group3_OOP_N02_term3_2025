public class testVoucher {
    public static void main(String[] args) {
        // Tạo đối tượng Voucher
        Voucher voucher = new Voucher();

        // Gán giá trị cho voucher
        voucher.setMa("V123");
        voucher.setMoTa("Giảm giá 20% cho đồ uống");
        voucher.setPhanTramGiam(20);
        voucher.setLoaiMenu("Đồ uống");
        voucher.setSoLuong(100);
        voucher.setDiemDoi(50);

        // In ra thông tin voucher
        System.out.println("=== THÔNG TIN VOUCHER ===");
        System.out.println("Mã: " + voucher.getMa());
        System.out.println("Mô tả: " + voucher.getMoTa());
        System.out.println("Phần trăm giảm: " + voucher.getPhanTramGiam() + "%");
        System.out.println("Loại menu áp dụng: " + voucher.getLoaiMenu());
        System.out.println("Số lượng: " + voucher.getSoLuong());
        System.out.println("Điểm đổi: " + voucher.getDiemDoi());
    }
    
}

