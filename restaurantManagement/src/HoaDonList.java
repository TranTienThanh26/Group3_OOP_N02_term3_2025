import java.util.ArrayList;

public class HoaDonList {

    ArrayList<HoaDon> danhsachHoaDon = new ArrayList<HoaDon>();

    // Thêm hóa đơn
    public ArrayList<HoaDon> themHoaDon(HoaDon hoadon) {
        danhsachHoaDon.add(hoadon);
        return danhsachHoaDon;
    }

    // In toàn bộ danh sách hóa đơn
    public void inDanhSach() {
        if (danhsachHoaDon.isEmpty()) {
            System.out.println("Danh sách hóa đơn đang trống.");
        } else {
            for (HoaDon hd : danhsachHoaDon) {
                System.out.println("Mã hóa đơn: " + hd.idHoaDon);
                System.out.println("Tên khách hàng: " + hd.tenKhachHang);
                System.out.println("Tổng tiền: " + hd.tongtien);
                System.out.println("---------------------------");
            }
        }
    }

    // Sửa hóa đơn theo mã
    public void suaHoaDon(int idHoaDon, String tenMoi, double tongTienMoi) {
        boolean timThay = false;
        for (HoaDon hd : danhsachHoaDon) {
            if (hd.idHoaDon == idHoaDon) {
                hd.tenKhachHang = tenMoi;
                hd.tongtien = tongTienMoi;
                timThay = true;
                System.out.println("Đã cập nhật hóa đơn có mã: " + idHoaDon);
                break;
            }
        }

        if (!timThay) {
            System.out.println("Không tìm thấy hóa đơn có mã: " + idHoaDon);
        }
    }

    // Xóa hóa đơn theo mã
    public void xoaHoaDon(int idHoaDon) {
        boolean daXoa = false;
        for (int i = danhsachHoaDon.size() - 1; i >= 0; i--) {
            if (danhsachHoaDon.get(i).idHoaDon == idHoaDon) {
                danhsachHoaDon.remove(i);
                daXoa = true;
                System.out.println("Đã xóa hóa đơn có mã: " + idHoaDon);
                break;
            }
        }

        if (!daXoa) {
            System.out.println("Không tìm thấy hóa đơn có mã: " + idHoaDon);
        }
  
    }
    
}

