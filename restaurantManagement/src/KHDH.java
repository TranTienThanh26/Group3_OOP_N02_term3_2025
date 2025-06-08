public class KHDH
{
    public String maKhachHang;
    public String maMonAn;
    public String ten;
    public boolean isPaid;

    public KHDH(String maKhachHang, String maMonAn, String ten, boolean isPaid) 
    {
        this.maKhachHang = maKhachHang; 
        this.maMonAn = maMonAn;
        this.ten = ten;
        this.isPaid = isPaid;
    }

    public void displayKHDH() 
    {
        System.out.println("User ID: " + maKhachHang);
        System.out.println("MonAn ID: " + maMonAn);
        System.out.println("Ten: " + ten);
       

    }

}
