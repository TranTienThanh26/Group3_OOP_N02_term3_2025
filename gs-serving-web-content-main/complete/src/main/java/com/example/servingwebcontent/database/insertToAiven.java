package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.example.servingwebcontent.Model.NguoiDung;

public class insertToAiven {

    public void insertNguoiDungToDb(NguoiDung nguoiDung) {
        try (
            Connection conn = new myConnection().getConnection();
            PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO NguoiDung (UserID, Email, MatKhau, VaiTro) VALUES (?, ?, ?, ?)"
            )
        ) {
            // Tạo userId nếu chưa có
            int userId = nguoiDung.getUserID() == 0
                         ? Math.abs(UUID.randomUUID().hashCode())
                         : nguoiDung.getUserID();

            String role = nguoiDung.getRole();
            if (role == null || role.trim().isEmpty()) {
                role = "Khach Hang"; // Giá trị mặc định
            }

            pst.setInt(1, userId);
            pst.setString(2, nguoiDung.getEmail());
            pst.setString(3, nguoiDung.getPassword());
            pst.setString(4, role);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.printf("✅ Người dùng được chèn thành công (ID: %d, Email: %s, Vai trò: %s)\n",
                                  userId, nguoiDung.getEmail(), role);
            } else {
                System.out.println("⚠️ Không có bản ghi nào được chèn.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi thao tác với database:");
            e.printStackTrace();
        }
    }
}
