import java.util.*;

public class ListKHDH {
    ArrayList<KHDH> ht = new ArrayList<KHDH>();

    public ArrayList<KHDH> addObject(KHDH k) {

        ht.add(k);

        return ht;

    }

    public void displayList() 
    {
        for (int i = 0; i < ht.size(); i++) 
        {

            System.out.println("MaKH: " + ht.get(i).maKhachHang);
            System.out.println("MaMonAn: " + ht.get(i).maMonAn);
            
        }
    }

    public static void displayList(List<KHDH> ht) 
    {
        for (int i = 0; i < ht.size(); i++) {

            System.out.println("MaKH: " + ht.get(i).maKhachHang);
            System.out.println("MaMonAn: " + ht.get(i).maMonAn);

        }
    }

    public void shortList() 
    {

        List<KHDH> list =ht.subList(0, 2);

        ListKHDH.displayList(list);

    }

    public ArrayList<KHDH> filterKHDHTradition(String keyword) 
    {
        ArrayList<KHDH> newList = new ArrayList<KHDH>();

        for (KHDH k : ht) {
            if ((k.maKhachHang).contains(keyword) || (k.maMonAn).contains(keyword)) {
                newList.add(k);

            }
        }
        return newList;

    }
    public ArrayList<KHDH> getPaidOrdersByKhachHang(String maKhachHang) 
   {
    ArrayList<KHDH> result = new ArrayList<>();

    for (KHDH k : ht) {
        if (k.maKhachHang.equals(maKhachHang) && k.isPaid) {
            result.add((k));
        }
    }

    return result;

}
  // Thêm getter nếu bạn muốn truy cập toàn bộ danh sách từ bên ngoài
  public List<KHDH> getHt() { // Hoặc getOrders() cho tên rõ ràng hơn
    return new ArrayList<>(ht); // Trả về một bản sao để ngăn chặn sửa đổi trực tiếp bên ngoài
}
}

   

