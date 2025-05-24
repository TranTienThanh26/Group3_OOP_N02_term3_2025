public class testNhanVien {
    public static void test() {
        NhanVien nv = new NhanVien(1, "Nguyen Van A", "2023-01-01", "0123456789", "Nhan vien", 2, "Dang lam");
        System.out.println("ID: " + nv.getId_NV());
        System.out.println("Tên: " + nv.getTenNV());
        System.out.println("Ngày vào làm: " + nv.getNgayVL());
        System.out.println("SĐT: " + nv.getSdt());
        System.out.println("Chức vụ: " + nv.getChucvu());
        System.out.println("ID quản lý: " + nv.getId_NQL());
        System.out.println("Tình trạng: " + nv.getTinhTrang());
    }
}
