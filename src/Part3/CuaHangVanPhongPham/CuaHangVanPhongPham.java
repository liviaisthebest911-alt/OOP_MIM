package Part3.CuaHangVanPhongPham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lớp dịch vụ trung tâm, quản lý toàn bộ hoạt động của cửa hàng.
 * Chứa kho hàng, danh sách khách hàng và xử lý nghiệp vụ thanh toán.
 */
public class CuaHangVanPhongPham {
    private static List<SanPham> khoHang = new ArrayList<>();
    private static List<KhachHang> danhSachKhachHang = new ArrayList<>();
    private static List<HoaDon> lichSuGiaoDich = new ArrayList<>();
    private static int nextInvoiceId = 1;

    public static void themSanPhamVaoKho(SanPham sp) { khoHang.add(sp); }
    public static void dangKyKhachHang(KhachHang kh) { danhSachKhachHang.add(kh); }

    /**
     * YÊU CẦU: Hoàn thiện phương thức này.
     * Tìm kiếm một đối tượng SanPham trong danh sách `khoHang` dựa vào `maSP`.
     * Gợi ý:
     * - Duyệt qua tất cả các đối tượng `SanPham` trong `khoHang`.
     * - Với mỗi sản phẩm, so sánh `maSP` của nó với `maSP` được truyền vào.
     * - Sử dụng phương thức `equalsIgnoreCase` để so sánh chuỗi không phân biệt chữ hoa/thường.
     *
     * @param maSP Mã sản phẩm cần tìm (dạng chuỗi).
     * @return  - Trả về đối tượng `SanPham` nếu tìm thấy.
     * - Trả về `null` nếu không có sản phẩm nào trong kho có mã tương ứng.
     */
    public static SanPham timSanPhamTheoMa(String maSP) {
        // TODO: Học sinh viết code ở đây
        return null;
    }

    /**
     * Xử lý nghiệp vụ thanh toán cho một giỏ hàng.
     * @param gioHang Giỏ hàng cần thanh toán.
     * @return Đối tượng HoaDon nếu thành công, null nếu thất bại.
     */
    public static HoaDon thanhToan(GioHang gioHang) {
        System.out.println("\n[THANH TOÁN] Bắt đầu xử lý cho khách hàng: " + gioHang.getChuSoHuu().getTenKH());
        Map<SanPham, Integer> sanPhamTrongGio = gioHang.getCacSanPham();

        if (sanPhamTrongGio.isEmpty()) {
            System.out.println("[LỖI] Giỏ hàng trống. Không thể thanh toán.");
            return null;
        }

        // Bước 1: Kiểm tra tồn kho trước khi thực hiện bất kỳ thay đổi nào
        for (Map.Entry<SanPham, Integer> entry : sanPhamTrongGio.entrySet()) {
            if (entry.getKey().getSoLuongTon() < entry.getValue()) {
                System.out.println("[LỖI] Không đủ hàng cho sản phẩm '" + entry.getKey().getTenSP() + "'.");
                System.out.println("   (Cần: " + entry.getValue() + ", Chỉ còn: " + entry.getKey().getSoLuongTon() + ")");
                return null;
            }
        }

        // Bước 2: Nếu đủ hàng, tiến hành cập nhật số lượng và tạo hóa đơn
        for (Map.Entry<SanPham, Integer> entry : sanPhamTrongGio.entrySet()) {
            entry.getKey().giamSoLuongTon(entry.getValue());
        }

        String hoaDonId = "HD" + String.format("%04d", nextInvoiceId++);
        // Tạo một bản sao của map để lưu vào hóa đơn, tránh việc giỏ hàng bị xóa làm mất dữ liệu
        Map<SanPham, Integer> danhSachMua = new HashMap<>(sanPhamTrongGio);
        HoaDon hoaDonMoi = new HoaDon(hoaDonId, gioHang.getChuSoHuu(), danhSachMua, gioHang.tinhTongTien());

        lichSuGiaoDich.add(hoaDonMoi);
        gioHang.xoaSachGioHang(); // Xóa giỏ hàng sau khi thanh toán

        System.out.println("[THÀNH CÔNG] Thanh toán thành công. Hóa đơn " + hoaDonId + " đã được tạo.");
        return hoaDonMoi;
    }

    /**
     * In báo cáo tồn kho của cửa hàng.
     */
    public static void inBaoCaoTonKho() {
        System.out.println("\n--- BÁO CÁO TỒN KHO ---");
        for (SanPham sp : khoHang) {
            System.out.println(sp);
        }
        System.out.println("------------------------");
    }
}
