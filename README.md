# Group3_OOP_N02_term3_2025
OOP-Group-3

#1.Thành viên:

1.Vũ Thành Trung

github id: thanhtrung2512

2.Trần Tiến Thành

github id: TranTienThanh26

3.Nguyễn Văn Dũng

github id: dungpnk

#2.Tiêu đề:
QUẢN LÝ NHÀ HÀNG

#3.Đối tượng:

MonAn(maMonAn,tenMonAn,donGia,loaiMonAn,trangThai)
BanAn(MaBan,TenBan,trangthai)
DonGoiMon(maDon,maBan,maKH,thoiGianGoi,trangThai)
HoaDon(idHoaDon,idBan,tienMonAn,tongtien)

#QUẢN LÝ NHÀ HÀNG

public class MonAn {

    private int maMonAn;
    private String tenMonAn;  // Tên món ăn
    private int donGia;     // Đơn giá
    private String loaiMonAn;   // Loại món ăn (ví dụ: Khai vị, Món chính, Tráng miệng)
    private String trangThai;;
    private double gia;
  
public class Ban {

    private int maBan;     // Mã bàn
    private String tenBan;   // Tên bàn
    private String trangThai; // Trạng thái bàn (Ví dụ: Trống, Đã đặt, Đang phục vụ)

 public class DonGoiMon {

    private int maDon;
    private int maBan;
    private int maKH;
    private String thoiGianGoi;
    private String trangThai; // Ví dụ: Mới, Đã xác nhận, Đang chuẩn bị, Đã xong, Hủy

public class HoaDon {
   private int idHoaDon;
   private int idBan;
   private int tienMonAn;
   private int tongtien;
    


