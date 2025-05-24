import java.util.ArrayList;

public class HoaDonList {

    ArrayList<HoaDon> danhsachHoaDon = new ArrayList<HoaDon>();

    public  ArrayList<HoaDon> themHoaDon(HoaDon hoadon) {

        danhsachHoaDon.add(hoadon);
        return danhsachHoaDon;

    }

    public void inDanhSach( ArrayList<HoaDon> danhsachHoaDon){


       for (int i =0; i < danhsachHoaDon.size(); i++)
       {

        System.out.println(danhsachHoaDon.get(i).idHoaDon);
        System.out.println(danhsachHoaDon.get(i).trangthai);


       }

      
    }





}