package Part5.Student;

import java.util.*;
class ScholarshipStudent implements Student {
    private String studentId;
    private String name;
    private double gpa;
    private int credits;
    private double scholarshipAmount;

    public ScholarshipStudent(String studentId, String name, double gpa, int credits,double scholarshipAmount) {
        this.studentId = studentId;
        this.name=name;
        this.gpa=gpa;
        this.credits=credits;
        this.scholarshipAmount=scholarshipAmount;
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
        double hPhiThuc = credits*200000 - scholarshipAmount;
        if(hPhiThuc<0){
            return 0;
        }else {
            return hPhiThuc;
        }
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
        String[] rank = {"Yếu", "Trung bình", "Khá", "Giỏi", "Xuất sắc"};
        int index = 0;

        if(gpa >= 5.0) index++;
        if(gpa >= 6.5) index++;
        if(gpa >= 8.0) index++;
        if(gpa >= 9.0) index++;

        return rank[index];
    }
    public String toString(){
        return "[SINH VIÊN HỌC BỔNG] Mã SV: "+studentId+"\n" +
                "Tên: "+name+"\n" +
                "GPA: "+gpa+"\n" +
                "Tín chỉ: "+credits+"\n"+
                "Học bổng: "+scholarshipAmount+" VNĐ";
    }
}