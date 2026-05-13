package Part1.BuldingCompany;

import java.util.Arrays;
import java.util.Comparator;

public class Company {
    private final String companyName;
    private final Employee[] employees;
    private int employeeCount;

    public Company(String companyName, int maxEmployees){
        this.companyName=companyName;
        this.employees= new Employee[maxEmployees];
        this.employeeCount=0;
    }

    public void addEmployee(Employee employee){
        if(employeeCount < employees.length){
            employees[employeeCount] = employee;
            employeeCount++;
        }else {
            System.out.println("Cannot add more employees. The company is full.");
        }
    }

    public double getTotalPayroll(){
        int total = 0;
        for (Employee e : employees){
            total += e.calculateSalary();

        }
        return total;
    }

    public Employee findHighestPaidEmployee() {

        if (employeeCount == 0) {
            return null;
        }

        return Arrays.stream(employees, 0, employeeCount)
                .max(Comparator.comparingDouble(Employee::calculateSalary))
                .orElse(null);
    }

    public void generateWorkReports() {
        System.out.println("\n--- Work Reports for " + companyName + " ---");
        for(Employee em : employees){
            System.out.println(em.getWorkReport());
        }
        System.out.println("------------------------------------");
    }


    public void displayAllEmployees() {
        System.out.println("\n--- Employee List for " + companyName + " ---");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i]); // Tự động gọi toString()
        }
        System.out.println("------------------------------------");
    }



}
