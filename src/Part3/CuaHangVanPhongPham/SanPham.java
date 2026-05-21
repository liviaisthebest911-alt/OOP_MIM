package Part3.CuaHangVanPhongPham;

/**
 * Đại diện cho một món đồ dùng học tập được bán tại cửa hàng.
 * Mỗi sản phẩm có thông tin cơ bản và số lượng tồn kho.
 */
public class SanPham {
    private final String maSP;
    private String tenSP;
    private double gia;
    private int soLuongTon;
    private LoaiSanPham loaiSanPham;

    public SanPham(String maSP, String tenSP, double gia, int soLuongTon, LoaiSanPham loaiSanPham) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuongTon = soLuongTon;
        this.loaiSanPham = loaiSanPham;
    }

    // Getters
    public String getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public double getGia() { return gia; }
    public int getSoLuongTon() { return soLuongTon; }

    /**
     * YÊU CẦU: Hoàn thiện phương thức này.
     * Giảm số lượng tồn kho của sản phẩm đi một lượng bằng với tham số `soLuong`.
     *
     * @param soLuong Số lượng cần giảm.
     * @return  - Trả về `true` nếu việc giảm số lượng thành công (điều kiện: `soLuong` phải lớn hơn 0 VÀ `soLuong` không được vượt quá `soLuongTon` hiện tại).
     * - Trả về `false` trong các trường hợp còn lại (không đủ hàng, hoặc số lượng đầu vào không hợp lệ).
     * - Nếu giảm thành công, phải cập nhật lại giá trị của `soLuongTon`.
     */
    public boolean giamSoLuongTon(int soLuong) {
        // TODO: Học sinh viết code ở đây
        if (soLuong > 0 && soLuong <= soLuongTon){
            soLuongTon -= soLuong;
            return true;

        }
        return false;
    }

    @Override
    public String toString() {
        return "SanPham[Ma=" + maSP + ", Ten='" + tenSP + "', Gia=" + gia + ", TonKho=" + soLuongTon + "]";
    }
}