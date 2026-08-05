package Part9.Company;
/**
 * CLASS: Company
 * MÔ TẢ: Class quản lý công ty, sử dụng Java Stream và Lambda để xử lý dữ liệu nhân viên
 *
 * THUỘC TÍNH:
 * - employees: List<IEmployee> - Danh sách nhân viên trong công ty
 *
 * YÊU CẦU:
 * 1. Implement interface ICompany
 * 2. Khởi tạo ArrayList trong constructor
 * 3. Implement các methods SAU ĐÂY PHẢI SỬ DỤNG JAVA STREAM VÀ LAMBDA:
 *
 *    - addEmployee(IEmployee employee): Thêm nhân viên vào công ty
 *
 *    - getAllEmployees(): Trả về tất cả nhân viên
 *
 *    - findEmployeesByDepartment(String department): Tìm nhân viên theo phòng ban
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findHighEarners(double minSalary): Tìm nhân viên có lương >= minSalary
 *      (sắp xếp giảm dần theo lương)
 *
 *    - getTop5HighestPaid(): Lấy 5 nhân viên có lương cao nhất
 *      (sắp xếp giảm dần theo lương, giới hạn 5 người)
 *
 *    - getAverageSalary(): Tính lương trung bình của tất cả nhân viên
 *      (sử dụng average, trả về 0 nếu không có nhân viên)
 *
 *    - getTotalSalaryByDepartment(String department): Tính tổng lương của một phòng ban
 *      (filter theo department, sau đó sum)
 *
 *    - getAverageSalaryByDepartment(): Tính lương trung bình theo từng phòng ban
 *      (trả về Map<String, Double>, sử dụng groupingBy và averagingDouble)
 *
 *    - getEmployeeCountByDepartment(): Đếm số nhân viên theo từng phòng ban
 *      (trả về Map<String, Long>, sử dụng groupingBy và counting)
 *
 *    - findEmployeeById(String id): Tìm nhân viên theo mã
 *      (sử dụng filter và findFirst, trả về null nếu không tìm thấy)
 *
 *    - countEmployeesWithExperience(int minYears): Đếm số nhân viên có kinh nghiệm >= minYears
 *      (sử dụng filter và count)
 *
 *    - getDepartmentNames(): Lấy danh sách tên phòng ban duy nhất, đã sắp xếp
 *      (sử dụng map, distinct, sorted)
 *
 * 4. Override toString() để trả về:
 *    "Company{totalEmployees=..., averageSalary=...}"
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Company implements ICompany {
    private List<IEmployee> employees;

    public Company() {
        this.employees = new ArrayList<>();
    }

    @Override
    public void addEmployee(IEmployee employee) {
        employees.add(employee);
    }

    @Override
    public List<IEmployee> getAllEmployees() {
        return employees.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<IEmployee> findEmployeesByDepartment(String department) {
        return employees.stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    @Override
    public List<IEmployee> findHighEarners(double minSalary) {
        return employees.stream()
                .filter(emp -> emp.getSalary() >= minSalary)
                .sorted(Comparator.comparingDouble(IEmployee::getSalary).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<IEmployee> getTop5HighestPaid() {
        return employees.stream()
                .sorted(Comparator.comparingDouble(IEmployee::getSalary).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageSalary() {
        return employees.stream()
                .mapToDouble(IEmployee::getSalary)
                .average()
                .orElse(0.0);
    }

    @Override
    public double getTotalSalaryByDepartment(String department) {
        return employees.stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase(department))
                .mapToDouble(IEmployee::getSalary)
                .sum();
    }

    @Override
    public Map<String, Double> getAverageSalaryByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        IEmployee::getDepartment,
                        Collectors.averagingDouble(IEmployee::getSalary)
                ));
    }

    @Override
    public Map<String, Long> getEmployeeCountByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        IEmployee::getDepartment,
                        Collectors.counting()
                ));
    }

    @Override
    public IEmployee findEmployeeById(String id) {
        return employees.stream()
                .filter(emp -> emp.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public long countEmployeesWithExperience(int minYears) {
        return employees.stream()
                .filter(emp -> emp.getYearsOfService() >= minYears)
                .count();
    }

    @Override
    public List<String> getDepartmentNames() {
        return employees.stream()
                .map(IEmployee::getDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Company{totalEmployees=%d, averageSalary=%.0f}",
                employees.size(), getAverageSalary());
    }
}