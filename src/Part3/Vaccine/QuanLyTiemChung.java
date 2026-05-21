package Part3.Vaccine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTiemChung {
    private static List<NguoiDan> danhSachNguoiDan = new ArrayList<>();
    private static List<TrungTamTiemChung> danhSachTrungTam = new ArrayList<>();

    public static void dangkyNguoiDan(NguoiDan nguoiDan){
        danhSachNguoiDan.add(nguoiDan);
    }

    public static void themTrungTam(TrungTamTiemChung trungTam){
        danhSachTrungTam.add(trungTam);
    }
    /**
     * [YÊU CẦU SINH VIÊN HOÀN THIỆN HÀM NÀY]
     *
     * Xử lý logic nghiệp vụ để đặt một lịch hẹn tiêm chủng.
     * Hàm cần thực hiện các bước sau:
     * 1. Tìm đối tượng NguoiDan trong `danhSachNguoiDan` dựa vào `cccd`. Nếu không tìm thấy, in ra lỗi và trả về `null`.
     * 2. Tìm đối tượng TrungTamTiemChung trong `danhSachTrungTam` dựa vào `idTrungTam`. Nếu không tìm thấy, in ra lỗi và trả về `null`.
     * 3. Kiểm tra điều kiện tiêm:
     * a. Tính toán mũi tiêm cần tiêm tiếp theo (bằng số mũi đã tiêm + 1).
     * b. So sánh với tổng số liều cần thiết của `loaiVaccine`. Nếu người dân đã tiêm đủ số liều, in thông báo và trả về `null`.
     * 4. Kiểm tra kho vaccine của trung tâm:
     * a. Gọi phương thức `coSanVaccine()` của trung tâm để xem có còn `loaiVaccine` được yêu cầu không.
     * b. Nếu không còn, in ra lỗi trung tâm đã hết vaccine và trả về `null`.
     * 5. Nếu tất cả các điều kiện trên đều thỏa mãn, tạo một đối tượng `LichHenTiem` mới với các thông tin đã tìm được,
     * in ra thông báo đặt lịch thành công và trả về đối tượng lịch hẹn vừa tạo.
     *

     */
    public static LichHenTiem datLichHen(String cccd, String idTringTam, Vaccine loaiVaccine){
        NguoiDan nguoiDan = null;
        for (NguoiDan nd : danhSachNguoiDan){ if (nd.getCccd().equalsIgnoreCase(cccd)) {nguoiDan = nd; break;}}

        if(nguoiDan == null) System.out.println("Khong tim thay nguoi dan voi "+cccd); return null;

        TrungTamTiemChung trungTamTiemChung = null;

        for (TrungTamTiemChung tt : danhSachTrungTam) {
            if (tt.getId().equalsIgnoreCase(idTringTam)) {
                trungTamTiemChung = tt;
                break;
            }
        }

        if(trungTamTiemChung == null ) System.out.println("Khong tim thay trung tam tiem trung voi Id"+idTringTam); return null;


        int muiDaTiem = nguoiDan.getSoMuiDaTiem();
        int muiTiepTheo = muiDaTiem + 1;

        if (muiTiepTheo > loaiVaccine.getSoLieuCanThiet()) {
            return null;
        }

        if (!trungTamTiemChung.coSanVaccine(loaiVaccine)) {
            return null;
        }

        LichHenTiem lichHen = new LichHenTiem(
                nguoiDan,
                trungTamTiemChung,
                LocalDate.now(),
                loaiVaccine,
                muiTiepTheo
        );

        System.out.println("[THÀNH CÔNG] Đã đặt lịch hẹn cho "
                + nguoiDan.getHoTen()
                + " tiêm mũi "
                + muiTiepTheo
                + " - "
                + loaiVaccine.getTenVaccine()
                + " tại "
                + trungTamTiemChung.getTenTrungTam()
                + ".");

        return lichHen;

    }

    public static void ghiNhanTiem(LichHenTiem lichHen) {

        if (lichHen == null) return;

        NguoiDan nguoiDan = lichHen.getNguoiDan();
        TrungTamTiemChung trungTam = lichHen.getTrungTam();
        Vaccine loaiVaccine = lichHen.getLoaiVaccine();

        String thongTinMuiTiem = "Mũi " + lichHen.getMuiSo()
                + " - " + loaiVaccine.getTenVaccine()
                + " tại " + trungTam.getTenTrungTam();

        nguoiDan.themMuiTiem(thongTinMuiTiem);

        trungTam.suDungVaccine(loaiVaccine);

        System.out.println("[GHI NHẬN] "
                + nguoiDan.getHoTen()
                + " đã tiêm thành công. Kho của "
                + trungTam.getTenTrungTam()
                + " đã được cập nhật.");
    }

    public static void inThongTinHeThong() {
        System.out.println("\n========== BÁO CÁO HỆ THỐNG ==========");
        System.out.println("--- Danh sách người dân ---");
        for (NguoiDan nd : danhSachNguoiDan) {
            System.out.println(nd);
        }
        System.out.println("\n--- Tình hình trung tâm tiêm chủng ---");
        for (TrungTamTiemChung tt : danhSachTrungTam) {
            System.out.println(tt);
        }
        System.out.println("=======================================");
    }
}
