package Part9.Course;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * CLASS: Course
 * MÔ TẢ: Class đại diện cho một khóa học
 *
 * THUỘC TÍNH:
 * - courseId: String - Mã khóa học (ví dụ: "CS101")
 * - courseName: String - Tên khóa học
 * - credits: int - Số tín chỉ
 * - instructor: String - Giảng viên
 * - enrolledStudents: List<IStudent> - Danh sách sinh viên đăng ký
 *
 * YÊU CẦU:
 * 1. Implement interface ICourse
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Thêm method getEnrollmentCount() (không có trong interface):
 *    - Trả về số lượng sinh viên đăng ký
 * 5. Thêm method getAverageGpaOfStudents() (không có trong interface):
 *    - Tính GPA trung bình của sinh viên trong khóa học
 *    - SỬ DỤNG STREAM: enrolledStudents.stream().mapToDouble(...).average().orElse(0.0)
 * 6. Override toString() để trả về chuỗi theo format:
 *    "Course{id='...', name='...', credits=..., instructor='...', enrolled=...}"
 */
public class Course implements ICourse {
    private String courseId;
    private String courseName;
    private int credits;
    private String instructor;
    private List<IStudent> enrolledStudents;

    public Course(String courseId, String courseName, int credits,
                  String instructor, List<IStudent> enrolledStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.instructor = instructor;
        this.enrolledStudents = enrolledStudents;
    }

    @Override
    public String getCourseId() {
        return courseId;
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public int getCredits() {
        return credits;
    }

    @Override
    public String getInstructor() {
        return instructor;
    }

    @Override
    public List<IStudent> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Methods bổ sung - PHẢI SỬ DỤNG STREAM
    public int getEnrollmentCount() {
        return enrolledStudents.size();
    }

    public double getAverageGpaOfStudents() {
        return enrolledStudents.stream()
                .mapToDouble(IStudent::getGpa)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return String.format("Course{id='%s', name='%s', credits=%d, instructor='%s', enrolled=%d}",
                courseId, courseName, credits, instructor, enrolledStudents.size());
    }
}