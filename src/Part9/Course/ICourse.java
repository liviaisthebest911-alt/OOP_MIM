package Part9.Course;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface ICourse {
    String getCourseId();
    String getCourseName();
    int getCredits();
    String getInstructor();
    java.util.List<IStudent> getEnrolledStudents();
}