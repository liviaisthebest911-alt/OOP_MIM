package Claude.bai2;

public abstract class Employee implements Payable {

    // 2b — Đóng gói: private + final
    private final String id;
    private final String name;

    // 2c — Constructor
    public Employee(String id, String name) {
        this.id   = id;
        this.name = name;
    }

    // 2d — Getter (không setter vì final)
    public String getId()   { return id; }
    public String getName() { return name; }

    // 2e — Lớp con override cái này
    public String getEmployeeType() { return "Employee"; }

    // 2e — toString gọi calculateSalary() → đa hình
    @Override
    public String toString() {
        return String.format("[%s] %-20s | %-11s | Luong: %,.2f VND",
                id, name, getEmployeeType(), calculateSalary());
    }
    // calculateSalary() KHÔNG viết ở đây — lớp con tự viết
}
