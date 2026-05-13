package Part1.BuldingCompany;

public class Manager extends Employee {
    private double bonus;

    public Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        // Lương của quản lý = lương cơ bản + thưởng
        return baseSalary + bonus;
    }

    @Override
    public String getWorkReport() {
        return "Manager " + name + " is managing the team and projects.";
    }
}
