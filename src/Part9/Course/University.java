package Part9.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * CLASS: University
 * MÔ TẢ: Class quản lý trường đại học, sử dụng Java Stream và Lambda
 *
 * THUỘC TÍNH:
 * - students: List<IStudent> - Danh sách sinh viên
 * - courses: List<ICourse> - Danh sách khóa học
 *
 * YÊU CẦU:
 * 1. Implement interface IUniversity
 * 2. Khởi tạo 2 ArrayList trong constructor
 * 3. Implement các methods SAU ĐÂY PHẢI SỬ DỤNG JAVA STREAM VÀ LAMBDA:
 *
 *    === QUẢN LÝ SINH VIÊN ===
 *    - addStudent(IStudent student): Thêm sinh viên
 *
 *    - getAllStudents(): Trả về tất cả sinh viên
 *
 *    - findStudentsByMajor(String major): Tìm sinh viên theo chuyên ngành
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findHighPerformers(double minGpa): Tìm sinh viên có GPA >= minGpa
 *      (sắp xếp giảm dần theo GPA)
 *
 *    - findStudentsYoungerThan(int maxAge): Tìm sinh viên có tuổi < maxAge
 *      (sắp xếp tăng dần theo tuổi)
 *
 *    - getAverageGpa(): Tính GPA trung bình của toàn trường
 *      (average, trả về 0.0 nếu không có sinh viên)
 *
 *    - getStudentCountByMajor(): Đếm số sinh viên theo chuyên ngành
 *      (groupingBy + counting)
 *
 *    - getAverageGpaByMajor(): Tính GPA trung bình theo chuyên ngành
 *      (groupingBy + averagingDouble)
 *
 *    - getTopStudent(): Tìm sinh viên có GPA cao nhất
 *      (max với Comparator.comparingDouble, trả về null nếu không có)
 *
 *    === QUẢN LÝ KHÓA HỌC ===
 *    - addCourse(ICourse course): Thêm khóa học
 *
 *    - getAllCourses(): Trả về tất cả khóa học
 *
 *    - findCoursesByInstructor(String instructor): Tìm khóa học theo giảng viên
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findCoursesWithMinCredits(int minCredits): Tìm khóa học có tín chỉ >= minCredits
 *      (sắp xếp giảm dần theo credits)
 *
 *    - getTotalEnrollments(): Đếm tổng số lượt đăng ký (tất cả sinh viên trong tất cả khóa)
 *      (flatMap để lấy enrolledStudents, sau đó count)
 *
 *    - getCourseCountByInstructor(): Đếm số khóa học theo giảng viên
 *      (groupingBy + counting)
 *
 *    - getAverageClassSize(): Tính số sinh viên trung bình mỗi lớp
 *      (mapToInt với getEnrollmentCount, sau đó average)
 *
 *    - getMostPopularCourse(): Tìm khóa học có nhiều sinh viên đăng ký nhất
 *      (max với Comparator.comparingInt, trả về null nếu không có)
 *
 * 4. Override toString() để trả về:
 *    "University{totalStudents=..., totalCourses=..., averageGpa=...}"
 */


public class University implements IUniversity {
    private List<IStudent> students;
    private List<ICourse> courses;

    public University() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // === QUẢN LÝ SINH VIÊN ===

    @Override
    public void addStudent(IStudent student) {
        students.add(student);
    }

    @Override
    public void addCourse(ICourse course) {
        courses.add(course);
    }

    @Override
    public List<IStudent> getAllStudents() {
        return students.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<IStudent> findStudentsByMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }

    @Override
    public List<IStudent> findHighPerformers(double minGpa) {
        return students.stream()
                .filter(s -> s.getGpa() >= minGpa)
                .sorted(Comparator.comparingDouble(IStudent::getGpa).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<IStudent> findStudentsYoungerThan(int maxAge) {
        return students.stream()
                .filter(s -> s.getAge() < maxAge)
                .sorted(Comparator.comparingInt(IStudent::getAge))
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageGpa() {
        return students.stream()
                .mapToDouble(IStudent::getGpa)
                .average()
                .orElse(0.0);
    }

    @Override
    public Map<String, Long> getStudentCountByMajor() {
        return students.stream()
                .collect(Collectors.groupingBy(
                        IStudent::getMajor,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Double> getAverageGpaByMajor() {
        return students.stream()
                .collect(Collectors.groupingBy(
                        IStudent::getMajor,
                        Collectors.averagingDouble(IStudent::getGpa)
                ));
    }

    @Override
    public IStudent getTopStudent() {
        return students.stream()
                .max(Comparator.comparingDouble(IStudent::getGpa))
                .orElse(null);
    }

    // === QUẢN LÝ KHÓA HỌC ===

    @Override
    public List<ICourse> getAllCourses() {
        return courses.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<ICourse> findCoursesByInstructor(String instructor) {
        return courses.stream()
                .filter(c -> c.getInstructor().equalsIgnoreCase(instructor))
                .collect(Collectors.toList());
    }

    @Override
    public List<ICourse> findCoursesWithMinCredits(int minCredits) {
        return courses.stream()
                .filter(c -> c.getCredits() >= minCredits)
                .sorted(Comparator.comparingInt(ICourse::getCredits).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalEnrollments() {
        return (int) courses.stream()
                .flatMap(c -> c.getEnrolledStudents().stream())
                .count();
    }

    @Override
    public Map<String, Long> getCourseCountByInstructor() {
        return courses.stream()
                .collect(Collectors.groupingBy(
                        ICourse::getInstructor,
                        Collectors.counting()
                ));
    }

    @Override
    public double getAverageClassSize() {
        return courses.stream()
                .mapToInt(c -> c.getEnrolledStudents().size())
                .average()
                .orElse(0.0);
    }

    @Override
    public ICourse getMostPopularCourse() {
        return courses.stream()
                .max(Comparator.comparingInt(c -> c.getEnrolledStudents().size()))
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.format(
                "University{totalStudents=%d, totalCourses=%d, averageGpa=%.2f}",
                students.size(), courses.size(), getAverageGpa()
        );
    }
}