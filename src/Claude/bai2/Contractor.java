package Claude.bai2;

class Contractor extends Employee {
    private static final double TAX_RATE = 0.10;
    private double projectFee;

    public Contractor(String id, String name, double projectFee) {
        super(id, name);
        this.projectFee = projectFee;
    }
    @Override public double calculateSalary()    { return projectFee * (1 - TAX_RATE); }
    @Override public String getEmployeeType() { return "Contractor"; }
}
