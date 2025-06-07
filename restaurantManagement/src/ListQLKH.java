import java.util.ArrayList;

class ListQLKH {
    private ArrayList<QLKH> list = new ArrayList<>(); // Danh sách chứa các khách hàng

    // Thêm một khách hàng vào danh sách
    public void addObject(QLKH obj) {
        list.add(obj);
    }

    // Hiển thị toàn bộ danh sách khách hàng
    public void displayList() {
        for (QLKH ql : list) {
            System.out.println(ql); // Gọi phương thức toString() của đối tượng QLKH để hiển thị thông tin
        }
    }

    // Lọc khách hàng theo mã khách hàng hoặc mã món ăn
    public ArrayList<QLKH> filterQLKHTradition(String maKhachHang) {
        ArrayList<QLKH> result = new ArrayList<>();
        for (QLKH ql : list) {
            // Nếu mã khách hàng trùng với từ khóa tìm kiếm, thêm vào danh sách kết quả
            if (ql.getMaKhachHang().equals(maKhachHang)) {
                result.add(ql);
            }
        }
        return result;
    }
}
