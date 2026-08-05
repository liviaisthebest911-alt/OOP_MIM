package Part9.Course;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface IUniversity {
    void addStudent(IStudent student);
    void addCourse(ICourse course);
    java.util.List<IStudent> getAllStudents();
    java.util.List<IStudent> findStudentsByMajor(String major);
    java.util.List<IStudent> findHighPerformers(double minGpa);
    java.util.List<IStudent> findStudentsYoungerThan(int maxAge);
    double getAverageGpa();
    java.util.Map<String, Long> getStudentCountByMajor();
    java.util.Map<String, Double> getAverageGpaByMajor();
    IStudent getTopStudent();
    java.util.List<ICourse> getAllCourses();
    java.util.List<ICourse> findCoursesByInstructor(String instructor);
    java.util.List<ICourse> findCoursesWithMinCredits(int minCredits);
    int getTotalEnrollments();
    java.util.Map<String, Long> getCourseCountByInstructor();
    double getAverageClassSize();
    ICourse getMostPopularCourse();
}
