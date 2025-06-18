package com.example.servingwebcontent;

public class NguoiDung {
    private int userID;
    private String email;
    private String password;
    private String role;

    // Constructors
    public NguoiDung() {}

    public NguoiDung(int userID, String tenHienThi, String email, String password, String role) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.role = role;
    }

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

    // ❗ Setter duy nhất có try-catch-finally
    public void setEmail(String email) {
        try {
            this.email = email;
        } catch (Exception e) {
            System.err.println("Lỗi khi set email: " + e.getMessage());
        } finally {
            System.out.println("Đã gọi setEmail.");
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
