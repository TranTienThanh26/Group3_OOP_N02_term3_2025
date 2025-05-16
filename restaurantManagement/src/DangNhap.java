

// Model Thông tin đăng nhập (Email, mật khẩu)
public class DangNhap {

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

    public DangNhap() {
    }

    public DangNhap(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    private String email; //email
    private String password; // Mật khẩu
    
}
