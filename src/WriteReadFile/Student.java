package WriteReadFile;

import java.io.Serializable;

/**
 * Class Student áp dụng Encapsulation: tất cả field là private,
 * chỉ truy cập qua getter/setter.
 *
 * implements Serializable: "đánh dấu" để JVM biết class này
 * có thể được chuyển đổi thành luồng byte (ghi/đọc file nhị phân).
 */
public class Student implements Serializable {

    // serialVersionUID: định danh phiên bản class.
    // Nếu bạn thay đổi class sau này mà không đổi số này,
    // JVM sẽ báo lỗi khi đọc file cũ. Luôn khai báo tường minh!
    private static final long serialVersionUID = 1L;

    // --- Fields (private = đóng gói) ---
    private String id;
    private String name;
    private double gpa;

    // Constructor
    public Student(String id, String name, double gpa) {
        this.id   = id;
        this.name = name;
        this.gpa  = gpa;
    }

    // --- Getters (chỉ đọc từ bên ngoài) ---
    public String getId()   { return id; }
    public String getName() { return name; }
    public double getGpa()  { return gpa; }

    // --- Setters (kiểm soát ghi từ bên ngoài) ---
    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Tên không được để trống!");
        this.name = name;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0)
            throw new IllegalArgumentException("GPA phải trong khoảng 0.0 – 4.0");
        this.gpa = gpa;
    }

    // toString() để in thông tin ra màn hình / ghi file text
    @Override
    public String toString() {
        return id + "," + name + "," + gpa;
    }
}