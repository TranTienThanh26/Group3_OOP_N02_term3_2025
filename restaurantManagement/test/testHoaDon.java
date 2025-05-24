import java.util.ArrayList;

public class testHoaDon {
   

    public static void test() {
        HoaDon hd = new HoaDon(1, 101, 5, "2025-05-11", 250000, 0, 250000);
        System.out.println("ID Hóa Đơn: " + hd.getIdHoaDon());
        System.out.println("Tổng Tiền: " + hd.getTongtien());
        System.out.println("Trạng Thái: " + hd.getTrangthai());

        HoaDonList dshoadon = new HoaDonList();
        dshoadon.themHoaDon(hd);
        

}
}