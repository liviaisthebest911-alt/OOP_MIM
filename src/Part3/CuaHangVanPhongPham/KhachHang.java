package Part3.CuaHangVanPhongPham;

/**
 * Đại diện cho một khách hàng mua sắm tại cửa hàng.
 * Mỗi khách hàng sở hữu một giỏ hàng riêng.
 */
public class KhachHang {
    private final String maKH;
    private String tenKH;
    private final GioHang gioHang;

    public KhachHang(String maKH, String tenKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioHang = new GioHang(this); // Tự động tạo giỏ hàng mới cho khách
    }

    // Getters
    public String getMaKH() { return maKH; }
    public String getTenKH() { return tenKH; }
    public GioHang getGioHang() { return gioHang; }

    @Override
    public String toString() {
        return "KhachHang[Ma=" + maKH + ", Ten='" + tenKH + "']";
    }
}