package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.servingwebcontent.MonAn; // Đảm bảo đường dẫn này đúng với vị trí file MonAn.java của bạn

public class monAnAiven {

    public monAnAiven() {}

    // ArrayList sẽ chứa các đối tượng MonAn
    ArrayList<MonAn> items = new ArrayList<MonAn>();

    /**
     * Phương thức này kết nối đến cơ sở dữ liệu Aiven, truy vấn thông tin món ăn
     * và trả về danh sách các đối tượng MonAn.
     * @return ArrayList<MonAn> - Danh sách các món ăn.
     */
    public ArrayList<MonAn> getMonAnListFromAiven() { // Đổi tên phương thức cho rõ ràng hơn

        Connection conn = null;
        try {
            // Tải driver JDBC cho MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Thiết lập kết nối đến cơ sở dữ liệu Aiven
            
            // không nên mã hóa cứng trong mã nguồn trong môi trường sản xuất.
            // Hãy xem xét sử dụng biến môi trường hoặc file cấu hình.
            conn = DriverManager.getConnection(
                    "jdbc:mysql://avnadmin:AVNS_HNm9Mr2leXuYSrqITaj@mysql-338b99d8-restaurantmanager.e.aivencloud.com:19834/defaultdb?ssl-mode=REQUIRED",
                    "sqluser", "password");

            Statement sta = conn.createStatement();

            // Thực thi truy vấn SQL để lấy dữ liệu từ bảng 'mon_an'
            // Đảm bảo tên bảng và tên cột khớp với cơ sở dữ liệu của bạn
            ResultSet setdata = sta.executeQuery("SELECT ma_mon_an, ten_mon_an, don_gia, loai_mon_an, trang_thai, so_luong_da_ban FROM mon_an LIMIT 10");

            while (setdata.next()) {
                MonAn monAn = new MonAn(); // Tạo một đối tượng MonAn mới cho mỗi hàng

                // Lấy dữ liệu từ ResultSet theo tên cột và kiểu dữ liệu phù hợp
                // Sử dụng getInt, getString, getDouble tương ứng với kiểu dữ liệu của cột
                monAn.setMaMonAn(setdata.getInt("ma_mon_an"));
                monAn.setTenMonAn(setdata.getString("ten_mon_an"));
                monAn.setDonGia(setdata.getDouble("don_gia"));
                monAn.setLoaiMonAn(setdata.getString("loai_mon_an"));
                monAn.setTrangThai(setdata.getString("trang_thai"));
                monAn.setSoLuongDaBan(setdata.getInt("so_luong_da_ban"));

                // In ra console để kiểm tra (có thể bỏ đi trong môi trường production)
                System.out.println("MON AN AIVEN DATA:");
                System.out.println("Mã: " + monAn.getMaMonAn() + ", Tên: " + monAn.getTenMonAn() + ", Giá: " + monAn.getDonGia());

                items.add(monAn); // Thêm đối tượng MonAn vào danh sách
            }

            // Đóng tài nguyên để tránh rò rỉ bộ nhớ và kết nối
            setdata.close();
            sta.close();
            conn.close();

        }
        catch (Exception e) {
            System.err.println("Lỗi kết nối hoặc truy vấn cơ sở dữ liệu Aiven cho Món Ăn:"); // Sử dụng System.err cho lỗi
            e.printStackTrace(); // In stack trace đầy đủ để gỡ lỗi
        }

        return items; // Trả về danh sách các món ăn
    }
}