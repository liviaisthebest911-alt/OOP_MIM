package Part4.Course;

import java.util.*;

/**
 * Class PremiumCourse - Môn học chất lượng cao
 *
 * MÔ TẢ:
 * - Có 5 thuộc tính:
 *   + courseId (String): Mã môn học
 *   + courseName (String): Tên môn học
 *   + credits (int): Số tín chỉ
 *   + studentCount (int): Số sinh viên đăng ký
 *   + facilityFee (double): Phụ phí cơ sở vật chất
 *
 * YÊU CẦU:
 * 1. Khai báo thuộc tính
 * 2. Tạo constructor
 * 3. Cài đặt các phương thức:
 *    - getCourseId(), getCourseName(), getCredits(), getStudentCount()
 *    - calculateTuitionFee():
 *        + Học phí cơ bản = credits * studentCount * 450000
 *        + Tổng học phí = học phí cơ bản + facilityFee
 *    - canOpenClass(): true nếu studentCount >= 10
 *    - getClassification(): giống RegularCourse
 * 4. Override toString():
 *    [MÔN HỌC CHẤT LƯỢNG CAO] Mã môn: [courseId]
 *    Tên môn: [courseName]
 *    Số tín chỉ: [credits]
 *    Số sinh viên: [studentCount]
 *    Phụ phí CSVC: [facilityFee] VNĐ
 */



class PremiumCourse implements Course {

    // Thuộc tính
    private String courseId;
    private String courseName;
    private int credits;
    private int studentCount;
    private double facilityFee;

    // Constructor
    public PremiumCourse(String courseId, String courseName,
                         int credits, int studentCount,
                         double facilityFee) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.studentCount = studentCount;
        this.facilityFee = facilityFee;
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

    public double calculateTeachingCost() {
        double baseFee = credits * studentCount * 450000;
        return baseFee + facilityFee;
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
        return "[MÔN HỌC CHẤT LƯỢNG CAO] Mã môn: " + courseId + "\n" +
                "Tên môn: " + courseName + "\n" +
                "Số tín chỉ: " + credits + "\n" +
                "Số sinh viên: " + studentCount + "\n" +
                "Phụ phí CSVC: " + facilityFee + " VNĐ";
    }
}