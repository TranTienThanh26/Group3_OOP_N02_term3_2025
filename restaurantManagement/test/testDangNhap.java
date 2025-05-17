
public class TestDangNhap {
    public static void main(String[] args) {
        // Kiểm tra constructor có tham số
        DangNhap dn1 = new DangNhap("trungsang@example.com", "123456");
        System.out.println("Email: " + dn1.getEmail()); 
        System.out.println("Password: " + dn1.getPassword()); 

        // Kiểm tra constructor không tham số + setter
        DangNhap dn2 = new DangNhap();
        dn2.setEmail("dungsang@example.com");
        dn2.setPassword("admin123");

        // In ra để kiểm tra getter hoạt động đúng
        System.out.println("Email: " + dn2.getEmail()); 
        System.out.println("Password: " + dn2.getPassword()); 
    }
}
