package Part3.Library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp dịch vụ trung tâm, quản lý toàn bộ hoạt động của thư viện.
 * Chứa danh mục sách, danh sách độc giả và các phiếu mượn đang hoạt động.
 */
public class ThuVien {
    private static List<Sach> danhMucSach = new ArrayList<>();
    private static List<DocGia> danhSachDocGia = new ArrayList<>();
    private static List<PhieuMuon> cacPhieuMuonDangHoatDong = new ArrayList<>();
    private static int nextLoanId = 1;

    // Các phương thức khởi tạo dữ liệu
    public static void themSach(Sach sach) { danhMucSach.add(sach); }
    public static void dangKyDocGia(DocGia docGia) { danhSachDocGia.add(docGia); }

    // File: ThuVien.java

    /**
     * Xử lý nghiệp vụ cho mượn sách.
     * <p>
     * YÊU CẦU SINH VIÊN HOÀN THIỆN:
     * 1. Tìm đối tượng 'Sach' trong 'danhMucSach' dựa vào 'idSach'.
     * 2. Tìm đối tượng 'DocGia' trong 'danhSachDocGia' dựa vào 'idDocGia'.
     * 3. Thực hiện các kiểm tra điều kiện:
     * - Nếu không tìm thấy sách, in ra thông báo lỗi và trả về null.
     * - Nếu không tìm thấy độc giả, in ra thông báo lỗi và trả về null.
     * - Nếu sách tìm thấy có trạng thái là DANG_DUOC_MUON, in ra thông báo lỗi và trả về null.
     * 4. Nếu tất cả điều kiện đều hợp lệ:
     * a. Tạo một mã phiếu mượn mới (ví dụ: "PM" + số thứ tự).
     * b. Tạo một đối tượng 'PhieuMuon' mới với thời hạn mượn là 14 ngày.
     * c. Cập nhật trạng thái của sách thành DANG_DUOC_MUON.
     * d. Thêm phiếu mượn mới vào danh sách 'sachDangMuon' của độc giả.
     * e. Thêm phiếu mượn mới vào danh sách 'cacPhieuMuonDangHoatDong' của thư viện.
     * f. In thông báo mượn sách thành công.
     * g. Trả về đối tượng 'PhieuMuon' vừa tạo.
     *
     * @param idSach ID của sách cần mượn.
     * @param idDocGia ID của độc giả mượn sách.
     * @return Đối tượng PhieuMuon nếu thành công, null nếu thất bại.
     */
    public static PhieuMuon choMuonSach(String idSach, String idDocGia) {
        System.out.println("\n[YÊU CẦU MƯỢN] Độc giả " + idDocGia + " muốn mượn sách " + idSach);


        // 1. Tìm sách
        Sach sach = null;
        for (Sach s : danhMucSach) {
            if (s.getId().equals(idSach)) {
                sach = s;
                break;
            }
        }

        if (sach == null) {

            System.out.println("   -> [THẤT BẠI] Không tìm thấy sách với ID: "+idSach);
            return null;
        }

        // 2. Tìm độc giả
        DocGia docGia = null;
        for (DocGia dg : danhSachDocGia) {
            if (dg.getId().equals(idDocGia)) {
                docGia = dg;
                break;
            }
        }

        if (docGia == null) {

            System.out.println("   -> [THẤT BẠI] Không tìm thấy độc giả với ID: "+idDocGia);
            return null;
        }

        // 3. Kiểm tra sách đã được mượn chưa
        if (sach.getTrangThai() == TrangThaiSach.DANG_DUOC_MUON) {
            System.out.println("   -> [THẤT BẠI] Sách '"+sach.getTieuDe()+"' hiện đang được mượn.");
            return null;
        }

        // a. Tạo mã phiếu mượn
        String maPhieu = "PM" + String.format("%03d", nextLoanId++);

        // b. Tạo phiếu mượn (14 ngày)
        // b. Tạo phiếu mượn (14 ngày)
        PhieuMuon phieuMuon = new PhieuMuon(maPhieu, sach, docGia, LocalDate.now(), 14);

        // c. Cập nhật trạng thái sách
        sach.setTrangThai(TrangThaiSach.DANG_DUOC_MUON);

        // d. Thêm vào danh sách sách đang mượn của độc giả
        docGia.getSachDangMuon().add(phieuMuon);

        // e. Thêm vào danh sách phiếu mượn đang hoạt động
        cacPhieuMuonDangHoatDong.add(phieuMuon);

        // f. Thông báo thành công

        System.out.println("   -> [THÀNH CÔNG] Độc giả "+docGia.getTenDocGia()+" đã mượn sách '"+sach.getTieuDe()+"'.");
        // g. Trả về phiếu mượn
        return phieuMuon;
    }

