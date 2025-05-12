public class TestNguyenLieu {
    public static void main(String[] args) {
        // Khởi tạo đối tượng nguyên liệu bằng constructor có tham số
        NguyenLieu nl = new NguyenLieu(1, "Đường", 15000, "kg");

        // In ra thông tin để kiểm tra
        System.out.println("ID: " + nl.getId());
        System.out.println("Tên nguyên liệu: " + nl.getTenNL());
        System.out.println("Đơn giá: " + nl.getDonGia());
        System.out.println("Đơn vị tính: " + nl.getDvt());

        // Thay đổi một số giá trị
        nl.setTenNL("Muối");
        nl.setDonGia(12000);

        // In lại thông tin sau khi thay đổi
        System.out.println("\nSau khi cập nhật:");
        System.out.println("Tên nguyên liệu: " + nl.getTenNL());
        System.out.println("Đơn giá: " + nl.getDonGia());
    }
}
