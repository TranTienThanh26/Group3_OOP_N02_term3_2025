import java.util.ArrayList;

public class testHoaDon {
   

    public static void test() {
        HoaDon hd = new HoaDon(10, 101, 5, "2025-05-11", 250000, 0, 250000);
        HoaDon hd2 = new HoaDon(100, 1010, 5, "2025-05-11", 1000000, 0, 250000);
        HoaDon hd3 = new HoaDon(1000, 1010, 5, "2025-05-11", 25000000, 0, 250000);



        System.out.println("ID Hóa Đơn: " + hd.getIdHoaDon());
        System.out.println("Tổng Tiền: " + hd.getTongtien());
        System.out.println("Trạng Thái: " + hd.getTrangthai());

        HoaDonList dshoadon = new HoaDonList();

        dshoadon.themHoaDon(hd);
        dshoadon.themHoaDon(hd2);
        dshoadon.themHoaDon(hd3);

        dshoadon.inDanhSach();

        


}


}