package Part3.Vaccine;
import java.lang.classfile.instruction.NewMultiArrayInstruction;
import java.util.*;


public class TrungTamTiemChung {
    private final String id;
    private String tenTrungTam;
    private String diaChi;
    private Map<Vaccine, Integer> khoVaccine;

    public TrungTamTiemChung(String id, String tenTrungTam, String diaChi) {
        this.id = id;
        this.tenTrungTam = tenTrungTam;
        this.diaChi = diaChi;
        this.khoVaccine = new HashMap<>();

    }

    public String getId() {
        return id;
    }

    public String getTenTrungTam() {
        return tenTrungTam;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public Map<Vaccine, Integer> getKhoVaccine() {
        return khoVaccine;
    }

    /**
     * Nhập thêm vaccine vào kho của trung tâm.
     *
     * @param vaccine Loại vaccine.
     * @param soLuong Số lượng cần thêm.
     */
    public void nhapKhoVaccine(Vaccine vaccine, int soLuong) {
        khoVaccine.put(vaccine, khoVaccine.getOrDefault(vaccine, 0) + soLuong);
        System.out.println("[KHO] Trung tâm " + tenTrungTam + ": Đã nhập " + soLuong + " liều " + vaccine.getTenVaccine());
    }

    /**
     * Kiểm tra xem trung tâm có đủ một loại vaccine cụ thể không.
     *
     * @param vaccine Loại vaccine cần kiểm tra.
     * @return true nếu có sẵn ít nhất 1 liều, ngược lại false.
     */
    public boolean coSanVaccine(Vaccine vaccine) {
        return khoVaccine.getOrDefault(vaccine, 0) > 0;
    }

    /**
     * Sử dụng một liều vaccine từ kho.
     *
     * @param vaccine Loại vaccine đã sử dụng.
     */
    public void suDungVaccine(Vaccine vaccine) {
        if (coSanVaccine(vaccine)) {
            khoVaccine.put(vaccine, khoVaccine.get(vaccine) - 1);
        }
    }

    @Override
    public String toString() {
        return "TrungTamTiemChung[ID=" + id + ", Ten='" + tenTrungTam + "', Kho=" + khoVaccine + "]";
    }
}
