import java.util.ArrayList;
    
public class HoaDonList {
    ArrayList<HoaDon>st = new ArrayList<HoaDon>();
    public ArrayList<HoaDon>addHoaDon(HoaDon  hoaDon) {
        st.add(hoaDon);
        return st;
    }
    public ArrayList<HoaDon> getEditHoaDOn(int idHoaDon  , int tongtien ) {

        for (int i = 0; i < st.size(); i++) {

            if (st.get(i).getIdHoaDon() ==  idHoaDon) {

                System.out.print("true");

                st.get(i).setTongtien(tongtien);
            }

        }
        return st;

     }
     public ArrayList<HoaDon> getDeleteHoaDon(int idHoaDon) {

        for (int i = 0; i < st.size(); i++) {

            if (st.get(i).getIdHoaDon() == idHoaDon) {

                st.remove(i);

            }

        }

        return st;
    }

    public void printHoaDonList() {
        int len = st.size();

        for (int i = 0; i < len; i++) {
            System.out.println("Mã Hóa Dơn: " + st.get(i).getIdHoaDon() + " Tổng Tiền Ăn: " + st.get(i).getTongtien());

        }

    }
}

 

