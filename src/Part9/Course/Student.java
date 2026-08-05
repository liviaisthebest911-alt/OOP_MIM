package Part9.Course;

/**
 * CLASS: Student
 * MÔ TẢ: Class đại diện cho một sinh viên
 *
 * THUỘC TÍNH:
 * - studentId: String - Mã sinh viên (ví dụ: "SV001")
 * - name: String - Tên sinh viên
 * - age: int - Tuổi
 * - major: String - Chuyên ngành (Computer Science, Business, Engineering, Medicine, Arts)
 * - gpa: double - Điểm trung bình (thang điểm 4.0)
 *
 * YÊU CẦU:
 * 1. Implement interface IStudent
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Override toString() để trả về chuỗi theo format:
 *    "Student{id='...', name='...', age=..., major='...', gpa=...}"
 */
public class Student implements IStudent {
    private String studentId;
    private String name;
    private int age;
    private String major;
    private double gpa;

    public Student(String studentId, String name, int age, String major, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.major = major;
        this.gpa = gpa;
    }

    @Override
    public String getStudentId() {
        return studentId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getMajor() {
        return major;
    }

    @Override
    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return String.format("Student{id='%s', name='%s', age=%d, major='%s', gpa=%.2f}",
                studentId, name, age, major, gpa);
    }
}
