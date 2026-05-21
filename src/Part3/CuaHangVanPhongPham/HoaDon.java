package Part3.CuaHangVanPhongPham;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Đại diện cho một hóa đơn thanh toán sau khi giao dịch thành công.
 * Đây là một bản ghi bất biến về một giao dịch đã xảy ra.
 */
public class HoaDon {
    private final String maHD;
    private final KhachHang khachHang;
    private final Map<SanPham, Integer> danhSachMua;
    private final double tongTien;
    private final LocalDateTime ngayTao;

    public HoaDon(String maHD, KhachHang khachHang, Map<SanPham, Integer> danhSachMua, double tongTien) {
        this.maHD = maHD;
        this.khachHang = khachHang;
        this.danhSachMua = danhSachMua;
        this.tongTien = tongTien;
        this.ngayTao = LocalDateTime.now();
    }

    /**
     * In hóa đơn ra màn hình với định dạng dễ đọc.
     */
    public void inHoaDon() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        System.out.println("\n========== HÓA ĐƠN BÁN LẺ ==========");
        System.out.println("Mã hóa đơn: " + this.maHD);
        System.out.println("Khách hàng: " + this.khachHang.getTenKH());
        System.out.println("Ngày tạo: " + this.ngayTao.format(formatter));
        System.out.println("--------------------------------------");
        System.out.println("Chi tiết mua hàng:");
        for (Map.Entry<SanPham, Integer> entry : this.danhSachMua.entrySet()) {
            SanPham sp = entry.getKey();
            int soLuong = entry.getValue();
            System.out.printf("- %-20s (SL: %d) - Đơn giá: %,.0f - Thành tiền: %,.0f\n",
                    sp.getTenSP(), soLuong, sp.getGia(), sp.getGia() * soLuong);
        }
        System.out.println("--------------------------------------");
        System.out.printf("TỔNG CỘNG: %,.0f VND\n", this.tongTien);
        System.out.println("======================================");
    }
}
