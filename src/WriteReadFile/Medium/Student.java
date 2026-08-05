package WriteReadFile.Medium;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(gpa, student.gpa) == 0 && Objects.equals(id, student.id) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gpa);
    }

    // toString() để in thông tin ra màn hình / ghi file text
    @Override
    public String toString() {
        return id + "," + name + "," + gpa;
    }
}
