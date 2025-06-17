import java.util.ArrayList;

public class BanList {

    ArrayList<Ban> st = new ArrayList<Ban>();

    public ArrayList<Ban> addBan(Ban ba) {

        st.add(ba);
        return st;

    }

    public ArrayList<Ban> getEditBan(String tenBan, int maBan) {

        for (int i = 0; i < st.size(); i++) {

            if (st.get(i).getMaBan() == maBan) {

                System.out.print("true");

                st.get(i).setTenBan(tenBan);
            }

        }

        return st;
    }

    public ArrayList<Ban> getDeleteBan(int maBan) {

        for (int i = 0; i < st.size(); i++) {

            if (st.get(i).getMaBan() == maBan) {

                st.remove(i);

            }

        }

        return st;
    }

    public void printBanList() {
        int len = st.size();

        for (int i = 0; i < len; i++) {
            System.out.println("maBan: " + st.get(i).getMaBan() + " TenBànàn: " + st.get(i).getTenBan());

        }

    }
}