    /**
     * Xử lý nghiệp vụ nhận trả sách.
     * <p>
     * YÊU CẦU SINH VIÊN HOÀN THIỆN:
     * 1. Kiểm tra nếu 'phieu' truyền vào là null thì không làm gì cả.
     * 2. Lấy ra đối tượng 'Sach' và 'DocGia' từ phiếu mượn.
     * 3. Cập nhật trạng thái của sách thành CO_SAN.
     * 4. Xóa phiếu mượn này khỏi danh sách 'sachDangMuon' của độc giả.
     * 5. Xóa phiếu mượn này khỏi danh sách 'cacPhieuMuonDangHoatDong' của thư viện.
     * 6. In thông báo đã nhận trả sách.
     * 7. Kiểm tra xem phiếu mượn có bị quá hạn không (dùng phieu.isQuaHan()). Nếu có, in thêm một cảnh báo.
     *
     * @param phieu Phiếu mượn tương ứng với sách được trả.
     */
    public static void nhanTraSach(PhieuMuon phieu) {

        System.out.println();   // dòng trống bắt buộc

        if (phieu == null) {
            System.out.println("[NHẬN TRẢ] Phiếu mượn không hợp lệ (null). Bỏ qua.");
            return;
        }

        Sach sach = phieu.getSach();
        DocGia docGia = phieu.getDocGia();

        sach.setTrangThai(TrangThaiSach.CO_SAN);

        docGia.getSachDangMuon().remove(phieu);
        cacPhieuMuonDangHoatDong.remove(phieu);

        System.out.println("[NHẬN TRẢ] Độc giả "
                + docGia.getTenDocGia()
                + " trả sách '"
                + sach.getTieuDe() + "'.");

        System.out.println("   -> [THÀNH CÔNG] Đã cập nhật trạng thái sách và độc giả.");

        if (phieu.isQuaHan()) {
            System.out.println("⚠ Cảnh báo: Phiếu mượn này đã quá hạn.");
        }
    }

    /**
     * In ra danh sách tất cả các sách đang bị mượn quá hạn.
     * <p>
     * YÊU CẦU SINH VIÊN HOÀN THIỆN:
     * 1. In ra một tiêu đề cho báo cáo (ví dụ: "--- Báo cáo sách mượn quá hạn ---").
     * 2. Duyệt qua danh sách 'cacPhieuMuonDangHoatDong' của thư viện.
     * 3. Với mỗi phiếu mượn, sử dụng phương thức isQuaHan() để kiểm tra.
     * 4. Nếu phiếu bị quá hạn, in thông tin của phiếu đó ra màn hình.
     * 5. Nếu sau khi duyệt hết danh sách mà không có phiếu nào quá hạn,
     * in ra thông báo "Không có sách nào bị mượn quá hạn.".
     */
    public static void lietKeSachQuaHan() {

        // 1. In tiêu đề
        System.out.println("\n--- Báo cáo sách mượn quá hạn ---");

        boolean coQuaHan = false;

        // 2. Duyệt danh sách phiếu mượn
        for (PhieuMuon phieu : cacPhieuMuonDangHoatDong) {

            // 3. Kiểm tra quá hạn
            if (phieu.isQuaHan()) {

                // 4. In thông tin phiếu
                System.out.println(phieu);
                coQuaHan = true;
            }
        }

        // 5. Nếu không có phiếu nào quá hạn
        if (!coQuaHan) {
            System.out.println("=> Không có sách nào bị mượn quá hạn tại thời điểm hiện tại.");
        }

        System.out.println("--- Kết thúc báo cáo ---");
        System.out.println();   // dòng trống bắt buộc
    }
}