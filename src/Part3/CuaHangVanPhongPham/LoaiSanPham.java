package Part3.CuaHangVanPhongPham;

/**
 * Đại diện cho các loại/danh mục của đồ dùng học tập.
 * Lớp này thay thế enum, sử dụng constructor private và các hằng số public static final.
 */
public class LoaiSanPham {
    public static final LoaiSanPham BUT = new LoaiSanPham("Bút viết");
    public static final LoaiSanPham SO = new LoaiSanPham("Sổ tay & Vở");
    public static final LoaiSanPham DUNG_CU_KHAC = new LoaiSanPham("Dụng cụ khác");

    private final String moTa;

    private LoaiSanPham(String moTa) {
        this.moTa = moTa;
    }

    public String getMoTa() {
        return moTa;
    }

    @Override
    public String toString() {
        return this.moTa;
    }
}