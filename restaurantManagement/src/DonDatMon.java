import java.util.*;

public class DonDatMon {
    private Map<Integer, List<MonAn>> danhSachDonDat = new HashMap<>();

    public boolean kiemTraDaDatMon(int userId) {
        List<MonAn> danhSachMon = danhSachDonDat.get(userId);
        return danhSachMon != null && !danhSachMon.isEmpty();
    }

    public void themMonAn(int userId, MonAn monAn) {
        danhSachDonDat.computeIfAbsent(userId, k -> new ArrayList<>()).add(monAn);
    }
}
