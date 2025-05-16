import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testNhanVien {

    @Test
    public void testConstructorFull() {
        NhanVien nv = new NhanVien(1, "Nguyen Van A", "2023-01-01", "0123456789", "Nhan vien", 2, "Dang lam");

        assertEquals(1, nv.getId_NV());
        assertEquals("Nguyen Van A", nv.getTenNV());
        assertEquals("2023-01-01", nv.getNgayVL());
        assertEquals("0123456789", nv.getSdt());
        assertEquals("Nhan vien", nv.getChucvu());
        assertEquals(2, nv.getId_NQL());
        assertEquals("Dang lam", nv.getTinhTrang());
    }

    @Test
    public void testConstructorWithoutTinhTrang() {
        NhanVien nv = new NhanVien(2, "Tran Thi B", "2024-01-01", "0987654321", "Quan ly", 3);

        assertEquals(2, nv.getId_NV());
        assertEquals("Tran Thi B", nv.getTenNV());
        assertEquals("2024-01-01", nv.getNgayVL());
        assertEquals("0987654321", nv.getSdt());
        assertEquals("Quan ly", nv.getChucvu());
        assertEquals(3, nv.getId_NQL());
        assertNull(nv.getTinhTrang());
    }

    @Test
    public void testSetterGetter() {
        NhanVien nv = new NhanVien();

        nv.setId_NV(3);
        nv.setTenNV("Le Van C");
        nv.setNgayVL("2025-05-16");
        nv.setSdt("0111222333");
        nv.setChucvu("Giam doc");
        nv.setId_NQL(1);
        nv.setTinhTrang("Nghi viec");

        assertEquals(3, nv.getId_NV());
        assertEquals("Le Van C", nv.getTenNV());
        assertEquals("2025-05-16", nv.getNgayVL());
        assertEquals("0111222333", nv.getSdt());
        assertEquals("Giam doc", nv.getChucvu());
        assertEquals(1, nv.getId_NQL());
        assertEquals("Nghi viec", nv.getTinhTrang());
    }
}
