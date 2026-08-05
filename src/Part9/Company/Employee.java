package Part9.Company;

/**
 * CLASS: Employee
 * MÔ TẢ: Class đại diện cho một nhân viên trong công ty
 *
 * THUỘC TÍNH:
 * - id: String - Mã nhân viên (ví dụ: "EMP001")
 * - name: String - Tên nhân viên
 * - department: String - Phòng ban (IT, HR, Sales, Finance, Marketing)
 * - salary: double - Lương (VND)
 * - yearsOfService: int - Số năm làm việc
 *
 * YÊU CẦU:
 * 1. Implement interface IEmployee
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Override toString() để trả về chuỗi theo format:
 *    "Employee{id='...', name='...', department='...', salary=..., yearsOfService=...}"
 */
public class Employee implements IEmployee {
    private String id;
    private String name;
    private String department;
    private double salary;
    private int yearsOfService;

    public Employee(String id, String name, String department, double salary, int yearsOfService) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.yearsOfService = yearsOfService;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public int getYearsOfService() {
        return yearsOfService;
    }

    @Override
    public String toString() {
        return String.format("Employee{id='%s', name='%s', department='%s', salary=%.0f, yearsOfService=%d}",
                id, name, department, salary, yearsOfService);
    }
}
