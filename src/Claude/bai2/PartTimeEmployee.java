package Claude.bai2;

class PartTimeEmployee extends Employee {
    private double hoursWorked, hourlyRate;

    public PartTimeEmployee(String id, String name,
                            double hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate  = hourlyRate;
    }
    @Override public double calculateSalary()    { return hoursWorked * hourlyRate; }
    @Override public String getEmployeeType() { return "PartTime"; }
}