package Part1.BuldingCompany;

public class Developer extends Employee {
    private int overtimeHours;
    private double hourlyRate;

    public Developer(String name, double baseSalary, int overtimeHours, double hourlyRate) {
        super(name, baseSalary);
        this.overtimeHours = overtimeHours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        // Lương của lập trình viên = lương cơ bản + (giờ làm thêm * lương mỗi giờ)
        return baseSalary + (overtimeHours * hourlyRate);
    }

    @Override
    public String getWorkReport() {
        return "Developer " + name + " is writing code and fixing bugs.";
    }
}