package Part1.BuldingCompany;

public abstract class Employee {
    private static int nextId = 1;
    protected int id;
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.id = nextId++;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Phương thức trừu tượng: Mỗi lớp con BẮT BUỘC phải định nghĩa lại
    // cách tính lương riêng của mình.
    public abstract double calculateSalary();

    // Phương thức trừu tượng: Mỗi lớp con BẮT BUỘC phải định nghĩa lại
    // báo cáo công việc của mình.
    public abstract String getWorkReport();

    @Override
    public String toString() {
        // Sử dụng phương thức calculateSalary() đã được ghi đè ở lớp con
        return "ID: " + id + ", Name: " + name + ", Calculated Salary: $" + String.format("%.2f", calculateSalary());
    }
}
