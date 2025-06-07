public class TestDonDatMon {
    public static void main(String[] args) {
        DonDatMon donDatMon = new DonDatMon();
        int userId = 10;

        // Kiểm tra khi chưa đặt món
        System.out.println("Trường hợp 1 - Chưa đặt món:");
        if (!donDatMon.kiemTraDaDatMon(userId)) {
            System.out.println("✅ Khách hàng chưa đặt món nào.");
        } else {
            System.out.println("❌ Sai! Đã có món dù chưa đặt.");
        }

        // Thêm món ăn
        MonAn mon1 = new MonAn(1, "Gà rán", 60000, "Món chính", "Còn");
        donDatMon.themMonAn(userId, mon1);

        // Kiểm tra sau khi đã đặt món
        System.out.println("\nTrường hợp 2 - Đã đặt 1 món:");
        if (donDatMon.kiemTraDaDatMon(userId)) {
            System.out.println("✅ Khách hàng đã đặt ít nhất một món.");
        } else {
            System.out.println("❌ Sai! Đặt món rồi mà vẫn báo chưa.");

        }
    }
    
}
