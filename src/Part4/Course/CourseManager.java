package Part4.Course;

import java.util.*;

class CourseManager {
    private List<Course> courses;

    public CourseManager() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    /**
     * Tìm môn học theo mã.
     * Đầu vào: courseId
     * Đầu ra:
     * - trả về Course nếu tìm thấy
     * - trả về null nếu không tìm thấy
     */
    public Course findCourseById(String courseId) {
        if (courseId == null) return null;

        return courses.stream()
                .filter(m -> m.getCourseId().equalsIgnoreCase(courseId))
                .findFirst()
                .orElse(null);
    }


    /**
     * Tìm các môn học có tên chứa từ khóa.
     * Đầu vào: keyword
     * Đầu ra: danh sách môn học phù hợp, nếu không có thì trả về danh sách rỗng
     */
    public List<Course> searchCoursesByName(String keyword) {
        if (keyword == null) {
            return List.of();
        }

        String lowerKeyword = keyword.toLowerCase();

        return courses.stream()
                .filter(m -> m.getCourseName().toLowerCase().contains(lowerKeyword))
                .toList();
    }


    /**
     * Lấy danh sách môn học đủ điều kiện mở lớp.
     * Đầu ra: danh sách môn học thỏa điều kiện, nếu không có thì trả về danh sách rỗng
     */
    public List<Course> getCoursesCanOpen() {
        return courses.stream()
                .filter(Course::canOpenClass)
                .toList();
    }

    /**
     * Lấy top n môn học có số tín chỉ cao nhất.
     * Đầu vào: n
     * Đầu ra: danh sách tối đa n môn học
     */
    public List<Course> getTopCourses(int n) {
        return courses.stream()
                .sorted(Comparator.comparingInt(Course::getCredits))
                .limit(n)
                .toList();
    }

    /**
     * Sắp xếp môn học theo số tín chỉ.
     * Đầu vào:
     * - ascending = true: tăng dần
     * - ascending = false: giảm dần
     * Đầu ra: danh sách mới đã sắp xếp
     */
    //public List<Course> sortByCredits(boolean ascending) {
    //    List<Course> sorted = new ArrayList<>(courses);
    //    sorted.sort((a,b) -> (ascending) ? Double.compare(a.getCredits(), b.getCredits()) : Double.compare(b.getCredits(), a.getCredits()));
    //    return sorted;


    public List<Course> sortByCredits(boolean ascending) {
        return courses.stream()
                .sorted((a, b) -> ascending
                        ? Integer.compare(a.getCredits(), b.getCredits())
                        : Integer.compare(b.getCredits(), a.getCredits()))
                .toList();
    }
    /*   public List<Course> sortByCredits(boolean ascending) {
            Comparator<Course> comparator = Comparator.comparingInt(Course::getCredits);

            if (!ascending) {
                comparator = comparator.reversed();
            }

            return courses.stream()
                    .sorted(comparator)
                    .toList();
        }
      */


    /**
     * Sắp xếp môn học theo tên A-Z.
     * Đầu ra: danh sách mới đã sắp xếp
     */
    public List<Course> sortByName() {
        return courses.stream()
                .sorted(Comparator.comparing(Course::getCourseName))
                .toList();
    }


    /**
     * Tính số tín chỉ trung bình.
     */
    public double calculateAverageCredits() {
        if (courses.isEmpty()) {
            return 0.0;
        }

        double sum = 0;

        for (Course course : courses) {
            sum += course.getCredits();
        }

        return sum / courses.size();
    }

    /**
     * Tính tổng học phí của tất cả môn học.
     */
    public double calculateTeachingCost(){
        double total = 0;

        for (Course course : courses) {
            total += course.calculateTeachingCost();
        }

        return total;
    }
}
