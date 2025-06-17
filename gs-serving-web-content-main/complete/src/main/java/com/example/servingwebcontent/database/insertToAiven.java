package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException; // Import SQLException để xử lý lỗi tốt hơn
import java.util.Random;

import com.example.servingwebcontent.NguoiDung; // Sử dụng lớp NguoiDung của bạn

public class insertToAiven {

    public void insertNguoiDungToDb(NguoiDung nguoiDung) { // Đổi tên phương thức và tham số
        Connection conn = null;

        try {
            // 1. Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Thiết lập kết nối
            // Loại bỏ "sqluser", "password" vì thông tin xác thực đã có trong URL
            conn = DriverManager.getConnection(
                    "jdbc:mysql://avnadmin:AVNS_HNm9Mr2leXuYSrqITaj@mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED");

            // 3. Xử lý userID (nếu userID được tự sinh trong DB thì bỏ đoạn này)
            // Nếu userID tự tăng trong DB, bạn không cần tạo Random ID ở đây.
            // Nếu không, hãy tạo một ID duy nhất hơn (ví dụ: Random lớn hơn, hoặc UUID).
            if (nguoiDung.getUserID() == 0) { // Giả định 0 là chưa set ID
                Random rand = new Random();
                nguoiDung.setUserID(rand.nextInt(1000000)); // Tăng phạm vi để tránh trùng lặp
            }

            // 4. Chuẩn bị và thực thi câu lệnh INSERT
            // Cập nhật câu lệnh SQL để bao gồm 'email', 'password', và 'role'
            // Đảm bảo tên cột trong DB khớp với tên bạn định nghĩa.
            // Ví dụ, nếu bảng là 'nguoi_dung' và cột là 'user_id', 'email', 'password', 'role':
            String sql = "INSERT INTO nguoi_dung(userID, email, password, role) VALUES(?, ?, ?, ?)";
            
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, nguoiDung.getUserID());
                pst.setString(2, nguoiDung.getEmail());
                pst.setString(3, nguoiDung.getPassword());
                
                // Gán giá trị mặc định cho role nếu chưa được set
                String roleToInsert = nguoiDung.getRole();
                if (roleToInsert == null || roleToInsert.isEmpty()) {
                    roleToInsert = "user"; // Hoặc "khach_hang" hoặc giá trị mặc định khác
                }
                pst.setString(4, roleToInsert);

                pst.executeUpdate();
                System.out.println("NguoiDung inserted successfully with ID: " + nguoiDung.getUserID() + " and Role: " + roleToInsert);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 5. Đóng kết nối trong khối finally để đảm bảo nó luôn được đóng
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}