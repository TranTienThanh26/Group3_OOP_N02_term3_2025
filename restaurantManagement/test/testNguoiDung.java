public class testNguoiDung {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        NguoiDung nd1 = new NguoiDung(1, "trunsan@gmail.com", "thanh123", "khach hang");

        System.out.println("NguoiDungID: " + nd1.getUserID());
        System.out.println("Email: " + nd1.getEmail());
        System.out.println("Password: " + nd1.getPassword());
        System.out.println("Role: " + nd1.getRole());
    }
}