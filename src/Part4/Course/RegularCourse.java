package Part4.Course;

import java.util.*;

/**
 * Class RegularCourse - Môn học thường
 *
 * MÔ TẢ:
 * - Có 4 thuộc tính:
 *   + courseId (String): Mã môn học
 *   + courseName (String): Tên môn học
 *   + credits (int): Số tín chỉ
 *   + studentCount (int): Số sinh viên đăng ký
 *
 * YÊU CẦU:
 * 1. Khai báo thuộc tính
 * 2. Tạo constructor
 * 3. Cài đặt các phương thức:
 *    - getCourseId(), getCourseName(), getCredits(), getStudentCount()
 *    - calculateTuitionFee(): credits * studentCount * 450000
 *    - canOpenClass(): true nếu studentCount >= 10
 *    - getClassification():
 *        + credits >= 5: "Chuyên sâu"
 *        + credits >= 4: "Quan trọng"
 *        + credits >= 3: "Cơ bản"
 *        + còn lại: "Đại cương"
 * 4. Override toString():
 *    [MÔN HỌC THƯỜNG] Mã môn: [courseId]
 *    Tên môn: [courseName]
 *    Số tín chỉ: [credits]
 *    Số sinh viên: [studentCount]
 */
import java.util.*;

/**
 * Class RegularCourse - Môn học thường
 */
class RegularCourse implements Course {

    // Thuộc tính
    private String courseId;
    private String courseName;
    private int credits;
    private int studentCount;

    // Constructor
    public RegularCourse(String courseId, String courseName,
                         int credits, int studentCount) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.studentCount = studentCount;
    }

    // Getter methods
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
    public int getStudentCount() {
        return studentCount;
    }

    // Tính học phí
    @Override
    public double calculateTeachingCost() {
        return credits * studentCount * 450000;
    }

    // Kiểm tra mở lớp
    @Override
    public boolean canOpenClass() {
        return studentCount >= 10;
    }

    // Phân loại môn học
    @Override
    public String getClassification() {
        if (credits >= 5) {
            return "Chuyên sâu";
        } else if (credits >= 4) {
            return "Quan trọng";
        } else if (credits >= 3) {
            return "Cơ bản";
        } else {
            return "Đại cương";
        }
    }

    // toString()
    @Override
    public String toString() {
        return "[MÔN HỌC THƯỜNG] Mã môn: " + courseId + "\n" +
                "Tên môn: " + courseName + "\n" +
                "Số tín chỉ: " + credits + "\n" +
                "Số sinh viên: " + studentCount;
    }
}