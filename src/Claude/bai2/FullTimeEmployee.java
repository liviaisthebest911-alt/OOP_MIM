package Claude.bai2;

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String id, String name, double monthlySalary) {
        super(id, name);           // gọi constructor lớp cha
        this.monthlySalary = monthlySalary;
    }
    @Override public double calculateSalary()    { return monthlySalary; }
    @Override public String getEmployeeType() { return "FullTime"; }
}
