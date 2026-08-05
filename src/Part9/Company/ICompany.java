package Part9.Company;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface ICompany {
    void addEmployee(IEmployee employee);
    java.util.List<IEmployee> getAllEmployees();
    java.util.List<IEmployee> findEmployeesByDepartment(String department);
    java.util.List<IEmployee> findHighEarners(double minSalary);
    java.util.List<IEmployee> getTop5HighestPaid();
    double getAverageSalary();
    double getTotalSalaryByDepartment(String department);
    java.util.Map<String, Double> getAverageSalaryByDepartment();
    java.util.Map<String, Long> getEmployeeCountByDepartment();
    IEmployee findEmployeeById(String id);
    long countEmployeesWithExperience(int minYears);
    java.util.List<String> getDepartmentNames();
}
