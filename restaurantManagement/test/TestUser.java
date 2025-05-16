public class TestUser {
    public static void main(String[] args) {
        user.setUser("trungsang", "Ha Noi");

        System.out.println("Username: " + user.getUser());       // mong đợi: trungsang
        System.out.println("Address: " + user.getAddress());     // mong đợi: Ha Noi
    }
}
