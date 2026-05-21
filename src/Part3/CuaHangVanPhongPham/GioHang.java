package Part3.CuaHangVanPhongPham;

import java.util.HashMap;
import java.util.Map;

/**
 * Đại diện cho giỏ hàng của một khách hàng.
 * Chứa các sản phẩm và số lượng tương ứng mà khách hàng muốn mua.
 */
public class GioHang {
    private final KhachHang chuSoHuu;
    private final Map<SanPham, Integer> cacSanPham;

    public GioHang(KhachHang chuSoHuu) {
        this.chuSoHuu = chuSoHuu;
        this.cacSanPham = new HashMap<>();
    }

    // Getters
    public KhachHang getChuSoHuu() { return chuSoHuu; }
    public Map<SanPham, Integer> getCacSanPham() { return cacSanPham; }

    /**
     * Thêm một sản phẩm vào giỏ hàng.
     * @param sp Sản phẩm cần thêm.
     * @param soLuong Số lượng.
     */
    public void themSanPham(SanPham sp, int soLuong) {
        if (sp != null && soLuong > 0) {
            this.cacSanPham.put(sp, this.cacSanPham.getOrDefault(sp, 0) + soLuong);
            System.out.println("[GIỎ HÀNG] " + chuSoHuu.getTenKH() + ": Đã thêm " + soLuong + " '" + sp.getTenSP() + "'.");
        }
    }

    /**
     * YÊU CẦU: Hoàn thiện phương thức này.
     * Tính tổng giá trị của tất cả các sản phẩm có trong giỏ hàng.
     * Gợi ý:
     * - Duyệt qua từng cặp (Sản phẩm, Số lượng) trong map `cacSanPham`.
     * - Với mỗi cặp, tính thành tiền bằng cách nhân đơn giá của sản phẩm với số lượng.
     * - Cộng dồn thành tiền của tất cả các sản phẩm để ra tổng cuối cùng.
     *
     * @return Tổng số tiền (kiểu double) của giỏ hàng. Nếu giỏ hàng trống, trả về 0.
     */
    public double tinhTongTien() {
        return cacSanPham.entrySet()
                .stream()
                .mapToDouble(entry ->
                        entry.getKey().getGia() * entry.getValue())
                .sum();
    }

    /**
     * Xóa sạch giỏ hàng, thường được gọi sau khi thanh toán thành công.
     */
    public void xoaSachGioHang() {
        this.cacSanPham.clear();
    }
}