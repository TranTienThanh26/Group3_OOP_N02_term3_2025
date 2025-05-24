public class NguoiDung {
    private int userID;
    private String email;
    private String password;
    private String role;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public NguoiDung() {}

    public NguoiDung(int userID, String email, String password, String role) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public NguoiDung(int userID, String email, String password) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.role = "user"; // hoặc giá trị mặc định khác nếu bạn muốn
    }
}