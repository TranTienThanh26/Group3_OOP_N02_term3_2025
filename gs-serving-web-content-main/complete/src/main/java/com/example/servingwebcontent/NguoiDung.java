package com.example.servingwebcontent;

public class NguoiDung {
    private int userID;
    private String email;
    private String password;
    private String role;

    // ✅ Constructor có bắt lỗi - hợp lý nhất
    public NguoiDung(int userID, String email, String password, String role) {
        try {
            if (userID <= 0) throw new IllegalArgumentException("User ID phải lớn hơn 0.");
            if (email == null || !email.contains("@"))
                throw new IllegalArgumentException("Email không hợp lệ.");
            if (password == null || password.length() < 4)
                throw new IllegalArgumentException("Mật khẩu phải có ít nhất 4 ký tự.");
            if (role == null || role.trim().isEmpty())
                throw new IllegalArgumentException("Vai trò không được để trống.");

            this.userID = userID;
            this.email = email;
            this.password = password;
            this.role = role;

        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi tạo NguoiDung: " + e.getMessage());
        } finally {
            System.out.println("Khởi tạo NguoiDung hoàn tất.");
        }
    }

    // ✅ Constructor mặc định
    public NguoiDung() {}

    // ✅ Constructor rút gọn (mặc định role = "user")
    public NguoiDung(int userID, String email, String password) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.role = "user";
    }

    // Getters
    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
