package Part5.Student;

import java.util.*;
class RegularStudent implements Student {
    // TODO: Khai báo thuộc tính

    // TODO: Constructor

    // TODO: Implement các phương thức

    // TODO: Override toString()
    private String studentId;
    private String name;
    private double gpa;
    private int credits;

    public RegularStudent(String studentId, String name, double gpa, int credits) {
        this.studentId = studentId;
        this.name=name;
        this.gpa=gpa;
        this.credits=credits;
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
    public double getGPA() {
        return gpa;
    }

    @Override
    public int getCredits() {
        return credits;
    }

    @Override
    public double calculateTuition() {
        return credits*200000;
    }

    @Override
    public boolean canGraduate() {
        if(credits >= 120 && gpa >= 2.0){
            return true;
        }
        return false;
    }

    @Override
    public String getAcademicRank() {
        double[] thresholds = {9.0, 8.0, 6.5, 5.0};
        String[] ranks = {"Xuất sắc", "Giỏi", "Khá", "Trung bình", "Yếu"};

        for (int i = 0; i < thresholds.length; i++) {
            if (gpa >= thresholds[i]) {
                return ranks[i];
            }
        }
        return ranks[ranks.length - 1];
    }

    public String toString(){
        return "[SINH VIÊN THƯỜNG] Mã SV: "+studentId+"\n" +
                "Tên: "+name+"\n" +
                "GPA: "+gpa+"\n" +
                "Tín chỉ: "+credits;
    }
}
